package utils;

import aquality.selenium.core.logging.Logger;

import java.io.*;

public class FileUtils {
    public static String parseJson(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Logger.getInstance().debug("Read and parse json from file to string");
            BufferedReader input = new BufferedReader(new FileReader(path));
            String tempLine = input.readLine();
            while (tempLine!=null){
                stringBuilder.append(tempLine);
                stringBuilder.append(System.lineSeparator());
                tempLine = input.readLine();
            }
        } catch (IOException e) {
            Logger.getInstance().error("Json file can't be read");
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
