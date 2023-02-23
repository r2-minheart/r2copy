package com.r2comms.r2copy.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = { "role"})
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long userId;

	@Column(length = 50, nullable = true, unique = false)
	private String loginId;

	@Column(length = 100, unique=true, nullable = false)
	private String fullNameEn;

	@Column(length = 100, nullable = false)
	private String password;

	// 유효 여부
	@Column(columnDefinition="boolean default true")
	private boolean isActive;
	
	private LocalDateTime lastLoginDate;

	// many to one
	// relation : role --> user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="roleId", nullable = false)
	private Role role;

	public void changeRole(Role role) {
		if (this.role != null) {
			this.role.getUserList().remove(this);
		}
		this.role = role;
		this.role.getUserList().add(this);
	}

    public void updateLastLoginTime(LocalDateTime _now) {
		this.lastLoginDate = _now;
    }
}
