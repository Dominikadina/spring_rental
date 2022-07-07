package pl.sda.arppl4.spring_wypozyczalnia.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.arppl4.spring_wypozyczalnia.service.CarService;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor

public class WynajemSamochoduController {
    private final RentalService rentalService;
}
