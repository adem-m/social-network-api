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
        /*final ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class, args);
        CreateUserCommandHandler userCommandHandler = applicationContext.getBean(CreateUserCommandHandler.class);
        CreateFollowCommandHandler followCommandHandler = applicationContext.getBean(CreateFollowCommandHandler.class);
        CreatePostCommandHandler postCommandHandler = applicationContext.getBean(CreatePostCommandHandler.class);
        LikePostCommandHandler likePostCommandHandler = applicationContext.getBean(LikePostCommandHandler.class);
        CreateCommentCommandHandler commentCommandHandler = applicationContext.getBean(CreateCommentCommandHandler.class);
        LikeCommentCommandHandler likeCommentCommandHandler = applicationContext.getBean(LikeCommentCommandHandler.class);

        CreateUser Louis = new CreateUser("XIA", "Louis", new Email("louis@gmail.com"), "louis");
        final UserId LouisId = userCommandHandler.handle(Louis);

        CreateUser Adem = new CreateUser("MRIZAK", "Adem", new Email("adem@hotmail.com"), "adem");
        final UserId AdemId = userCommandHandler.handle(Adem);

        CreateUser Arthur = new CreateUser("MATUSZEK", "Arthur", new Email("arthur@outlook.fr"), "arthur");
        final UserId ArthurId = userCommandHandler.handle(Arthur);

        CreateFollow LouisFollowAdem = new CreateFollow(LouisId, AdemId);
        final FollowId LouisFollowAdemId = followCommandHandler.handle(LouisFollowAdem);

        CreateFollow LouisFollowArthur = new CreateFollow(LouisId, ArthurId);
        final FollowId LouisFollowArthurId = followCommandHandler.handle(LouisFollowArthur);

        CreateFollow AdemFollowArthur = new CreateFollow(AdemId, ArthurId);
        final FollowId AdemFollowArthurId = followCommandHandler.handle(AdemFollowArthur);

        CreateFollow ArthurFollowLouis = new CreateFollow(ArthurId, LouisId);
        final FollowId ArthurFollowLouisId = followCommandHandler.handle(ArthurFollowLouis);

        CreatePost post1 = new CreatePost("Louis", LouisId, new Date());
        final PostId postId1 = postCommandHandler.handle(post1);

        CreatePost post2 = new CreatePost("Adem", AdemId, new Date());
        final PostId postId2 = postCommandHandler.handle(post2);

        CreatePost post3 = new CreatePost("Arthur", ArthurId, new Date());
        final PostId postId3 = postCommandHandler.handle(post3);

        CreatePost post4 = new CreatePost("Louis2", LouisId, new Date());
        final PostId postId4 = postCommandHandler.handle(post4);

        LikePost LouisLikePost1 = new LikePost(LouisId, postId1);
        final PostLikeId postLikeId1 = likePostCommandHandler.handle(LouisLikePost1);

        LikePost LouisLikePost2 = new LikePost(LouisId, postId2);
        final PostLikeId postLikeId2 = likePostCommandHandler.handle(LouisLikePost2);

        LikePost ArthurLikePost2 = new LikePost(ArthurId, postId2);
        final PostLikeId postLikeId3 = likePostCommandHandler.handle(ArthurLikePost2);

        LikePost AdemLikePost4 = new LikePost(AdemId, postId4);
        final PostLikeId postLikeId4 = likePostCommandHandler.handle(AdemLikePost4);

        CreateComment comment1 = new CreateComment(postId1, "Louis", LouisId, new Date());
        final CommentId commentId1 = commentCommandHandler.handle(comment1);

        CreateComment comment2 = new CreateComment(postId1, "Adem", AdemId, new Date());
        final CommentId commentId2 = commentCommandHandler.handle(comment2);

        CreateComment comment3 = new CreateComment(postId2, "Arthur", ArthurId, new Date());
        final CommentId commentId3 = commentCommandHandler.handle(comment3);

        CreateComment comment4 = new CreateComment(postId3, "Arthur", ArthurId, new Date());
        final CommentId commentId4 = commentCommandHandler.handle(comment4);

        LikeComment LouisLikeComment2 = new LikeComment(LouisId, commentId2);
        final CommentLikeId commentLikeId1 = likeCommentCommandHandler.handle(LouisLikeComment2);

        LikeComment AdemLikeComment2 = new LikeComment(AdemId, commentId2);
        final CommentLikeId commentLikeId2 = likeCommentCommandHandler.handle(AdemLikeComment2);

        LikeComment ArthurLikeComment1 = new LikeComment(ArthurId, commentId1);
        final CommentLikeId commentLikeId3 = likeCommentCommandHandler.handle(ArthurLikeComment1);*/
    }
}
