package com.eu.mongo.controller;

import com.eu.mongo.service.MongoDbService;
import com.eu.pojo.bo.Book;
import com.eu.util.result.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuanjie
 * @date 2018/12/26 17:30
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private MongoDbService mongoDbService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultMessage saveBook() {
        Book book = new Book();
        book.setId(1L);
        book.setName("三国演义");
        book.setInfo("info");
        book.setPublish("publish");
        book.setPrice(100);
        mongoDbService.saveBook(book);
        return new ResultMessage();
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    public ResultMessage findAll() {
        ResultMessage message = new ResultMessage();
        List<Book> list = mongoDbService.findAll();
        message.setValue(list);
        return message;
    }

    @RequestMapping(value = "findOne", method = RequestMethod.GET)
    public ResultMessage findOne(@RequestParam long id) {
        ResultMessage resultMessage = new ResultMessage();
        Book book = mongoDbService.getBookById(id);
        resultMessage.setValue(book);
        return resultMessage;
    }

    @RequestMapping(value = "findByName", method = RequestMethod.GET)
    public ResultMessage findByName(@RequestParam String name) {
        ResultMessage resultMessage = new ResultMessage();
        Book book = mongoDbService.getBookByName(name);
        resultMessage.setValue(book);
        return resultMessage;
    }

}
