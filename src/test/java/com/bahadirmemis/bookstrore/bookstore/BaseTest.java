package com.bahadirmemis.bookstrore.bookstore;

import com.bahadirmemis.bookstrore.bookstore.gen.dto.RestResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public class BaseTest {

    protected ObjectMapper objectMapper;

    protected RestResponse getRestResponse(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        return objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
    }

    protected boolean isSuccess(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        RestResponse restResponse = getRestResponse(result);

        return restResponse.isSuccess();
    }
}
