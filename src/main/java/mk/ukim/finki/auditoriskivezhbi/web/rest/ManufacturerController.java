package mk.ukim.finki.auditoriskivezhbi.web.rest;

import mk.ukim.finki.auditoriskivezhbi.model.Manufacturer;
import mk.ukim.finki.auditoriskivezhbi.service.ManufacturerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll(){
        return this.manufacturerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id){
        return this.manufacturerService.findById(id).map(manufacturer -> ResponseEntity.ok().body(manufacturer)).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Manufacturer> save(@RequestParam String name, @RequestParam String address){
        return this.manufacturerService.save(name, address).map(manufacturer -> ResponseEntity.ok().body(manufacturer)).orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Manufacturer> deleteById(Long id){
//        if (this.manufacturerService.deleteById(id))
//            return ResponseEntity.ok().build();
//        return ResponseEntity.badRequest().build();
//    }
    public ResponseEntity<Manufacturer> deleteById(Long id){
        this.manufacturerService.deleteById(id);
        if (this.manufacturerService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
