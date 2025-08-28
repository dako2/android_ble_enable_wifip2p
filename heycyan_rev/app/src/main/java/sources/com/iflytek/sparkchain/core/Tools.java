package com.iflytek.sparkchain.core;

import iflc.AbstractC2442a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class Tools {
    private ArrayList<AbstractC2442a> pluginList;

    public Tools() {
        this.pluginList = new ArrayList<>();
    }

    public Tools(AbstractC2442a[] abstractC2442aArr) {
        ArrayList<AbstractC2442a> arrayList = new ArrayList<>();
        this.pluginList = arrayList;
        arrayList.addAll(Arrays.asList(abstractC2442aArr));
    }

    public Tools append(Tools tools) {
        this.pluginList.addAll(tools.getAllPlugin());
        return this;
    }

    public Tools append(AbstractC2442a abstractC2442a) {
        this.pluginList.add(abstractC2442a);
        return this;
    }

    public Tools append(AbstractC2442a[] abstractC2442aArr) {
        this.pluginList.addAll(Arrays.asList(abstractC2442aArr));
        return this;
    }

    public ArrayList<AbstractC2442a> getAllPlugin() {
        return this.pluginList;
    }

    public AbstractC2442a getPlugin(String str) {
        if (str != null && !str.isEmpty()) {
            Iterator<AbstractC2442a> it = this.pluginList.iterator();
            while (it.hasNext()) {
                AbstractC2442a next = it.next();
                if (str.equals(next.f553a)) {
                    return next;
                }
            }
        }
        return null;
    }

    public String toString() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator<AbstractC2442a> it = this.pluginList.iterator();
        while (it.hasNext()) {
            AbstractC2442a next = it.next();
            JSONObject jSONObject = new JSONObject();
            try {
                String str = next.f553a;
                Objects.isNull(str);
                jSONObject.put("name", str == null ? "" : next.f553a);
                String str2 = next.f554b;
                Objects.isNull(str2);
                jSONObject.put("desc", str2 == null ? "" : next.f554b);
                jSONObject.put("prompt", "");
                jSONObject.put("isDirect", next.f555c);
                jSONObject.put("platform", "android");
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return jSONArray.toString();
    }
}
