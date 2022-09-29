package com.acosux.MSVitapro.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import javax.servlet.http.*;
import java.util.*;

@Service
public class TokenUtil {

    private static final String AUTH_HEADER_NAME = "Authorization";

    private final String secret = "mrin";

    public Optional<Authentication> verifyToken(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null && !token.isEmpty()) {
            final TokenUser user = parseUserFromToken(token.replace("Bearer", "").trim());
            if (user != null) {
                return Optional.of(new UserAuthentication(user));
            }
        }
        return Optional.empty();
    }

    public TokenUser parseUserFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return new TokenUser((String) claims.get("userId"), (String) claims.get("sub"));
    }

}
