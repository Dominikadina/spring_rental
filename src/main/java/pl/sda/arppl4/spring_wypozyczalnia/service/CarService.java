package pl.sda.arppl4.spring_wypozyczalnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.spring_wypozyczalnia.model.Samochod;
import pl.sda.arppl4.spring_wypozyczalnia.repository.CarRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
        private final CarRepository carRepository;

        public List<Samochod> getAllCars() {
                return carRepository.findAll();
        }

        public void addCar(Samochod samochod) {
                carRepository.save(samochod);
        }
}
