package pl.sda.arppl4.spring_wypozyczalnia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class WynajemSamochodu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private String imieINazwisko;
private LocalDateTime dataWynajmu;
private LocalDateTime dataZwrotu;
private Double cenaNajmu;
}
