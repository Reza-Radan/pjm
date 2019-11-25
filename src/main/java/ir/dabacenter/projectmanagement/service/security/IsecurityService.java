package ir.dabacenter.projectmanagement.service.security;

import ir.dabacenter.projectmanagement.domain.ResponseModel;

public interface IsecurityService {

    public ResponseModel createTokenByUserPasswordAuthentication(String userName);
}
