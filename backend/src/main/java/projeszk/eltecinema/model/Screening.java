package projeszk.eltecinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "SCREENING")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Screening implements ModelInterface, Comparable<Screening>, Serializable {

    @Id
    @Column(name = "SCREENING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Integer id;

    @JoinColumn
    @ManyToOne(targetEntity = Movie.class, optional = true)
    @JsonIgnoreProperties("screenings")
    @Getter @Setter private Movie movie;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="ROOM_ID", unique= false, nullable=true, insertable=true, updatable=true)
    @Getter @Setter private CinemaRoom cinemaRoom;

    @Column(name = "START_TIME", nullable = false)
    @Getter @Setter private Date startTime;

    @Column(name = "END_TIME", nullable = false)
    @Getter @Setter private Date endTime;

    @OneToMany(mappedBy = "screening")
    @JsonIgnore
    @Getter @Setter private List<Reservation> reservations;

    @Override
    public int compareTo(Screening otherScreening) {
        return this.getEndTime().compareTo(otherScreening.getStartTime());
    }

    public boolean screeningOverLapsWith(Screening otherScreening) {
        return this.compareTo(otherScreening) >= 1;
    }

    public Screening(Movie movie, Date startTime, Date endTime) {
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}