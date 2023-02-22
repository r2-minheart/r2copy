package com.r2comms.rclone.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass   // 이 어노테이션이 적용되면 테이블로 생성되지 않고
                    // BaseEntity 클래스를 상속한 엔티티의 클래스로 테이블이 생성
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {
    @CreatedDate
    @Column(name="create_date", updatable = false, nullable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name="modify_date", nullable = false)
    private LocalDateTime modifyDate;

    // others
    // @CreatedBy @LastModifiedBy
}
