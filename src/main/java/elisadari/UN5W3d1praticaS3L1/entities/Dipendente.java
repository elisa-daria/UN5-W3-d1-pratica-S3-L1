package elisadari.UN5W3d1praticaS3L1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import elisadari.UN5W3d1praticaS3L1.enums.TypeOfDipendente;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="dipendenti")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"pw,typeOfDipendente,dispositivi"})
public class Dipendente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String pw;
    private TypeOfDipendente typeOfDipendente;
    private String profile_pic;
    @OneToMany(mappedBy="dipendente")
    private List<Dispositivo> dispositivi;

    public Dipendente(String username, String name, String surname, String email, String pw, TypeOfDipendente typeOfDipendente) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pw = pw;
        this.typeOfDipendente = TypeOfDipendente.USER;
    }
}
