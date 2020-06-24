package com.qgh.spring_mvc.moduels.dao;

import com.qgh.spring_mvc.moduels.bean.WorkCard;
import org.apache.ibatis.annotations.Param;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:42
 */
public interface WorkCardDao {
   WorkCard getWorkCardByEmpId(@Param("id") long id);
}
