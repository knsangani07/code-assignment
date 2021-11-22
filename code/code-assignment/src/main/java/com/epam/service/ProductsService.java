package com.epam.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.model.Product;
import com.epam.repository.ProductRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		List<Product> product = new ArrayList<>();
		productRepository.findAll().forEach(product::add);
		return product;
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public Product getProductByProductName(String productName) {
		Product productObject = null;
		Optional<Product> optionalOffer = productRepository.findAll().stream()
				.filter(offer -> offer.getName().equalsIgnoreCase(productName)).findFirst();
		if (optionalOffer.isPresent()) {
			productObject = optionalOffer.get();
		}
		return productObject;
	}
}
