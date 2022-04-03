package com.esgi;

import com.esgi.modules.comment.application.CreateComment;
import com.esgi.modules.comment.application.CreateCommentCommandHandler;
import com.esgi.modules.comment.application.LikeComment;
import com.esgi.modules.comment.application.LikeCommentCommandHandler;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentLikeId;
import com.esgi.modules.follow.application.CreateFollow;
import com.esgi.modules.follow.application.CreateFollowCommandHandler;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.post.application.CreatePost;
import com.esgi.modules.post.application.CreatePostCommandHandler;
import com.esgi.modules.post.application.LikePost;
import com.esgi.modules.post.application.LikePostCommandHandler;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostLikeId;
import com.esgi.modules.user.application.CreateUser;
import com.esgi.modules.user.application.CreateUserCommandHandler;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.UserId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("Hello Spring!");
        SpringApplication.run(App.class, args);
    }
}
