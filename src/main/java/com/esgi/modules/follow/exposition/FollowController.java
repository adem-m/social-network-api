package com.esgi.modules.follow.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.follow.application.*;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.user.application.RetrieveUserById;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.exposition.UserResponse;
import com.esgi.modules.user.exposition.UsersResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
public class FollowController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public FollowController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(path = "/follow", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createFollow(@RequestBody @Valid FollowRequest request) {
        CreateFollow createFollow = new CreateFollow(request.followerId, request.followedId);
        FollowId followId = (FollowId) commandBus.send(createFollow);
        return ResponseEntity.created(URI.create("/follows/id=" + followId.getValue())).build();
    }

    @GetMapping(path = "/following/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getFollowingByFollowerId(@PathVariable String id) {
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollowing(id));
        return getUsersResponseResponseEntity(follows);
    }

    @GetMapping(path = "/followers/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getFollowersByFollowerId(@PathVariable String id) {
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollowers(id));
        return getUsersResponseResponseEntity(follows);
    }

    private ResponseEntity<UsersResponse> getUsersResponseResponseEntity(List<Follow> follows) {
        UsersResponse usersResponseResult = new UsersResponse(new ArrayList<>());
        for (Follow follow : follows) {
            final User user = (User) queryBus.send(new RetrieveUserById(follow.getFollowedId().getValue()));
            usersResponseResult.members.add(
                    new UserResponse(
                            String.valueOf(user.getId().getValue()),
                            user.getLastname(),
                            user.getFirstname(),
                            user.getEmail().getEmail()));
        }
        return ResponseEntity.ok(usersResponseResult);
    }

    @GetMapping(path = "/follows", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FollowsResponse> getAllFollows() {
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollows());
        FollowsResponse followsResponseResult = new FollowsResponse(
                follows.stream().map(follow -> new FollowResponse(
                                String.valueOf(follow.getFollowId().getValue()),
                                follow.getFollowerId().getValue(),
                                follow.getFollowedId().getValue()))
                        .collect(Collectors.toList()));
        return ResponseEntity.ok(followsResponseResult);
    }

    @DeleteMapping(path = "/unfollow", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> unfollow(@RequestBody @Valid FollowRequest request) {
        Unfollow unfollow = new Unfollow(request.followerId, request.followedId);
        commandBus.send(unfollow);
        return ResponseEntity.noContent().build();
    }
}