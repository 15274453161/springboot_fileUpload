package com.qgh.spring_mvc.common.util;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    /**
     * <p>
     * Description:将内容写入文件，重写IOUtils.writeLines方法，最后一行不写入空行
     * </p>
     *
     * @param file
     * @param lines
     * @param lineEnding
     * @param encoding
     * @throws IOException
     */
    private static void writeFile(File file, Collection<?> lines, String lineEnding, String encoding)
            throws IOException {
        OutputStream output = FileUtils.openOutputStream(file);
        if (encoding == null) {
            IOUtils.writeLines(lines, lineEnding, output);
        } else {
            if (lines == null) {
                return;
            }
            if (lineEnding == null) {
                lineEnding = IOUtils.LINE_SEPARATOR;
            }
            int i = 0;
            for (Object line : lines) {
                i++;
                if (line != null) {
                    output.write(line.toString().getBytes(encoding));
                }
                if (i != lines.size()) {
                    output.write(lineEnding.getBytes(encoding));
                }
            }
        }
    }

    public static void main(String[] args) {
        List list=new ArrayList();
        list.add("000000000004|000000000000200016|");
        list.add("20150814210388888892|6228480120650477918|000000000000100003|卢辛|01|511129199606114812|保险费|测试附言信息|");
        list.add("6D82698A230D3817DB17C56BC6EBA517");
        try {
            writeFile(new File("D:\\0428.txt"),list,null,"GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
