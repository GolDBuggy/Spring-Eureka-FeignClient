package com.spring.project.utils;

import com.spring.project.exception.ErrorResponse;
import com.spring.project.exception.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.micrometer.core.instrument.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ProductErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder decoder=new Default();

    @Override
    public Exception decode(String s, Response response) {
        ErrorResponse errorResponse=null;
        try (InputStream body=response.body().asInputStream()){
            errorResponse=new ErrorResponse(IOUtils.toString(body, StandardCharsets.UTF_8),
                    response.status(), response.headers().get("date").toArray()[0].toString());

        }catch (IOException e) {
            return new Exception(e.getMessage());
        }

        switch (errorResponse.getStatus()){
            case 404: throw new UserNotFoundException(errorResponse.toString());
            case 500: throw new RuntimeException(errorResponse.toString());
            default:
                return decoder.decode(s,response);
        }


    }
}
