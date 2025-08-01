package cosimocrupi.L5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import cosimocrupi.L5.entities.Employee;
import cosimocrupi.L5.exceptions.BadRequestException;
import cosimocrupi.L5.exceptions.NotFoundException;
import cosimocrupi.L5.payloads.EmployeeDTO;
import cosimocrupi.L5.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    Cloudinary imageUp;

    public Employee save(EmployeeDTO payload){
        this.employeeRepository.findByEmail(payload.email()).ifPresent(employee -> {
            throw new BadRequestException("L'email " + employee.getEmail() + " è già in uso!");
        });
        Employee newE = new Employee(payload.username(), payload.name(), payload.surname(), payload.email());
        newE.setAvatarUrl("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
        Employee saveE = this.employeeRepository.save(newE);
        log.info("Il dipendente con username " + saveE.getUsername() + " è stato salvato correttamente!");
        return saveE;
    }
    public List<Employee> findAll(){
        return this.employeeRepository.findAll();
    }

    public Employee findById(UUID employeeId){
        return this.employeeRepository.findById(employeeId).orElseThrow(()-> new NotFoundException(employeeId));
    }
    public Employee findByIdAndUpdate(UUID employeeId, EmployeeDTO payload){
        Employee fnd =this.findById(employeeId);

        if (!fnd.getEmail().equals(payload.email()))
            this.employeeRepository.findByEmail(payload.email()).ifPresent(employee -> {throw new BadRequestException("L'email " + payload.email() + " è già in uso");
            });
        fnd.setUsername(payload.username());
        fnd.setName(payload.name());
        fnd.setSurname(payload.surname());
        fnd.setEmail(payload.email());
        fnd.setAvatarUrl("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
        Employee updateEmp = this.employeeRepository.save(fnd);
        log.info("Il dipendente con id " + fnd.getId() + " èstato modificato correttamente!");
        return updateEmp;
    }
    public void findByIdAndDelete(UUID employeeId){
        Employee fnd = this.findById(employeeId);
        this.employeeRepository.delete(fnd);
    }

    public String uploadAvatar(MultipartFile file){
        try {
            Map result = imageUp.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imgUrl= (String) result.get("url");
            return imgUrl;
        }catch (IOException e) {
            throw new BadRequestException("Errore nel salvataggio!");
        }
    }
}
