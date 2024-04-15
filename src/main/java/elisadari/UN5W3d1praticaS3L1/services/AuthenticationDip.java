package elisadari.UN5W3d1praticaS3L1.services;

import elisadari.UN5W3d1praticaS3L1.entities.Dipendente;
import elisadari.UN5W3d1praticaS3L1.exceptions.NotFoundEx;
import elisadari.UN5W3d1praticaS3L1.exceptions.UnauthorizedEx;
import elisadari.UN5W3d1praticaS3L1.payloads.DipendenteLoginDTO;
import elisadari.UN5W3d1praticaS3L1.repositories.DipendenteDAO;
import elisadari.UN5W3d1praticaS3L1.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationDip {
    @Autowired
    DipendenteDAO dipendenteDAO;
    @Autowired
    JWTTools jwtTools;

    public String generateToken(DipendenteLoginDTO body){
        Dipendente dipendente=this.dipendenteDAO.findByEmail(body.email()).orElseThrow(()->new NotFoundEx ("nessun dipendente con questa email "+ body.email()));
        if(dipendente.getPw().equals(body.pw())){
            return jwtTools.createToken(dipendente);
        }else {
            throw new UnauthorizedEx("accesso negato, rifare il login");
        }
    }
}
