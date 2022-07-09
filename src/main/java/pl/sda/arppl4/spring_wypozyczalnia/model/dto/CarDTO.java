package pl.sda.arppl4.spring_wypozyczalnia.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.arppl4.spring_wypozyczalnia.model.TypNadwozia;
import pl.sda.arppl4.spring_wypozyczalnia.model.TypSkrzyni;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Long carId;
    private String name;
    private String make;
    private LocalDate productionDate;
    private TypNadwozia bodyType;
    private Integer seats;
    private TypSkrzyni carGearBox;
    private Double engineCapacity;
}

