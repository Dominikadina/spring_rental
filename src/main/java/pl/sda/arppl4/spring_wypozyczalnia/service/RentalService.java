package pl.sda.arppl4.spring_wypozyczalnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.spring_wypozyczalnia.exception.CarNotAvailableException;
import pl.sda.arppl4.spring_wypozyczalnia.model.Samochod;
import pl.sda.arppl4.spring_wypozyczalnia.model.WynajemSamochodu;
import pl.sda.arppl4.spring_wypozyczalnia.model.dto.CarDTO;
import pl.sda.arppl4.spring_wypozyczalnia.model.dto.RentCarRequest;
import pl.sda.arppl4.spring_wypozyczalnia.repository.CarRepository;
import pl.sda.arppl4.spring_wypozyczalnia.repository.RentalRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;

    public List<CarDTO> getAvailableCars() {
        List<Samochod> carList = carRepository.findAll();

        List<CarDTO> samochody = new ArrayList<>();
        for (Samochod samochod : carList) {
            if (!isRented(samochod)) {

                samochody.add(samochod.mapToCarDTO());
            }
        }
        return samochody;
    }

    private boolean isRented(Samochod samochod) {
        for (WynajemSamochodu wynajemSamochodu : samochod.getWynajemSamochodu()) {
            if (wynajemSamochodu.getDataZwrotu() == null) {
                return true;

            }
        }
        return false;
    }

    public void rentCar(Long carId, RentCarRequest request) {
        Optional<Samochod> optionalSamochod = carRepository.findById(carId);
        if (optionalSamochod.isPresent()) {
            Samochod samochod = optionalSamochod.get();
            if (!isRented(samochod)) {
                WynajemSamochodu wynajemSamochodu = mapRentCarRequestToWynajem(request);
                wynajemSamochodu.setSamochod(samochod);
                rentalRepository.save(wynajemSamochodu);
                return;
            }
            throw new CarNotAvailableException("Samochod nie jest dostepny do wynajmu, id: " + carId);
        }
        throw new EntityNotFoundException("Nie mozna odnalezc samochodu o podanym Id: " + carId);
    }

    private WynajemSamochodu mapRentCarRequestToWynajem(RentCarRequest request) {
        return new WynajemSamochodu(
                request.getNameOfTheClient(),
                request.getSurnameOfTheClient(),
                request.getHourlyPrice());
    }

    public void returnCar(Long carId) {
        Optional<Samochod> optionalSamochod = carRepository.findById(carId);
        if (optionalSamochod.isPresent()) {
            Samochod samochod = optionalSamochod.get();

            Optional<WynajemSamochodu> optionalWynajemSamochodu = findActiveCarRental(samochod);
            if (optionalWynajemSamochodu.isPresent()) {
                WynajemSamochodu wynajemSamochodu = optionalWynajemSamochodu.get();

                wynajemSamochodu.setDataZwrotu(LocalDateTime.now());
                rentalRepository.save(wynajemSamochodu);
                return;
            }
            throw new CarNotAvailableException("Samochod nie jest dostepny do wynajmu, id: " + carId);
        }
        throw new EntityNotFoundException("Nie mozna odnalezc samochodu o podanym Id: " + carId);

    }

    private Optional<WynajemSamochodu> findActiveCarRental(Samochod samochod) {
        for (WynajemSamochodu wynajemSamochodu : samochod.getWynajemSamochodu()) {
            if (wynajemSamochodu.getDataZwrotu() == null) {
                return Optional.of(wynajemSamochodu);
            }
        }
        return Optional.empty();
    }
}
