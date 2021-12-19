package com.tchokoapps.springboot.blogrestapi;

import com.github.javafaker.Faker;
import com.tchokoapps.springboot.blogrestapi.dto.CommentDto;
import com.tchokoapps.springboot.blogrestapi.dto.PostDto;
import com.tchokoapps.springboot.blogrestapi.entity.Role;
import com.tchokoapps.springboot.blogrestapi.entity.User;
import com.tchokoapps.springboot.blogrestapi.enums.RoleEnum;
import com.tchokoapps.springboot.blogrestapi.service.CommentService;
import com.tchokoapps.springboot.blogrestapi.service.PostService;
import com.tchokoapps.springboot.blogrestapi.service.RoleService;
import com.tchokoapps.springboot.blogrestapi.service.UserService;
import com.tchokoapps.springboot.blogrestapi.utils.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.HashSet;

@AllArgsConstructor
@SpringBootApplication
public class BlogRestApiApplication implements CommandLineRunner {

    public static final int NUMBER_OF_COMMENTS = 50;
    public static final int NUMBER_OF_POSTS = 20;
    public static final int NUMBER_OF_USERS = 10;
    private PostService postService;
    private CommentService commentService;
    private UserService userService;
    private RoleService roleService;

    public static void main(String[] args) {
        SpringApplication.run(BlogRestApiApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Faker faker = new Faker();

        createPosts(faker);
        createComments(faker);
        createRoles();
        createUser(faker);
    }

    private void createRoles() {

        Role role = new Role();
        role.setName(RoleEnum.USER.name());

        Role role2 = new Role();
        role2.setName(RoleEnum.ADMIN.name());

        roleService.createRole(role);
        roleService.createRole(role2);
    }

    private void createUser(Faker faker) {

        for (int i = 0; i < NUMBER_OF_USERS; i++) {
            User user = new User();
            user.setName(faker.name().name());
            user.setUsername(faker.name().username());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(PasswordEncoder.encodePassword("password"));
            user.setRoles(new HashSet<>(Collections.singletonList(roleService.findRoleById(RandomUtils.nextLong(1, 3)))));
            userService.createUser(user);
        }
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
