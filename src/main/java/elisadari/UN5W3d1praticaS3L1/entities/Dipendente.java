package elisadari.UN5W3d1praticaS3L1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import elisadari.UN5W3d1praticaS3L1.enums.TypeOfDipendente;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="dipendenti")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"pw", "typeOfDipendente", "dispositivi", "authorities","enabled", "password","accountNonExpired",
        "accountNonLocked",
        "credentialsNonExpired"})
public class Dipendente implements UserDetails {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String pw;
//    @Enumerated(EnumType.STRING)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.typeOfDipendente.name()));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
