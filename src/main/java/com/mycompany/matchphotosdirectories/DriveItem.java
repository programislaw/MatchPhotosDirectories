package com.mycompany.matchphotosdirectories;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileSystemView;

public class DriveItem {

    String driveName;
    List<DirectoryItem> directories = new ArrayList<>();

    public DriveItem(String driveName) {
        this.driveName = driveName;
    }
    
    public List<DirectoryItem> getDirectories() {
        return directories;
    }
    
    static List<DriveItem> loadDriveNames() {
        List<DriveItem> driveItems = new ArrayList<>();
        File[] paths;
        FileSystemView fsv = FileSystemView.getFileSystemView();
        paths = File.listRoots();
        for(File path:paths)
        {
            driveItems.add(new DriveItem(path.getPath()));
        }
        return driveItems;
    }
}
