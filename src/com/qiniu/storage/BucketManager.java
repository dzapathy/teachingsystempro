package com.qiniu.storage;

import com.qiniu.common.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 涓昏娑夊強浜嗙┖闂磋祫婧愮鐞嗗強鎵归噺鎿嶄綔鎺ュ彛鐨勫疄鐜帮紝鍏蜂綋鐨勬帴鍙ｈ鏍煎彲浠ュ弬鑰� *
 * @link http://developer.qiniu.com/docs/v6/api/reference/rs/
 */
public final class BucketManager {
    private final Auth auth;
    private final Client client;

    public BucketManager(Auth auth) {
        this.auth = auth;
        client = new Client();
    }

    /**
     * EncodedEntryURI鏍煎紡
     *
     * @param bucket
     * @param key
     * @return urlsafe_base64_encode(Bucket:Key)
     */
    public static String entry(String bucket, String key) {
        return entry(bucket, key, true);
    }


    /**
     * EncodedEntryURI鏍煎紡
     * 褰�mustHaveKey 涓�false锛�涓�key 涓�null 鏃讹紝杩斿洖 urlsafe_base64_encode(Bucket);
     * 鍏跺畠鏉′欢涓嬭繑鍥� urlsafe_base64_encode(Bucket:Key)
     *
     * @param bucket
     * @param key
     * @param mustHaveKey
     * @return urlsafe_base64_encode(entry)
     */
    public static String entry(String bucket, String key, boolean mustHaveKey) {
        String en = bucket + ":" + key;
        if (!mustHaveKey && (key == null)) {
            en = bucket;
        }
        return UrlSafeBase64.encodeToString(en);
    }

    /**
     * 鑾峰彇璐﹀彿涓嬫墍鏈夌┖闂村悕鍒楄〃
     *
     * @return bucket 鍒楄〃
     */
    public String[] buckets() throws QiniuException {
        Response r = rsGet("/buckets");
        return r.jsonToObject(String[].class);
    }

    /**
     * 鏍规嵁鍓嶇紑鑾峰彇鏂囦欢鍒楄〃鐨勮凯浠ｅ櫒
     *
     * @param bucket 绌洪棿鍚�     * @param prefix 鏂囦欢鍚嶅墠缂�     * @return FileInfo杩唬鍣�     */
    public FileListIterator createFileListIterator(String bucket, String prefix) {
        return new FileListIterator(bucket, prefix, 100, null);
    }

    /**
     * 鏍规嵁鍓嶇紑鑾峰彇鏂囦欢鍒楄〃鐨勮凯浠ｅ櫒
     *
     * @param bucket    绌洪棿鍚�     * @param prefix    鏂囦欢鍚嶅墠缂�     * @param limit     姣忔杩唬鐨勯暱搴﹂檺鍒讹紝鏈�ぇ1000锛屾帹鑽愬� 100
     * @param delimiter 鎸囧畾鐩綍鍒嗛殧绗︼紝鍒楀嚭鎵�湁鍏叡鍓嶇紑锛堟ā鎷熷垪鍑虹洰褰曟晥鏋滐級銆傜己鐪佸�涓虹┖瀛楃涓�     * @return FileInfo杩唬鍣�     */
    public FileListIterator createFileListIterator(String bucket, String prefix, int limit, String delimiter) {
        return new FileListIterator(bucket, prefix, limit, delimiter);
    }

    /**
     * 鏍规嵁鍓嶇紑鑾峰彇鏂囦欢鍒楄〃
     *
     * @param bucket    绌洪棿鍚�     * @param prefix    鏂囦欢鍚嶅墠缂�     * @param marker    涓婁竴娆¤幏鍙栨枃浠跺垪琛ㄦ椂杩斿洖鐨�marker
     * @param limit     姣忔杩唬鐨勯暱搴﹂檺鍒讹紝鏈�ぇ1000锛屾帹鑽愬� 100
     * @param delimiter 鎸囧畾鐩綍鍒嗛殧绗︼紝鍒楀嚭鎵�湁鍏叡鍓嶇紑锛堟ā鎷熷垪鍑虹洰褰曟晥鏋滐級銆傜己鐪佸�涓虹┖瀛楃涓�     * @return
     * @throws QiniuException
     */
    public FileListing listFiles(String bucket, String prefix, String marker, int limit, String delimiter)
            throws QiniuException {
        StringMap map = new StringMap().put("bucket", bucket).putNotEmpty("marker", marker)
                .putNotEmpty("prefix", prefix).putNotEmpty("delimiter", delimiter).putWhen("limit", limit, limit > 0);

        String url = Config.RSF_HOST + "/list?" + map.formString();
        Response r = get(url);
        return r.jsonToObject(FileListing.class);
    }

    /**
     * 鑾峰彇鎸囧畾绌洪棿銆佹枃浠跺悕鐨勭姸鎬�     *
     * @param bucket
     * @param key
     * @return
     * @throws QiniuException
     */
    public FileInfo stat(String bucket, String key) throws QiniuException {
        Response r = rsGet("/stat/" + entry(bucket, key));
        return r.jsonToObject(FileInfo.class);
    }

    /**
     * 鍒犻櫎鎸囧畾绌洪棿銆佹枃浠跺悕鐨勬枃浠�     *
     * @param bucket
     * @param key
     * @throws QiniuException
     */
    public void delete(String bucket, String key) throws QiniuException {
        rsPost("/delete/" + entry(bucket, key));
    }

    /**
     * 淇敼鎸囧畾绌洪棿銆佹枃浠剁殑鏂囦欢鍚�     *
     * @param bucket
     * @param oldname
     * @param newname
     * @throws QiniuException
     */
    public void rename(String bucket, String oldname, String newname) throws QiniuException {
        move(bucket, oldname, bucket, newname);
    }

    /**
     * 澶嶅埗鏂囦欢銆傝姹傜┖闂村湪鍚屼竴璐﹀彿涓嬨�
     *
     * @param from_bucket
     * @param from_key
     * @param to_bucket
     * @param to_key
     * @throws QiniuException
     */
    public void copy(String from_bucket, String from_key, String to_bucket, String to_key) throws QiniuException {
        String from = entry(from_bucket, from_key);
        String to = entry(to_bucket, to_key);
        String path = "/copy/" + from + "/" + to;
        rsPost(path);
    }

    /**
     * 绉诲姩鏂囦欢銆傝姹傜┖闂村湪鍚屼竴璐﹀彿涓嬨�
     *
     * @param from_bucket
     * @param from_key
     * @param to_bucket
     * @param to_key
     * @throws QiniuException
     */
    public void move(String from_bucket, String from_key, String to_bucket, String to_key) throws QiniuException {
        String from = entry(from_bucket, from_key);
        String to = entry(to_bucket, to_key);
        String path = "/move/" + from + "/" + to;
        rsPost(path);
    }

    /**
     * 淇敼瀹屾枃浠秏imeTYpe
     *
     * @param bucket
     * @param key
     * @param mime
     * @throws QiniuException
     */
    public void changeMime(String bucket, String key, String mime) throws QiniuException {
        String resource = entry(bucket, key);
        String encode_mime = UrlSafeBase64.encodeToString(mime);
        String path = "/chgm/" + resource + "/mime/" + encode_mime;
        rsPost(path);
    }

    /**
     * 鎶撳彇鎸囧畾鍦板潃鐨勬枃浠讹紝宸叉寚瀹氬悕绉颁繚瀛樺湪鎸囧畾绌洪棿銆�     * 瑕佹眰鎸囧畾url鍙闂�
     * 澶ф枃浠朵笉寤鸿浣跨敤姝ゆ帴鍙ｆ姄鍙栥�鍙厛涓嬭浇鍐嶄笂浼犮�
     *
     * @param url
     * @param bucket
     * @throws QiniuException
     */
    public DefaultPutRet fetch(String url, String bucket) throws QiniuException {
        return fetch(url, bucket, null);
    }

    /**
     * 鎶撳彇鎸囧畾鍦板潃鐨勬枃浠讹紝宸叉寚瀹氬悕绉颁繚瀛樺湪鎸囧畾绌洪棿銆�     * 瑕佹眰鎸囧畾url鍙闂�
     * 澶ф枃浠朵笉寤鸿浣跨敤姝ゆ帴鍙ｆ姄鍙栥�鍙厛涓嬭浇鍐嶄笂浼犮�
     *
     * @param url
     * @param bucket
     * @param key
     * @throws QiniuException
     */
    public DefaultPutRet fetch(String url, String bucket, String key) throws QiniuException {
        String resource = UrlSafeBase64.encodeToString(url);
        String to = entry(bucket, key, false);
        String path = "/fetch/" + resource + "/to/" + to;
        Response r = ioPost(path);
        return r.jsonToObject(DefaultPutRet.class);
    }

    /**
     * 瀵逛簬璁剧疆浜嗛暅鍍忓瓨鍌ㄧ殑绌洪棿锛屼粠闀滃儚婧愮珯鎶撳彇鎸囧畾鍚嶇О鐨勮祫婧愬苟瀛樺偍鍒拌绌洪棿涓�
     * 濡傛灉璇ョ┖闂翠腑宸插瓨鍦ㄨ鍚嶇О鐨勮祫婧愶紝鍒欎細灏嗛暅鍍忔簮绔欑殑璧勬簮瑕嗙洊绌洪棿涓浉鍚屽悕绉扮殑璧勬簮
     *
     * @param bucket
     * @param key
     * @throws QiniuException
     */
    public void prefetch(String bucket, String key) throws QiniuException {
        String resource = entry(bucket, key);
        String path = "/prefetch/" + resource;
        ioPost(path);
    }

    /**
     * 鎵归噺鎵ц鏂囦欢绠＄悊鐩稿叧鎿嶄綔
     *
     * @param operations
     * @return
     * @throws QiniuException
     * @see Batch
     */
    public Response batch(Batch operations) throws QiniuException {
        return rsPost("/batch", operations.toBody());
    }

    private Response rsPost(String path, byte[] body) throws QiniuException {
        String url = Config.RS_HOST + path;
        return post(url, body);
    }

    private Response rsPost(String path) throws QiniuException {
        return rsPost(path, null);
    }

    private Response rsGet(String path) throws QiniuException {
        String url = Config.RS_HOST + path;
        return get(url);
    }

    private Response ioPost(String path) throws QiniuException {
        String url = Config.IO_HOST + path;
        return post(url, null);
    }

    private Response get(String url) throws QiniuException {
        StringMap headers = auth.authorization(url);
        return client.get(url, headers);
    }

    private Response post(String url, byte[] body) throws QiniuException {
        StringMap headers = auth.authorization(url, body, Client.FormMime);
        return client.post(url, body, headers, Client.FormMime);
    }

    /**
     * 鏂囦欢绠＄悊鎿嶄綔鎸囦护
     */
    public static class Batch {
        private ArrayList<String> ops;

        public Batch() {
            this.ops = new ArrayList<String>();
        }

        public Batch copy(String from_bucket, String from_key, String to_bucket, String to_key) {
            String from = entry(from_bucket, from_key);
            String to = entry(to_bucket, to_key);
            ops.add("copy" + "/" + from + "/" + to);
            return this;
        }

        public Batch rename(String from_bucket, String from_key, String to_key) {
            return move(from_bucket, from_key, from_bucket, to_key);
        }

        public Batch move(String from_bucket, String from_key, String to_bucket, String to_key) {
            String from = entry(from_bucket, from_key);
            String to = entry(to_bucket, to_key);
            ops.add("move" + "/" + from + "/" + to);
            return this;
        }

        public Batch delete(String bucket, String... keys) {
            for (String key : keys) {
                ops.add("delete" + "/" + entry(bucket, key));
            }
            return this;
        }

        public Batch stat(String bucket, String... keys) {
            for (String key : keys) {
                ops.add("stat" + "/" + entry(bucket, key));
            }
            return this;
        }

        public byte[] toBody() {
            String body = StringUtils.join(ops, "&op=", "op=");
            return StringUtils.utf8Bytes(body);
        }

        public Batch merge(Batch batch) {
            this.ops.addAll(batch.ops);
            return this;
        }
    }

    /**
     * 鑾峰彇鏂囦欢鍒楄〃杩唬鍣�     */
    public class FileListIterator implements Iterator<FileInfo[]> {
        private String marker = null;
        private String bucket;
        private String delimiter;
        private int limit;
        private String prefix;
        private QiniuException exception = null;

        public FileListIterator(String bucket, String prefix, int limit, String delimiter) {
            if (limit <= 0) {
                throw new IllegalArgumentException("limit must great than 0");
            }
            this.bucket = bucket;
            this.prefix = prefix;
            this.limit = limit;
            this.delimiter = delimiter;
        }

        public QiniuException error() {
            return exception;
        }

        
        public boolean hasNext() {
            return exception == null && !"".equals(marker);
        }

        
        public FileInfo[] next() {
            try {
                FileListing f = listFiles(bucket, prefix, marker, limit, delimiter);
                this.marker = f.marker == null ? "" : f.marker;
                return f.items;
            } catch (QiniuException e) {
                this.exception = e;
                return null;
            }
        }

        
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}
