package ir.dabacenter.projectmanagement.service.member;

import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.member.Member;
import ir.dabacenter.projectmanagement.security.UserPassSecurityModel;

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