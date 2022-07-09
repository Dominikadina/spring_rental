package pl.sda.arppl4.spring_wypozyczalnia.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.spring_wypozyczalnia.model.dto.CarDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Samochod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;
    private String marka;
    private LocalDate dataProdukcji;
    @Enumerated(EnumType.STRING)
    private TypNadwozia typNadwozia;
    private Integer ilośćMiejsc;
    @Enumerated(EnumType.STRING)
    private TypSkrzyni typSkrzyni;
    private Double pojemnoscSilnika;


    @OneToMany(mappedBy = "samochod", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    private Set<WynajemSamochodu> wynajemSamochodu;

    public CarDTO mapToCarDTO() {
        return new CarDTO(
                id,
                nazwa,
                marka,
                dataProdukcji,
                typNadwozia,
                ilośćMiejsc,
                typSkrzyni,
                pojemnoscSilnika
        );
    }
}
