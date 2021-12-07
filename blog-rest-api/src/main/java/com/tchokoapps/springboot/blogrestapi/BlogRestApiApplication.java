package com.tchokoapps.springboot.blogrestapi;

import com.github.javafaker.Faker;
import com.tchokoapps.springboot.blogrestapi.dto.PostDto;
import com.tchokoapps.springboot.blogrestapi.dto.mapper.PostDtoMapper;
import com.tchokoapps.springboot.blogrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class BlogRestApiApplication implements CommandLineRunner {

	private PostService postService;

	public static void main(String[] args) {
		SpringApplication.run(BlogRestApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		PostDtoMapper postDtoMapper = new PostDtoMapper();
		Faker faker = new Faker();

		for (int i = 0; i < 10; i++) {

			PostDto postDto = new PostDto();
			postDto.setTitle(faker.artist().name());
			postDto.setDescription(faker.artist().name());
			postDto.setContent(faker.artist().name());

			PostDto savedPostDto = postService.createPostDto(postDto);
			System.out.println("Created: " + savedPostDto);

		}
	}
}
