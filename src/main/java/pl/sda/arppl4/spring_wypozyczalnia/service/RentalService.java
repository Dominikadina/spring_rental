package pl.sda.arppl4.spring_wypozyczalnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.spring_wypozyczalnia.model.Samochod;
import pl.sda.arppl4.spring_wypozyczalnia.model.WynajemSamochodu;
import pl.sda.arppl4.spring_wypozyczalnia.repository.CarRepository;
import pl.sda.arppl4.spring_wypozyczalnia.repository.RentalRepository;

import java.util.ArrayList;
import java.util.List;

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
            if(isRented(samochod)){
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
}
