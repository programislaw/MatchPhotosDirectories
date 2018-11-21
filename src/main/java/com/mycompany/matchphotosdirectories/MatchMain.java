/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.matchphotosdirectories;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.filechooser.FileSystemView;
import org.apache.log4j.Logger;

/**
 *
 * @author Bak
 */
public class MatchMain {

    final static Logger logger = Logger.getLogger(MatchMain.class);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<DriveItem> drives = DriveItem.loadDriveNames();

//        List<DriveItem> drives = new ArrayList<>();
//        final String directoryWindows ="C://CompTest";
//        DriveItem mockDrive = new DriveItem(directoryWindows);
//        drives.add(mockDrive);
        
        long startTime = System.currentTimeMillis();
        logger.info("Start");
        DirectoryLoader loader = new DirectoryLoader(Arrays.asList("jpg", "mov", "mp4", "avi", "png"));
        loader.load(drives);
        
        DirectoryComparator.compareDirectory(drives);
        
        ComparationReporter comparationReporter = new ComparationReporter();
        comparationReporter.printResult(drives);
        long estimatedTime = System.currentTimeMillis() - startTime;
        Date date = new Date(estimatedTime);
        DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
        String dateFormatted = formatter.format(date);
        logger.info("Finish time:" + dateFormatted);
    }





}
