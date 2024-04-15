package elisadari.UN5W3d1praticaS3L1.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name="dispositivi")
@Data
public class Dispositivo {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String type_of_dispositvo;
    private String type_of_availability;
    @ManyToOne
    @JoinColumn(name="dipendente_id")
    private Dipendente dipendente;

}
