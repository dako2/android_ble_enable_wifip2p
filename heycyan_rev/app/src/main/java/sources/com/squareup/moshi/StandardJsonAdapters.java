package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
final class StandardJsonAdapters {
    private static final String ERROR_FORMAT = "Expected %s but was %s at path %s";
    public static final JsonAdapter.Factory FACTORY = new JsonAdapter.Factory() { // from class: com.squareup.moshi.StandardJsonAdapters.1
        @Override // com.squareup.moshi.JsonAdapter.Factory
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
            if (!set.isEmpty()) {
                return null;
            }
            if (type == Boolean.TYPE) {
                return StandardJsonAdapters.BOOLEAN_JSON_ADAPTER;
            }
            if (type == Byte.TYPE) {
                return StandardJsonAdapters.BYTE_JSON_ADAPTER;
            }
            if (type == Character.TYPE) {
                return StandardJsonAdapters.CHARACTER_JSON_ADAPTER;
            }
            if (type == Double.TYPE) {
                return StandardJsonAdapters.DOUBLE_JSON_ADAPTER;
            }
            if (type == Float.TYPE) {
                return StandardJsonAdapters.FLOAT_JSON_ADAPTER;
            }
            if (type == Integer.TYPE) {
                return StandardJsonAdapters.INTEGER_JSON_ADAPTER;
            }
            if (type == Long.TYPE) {
                return StandardJsonAdapters.LONG_JSON_ADAPTER;
            }
            if (type == Short.TYPE) {
                return StandardJsonAdapters.SHORT_JSON_ADAPTER;
            }
            if (type == Boolean.class) {
                return StandardJsonAdapters.BOOLEAN_JSON_ADAPTER.nullSafe();
            }
            if (type == Byte.class) {
                return StandardJsonAdapters.BYTE_JSON_ADAPTER.nullSafe();
            }
            if (type == Character.class) {
                return StandardJsonAdapters.CHARACTER_JSON_ADAPTER.nullSafe();
            }
            if (type == Double.class) {
                return StandardJsonAdapters.DOUBLE_JSON_ADAPTER.nullSafe();
            }
            if (type == Float.class) {
                return StandardJsonAdapters.FLOAT_JSON_ADAPTER.nullSafe();
            }
            if (type == Integer.class) {
                return StandardJsonAdapters.INTEGER_JSON_ADAPTER.nullSafe();
            }
            if (type == Long.class) {
                return StandardJsonAdapters.LONG_JSON_ADAPTER.nullSafe();
            }
            if (type == Short.class) {
                return StandardJsonAdapters.SHORT_JSON_ADAPTER.nullSafe();
            }
            if (type == String.class) {
                return StandardJsonAdapters.STRING_JSON_ADAPTER.nullSafe();
            }
            if (type == Object.class) {
                return new ObjectJsonAdapter(moshi).nullSafe();
            }
            Class<?> rawType = Types.getRawType(type);
            JsonAdapter<?> jsonAdapterGeneratedAdapter = Util.generatedAdapter(moshi, type, rawType);
            if (jsonAdapterGeneratedAdapter != null) {
                return jsonAdapterGeneratedAdapter;
            }
            if (rawType.isEnum()) {
                return new EnumJsonAdapter(rawType).nullSafe();
            }
            return null;
        }
    };
    static final JsonAdapter<Boolean> BOOLEAN_JSON_ADAPTER = new JsonAdapter<Boolean>() { // from class: com.squareup.moshi.StandardJsonAdapters.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Boolean fromJson(JsonReader jsonReader) throws IOException {
            return Boolean.valueOf(jsonReader.nextBoolean());
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Boolean bool) throws IOException {
            jsonWriter.value(bool.booleanValue());
        }

        public String toString() {
            return "JsonAdapter(Boolean)";
        }
    };
    static final JsonAdapter<Byte> BYTE_JSON_ADAPTER = new JsonAdapter<Byte>() { // from class: com.squareup.moshi.StandardJsonAdapters.3
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Byte fromJson(JsonReader jsonReader) throws IOException {
            return Byte.valueOf((byte) StandardJsonAdapters.rangeCheckNextInt(jsonReader, "a byte", -128, 255));
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Byte b) throws IOException {
            jsonWriter.value(b.intValue() & 255);
        }

        public String toString() {
            return "JsonAdapter(Byte)";
        }
    };
    static final JsonAdapter<Character> CHARACTER_JSON_ADAPTER = new JsonAdapter<Character>() { // from class: com.squareup.moshi.StandardJsonAdapters.4
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Character fromJson(JsonReader jsonReader) throws IOException {
            String strNextString = jsonReader.nextString();
            if (strNextString.length() > 1) {
                throw new JsonDataException(String.format(StandardJsonAdapters.ERROR_FORMAT, "a char", "\"" + strNextString + '\"', jsonReader.getPath()));
            }
            return Character.valueOf(strNextString.charAt(0));
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Character ch) throws IOException {
            jsonWriter.value(ch.toString());
        }

        public String toString() {
            return "JsonAdapter(Character)";
        }
    };
    static final JsonAdapter<Double> DOUBLE_JSON_ADAPTER = new JsonAdapter<Double>() { // from class: com.squareup.moshi.StandardJsonAdapters.5
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Double fromJson(JsonReader jsonReader) throws IOException {
            return Double.valueOf(jsonReader.nextDouble());
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Double d) throws IOException {
            jsonWriter.value(d.doubleValue());
        }

        public String toString() {
            return "JsonAdapter(Double)";
        }
    };
    static final JsonAdapter<Float> FLOAT_JSON_ADAPTER = new JsonAdapter<Float>() { // from class: com.squareup.moshi.StandardJsonAdapters.6
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Float fromJson(JsonReader jsonReader) throws IOException {
            float fNextDouble = (float) jsonReader.nextDouble();
            if (!jsonReader.isLenient() && Float.isInfinite(fNextDouble)) {
                throw new JsonDataException("JSON forbids NaN and infinities: " + fNextDouble + " at path " + jsonReader.getPath());
            }
            return Float.valueOf(fNextDouble);
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Float f) throws IOException {
            f.getClass();
            jsonWriter.value(f);
        }

        public String toString() {
            return "JsonAdapter(Float)";
        }
    };
    static final JsonAdapter<Integer> INTEGER_JSON_ADAPTER = new JsonAdapter<Integer>() { // from class: com.squareup.moshi.StandardJsonAdapters.7
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Integer fromJson(JsonReader jsonReader) throws IOException {
            return Integer.valueOf(jsonReader.nextInt());
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Integer num) throws IOException {
            jsonWriter.value(num.intValue());
        }

        public String toString() {
            return "JsonAdapter(Integer)";
        }
    };
    static final JsonAdapter<Long> LONG_JSON_ADAPTER = new JsonAdapter<Long>() { // from class: com.squareup.moshi.StandardJsonAdapters.8
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.moshi.JsonAdapter
        public Long fromJson(JsonReader jsonReader) throws IOException {
            return Long.valueOf(jsonReader.nextLong());
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Long l) throws IOException {
            jsonWriter.value(l.longValue());
        }

        public String toString() {
            return "JsonAdapter(Long)";
        }
    };
    static final JsonAdapter<Short> SHORT_JSON_ADAPTER = new JsonAdapter<Short>() { // from class: com.squareup.moshi.StandardJsonAdapters.9
        @Override // com.squareup.moshi.JsonAdapter
        public Short fromJson(JsonReader jsonReader) throws IOException {
            return Short.valueOf((short) StandardJsonAdapters.rangeCheckNextInt(jsonReader, "a short", -32768, 32767));
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Short sh) throws IOException {
            jsonWriter.value(sh.intValue());
        }

        public String toString() {
            return "JsonAdapter(Short)";
        }
    };
    static final JsonAdapter<String> STRING_JSON_ADAPTER = new JsonAdapter<String>() { // from class: com.squareup.moshi.StandardJsonAdapters.10
        @Override // com.squareup.moshi.JsonAdapter
        public String fromJson(JsonReader jsonReader) throws IOException {
            return jsonReader.nextString();
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, String str) throws IOException {
            jsonWriter.value(str);
        }

        public String toString() {
            return "JsonAdapter(String)";
        }
    };

    private StandardJsonAdapters() {
    }

    static int rangeCheckNextInt(JsonReader jsonReader, String str, int i, int i2) throws IOException {
        int iNextInt = jsonReader.nextInt();
        if (iNextInt < i || iNextInt > i2) {
            throw new JsonDataException(String.format(ERROR_FORMAT, str, Integer.valueOf(iNextInt), jsonReader.getPath()));
        }
        return iNextInt;
    }

    static final class EnumJsonAdapter<T extends Enum<T>> extends JsonAdapter<T> {
        private final T[] constants;
        private final Class<T> enumType;
        private final String[] nameStrings;
        private final JsonReader.Options options;

        EnumJsonAdapter(Class<T> cls) {
            this.enumType = cls;
            try {
                T[] enumConstants = cls.getEnumConstants();
                this.constants = enumConstants;
                this.nameStrings = new String[enumConstants.length];
                int i = 0;
                while (true) {
                    T[] tArr = this.constants;
                    if (i < tArr.length) {
                        String strName = tArr[i].name();
                        this.nameStrings[i] = Util.jsonName(strName, cls.getField(strName));
                        i++;
                    } else {
                        this.options = JsonReader.Options.m593of(this.nameStrings);
                        return;
                    }
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError("Missing field in " + cls.getName(), e);
            }
        }

        @Override // com.squareup.moshi.JsonAdapter
        public T fromJson(JsonReader jsonReader) throws IOException {
            int iSelectString = jsonReader.selectString(this.options);
            if (iSelectString != -1) {
                return this.constants[iSelectString];
            }
            throw new JsonDataException("Expected one of " + Arrays.asList(this.nameStrings) + " but was " + jsonReader.nextString() + " at path " + jsonReader.getPath());
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, T t) throws IOException {
            jsonWriter.value(this.nameStrings[t.ordinal()]);
        }

        public String toString() {
            return "JsonAdapter(" + this.enumType.getName() + ")";
        }
    }

    static final class ObjectJsonAdapter extends JsonAdapter<Object> {
        private final JsonAdapter<Boolean> booleanAdapter;
        private final JsonAdapter<Double> doubleAdapter;
        private final JsonAdapter<List> listJsonAdapter;
        private final JsonAdapter<Map> mapAdapter;
        private final Moshi moshi;
        private final JsonAdapter<String> stringAdapter;

        ObjectJsonAdapter(Moshi moshi) {
            this.moshi = moshi;
            this.listJsonAdapter = moshi.adapter(List.class);
            this.mapAdapter = moshi.adapter(Map.class);
            this.stringAdapter = moshi.adapter(String.class);
            this.doubleAdapter = moshi.adapter(Double.class);
            this.booleanAdapter = moshi.adapter(Boolean.class);
        }

        @Override // com.squareup.moshi.JsonAdapter
        public Object fromJson(JsonReader jsonReader) throws IOException {
            switch (C242411.$SwitchMap$com$squareup$moshi$JsonReader$Token[jsonReader.peek().ordinal()]) {
                case 1:
                    return this.listJsonAdapter.fromJson(jsonReader);
                case 2:
                    return this.mapAdapter.fromJson(jsonReader);
                case 3:
                    return this.stringAdapter.fromJson(jsonReader);
                case 4:
                    return this.doubleAdapter.fromJson(jsonReader);
                case 5:
                    return this.booleanAdapter.fromJson(jsonReader);
                case 6:
                    return jsonReader.nextNull();
                default:
                    throw new IllegalStateException("Expected a value but was " + jsonReader.peek() + " at path " + jsonReader.getPath());
            }
        }

        @Override // com.squareup.moshi.JsonAdapter
        public void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
            Class<?> cls = obj.getClass();
            if (cls == Object.class) {
                jsonWriter.beginObject();
                jsonWriter.endObject();
            } else {
                this.moshi.adapter(toJsonType(cls), Util.NO_ANNOTATIONS).toJson(jsonWriter, (JsonWriter) obj);
            }
        }

        private Class<?> toJsonType(Class<?> cls) {
            return Map.class.isAssignableFrom(cls) ? Map.class : Collection.class.isAssignableFrom(cls) ? Collection.class : cls;
        }

        public String toString() {
            return "JsonAdapter(Object)";
        }
    }

    /* renamed from: com.squareup.moshi.StandardJsonAdapters$11 */
    static /* synthetic */ class C242411 {
        static final /* synthetic */ int[] $SwitchMap$com$squareup$moshi$JsonReader$Token;

        static {
            int[] iArr = new int[JsonReader.Token.values().length];
            $SwitchMap$com$squareup$moshi$JsonReader$Token = iArr;
            try {
                iArr[JsonReader.Token.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$squareup$moshi$JsonReader$Token[JsonReader.Token.BEGIN_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$squareup$moshi$JsonReader$Token[JsonReader.Token.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$squareup$moshi$JsonReader$Token[JsonReader.Token.NUMBER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$squareup$moshi$JsonReader$Token[JsonReader.Token.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$squareup$moshi$JsonReader$Token[JsonReader.Token.NULL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
