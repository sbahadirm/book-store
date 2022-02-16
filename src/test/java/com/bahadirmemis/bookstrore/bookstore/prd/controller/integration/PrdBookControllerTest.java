package com.bahadirmemis.bookstrore.bookstore.prd.controller.integration;

import com.bahadirmemis.bookstrore.bookstore.BaseTest;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.prd.dto.PrdBookSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PrdBookControllerTest extends BaseTest {

    private final static String BASE_URL = "/api/v1/books";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void test_getAll_successful() throws Exception {

        MvcResult result = mockMvc.perform(get(BASE_URL).content("").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void test_create_successful() throws Exception {

        PrdBookSaveRequestDto cusCustomerSaveRequestDto = new PrdBookSaveRequestDto();
        cusCustomerSaveRequestDto.setName("Java");
        cusCustomerSaveRequestDto.setPrice(BigDecimal.TEN);
        cusCustomerSaveRequestDto.setWriterName("Sadık Bahadır Memiş");

        String content = objectMapper.writeValueAsString(cusCustomerSaveRequestDto);

        MvcResult result = mockMvc.perform(post(BASE_URL).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void test_delete_idDoesNotExists() throws Exception {

        MvcResult result = mockMvc.perform(delete(BASE_URL + "/0").content("0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    void test_addStock_successful() throws Exception {

        MvcResult result = mockMvc.perform(post(BASE_URL + "/1/stocks/add")
                        .param("id", "1L")
                        .param("amount", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void test_removeStock_successful() throws Exception {

        MvcResult result = mockMvc.perform(post(BASE_URL + "/1/stocks/remove")
                        .param("id", "1L")
                        .param("amount", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void test_getStock_successful() throws Exception {

        MvcResult result = mockMvc.perform(get(BASE_URL + "/1/stocks")
                        .param("id", "1L")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}