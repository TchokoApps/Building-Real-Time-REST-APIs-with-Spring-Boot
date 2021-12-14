package com.tchokoapps.springboot.blogrestapi;

import com.github.javafaker.Faker;
import com.tchokoapps.springboot.blogrestapi.dto.CommentDto;
import com.tchokoapps.springboot.blogrestapi.dto.PostDto;
import com.tchokoapps.springboot.blogrestapi.service.CommentService;
import com.tchokoapps.springboot.blogrestapi.service.PostService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class BlogRestApiApplication implements CommandLineRunner {

    public static final int NUMBER_OF_COMMENTS = 50;
    public static final int NUMBER_OF_POSTS = 20;
    private PostService postService;
    private CommentService commentService;

    public static void main(String[] args) {
        SpringApplication.run(BlogRestApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        createPosts(faker);

        createComments(faker);
    }

    private void createComments(Faker faker) {

        System.out.println("--------- Create Comments --------- ");
        for (int i = 0; i < NUMBER_OF_COMMENTS; i++) {
            CommentDto commentDto = new CommentDto();
            commentDto.setBody(faker.funnyName().name());
            commentDto.setName(faker.funnyName().name());
            commentDto.setEmail(faker.internet().emailAddress());
            CommentDto savedCommentDto = commentService.createCommentDto(RandomUtils.nextLong(1, 6), commentDto);
            System.out.println("Comment created: " + savedCommentDto);
        }
    }

    private void createPosts(Faker faker) {

        System.out.println("--------- Create Posts --------- ");
        for (int i = 0; i < NUMBER_OF_POSTS; i++) {

            PostDto postDto = new PostDto();
            postDto.setTitle(faker.artist().name());
            postDto.setDescription(faker.artist().name());
            postDto.setContent(faker.artist().name());

            PostDto savedPostDto = postService.createPostDto(postDto);
            System.out.println("Comment created: " + savedPostDto);

        }
    }
}
