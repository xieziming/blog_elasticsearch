/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */
package com.xieziming.blog;

import com.xieziming.blog.service.TransferService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * Application configuration file.
 *
 * @author Suny Xie
 */
@SpringBootApplication
public class TapServiceApplication {
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(TapServiceApplication.class, args);
		TransferService transferService = (TransferService) configurableApplicationContext.getBean("transferService");
		transferService.deletePostIndex();
		//transferService.deleteCalendarIndex();
		transferService.createPostIndex();
		//transferService.createCalendarIndex();
		transferService.tranferAllPostFromWordpressToElasticsearch();
		//transferService.tranferAllCalendarFromWordpressToElasticsearch();
	}
}
