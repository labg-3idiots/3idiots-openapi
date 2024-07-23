package com.idiots.openapi.service;

import com.idiots.openapi.dto.BizmRequestDto;
import com.idiots.openapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BizmService {
    private final UserRepository userRepository;

    @Value("${bizm.host}")
    private String host;

    @Value("${bizm.endPoint}")
    private String endPoint;

    @Value("${bizm.profileKey}")
    private String profileKey;


    private final WebClient webClient;

    public void sendVerificationCode(String phoneNumber, String code) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddhhmmss");
        String formattedDateTime = currentDateTime.format(formatter);

        BizmRequestDto request = BizmRequestDto.builder()
                .msgid(formattedDateTime + phoneNumber.substring(3))
                .receiver_num(phoneNumber)
                .profile_key(profileKey)
                .template_code("PGS0004")
                .sms_message(makeVerificationCodeText(code))
                .sms_title("인증번호 발송")
                .sms_only("Y")
                .build();

        List<BizmRequestDto> requestList = new ArrayList<>();
        requestList.add(request);

        String result = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host(host)
                        .path(endPoint)
                        .build())
                .header("userid", "labgenomics")
                .bodyValue(requestList)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        log.info("result : {}", result);
    }

    public void sendKakaoTalk(String phoneNumber, String message) {

    }

    private String makeVerificationCodeText(String code) {
        return String.format("[3idiots]본인확인\n인증번호(%s)를 정확히 입력해주세요.", code);
    }
}
