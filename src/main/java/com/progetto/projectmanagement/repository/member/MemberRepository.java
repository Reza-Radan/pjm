package com.progetto.projectmanagement.repository.member;

import com.progetto.projectmanagement.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
}
