package com.idiots.openapi.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BizmRequestDto {
    private String msgid;
    private String message_type;
    private String sms_kind;
    private String receiver_num;
    private String sender_num;
    private String profile_key;
    private String template_code;
    private String message;
    private String sms_message;
    private String sms_only;
    private String sms_title;
    private String reserved_time;

    @Builder
    public BizmRequestDto(String msgid, String receiver_num, String profile_key, String template_code, String message, String sms_message, String sms_only, String sms_title) {
        this.msgid = msgid;
        this.message_type = "at";
        this.sms_kind = "L";
        this.receiver_num = receiver_num;
        this.sender_num = "031-628-0660";
        this.profile_key = profile_key;
        this.template_code = template_code;
        this.message = message;
        this.sms_message = sms_message;
        this.sms_only = sms_only;
        this.sms_title = sms_title;
        this.reserved_time = "00000000000000";
    }

}
