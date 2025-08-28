package com.liulishuo.okdownload.core.breakpoint;

import android.database.Cursor;
import java.io.File;

/* loaded from: classes2.dex */
public class BreakpointInfoRow {
    private final boolean chunked;
    private final String etag;
    private final String filename;

    /* renamed from: id */
    private final int f520id;
    private final String parentPath;
    private final boolean taskOnlyProvidedParentPath;
    private final String url;

    public BreakpointInfoRow(Cursor cursor) {
        this.f520id = cursor.getInt(cursor.getColumnIndex(BreakpointSQLiteKey.f521ID));
        this.url = cursor.getString(cursor.getColumnIndex("url"));
        this.etag = cursor.getString(cursor.getColumnIndex(BreakpointSQLiteKey.ETAG));
        this.parentPath = cursor.getString(cursor.getColumnIndex(BreakpointSQLiteKey.PARENT_PATH));
        this.filename = cursor.getString(cursor.getColumnIndex(BreakpointSQLiteKey.FILENAME));
        this.taskOnlyProvidedParentPath = cursor.getInt(cursor.getColumnIndex(BreakpointSQLiteKey.TASK_ONLY_PARENT_PATH)) == 1;
        this.chunked = cursor.getInt(cursor.getColumnIndex("chunked")) == 1;
    }

    public int getId() {
        return this.f520id;
    }

    public String getUrl() {
        return this.url;
    }

    public String getEtag() {
        return this.etag;
    }

    public String getParentPath() {
        return this.parentPath;
    }

    public String getFilename() {
        return this.filename;
    }

    public boolean isTaskOnlyProvidedParentPath() {
        return this.taskOnlyProvidedParentPath;
    }

    public boolean isChunked() {
        return this.chunked;
    }

    public BreakpointInfo toInfo() {
        BreakpointInfo breakpointInfo = new BreakpointInfo(this.f520id, this.url, new File(this.parentPath), this.filename, this.taskOnlyProvidedParentPath);
        breakpointInfo.setEtag(this.etag);
        breakpointInfo.setChunked(this.chunked);
        return breakpointInfo;
    }
}
