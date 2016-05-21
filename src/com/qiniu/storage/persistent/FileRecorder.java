package com.qiniu.storage.persistent;

import com.qiniu.storage.Recorder;
import com.qiniu.util.UrlSafeBase64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * 瀹炵幇鍒嗙墖涓婁紶鏃朵笂浼犺繘搴︾殑鎺ュ彛鏂规硶
 */
public final class FileRecorder implements Recorder {
    private final File directory;

    /**
     * 鏂偣璁板綍鏂囦欢淇濆瓨鐨勭洰褰�     *
     * @param directory
     * @throws IOException
     */
    public FileRecorder(String directory) throws IOException {
        this(new File(directory));
    }

    /**
     * 鏂偣璁板綍鏂囦欢淇濆瓨鐨勭洰褰�     *
     * @param directory
     * @throws IOException
     */
    public FileRecorder(File directory) throws IOException {
        this.directory = directory;
        if (!directory.exists()) {
            boolean r = directory.mkdirs();
            if (!r) {
                throw new IOException("mkdir failed");
            }
            return;
        }
        if (!directory.isDirectory()) {
            throw new IOException("does not mkdir");
        }
    }

    /**
     * 绾綍鍒嗙墖涓婁紶杩涘害
     *
     * @param key  涓婁紶鏂囦欢杩涘害鏂囦欢淇濆瓨鍚�     * @param data 涓婁紶鏂囦欢鐨勮繘搴︽暟鎹�     */
    
    public void set(String key, byte[] data) {
        File f = new File(directory, UrlSafeBase64.encodeToString(key));
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream(f);
            fo.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fo != null) {
            try {
                fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 鑾峰彇鍒嗙墖涓婁紶杩涘害
     *
     * @param key 涓婁紶鏂囦欢杩涘害鏂囦欢淇濆瓨鍚�     */
    
    public byte[] get(String key) {
        File f = new File(directory, UrlSafeBase64.encodeToString(key));
        if (!f.exists()) {
            return null;
        }
        FileInputStream fi = null;
        byte[] data = null;
        int read = 0;
        try {
            if (outOfDate(f)) {
                f.delete();
                return null;
            }
            data = new byte[(int) f.length()];
            fi = new FileInputStream(f);
            read = fi.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fi != null) {
            try {
                fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (read == 0) {
            return null;
        }
        return data;
    }

    private boolean outOfDate(File f) {
        return f.lastModified() + 1000 * 3600 * 24 * 2 < new Date().getTime();
    }

    /**
     * 鍒犻櫎宸蹭笂浼犳枃浠剁殑杩涘害鏂囦欢
     *
     * @param key 涓婁紶鏂囦欢杩涘害鏂囦欢淇濆瓨鍚�     */
    
    public void del(String key) {
        File f = new File(directory, UrlSafeBase64.encodeToString(key));
        f.delete();
    }
}
