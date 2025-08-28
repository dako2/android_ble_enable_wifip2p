package com.google.android.gms.libs.identity;

import android.location.Location;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes.dex */
public interface zzz extends IInterface {
    void zzb(Status status, Location location) throws RemoteException;
}
