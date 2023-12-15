package dev.patika.VeterinerYonetimSistemi.mapper;

import dev.patika.VeterinerYonetimSistemi.dto.request.AnimalRequest;
import dev.patika.VeterinerYonetimSistemi.dto.response.AnimalResponse;
import dev.patika.VeterinerYonetimSistemi.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnimalMapper {

    AnimalResponse asOutput(Animal animal);
    List<AnimalResponse> asOutput(List<Animal> animal);
    Animal asEntity(AnimalRequest animalRequest);

    void update(@MappingTarget Animal entity,AnimalRequest request);
}
