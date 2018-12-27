package com.eu.mongo.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * mongoDB基础方法封装
 * @author yuanjie
 * @date 2018/12/27 14:11
 */
@Slf4j
public abstract class MongoDbDao<T> {

    /**
     * 反射获取泛型类型
     * @return
     */
    protected abstract Class<T> getEntityClass();

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(T t) {
        log.info("----> [MongoDB save start ]");
        this.mongoTemplate.save(t);
    }

    /**
     * 根据id从集合中查询对象
     * @param id
     * @return
     */
    public T queryById(Long id) {
        log.info("-----> [MongoDB find one start]");
        Query query = new Query(Criteria.where("_id").is(id));
        return this.mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 根据条件查询只返回一个文档
     * @param t
     * @return
     */
    public T queryOne(T t) {
        Query query = getQueryByObject(t);
        log.info("-----> MongoDB find start");
        return mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 根据条件查询集合
     * @param t
     * @return
     */
    public List<T> queryList(T t) {
        Query query = getQueryByObject(t);
        log.info("-----> MongoDB find start");
        return mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 根据条件分页查询
     * @param t
     * @param start
     * @param size
     * @return
     */
    public List<T> getPage(T t, int start, int size) {
        Query query = getQueryByObject(t);
        query.skip(start);
        query.limit(size);
        log.info("-----> MongoDB queryPage start");
        return this.mongoTemplate.find(query, this.getEntityClass());
    }

    /**
     * 根据条件查询记录数量
     * @param t
     * @return
     */
    public Long getCount(T t) {
        Query query = getQueryByObject(t);
        log.info("-----> MongoDB count start");
        return this.mongoTemplate.count(query, this.getEntityClass());
    }

    /**
     * 删除对象
     * @param t
     * @return
     */
    public int delete(T t) {
        log.info("-----> MongoDB delete start");
        return (int) this.mongoTemplate.remove(t).getDeletedCount();
    }

    public void deleteById(Long id) {
        Criteria criteria = Criteria.where("_id").is(id);
        if (null != criteria) {
            Query query = new Query(criteria);
            T t = this.mongoTemplate.findOne(query, this.getEntityClass());
            log.info("-----> MongoDB deleteById start");
            if (t != null) {
                this.delete(t);
            }
        }
    }

    /**
     * 修改记录
     * updateFirst    修改第一条
     * updateMulti    修改所有匹配的
     * upsert         修改时如果不存在这进行添加操作
     * @param t
     * @param targetObj
     */
    public void updateFirst(T t, T targetObj) {
        Query query = getQueryByObject(t);
        Update update = getUpdateByObject(targetObj);
        log.info("-------------->MongoDB updateFirst start");
        this.mongoTemplate.updateFirst(query,update,this.getEntityClass());
    }

    /**
     * 将查询条件对象转换为query
     * @param object
     * @return
     */
    private Query getQueryByObject(T object) {
        Query query = new Query();
        String[] fileds = getFiledName(object);
        Criteria criteria = new Criteria();
        for (int i=0; i< fileds.length; i++) {
            String filedNmae = fileds[i];
            Object filedValue = getFieldValueByName(filedNmae, object);
            if (filedValue != null) {
                criteria.and(filedNmae).is(filedValue);
            }
        }
        query.addCriteria(criteria);
        return query;
    }

    private Update getUpdateByObject(T object) {
        Update update = new Update();
        String[] fileds = getFiledName(object);
        for (int i=0; i< fileds.length; i++) {
            String filedNmae = fileds[i];
            Object filedValue = getFieldValueByName(filedNmae, object);
            if (filedValue != null) {
                update.set(filedNmae, filedValue);
            }
        }
        return update;
    }

    /**
     * 获取对象属性返回字符串数组
     * @param o
     * @return
     */
    private static String[] getFiledName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i=0; i< fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 根据属性获取对象属性值
     * @param fieldName
     * @param o
     * @return
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String e = fieldName.substring(0,1).toUpperCase();
            String getter = "get" + e + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[0]);
            return method.invoke(o, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
