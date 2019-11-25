
package ir.dabacenter.projectmanagement.service.security;

import ir.dabacenter.projectmanagement.domain.RequirementsProperties;
import ir.dabacenter.projectmanagement.domain.ResponseModel;
import ir.dabacenter.projectmanagement.domain.ResultModel;
import ir.dabacenter.projectmanagement.security.SecurityModel;
import ir.dabacenter.projectmanagement.security.TokenUtil;
import ir.dabacenter.projectmanagement.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class SecurityService implements IsecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private ResultModel resultModel;

    @Autowired
    RequirementsProperties requirementsProperties;

    @Autowired
    private ResponseModel responseModel;

    List<SecurityModel> resultList;
    @Override
    public ResponseModel createTokenByUserPasswordAuthentication(String userName) {

        resultList = new ArrayList<>();
        responseModel.setError("");
        responseModel.setSystemError("");
        responseModel.setContents(null);
        responseModel.setRecordCount(0);

        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userName,
                    "1522");

            UserDetailServiceImpl userDetailsService = new UserDetailServiceImpl();
            final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            final String token = tokenUtil.generateToken(userDetails.getUsername(), userDetails.getPassword());

            System.out.println("\n generated token in SecurityService class : " + token);
            SecurityModel securityModel = new SecurityModel(token);

            resultList.add(securityModel);
            responseModel.setError(null);
            responseModel.setSystemError("");
            responseModel.setContents(resultList);
            responseModel.setRecordCount(resultList.size());

            return responseModel;

        } catch (IllegalArgumentException e) {
            responseModel.setError(requirementsProperties.fieldsNameMisake());
            responseModel.setSystemError("");
            responseModel.setRecordCount(resultList.size());

            return responseModel;

        } catch (Exception e) {
            System.out.println("error in generating token in SecurityService class ... " + e);
            responseModel.setError(e.toString());
            responseModel.setSystemError(e.toString());
            responseModel.setRecordCount(resultList.size());
            return responseModel;
        }
    }

    public String refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        UserDetailServiceImpl userDetailsService = new UserDetailServiceImpl();
        final String token = authToken.substring(7);
        String username = tokenUtil.getUsernameFromToken(token);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (!userDetails.getUsername().isEmpty()) {
            String refreshedToken = tokenUtil.refreshToken(token);
            return refreshedToken;
        } else {
            return null;
        }
    }
    public String getUserNameByToken(String token){
        return tokenUtil.getNameByToken();
    }
}
