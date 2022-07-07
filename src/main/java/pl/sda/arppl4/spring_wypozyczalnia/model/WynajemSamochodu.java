package pl.sda.arppl4.spring_wypozyczalnia.model;

import lombok.*;

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
    private LocalDateTime dataWynajmu;
    private LocalDateTime dataZwrotu;
    private Double cenaNajmu;


    @ManyToOne()
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Samochod samochod;


}
