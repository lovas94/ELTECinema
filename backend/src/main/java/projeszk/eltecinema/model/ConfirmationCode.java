package projeszk.eltecinema.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CONFIRMATION_CODES")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfirmationCode implements ModelInterface {

    @Id
    @Column(name = "ACTOR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Integer id;

    @Column(name = "USER_ID", nullable = false)
    @Getter @Setter Integer userId;

    @Column(name = "CODE", nullable = false)
    @Getter @Setter String code;
}
