package com.bahadirmemis.bookstrore.bookstore.ord.controller.integration;

import com.bahadirmemis.bookstrore.bookstore.BaseTest;
import com.bahadirmemis.bookstrore.bookstore.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.ord.dto.OrdOrderSaveRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.RandomStringUtils;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class OrdOrderControllerTest extends BaseTest {

    private final static String BASE_URL = "/api/v1/orders";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    public void test_getAll_successful() throws Exception {

        MvcResult result = mockMvc.perform(get(BASE_URL).content("").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    public void test_findById_successful() throws Exception {

        MvcResult result = mockMvc.perform(get(BASE_URL + "/1").content("1L").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    public void test_create_failForCusCustomerIsNull() throws Exception {

        OrdOrderSaveRequestDto cusCustomerSaveRequestDto = new OrdOrderSaveRequestDto();
        cusCustomerSaveRequestDto.setAmount(1L);
        cusCustomerSaveRequestDto.setPrdBookId(1L);

        String content = objectMapper.writeValueAsString(cusCustomerSaveRequestDto);

        MvcResult result = mockMvc.perform(post(BASE_URL).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);

    }

    @Test
    public void test_getAllOrdersByCustomerId_Successful() throws Exception {

        MvcResult result = mockMvc.perform(get(BASE_URL + "/customers/1").content("1L").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    public void testDelete_idDoesNotExists() throws Exception {

        MvcResult result = mockMvc.perform(delete(BASE_URL + "/0").content("0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    public void test_findAllOrdersByGivenRange_Successful() throws Exception {

        MvcResult result = mockMvc.perform(get(BASE_URL + "/given-range")
                        .param("startDate", "2022-01-01")
                        .param("endDate","2022-01-01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
}