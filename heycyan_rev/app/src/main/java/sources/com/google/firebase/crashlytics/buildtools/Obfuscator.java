package com.google.firebase.crashlytics.buildtools;

/* loaded from: classes2.dex */
public class Obfuscator {
    private final Vendor vendor;
    private final String version;

    public enum Vendor {
        PROGUARD("proguard"),
        DEXGUARD("dexguard"),
        R8("R8");

        private final String name;

        Vendor(String str) {
            this.name = str;
        }

        public String getName() {
            return this.name;
        }
    }

    public Obfuscator(Vendor vendor, String str) {
        this.vendor = vendor;
        this.version = str;
    }

    public Vendor getVendor() {
        return this.vendor;
    }

    public String getVersion() {
        return this.version;
    }
}
