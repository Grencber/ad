package com.demo.starbux.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.starbux.domain.Item;

public interface ItemRepo extends CrudRepository<Item, Integer>{

}
