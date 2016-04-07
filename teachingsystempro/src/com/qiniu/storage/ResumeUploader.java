package com.qiniu.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import se.akerfeldt.com.google.gson.Gson;

import com.qiniu.common.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.model.ResumeBlockInfo;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;


/**
 * 閸掑棛澧栨稉濠佺炊
 * 閺傚洦銆傞敍锟絘 href="http://developer.qiniu.com/docs/v6/api/overview/up/chunked-upload.html">
 * 閸掑棛澧栨稉濠佺炊</a>
 * <p/>
 * 閸掑棛澧栨稉濠佺炊闁俺绻冪亸鍡曠娑擃亝鏋冩禒璺哄瀻閸撹弓璐熼崶鍝勭暰婢堆冪毈閻ㄥ嫬娼�4M)閿涘本鐦″▎鈥茬瑐娴肩姳绔存稉顏勬健閻ㄥ嫬鍞寸�鐧哥礄閺堝秴濮熺粩顖氬涧閸掑棗娼￠敍灞剧梾閺堝鍨庨悧鍥风礆閵嗭拷 * 缁涘绶熼幍锟芥箒閸ф鍏樻稉濠佺炊鐎瑰本鍨氭稊瀣倵閿涘苯鍟�亸鍡氱箹娴滄稑娼￠幏鍏煎复鐠ч攱娼甸敍灞剧�閹存劒绔存稉顏勭暚閺佸娈戦弬鍥︽閵嗭拷 * 閸欙箑顧囬崚鍡欏娑撳﹣绱舵潻妯绘暜閹镐胶閭ぐ鏇氱瑐娴肩姾绻樻惔锔肩礉婵″倹鐏夐張顒侇偧娑撳﹣绱剁悮顐ｆ畯閸嬫粣绱濋柇锝勭疄娑撳顐兼潻妯哄讲娴犮儰绮犳稉濠冾偧
 * 娑撳﹥顐肩�灞惧灇閻ㄥ嫭鏋冩禒璺轰焊缁夎缍呯純顕嗙礉缂佈呯敾瀵拷顬婃稉濠佺炊閿涘矁绻栭弽宄版皑鐎圭偟骞囨禍鍡樻焽閻愬湱鐢绘导鐘插閼冲锟� * <p/>
 * 閺堝秴濮熺粩顖滅秹缂佹粏绶濈粙鍐茬暰閿涘矁绶濇径褎鏋冩禒璁圭礄婵★拷00M娴犮儰绗傞敍澶嬪闂囷拷顪呯亸鍡楁健鐠佹澘缍嶆穱婵嗙摠娑撳娼甸妴锟�* 鐏忓繑鏋冩禒鑸电梾閺堝绻�憰渚婄礉閸欘垯浜掗張澶嬫櫏閸︽澘鐤勯悳鏉裤亣閺傚洣娆㈤惃鍕瑐娴肩姰锟� */
public final class ResumeUploader {
    private final String upToken;
    private final String key;
    private final File f;
    private final long size;
    private final StringMap params;
    private final String mime;
    private final String[] contexts;
    private final Client client;
    private final byte[] blockBuffer;
    private final Recorder recorder;
    private final String recorderKey;
    private final long modifyTime;
    private final RecordHelper helper;
    private FileInputStream file;
    private String host;

    ResumeUploader(Client client, String upToken, String key, File file,
                   StringMap params, String mime, Recorder recorder, String recorderKey) {
        this.client = client;
        this.upToken = upToken;
        this.key = key;
        this.f = file;
        this.size = file.length();
        this.params = params;
        this.mime = mime == null ? Client.DefaultMime : mime;
        this.host = Config.zone.upHost;
        long count = (size + Config.BLOCK_SIZE - 1) / Config.BLOCK_SIZE;
        this.contexts = new String[(int) count];
        this.blockBuffer = new byte[Config.BLOCK_SIZE];
        this.recorder = recorder;
        this.recorderKey = recorderKey;
        this.modifyTime = f.lastModified();
        helper = new RecordHelper();
    }

    public Response upload() throws QiniuException {
        long uploaded = helper.recoveryFromRecord();
        try {
            this.file = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            throw new QiniuException(e);
        }
        boolean retry = false;
        int contextIndex = blockIdx(uploaded);
        while (uploaded < size) {
            int blockSize = nextBlockSize(uploaded);
            try {
                file.read(blockBuffer, 0, blockSize);
            } catch (IOException e) {
                close();
                throw new QiniuException(e);
            }

//            long crc = Crc32.bytes(blockBuffer, 0, blockSize);
            Response response = null;
            try {
                response = makeBlock(blockBuffer, blockSize);
            } catch (QiniuException e) {
                if (e.code() < 0) {
                    host = Config.zone.upHostBackup;
                }
                if (e.response == null || e.response.needRetry()) {
                    retry = true;
                } else {
                    close();
                    throw e;
                }
            }
            if (retry) {
                try {
                    response = makeBlock(blockBuffer, blockSize);
                    retry = false;
                } catch (QiniuException e) {
                    close();
                    throw e;
                }

            }
            ResumeBlockInfo blockInfo = response.jsonToObject(ResumeBlockInfo.class);
            //TODO check return crc32
            // if blockInfo.crc32 != crc{}

            contexts[contextIndex++] = blockInfo.ctx;
            uploaded += blockSize;
            helper.record(uploaded);
        }
        close();

        try {
            return makeFile();
        } catch (QiniuException e) {
            try {
                return makeFile();
            } catch (QiniuException e1) {
                throw e1;
            }
        } finally {
            helper.removeRecord();
        }
    }

    private Response makeBlock(byte[] block, int blockSize) throws QiniuException {
        String url = host + "/mkblk/" + blockSize;
        return post(url, block, 0, blockSize);
    }

    private void close() {
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String fileUrl() {
        String url = host + "/mkfile/" + size + "/mimeType/" + UrlSafeBase64.encodeToString(mime)
                + "/fname/" + UrlSafeBase64.encodeToString(f.getName());
        final StringBuilder b = new StringBuilder(url);
        if (key != null) {
            b.append("/key/");
            b.append(UrlSafeBase64.encodeToString(key));
        }
        if (params != null) {
            params.forEach(new StringMap.Consumer() {
                
                public void accept(String key, Object value) {
                    b.append("/");
                    b.append(key);
                    b.append("/");
                    b.append(value);
                }
            });
        }
        return b.toString();
    }

    private Response makeFile() throws QiniuException {
        String url = fileUrl();
        String s = StringUtils.join(contexts, ",");
        return post(url, StringUtils.utf8Bytes(s));
    }

    private Response post(String url, byte[] data) throws QiniuException {
        return client.post(url, data, new StringMap().put("Authorization", "UpToken " + upToken));
    }

    private Response post(String url, byte[] data, int offset, int size) throws QiniuException {
        return client.post(url, data, offset, size, new StringMap().put("Authorization", "UpToken " + upToken),
                Client.DefaultMime);
    }

    private int nextBlockSize(long uploaded) {
        if (size < uploaded + Config.BLOCK_SIZE) {
            return (int) (size - uploaded);
        }
        return Config.BLOCK_SIZE;
    }

    private int blockIdx(long offset) {
        return (int) (offset / Config.BLOCK_SIZE);
    }

    private class RecordHelper {
        long recoveryFromRecord() {
            try {
                return recoveryFromRecord0();
            } catch (Exception e) {
                e.printStackTrace();
                // ignore

                return 0;
            }
        }

        long recoveryFromRecord0() {
            if (recorder == null) {
                return 0;
            }
            byte[] data = recorder.get(recorderKey);
            if (data == null) {
                return 0;
            }
            String jsonStr = new String(data);
            Record r = new Gson().fromJson(jsonStr, Record.class);
            if (r.offset == 0 || r.modify_time != modifyTime || r.size != size
                    || r.contexts == null || r.contexts.length == 0) {
                return 0;
            }
            for (int i = 0; i < r.contexts.length; i++) {
                contexts[i] = r.contexts[i];
            }

            return r.offset;
        }

        void removeRecord() {
            try {
                if (recorder != null) {
                    recorder.del(recorderKey);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // ignore
            }
        }

        // save json value
        //{
        //    "size":filesize,
        //    "offset":lastSuccessOffset,
        //    "modify_time": lastFileModifyTime,
        //    "contexts": contexts
        //}
        void record(long offset) {
            try {
                if (recorder == null || offset == 0) {
                    return;
                }
                String data = new Gson().toJson(new Record(size, offset, modifyTime, contexts));
                recorder.set(recorderKey, data.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
                // ignore
            }
        }

        private class Record {
            long size;
            long offset;
            // CHECKSTYLE:OFF
            long modify_time;
            // CHECKSTYLE:ON
            String[] contexts;

            Record() {
            }

            Record(long size, long offset, long modify_time, String[] contexts) {
                this.size = size;
                this.offset = offset;
                this.modify_time = modify_time;
                this.contexts = contexts;
            }
        }
    }
}
