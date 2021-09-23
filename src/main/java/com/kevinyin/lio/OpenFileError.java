package com.kevinyin.lio;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Kevin_Yin on 2017/7/3.
 */
@Slf4j
public class OpenFileError {

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
                log.error("打开文件异常",e);
            }finally {
                i++;
            }
        }
    }
}
