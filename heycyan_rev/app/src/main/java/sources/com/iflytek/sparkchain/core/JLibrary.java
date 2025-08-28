package com.iflytek.sparkchain.core;

@Deprecated
/* loaded from: classes2.dex */
public class JLibrary extends BaseLibrary {

    private static class Holder {
        private static final JLibrary instance = new JLibrary();

        private Holder() {
        }
    }

    private JLibrary() {
    }

    public static JLibrary getInst() {
        return Holder.instance;
    }

    @Override // com.iflytek.sparkchain.core.BaseLibrary
    public void registerListener(AiResponseListener aiResponseListener) {
        AiHelper.getInst().registerListener(aiResponseListener);
    }

    public void registerListener(CoreListener coreListener) {
        Auth.m510c().m516a(coreListener);
    }

    public void registerListener(CoreListener coreListener, AiResponseListener aiResponseListener) {
        registerListener(coreListener);
        registerListener(aiResponseListener);
    }

    public void registerListener(String str, AiListener aiListener) {
        AiHelper.getInst().registerListener(str, aiListener);
    }
}
