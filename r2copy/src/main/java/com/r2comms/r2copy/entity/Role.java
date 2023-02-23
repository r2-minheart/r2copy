package com.r2comms.r2copy.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="role")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "userList")
public class Role {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long roleId;
    
	@Column(length = 50, unique=true, nullable = false)
	private String roleName;
	
	private int roleOrder;
	
	// one to many
	// relation : role --> user
	@Builder.Default
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<User> userList = new ArrayList<User>();
}
