package elisadari.UN5W3d1praticaS3L1.security;

import elisadari.UN5W3d1praticaS3L1.entities.Dipendente;
import elisadari.UN5W3d1praticaS3L1.exceptions.UnauthorizedEx;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${jwt.secret}")
    private String secret;
    public String createToken(Dipendente dipendente){
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date((System.currentTimeMillis()+1000*60*60*24*14)))
                .subject(String.valueOf(dipendente.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
    public void verifyToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedEx("Problemi col token! Per favore effettua di nuovo il login!");
        }

    }
}
