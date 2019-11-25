package com.progetto.projectmanagement.service.member;

import com.progetto.projectmanagement.domain.member.Member;
import com.progetto.projectmanagement.domain.ResponseModel;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.security.UserPassSecurityModel;

import java.util.UUID;

public interface IMemberService {

	ResponseModel addMember(Member member);
	ResponseModel updateMember(Member member);
	ResponseModel getMembers(String lang);
	ResponseModel getMemberByProject(UUID pId, String lang);
	ResultModel changePassword(UUID memberId,String oldPassword,String newPassword,String lang);
	ResponseModel getMember(UUID memberId, String lang);
	ResultModel checkMember(String name);
	ResponseModel login(UserPassSecurityModel userNameModel );
	UUID getPermissionIdByMemberName(String userName);
	ResponseModel deleteMember(UUID memberId, String lang);
}