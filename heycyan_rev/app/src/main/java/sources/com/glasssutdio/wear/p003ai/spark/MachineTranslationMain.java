package com.glasssutdio.wear.p003ai.spark;

import android.os.Build;
import com.elvishew.xlog.XLog;
import com.glasssutdio.wear.p003ai.bean.TranslateBean;
import com.glasssutdio.wear.p003ai.bean.TranslateResult;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.cli.HelpFormatter;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.p008io.IOUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iflytek.sparkchain.core.its.ITS;
import com.iflytek.sparkchain.core.its.ITSCallbacks;
import com.iflytek.sparkchain.core.its.TransType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class MachineTranslationMain {
    private static String APIKey = "f7b7aed3090bccedd4851ef4be86bba0";
    private static String APISecret = "NjM5ZGU0ODRjMWJhYjg4NmE5MmUyZGY5";
    private static String APPID = "a79afcfe";
    private static final String RES_ID = "its_en_cn_word";
    private static final Gson gson = new Gson();
    private static String requestUrl = "https://itrans.xf-yun.com/v1/its";
    private String fromLanguage;
    private OnTranslateListener listener;
    private String toLanguage;
    private ITS its = null;
    ITSCallbacks miTransCallback = new ITSCallbacks() { // from class: com.glasssutdio.wear.ai.spark.MachineTranslationMain.1
        @Override // com.iflytek.sparkchain.core.its.ITSCallbacks
        public void onResult(ITS.ITSResult itsResult, Object o) {
            String src = itsResult.getTransResult().getSrc();
            String dst = itsResult.getTransResult().getDst();
            int status = itsResult.getStatus();
            String from = itsResult.getFrom();
            String to = itsResult.getTo();
            XLog.m137i("{src:" + src + ",dst:" + dst + ",status:" + status + ",from:" + from + ",to:" + to + ",sid:" + itsResult.getSid() + "}");
            if (MachineTranslationMain.this.listener != null) {
                MachineTranslationMain.this.listener.onTranslate(new TranslateBean(from, to, new TranslateResult(itsResult.getTransResult().getSrc(), itsResult.getTransResult().getDst())));
            }
        }

        @Override // com.iflytek.sparkchain.core.its.ITSCallbacks
        public void onError(ITS.ITSError itsError, Object o) {
            int code = itsError.getCode();
            XLog.m137i("翻译出错！错误码:" + code + "\n错误信息:" + itsError.getErrMsg() + "\nSid:" + itsError.getSid() + IOUtils.LINE_SEPARATOR_UNIX);
            if (MachineTranslationMain.this.listener != null) {
                MachineTranslationMain.this.listener.onTranslateError(code);
            }
        }
    };

    public interface OnTranslateListener {
        void onTranslate(TranslateBean translateBean);

        void onTranslateError(int errorCode);
    }

    public MachineTranslationMain(OnTranslateListener listener) {
        this.listener = listener;
    }

    public MachineTranslationMain() {
    }

    public void setListener(OnTranslateListener listener) {
        this.listener = listener;
    }

    public OnTranslateListener getListener() {
        return this.listener;
    }

    public void translate(String from, String to, String text) {
        XLog.m137i("from = " + from + ",to = " + to);
        if (from.startsWith("cn")) {
            from = "zh-CN";
        }
        if (to.startsWith("cn")) {
            to = "zh-CN";
        }
        if (!Objects.equals(this.fromLanguage, from) || !Objects.equals(this.toLanguage, to)) {
            this.its = new ITS(from, to, TransType.GOOGLE_TRANS);
        }
        if (this.its == null) {
            this.its = new ITS(from, to, TransType.GOOGLE_TRANS);
        }
        this.fromLanguage = from;
        this.toLanguage = to;
        this.its.registerCallbacks(this.miTransCallback);
        XLog.m127d("its.arun ret:" + this.its.arun(text, "12345") + ",text:" + text + "-----" + from + to);
    }

    public TranslateBean translateOld(String from, String to, String text) {
        try {
            String strDoRequest = doRequest(from, to, text);
            System.out.println("resp=>" + strDoRequest);
            String str = Build.VERSION.SDK_INT >= 26 ? new String(Base64.getDecoder().decode(((JsonParse) gson.fromJson(strDoRequest, JsonParse.class)).payload.result.text), StandardCharsets.UTF_8) : null;
            System.out.println("text字段Base64解码后=>" + new Gson().toJson(str));
            return (TranslateBean) fromJson(str, TranslateBean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        return (T) Holder.GSON_INSTANCE.fromJson(str, (Class) cls);
    }

    private static class Holder {
        private static final Gson GSON_INSTANCE = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        private Holder() {
        }
    }

    public String doRequest(String from, String to, String text) throws Exception {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(buildRequetUrl()).openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
        httpURLConnection.setRequestProperty("Content-type", "application/json");
        OutputStream outputStream = httpURLConnection.getOutputStream();
        String strBuildParam = buildParam(from, to, text);
        System.out.println("params=>" + strBuildParam.replaceAll(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR, ""));
        outputStream.write(strBuildParam.getBytes());
        outputStream.flush();
        try {
            return readAllBytes(httpURLConnection.getInputStream());
        } catch (Exception unused) {
            throw new Exception("make request error:code is " + httpURLConnection.getResponseMessage() + readAllBytes(httpURLConnection.getErrorStream()));
        }
    }

    public String buildRequetUrl() throws NoSuchAlgorithmException, InvalidKeyException {
        try {
            URL url = new URL(requestUrl.replace("ws://", "http://").replace("wss://", "https://"));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            String str = simpleDateFormat.format(new Date());
            String host = url.getHost();
            StringBuilder sbAppend = new StringBuilder("host: ").append(host).append("\ndate: ").append(str).append("\nPOST ").append(url.getPath()).append(" HTTP/1.1");
            Charset charset = StandardCharsets.UTF_8;
            Mac mac = Mac.getInstance("hmacsha256");
            mac.init(new SecretKeySpec(APISecret.getBytes(charset), "hmacsha256"));
            return String.format("%s?authorization=%s&host=%s&date=%s", requestUrl, URLEncoder.encode(Build.VERSION.SDK_INT >= 26 ? Base64.getEncoder().encodeToString(String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", APIKey, "hmac-sha256", "host date request-line", Build.VERSION.SDK_INT >= 26 ? Base64.getEncoder().encodeToString(mac.doFinal(sbAppend.toString().getBytes(charset))) : null).getBytes(charset)) : null), URLEncoder.encode(host), URLEncoder.encode(str));
        } catch (Exception e) {
            throw new RuntimeException("assemble requestUrl error:" + e.getMessage());
        }
    }

    private String buildParam(String from, String to, String text) {
        if (Build.VERSION.SDK_INT >= 26) {
            return "{    \"header\": {        \"app_id\": \"" + APPID + "\",        \"status\": 3,        \"res_id\": \"its_en_cn_word\"    },    \"parameter\": {        \"its\": {            \"from\": \"" + from + "\",            \"to\": \"" + to + "\",            \"result\": {}        }    },    \"payload\": {        \"input_data\": {            \"encoding\": \"utf8\",            \"status\": 3,            \"text\": \"" + Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8)) + "\"        }    }}";
        }
        return null;
    }

    private String readAllBytes(InputStream is) throws IOException {
        byte[] bArr = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = is.read(bArr);
            if (i != -1) {
                sb.append(new String(bArr, 0, i, StandardCharsets.UTF_8));
            } else {
                return sb.toString();
            }
        }
    }

    class JsonParse {
        Payload payload;

        JsonParse() {
        }
    }

    class Payload {
        Result result;

        Payload() {
        }
    }

    class Result {
        String text;

        Result() {
        }
    }
}
