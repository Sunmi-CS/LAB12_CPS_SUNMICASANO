package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.OwnerDTO;
import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.exceptions.OwnerNotFoundException;
import com.tecsup.petclinic.mapper.OwnerMapper;
import com.tecsup.petclinic.repositories.OwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final OwnerMapper mapper;

    public OwnerServiceImpl(OwnerRepository ownerRepository, OwnerMapper mapper) {
        this.ownerRepository = ownerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public OwnerDTO findById(Integer id) throws OwnerNotFoundException {
        Optional<Owner> optional = ownerRepository.findById(id);
        if (optional.isEmpty()) {
            throw new OwnerNotFoundException(id);
        }
        return mapper.toOwnerDTO(optional.get());
    }

    @Override
    public OwnerDTO create(OwnerDTO ownerDTO) {
        Owner owner = mapper.toOwnerEntity(ownerDTO);
        Owner newOwner = ownerRepository.save(owner);
        return mapper.toOwnerDTO(newOwner);
    }

    @Override
    public OwnerDTO update(OwnerDTO ownerDTO) throws OwnerNotFoundException {
        if (!ownerRepository.existsById(ownerDTO.getId())) {
            throw new OwnerNotFoundException(ownerDTO.getId());
        }
        Owner owner = mapper.toOwnerEntity(ownerDTO);
        Owner updated = ownerRepository.save(owner);
        return mapper.toOwnerDTO(updated);
    }

    @Override
    public void delete(Integer id) throws OwnerNotFoundException {
        if (!ownerRepository.existsById(id)) {
            throw new OwnerNotFoundException(id);
        }
        ownerRepository.deleteById(id);
    }
}
