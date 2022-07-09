package pl.sda.arppl4.spring_wypozyczalnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.spring_wypozyczalnia.exception.CarNotAvailableException;
import pl.sda.arppl4.spring_wypozyczalnia.model.Samochod;
import pl.sda.arppl4.spring_wypozyczalnia.model.WynajemSamochodu;
import pl.sda.arppl4.spring_wypozyczalnia.repository.CarRepository;
import pl.sda.arppl4.spring_wypozyczalnia.repository.RentalRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;

    public List<Samochod> getAvailableCars() {
        List<Samochod> carList = carRepository.findAll();

        List<Samochod> samochody = new ArrayList<>();
        for (Samochod samochod: carList) {
            if(!isRented(samochod)){
                samochody.add(samochod);
            }
        }
        return samochody;
    }
    private boolean isRented(Samochod samochod){
        for(WynajemSamochodu wynajemSamochodu: samochod.getWynajemSamochodu()){
            if(wynajemSamochodu.getDataZwrotu() ==null){
                return true;

            }
        }
return false;
    }

    public void rentCar(Long carId, WynajemSamochodu wynajemSamochodu) {
        Optional<Samochod> optionalSamochod = carRepository.findById(carId);
        if(optionalSamochod.isPresent()){
            Samochod samochod = optionalSamochod.get();
            if(!isRented(samochod)){
                WynajemSamochodu stworzonyNowyWynajem = new WynajemSamochodu();
                stworzonyNowyWynajem.setImie(stworzonyNowyWynajem.getImie());
                stworzonyNowyWynajem.setNazwisko(stworzonyNowyWynajem.getNazwisko());
                stworzonyNowyWynajem.setCenaNajmu(stworzonyNowyWynajem.getCenaNajmu());
                stworzonyNowyWynajem.setSamochod(samochod);

                rentalRepository.save(stworzonyNowyWynajem);
                return;
            }
        throw new CarNotAvailableException("Samochod nie jest dostepny do wynajmu, id: " + carId);
        }
        throw new EntityNotFoundException("Nie mozna odnalezc samochodu o podanym Id: " + carId);
    }

}
