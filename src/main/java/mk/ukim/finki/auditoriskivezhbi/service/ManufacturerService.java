package mk.ukim.finki.auditoriskivezhbi.service;

import mk.ukim.finki.auditoriskivezhbi.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<Manufacturer> findAll();
    Optional<Manufacturer>findById(Long id);
}
