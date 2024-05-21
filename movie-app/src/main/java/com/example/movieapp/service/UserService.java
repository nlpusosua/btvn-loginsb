package com.example.movieapp.service;

import com.example.movieapp.entity.User;
import com.example.movieapp.exception.BadRequestException;
import com.example.movieapp.model.request.UpdateUserPasswordRequest;
import com.example.movieapp.model.request.UpdateUserRequest;
import com.example.movieapp.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession session;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public void updatePassword(UpdateUserPasswordRequest userRequest) {
        User user = userRepository.findById(userRequest.getId())
                .orElseThrow(() -> new BadRequestException("Id lỗi"));

        // check xem password giống k
        if (!bCryptPasswordEncoder.matches(userRequest.getOPassword(), user.getPassword())) {
            throw new BadRequestException("mật khẩu cũ không đúng");
        }
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getNPassword()));
        userRepository.save(user);
//      lưu thông tin user vào session dể sử dụng ở các request tiếp theo
        session.setAttribute("currentUser", user);
    }
    public void updateUser(UpdateUserRequest userRequest) {
        User user = userRepository.findById(userRequest.getId())
                .orElseThrow(() -> new BadRequestException("Id lỗi"));
        user.setName(userRequest.getUsername());
        userRepository.save(user);
//      lưu thông tin user vào session dể sử dụng ở các request tiếp theo
        session.setAttribute("currentUser", user);
    }
}
