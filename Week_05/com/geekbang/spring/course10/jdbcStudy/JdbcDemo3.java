package com.geekbang.spring.course10.jdbcStudy;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

/**
 * 配置 Hikari 连接池
 */
public class JdbcDemo3 {
    public static void main(String[] args) throws SQLException {
        try {
            //通过反射加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/study?characterEncoding=UTF-8");
        config.setUsername("root");
        config.setPassword("root");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "300");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource dataSource = new HikariDataSource(config);
        Connection c = null;
        c = dataSource.getConnection();
        //获得statement对象
        Statement s = c.createStatement();
        try
        {
            try {
                PreparedStatement preparedStatement = null;
                c.setAutoCommit(false);
                PreparedStatement ps = c.prepareStatement("insert into student value(null,?)");

                for (int i = 0; i < 100; i++) {
                    ps.setString(1, "学生" + (i+1));
                    ps.addBatch();
                }
                ps.executeBatch();
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
