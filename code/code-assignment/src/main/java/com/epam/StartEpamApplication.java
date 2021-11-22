package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import com.epam.model.Item;
import com.epam.model.Offers;
import com.epam.model.OrderDetail;
import com.epam.model.Product;
import com.epam.repository.ItemRepository;
import com.epam.repository.OffersRepository;
import com.epam.repository.OrderRepository;
import com.epam.repository.ProductRepository;
import com.epam.service.ProductsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

@SpringBootApplication
public class StartEpamApplication {
	
	@Autowired
	private Environment env;
	
    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartEpamApplication.class, args);
    }
    
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabaseProduct(ProductRepository productRepository,OffersRepository offersRepository,ProductsService productsService) {
        return args -> {
        	String products = env.getProperty("epam.products");
        	List<Product> productList = new Gson().fromJson(products, new TypeToken<List<Product>>(){}.getType());
        	for(Product product : productList) {
        		productRepository.save(product);
        	}
        	String offers = env.getProperty("epam.offers");
        	List<Offers> offersList = new Gson().fromJson(offers, new TypeToken<List<Offers>>(){}.getType());
        	for(Offers offer : offersList) {
        		Product product = productsService.getProductByProductName(offer.getProductName());
        		offer.setProductId(product.getId()); 
        		offersRepository.save(offer);
        	}
        };
    }
    
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabaseOrder(OrderRepository repository) {
        return args -> {
        	repository.save(new OrderDetail(0.00, 0.00, 0.00, 0.00, "Complete"));
        	repository.save(new OrderDetail(0.00, 0.00, 0.00,  0.00, "Complete"));
        };
    }
    
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabaseItem(ItemRepository repository) {
        return args -> {
            repository.save(new Item(4l, 8L, 1 ,0.25));
        };
    }
    
   
    
    
}