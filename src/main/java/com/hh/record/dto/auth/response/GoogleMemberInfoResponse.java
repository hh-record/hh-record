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

    @Builder
    public GoogleMemberInfoResponse(String id, String email, String picture) {
        this.id = id;
        this.email = email;
        this.picture = picture;
    }

}
