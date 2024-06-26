package elisadari.UN5W3d1praticaS3L1.services;

import elisadari.UN5W3d1praticaS3L1.entities.Dipendente;
import elisadari.UN5W3d1praticaS3L1.exceptions.NotFoundEx;
import elisadari.UN5W3d1praticaS3L1.exceptions.UnauthorizedEx;
import elisadari.UN5W3d1praticaS3L1.payloads.DipendenteLoginDTO;
import elisadari.UN5W3d1praticaS3L1.repositories.DipendenteDAO;
import elisadari.UN5W3d1praticaS3L1.security.JWTTools;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationDip {
    @Autowired
    DipendentiService dipendentiService;
    @Autowired
    JWTTools jwtTools;
    @Autowired
    PasswordEncoder passwordEncoder;

    public String generateToken(DipendenteLoginDTO body){
        Dipendente dipendente=this.dipendentiService.findByEmail(body.email());
        if(passwordEncoder.matches(body.pw(),dipendente.getPw())){
            return jwtTools.createToken(dipendente);
        }else {
            throw new UnauthorizedEx("accesso negato, rifare il login");
        }
    }

}
