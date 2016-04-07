package com.qiniu.util;

import com.qiniu.http.Client;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.security.GeneralSecurityException;

public final class Auth {

    /**
     * 涓婁紶绛栫暐锛屽弬鏁拌鏍艰瑙�     * <p/>
     * http://developer.qiniu.com/docs/v6/api/reference/security/put-policy.html
     */
    private static final String[] policyFields = new String[]{
            "callbackUrl",
            "callbackBody",
            "callbackHost",
            "callbackBodyType",
            "callbackFetchKey",

            "returnUrl",
            "returnBody",

            "endUser",
            "saveKey",
            "insertOnly",

            "detectMime",
            "mimeLimit",
            "fsizeLimit",
            "fsizeMin",

            "persistentOps",
            "persistentNotifyUrl",
            "persistentPipeline",
    };
    private static final String[] deprecatedPolicyFields = new String[]{
            "asyncOps",
    };
    private final String accessKey;
    private final SecretKeySpec secretKey;

    private Auth(String accessKey, SecretKeySpec secretKeySpec) {
        this.accessKey = accessKey;
        this.secretKey = secretKeySpec;
    }

    public static Auth create(String accessKey, String secretKey) {
        if (StringUtils.isNullOrEmpty(accessKey) || StringUtils.isNullOrEmpty(secretKey)) {
            throw new IllegalArgumentException("empty key");
        }
        byte[] sk = StringUtils.utf8Bytes(secretKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(sk, "HmacSHA1");
        return new Auth(accessKey, secretKeySpec);
    }

    private static void copyPolicy(final StringMap policy, StringMap originPolicy, final boolean strict) {
        if (originPolicy == null) {
            return;
        }
        originPolicy.forEach(new StringMap.Consumer() {
            
            public void accept(String key, Object value) {
                if (StringUtils.inStringArray(key, deprecatedPolicyFields)) {
                    throw new IllegalArgumentException(key + " is deprecated!");
                }
                if (!strict || StringUtils.inStringArray(key, policyFields)) {
                    policy.put(key, value);
                }
            }
        });
    }

    private Mac createMac() {
        Mac mac;
        try {
            mac = javax.crypto.Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
        return mac;
    }

    public String sign(byte[] data) {
        Mac mac = createMac();
        String encodedSign = UrlSafeBase64.encodeToString(mac.doFinal(data));
        return this.accessKey + ":" + encodedSign;
    }

    public String sign(String data) {
        return sign(StringUtils.utf8Bytes(data));
    }

    public String signWithData(byte[] data) {
        String s = UrlSafeBase64.encodeToString(data);
        return sign(StringUtils.utf8Bytes(s)) + ":" + s;
    }

    public String signWithData(String data) {
        return signWithData(StringUtils.utf8Bytes(data));
    }

    /**
     * 鐢熸垚HTTP璇锋眰绛惧悕瀛楃涓�     *
     * @param urlString
     * @param body
     * @param contentType
     * @return
     */
    public String signRequest(String urlString, byte[] body, String contentType) {
        URI uri = URI.create(urlString);
        String path = uri.getRawPath();
        String query = uri.getRawQuery();

        Mac mac = createMac();

        mac.update(StringUtils.utf8Bytes(path));

        if (query != null && query.length() != 0) {
            mac.update((byte) ('?'));
            mac.update(StringUtils.utf8Bytes(query));
        }
        mac.update((byte) '\n');
        if (body != null && body.length > 0 && !StringUtils.isNullOrEmpty(contentType)) {
            if (contentType.equals(Client.FormMime)
                    || contentType.equals(Client.JsonMime)) {
                mac.update(body);
            }
        }

        String digest = UrlSafeBase64.encodeToString(mac.doFinal());

        return this.accessKey + ":" + digest;
    }

    /**
     * 楠岃瘉鍥炶皟绛惧悕鏄惁姝ｇ‘
     *
     * @param originAuthorization 寰呴獙璇佺鍚嶅瓧绗︿覆锛屼互 "QBox "浣滀负璧峰瀛楃
     * @param url                 鍥炶皟鍦板潃
     * @param body                鍥炶皟璇锋眰浣撱�鍘熷璇锋眰浣擄紝涓嶈瑙ｆ瀽鍚庡啀灏佽鎴愭柊鐨勮姹備綋--鍙兘瀵艰嚧绛惧悕涓嶄竴鑷淬�
     * @param contentType         鍥炶皟ContentType
     * @return
     */
    public boolean isValidCallback(String originAuthorization, String url, byte[] body, String contentType) {
        String authorization = "QBox " + signRequest(url, body, contentType);
        return authorization.equals(originAuthorization);
    }

    /**
     * 涓嬭浇绛惧悕
     *
     * @param baseUrl 寰呯鍚嶆枃浠秛rl锛屽 http://img.domain.com/u/3.jpg 銆�     *                http://img.domain.com/u/3.jpg?imageView2/1/w/120
     * @return
     */
    public String privateDownloadUrl(String baseUrl) {
        return privateDownloadUrl(baseUrl, 3600);
    }

    /**
     * 涓嬭浇绛惧悕
     *
     * @param baseUrl 寰呯鍚嶆枃浠秛rl锛屽 http://img.domain.com/u/3.jpg 銆�     *                http://img.domain.com/u/3.jpg?imageView2/1/w/120
     * @param expires 鏈夋晥鏃堕暱锛屽崟浣嶇銆傞粯璁�600s
     * @return
     */
    public String privateDownloadUrl(String baseUrl, long expires) {
        long deadline = System.currentTimeMillis() / 1000 + expires;
        return privateDownloadUrlWithDeadline(baseUrl, deadline);
    }

    String privateDownloadUrlWithDeadline(String baseUrl, long deadline) {
        StringBuilder b = new StringBuilder();
        b.append(baseUrl);
        int pos = baseUrl.indexOf("?");
        if (pos > 0) {
            b.append("&e=");
        } else {
            b.append("?e=");
        }
        b.append(deadline);
        String token = sign(StringUtils.utf8Bytes(b.toString()));
        b.append("&token=");
        b.append(token);
        return b.toString();
    }

    /**
     * scope = bucket
     * 涓�埇鎯呭喌涓嬪彲閫氳繃姝ゆ柟娉曡幏鍙杢oken
     *
     * @param bucket 绌洪棿鍚�     * @return 鐢熸垚鐨勪笂浼爐oken
     */
    public String uploadToken(String bucket) {
        return uploadToken(bucket, null, 3600, null, true);
    }

    /**
     * scope = bucket:key
     * 鍚屽悕鏂囦欢瑕嗙洊鎿嶄綔銆佸彧鑳戒笂浼犳寚瀹歬ey鐨勬枃浠跺彲浠ュ彲閫氳繃姝ゆ柟娉曡幏鍙杢oken
     *
     * @param bucket 绌洪棿鍚�     * @param key    key锛屽彲涓�null
     * @return 鐢熸垚鐨勪笂浼爐oken
     */
    public String uploadToken(String bucket, String key) {
        return uploadToken(bucket, key, 3600, null, true);
    }

    /**
     * 鐢熸垚涓婁紶token
     *
     * @param bucket  绌洪棿鍚�     * @param key     key锛屽彲涓�null
     * @param expires 鏈夋晥鏃堕暱锛屽崟浣嶇
     * @param policy  涓婁紶绛栫暐鐨勫叾瀹冨弬鏁帮紝濡�new StringMap().put("endUser", "uid").putNotEmpty("returnBody", "")銆�     *                scope閫氳繃 bucket銆乲ey闂存帴璁剧疆锛宒eadline 閫氳繃 expires 闂存帴璁剧疆
     * @return 鐢熸垚鐨勪笂浼爐oken
     */
    public String uploadToken(String bucket, String key, long expires, StringMap policy) {
        return uploadToken(bucket, key, expires, policy, true);
    }

    /**
     * 鐢熸垚涓婁紶token
     *
     * @param bucket  绌洪棿鍚�     * @param key     key锛屽彲涓�null
     * @param expires 鏈夋晥鏃堕暱锛屽崟浣嶇銆傞粯璁�600s
     * @param policy  涓婁紶绛栫暐鐨勫叾瀹冨弬鏁帮紝濡�new StringMap().put("endUser", "uid").putNotEmpty("returnBody", "")銆�     *                scope閫氳繃 bucket銆乲ey闂存帴璁剧疆锛宒eadline 閫氳繃 expires 闂存帴璁剧疆
     * @param strict  鏄惁鍘婚櫎闈為檺瀹氱殑绛栫暐瀛楁锛岄粯璁rue
     * @return 鐢熸垚鐨勪笂浼爐oken
     */
    public String uploadToken(String bucket, String key, long expires, StringMap policy, boolean strict) {
        long deadline = System.currentTimeMillis() / 1000 + expires;
        return uploadTokenWithDeadline(bucket, key, deadline, policy, strict);
    }

    String uploadTokenWithDeadline(String bucket, String key, long deadline, StringMap policy, boolean strict) {
        String scope = bucket;
        if (key != null) {
            scope = bucket + ":" + key;
        }
        StringMap x = new StringMap();
        copyPolicy(x, policy, strict);
        x.put("scope", scope);
        x.put("deadline", deadline);

        String s = Json.encode(x);
        return signWithData(StringUtils.utf8Bytes(s));
    }

    public StringMap authorization(String url, byte[] body, String contentType) {
        String authorization = "QBox " + signRequest(url, body, contentType);
        return new StringMap().put("Authorization", authorization);
    }

    public StringMap authorization(String url) {
        return authorization(url, null, null);
    }
}
