package com.s4hanaextension.dataadjustments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DataadjustmentsApplication extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(DataadjustmentsApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(DataadjustmentsApplication.class, args);
	}
}
