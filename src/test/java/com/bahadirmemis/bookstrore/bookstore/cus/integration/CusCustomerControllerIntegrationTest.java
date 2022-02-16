package com.bahadirmemis.bookstrore.bookstore.cus.integration;

import com.bahadirmemis.bookstrore.bookstore.BaseTest;
import com.bahadirmemis.bookstrore.bookstore.cus.dto.CusCustomerSaveRequestDto;
import com.bahadirmemis.bookstrore.bookstore.gen.dto.RestResponse;
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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CusCustomerControllerIntegrationTest extends BaseTest {

    private final static String BASE_URL = "/api/v1/customers";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    public void testGetAll() throws Exception {

        MvcResult result = mockMvc.perform(get(BASE_URL).content("").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    public void testSave_successful() throws Exception {

        String username = RandomStringUtils.random(10, true, false);

        CusCustomerSaveRequestDto cusCustomerSaveRequestDto = new CusCustomerSaveRequestDto();
        cusCustomerSaveRequestDto.setName(username);
        cusCustomerSaveRequestDto.setSurname(username);
        cusCustomerSaveRequestDto.setUsername(username);
        cusCustomerSaveRequestDto.setPassword(username);

        String content = objectMapper.writeValueAsString(cusCustomerSaveRequestDto);

        MvcResult result = mockMvc.perform(post(BASE_URL).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }

    @Test
    public void testSave_usernameAlreadyInUse() throws Exception {

        String content = "{\n" +
                "  \"name\": \"bahadir\",\n" +
                "  \"surname\": \"memis\",\n" +
                "  \"username\": \"sbahadirm\",\n" +
                "  \"password\": \"12345678\"\n" +
                "}";

        MvcResult result = mockMvc.perform(post(BASE_URL).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }

    @Test
    public void testGetAllOrdersByCustomerId_Successful() throws Exception {

        MvcResult result = mockMvc.perform(get(BASE_URL + "/orders").content("").contentType(MediaType.APPLICATION_JSON))
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
}
