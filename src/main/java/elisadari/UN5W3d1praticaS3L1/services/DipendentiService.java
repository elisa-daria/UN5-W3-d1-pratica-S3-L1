package elisadari.UN5W3d1praticaS3L1.services;


import elisadari.UN5W3d1praticaS3L1.entities.Dipendente;
import elisadari.UN5W3d1praticaS3L1.exceptions.BadRequestEx;
import elisadari.UN5W3d1praticaS3L1.exceptions.NotFoundEx;
import elisadari.UN5W3d1praticaS3L1.payloads.DipendenteRequestDTO;
import elisadari.UN5W3d1praticaS3L1.repositories.DipendenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.io.IOException;

@Service
public class DipendentiService {
    @Autowired
    private DipendenteDAO dipendenteDAO;


    //GENERIC GET
    public Page<Dipendente> getDipendenti(int page, int size, String sortBy){
        if(size>25)size=25;
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        return this.dipendenteDAO.findAll(pageable);
    }

    //GET BY ID
    public Dipendente findById(long id){
        return this.dipendenteDAO.findById(id).orElseThrow(()->new NotFoundEx(id));
    }

    //SAVE

    public Dipendente save(DipendenteRequestDTO payload) throws IOException{
        dipendenteDAO.findByEmail(payload.email()).ifPresent(dipendente -> {
            throw new BadRequestEx("utente registrto con email "+payload.email()+" già presente");
        });
        Dipendente newDipendente= new Dipendente();
        newDipendente.setName(payload.name());
        newDipendente.setSurname(payload.surname());
        newDipendente.setUsername(payload.username());
        newDipendente.setEmail(payload.email());
        return dipendenteDAO.save(newDipendente);
    }
    //DELETE
    public void delete(long id){
        Dipendente found=this.findById(id);
        dipendenteDAO.delete(found);
    }
    //UPDATE
    public Dipendente update(long id, Dipendente payload){
        Dipendente found=this.findById(id);
        found.setUsername(payload.getUsername());
        found.setName(payload.getName());
        found.setSurname(payload.getSurname());
        found.setEmail(payload.getEmail());
        return dipendenteDAO.save(found);
    }
    //GET BY EMAIl
    public Dipendente findByEmail(String email){
        return dipendenteDAO.findByEmail(email).orElseThrow(()->new NotFoundEx ("nessun dipendente con questa email "+ email));
    }

}
