package edu.ap.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import edu.ap.spring.controller.JokeController;
@SpringBootApplication
public class JokeService {


	private static final Logger log = LoggerFactory.getLogger(JokeService.class);

	public static void main(String args[]) {
		SpringApplication.run(JokeService.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			JokeController quote = restTemplate.getForObject(
					"http://gturnquist-quoters.cfapps.io/api/random", JokeController.class);
			log.info(quote.toString());
		};
	}
}