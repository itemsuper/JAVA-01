package com.example.switchDataSource.mapper;

import com.example.switchDataSource.entity.Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description
 *
 */
public interface TestMapper {
    /**
     * 获取全部数据
     * @return List<Test>
     */
    List<Test> getAll();

    /**
     * 插入一条数据
     * @param test 数据
     */
    void insertOne(@Param("test") Test test);
}
