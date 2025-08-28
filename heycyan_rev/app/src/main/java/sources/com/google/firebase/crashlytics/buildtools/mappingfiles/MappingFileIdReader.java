package com.google.firebase.crashlytics.buildtools.mappingfiles;

import com.google.firebase.crashlytics.buildtools.Buildtools;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/* loaded from: classes2.dex */
public abstract class MappingFileIdReader {
    protected final DocumentBuilder docBuilder;

    protected abstract Document parseXmlSource() throws SAXException, IOException;

    private static class FileIdReader extends MappingFileIdReader {
        private final File file;

        FileIdReader(File file, DocumentBuilder documentBuilder) {
            super(documentBuilder);
            this.file = file;
        }

        @Override // com.google.firebase.crashlytics.buildtools.mappingfiles.MappingFileIdReader
        protected Document parseXmlSource() throws SAXException, IOException {
            if (this.file.exists()) {
                return getDocumentBuilder().parse(this.file);
            }
            return null;
        }
    }

    private static class StringIdReader extends MappingFileIdReader {
        private final String string;

        StringIdReader(String str, DocumentBuilder documentBuilder) {
            super(documentBuilder);
            this.string = str;
        }

        @Override // com.google.firebase.crashlytics.buildtools.mappingfiles.MappingFileIdReader
        protected Document parseXmlSource() throws SAXException, IOException {
            String str = this.string;
            if (str == null || str.isEmpty()) {
                return null;
            }
            return getDocumentBuilder().parse(new ByteArrayInputStream(this.string.getBytes(StandardCharsets.UTF_8)));
        }
    }

    public static MappingFileIdReader create(File file) {
        try {
            return new FileIdReader(file, DocumentBuilderFactory.newInstance().newDocumentBuilder());
        } catch (ParserConfigurationException e) {
            Buildtools.logE("Crashlytics experienced an unrecoverable parser configuration exception", e);
            throw new RuntimeException(e);
        }
    }

    public static MappingFileIdReader create(String str) {
        try {
            return new StringIdReader(str, DocumentBuilderFactory.newInstance().newDocumentBuilder());
        } catch (ParserConfigurationException e) {
            Buildtools.logE("Crashlytics experienced an unrecoverable parser configuration exception", e);
            throw new RuntimeException(e);
        }
    }

    protected MappingFileIdReader(DocumentBuilder documentBuilder) {
        this.docBuilder = documentBuilder;
    }

    protected DocumentBuilder getDocumentBuilder() {
        return this.docBuilder;
    }

    public String getMappingFileId() throws IOException {
        Element mappingFileIdElement;
        try {
            Document xmlSource = parseXmlSource();
            if (xmlSource == null || (mappingFileIdElement = XmlResourceUtils.getMappingFileIdElement(xmlSource)) == null) {
                return null;
            }
            return mappingFileIdElement.getTextContent();
        } catch (SAXException e) {
            throw new IOException(e);
        }
    }
}
