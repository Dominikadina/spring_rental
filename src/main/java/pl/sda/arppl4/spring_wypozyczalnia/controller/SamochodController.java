package pl.sda.arppl4.spring_wypozyczalnia.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.spring_wypozyczalnia.model.Samochod;
import pl.sda.arppl4.spring_wypozyczalnia.service.CarService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/car")
@RequiredArgsConstructor
public class SamochodController {
    private final CarService carService;

    @GetMapping("/list")
    public List<Samochod> getAllCars() {
        log.info("Wywolano liste samochodow");
        List<Samochod> list = carService.getAllCars();
    }

    @PostMapping("/add")
            public void addCar(@RequestBody Samochod samochod){


    log.info("Wywolano metode dodania samochodu:" + samochod);
    carService.addCar(samochod);
}
}