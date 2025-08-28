package com.iflytek.sparkchain.media.record;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* renamed from: com.iflytek.sparkchain.media.record.c */
/* loaded from: classes2.dex */
public class C2220c {

    /* renamed from: a */
    private RandomAccessFile f506a;

    /* renamed from: b */
    private short f507b;

    /* renamed from: c */
    private int f508c;

    /* renamed from: d */
    private short f509d;

    public C2220c(File file, int i) throws IOException {
        m540a(file, (short) 1, i, (short) 16);
    }

    /* renamed from: a */
    private boolean m540a(File file, short s, int i, short s2) throws IOException {
        if (file == null) {
            return false;
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        this.f506a = randomAccessFile;
        this.f507b = s;
        this.f508c = i;
        this.f509d = s2;
        randomAccessFile.write(new byte[44]);
        return true;
    }

    /* renamed from: a */
    public void m541a() throws IOException {
        RandomAccessFile randomAccessFile = this.f506a;
        if (randomAccessFile != null) {
            randomAccessFile.close();
            this.f506a = null;
        }
    }

    /* renamed from: a */
    public void m542a(int i) throws IOException {
        this.f506a.write(i);
        this.f506a.write(i >> 8);
        this.f506a.write(i >> 16);
        this.f506a.write(i >> 24);
    }

    /* renamed from: a */
    public void m543a(String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            this.f506a.write(str.charAt(i));
        }
    }

    /* renamed from: a */
    public void m544a(short s) throws IOException {
        this.f506a.write(s);
        this.f506a.write(s >> 8);
    }

    /* renamed from: b */
    public int m545b() throws IOException {
        return (int) (this.f506a.length() - 44);
    }

    /* renamed from: c */
    public void m546c() throws IOException {
        this.f506a.seek(0L);
        m543a("RIFF");
        m542a(m545b() + 36);
        m543a("WAVE");
        m543a("fmt ");
        m542a(16);
        m544a((short) 1);
        m544a(this.f507b);
        m542a(this.f508c);
        m542a(((this.f507b * this.f508c) * this.f509d) / 8);
        m544a((short) ((this.f507b * this.f509d) / 8));
        m544a(this.f509d);
        m543a("data");
        m542a(m545b());
    }
}
