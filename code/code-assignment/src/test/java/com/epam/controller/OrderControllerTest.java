package com.epam.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.model.Item;
import com.epam.model.OrderDetail;
import com.epam.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository mockRepository;

    @Before
    public void init() {
        OrderDetail orderDetail = new OrderDetail(0.60, 0.60, 0.60, 0.60, "Complete");
        when(mockRepository.findById(1L)).thenReturn(Optional.of(orderDetail));
    }

    //@WithMockUser(username = "USER")
    @WithMockUser("USER")
    @Test
    public void find_login_ok() throws Exception {

        mockMvc.perform(get("/orders/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("Complete")))
                .andExpect(jsonPath("$.subTotal", is(0.60)));
    }
    
    @WithMockUser("USER")
    @Test
    public void getAllOrders() throws Exception 
    {
    	mockMvc.perform( MockMvcRequestBuilders
          .get("/orders")
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk());
         // .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
        //  .andExpect(MockMvcResultMatchers.jsonPath("$.[*].status").isNotEmpty());
    }
    
    @WithMockUser("USER")
    @Test
    public void placeOrderAPI() throws Exception 
    {
    	mockMvc.perform( MockMvcRequestBuilders
          .post("/order/placeOrder")
          .content(asJsonString(new OrderDetail(0.60, 0.60, 0.60, 0.60, "Complete")))
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated());
        //  .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
    
    @WithMockUser("USER")
    @Test
    public void checkourOrderAPI() throws Exception 
    {
    	 List<Item> itemList = new ArrayList<Item>();
    	 Item item1 = new Item(1L,8L, 1 ,0.00);
    	 Item item2 = new Item(1L, 8L, 1 ,0.00);
    	 itemList.add(item1);
    	 itemList.add(item2);
    	mockMvc.perform( MockMvcRequestBuilders
          .post("/order/checkout")
          .content(asJsonString(itemList))
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated());
        //  .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void find_nologin_401() throws Exception {
        mockMvc.perform(get("/orders/1"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

}
