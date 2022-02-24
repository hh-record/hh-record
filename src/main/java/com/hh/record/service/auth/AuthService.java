package com.hh.record.service.auth;

import com.hh.record.dto.auth.AuthRequest;

public interface AuthService {

    String googleAuthentication(AuthRequest request);

}
