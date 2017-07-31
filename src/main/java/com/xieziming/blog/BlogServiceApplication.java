/*
 * Author: Suny Xie
 * Email: inbox@xieziming.com
 * Copyright (c) 2017 xieziming.com All rights reserved.
 */
package com.xieziming.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application configuration file.
 *
 * @author Suny Xie
 */
@SpringBootApplication
public class BlogServiceApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BlogServiceApplication.class, args);
//		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(TapServiceApplication.class, args);
//		Wordpress2ElasticsearchTransferService wordpress2ElasticsearchTransferService = (Wordpress2ElasticsearchTransferService) configurableApplicationContext.getBean("wordpress2ElasticsearchTransferService");
//		wordpress2ElasticsearchTransferService.deleteWordpressArticleIndex();
//		wordpress2ElasticsearchTransferService.createWordpressArticleIndex();

//		wordpress2ElasticsearchTransferService.tranferAllPosts();
//		wordpress2ElasticsearchTransferService.tranferAllPages();


//		wordpress2ElasticsearchTransferService.deleteWordpressCalendarIndex();
//		wordpress2ElasticsearchTransferService.createWordpressCalendarIndex();
//		wordpress2ElasticsearchTransferService.tranferAllCalendars();
	}
}
