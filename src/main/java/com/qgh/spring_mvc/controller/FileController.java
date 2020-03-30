package com.qgh.spring_mvc.controller;

import com.qgh.spring_mvc.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 秦光泓
 * @title:文件上传
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/3/1810:45
 */
@RestController
public class FileController {
    @RequestMapping(value = "/uploadFolder", method = RequestMethod.POST)
    public String uploadFolder(MultipartFile[] folder) {
       // FileUtil.saveMultiFile("D:/upload/", folder);
        FileUtil.saveMultiFile1(folder);
        return "ok";
    }

}
