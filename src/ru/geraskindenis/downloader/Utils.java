package ru.geraskindenis.downloader;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String getFileName(String url) {
        String[] strings = url.split("/");
        return strings[strings.length - 1];
    }

    public static void downloadFile(String url, String folder) {

        if (url.isEmpty()) {
            return;
        }

        String fileName = folder + getFileName(url);

        try (BufferedInputStream bis = new BufferedInputStream(new URL(url).openStream()); FileOutputStream fos = new FileOutputStream(fileName)) {
            byte[] bytes = bis.readAllBytes();
            fos.write(bytes);
            fos.flush();
            System.out.println(url + " --> Download completed!");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStorageFolder() {
        Date date = new Date(System.currentTimeMillis());
        String folderName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
        String separator = System.getProperty("file.separator");
        String userDir = System.getProperty("user.dir");
        return userDir + separator + folderName + separator;
    }
}
