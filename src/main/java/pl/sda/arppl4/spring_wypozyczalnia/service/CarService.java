package pl.sda.arppl4.spring_wypozyczalnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.spring_wypozyczalnia.model.Samochod;
import pl.sda.arppl4.spring_wypozyczalnia.model.dto.CarDTO;
import pl.sda.arppl4.spring_wypozyczalnia.repository.CarRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<CarDTO> getAllCars() {
        List<Samochod> carList = carRepository.findAll();

        List<CarDTO> cars = new ArrayList<>();
        for (Samochod samochod : carList) {
            cars.add(samochod.mapToCarDTO());
        }

        return cars;
    }


    public void addCar(Samochod samochod) {
        carRepository.save(samochod);
    }

    public void updateCar(Samochod daneAktualizujace) {
        Long identifier = daneAktualizujace.getId();

        Optional<Samochod> samochodOptional = carRepository.findById(identifier);
        if (samochodOptional.isPresent()) {
            Samochod editedCar = samochodOptional.get();

            if (daneAktualizujace.getNazwa() != null) {
                editedCar.setNazwa(daneAktualizujace.getNazwa());
            }
            if (daneAktualizujace.getDataProdukcji() != null) {
                editedCar.setDataProdukcji(daneAktualizujace.getDataProdukcji());
            }
            if (daneAktualizujace.getTypNadwozia() != null) {
                editedCar.setTypNadwozia(daneAktualizujace.getTypNadwozia());
            }
            if (daneAktualizujace.getIlośćMiejsc() != null) {
                editedCar.setIlośćMiejsc(daneAktualizujace.getIlośćMiejsc());
            }
            if (daneAktualizujace.getTypSkrzyni() != null) {
                editedCar.setTypSkrzyni(daneAktualizujace.getTypSkrzyni());
            }
            if (daneAktualizujace.getPojemnoscSilnika() != null) {
                editedCar.setPojemnoscSilnika(daneAktualizujace.getPojemnoscSilnika());
            }
            carRepository.save(editedCar);
            log.info("Samochod został zaktualizowany.");
            return;
        }
        throw new EntityNotFoundException("Nie znaleziono samochodu o id: " + daneAktualizujace.getId());
    }

    public void deleteById(Long identyfikator) {
        carRepository.deleteById(identyfikator);
    }
}
