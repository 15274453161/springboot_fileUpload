package com.qgh.spring_mvc.moduels.dao;

import com.qgh.spring_mvc.moduels.bean.FemaleHealthForm;
import org.apache.ibatis.annotations.Param;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2310:49
 */
public interface FemaleHealthFormDao {
    FemaleHealthForm getFemaleHealthForm(@Param("id") long id);
}
