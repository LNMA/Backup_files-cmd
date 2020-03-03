package com.louay.project;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class MyZipFile extends FileOperation {
    ArrayList<String> src = new ArrayList<>();

    public void fillList(){
        int time = new CommandLineInterface().inputNumOfFiles();
        for (int i =0;i<time;i++) {
            System.out.print("\n>>file number "+(i+1));
            src.add(new CommandLineInterface().inputAbsolutePath());
        }
    }

    public void checkFiles(){

        for (int i = 0;i<src.size();i++){
            File check = new File(src.get(i));
            if (check.exists() && !check.canRead()) {
                System.out.print("\n>>file "+(i+1));
                System.out.print("\n>>File exists and it is read only, making it writable");
                check.setReadable(true);
                System.out.print("\n>>done");
            }if (!check.exists()){
                System.out.print("\n>>the file is not exist");
            }
        }
    }

    public void compressZip(String trgt) throws IOException {
        ZipOutputStream out= null;
        BufferedInputStream in = null;
        ZipEntry zipEntry = null;
        int byteRead;
        byte[] buf;
        String name;
        try {
            name = new CommandLineInterface().inputZipFileName();
            new File(trgt+"\\"+name).mkdirs();
            out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(new File(trgt + "\\" + name, name + ".zip")), 4096));
            for (int k = 0;k<src.size();k++){
                setSourceFileAbsolutePath(src.get(k));
                System.out.print("\n--> source file name is: "+ getNameWithoutDot()+" <--");
                System.out.print("\n--> source file size is: "+getSize()/(1024*1024)+" Mb, "+getSize()+" byte. <--");
                buf = new byte[1024];
                in = new BufferedInputStream(new FileInputStream(getSourceFileAbsolutePath()), 4096);
                zipEntry = new ZipEntry(getFileName());
                out.putNextEntry(zipEntry);
                while ((byteRead = in.read(buf)) > -1) {
                    out.write(buf, 0, byteRead);
                }
                out.closeEntry();
                in.close();
            }
            System.out.println("\ncomplete...");
            out.close();
            in.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            out.close();
            in.close();
        }
    }

    public void extractZip(String src,String trgt) throws IOException {
        BufferedOutputStream out= null;
        ZipInputStream in = null;
        ZipEntry zipEntry = null;
        int byteRead;
        byte[] buf;
        String name;
        try {
            setSourceFileAbsolutePath(src);
            in =new ZipInputStream( new BufferedInputStream(new FileInputStream(getSourceFileAbsolutePath()), 4096));
            zipEntry = in.getNextEntry();
            System.out.print("\n--> Zip file name is: "+ getFileName()+" <--");
            System.out.print("\n--> Zip file size is: "+getSize()/(1024*1024)+" Mb, "+getSize()+" byte. <--");
            buf = new byte[1024];
            while (zipEntry != null){
                System.out.print("\n--> source file name is: "+ zipEntry.getName()+" <--");
                out = new BufferedOutputStream(new FileOutputStream(new File(trgt, zipEntry.getName())), 4096);
                while ((byteRead = in.read(buf)) > -1) {
                out.write(buf, 0, byteRead);
                }
                out.close();
                zipEntry = in.getNextEntry();
            }
            System.out.println("\ncomplete...");
            out.close();
            in.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        finally {
            out.close();
            in.close();
        }
    }
}