package com.example.batchInsert;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;

public class MySQLDemo {
    /**
     * 配置 Hikari 连接池
     */
    public static void main(String[] args) throws SQLException {
        try {
            //通过反射加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/rbt?characterEncoding=UTF-8");
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
        try {
            try {
                long startTime = System.currentTimeMillis();
                c.setAutoCommit(false);
                //构造预处理statement
                PreparedStatement pst = c.prepareStatement("INSERT INTO `rbt`.`goodsorder` (`order_id`, `buy_cust_id`, `sell_cust_id`, `consignee`, `area_attr`, `buy_address`, `zip_code`, `telephone`, `mobile`, `goods_amount`, `comm_free`, `ship_free`, `insured`, `why_price`, `tatal_amount`, `pay_id`, `send_mode`, `order_state`, `order_time`, `pay_time`, `send_time`, `sure_time`, `mem_remark`, `send_num`, `is_virtual`, `order_type`, `mark_id`) VALUES (?, '1', '1', '测试', '测试地址', '详细地址', '309812', '64345353', '1532123123', '3232.12', '12.21', '3.21', '2.00', '12.21', '3214.12', '1', '12', '3', '2021-03-06 21:51:45', '2021-03-07 21:51:50', '2021-03-10 21:51:55', '2021-03-15 21:51:58', '会员留言', '314123', '1', '2', '备注');\n");
                Date now = new Date(System.currentTimeMillis());
                    //1万次循环
                    for (int i = 1; i <= 1000000; i++) {
                    pst.setString(1, "test" + i);
                    pst.addBatch();
                    //每1000次提交一次
                    if (i % 1000 == 0) {//可以设置不同的大小；如50，100，500，1000等等
                        pst.executeBatch();
                        c.commit();
                        pst.clearBatch();
                    }
                }
                pst.executeBatch();
                c.commit();
                long endTime = System.currentTimeMillis();
                //300+秒
                System.out.println("-----------用时-----------"+(endTime-startTime)/1000+"秒");
            } catch (Exception e) {
                e.printStackTrace();
                c.rollback();
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
