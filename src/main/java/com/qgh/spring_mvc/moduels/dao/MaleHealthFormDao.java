package com.qgh.spring_mvc.moduels.dao;

import com.qgh.spring_mvc.moduels.bean.MaleHealthForm;
import org.apache.ibatis.annotations.Param;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:47
 */
public interface MaleHealthFormDao {
    MaleHealthForm getMaleHealthForm(@Param("id") long id);
}
