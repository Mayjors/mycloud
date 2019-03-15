package com.eu.mongo.dao;

import com.eu.pojo.bo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author yuanjie
 * @date 2019/3/15 15:25
 */
public interface TestMongoRepository extends MongoRepository<Book, Long> {
    Book findByName(String name);
}
