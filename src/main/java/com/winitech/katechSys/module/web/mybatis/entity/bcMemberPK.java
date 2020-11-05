package com.winitech.katechSys.module.web.mybatis.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Embeddable
@SequenceGenerator(name           = "MEMBER_SEQ_GENERATOR", //name=식별자 생성기 이름
				   sequenceName   = "MEMBER_SEQ", 			//sequenceName=DB에 등록될 시퀀스이름
				   initialValue   = 1, 						//initialValue=최초시작하는 수
				   allocationSize = 1) 						//allocationSize=증가하는수
public class bcMemberPK implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy  = GenerationType.SEQUENCE,
					generator = "MEMBER_SEQ_GENERATOR")
	@Column(name="mid", nullable=false)
	private int mid;

	public bcMemberPK() {}

	public bcMemberPK(int mid, String id) {
		super();
		this.mid = mid;
	}
}
