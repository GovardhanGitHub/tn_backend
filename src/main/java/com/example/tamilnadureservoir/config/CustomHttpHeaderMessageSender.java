package com.example.tamilnadureservoir.config;

import org.springframework.http.HttpHeaders;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender.RemoveSoapHeadersInterceptor;

public class CustomHttpHeaderMessageSender extends HttpComponentsMessageSender {
    // private final String userName;
    // private final String password;
    // private final String program;

    // public CustomHttpHeaderMessageSender(String userName, String password, String
    // program) {
    // this.userName = userName;
    // this.password = password;
    // this.program = program;

    // // Add an interceptor to remove the SOAPAction header
    // getHttpClient().addInterceptorFirst(new RemoveSoapHeadersInterceptor());
    // }

    // @Override
    // protected void addRequestHeaders(HttpHeaders headers) {
    // super.addRequestHeaders(headers);
    // headers.add("userName", userName);
    // headers.add("password", password);
    // headers.add("program", program);
    // }
}
