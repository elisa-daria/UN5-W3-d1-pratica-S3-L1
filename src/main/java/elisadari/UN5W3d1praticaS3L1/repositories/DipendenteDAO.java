package elisadari.UN5W3d1praticaS3L1.repositories;

import elisadari.UN5W3d1praticaS3L1.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteDAO extends JpaRepository<Dipendente,Long> {
    Optional<Dipendente> findByEmail(String email);
}
