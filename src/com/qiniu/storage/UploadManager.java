package com.qiniu.storage;

import com.qiniu.common.Config;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.StringMap;

import java.io.File;
import java.io.IOException;

/**
 * 涓冪墰鏂囦欢涓婁紶绠＄悊鍣� * <p/>
 * 涓�埇榛樿鍙互浣跨敤杩欎釜绫荤殑鏂规硶鏉ヤ笂浼犳暟鎹拰鏂囦欢銆傝繖涓被鑷姩妫�祴鏂囦欢鐨勫ぇ灏忥紝
 * 鍙瓒呰繃浜唟@link com.qiniu.common.Config#PUT_THRESHOLD}
 */
public final class UploadManager {
    private final Client client;
    private final Recorder recorder;
    private final RecordKeyGenerator keyGen;

    public UploadManager() {
        this(null, null);
    }

    /**
     * 鏂偣涓婁紶璁板綍銆傚彧閽堝 鏂囦欢鍒嗗潡涓婁紶銆�     * 鍒嗗潡涓婁紶涓紝灏嗘瘡涓�潡涓婁紶鐨勮褰曚繚瀛樹笅鏉ャ�涓婁紶涓柇鍚庡彲鍦ㄤ笂涓�鏂偣璁板綍鍩虹涓婁笂浼犲墿浣欓儴鍒嗐�
     *
     * @param recorder 鏂偣璁板綍鑰�     */
    public UploadManager(Recorder recorder) {
        this(recorder, new RecordKeyGenerator() {

            
            public String gen(String key, File file) {
                return key + "_._" + file.getAbsolutePath();
            }
        });
    }

    /**
     * 鏂偣涓婁紶璁板綍銆傚彧閽堝 鏂囦欢鍒嗗潡涓婁紶銆�     * 鍒嗗潡涓婁紶涓紝灏嗘瘡涓�潡涓婁紶鐨勮褰曚繚瀛樹笅鏉ャ�涓婁紶涓柇鍚庡彲鍦ㄤ笂涓�鏂偣璁板綍鍩虹涓婁笂浼犲墿浣欓儴鍒嗐�
     *
     * @param recorder 鏂偣璁板綍鑰�     * @param keyGen   鐢熸垚鏂囦欢鐨勬柇鐐硅褰曟爣绀猴紝鏍规嵁鐢熸垚鐨勬爣绀猴紝鍙壘鍒版柇鐐硅褰曠殑鍐呭
     */
    public UploadManager(Recorder recorder, RecordKeyGenerator keyGen) {
        client = new Client();
        this.recorder = recorder;
        this.keyGen = keyGen;
    }

    private static void checkArgs(final String key, byte[] data, File f, String token) {
        String message = null;
        if (f == null && data == null) {
            message = "no input data";
        } else if (token == null || token.equals("")) {
            message = "no token";
        }
        if (message != null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 杩囨护鐢ㄦ埛鑷畾涔夊弬鏁帮紝鍙湁鍙傛暟鍚嶄互<code>x:</code>寮�ご鐨勫弬鏁版墠浼氳浣跨敤
     *
     * @param params 寰呰繃婊ょ殑鐢ㄦ埛鑷畾涔夊弬鏁�     * @return 杩囨护鍚庣殑鐢ㄦ埛鑷畾涔夊弬鏁�     */
    private static StringMap filterParam(StringMap params) {
        final StringMap ret = new StringMap();
        if (params == null) {
            return ret;
        }

        params.forEach(new StringMap.Consumer() {
            
            public void accept(String key, Object value) {
                if (value == null) {
                    return;
                }
                String val = value.toString();
                if (key.startsWith("x:") && !val.equals("")) {
                    ret.put(key, val);
                }
            }
        });

        return ret;
    }

    /**
     * 涓婁紶鏁版嵁
     *
     * @param data  涓婁紶鐨勬暟鎹�     * @param key   涓婁紶鏁版嵁淇濆瓨鐨勬枃浠跺悕
     * @param token 涓婁紶鍑瘉
     */
    public Response put(final byte[] data, final String key, final String token) throws QiniuException {
        return put(data, key, token, null, null, false);
    }

    /**
     * 涓婁紶鏁版嵁
     *
     * @param data     涓婁紶鐨勬暟鎹�     * @param key      涓婁紶鏁版嵁淇濆瓨鐨勬枃浠跺悕
     * @param token    涓婁紶鍑瘉
     * @param params   鑷畾涔夊弬鏁帮紝濡�params.put("x:foo", "foo")
     * @param mime     鎸囧畾鏂囦欢mimetype
     * @param checkCrc 鏄惁楠岃瘉crc32
     * @return
     * @throws QiniuException
     */
    public Response put(final byte[] data, final String key, final String token, StringMap params,
                        String mime, boolean checkCrc) throws QiniuException {
        checkArgs(key, data, null, token);
        if (mime == null) {
            mime = Client.DefaultMime;
        }
        params = filterParam(params);
        return new FormUploader(client, token, key, data, params, mime, checkCrc).upload();
    }

    /**
     * 涓婁紶鏂囦欢
     *
     * @param filePath 涓婁紶鐨勬枃浠惰矾寰�     * @param key      涓婁紶鏂囦欢淇濆瓨鐨勬枃浠跺悕
     * @param token    涓婁紶鍑瘉
     */
    public Response put(String filePath, String key, String token) throws QiniuException {
        return put(filePath, key, token, null, null, false);
    }

    /**
     * 涓婁紶鏂囦欢
     *
     * @param filePath 涓婁紶鐨勬枃浠惰矾寰�     * @param key      涓婁紶鏂囦欢淇濆瓨鐨勬枃浠跺悕
     * @param token    涓婁紶鍑瘉
     * @param params   鑷畾涔夊弬鏁帮紝濡�params.put("x:foo", "foo")
     * @param mime     鎸囧畾鏂囦欢mimetype
     * @param checkCrc 鏄惁楠岃瘉crc32
     */
    public Response put(String filePath, String key, String token, StringMap params,
                        String mime, boolean checkCrc) throws QiniuException {
        return put(new File(filePath), key, token, params, mime, checkCrc);
    }

    /**
     * 涓婁紶鏂囦欢
     *
     * @param file  涓婁紶鐨勬枃浠跺璞�     * @param key   涓婁紶鏂囦欢淇濆瓨鐨勬枃浠跺悕
     * @param token 涓婁紶鍑瘉
     */
    public Response put(File file, String key, String token) throws QiniuException {
        return put(file, key, token, null, null, false);
    }

    /**
     * 涓婁紶鏂囦欢
     *
     * @param file     涓婁紶鐨勬枃浠跺璞�     * @param key      涓婁紶鏂囦欢淇濆瓨鐨勬枃浠跺悕
     * @param token    涓婁紶鍑瘉
     * @param mime     鎸囧畾鏂囦欢mimetype
     * @param checkCrc 鏄惁楠岃瘉crc32
     */
    public Response put(File file, String key, String token, StringMap params,
                        String mime, boolean checkCrc) throws QiniuException {
        checkArgs(key, null, file, token);
        if (mime == null) {
            mime = Client.DefaultMime;
        }
        params = filterParam(params);
        long size = file.length();
        if (size <= Config.PUT_THRESHOLD) {
            return new FormUploader(client, token, key, file, params, mime, checkCrc).upload();
        }

        String recorderKey = key;
        if (keyGen != null) {
            recorderKey = keyGen.gen(key, file);
        }
        ResumeUploader uploader = new ResumeUploader(client, token, key, file,
                params, mime, recorder, recorderKey);
        return uploader.upload();
    }


    public void asyncPut(final byte[] data, final String key, final String token, StringMap params,
                         String mime, boolean checkCrc, UpCompletionHandler handler) throws IOException {
        checkArgs(key, data, null, token);
        if (mime == null) {
            mime = Client.DefaultMime;
        }
        params = filterParam(params);
        new FormUploader(client, token, key, data, params, mime, checkCrc).asyncUpload(handler);
    }
}
