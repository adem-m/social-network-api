package com.esgi.modules.user.exposition;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@SuppressWarnings("all")
@XmlRootElement
public class UsersResponse {
    public final List<UserResponse> members;

    public UsersResponse(List<UserResponse> members) {
        this.members = members;
    }
}
