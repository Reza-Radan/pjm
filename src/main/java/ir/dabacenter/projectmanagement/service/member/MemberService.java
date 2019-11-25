package ir.dabacenter.projectmanagement.service.member;

import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.member.Member;
import ir.dabacenter.projectmanagement.domain.member.MemberRepository;
import ir.dabacenter.projectmanagement.security.UserPassSecurityModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class MemberService implements IMemberService {

	private String tag = "MemberService";
	Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	ResultModel resultModel, resultModelCheckExistence, resultModelDB;

	@Autowired
	ResponseModel responseModel;

	@Autowired
	MemberValidator memberValidator;

	@Autowired
	RequirementsProperties requirementsProperties;


	@Override
	public ResponseModel addMember(Member member) {

		resultModel = memberValidator.memberValidate(member);
		logger.info("resultModel " + resultModel.getError());
		if (resultModel.getError() == 0) {
			resultModelCheckExistence = checkMember(member.getUserName());
			if (resultModelCheckExistence.getError() == 0) {
				resultModelDB = memberRepository.addMember(member, false);
				if (resultModelDB.getError() == 0)
					responseModel.setResult(requirementsProperties.getSuccessful());
				else {
					responseModel.setResult(requirementsProperties.getFail());
					responseModel.setSystemError(resultModelDB.getResult());
				}
			} else {
					/*
				     user existence error
				    */
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setError(resultModelCheckExistence.getResult());
			}
		} else {
		   	 /*
		   	  Validation error
		   	  */
			responseModel.setResult(requirementsProperties.getFail());
			responseModel.setError(resultModel.getResult());
		}

		return responseModel;
	}


	@Override
	public ResponseModel updateMember(Member member) {

		resultModel = memberValidator.memberValidate(member);
		logger.info("resultModel " + resultModel.getError());
		if (resultModel.getError() == 0) {
			resultModelDB = memberRepository.addMember(member, true);
			if (resultModelDB.getError() == 0)
				responseModel.setResult(requirementsProperties.getSuccessful());
			else {
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setSystemError(resultModelDB.getResult());
				responseModel.setError(resultModelDB.getResult());
			}

		} else {
		   	 /*
		   	  Validation error
		   	  */
			responseModel.setResult(requirementsProperties.getFail());
			responseModel.setError(resultModel.getResult());
		}

		return responseModel;
	}

	@Override
	public ResponseModel getMembers(String lang) {
		responseModel = memberRepository.getMembers(lang);
		return responseModel;
	}

	@Override
	public ResultModel changePassword(UUID memberId,String password,String newPassword,String lang) {
		return memberRepository.changePassword(memberId,password,newPassword,lang);
	}


	@Override
	public ResponseModel getMemberByProject(UUID pId ,String lang) {
		return null;
	}


	@Override
	public ResponseModel getMember(UUID memberId,String lang) {
		logger.error("getMembermemberId: " +memberId);
		resultModel = memberValidator.getMemberByProjectValidate(memberId,lang);
		if(resultModel.getError() == 0) {
//			resultModelDB = memberRepository.deleteAccount(memberId, lang);
//			if (resultModelDB.getError() == 0)
			responseModel = memberRepository.getMembersById(memberId, lang);
		}else {
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setError(resultModelDB.getResult());
				responseModel.setSystemError(resultModelDB.getResult());
			}


		return responseModel;
	}

	@Override
	public ResultModel checkMember(String name) {
		return memberValidator.checkMemberExistence(name);
	}

	@Override
	public ResponseModel login(UserPassSecurityModel userNameModel ) {

		makeResponseClear();
		resultModel = memberValidator.loginValidate(userNameModel);
		 if(resultModel.getError() == 0){
		 	  if(memberRepository.loginMember(userNameModel)){
				  responseModel.setResult(requirementsProperties.getSuccessful());
			  }else {
				  responseModel.setResult(requirementsProperties.getFail());
				  responseModel.setError(resultModel.getErrorTextByLanguage(userNameModel.getLang(), "WRONGUSERPASS"));
			  }

		 }else{
		 	responseModel.setError(resultModel.getResult());
		 	responseModel.setResult(requirementsProperties.getFail());
		 }
		return responseModel;
	}

	@Override
	public UUID getPermissionIdByMemberName(String userName ) {
		return memberRepository.getPermissionIdByMemberName(userName);
	}


	@Override
	public ResponseModel deleteMember(UUID memberId, String lang) {

		resultModel = memberValidator.getMemberByProjectValidate(memberId,lang);
		logger.info("resultModel "+resultModel.getError());
		if(resultModel.getError() == 0) {
			resultModelDB = memberRepository.deleteAccount(memberId,lang);
			if(resultModelDB.getError() == 0 )
				responseModel.setResult(requirementsProperties.getSuccessful());
			else {
				responseModel.setResult(requirementsProperties.getFail());
				responseModel.setError(resultModelDB.getResult());
				responseModel.setSystemError(resultModelDB.getResult());
			}

		}else{
		   	 /*
		   	  Validation error
		   	  */
			responseModel.setResult(requirementsProperties.getFail());
			responseModel.setError(resultModel.getResult());
		}

		return responseModel;
	}

	public void makeResponseClear(){
		try {
			responseModel.setContents(null);
			responseModel.setContent(null);
			responseModel.setSystemError("");
			responseModel.setError("");
			responseModel.setRecordCount(0);
		}catch (Exception e){
			System.out.println(" Error in makeResponseClear "+e);
		}
	}

}