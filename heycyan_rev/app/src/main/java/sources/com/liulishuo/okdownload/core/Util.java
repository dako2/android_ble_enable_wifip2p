package com.liulishuo.okdownload.core;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.StatFs;
import android.util.Log;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.codec.digest.MessageDigestAlgorithms;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointStoreOnCache;
import com.liulishuo.okdownload.core.breakpoint.DownloadStore;
import com.liulishuo.okdownload.core.connection.DownloadConnection;
import com.liulishuo.okdownload.core.connection.DownloadUrlConnection;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public class Util {
    public static final String ACCEPT_RANGES = "Accept-Ranges";
    public static final int CHUNKED_CONTENT_LENGTH = -1;
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_RANGE = "Content-Range";
    public static final String ETAG = "Etag";
    public static final String IF_MATCH = "If-Match";
    public static final String METHOD_HEAD = "HEAD";
    public static final String RANGE = "Range";
    public static final int RANGE_NOT_SATISFIABLE = 416;
    public static final String TRANSFER_ENCODING = "Transfer-Encoding";
    public static final String USER_AGENT = "User-Agent";
    public static final String VALUE_CHUNKED = "chunked";
    private static Logger logger = new EmptyLogger();

    public static class EmptyLogger implements Logger {
        @Override // com.liulishuo.okdownload.core.Util.Logger
        /* renamed from: d */
        public void mo587d(String str, String str2) {
        }

        @Override // com.liulishuo.okdownload.core.Util.Logger
        /* renamed from: e */
        public void mo588e(String str, String str2, Exception exc) {
        }

        @Override // com.liulishuo.okdownload.core.Util.Logger
        /* renamed from: i */
        public void mo589i(String str, String str2) {
        }

        @Override // com.liulishuo.okdownload.core.Util.Logger
        /* renamed from: w */
        public void mo590w(String str, String str2) {
        }
    }

    public interface Logger {
        /* renamed from: d */
        void mo587d(String str, String str2);

        /* renamed from: e */
        void mo588e(String str, String str2, Exception exc);

        /* renamed from: i */
        void mo589i(String str, String str2);

        /* renamed from: w */
        void mo590w(String str, String str2);
    }

    public static boolean isCorrectFull(long j, long j2) {
        return j == j2;
    }

    public static void enableConsoleLog() {
        logger = null;
    }

    public static void setLogger(Logger logger2) {
        logger = logger2;
    }

    public static Logger getLogger() {
        return logger;
    }

    /* renamed from: e */
    public static void m584e(String str, String str2, Exception exc) {
        Logger logger2 = logger;
        if (logger2 != null) {
            logger2.mo588e(str, str2, exc);
        } else {
            Log.e(str, str2, exc);
        }
    }

    /* renamed from: w */
    public static void m586w(String str, String str2) {
        Logger logger2 = logger;
        if (logger2 != null) {
            logger2.mo590w(str, str2);
        } else {
            Log.w(str, str2);
        }
    }

    /* renamed from: d */
    public static void m583d(String str, String str2) {
        Logger logger2 = logger;
        if (logger2 != null) {
            logger2.mo587d(str, str2);
        } else {
            Log.d(str, str2);
        }
    }

    /* renamed from: i */
    public static void m585i(String str, String str2) {
        Logger logger2 = logger;
        if (logger2 != null) {
            logger2.mo589i(str, str2);
        } else {
            Log.i(str, str2);
        }
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static ThreadFactory threadFactory(final String str, final boolean z) {
        return new ThreadFactory() { // from class: com.liulishuo.okdownload.core.Util.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    public static String md5(String str) {
        byte[] bArrDigest;
        try {
            bArrDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException unused) {
            bArrDigest = null;
        }
        if (bArrDigest == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
        for (byte b : bArrDigest) {
            int i = b & 255;
            if (i < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

    public static void resetBlockIfDirty(BlockInfo blockInfo) {
        if (blockInfo.getCurrentOffset() >= 0 && blockInfo.getCurrentOffset() <= blockInfo.getContentLength()) {
            return;
        }
        m586w("resetBlockIfDirty", "block is dirty so have to reset: " + blockInfo);
        blockInfo.resetBlock();
    }

    public static long getFreeSpaceBytes(StatFs statFs) {
        return statFs.getAvailableBytes();
    }

    public static String humanReadableBytes(long j, boolean z) {
        int i = z ? 1000 : 1024;
        if (j < i) {
            return j + " B";
        }
        double d = j;
        double d2 = i;
        int iLog = (int) (Math.log(d) / Math.log(d2));
        return String.format(Locale.ENGLISH, "%.1f %sB", Double.valueOf(d / Math.pow(d2, iLog)), (z ? "kMGTPE" : "KMGTPE").charAt(iLog - 1) + (z ? "" : "i"));
    }

    public static DownloadStore createDefaultDatabase(Context context) {
        try {
            return (DownloadStore) Class.forName("com.liulishuo.okdownload.core.breakpoint.BreakpointStoreOnSQLite").getDeclaredConstructor(Context.class).newInstance(context);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            return new BreakpointStoreOnCache();
        }
    }

    public static DownloadStore createRemitDatabase(DownloadStore downloadStore) {
        try {
            downloadStore = (DownloadStore) downloadStore.getClass().getMethod("createRemitSelf", new Class[0]).invoke(downloadStore, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        m583d("Util", "Get final download store is " + downloadStore);
        return downloadStore;
    }

    public static DownloadConnection.Factory createDefaultConnectionFactory() {
        try {
            return (DownloadConnection.Factory) Class.forName("com.liulishuo.okdownload.core.connection.DownloadOkHttp3Connection$Factory").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            return new DownloadUrlConnection.Factory();
        }
    }

    public static void assembleBlock(DownloadTask downloadTask, BreakpointInfo breakpointInfo, long j, boolean z) {
        int iDetermineBlockCount = OkDownload.with().downloadStrategy().isUseMultiBlock(z) ? OkDownload.with().downloadStrategy().determineBlockCount(downloadTask, j) : 1;
        breakpointInfo.resetBlockInfos();
        long j2 = iDetermineBlockCount;
        long j3 = j / j2;
        long j4 = 0;
        int i = 0;
        long j5 = 0;
        while (i < iDetermineBlockCount) {
            j4 += j5;
            j5 = i == 0 ? (j % j2) + j3 : j3;
            breakpointInfo.addBlock(new BlockInfo(j4, j5));
            i++;
        }
    }

    public static long parseContentLength(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            m583d("Util", "parseContentLength failed parse for '" + str + "'");
            return -1L;
        }
    }

    public static boolean isNetworkNotOnWifiType(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            m586w("Util", "failed to get connectivity manager!");
            return true;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null || activeNetworkInfo.getType() != 1;
    }

    public static boolean checkPermission(String str) {
        return OkDownload.with().context().checkCallingOrSelfPermission(str) == 0;
    }

    public static long parseContentLengthFromContentRange(String str) throws NumberFormatException {
        if (str != null && str.length() != 0) {
            try {
                Matcher matcher = Pattern.compile("bytes (\\d+)-(\\d+)/\\d+").matcher(str);
                if (matcher.find()) {
                    return (Long.parseLong(matcher.group(2)) - Long.parseLong(matcher.group(1))) + 1;
                }
            } catch (Exception e) {
                m586w("Util", "parse content-length from content-range failed " + e);
            }
        }
        return -1L;
    }

    public static boolean isUriContentScheme(Uri uri) {
        return uri.getScheme().equals("content");
    }

    public static boolean isUriFileScheme(Uri uri) {
        return uri.getScheme().equals("file");
    }

    public static String getFilenameFromContentUri(Uri uri) {
        Cursor cursorQuery = OkDownload.with().context().getContentResolver().query(uri, null, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        try {
            cursorQuery.moveToFirst();
            return cursorQuery.getString(cursorQuery.getColumnIndex("_display_name"));
        } finally {
            cursorQuery.close();
        }
    }

    public static File getParentFile(File file) {
        File parentFile = file.getParentFile();
        return parentFile == null ? new File("/") : parentFile;
    }

    public static long getSizeFromContentUri(Uri uri) {
        Cursor cursorQuery = OkDownload.with().context().getContentResolver().query(uri, null, null, null, null);
        if (cursorQuery == null) {
            return 0L;
        }
        try {
            cursorQuery.moveToFirst();
            return cursorQuery.getLong(cursorQuery.getColumnIndex("_size"));
        } finally {
            cursorQuery.close();
        }
    }

    public static boolean isNetworkAvailable(ConnectivityManager connectivityManager) {
        if (connectivityManager == null) {
            m586w("Util", "failed to get connectivity manager!");
            return true;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void inspectUserHeader(Map<String, List<String>> map) throws IOException {
        if (map.containsKey("If-Match") || map.containsKey("Range")) {
            throw new IOException("If-Match and Range only can be handle by internal!");
        }
    }

    public static void addUserRequestHeaderField(Map<String, List<String>> map, DownloadConnection downloadConnection) throws IOException {
        inspectUserHeader(map);
        addRequestHeaderFields(map, downloadConnection);
    }

    public static void addRequestHeaderFields(Map<String, List<String>> map, DownloadConnection downloadConnection) {
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            Iterator<String> it = entry.getValue().iterator();
            while (it.hasNext()) {
                downloadConnection.addHeader(key, it.next());
            }
        }
    }

    public static void addDefaultUserAgent(DownloadConnection downloadConnection) {
        downloadConnection.addHeader("User-Agent", "OkDownload/1.0.7");
    }
}
