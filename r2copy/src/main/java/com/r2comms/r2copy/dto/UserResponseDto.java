package com.r2comms.r2copy.dto;

import com.r2comms.r2copy.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserResponseDto {
    private Long userId;
    private String fullNameEn;
    private String password;
    private boolean isActive;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private LocalDateTime lastLoginDate;

    private void toUser(User entity) {
        this.userId = entity.getUserId();
        this.fullNameEn = entity.getFullNameEn();
        this.password = entity.getPassword();
        this.isActive = entity.isActive();

        this.createDate = entity.getCreateDate();
        this.modifyDate = entity.getModifyDate();
        this.lastLoginDate = entity.getLastLoginDate();
    }

    public UserResponseDto(User entity) {
        this.toUser(entity);
    }
}
