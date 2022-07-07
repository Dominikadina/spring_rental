package pl.sda.arppl4.spring_wypozyczalnia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arppl4.spring_wypozyczalnia.model.WynajemSamochodu;

public interface RentalRepository extends JpaRepository <WynajemSamochodu, Long> {
}
