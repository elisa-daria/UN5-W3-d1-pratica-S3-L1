package elisadari.UN5W3d1praticaS3L1.controllers;

import elisadari.UN5W3d1praticaS3L1.entities.Dipendente;
import elisadari.UN5W3d1praticaS3L1.exceptions.BadRequestEx;
import elisadari.UN5W3d1praticaS3L1.payloads.DipendenteLoginDTO;
import elisadari.UN5W3d1praticaS3L1.payloads.DipendenteRequestDTO;
import elisadari.UN5W3d1praticaS3L1.payloads.DipendenteRespDTO;
import elisadari.UN5W3d1praticaS3L1.services.AuthenticationDip;
import elisadari.UN5W3d1praticaS3L1.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationDip ad;
    @Autowired
    DipendentiService dipendentiService;

    @PostMapping("/login")
    public DipendenteRespDTO login(@RequestBody DipendenteLoginDTO body){
        return new DipendenteRespDTO(this.ad.generateToken(body));
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteRespDTO savingD(@RequestBody @Validated DipendenteRequestDTO payload, BindingResult validation)throws Exception{
        if(validation.hasErrors()){
            throw new BadRequestEx(validation.getAllErrors());
        }
        Dipendente newD=dipendentiService.save(payload);
        return new DipendenteRespDTO(String.valueOf(newD.getId()));
    }

}
