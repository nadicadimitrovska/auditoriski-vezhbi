package mk.ukim.finki.auditoriskivezhbi.service.impl;

import mk.ukim.finki.auditoriskivezhbi.model.Manufacturer;
import mk.ukim.finki.auditoriskivezhbi.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.auditoriskivezhbi.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

//    private final InMemoryManufacturerRepository manufacturerRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }
//    public ManufacturerServiceImpl(InMemoryManufacturerRepository manufacturerRepository) {
//        this.manufacturerRepository = manufacturerRepository;
//    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        return this.manufacturerRepository.findById(id);
    }

    @Override
//    public Optional<Manufacturer> save(String name, String address) {
//        return this.manufacturerRepository.save(name, address);
//    }
    public Optional<Manufacturer> save(String name, String address) {
        return Optional.of(this.manufacturerRepository.save(new Manufacturer(name,address)));
    }

    @Override
//    public boolean deleteById(Long id) {
//        return this.manufacturerRepository.deleteById(id);
//    }
    public void deleteById(Long id) {
         this.manufacturerRepository.deleteById(id);
    }
}
