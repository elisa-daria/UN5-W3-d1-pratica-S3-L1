package elisadari.UN5W3d1praticaS3L1.controllers;


import elisadari.UN5W3d1praticaS3L1.entities.Dipendente;
import elisadari.UN5W3d1praticaS3L1.exceptions.BadRequestEx;
import elisadari.UN5W3d1praticaS3L1.payloads.DipendenteRequestDTO;
import elisadari.UN5W3d1praticaS3L1.payloads.DipendenteRespDTO;
import elisadari.UN5W3d1praticaS3L1.services.DipendentiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    DipendentiService dipendentiService;

    //http://localhost:3002/dipendenti
    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Dipendente> getDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "username") String sortBy
    ) {
        return dipendentiService.getDipendenti(page, size, sortBy);
    }


    //http://localhost:3002/dipendenti/{id}
    @GetMapping("/{id}")
    public Dipendente findById(@PathVariable long id) {
        return dipendentiService.findById(id);
    }
    @PutMapping("/{id}")
    public Dipendente updating(@PathVariable long id,@RequestBody Dipendente payload){
        return dipendentiService.update(id,payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleting(@PathVariable long id){
        dipendentiService.delete(id);
    }
}
