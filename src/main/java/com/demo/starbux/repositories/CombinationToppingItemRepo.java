package com.demo.starbux.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.starbux.domain.CombinationToppingItem;

public interface CombinationToppingItemRepo extends CrudRepository<CombinationToppingItem, Integer>{

}
