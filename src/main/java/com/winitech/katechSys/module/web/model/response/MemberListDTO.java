package com.winitech.katechSys.module.web.model.response;

import com.winitech.katechSys.module.web.mybatis.entity.bcMember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberListDTO {
	private int mid;
	private String id;
	private String name;

	private String age;
	private String email;
	private String address;
	private String regDate;

	public bcMember toEntity() {
		return bcMember.builder()
				.id(id)
				.name(name)
				.age(age)
				.email(email)
				.address(address)
				.build();
	}
}
