package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.epam.error.OrderNotFoundException;
import com.epam.model.Item;
import com.epam.model.OrderDetail;
import com.epam.repository.ItemRepository;
import com.epam.repository.OrderRepository;
import com.epam.service.ItemsService;
import com.epam.service.OffersService;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
public class OrderController {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private OffersService offersService;

	@Autowired
	private ItemsService itemsService;

	@Autowired
	private Environment env;

	// Find
	@GetMapping("/orders")
	List<OrderDetail> findAll() {
		List<OrderDetail> orderDetailList = repository.findAll();
		List<OrderDetail> orderDetailItemList = new ArrayList<OrderDetail>();
		for(OrderDetail orderDetail : orderDetailList) {
			List<Item> itemList = itemsService.getItemsByOrderId(orderDetail.getId());
			if(itemList!=null) {
				orderDetail.setItemList(itemList);
			}
			orderDetailItemList.add(orderDetail);
		}
		return orderDetailItemList;
	}

	// Save
	@PostMapping("/orders")
	@ResponseStatus(HttpStatus.CREATED)
	OrderDetail orders(@Valid @RequestBody OrderDetail newOrderNew) {
		return repository.save(newOrderNew);
	}

	@PostMapping("/order/checkout")
	@ResponseStatus(HttpStatus.CREATED)
	OrderDetail checkOutOrder(@Valid @RequestBody List<Item> itemList) {
		double totalItemCost = 0.0;
		List<Item> priceItemList = new ArrayList<Item>();
		for (Item item : itemList) {
			if (item != null && item.getProductId() > 0) {
				offersService.applyOffers(item);
				priceItemList.add(item);
				totalItemCost = totalItemCost + item.getItemTotalPrice();
			}
		}
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setItemList(priceItemList);
		orderDetail.setSubTotal(totalItemCost);
		String taxStr = env.getProperty("epam.tax");
		if (taxStr != null && !StringUtils.isEmpty(taxStr)) {
			double tax = Double.parseDouble(env.getProperty("epam.tax"));
			double itmsTax = totalItemCost / (100 + tax) * tax;
			orderDetail.setTotal(totalItemCost + itmsTax);
			orderDetail.setTax(itmsTax);
		}
		return orderDetail;
	}

	// Save
	@PostMapping("/order/placeOrder")
	@ResponseStatus(HttpStatus.CREATED)
	OrderDetail placeOrder(@Valid @RequestBody OrderDetail newOrderNew) {
		List<Item> itemList = newOrderNew.getItemList();
		OrderDetail orderDetail = repository.save(newOrderNew);
		for (Item item : itemList) {
			System.out.println("item productId : " + item.getProductId());
			item.setOrderId(orderDetail.getId());
			item.setItemTotalPrice(0.60);
			itemRepository.save(item);
		}
		return orderDetail;
	}

	// Find
	@GetMapping("/orders/{id}")
	OrderDetail findOne(@PathVariable @Min(1) Long id) {
		OrderDetail orderDetail = repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
		List<Item> itemList = itemsService.getItemsByOrderId(id);
		orderDetail.setItemList(itemList);
		return orderDetail;
	}
}
