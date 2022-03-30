package com.esgi.modules.follow.exposition;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@SuppressWarnings("all")
@XmlRootElement
public class FollowsResponse {
    public final List<FollowsResponse> follows;

    public FollowsResponse(List<FollowsResponse> follows) {
        this.follows = follows;
    }
}
