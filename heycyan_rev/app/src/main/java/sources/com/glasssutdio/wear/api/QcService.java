package com.glasssutdio.wear.api;

import com.glasssutdio.wear.all.bean.Req.CheckVersionReq;
import com.glasssutdio.wear.all.bean.Req.FeedbackReq;
import com.glasssutdio.wear.all.bean.Req.LoginReq;
import com.glasssutdio.wear.all.bean.Req.ResetPwdReq;
import com.glasssutdio.wear.all.bean.Req.UpdateUserLocationReq;
import com.glasssutdio.wear.all.bean.Req.UpdateUserReq;
import com.glasssutdio.wear.all.bean.ResetPwdModel;
import com.glasssutdio.wear.api.request.AiChatBean;
import com.glasssutdio.wear.api.request.LastOtaRequest;
import com.glasssutdio.wear.api.request.VisionChatRequest;
import com.glasssutdio.wear.api.response.DevicePictureResp;
import com.glasssutdio.wear.api.response.FirmwareOtaResp;
import com.glasssutdio.wear.home.bean.AppUpgradeModel;
import com.glasssutdio.wear.home.bean.LoginResModel;
import com.glasssutdio.wear.home.bean.UserModel;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie.ClientCookie;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/* compiled from: QcService.kt */
@Metadata(m606d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 E2\u00020\u0001:\u0001EJ1\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH§@ø\u0001\u0000¢\u0006\u0002\u0010\tJ+\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\fH§@ø\u0001\u0000¢\u0006\u0002\u0010\rJ!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0011H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0015H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0018H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00032\b\b\u0001\u0010\u001c\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00032\b\b\u0001\u0010 \u001a\u00020!H§@ø\u0001\u0000¢\u0006\u0002\u0010\"J!\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00032\b\b\u0001\u0010 \u001a\u00020!H§@ø\u0001\u0000¢\u0006\u0002\u0010\"J!\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010%\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ!\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ!\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00032\b\b\u0001\u0010\u0010\u001a\u00020*H§@ø\u0001\u0000¢\u0006\u0002\u0010+J!\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ!\u0010-\u001a\b\u0012\u0004\u0012\u00020)0\u00032\b\b\u0001\u0010\u0010\u001a\u00020*H§@ø\u0001\u0000¢\u0006\u0002\u0010+J+\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010/\u001a\u00020\u00062\b\b\u0001\u00100\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u00101J!\u00102\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0015H§@ø\u0001\u0000¢\u0006\u0002\u0010\u0016J!\u00103\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u00104\u001a\u00020\u0006H§@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ+\u00105\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u00106\u001a\u00020\u00062\b\b\u0001\u00107\u001a\u000208H§@ø\u0001\u0000¢\u0006\u0002\u00109J\u001b\u0010:\u001a\u00020;2\b\b\u0001\u0010\u0010\u001a\u00020<H§@ø\u0001\u0000¢\u0006\u0002\u0010=J!\u0010>\u001a\b\u0012\u0004\u0012\u00020'0\u00032\b\b\u0001\u0010\u0010\u001a\u00020?H§@ø\u0001\u0000¢\u0006\u0002\u0010@J!\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0001\u0010B\u001a\u00020CH§@ø\u0001\u0000¢\u0006\u0002\u0010D\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006F"}, m607d2 = {"Lcom/glasssutdio/wear/api/QcService;", "", "aiChatGPT", "Lcom/glasssutdio/wear/api/QcResponse;", "Lcom/glasssutdio/wear/api/request/AiChatBean;", "uid", "", "messages", "", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "aiVisual", "request", "Lcom/glasssutdio/wear/api/request/VisionChatRequest;", "(Ljava/lang/String;Lcom/glasssutdio/wear/api/request/VisionChatRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "appLastVersion", "Lcom/glasssutdio/wear/home/bean/AppUpgradeModel;", "req", "Lcom/glasssutdio/wear/all/bean/Req/CheckVersionReq;", "(Lcom/glasssutdio/wear/all/bean/Req/CheckVersionReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emailSendCode", "Lcom/glasssutdio/wear/all/bean/ResetPwdModel;", "Lcom/glasssutdio/wear/all/bean/Req/ResetPwdReq;", "(Lcom/glasssutdio/wear/all/bean/Req/ResetPwdReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "feedback", "Lcom/glasssutdio/wear/all/bean/Req/FeedbackReq;", "(Lcom/glasssutdio/wear/all/bean/Req/FeedbackReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDevicePicture", "Lcom/glasssutdio/wear/api/response/DevicePictureResp;", ClientCookie.VERSION_ATTR, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastOta", "Lcom/glasssutdio/wear/api/response/FirmwareOtaResp;", "otaRequest", "Lcom/glasssutdio/wear/api/request/LastOtaRequest;", "(Lcom/glasssutdio/wear/api/request/LastOtaRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastOtaChina", "getToken", "key", "getUserInfo", "Lcom/glasssutdio/wear/home/bean/UserModel;", "login", "Lcom/glasssutdio/wear/home/bean/LoginResModel;", "Lcom/glasssutdio/wear/all/bean/Req/LoginReq;", "(Lcom/glasssutdio/wear/all/bean/Req/LoginReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logoff", "registerByEmail", "registerGetCode", "email", "appName", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetPassword", "scanConfig", "app", "uniqueMac", "mac", "type", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLocation", "Lcom/glasssutdio/wear/api/QcNoDataResponse;", "Lcom/glasssutdio/wear/all/bean/Req/UpdateUserLocationReq;", "(Lcom/glasssutdio/wear/all/bean/Req/UpdateUserLocationReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUserInfo", "Lcom/glasssutdio/wear/all/bean/Req/UpdateUserReq;", "(Lcom/glasssutdio/wear/all/bean/Req/UpdateUserReq;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadImg", "body", "Lokhttp3/RequestBody;", "(Lokhttp3/RequestBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
/* loaded from: classes.dex */
public interface QcService {
    public static final String BASE = "https://www.qlifesnap.com";
    public static final String BASE_URL = "https://www.qlifesnap.com/glasses/";
    public static final String BASE_URL_CHINA = "https://www.qlifesnap.com/glasses/";
    public static final String CHINA_PPM_AGREEMENT = "https://www.qlifesnap.com/ppm/heycyan_agreement.html";
    public static final String CHINA_PPM_POLICY = "https://www.qlifesnap.com/ppm/heycyan_cn.html";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final String HW_PPM_AGREEMENT = "https://www.qlifesnap.com/ppm/heycyan_agreement.html";
    public static final String HW_PPM_POLICY = "https://www.qlifesnap.com/heycyan.html";

    @RequiresSignature
    @POST("ai/chat/{uid}")
    Object aiChatGPT(@Path("uid") String str, @Body List<AiChatBean> list, Continuation<? super QcResponse<AiChatBean>> continuation);

    @POST("ai/visual/{uid}")
    Object aiVisual(@Path("uid") String str, @Body VisionChatRequest visionChatRequest, Continuation<? super QcResponse<AiChatBean>> continuation);

    @POST("app-update/appLastVersion")
    Object appLastVersion(@Body CheckVersionReq checkVersionReq, Continuation<? super QcResponse<AppUpgradeModel>> continuation);

    @POST("users/reset-password-email")
    Object emailSendCode(@Body ResetPwdReq resetPwdReq, Continuation<? super QcResponse<ResetPwdModel>> continuation);

    @POST("customer/submit/v2")
    Object feedback(@Body FeedbackReq feedbackReq, Continuation<? super QcResponse<String>> continuation);

    @GET("device/effectPicture")
    Object getDevicePicture(@Query("hardwareVersion") String str, Continuation<? super QcResponse<DevicePictureResp>> continuation);

    @POST("app-update/last-ota")
    Object getLastOta(@Body LastOtaRequest lastOtaRequest, Continuation<? super QcResponse<FirmwareOtaResp>> continuation);

    @POST("app-update/last-ota/china")
    Object getLastOtaChina(@Body LastOtaRequest lastOtaRequest, Continuation<? super QcResponse<FirmwareOtaResp>> continuation);

    @GET("token/getToken")
    Object getToken(@Query("key") String str, Continuation<? super QcResponse<String>> continuation);

    @GET("users/info")
    Object getUserInfo(@Query("uid") String str, Continuation<? super QcResponse<UserModel>> continuation);

    @POST("users/login/v1")
    Object login(@Body LoginReq loginReq, Continuation<? super QcResponse<LoginResModel>> continuation);

    @GET("users/login/logoff")
    Object logoff(@Query("uid") String str, Continuation<? super QcResponse<String>> continuation);

    @POST("users/register/v2/verification")
    Object registerByEmail(@Body LoginReq loginReq, Continuation<? super QcResponse<LoginResModel>> continuation);

    @GET("users/register/verification/code")
    Object registerGetCode(@Query("email") String str, @Query("appName") String str2, Continuation<? super QcResponse<String>> continuation);

    @POST("users/reset-password")
    Object resetPassword(@Body ResetPwdReq resetPwdReq, Continuation<? super QcResponse<ResetPwdModel>> continuation);

    @GET("device/scanConfig")
    Object scanConfig(@Query("app") String str, Continuation<? super QcResponse<String>> continuation);

    @RequiresSignature
    @GET("ai/unique/mac/type")
    Object uniqueMac(@Query("mac") String str, @Query("type") int i, Continuation<? super QcResponse<String>> continuation);

    @POST("users/userLocation")
    Object updateLocation(@Body UpdateUserLocationReq updateUserLocationReq, Continuation<? super QcNoDataResponse> continuation);

    @POST("users/update")
    Object updateUserInfo(@Body UpdateUserReq updateUserReq, Continuation<? super QcResponse<UserModel>> continuation);

    @POST("users/image/upload")
    Object uploadImg(@Body RequestBody requestBody, Continuation<? super QcResponse<String>> continuation);

    /* compiled from: QcService.kt */
    @Metadata(m606d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m607d2 = {"Lcom/glasssutdio/wear/api/QcService$Companion;", "", "()V", "BASE", "", "BASE_URL", "BASE_URL_CHINA", "CHINA_PPM_AGREEMENT", "CHINA_PPM_POLICY", "HW_PPM_AGREEMENT", "HW_PPM_POLICY", "app_release"}, m608k = 1, m609mv = {1, 9, 0}, m611xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String BASE = "https://www.qlifesnap.com";
        public static final String BASE_URL = "https://www.qlifesnap.com/glasses/";
        public static final String BASE_URL_CHINA = "https://www.qlifesnap.com/glasses/";
        public static final String CHINA_PPM_AGREEMENT = "https://www.qlifesnap.com/ppm/heycyan_agreement.html";
        public static final String CHINA_PPM_POLICY = "https://www.qlifesnap.com/ppm/heycyan_cn.html";
        public static final String HW_PPM_AGREEMENT = "https://www.qlifesnap.com/ppm/heycyan_agreement.html";
        public static final String HW_PPM_POLICY = "https://www.qlifesnap.com/heycyan.html";

        private Companion() {
        }
    }
}
