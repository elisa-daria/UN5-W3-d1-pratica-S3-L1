package elisadari.UN5W3d1praticaS3L1.controllers;

import elisadari.UN5W3d1praticaS3L1.payloads.DipendenteLoginDTO;
import elisadari.UN5W3d1praticaS3L1.payloads.DipendenteRespDTO;
import elisadari.UN5W3d1praticaS3L1.services.AuthenticationDip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationDip ad;

    @PostMapping("/login")
    public DipendenteRespDTO login(@RequestBody DipendenteLoginDTO body){
        return new DipendenteRespDTO(this.ad.generateToken(body));
    }
}
