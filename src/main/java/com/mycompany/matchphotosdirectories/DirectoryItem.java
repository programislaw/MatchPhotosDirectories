package com.mycompany.matchphotosdirectories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DirectoryItem {
    
    String path;
    Map<String, Integer> dirEqualsMoreThen50Percent = new HashMap<String, Integer>();
    List<Long> sizeOfFiles = new ArrayList<>();

    public DirectoryItem(String path) {
        this.path = path;
    }

    public List<Long> getSizeOfFiles() {
        return sizeOfFiles;
    }

    public String getPath() {
        return path;
    }

    void setDirectoryItemBase(String equalsDirName, int equalInSizeItems) {
        if (sizeOfFiles.size() > 0) {
            dirEqualsMoreThen50Percent.put(equalsDirName, equalInSizeItems);
        }
    }

    private int getCorelationInPercent(int equalInSizeItems) {
        return equalInSizeItems*100/sizeOfFiles.size();
    }

    public String getDirEqualsMoreThen50Percent() {
        return dirEqualsMoreThen50Percent.toString();
    }

    public boolean hasDuplicate() {
        for (Map.Entry<String, Integer> item : dirEqualsMoreThen50Percent.entrySet()) {
            if (item.getValue() > 0) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfDuplicatedDirectories() {
        return dirEqualsMoreThen50Percent.size();
    }

    public String getDuplicatedDirectoryDetails() {
        String result = "";
        for (Map.Entry<String, Integer> item : dirEqualsMoreThen50Percent.entrySet()) {
            result = result + item.getKey() + " in " + getCorelationInPercent(item.getValue().intValue()) + "% ";
        }
        return result;
    }
    
    
}
