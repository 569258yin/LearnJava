package com.kevinyin.common.io;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by Kevin_Yin on 2017/7/3.
 */
public class TestFile {

    public static void main(String[] args) {
//        ioClose();

//        fileUtils();

        fileSystemUtils();
    }

    public static void ioClose() {
        InputStream in = null;
        try {
            in = new URL("http://commons.apache.org").openStream();
            try {
                System.out.println(IOUtils.toString(in, "UTF-8"));
            } finally {
                IOUtils.closeQuietly(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileUtils() {
        File file = new File("log4j.properties");
        try {
//            List lines = FileUtils.readLines(file, "UTF-8");
//            System.out.println(lines);

            File df = new File("file_1.tmp");
            FileUtils.copyFile(file, df);

//            FileUtils.write();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            FileUtils.forceDelete(df);
            //不抛出任何异常
//            FileUtils.deleteQuietly(df);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fileSystemUtils(){
        try {
            //当前项目所在的磁盘
//            long freeSpace = FileSystemUtils.freeSpaceKb();
            long freeSpace = FileSystemUtils.freeSpaceKb("c:/");


            System.out.println(freeSpace / 1024 / 1024 +"GB");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
