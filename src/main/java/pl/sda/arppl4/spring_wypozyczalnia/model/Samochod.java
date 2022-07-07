package pl.sda.arppl4.spring_wypozyczalnia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    private Set<WynajemSamochodu> wynajemSamochodu;


}
