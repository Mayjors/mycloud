package com.eu.mongo.service;

import com.eu.mongo.dao.TestMongoRepository;
import com.eu.pojo.bo.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author yuanjie
 * @date 2018/12/26 16:45
 */
@Slf4j
@Service
public class MongoDbService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TestMongoRepository testMongoRepository;

    public List<Book> find() {
        Optional<Book> byId = testMongoRepository.findById(1L);
        Book book = byId.get();
        book = testMongoRepository.findByName("三国演义");
        return testMongoRepository.findAll();
    }
    /**
     * 保存对象
     * @param book
     */
    public void saveBook(Book book) {
      log.info("----> [MongoDB save start ]");
      book.setCreateTime(new Date());
      book.setUpdateTime(new Date());
      mongoTemplate.save(book);
    }

    public List<Book> findAll() {
        log.info("----> [MongoDB find start]");
        return mongoTemplate.findAll(Book.class);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Book getBookById(long id) {
        log.info("-----> [MongoDB find one start]");
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Book.class);
    }

    /**
     * 根据name查询
     * @param name
     * @return
     */
    public Book getBookByName(String name) {
        log.info("-----> [MongoDB find by name start]");
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Book.class);
    }

    /**
     * 更新对象
     * @param book
     */
    public void updateBook(Book book) {
        log.info("-----> [MongoDB update start]");
        Query query = new Query(Criteria.where("_id").is(book.getId()));
        Update update = new Update().set("publish", book.getPublish())
                .set("info", book.getInfo())
                .set("updateTime", new Date());
        // updateFirst 更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, Book.class);
        // updateMulti 更新查询返回结果集的全部
//        mongoTemplate.updateMulti(query, update, Book.class);
        // usert 更新对象不存在则取添加
//        mongoTemplate.upsert(query, update, Book.class);
    }

    /**
     * 删除对象
     * @param book
     */
    public void deleteBook(Book book) {
        log.info("-----> [MongoDB delete start]");
        mongoTemplate.remove(book);
    }

    public void deleteById(long id) {
        log.info("-----> [MongoDB delete By Id start]");
        // findOne
        Book book = getBookById(id);
        // delete
        deleteBook(book);
    }

}
