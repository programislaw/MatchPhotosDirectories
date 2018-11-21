/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.matchphotosdirectories;

import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Bak
 */
public class ComparationReporter extends DirectoryComparator {
    
    final static Logger logger = Logger.getLogger(DirectoryLoader.class);
    
    protected static void printResult(List<DriveItem> drives) {
        for (DriveItem drive : drives) {
            for (DirectoryItem directoryItem : drive.getDirectories()) {
                if (directoryItem.hasDuplicate()) {
                    logger.info("Dir: " + directoryItem.getPath() + " has: " + directoryItem.getNumberOfDuplicatedDirectories() 
                            + " duplicated Directories [ " + directoryItem.getDuplicatedDirectoryDetails() + " ]");
                }
            }
        }
    }
    
}
