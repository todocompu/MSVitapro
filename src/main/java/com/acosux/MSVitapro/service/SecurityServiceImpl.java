/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.service;

import com.acosux.MSVitapro.security.TokenUtil;
import com.acosux.MSVitapro.util.Encriptacion;
import org.springframework.stereotype.Service;

/**
 * @author mario
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    final String usrVitapro = "adminvitapro";
    final String passVitapro = "e8aff578850c317bdc358b495dce983503996dabd73e7c23bce03591988b66f8";

    @Override
    public String getToken(String user, String password, int timeExpiredDay) throws Exception {
        Encriptacion encriptacion = new Encriptacion();
        TokenUtil tokenUtil = new TokenUtil();
        String clave = encriptacion.sha256(password);
        if (user.equals(usrVitapro) && clave.equals(passVitapro)) {
            return "Bearer " + tokenUtil.createTokenForUser(user, timeExpiredDay);
        }
        return "";
    }

}
