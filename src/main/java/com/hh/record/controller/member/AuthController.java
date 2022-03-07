package com.hh.record.controller.member;

import com.hh.record.controller.ApiResponse;
import com.hh.record.dto.auth.AuthRequest;
import com.hh.record.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("hh-record-intro")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/google/login")
    public ApiResponse<String> googleAuthentication(@Valid @RequestBody AuthRequest request) {
        return ApiResponse.success(authService.googleAuthentication(request));
    }

    @GetMapping("login/oauth2/code/google")
    public String confirmCode(String code) {
        return code;
    }

}
