package com.geekbang.spring.course10.jdbcStudy;

import java.sql.*;

/**
 * JDBC原生接口 实现数据库增删改查
 */
public class JdbcDemo {
    public static void main(String[] args) {
        try {
            //通过反射加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try
        (
            //通过驱动获得连接
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/study?characterEncoding=UTF-8",
                    "root",
                    "root");
            //获得statement对象
            Statement s = c.createStatement();
        )
        {
            //新增
            for (int i = 0; i < 10; i++) {
                String sql = "insert into student value(null,'"+"学生"+(i+1)+"')";//创建一个user表
                s.execute(sql);
            }

            //删除
            String deleteSql = "delete from student where id = 1";
            s.execute(deleteSql);

            //修改
            String updateSql = "update student set name = '修改后的姓名' where id = 10";
            s.execute(updateSql);

            //查询
            String sql = "select * from student";
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                int i = rs.getInt("id");
                String name = rs.getString("name");
                System.out.printf("%d : %s%n",i,name);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
