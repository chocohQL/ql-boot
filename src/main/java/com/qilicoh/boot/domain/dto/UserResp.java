package com.qilicoh.boot.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResp {
    private Long id;
    private String username;
    private String avatar;
    private String intro;
    private String email;
}
