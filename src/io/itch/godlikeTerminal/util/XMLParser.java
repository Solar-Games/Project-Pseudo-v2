package io.itch.godlikeTerminal.util;

import java.io.*;
import java.util.Properties;

public class XMLParser {

    boolean fileParsed;
    File file;
    String path;

    OutputStream write;
    InputStream read;

    Properties properties = new Properties();

    public XMLParser(File file) {

        this.fileParsed = false;

        this.file = file;
        this.path = file.getPath();

        try {

            if (this.file.exists()) {

                boolean delete = this.file.delete();

            } else {

                boolean newFile = this.file.createNewFile();

            }

            write = new FileOutputStream(this.file);
            read = new FileInputStream(this.file);

        } catch (IOException el) {

            el.printStackTrace();

        }

    }

    public void parseXML(String entry, int value) throws IOException {

        properties.setProperty(entry, Integer.toString(value));

    }

    public void endParsing(String comment) throws IOException {

        properties.storeToXML(write, comment);

        fileParsed = true;

    }

    public String loadXML(String entry) throws IOException, FileNotFoundException {

        properties.loadFromXML(read);

        return properties.getProperty(entry);


    }

    public boolean fileDoneParsing() {

        return fileParsed;

    }

}
