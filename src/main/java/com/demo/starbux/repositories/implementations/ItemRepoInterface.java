package com.demo.starbux.repositories.implementations;

import java.util.Optional;

import com.demo.starbux.domain.Item;
import com.demo.starbux.repositories.ItemRepo;

public interface ItemRepoInterface extends ItemRepo {

	public Integer findByName(String itemName);

}
