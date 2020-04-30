package com.qgh.spring_mvc.common.controller;

import com.qgh.spring_mvc.common.util.ExcelUtil;
import com.qgh.spring_mvc.common.util.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    /**
     * 发送响应流方法
     *
     * @param request  :
     * @param response :
     * @param fileName : 下载文件名
     * @return : void
     * @Authoor :dongxiaoyong
     * @Date :2020/2/29 19:48
     */
    private void setResponseHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        try {
            fileName = ExcelUtil.encodeChineseDownloadFileName(request, fileName);
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception e) {
          //  logger.error("设置响应头异常", e);
        }
    }

}
