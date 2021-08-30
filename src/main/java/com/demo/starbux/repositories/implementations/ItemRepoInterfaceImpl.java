package com.demo.starbux.repositories.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.starbux.domain.Item;
import com.demo.starbux.repositories.ItemRepo;

public class ItemRepoInterfaceImpl implements ItemRepoInterface{
	
	@Autowired
	private ItemRepo itemRepo;

	public Integer findByName(String itemName) {
		for (Item curr : itemRepo.findAll()) {
			if (curr.getItemName().equalsIgnoreCase(itemName)) {
				return curr.getId();
			}
		}
		return 0;
		
	}

	@Override
	public <S extends Item> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Item> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Item> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Item> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Item> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Item entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Item> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
}
