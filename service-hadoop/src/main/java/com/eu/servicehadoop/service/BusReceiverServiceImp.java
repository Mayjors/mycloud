package com.eu.servicehadoop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
@Service
public class BusReceiverServiceImp implements BusReceiverService {

    @Autowired
    JdbcTemplate hiveJdbcTemplate;

    @Override
    @PostConstruct
    public void createTable() {
        StringBuffer sql = new StringBuffer("create table IF NOT EXISTS ");
        sql.append("bus_receiver ");
        sql.append("(id BIGINT comment '主键ID' " +
                ",name STRING  comment '姓名' " +
                ",address STRING comment '地址'" +
                ",en_name STRING comment '拼音名字'" +
                ",member_family INT comment '家庭成员'" +
                ",createDate DATE comment '创建时') ");
        sql.append(" ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'"); // 定义分隔符
        sql.append(" STORED AS TEXTFILE"); // 作为文本存储*/
        hiveJdbcTemplate.execute(sql.toString());
    }

    public void loadData(String pathFile){
        String sql = "LOAD DATA INPATH  '"+pathFile+"' INTO TABLE bus_receiver";
        hiveJdbcTemplate.execute(sql);
    }

    @Override
    public void insert() {
        hiveJdbcTemplate.update("insert into bus_receiver(id,name,address,en_name,member_family) values(?,?,?,?,?)", new Object());
    }

    public void deleteAll(){
        String sql = "insert overwrite table bus_receiver select * from bus_receiver where 1=0";
        hiveJdbcTemplate.execute(sql);
    }

}
