package com.hust_twj.zademo.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/09/21
 *     desc  : Log相关工具类
 * </pre>
 */
public final class LogUtils {

    public static final int V = Log.VERBOSE;
    public static final int D = Log.DEBUG;
    public static final int I = Log.INFO;
    public static final int W = Log.WARN;
    public static final int E = Log.ERROR;
    public static final int A = Log.ASSERT;

    @IntDef({V, D, I, W, E, A})
    @Retention(RetentionPolicy.SOURCE)
    private @interface TYPE {
    }

    private static final char[] T = new char[]{'V', 'D', 'I', 'W', 'E', 'A'};

    private static final int FILE = 0x10;
    private static final int JSON = 0x20;
    private static final int XML = 0x30;
    private static ExecutorService executor;
    private static String defaultDir;// log默认存储目录
    private static String sDir;       // log存储目录

    private static boolean sLogSwitch = true; // log总开关，默认开
    private static boolean sLog2ConsoleSwitch = true; // logcat是否打印，默认打印
    private static String sGlobalTag = null; // log标签
    private static boolean sTagIsSpace = true; // log标签是否为空白
    private static boolean sLogHeadSwitch = true; // log头部开关，默认开
    private static boolean sLog2FileSwitch = false;// log写入文件开关，默认关
    private static boolean sLogBorderSwitch = true; // log边框开关，默认开
    private static int sConsoleFilter = V;    // log控制台过滤器
    private static int sFileFilter = V;    // log文件过滤器
    private static String sFileName = "";

    private static final String FILE_DIR_NAME = "zlog";
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final String TOP_BORDER = "╔═══════════════════════════════════════════════════════════════════════════════════════════════════";
    private static final String LEFT_BORDER = "║ ";
    private static final String BOTTOM_BORDER = "╚═══════════════════════════════════════════════════════════════════════════════════════════════════";
    private static final int MAX_LEN = 4000;
    private static final Format FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS ", Locale.getDefault());
    private static final Format FORMAT_2 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
    private static final String LOG_FILE_SUFFIX = ".txt";

    private static final String NULL_TIPS = "Log with null object.";
    private static final String NULL = "null";
    private static final String ARGS = "args";

    private LogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static class Builder {
        public Builder(@NonNull Context context) {
            if (defaultDir != null) {
                return;
            }
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                    && context.getApplicationContext().getExternalFilesDir(FILE_DIR_NAME) != null) {
                defaultDir = context.getApplicationContext().getExternalFilesDir(FILE_DIR_NAME).getPath();
            } else {
                defaultDir = context.getApplicationContext().getFilesDir().getPath() + File.separator + FILE_DIR_NAME;
            }
        }

        /**
         * 设置log总开关，包括输出到控制台和文件，默认开
         *
         * @param logSwitch
         * @return
         */
        public Builder setLogSwitch(final boolean logSwitch) {
            LogUtils.sLogSwitch = logSwitch;
            return this;
        }

        /**
         * 设置是否输出到控制台开关，默认开
         *
         * @param consoleSwitch
         * @return
         */
        public Builder setConsoleSwitch(final boolean consoleSwitch) {
            LogUtils.sLog2ConsoleSwitch = consoleSwitch;
            return this;
        }

        /**
         * 设置log全局标签，默认为空
         * <p>当全局标签不为空时，我们输出的log全部为该tag，为空时，如果传入的tag为空那就显示类名，否则显示tag
         *
         * @param tag
         * @return
         */
        public Builder setGlobalTag(final String tag) {
            if (isSpace(tag)) {
                LogUtils.sGlobalTag = "";
                sTagIsSpace = true;
            } else {
                LogUtils.sGlobalTag = tag;
                sTagIsSpace = false;
            }
            return this;
        }

        /**
         * 设置log头信息开关，默认为开（log头包含：线程，方法，类，行号）
         *
         * @param logHeadSwitch
         * @return
         */
        public Builder setLogHeadSwitch(final boolean logHeadSwitch) {
            LogUtils.sLogHeadSwitch = logHeadSwitch;
            return this;
        }

        /**
         * 打印log时是否存到文件的开关，默认关
         *
         * @param log2FileSwitch
         * @return
         */
        public Builder setLog2FileSwitch(final boolean log2FileSwitch) {
            LogUtils.sLog2FileSwitch = log2FileSwitch;
            return this;
        }

        /**
         * 当自定义路径为空时，写入应用的/files/zlog/目录中
         *
         * @param dir
         * @return
         */
        public Builder setDir(final String dir) {
            if (isSpace(dir)) {
                LogUtils.sDir = defaultDir;
            } else {
                LogUtils.sDir = dir;
            }
            return this;
        }

        /**
         * 设置log头信息开关，默认为开（log头包含：线程，方法，类，行号）
         *
         * @param borderSwitch
         * @return
         */
        public Builder setBorderSwitch(final boolean borderSwitch) {
            LogUtils.sLogBorderSwitch = borderSwitch;
            return this;
        }

        /**
         * log的控制台过滤器，和logcat过滤器同理，默认Verbose
         *
         * @param consoleFilter
         * @return
         */
        public Builder setConsoleFilter(@TYPE final int consoleFilter) {
            LogUtils.sConsoleFilter = consoleFilter;
            return this;
        }

        /**
         * log文件过滤器，和logcat过滤器同理，默认Verbose
         *
         * @param fileFilter
         * @return
         */
        public Builder setFileFilter(@TYPE final int fileFilter) {
            LogUtils.sFileFilter = fileFilter;
            return this;
        }

        /**
         * 设置log文件名，默认为 log_yyyyMMddhhmmss
         */
        public Builder setFileName() {
            return setFileName("log_" + FORMAT_2.format(new Date()));
        }

        /**
         * 设置log文件名
         *
         * @param fileName
         */
        public Builder setFileName(String fileName) {
            LogUtils.sFileName = fileName;
            return this;
        }

        @Override
        public String toString() {
            return "switch: " + sLogSwitch
                    + LINE_SEP + "console: " + sLog2ConsoleSwitch
                    + LINE_SEP + "file: " + sLog2FileSwitch
                    + LINE_SEP + "head: " + sLogHeadSwitch
                    + LINE_SEP + "tag: " + (sTagIsSpace ? "null" : sGlobalTag)
                    + LINE_SEP + "border: " + sLogBorderSwitch
                    + LINE_SEP + "sDir: " + sDir
                    + LINE_SEP + "fileName: " + sFileName
                    + LINE_SEP + "consoleFilter: " + T[sConsoleFilter - V]
                    + LINE_SEP + "fileFilter: " + T[sFileFilter - V];
        }
    }

    public static void v(final Object contents) {
        log(V, sGlobalTag, contents);
    }

    public static void v(final String tag, final Object... contents) {
        log(V, tag, contents);
    }

    public static void d(final Object contents) {
        log(D, sGlobalTag, contents);
    }

    public static void d(final String tag, final Object... contents) {
        log(D, tag, contents);
    }

    public static void i(final Object contents) {
        log(I, sGlobalTag, contents);
    }

    public static void i(final String tag, final Object... contents) {
        log(I, tag, contents);
    }

    public static void w(final Object contents) {
        log(W, sGlobalTag, contents);
    }

    public static void w(final String tag, final Object... contents) {
        log(W, tag, contents);
    }

    public static void e(final Object contents) {
        log(E, sGlobalTag, contents);
    }

    public static void e(final String tag, final Object... contents) {
        log(E, tag, contents);
    }

    public static void a(final Object contents) {
        log(A, sGlobalTag, contents);
    }

    public static void a(final String tag, final Object... contents) {
        log(A, tag, contents);
    }

    public static void file(final Object contents) {
        log(FILE | D, sGlobalTag, contents);
    }

    public static void file(@TYPE final int type, final Object contents) {
        log(FILE | type, sGlobalTag, contents);
    }

    public static void file(final String tag, final Object contents) {
        log(FILE | D, tag, contents);
    }

    public static void file(@TYPE final int type, final String tag, final Object contents) {
        log(FILE | type, tag, contents);
    }

    public static void json(final String contents) {
        log(JSON | D, sGlobalTag, contents);
    }

    public static void json(@TYPE final int type, final String contents) {
        log(JSON | type, sGlobalTag, contents);
    }

    public static void json(final String tag, final String contents) {
        log(JSON | D, tag, contents);
    }

    public static void json(@TYPE final int type, final String tag, final String contents) {
        log(JSON | type, tag, contents);
    }

    public static void xml(final String contents) {
        log(XML | D, sGlobalTag, contents);
    }

    public static void xml(@TYPE final int type, final String contents) {
        log(XML | type, sGlobalTag, contents);
    }

    public static void xml(final String tag, final String contents) {
        log(XML | D, tag, contents);
    }

    public static void xml(@TYPE final int type, final String tag, final String contents) {
        log(XML | type, tag, contents);
    }

    private static void log(final int type, final String tag, final Object... contents) {
        if (!sLogSwitch || (!sLog2ConsoleSwitch && !sLog2FileSwitch)) { // 总开关
            return;
        }
        int type_low = type & 0x0f;
        int type_high = type & 0xf0;
        if (type_low < sConsoleFilter && type_low < sFileFilter) {
            return;
        }
        final String[] tagAndHead = processTagAndHead(tag);
        String body = processBody(type_high, contents);

        if (sLog2ConsoleSwitch && type_low >= sConsoleFilter) {
            print2Console(type_low, tagAndHead[0], tagAndHead[1] + body);
        }
        if (sLog2FileSwitch && type_high == FILE) {
            if (type_low >= sFileFilter) print2File(type_low, tagAndHead[0], tagAndHead[2] + body);
        }
    }

    private static String[] processTagAndHead(String tag) {
        if (!sTagIsSpace && !sLogHeadSwitch) {
            tag = sGlobalTag;
        } else {
            StackTraceElement targetElement = new Throwable().getStackTrace()[3];
            String className = targetElement.getClassName();
            String[] classNameInfo = className.split("\\.");
            if (classNameInfo.length > 0) {
                className = classNameInfo[classNameInfo.length - 1];
            }
            if (className.contains("$")) {
                className = className.split("\\$")[0];
            }
            if (sTagIsSpace) {
                tag = isSpace(tag) ? className : tag;
            }
            if (sLogHeadSwitch) {
                String head = new Formatter()
                        .format("%s, %s(%s.java:%d)",
                                Thread.currentThread().getName(),
                                targetElement.getMethodName(),
                                className,
                                targetElement.getLineNumber())
                        .toString();
                return new String[]{tag, head + LINE_SEP, " [" + head + "]: "};
            }
        }
        return new String[]{tag, "", ": "};
    }

    private static String processBody(final int type, final Object... contents) {
        String body = NULL_TIPS;
        if (contents != null) {
            if (contents.length == 1) {
                Object object = contents[0];
                body = object == null ? NULL : object.toString();
                if (type == JSON) {
                    body = formatJson(body);
                } else if (type == XML) {
                    body = formatXml(body);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0, len = contents.length; i < len; ++i) {
                    Object content = contents[i];
                    sb.append(ARGS)
                            .append("[")
                            .append(i)
                            .append("]")
                            .append(" = ")
                            .append(content == null ? NULL : content.toString())
                            .append(LINE_SEP);
                }
                body = sb.toString();
            }
        }
        return body;
    }

    private static String formatJson(String json) {
        try {
            if (json.startsWith("{")) {
                json = new JSONObject(json).toString(4);
            } else if (json.startsWith("[")) {
                json = new JSONArray(json).toString(4);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static String formatXml(String xml) {
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(xmlInput, xmlOutput);
            xml = xmlOutput.getWriter().toString().replaceFirst(">", ">" + LINE_SEP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    private static void print2Console(final int type, final String tag, String msg) {
        if (sLogBorderSwitch) {
            print(type, tag, TOP_BORDER);
            msg = addLeftBorder(msg);
        }
        int len = msg.length();
        int countOfSub = len / MAX_LEN;
        if (countOfSub > 0) {
            print(type, tag, msg.substring(0, MAX_LEN));
            String sub;
            int index = MAX_LEN;
            for (int i = 1; i < countOfSub; i++) {
                sub = msg.substring(index, index + MAX_LEN);
                print(type, tag, sLogBorderSwitch ? LEFT_BORDER + sub : sub);
                index += MAX_LEN;
            }
            sub = msg.substring(index, len);
            print(type, tag, sLogBorderSwitch ? LEFT_BORDER + sub : sub);
        } else {
            print(type, tag, msg);
        }
        if (sLogBorderSwitch) print(type, tag, BOTTOM_BORDER);
    }

    private static void print(final int type, final String tag, final String msg) {
        Log.println(type, tag, msg);
    }

    private static String addLeftBorder(final String msg) {
        if (!sLogBorderSwitch) return msg;
        StringBuilder sb = new StringBuilder();
        String[] lines = msg.split(LINE_SEP);
        for (String line : lines) {
            sb.append(LEFT_BORDER).append(line).append(LINE_SEP);
        }
        return sb.toString();
    }

    private static void print2File(final int type, final String tag, final String msg) {
        if (TextUtils.isEmpty(sFileName)) {
            sFileName = "log_" + FORMAT_2.format(new Date());
        }
        final String fullPath = new File(sDir, sFileName + LOG_FILE_SUFFIX).getPath();
        if (!createOrExistsFile(fullPath)) {
            Log.e(tag, "log to " + fullPath + " failed!");
            return;
        }
        String time = FORMAT.format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append(time)
                .append(T[type - V])
                .append("/")
                .append(tag)
                .append(msg)
                .append(LINE_SEP);
        final String content = sb.toString();
        if (executor == null) {
            executor = Executors.newSingleThreadExecutor();
        }
        executor.execute(new Runnable() {
            @Override
            public void run() {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter(fullPath, true));
                    bw.write(content);
                    Log.d(tag, "log to " + fullPath + " success!");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(tag, "log to " + fullPath + " failed!");
                } finally {
                    try {
                        if (bw != null) {
                            bw.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private static boolean createOrExistsFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static byte[] compress(final byte input[]) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Deflater compressor = new Deflater(1);
        try {
            compressor.setInput(input);
            compressor.finish();
            final byte[] buf = new byte[2048];
            while (!compressor.finished()) {
                int count = compressor.deflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            compressor.end();
        }
        return bos.toByteArray();
    }

    public static byte[] uncompress(final byte[] input) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Inflater decompressor = new Inflater();
        try {
            decompressor.setInput(input);
            final byte[] buf = new byte[2048];
            while (!decompressor.finished()) {
                int count = 0;
                try {
                    count = decompressor.inflate(buf);
                } catch (DataFormatException e) {
                    e.printStackTrace();
                }
                bos.write(buf, 0, count);
            }
        } finally {
            decompressor.end();
        }
        return bos.toByteArray();
    }

    /**
     * 获取日志文件目录
     *
     * @return
     */
    public static String getDir() {
        return sDir;
    }

    /**
     * 获取当前日志文件
     *
     * @return 文件不存在返回“”
     */
    public static String getCurrentFile() {
        File file = new File(getDir(), sFileName + LOG_FILE_SUFFIX);
        if (file.exists()) {
            return file.getPath();
        }
        return "";
    }

    /**
     * 获取需要上传的日志文件
     *
     * @return
     */
    public static ArrayList<String> getUploadFiles() {
        ArrayList<String> uploadFiles = new ArrayList<>();
        File[] files = null;
        try {
            File dir = new File(getDir());
            files = dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isFile() && pathname.getName().toLowerCase().endsWith(LOG_FILE_SUFFIX);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (files == null) return null;
        for (File f : files) {
            if (f.exists() && !TextUtils.equals(f.getPath(), getCurrentFile())) {
                uploadFiles.add(f.getPath());
            }
        }
        return uploadFiles;
    }

    /**
     * 删除日志文件
     *
     * @param fileName 文件名，不含路径
     */
    public static void deleteFile(String fileName) {
        File file = new File(getDir(), fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
