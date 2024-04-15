package elisadari.UN5W3d1praticaS3L1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="dipendenti")
@Data
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
    private String profile_pic;
    @OneToMany(mappedBy="dipendente")
    @JsonIgnore
    private List<Dispositivo> dispositivi;

}
