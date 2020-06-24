package com.qgh.spring_mvc.moduels.jdbc;

import com.qgh.spring_mvc.moduels.bean.OrgBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 秦光泓
 * @title:JDBC连接数据库
 * @projectName spring_mvc_8
 * @description: TODO
 * @date 2020/6/189:43
 */
public class JdbcExample {
    private static final Logger logger = LoggerFactory.getLogger(JdbcExample.class);
    /***
     * 获得数据库的链接
     * @return java.sql.Connection
     * @date 2020/6/18  9:53
     */

    private Connection getConnection() {
        Connection connection = null;
        try {
            /**加载数据库驱动 需要引入数据的驱动jar包*/
            Class.forName("com.mysql.jdbc.Driver");
            /**具体的数据库服务器地址*/
            String url = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return connection;
    }

    /***
     * 关闭数据库
     * @param rs
     * @param st
     * @param conn
     * @return void
     * @date 2020/6/18  9:53
     */
    private void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            if (st != null && !st.isClosed()) {
                st.close();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public List<OrgBean> getOrg(OrgBean orgBean) {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrgBean> orgBeanList = new ArrayList<>();
        try {
            ps = connection.prepareStatement("SELECT count(*) FROM T_ORG t WHERE t.status='0' AND id = ?");
            /**设置占位符参数*/
            ps.setLong(1, orgBean.getOrgId());
            rs = ps.executeQuery();
            while (rs.next()) {
                orgBean.setName(rs.getString("org_Name"));
                orgBeanList.add(orgBean);
            }
        } catch (SQLException e) {
            logger.error(e.getSQLState());
        }
        return orgBeanList;
    }
}
