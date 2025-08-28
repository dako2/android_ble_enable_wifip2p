package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.cookie;

import java.util.Date;

/* loaded from: classes2.dex */
public interface Cookie {
    String getComment();

    String getCommentURL();

    String getDomain();

    Date getExpiryDate();

    String getName();

    String getPath();

    int[] getPorts();

    String getValue();

    int getVersion();

    boolean isExpired(Date date);

    boolean isPersistent();

    boolean isSecure();
}
