package dev.patika.VeterinerYonetimSistemi.service;
import dev.patika.VeterinerYonetimSistemi.dto.request.CustomerRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AnimalResponse;
import dev.patika.VeterinerYonetimSistemi.dto.response.CustomerResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Customer;
import dev.patika.VeterinerYonetimSistemi.mapper.AnimalMapper;
import dev.patika.VeterinerYonetimSistemi.mapper.CustomerMapper;
import dev.patika.VeterinerYonetimSistemi.repository.IAnimalRepo;
import dev.patika.VeterinerYonetimSistemi.repository.ICustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ICustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final AnimalMapper animalMapper;
    private final IAnimalRepo animalRepo;

    public List<CustomerResponse> findAll() {
        return customerMapper.asOutput(customerRepo.findAll());
    }

    //Değerlendirme Formu 17; Proje isterlerine göre hayvan sahibinin ismine göre filtrelendiği metod
    public CustomerResponse findByName(String name) {
        Optional<Customer> optionalCustomer = customerRepo.findByName(name);
        return customerMapper.asOutput(optionalCustomer.get());
    }

    public CustomerResponse getCustomerById(Long id){
        Optional<Customer> optionalCustomer = customerRepo.findById(id);
        if (optionalCustomer.isPresent()){
            return customerMapper.asOutput(optionalCustomer.get());
        }else {
            throw new RuntimeException("Bu id'de hayvan sahibi bulunamadı: " + id);
        }
    }

    //Değerlendirme Formu 10; Proje isterlerine göre hayvan sahibinin kaydedildiği metod
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Optional<Customer> isCustomerExist = customerRepo.findByNameAndPhoneAndMailAndAddressAndCity(customerRequest.getName(), customerRequest.getPhone(), customerRequest.getMail(), customerRequest.getAddress(), customerRequest.getCity());
        if (isCustomerExist.isPresent()){
            throw new RuntimeException("Bu isimde hayvan sahibi zaten var" + customerRequest.getName());
        }
        return customerMapper.asOutput(customerRepo.save(customerMapper.asEntity(customerRequest)));
    }

    public CustomerResponse customerUpdate(Long id, CustomerRequest request) {
        Optional<Customer> customerFromDb = customerRepo.findById(id);
        Optional<Customer> isCustomerExist = customerRepo.findByNameAndPhoneAndMailAndAddressAndCity(request.getName(), request.getPhone(), request.getMail(), request.getAddress(), request.getCity());

        if (customerFromDb.isEmpty()) {
            throw new RuntimeException(id + " Güncellemeye çalıştığınız hayvan sahibi sistemde yok.");
        }

        if (isCustomerExist.isPresent()) {
            throw new RuntimeException(id + " Bu hayvan sahibi daha önce sisteme kayıt olmuştur.");
        }
        Customer customer = customerFromDb.get();
        customerMapper.update(customer, request);
        return  customerMapper.asOutput(customerRepo.save(customer));

    }

    public void customerDeleteById(Long id) {
        Optional<Customer> customerFromDb = customerRepo.findById(id);
        if (customerFromDb.isPresent()) {
            customerRepo.delete(customerFromDb.get());
        }else {
            throw new RuntimeException(id +"id'li hayvan sahibi sistemde bulunamadı.");
        }
    }
}
