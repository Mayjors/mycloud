package com.eu.servicehadoop.service;

public interface BusReceiverService {

    void createTable();

    void loadData(String pathFile);

    void insert();

    void deleteAll();

}
