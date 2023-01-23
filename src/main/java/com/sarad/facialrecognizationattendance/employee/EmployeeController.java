package com.sarad.facialrecognizationattendance.employee;

import com.sarad.facialrecognizationattendance.entity.Employee;
import com.sarad.facialrecognizationattendance.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeRequest request){
        Employee employee =  employeeService.add(request);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees = employeeService.getAll();
        if(employees.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long id){
        Employee employee = employeeService.getById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id, @RequestBody EmployeeRequest request){
        Employee employee = employeeService.updateEmployee(id, request);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PatchMapping("/update/role/{id}")
    public ResponseEntity<Employee> patchRole(@PathVariable(value = "id") Long id, @RequestBody EmployeeEnumRequest enumRequest){
        Employee employee = employeeService.patchRole(id, enumRequest);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PatchMapping("/uploadImages/{id}")
    public ResponseEntity<Employee> uploadFiles(@PathVariable(value = "id") Long id, @RequestParam("files") MultipartFile[] files){
        Employee employee = employeeService.uploadImages(id,files);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(value = "id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
