package ru.geraskindenis.downloader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Downloader {
    public static void main(String[] args) throws IOException {

        System.out.println("This program download files from Internet.");

        File linkList;
        if (args.length == 0) {
            linkList = new File("links.txt");
        } else {
            linkList = new File(args[0]);
        }

        String storageFolder = Utils.getStorageFolder();
        Files.createDirectory(Path.of(storageFolder));

        try (BufferedReader br = new BufferedReader(new FileReader(linkList))) {
            while (br.ready()) {
                String url = br.readLine();
                if (url.charAt(0) == '#') {
                    System.out.println("Skip:" + url);
                    continue;
                }
                Utils.downloadFile(url, storageFolder);
            }
            System.out.println("Download of all files completed!");
        } catch (FileNotFoundException e) {
            System.out.println("You can put a 'links.txt' file next to the program and run it, ");
            System.out.println("or you can run the program with the parameter as the path to the link list file.");
            System.out.println("Add '#' at the beginning of the line to skip loading.");
            throw new RuntimeException(String.format("File '%s' not found!%n", linkList.getAbsolutePath()));
        }
    }
}
