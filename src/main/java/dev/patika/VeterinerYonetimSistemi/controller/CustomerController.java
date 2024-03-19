package dev.patika.VeterinerYonetimSistemi.controller;
import dev.patika.VeterinerYonetimSistemi.dto.request.CustomerRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.CustomerResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Animal;
import dev.patika.VeterinerYonetimSistemi.entity.Customer;
import dev.patika.VeterinerYonetimSistemi.service.AnimalService;
import dev.patika.VeterinerYonetimSistemi.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final AnimalService animalService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/findByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse findByName(@PathVariable String name) {
        return customerService.findByName(name);
    }

    //Değerlendirme Formu 18; Proje isterlerine göre hayvan sahibinin sistemde kayıtlı tüm hayvanlarını görüntüleyen metod
    @GetMapping("/{name}/animals")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Animal>> getAnimalsByOwnerId(@PathVariable String name) {
        List<Animal> animals = animalService.getAnimalsByCustomerId(name);
        return ResponseEntity.ok(animals);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.createCustomer(customerRequest);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse customerUpdate(@PathVariable Long id, @RequestBody CustomerRequest request) {
        return customerService.customerUpdate(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void customerDelete(@PathVariable("id") Long id) {
        customerService.customerDeleteById(id);
    }

}
