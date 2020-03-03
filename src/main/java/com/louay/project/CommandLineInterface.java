package com.louay.project;

import com.louay.project.MyZipFile;

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class CommandLineInterface extends MyZipFile {
    private Scanner input = new Scanner(System.in);

    public CommandLineInterface() {
    }

    public String inputAbsolutePath(){
        System.out.print("\n>>please input source file path [absolute path]: \nYou:\\>");
        String src = input.next();
        if (input.hasNextLine())
            src += input.nextLine();
        return src;
    }

    public String inputParentPath(){
        System.out.print("\n>>please input Target location path [Parent path]: \nYou:\\>");
        String trgt = input.next();
        if (input.hasNextLine())
            trgt += input.nextLine();
        return trgt;
    }

    public String inputFirstPartAbsolutePath(){
        System.out.print("\n>>please input First Part path [absolute path]: \nYou:\\>");
        String src = input.next();
        if (input.hasNextLine())
            src += input.nextLine();
        return src;
    }

    public String inputExtension(){
        System.out.print("\n>>What is the original file extension with DOT\".\": \nYou:\\>");
        String ext = input.next();
        if (input.hasNextLine())
            ext += input.nextLine();
        return ext;
    }

    public String inputZipFileName(){
        System.out.print("\n>>please set file new name: \nYou:\\>");
        String name = input.next();
        if (input.hasNextLine())
            name += input.nextLine();
        return name;
    }

    public int inputPart(){
        int part;
        try {
            System.out.print("\n>>enter numbers of part: \nYou:\\>");
            part = input.nextInt();
            if (part <0)
                throw new IllegalArgumentException("\nnumber of part cannot be negative.\n");
        }
        catch (IllegalArgumentException e){
            System.out.print(e.getMessage());
            part = inputPart();
        }
        catch (InputMismatchException e){
            System.out.print("\n>>"+e.getMessage()+"\n");
            part = inputPart();
        }
        return part;
    }

    public int inputNumOfFiles(){
        int files;
        try {
            System.out.print("\n>>How many file you want to compress it: \nYou:\\>");
            files = input.nextInt();
            if (files <0)
                throw new IllegalArgumentException("\nnumber of part cannot be negative.\n");
        }
        catch (IllegalArgumentException e){
            System.out.print(e.getMessage());
            files = inputNumOfFiles();
        }
        catch (InputMismatchException e){
            System.out.print("\n>>"+e.getMessage()+"\n");
            files = inputNumOfFiles();
        }
        return files;
    }

    public void printSplitDetail(){
        System.out.print("\n--> source file name is: "+ getFileName()+" <--");
        System.out.print("\n--> source file size is: "+getSize()/(1024*1024)+" Mb, "+getSize()+" byte. <--");
        System.out.print("\n--> part size "+getPartSize()/(1024*1024)+" Mb, "+getPartSize()+" byte. <--");
    }

    public void printAggregationDetail(){
        System.out.print("\n--> source file name is: "+ getNameWithoutDash()+" <--");
        System.out.print("\n--> source file size is: "+getTotalPartSize()/(1024*1024)+" Mb, "+getTotalPartSize()+" byte. <--");
        System.out.print("\n--> part size "+getSize()/(1024*1024)+" Mb, "+getSize()+" byte. <--");
    }


    public void signature(){
        System.out.println(" ______\n|      |\t\t\t\t\t\t\t          \t\t\t\t     \t\t\t  \n|      |\t\t\t\t _______________\t\t____\t     ____  \t\t\t __________ \t\t    ____\t\t ____\n|      |\t\t\t\t/\t\t\t\t\\      |    |       |\t |\t\t    /    __    \\\t\t   |    |       |    |\n|      |    \t\t   |     _______\t |     |    |       |\t |    \t   /    /  \\    \\\t\t   |    |       |    |\n|      |\t\t       |    |       |\t |     |    |       |\t |     \t  /    /    \\    \\\t\t   |    |       |    |\n|      |\t\t       |    |\t    |\t |     |    |\t    |\t |\t\t /    /______\\    \\\t\t   |    |       |    |\n|      |________       |    |\t    |\t |     |    |\t    |\t | \t\t/    __________    \\\t   |    |       |    |\n|               |      |    |_______|\t |     |    |_______|\t |     /    /          \\    \\ \t   |    |_______|    |\n|               |      |   \t\t\t\t |     |   \t\t\t\t |    /    /  \t\t    \\    \\     |                 |\n|_______________| \t\t\\_______________/\t\t\\_______________/ \t /____/\t\t\t     \\____\\    \\___________      |\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t|    |\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t        |    |\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t        |    |\n ______________________________________________________________________________________________________________/     |\n|\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     |\n|___________________________________________________________________________________________________________________/");
    }

    public void mainScreen() {
        System.out.print("\n{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}\n[1]. Split file to volumes .dat files. \n[2]. Aggregation .dat Files to one volume. \n[3]. Compress .dat files to one file. \n[4]. extract .zip files. \n[5]. exit. \n{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}\n{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{}\nYou:\\>");
    }

    public int inputChoice() throws InputMismatchException {
        int choice =0;
        try{
            choice = input.nextInt();
            if (!(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 ))
                throw new IllegalArgumentException("\n######################################### \n You are enter invalid value...try again \n#########################################\n");
        }
        catch (IllegalArgumentException e){
            System.out.print(e.getMessage());
            mainScreen();
            choice = inputChoice();
        }
        catch (InputMismatchException e){
            System.out.print("\n>> you can just input integer numbers only\n");
            input.nextLine();
            mainScreen();
            choice = inputChoice();
        }
        return choice;
    }


    public String inputYesNoChoice() {
        String yesNo = null;
        try {
            System.out.print("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n| Would you like to perform a new operation.  | \n|                                             | \n| <Y>es, I would        <N>o, thanks          |\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= \nYou:\\>");
            yesNo = input.next();
            if (input.hasNextLine()) {
                yesNo += input.nextLine();
            }
            if (!(yesNo.equalsIgnoreCase("n") || yesNo.equalsIgnoreCase("y"))) {
                throw new IllegalArgumentException("\n######################################### \n You are enter invalid value...try again \n#########################################\n");
            }
        }
        catch (IllegalArgumentException e){
            System.out.print(e.getMessage());
            yesNo =  inputYesNoChoice();
        }
        return yesNo;
    }

    public void triggerMethod(){
        String yesNo ="y";
        int choice = 0;
        signature();
        while(yesNo.equalsIgnoreCase("y")){
            mainScreen();
            choice = inputChoice();
            if (choice == 1){
                setSourceFileAbsolutePath(inputAbsolutePath());
                makingFileReadable();
                setTargetLocation(inputParentPath());
                makingFileWritable();
                setPart(inputPart());
                printSplitDetail();
                try {
                    splitToVolumes(getSourceFileAbsolutePath(),getTargetLocation(),getNameWithoutDot(),getPart(),getPartSize());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (choice == 2){
                setSourceFileAbsolutePath(inputFirstPartAbsolutePath());
                makingFileReadable();
                setTargetLocation(inputParentPath());
                makingFileWritable();
                setPart(inputPart());
                setExtension(inputExtension());
                printAggregationDetail();
                try {
                    aggregationFiles(getSourceFileAbsolutePath(),getTargetLocation(),getParent(),getNameWithoutDash(),getExtension(),getPart());
                }
                catch (IOException e){
                e.printStackTrace();
                }
            }
            if (choice ==3){
                fillList();
                checkFiles();
                try {
                    compressZip(inputParentPath());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (choice == 4){
                try {
                    extractZip(inputAbsolutePath(),inputParentPath());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (choice == 5){
                System.out.print("\nGood bye...\n");
                signature();
                System.exit(0);
            }
            yesNo = inputYesNoChoice();
            if (yesNo.equalsIgnoreCase("n")) {
                System.out.print("\nGood bye...\n");
                signature();
                break;
            }
        }

    }

}
