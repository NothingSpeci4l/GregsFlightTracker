package com.gregwll.flighttracker.files;

import java.io.*;

public class FilesUtils {
    public static void createFile(File file) throws IOException {
        if(!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
    }

    public static void save(File file, String text) {
        final FileWriter fw;

        try{
            createFile(file);

            fw = new FileWriter(file);
            fw.write(text);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadContent(File file) {
        if(file.exists()) {
            try{
                final BufferedReader reader = new BufferedReader(new FileReader(file));
                final StringBuilder text = new StringBuilder();

                String line;

                while((line = reader.readLine()) != null) {
                    text.append(line);

                }
                reader.close();
                return text.toString();
            } catch (IOException e) {
                e.printStackTrace();;
            }
        }
        return "";

    }
}
