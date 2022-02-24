package com.hh.record.controller.member;

import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.auth.AuthRequest;
import com.hh.record.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/google")
    public ApiResponse<String> googleAuthentication(@Valid @RequestBody AuthRequest request) {
        return ApiResponse.success(authService.googleAuthentication(request));
    }

    @GetMapping("login/oauth2/code/google")
    public String confirmCode(String code) {
        return code;
    }

}
