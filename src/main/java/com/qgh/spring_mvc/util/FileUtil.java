package com.qgh.spring_mvc.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 秦光泓
 * @title:文件上传工具类
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/3/1810:46
 */
public class FileUtil {
    /***
    * 在basePath下保存上传文件
     * @param basePath
     * @param files
    * @return void
    * @date 2020/3/18  10:47
    */

    public static void saveMultiFile(String basePath, MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return;
        }
        if (basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }
        for (MultipartFile file : files) {
            String filePath = basePath + "/" + file.getOriginalFilename();
            makeDir(filePath);
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
    }
    /***
    * 在配置文件中配置了上传的路径
     * @param files
    * @return void
    * @date 2020/3/18  11:28
    */

    public static void saveMultiFile1(MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return;
        }
        for (MultipartFile file : files) {
            String filePath =  file.getOriginalFilename();
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 确保目录存在，不存在则创建
     * @param filePath
     */
    private static void makeDir(String filePath) {
        if (filePath.lastIndexOf('/') > 0) {
            String dirPath = filePath.substring(0, filePath.lastIndexOf('/'));
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }


}
