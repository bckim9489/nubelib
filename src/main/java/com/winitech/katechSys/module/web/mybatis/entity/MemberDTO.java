package com.winitech.katechSys.module.web.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	private int mid;
	private String id;
	private String name;
	private String password;

	private String age;
	private String email;
	private String address;
	private String regDate;

	public bcMember toEntity() {
		return bcMember.builder()
				.id(id)
				.name(name)
				.password(password)
				.age(age)
				.email(email)
				.address(address)
				.build();
	}
}
