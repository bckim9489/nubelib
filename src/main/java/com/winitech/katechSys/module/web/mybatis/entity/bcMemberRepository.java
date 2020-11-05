package com.winitech.katechSys.module.web.mybatis.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface bcMemberRepository extends JpaRepository<bcMember, Integer>{

	public bcMember findByIdAndPassword(String id, String password);
}
