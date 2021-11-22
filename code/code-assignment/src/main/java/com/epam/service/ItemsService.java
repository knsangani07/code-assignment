package com.epam.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.model.Item;
import com.epam.repository.ItemRepository;

@Service
public class ItemsService {

	@Autowired
	private ItemRepository itemsRepository;

	public List<Item> getAllItems() {
		List<Item> items = new ArrayList<>();
		itemsRepository.findAll().forEach(items::add);
		return items;
	}

	public void addItem(Item item) {
		itemsRepository.save(item);
	}

	public List<Item> getItemsByOrderId(Long orderId) {
		List<Item> itemList = itemsRepository.findAll().stream().filter(offer -> offer.getOrderId() == orderId)
				.collect(Collectors.toList());
		return itemList;
	}
}
