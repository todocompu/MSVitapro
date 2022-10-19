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
    final String passVitapro = "77d62939b00c38b450e66c5c18512069c9959bdc6331553181d96770a410997c";

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
