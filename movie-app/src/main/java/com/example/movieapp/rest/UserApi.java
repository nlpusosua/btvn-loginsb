package com.example.movieapp.rest;

import com.example.movieapp.model.request.UpdateUserPasswordRequest;
import com.example.movieapp.model.request.UpdateUserRequest;
import com.example.movieapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;


    // Sửa tt user
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUserRequest request) {
        userService.updateUser(request);
        return ResponseEntity.ok("update successfully");
    }

    // Sửa pass
    @PutMapping("/update/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdateUserPasswordRequest request) {
        userService.updatePassword(request);
        return ResponseEntity.ok("update successfully");
    }

}
