/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.matchphotosdirectories;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Bak
 */
public class DirectoryLoader {
    final static Logger logger = Logger.getLogger(DirectoryLoader.class);
    List<String> fileExtensions;

    public DirectoryLoader(List<String> fileExtensions) {
        this.fileExtensions = fileExtensions;
    }
    
    public void load(List<DriveItem> drives) {
        for (DriveItem drive : drives) {
            DirectoryItem newDirectoryItem = new DirectoryItem(drive.driveName);
            drive.getDirectories().add(newDirectoryItem);
            listFilesAndFilesSubDirectories(drive, newDirectoryItem, newDirectoryItem.getPath());
        }
        System.out.println("Removing empty directories ...");
        removeEmptyDirectories(drives);
    }
    protected void listFilesAndFilesSubDirectories(DriveItem drive, DirectoryItem directoryItem, String directoryName) {
        try {
            File directory = new File(directoryName);
            File[] fList = directory.listFiles();
            for (File file : fList) {
                if (file.isFile()) {
                    if (isRightExtension(getFileExtension(file.getAbsolutePath()))) {
                        directoryItem.getSizeOfFiles().add(file.length());
//                        System.out.println(file.getAbsolutePath() + " Size: " + file.length());
                    }
                } else if (file.isDirectory()) {
                    if (!file.getAbsolutePath().contains("$Recycle.Bin")) {
//                        System.out.println(file.getAbsolutePath());
                        DirectoryItem newDirectoryItem = new DirectoryItem(file.getAbsolutePath());
                        drive.getDirectories().add(newDirectoryItem);
                        listFilesAndFilesSubDirectories(drive, newDirectoryItem, file.getAbsolutePath());
                    }
                }
            }
        } catch (Exception e) {
            //logger.warn(e);
        }
    }
    
    private String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    private boolean isRightExtension(String fileExtension) {
        boolean isRight = false; 
        for (String fileExt : fileExtensions) {
            if (fileExtension.equalsIgnoreCase(fileExt)) {
                isRight = isRight || true;
            }
        }
        return isRight;
    }

    private void removeEmptyDirectories(List<DriveItem> drives) {
        for (DriveItem drive : drives) {
            for (Iterator<DirectoryItem> iterator = drive.directories.iterator(); iterator.hasNext(); ) {
                DirectoryItem directoryItem = iterator.next();
                if (directoryItem.sizeOfFiles.isEmpty()) {
                    iterator.remove();
                }
            }
        }
    }



}
