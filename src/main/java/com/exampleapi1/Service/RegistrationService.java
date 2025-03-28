package com.exampleapi1.Service;

import com.exampleapi1.Entity.Registration;
import com.exampleapi1.Exception.ResourceNotFound;
import com.exampleapi1.Repository.RegistrationRepository;
import com.exampleapi1.payload.RegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;

    }

    public RegistrationDto addRegistration(RegistrationDto regDto) {
        // Dto to Entity
        Registration registration = convertDtoToEntity(regDto);
        Registration savedReg = registrationRepository.save(registration);

        //Entity to Dto
        RegistrationDto savedRegDto = new RegistrationDto();
        savedRegDto.setName(savedReg.getName());
        savedRegDto.setEmailId(savedReg.getEmailId());
        savedRegDto.setMobile(savedReg.getMobile());
        return savedRegDto;
    }


    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);

    }

    public void updateRegistration(Long id, Registration registration) {
        Optional<Registration> ofReg = registrationRepository.findById(id);
        Registration reg = ofReg.get();
        reg.setName(registration.getName());
        reg.setEmailId(registration.getEmailId());
        reg.setMobile(registration.getMobile());
        registrationRepository.save(reg);

    }


    public List<Registration> getRegistrations(int pageNo, int pageSize, String sortBy, String sortDir) {
        //use ternary operators for creating sort Object
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Registration> record = registrationRepository.findAll(page);
        List<Registration> registrations = record.getContent();
        System.out.println(page.getPageNumber());
        System.out.println(page.getPageSize());
        System.out.println(record.getTotalPages());
        System.out.println(record.getTotalElements());
        System.out.println(record.isFirst());
        System.out.println(record.isLast());
        System.out.println(record.getNumber());
        System.out.println(record.getTotalElements());
        return registrations;
    }

    public Registration getRegistrationById(long id) {

        Registration registration = registrationRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFound("Record not Found with id :" + id)
                );
        return registration;
    }

    Registration convertDtoToEntity(RegistrationDto regDto) {
        Registration registration = modelMapper.map(regDto, Registration.class);
        return registration;
    }


}

