package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<RequestClass, Greeting> {
    
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

	@Override
	public Greeting handleRequest(RequestClass request, Context context) {
		// TODO Auto-generated method stub
		Greeting result = null;
		context.getLogger().log("Input: " + request.getName());
		try {
	        result= new Greeting(counter.incrementAndGet(),
                    String.format(template, request.getName()));

            context.getLogger().log("Output: " + result.toString());
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return result;
	}
}