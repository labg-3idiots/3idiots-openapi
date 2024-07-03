package com.idiots.openapi.service;

import com.idiots.openapi.dto.UserRequestDto;
import com.idiots.openapi.dto.UserResponseDto;
import com.idiots.openapi.entity.User;
import com.idiots.openapi.entity.VerificationData;
import com.idiots.openapi.exception.BaseExceptionStatus;
import com.idiots.openapi.exception.Exception400;
import com.idiots.openapi.exception.Exception404;
import com.idiots.openapi.exception.Exception500;
import com.idiots.openapi.exception.user.UserExceptionStatus;
import com.idiots.openapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final BizmService bizmService;

    private ConcurrentHashMap<String, VerificationData> verificationCodes = new ConcurrentHashMap<String, VerificationData>();

    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::of)
                .toList();
    }

    public UserResponseDto findById(Long userId) {
        return UserResponseDto.of(
                userRepository
                        .findById(userId)
                        .orElseThrow(() -> new Exception404(UserExceptionStatus.USER_NOT_FOUND))
        );
    }

    // 중복 검사 체크
    public void validateDuplicateEmail(String email) {
        if(userRepository.existsByEmail(email)){
            throw new Exception400(UserExceptionStatus.USER_ALREADY_EXIST);
        }
    }

    // 핸드폰 번호 중복 검사 체크
    private void validateDuplicatePhoneNumber(String phoneNumber) {
        if(userRepository.existsByPhoneNumber(phoneNumber)){
            throw new Exception400(UserExceptionStatus.PHONE_NUMBER_ALREADY_EXIST);
        }

        String verificationCode = getVerificationCode();
        long currentTime = System.currentTimeMillis();
        verificationCodes.put(phoneNumber, new VerificationData(verificationCode, currentTime));
    }

    // 6자리 난수 생성
    private String getVerificationCode() {
        Random random = new Random();

        StringBuilder randomCodes = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            randomCodes.append(random.nextInt(10));
        }
        return randomCodes.toString();
    }

    public UserResponseDto verifyPhoneNumber(UserRequestDto userRequestDto) {
        validateDuplicatePhoneNumber(userRequestDto.phoneNumber());


        User user = userRequestDto.toEntity();
        return UserResponseDto.of(user);
    }

    // 회원가입
    @Transactional
    public UserResponseDto create(UserRequestDto userRequestDto) {
        validateDuplicateEmail(userRequestDto.email());
        User user = userRequestDto.toEntity();
        user.userVerified();
        user.hashPassword(bCryptPasswordEncoder);

        try {
            userRepository.save(user);
            return UserResponseDto.of(user);
        } catch(Exception e) {
            e.printStackTrace();
            throw new Exception500(UserExceptionStatus.USER_SAVE_ERROR);
        }

    }

}
