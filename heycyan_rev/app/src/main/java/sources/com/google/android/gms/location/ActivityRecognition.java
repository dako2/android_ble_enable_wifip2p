package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes.dex */
public class ActivityRecognition {

    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API = com.google.android.gms.libs.identity.zzaj.zzb;

    @Deprecated
    public static final ActivityRecognitionApi ActivityRecognitionApi = new com.google.android.gms.libs.identity.zzaf();

    private ActivityRecognition() {
    }

    public static ActivityRecognitionClient getClient(Activity activity) {
        return new com.google.android.gms.libs.identity.zzaj(activity);
    }

    public static ActivityRecognitionClient getClient(Context context) {
        return new com.google.android.gms.libs.identity.zzaj(context);
    }
}
