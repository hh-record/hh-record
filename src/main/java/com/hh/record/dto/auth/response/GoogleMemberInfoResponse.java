package com.hh.record.dto.auth.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleMemberInfoResponse {

    private String id;

    private String email;

    private String picture;

    private String name;

    @Builder
    public GoogleMemberInfoResponse(String id, String email, String picture, String name) {
        this.id = id;
        this.email = email;
        this.picture = picture;
        this.name = name;
    }

    public static GoogleMemberInfoResponse testInstance(String id, String email, String picture, String name) {
        return new GoogleMemberInfoResponse(id, email, picture, name);
    }

}
