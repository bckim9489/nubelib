package com.winitech.katechSys.module.web.mybatis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class bcMember {

	/*
	 * @EmbeddedId
	 * private bcMemberPK pk;
	 */
	@Id
	@SequenceGenerator(name           = "MEMBER_SEQ_GENERATOR", //name=식별자 생성기 이름
					   sequenceName   = "SEQ_MEMBER", 			//sequenceName=DB에 등록될 시퀀스이름
					   initialValue   = 1, 						//initialValue=최초시작하는 수
					   allocationSize = 1) 						//allocationSize=증가하는수
	@GeneratedValue(strategy  = GenerationType.SEQUENCE,
						generator = "MEMBER_SEQ_GENERATOR")
	@Column(name="mid", nullable=false)
	private int mid;

	@Column(nullable=false)
	private String id;

	@Column(nullable = false)

	private String name;

	@Column(nullable = false)

	private String password;


	private String age;


	private String email;


	private String address;

	@Column(insertable=false)
	@JsonProperty(value = "reg_date")
	private String regDate;

	@Builder
	public bcMember(String id, String name, String password, String age, String email, String address) {
		Assert.hasText(id, "id must not be empty");
	    Assert.hasText(name, "name must not be empty");
	    Assert.hasText(password, "password must not be empty");

		this.id = id;
		this.name = name;
		this.password = password;
		this.age = age;
		this.email = email;
		this.address = address;
	}
}
