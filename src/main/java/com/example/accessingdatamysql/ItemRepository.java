package com.example.accessingdatamysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer>{
public List<Item> findByCategoryId(int id);
public long deleteByItemName(String itemName);
public Item findByItemName(String itemName);
}
