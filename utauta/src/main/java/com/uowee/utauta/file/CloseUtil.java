package com.uowee.utauta.file;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by GuoWee on 2018/1/17.
 */

public final class CloseUtil {

    private CloseUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 关闭IO操作
     *
     * @param closeables
     */
    public static void closeIO(final Closeable... closeables) {
        if (null == closeables || closeables.length <= 0) {
            return;
        }
        for (Closeable cb : closeables) {
            try {
                if (null == cb) {
                    continue;
                }
                cb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
