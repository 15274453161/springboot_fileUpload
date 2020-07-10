package com.qgh.spring_mvc.common.test.json;

import com.alibaba.fastjson.JSONArray;
import com.qgh.spring_mvc.common.util.JsonHelper;
import com.qgh.spring_mvc.moduels.java8.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 秦光泓
 * @title:测试json和JavaBean的转换
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/7/1013:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestJson {

    /***
    * 测试JavaBean转换为JsonString
    * @return void
    * @date 2020/7/10  13:57
    */

   /* @Test
    public void javaBeanToJson() {
        Student student = new Student("xiaoming",80.2);
        *//**没有格式化*//*
        String s;
       // String s = JsonHelper.javaBeanToJson(student);
        *//**格式化*//*
        s=JsonHelper.javaBeanToJsonPretty(student);
        System.out.println(s);
    }*/
    /***
    * list转换为JsonString
     * 转换结果：
     [
     {
     "name": "xiaoming",
     "score": 80.2
     },
     {
     "name": "xiaohong",
     "score": 89
     }
     ]
     * @return void
    * @date 2020/7/10  14:23
    */

    /*@Test
    public void listToJson() {
        Student student1 = new Student("xiaoming",80.2);
        Student student2= new Student("xiaohong",89d);
        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        String s;
        *//**格式化*//*
        s=JsonHelper.javaBeanToJsonPretty(studentList);
        System.out.println(s);
    }*/
    /***
     * 测试JsonString转换为JavaBean
     * @return void
     * @date 2020/7/10  13:57
     */

   /* @Test
    public void string2Obj() {
         String s ="{\n" +
                 "  \"name\" : \"xiaoming\",\n" +
                 "  \"score\" : 80.2\n" +
                 "}";
        Student student = JsonHelper.jsonToJavaBean(s, Student.class);
        System.out.println(student);
    }*/
   /***
   * jsonString转换为Collection
    *转换结果
    [{"name":"xiaoming", "score":"80.2"}, {"name":"xiaobai", "score":"80.3"}]
   * @return void
   * @date 2020/7/10  14:24
   */

   /* @Test
    public void string2Collection() {
        Student xiaoming = new Student("xiaoming", 80.2);
        Student xiaobai = new Student( "xiaobai", 80.3);
        List<Student> studentList = new ArrayList<>();
        studentList.add(xiaoming);
        studentList.add(xiaobai);
        String studentListStr = JsonHelper.javaBeanToJsonPretty(studentList);
        //List<Student> studentListObj = JsonTools.string2ObjRef(studentListStr, new TypeReference<List<Student>>(){});
        // 依次传入集合以及集合中对象类型的class
        List<Student> studentListObj = JsonHelper.jsonStringToCollection(studentListStr, List.class, Student.class);
        System.out.println(studentListObj.toString());
    }*/
    /***
    *
    * @return void
    * @date 2020/7/10  14:33
    */

    /*@Test
    public void getListByJSONArray() {
        //定义一个数组
        List<Student> students = new ArrayList<>();
        Student xiaoming = new Student( "xiaoming",80.2);
        Student xiaohong = new Student( "xiaohong",80.2);
        students.add(xiaoming);
        students.add(xiaohong);
        //将List转换为jsonArray
        JSONArray student = JsonHelper.getJSONArrayByList(students);
        System.out.println(student);
        //将jsonArray转为List
        List<Student> studentList = JsonHelper.getListByJSONArray(Student.class, student.toJSONString());
        System.out.println(studentList);
    }*/

}
