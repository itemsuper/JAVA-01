package com.geekbang.spring.course10.jdbcStudy;

import java.sql.*;

/**
 * 使用事务，PrepareStatement 方式，批处理方式
 */
public class JdbcDemo2 {
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
            PreparedStatement preparedStatement = null;
        )
        {
            try {
                c.setAutoCommit(false);
                PreparedStatement ps = c.prepareStatement("insert into student value(null,?)");

                for (int i = 0; i < 100; i++) {
                    ps.setString(1, "学生" + (i+1));
                    ps.addBatch();
                }
                ps.executeBatch();
//                int i = 1/0;
                c.commit();

            }catch (Exception e){
                e.printStackTrace();
                c.rollback();
            }
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
