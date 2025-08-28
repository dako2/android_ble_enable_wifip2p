package com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;

/* loaded from: classes2.dex */
public class DeflateDecompressingEntity extends DecompressingEntity {
    public DeflateDecompressingEntity(HttpEntity httpEntity) {
        super(httpEntity, DeflateInputStreamFactory.getInstance());
    }
}
