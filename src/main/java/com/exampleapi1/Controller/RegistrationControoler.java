package com.exampleapi1.Controller;

import com.exampleapi1.Entity.Registration;
import com.exampleapi1.Service.RegistrationService;
import com.exampleapi1.payload.RegistrationDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reg")
public class RegistrationControoler {
    private RegistrationService registrationService;

    public RegistrationControoler(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

//http://localhost:8080/api/v1/reg
    @PostMapping
        public ResponseEntity< RegistrationDto> addRegistration(@RequestBody RegistrationDto regDto){

        RegistrationDto r = registrationService.addRegistration(regDto);
        return new ResponseEntity<>(r, HttpStatus.CREATED);
}
 //QueryParameter
//http://localhost:8080/api/v1/reg?id=2
@DeleteMapping
    public String deleteRegistration(@RequestParam Long id ){
    registrationService.deleteRegistration(id);
    return "Deleted";
}

    //path Variable
//http://localhost:8080/api/v1/reg/{id}
    @DeleteMapping("/{id}")
    public String deleteRegistration(@PathVariable long id ){
        registrationService.deleteRegistration(id);
        return "Deleted";
    }
    //http://localhost:8080/api/v1/reg/{id}
    @PutMapping("{id}")
    public String updateRegistration(@ PathVariable Long id , @RequestBody Registration  registration ){
        registrationService. updateRegistration(id,registration);
     return "updated";
    }
    //get allRegistrations
    //http://localhost:8080/api/v1/reg?pageNo=0&pageSize=5orsortBy=name&sortDir=asc
    @GetMapping
    public List<Registration> getRegistrations(
            @ RequestParam (defaultValue = "0",required=false) int pageNo,
            @ RequestParam (defaultValue = "5",required=false) int pageSize,
             @ RequestParam (defaultValue = "id",required=false) String sortBy,
              @ RequestParam (defaultValue = "asc",required=false) String sortDir
            ){
        List<Registration> registrations = registrationService.getRegistrations(pageNo,pageSize,sortBy,sortDir);
        return registrations;
    }
@GetMapping("id/{id}")
    public ResponseEntity<Registration> getRegistrationById(@ PathVariable long id) throws FileNotFoundException {
    FileReader fr = new FileReader("E://test.txt");
        Registration reg = registrationService.getRegistrationById(id);
     if(reg!=null){
         return new ResponseEntity<>(reg,HttpStatus.OK);
     }
    return new ResponseEntity<>(reg,HttpStatus.OK);
}



}
