package com.demo.starbux.repositories;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.starbux.domain.Item;
@Primary
public interface ItemRepo extends CrudRepository<Item, Integer>{

	
}
