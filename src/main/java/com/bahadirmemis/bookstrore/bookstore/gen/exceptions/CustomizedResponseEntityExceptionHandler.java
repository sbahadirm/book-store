package com.bahadirmemis.bookstrore.bookstore.gen.exceptions;

import com.bahadirmemis.bookstrore.bookstore.gen.dto.ExceptionResponse;
import com.bahadirmemis.bookstrore.bookstore.gen.dto.RestResponse;
import com.bahadirmemis.bookstrore.bookstore.gen.enums.GenErrorMessageType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){
        return getResponseEntity(ex, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllGenItemNotFoundExceptions(GenItemNotFoundException ex, WebRequest webRequest){
        return getResponseEntity(ex, webRequest, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllGenBusinessExceptions(GenBusinessException ex, WebRequest webRequest){
        return getResponseEntity(ex, webRequest, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        Date errorDate = new Date();
        String message = GenErrorMessageType.VALIDATION_FAILED.getMessage();
        String errorCode = GenErrorMessageType.VALIDATION_FAILED.getErrorCode();
        String description = getExceptionDescription(ex);

        ExceptionResponse exceptionResponse = new ExceptionResponse(errorDate, errorCode, message, description);

        return new ResponseEntity(RestResponse.error(exceptionResponse), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> getResponseEntity(Exception ex, WebRequest webRequest, HttpStatus httpStatus) {
        ExceptionResponse exceptionResponse = getExceptionResponse(ex, webRequest);

        return new ResponseEntity(RestResponse.error(exceptionResponse), httpStatus);
    }

    private ResponseEntity<Object> getResponseEntity(GenBusinessException ex, WebRequest webRequest, HttpStatus httpStatus) {

        String errorCode = ex.getBaseErrorMessageType().getErrorCode();

        ExceptionResponse exceptionResponse = getExceptionResponse(ex, errorCode, webRequest);

        return new ResponseEntity(RestResponse.error(exceptionResponse), httpStatus);
    }

    private ExceptionResponse getExceptionResponse(Exception ex, WebRequest webRequest) {
        return getExceptionResponse(ex, "", webRequest);
    }

    private ExceptionResponse getExceptionResponse(Exception ex, String errorCode, WebRequest webRequest) {
        Date errorDate = new Date();
        String message = getErrorMessage(ex);
        String code = getErrorCode(errorCode);
        String description = webRequest.getDescription(false);

        return new ExceptionResponse(errorDate, code, message, description);
    }

    private String getErrorCode(String errorCode) {

        String code = errorCode;
        if (!StringUtils.hasText(errorCode)){
            code = GenErrorMessageType.UNKNOWN_ERROR.getErrorCode();
        }

        return code;
    }

    private String getErrorMessage(Exception ex) {
        String message = ex.getMessage();
        if (!StringUtils.hasText(message)){
            message = GenErrorMessageType.UNKNOWN_ERROR.getMessage();
        }
        return message;
    }

    private String getExceptionDescription(MethodArgumentNotValidException ex) {
        StringBuilder stringBuilder = new StringBuilder();
        ex.getFieldErrors().stream().forEach(objectError -> {
            stringBuilder.append(objectError.getField());
            stringBuilder.append(" -> ");
            stringBuilder.append(objectError.getDefaultMessage());
        });

        String description = stringBuilder.toString();
        return description;
    }
}
