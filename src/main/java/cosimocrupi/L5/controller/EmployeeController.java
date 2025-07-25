package cosimocrupi.L5.controller;

import cosimocrupi.L5.entities.Employee;
import cosimocrupi.L5.exceptions.ValidationException;
import cosimocrupi.L5.payloads.EmployeeDTO;
import cosimocrupi.L5.payloads.EmployeeRespDTO;
import cosimocrupi.L5.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeRespDTO save(@RequestBody @Validated EmployeeDTO payload, BindingResult validRes){
        if (validRes.hasErrors()){
            throw new ValidationException(validRes.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        }else {
            Employee newE = this.employeeService.save(payload);
            return new EmployeeRespDTO(newE.getId());
        }
    }
    @GetMapping("/{employeeId}")
    public Employee getById(@PathVariable UUID employeeId){
        return this.employeeService.findById(employeeId);
    }

    @GetMapping("/employee")
    public List<Employee> getAll(){
        return this.employeeService.findAll();
    }

    @PutMapping("/{employeeId}")
    public Employee getByIdAndUpdate(@PathVariable UUID employeeId, @RequestBody EmployeeDTO payload){
        return this.employeeService.findByIdAndUpdate(employeeId, payload);
    }
    @DeleteMapping("/{employeeId}")
    public void getByIdAndDelete(UUID employeeID){
        this.employeeService.findByIdAndDelete(employeeID);
    }
    @PatchMapping("/{employeeId}/avatar")
    public String uploadImg(@RequestParam("avatar")MultipartFile file){
        System.out.println(file.getOriginalFilename());
        return this.employeeService.uploadAvatar(file);
    }
}
