package pl.sda.arppl4.spring_wypozyczalnia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class WynajemSamochodu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;
    private String nazwisko;
    @CreationTimestamp
    private LocalDateTime dataWynajmu;
    private LocalDateTime dataZwrotu;
    private Double cenaNajmu;


    @ManyToOne()
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    private Samochod samochod;

    public WynajemSamochodu(String imie, String nazwisko, Double cenaNajmu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.cenaNajmu = cenaNajmu;
    }
}
