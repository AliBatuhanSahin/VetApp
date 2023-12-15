package dev.patika.VeterinerYonetimSistemi.mapper;
import dev.patika.VeterinerYonetimSistemi.dto.request.CustomerRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.CustomerResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerResponse asOutput(Customer customer);
    List<CustomerResponse> asOutput(List<Customer> customer);
    Customer asEntity(CustomerRequest customerRequest);

    void update(@MappingTarget Customer entity, CustomerRequest request);
}
