package com.epam.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.model.Item;
import com.epam.model.Offers;
import com.epam.model.Product;
import com.epam.repository.OffersRepository;
import com.epam.repository.ProductRepository;

@Service
public class OffersService {
	@Autowired
	private OffersRepository offersRepository;

	@Autowired
	private ProductRepository productRepository;

	public List<Offers> getAllOffers() {
		List<Offers> userRecords = new ArrayList<>();
		offersRepository.findAll().forEach(userRecords::add);
		return userRecords;
	}

	public void addOffer(Offers userRecord) {
		offersRepository.save(userRecord);
	}

	public Offers getOfferByProductId(Long productId) {
		Offers offerObject = null;
		Optional<Offers> optionalOffer = offersRepository.findAll().stream()
				.filter(offer -> offer.getProductId() == productId).findFirst();
		if (optionalOffer.isPresent()) {
			offerObject = optionalOffer.get();
		}
		return offerObject;
	}

	public Item applyOffers(Item item) {
		Product product = productRepository.getOne(item.getProductId());
		if (product != null) {
			Offers itemOffer = getOfferByProductId(item.getProductId());
			int buyQuantity = 1;
			int freeQuantity = 0;
			if (itemOffer != null) {
				buyQuantity = itemOffer.getBuyQuantity();
				freeQuantity =  itemOffer.getFreeQuantity();
			}
			int quotient = (item.getQuantity() / (buyQuantity+ freeQuantity))* buyQuantity;
			int remainder = item.getQuantity() % (buyQuantity+ freeQuantity);
			int noOfItemsToBeCharge = quotient + remainder;
			double chargedPrice = (noOfItemsToBeCharge * product.getPrice());
			item.setItemTotalPrice(chargedPrice);
		}
		return item;
	}

}
