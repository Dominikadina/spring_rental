package pl.sda.arppl4.spring_wypozyczalnia.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arppl4.spring_wypozyczalnia.repository.RentalRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
}
