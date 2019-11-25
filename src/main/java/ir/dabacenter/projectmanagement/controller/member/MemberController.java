package ir.dabacenter.projectmanagement.controller.member;

import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.domain.member.Member;
import ir.dabacenter.projectmanagement.security.UserPassSecurityModel;
import ir.dabacenter.projectmanagement.service.member.IMemberService;
import ir.dabacenter.projectmanagement.service.member.MemberService;
import ir.dabacenter.projectmanagement.service.security.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Service
@RestController
@RequestMapping("/member")
public class MemberController implements IMemberController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private IMemberService member;

    @Autowired
    private ResultModel resultModel;

    @Autowired
    private ResponseModel responseModel;

    @Autowired
    private MemberService memberService;

    @Autowired
    RequirementsProperties requirementsProperties;


    Logger logger = LoggerFactory.getLogger(this.getClass());
    private String tag = "MemberController";


    @RequestMapping(value = "/getMember", method = RequestMethod.POST)
    public ResponseModel getMember(@RequestParam UUID memberId,String lang, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        responseModel = memberService.getMember(memberId ,lang);
        return responseModel;
    }

//    @Override
    @RequestMapping(value = "/getMembers", method = RequestMethod.POST)
    public ResponseModel getMember(String lang , HttpServletResponse httpServletResponse) {
//        logger.warn(tag+" in getMember ");
        System.out.println(tag + " lang...............: " +lang);
        makeResponseClear();

        responseModel = memberService.getMembers(lang);
        responseModel.setStatus(httpServletResponse.getStatus());
        return responseModel;
    }

    @Override
    @RequestMapping(value = "/getMemberByProject", method = RequestMethod.POST)
    public ResponseModel getMemberByProject(UUID pId,HttpServletResponse httpServletResponse) {
        return null;
    }



//    @RequestMapping(value = "/test", method = RequestMethod.POST)
//    public ResponseModel getTest() throws UnsupportedEncodingException {
//        Locale locale = new Locale("fa", "persian");
//        ResourceBundle bundle4 = ResourceBundle.getBundle("language", locale);
//        String s = new String(bundle4.getString("REQUIREDFIELD").getBytes("UTF-8"), "UTF-8");
//        responseModel.setResult(s);
//        System.out.println("database error : "+responseModel.getStringUTF8(bundle4.getString("UNKNOWN_DATABASE_ERROR")));
//        return responseModel;
//    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseModel login(@RequestBody UserPassSecurityModel userNameModel , HttpServletResponse httpServletResponse) {
            makeResponseClear();
            logger.info(tag + " received user name and password in login : "+userNameModel.getUsername()+" pass "+userNameModel.getPassword());
            responseModel = memberService.login(userNameModel);
            if (responseModel.getResult().equalsIgnoreCase(requirementsProperties.getSuccessful())) {
                ResponseModel  responseModelSec = securityService.createTokenByUserPasswordAuthentication(userNameModel.getUsername());
                 if(responseModelSec.getError() == null){
                     responseModel.setContents(responseModelSec.getContents());
                     responseModel.setRecordCount(responseModelSec.getRecordCount());
                 }else{
                     responseModel.setError(responseModelSec.getError());
                     responseModel.setResult(requirementsProperties.getFail());
                 }
            }
            logger.info(tag + " result in login : "+responseModel.getResult());
            responseModel.setStatus(httpServletResponse.getStatus());

        return responseModel;
    }

    @Override
    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public ResponseModel addMember(@RequestBody Member member, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        logger.info(tag+" in addMember "+member.getPhoneNumber());

            responseModel = memberService.addMember(member);
            responseModel.setStatus(httpServletResponse.getStatus());
        return responseModel;
    }

    @Override
    @RequestMapping(value = "/changePass", method = RequestMethod.POST)
    public ResponseModel changePassword(@RequestParam UUID memberId,@RequestParam String password,@RequestParam String newPassword,String lang, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        resultModel = memberService.changePassword(memberId,password,newPassword,lang);
         if (resultModel.getError() == 0){
             responseModel.setResult(requirementsProperties.getSuccessful());
         }else {
             responseModel.setResult(requirementsProperties.getFail());
             responseModel.setError(resultModel.getResult());
         }
        responseModel.setStatus(httpServletResponse.getStatus());
        return responseModel;
    }


    @Override
    @RequestMapping(value = "/updateMember", method = RequestMethod.POST)
    public ResponseModel updateMember(@RequestBody Member member, HttpServletResponse httpServletResponse) {
        makeResponseClear();
        logger.info(tag+" in addMember "+member.getPhoneNumber());

        responseModel = memberService.updateMember(member);
        responseModel.setStatus(httpServletResponse.getStatus());
        return responseModel;
    }

    @Override
    @RequestMapping(value = "/deleteMember", method = RequestMethod.POST)
    public ResponseModel deleteMember(@RequestParam UUID memberId,String lang, HttpServletResponse httpServletResponse) {
        makeResponseClear();
       // UUID memID = UUID.fromString(memberId);
        logger.info(tag+" in delete member "+memberId+" lang : "+lang);

        responseModel = memberService.deleteMember(memberId,lang);/*------------------------delete test---------------------*/
        responseModel.setStatus(httpServletResponse.getStatus());
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
