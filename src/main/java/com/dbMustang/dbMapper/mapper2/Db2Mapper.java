package com.dbMustang.dbMapper.mapper2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Db2Mapper {
    public String getUserById(@Param("id") Integer id);
}
