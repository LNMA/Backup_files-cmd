package com.louay.project;

import java.io.*;
import java.util.*;

class FileOperation extends MyFile {

    public FileOperation() {
    }

    public void splitToVolumes(String src, String trgt, String nameWithoutDot , int part , long partSize) throws IOException{
        int byteRead;
        byte [] buf;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        long count;
        try{
            System.out.print("\n>>please wait until the process is done....\n");
            in =new BufferedInputStream(new FileInputStream(src),2048);
            buf = new byte[1];
            for (int i = 1;i<=part;i++) {
                out =new BufferedOutputStream(new FileOutputStream(new File(trgt,nameWithoutDot+"-part"+String.valueOf(i)+".dat")),2048);
                count = 0;
                while ( true ) {
                    count ++;
                    if (count == partSize){
                        break;
                    }
                    byteRead = in.read(buf);
                    out.write(buf,0,byteRead);
                }
                out.close();
            }
            System.out.println("\ncomplete...");
            out.close();
            in.close();
        }
        catch (IOException e){
            System.out.print("\n>>"+e.getMessage());
        }
        finally {
            out.close();
            in.close();
        }
    }

    public void aggregationFiles(String src,String trgt,String parent,String nameWithoutDash,String extension ,int part ) throws IOException {
        int byteRead;
        byte [] buf;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try{
            System.out.print("\n>>please wait until the process is done....\n");
            new File(trgt+"\\"+nameWithoutDash).mkdirs();
            out =new BufferedOutputStream(new FileOutputStream(new File(trgt+"\\"+nameWithoutDash,nameWithoutDash+extension)),4096);
            buf = new byte[1024];
            for (int i = 1;i<=part;i++) {
                in =new BufferedInputStream(new FileInputStream(new File(parent,nameWithoutDash+"-part"+String.valueOf(i)+".dat")),4096);
                while ( (byteRead = in.read(buf)) > -1 ) {
                    out.write(buf,0,byteRead);
                }
                in.close();
            }
            System.out.println("\ncomplete...");
            out.close();
            in.close();
        }
        catch (IOException e){
            System.out.print("\n>>"+e.getMessage());
        }
        finally {
            out.close();
            in.close();
        }
    }


}
