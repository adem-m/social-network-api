package com.esgi.modules.follow.exposition;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@SuppressWarnings("all")
@XmlRootElement
public class FollowsResponse {
    public final List<FollowResponse> follows;

    public FollowsResponse(List<FollowResponse> follows) {
        this.follows = follows;
    }
}
