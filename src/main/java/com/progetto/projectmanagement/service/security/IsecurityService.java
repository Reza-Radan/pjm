package com.progetto.projectmanagement.service.security;

import com.progetto.projectmanagement.domain.ResponseModel;

public interface IsecurityService {

    public ResponseModel createTokenByUserPasswordAuthentication(String userName);
}
