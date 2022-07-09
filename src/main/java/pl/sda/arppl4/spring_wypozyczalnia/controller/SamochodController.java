package pl.sda.arppl4.spring_wypozyczalnia.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.spring_wypozyczalnia.model.Samochod;
import pl.sda.arppl4.spring_wypozyczalnia.model.dto.CarDTO;
import pl.sda.arppl4.spring_wypozyczalnia.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class SamochodController {
    private final CarService carService;

    @GetMapping("/list")
    public List<CarDTO> getAllCars() {
        log.info("Wywolano liste samochodow");
        List<CarDTO> list = carService.getAllCars();
        return list;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCar(@RequestBody Samochod samochod) {
        log.info("Wywolano metode dodania samochodu:" + samochod);
        carService.addCar(samochod);
    }

    @PatchMapping("/update")
    public void updateCar(@RequestBody Samochod samochod) {
        log.info("Wywolao aktualizacje samochodu:" + samochod);
        carService.updateCar(samochod);
    }

    @DeleteMapping("/delete/{identifier}")
    public void deleteCar(@PathVariable(name = "identifier") Long identyfikator) {
        log.info("Wywolao usineicie samochodu: " + identyfikator);
        carService.deleteById(identyfikator);
    }
}