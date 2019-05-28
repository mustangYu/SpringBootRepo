package com.dbMustang.dbMapper.mapper1;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Db1Mapper {
    public String getUserById(@Param("id") Integer id);
}
