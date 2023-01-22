package com.sarad.facialrecognizationattendance.employee;

import com.sarad.facialrecognizationattendance.entity.Employee;
import com.sarad.facialrecognizationattendance.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> addEmployee(@Valid @RequestBody EmployeeRequest request){
        employeeService.add(request);
        return ResponseEntity.ok("Employee Added.");
    }

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees = employeeService.getAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable(value = "id") Long id){
        Optional<Employee> employee = employeeService.getById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Employee>> updateEmployee(@PathVariable(value = "id") Long id, @RequestBody EmployeeRequest request){
        Optional<Employee> employee = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(employee);
    }

    @PatchMapping("/update/role/{id}")
    public ResponseEntity<Optional<Employee>> patchRole(@PathVariable(value = "id") Long id, @RequestBody EmployeeEnumRequest enumRequest){
        Optional<Employee> employee = employeeService.patchRole(id, enumRequest);
        return ResponseEntity.ok(employee);
    }

    @PatchMapping("/uploadImages/{id}")
    public ResponseEntity<String> uploadFiles(@PathVariable(value = "id") Long id, @RequestParam("files") MultipartFile[] files){
        employeeService.uploadImages(id,files);
        return ResponseEntity.ok("Files Uploaded");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(value = "id") Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted.");
    }

}
