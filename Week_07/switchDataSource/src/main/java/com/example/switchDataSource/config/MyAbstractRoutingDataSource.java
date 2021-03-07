package com.example.switchDataSource.config;

import com.example.switchDataSource.util.NumberUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Description
 * spring 在开始进行数据库操作时会通过这个方法来决定使用哪个数据库，
 * 因此我们在这里调用上面 DbContextHolder 类的getDbType()方法获取当前操作类别,
 * 同时可进行读库的负载均衡
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {

    @Value("${mysql.datasource.num}")
    private int num;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DbContextHolder.getDbType();
        if (typeKey == DbContextHolder.WRITE) {
            log.info("使用了写库");
            return typeKey;
        }
        //使用随机数决定使用哪个读库
        int sum = NumberUtil.getRandom(1, num);
        log.info("使用了读库{}", sum);
        return DbContextHolder.READ + sum;
    }
}
