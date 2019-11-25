package com.progetto.projectmanagement.service.member;

import com.progetto.projectmanagement.domain.RequirementsProperties;
import com.progetto.projectmanagement.domain.ResultModel;
import com.progetto.projectmanagement.domain.member.Member;
import com.progetto.projectmanagement.domain.member.MemberRepository;
import com.progetto.projectmanagement.security.UserPassSecurityModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class MemberValidator {

    @Autowired
    ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    MemberRepository memberRepository;


    public ResultModel memberValidate(Member member){

        if(!validateField(member.getName())) {
            resultModel.setError(1);
            resultModel.setResult(" name " + resultModel.getErrorTextByLanguage(member.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(member.getFamily())){
            resultModel.setError(1);
            resultModel.setResult(" family " + resultModel.getErrorTextByLanguage(member.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(member.getPhoneNumber())){
            resultModel.setError(1);
            resultModel.setResult(" phoneNumber " + resultModel.getErrorTextByLanguage(member.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(member.getEmail())){
            resultModel.setError(1);
            resultModel.setResult(" email " + resultModel.getErrorTextByLanguage(member.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(member.getUserName())) {
            resultModel.setError(1);
            resultModel.setResult(" username " + resultModel.getErrorTextByLanguage(member.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(member.getRoleId())) {
            resultModel.setError(1);
            resultModel.setResult(" roleId " + resultModel.getErrorTextByLanguage(member.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(member.getWorkingtype())) {
            resultModel.setError(1);
            resultModel.setResult(" wroking type " + resultModel.getErrorTextByLanguage(member.getLang(),"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel getMemberByProjectValidate(UUID pId,String lang){

        if(!validateField(pId)) {
            resultModel.setError(1);
            resultModel.setResult(" pId " + resultModel.getErrorTextByLanguage(lang,"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel loginValidate(UserPassSecurityModel userNameModel){

        if(!validateField(userNameModel.getUsername())) {
            resultModel.setError(1);
            resultModel.setResult(" username " + resultModel.getErrorTextByLanguage(userNameModel.getLang(),"REQUIREDFIELD"));

        }else if(!validateField(userNameModel.getPassword())){
            resultModel.setError(1);
            resultModel.setResult(" password " + resultModel.getErrorTextByLanguage(userNameModel.getLang(),"REQUIREDFIELD"));

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }


    public ResultModel checkMemberExistence(String userName){

        if(memberRepository.checkMember(userName)) {
            resultModel.setError(1);
            resultModel.setResult(requirementsProperties.getUserExistence());

        }else  {
            resultModel.setResult(requirementsProperties.getSuccessful());
            resultModel.setError(0);
        }

        return resultModel;
    }

    public <T> boolean validateField(T field){
        if(field!=null)
            if (field=="")
                return false;
            else
                return true;
        else
            return false;

    }
}
