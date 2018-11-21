/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.matchphotosdirectories;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Bak
 */
public class DirectoryComparator {
    
    protected static void compareDirectory(List<DriveItem> drives) {
        for (DriveItem driveBase : drives) {
            for (DirectoryItem directoryItemBase : driveBase.directories) {
                for (DriveItem drive : drives) {
                    for (DirectoryItem directoryItem : drive.directories) {
                        if (!directoryItemBase.getPath().equals(directoryItem.getPath())) {
                            int equalInSizeItems = compareDirectory(directoryItemBase, directoryItem);
                            if (equalInSizeItems > 0) {
                                directoryItemBase.setDirectoryItemBase(directoryItem.getPath(), equalInSizeItems);
                            }
                        }
                    }
                }
            }
        }
    }

    protected static int compareDirectory(DirectoryItem directoryItemBase, DirectoryItem directoryItem) {
        int equalInSizeItems = 0;
        for (Long fileBaseSize : directoryItemBase.getSizeOfFiles()) {
            for (Long fileSize : directoryItem.getSizeOfFiles()) {
                if (Objects.equals(fileBaseSize, fileSize)) {
                    equalInSizeItems++;
                }
            }
        }
        return equalInSizeItems;
    }
    
}
