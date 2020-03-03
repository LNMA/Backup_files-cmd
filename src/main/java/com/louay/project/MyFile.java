package com.louay.project;

import java.io.*;
import java.util.*;

class MyFile {
    private String sourceFileAbsolutePath;
    private String targetLocation;
    private String nameWithoutDot;
    private String nameWithoutDash;
    private String parent;
    private String fileName;
    private String extension;
    private int part;
    private long partSize;
    private long totalPartSize;
    private long size;
    private File fileSrc;
    private File fileTrgt;


    public MyFile() {
    }

    private void setFileSrc(String fileSrc) {
        this.fileSrc = new File(fileSrc);
    }

    private void setFileTrgt(String fileTrgt) {
        this.fileTrgt = new File(fileTrgt);
    }

    public String getSourceFileAbsolutePath() {
        return sourceFileAbsolutePath;
    }

    public void setSourceFileAbsolutePath(String sourceFileAbsolutePath) {
        fileSrc = new File(sourceFileAbsolutePath);
        this.sourceFileAbsolutePath = sourceFileAbsolutePath;
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(String targetLocation) {
        setFileTrgt(targetLocation);
        this.targetLocation = targetLocation;
    }

    public String getNameWithoutDot() {
        setNameWithoutDot();
        return nameWithoutDot;
    }

    private void setNameWithoutDot() {
        String child = getFileName();
        int len = child.length();
        int lastLen = child.indexOf('.',len-5);
        String name = child.substring(0,lastLen);
        this.nameWithoutDot = name;
    }

    public String getNameWithoutDash() {
        setNameWithoutDash();
        return nameWithoutDash;
    }

    private void setNameWithoutDash() {
        String child = getFileName();
        int len = child.length();
        int lastLen = child.indexOf('-',len-11);
        String name = child.substring(0,lastLen);
        this.nameWithoutDash = name;
    }

    public String getParent() {
        setParent();
        return parent;
    }

    private void setParent() {
        if (fileSrc.exists())
            this.parent = fileSrc.getParent();
        else
            System.out.print("\n>>the file is not exist");
    }

    public String getFileName() {
        setFileName();
        return fileName;
    }

    private void setFileName() {
        if (fileSrc.exists())
            this.fileName = fileSrc.getName();
        else
            System.out.print("\n>>the file is not exist");
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public long getPartSize() {
        setPartSize();
        return partSize;
    }

    private void setPartSize() {
        this.partSize = getSize()/getPart();
    }

    public long getTotalPartSize() {
        setTotalPartSize();
        return totalPartSize;
    }

    private void setTotalPartSize() {
        this.totalPartSize = getSize()*getPart();
    }

    public long getSize() {
        setSize();
        return size;
    }

    private void setSize() {
        if (fileSrc.exists()){
            this.size = fileSrc.length();
        }
        else{
            System.out.print("\n>>the file is not exist");
            this.size = 0;
        }
    }

    public  void  makingFileWritable(){
        if (fileTrgt.exists() && !fileTrgt.canWrite()) {
            System.out.print("\n>>File exists and it is read only, making it writable");
            fileTrgt.setWritable(true);
            System.out.print("\n>>done");
        }
    }

    public  void  makingFileReadable(){
        if (fileSrc.exists() && !fileSrc.canRead()) {
            System.out.print("\n>>File exists and it is read only, making it writable");
            fileSrc.setReadable(true);
            System.out.print("\n>>done");
        }
    }
}
