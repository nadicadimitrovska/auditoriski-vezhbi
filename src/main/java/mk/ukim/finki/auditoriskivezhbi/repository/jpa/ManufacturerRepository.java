package mk.ukim.finki.auditoriskivezhbi.repository.jpa;

import mk.ukim.finki.auditoriskivezhbi.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
}
