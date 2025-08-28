package com.google.mlkit.p012nl.languageid;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.tasks.Task;
import java.io.Closeable;
import java.util.List;

/* compiled from: com.google.mlkit:language-id-common@@16.0.0 */
/* loaded from: classes2.dex */
public interface LanguageIdentifier extends Closeable, LifecycleObserver {
    public static final float DEFAULT_IDENTIFY_LANGUAGE_CONFIDENCE_THRESHOLD = 0.5f;
    public static final float DEFAULT_IDENTIFY_POSSIBLE_LANGUAGES_CONFIDENCE_THRESHOLD = 0.01f;
    public static final String UNDETERMINED_LANGUAGE_TAG = "und";

    @Override // java.io.Closeable, java.lang.AutoCloseable
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void close();

    Task<String> identifyLanguage(String str);

    Task<List<IdentifiedLanguage>> identifyPossibleLanguages(String str);
}
