package com.kevinyin.lio;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Kevin_Yin on 2017/7/3.
 */
public class OpenFileError {

    private static Logger logger = Logger.getLogger(OpenFileError.class);

    public static void main(String[] args) {
        System.out.println("====================Start===================");
        File filePath = new File("files");
        if (!filePath.exists()){
            filePath.mkdir();
        }
        int i = 0;
        while (true){
            try {
                File file = new File(filePath,i+".txt");
                if (!file.exists()){
                    file.createNewFile();
                }
                FileInputStream fileInputStream = new FileInputStream(file);

            }catch (Exception e){
                logger.error("打开文件异常",e);
            }finally {
                i++;
            }
        }
    }
}
