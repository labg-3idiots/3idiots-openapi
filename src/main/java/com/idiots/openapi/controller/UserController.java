package com.idiots.openapi.controller;

import com.idiots.openapi.service.UserService;
import com.idiots.openapi.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    /**
     * 사용자 전체 조회
     * @Parameter
     * @return 전체 사용자 리스트
     */
    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(ApiUtils.success(userService.findAll()));
    }

    /**
     * 특정 사용자 조회
     * @Parameter
     *      user_id : 조회할 사용자 pk(pathvariable)
     * @return 조회한 사용자 정보
     */
    @GetMapping("/{user_id}")
    public ResponseEntity<?> findById(
            @PathVariable("user_id") Long userId
    ) {

        return ResponseEntity.ok(ApiUtils.success(userService.findById(userId)));
    }
}