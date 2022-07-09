package pl.sda.arppl4.spring_wypozyczalnia.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.spring_wypozyczalnia.model.Samochod;
import pl.sda.arppl4.spring_wypozyczalnia.model.WynajemSamochodu;
import pl.sda.arppl4.spring_wypozyczalnia.model.dto.CarDTO;
import pl.sda.arppl4.spring_wypozyczalnia.model.dto.RentCarRequest;
import pl.sda.arppl4.spring_wypozyczalnia.service.CarService;
import pl.sda.arppl4.spring_wypozyczalnia.service.RentalService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/rental")
@RequiredArgsConstructor

public class WynajemSamochoduController {
    private final RentalService rentalService;

    @GetMapping("/available")
    public List<CarDTO> getAvailableCars() {
        log.info("Wywolano wypozyczalnie");
        return rentalService.getAvailableCars();
    }
    @PostMapping("/rent")
    public void rentCar(@RequestParam Long carId, @RequestBody RentCarRequest request) {
        log.info("Wywolano wypozyczalnie" + carId);
        rentalService.rentCar(carId, request);
    }
    @PatchMapping("/return")
    public void returnCar(@RequestParam Long carId){
        log.info("Wywolani zwrot samochodu o id: " + carId);
        rentalService.returnCar(carId);
    }

}

