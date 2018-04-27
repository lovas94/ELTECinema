package projeszk.eltecinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements ModelInterface, Serializable {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    @Getter @Setter private String username;

    @Column(name = "EMAIL", nullable = false, unique = true)
    @Getter @Setter private String email;

    @Column(name = "PASSWORD", nullable = false)
    @Getter @Setter private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    @Getter @Setter private Role role;

    @Column(name = "FULL_NAME", nullable = true)
    @Getter @Setter private String fullName;

    @Column(name = "ADDRESS", nullable = true)
    @Getter @Setter private String address;

    @Column(name = "PHONE_NUMBER", nullable = true)
    @Getter @Setter private String phoneNumber;

    @Column(name = "AGE", columnDefinition = "int default 0")
    @Getter @Setter private int age;

    @OneToMany(targetEntity = Reservation.class, mappedBy = "owner")
    @JsonIgnore
    @Getter @Setter private List<Reservation> reservationList;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }


    public enum Role {
        GUEST, USER, ADMIN
    }

    public User(String username, String email, String password, Role role, String fullName, String address, String phoneNumber, int age) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }
}
