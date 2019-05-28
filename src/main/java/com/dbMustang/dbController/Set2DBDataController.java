package com.dbMustang.dbController;

import com.dbMustang.dbMapper.mapper1.Db1Mapper;
import com.dbMustang.dbMapper.mapper2.Db2Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/dbMustang")
public class Set2DBDataController {

    @Autowired
    private Db1Mapper db1Mapper;
    @Autowired
    private Db2Mapper db2Mapper;

    @GetMapping("/test/{id}")
    public void getTestUser(@PathVariable("id") Integer id){
       String db1Name =  db1Mapper.getUserById(id);
        String db2Name =  db2Mapper.getUserById(id);
        log.info("数据库1中该id用户名："+db1Name);
        log.info("数据库2中该id用户名："+db2Name);
    }
}
