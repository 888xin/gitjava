package trash.util;

import java.sql.*;

/**
 * Created by lhx on 15-1-5 下午4:59
 *
 * @project anti
 * @package com.lifeix.anti.common.util
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public final class JdbcUtils {
    // 参数定义
    private static String url = "jdbc:mysql://localhost:3306/li"; // 数据库地址
    private static String username = "root"; // 数据库用户名
    private static String password = "465864"; // 数据库密码

    private JdbcUtils() {

    }

    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动加载出错!");
        }
    }

    // 获得连接
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(url, username, password);
//    }

    public static Connection getConnection(String url, String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // 释放连接
    public static void free(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close(); // 关闭结果集
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close(); // 关闭Statement
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close(); // 关闭连接
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
