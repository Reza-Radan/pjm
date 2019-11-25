package ir.dabacenter.projectmanagement.controller.member;

import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.member.Member;
import ir.dabacenter.projectmanagement.security.UserPassSecurityModel;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public interface IMemberController {


    ResponseModel getMemberByProject(UUID pId,HttpServletResponse httpServletResponse);
    ResponseModel login(UserPassSecurityModel userNameModel,HttpServletResponse httpServletResponse);
    ResponseModel updateMember(Member member,HttpServletResponse httpServletResponse);
    ResponseModel addMember(Member member,HttpServletResponse httpServletResponse);
    ResponseModel getMember( String lang , HttpServletResponse httpServletResponse);
    ResponseModel getMember(UUID memberId, String lang, HttpServletResponse httpServletResponse);
    ResponseModel changePassword(UUID memberId, String oldPassword, String newpassword, String lang, HttpServletResponse httpServletResponse);
    ResponseModel deleteMember(UUID memberId, String lang, HttpServletResponse httpServletResponse);


}
