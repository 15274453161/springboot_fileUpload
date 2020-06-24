package com.qgh.spring_mvc.moduels.dao;

import com.qgh.spring_mvc.moduels.bean.Task;
import org.apache.ibatis.annotations.Param;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:40
 */
public interface TaskDao {
    Task getTask(@Param("id") int id);
}
