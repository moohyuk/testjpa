package com.ryan.testjpa.tutorial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import java.sql.SQLOutput;

//@SpringBootTest
@DataJpaTest
public class TestCommentRepository {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public void getComment() {
        Post post = new Post();
        post.setTitle("JPA POST");
        Post savedPost = postRepository.save(post);

        Comment comment = new Comment();
        comment.setComment("Hello~");
        comment.setPost(savedPost);
        comment.setUp(10);
        comment.setDown(1);
        Comment savedComment = commentRepository.save(comment);

        commentRepository.findByPost_Id(savedPost.getId(), CommentSummary.class).forEach(c-> {
            System.out.println("==========");
            System.out.println(c.getVotes());
        });

        commentRepository.findByPost_Id(savedPost.getId(), CommentOnly.class).forEach(c->{
            System.out.println("==========");
            System.out.println(c.getComment());
        });
    }
}
