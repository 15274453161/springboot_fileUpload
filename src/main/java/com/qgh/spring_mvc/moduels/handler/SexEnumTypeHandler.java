package com.qgh.spring_mvc.moduels.handler;

import com.qgh.spring_mvc.moduels.enums.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 秦光泓
 * @title:
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/2311:32
 */
public class SexEnumTypeHandler implements TypeHandler<SexEnum> {
    /***
    * 查询sql前设置参数
     * @param ps
     * @param i
     * @param sexEnum
     * @param jdbcType
    * @return void
    * @date 2020/6/23  11:33
    */

    @Override
    public void setParameter(PreparedStatement ps, int index, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        ps.setInt(index,sexEnum.getId());
    }
    /***
    * 获取数据库到POJO的映射
     * @param rs
     * @param name pojo中的name
    * @return com.qgh.spring_mvc.moduels.enums.SexEnum
    * @date 2020/6/23  11:35
    */

    @Override
    public SexEnum getResult(ResultSet rs, String name) throws SQLException {
       int id=  rs.getInt(name);
        return SexEnum.getSex(id);
    }

    @Override
    public SexEnum getResult(ResultSet rs, int index) throws SQLException {

        int id=  rs.getInt(index);
        return SexEnum.getSex(id);
    }

    @Override
    public SexEnum getResult(CallableStatement cs, int index) throws SQLException {
        int id=  cs.getInt(index);
        return SexEnum.getSex(id);
    }
}
