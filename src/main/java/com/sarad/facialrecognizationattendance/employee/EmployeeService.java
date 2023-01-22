package com.sarad.facialrecognizationattendance.employee;

import com.sarad.facialrecognizationattendance.entity.Employee;
import com.sarad.facialrecognizationattendance.fileStorage.FileStorageService;
import com.sarad.facialrecognizationattendance.helper.EnumHelper;
import com.sarad.facialrecognizationattendance.helper.FIleStorageNameHelper;
import com.sarad.facialrecognizationattendance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EnumHelper enumHelper;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FIleStorageNameHelper fIleStorageNameHelper;

    public void add(EmployeeRequest request) {
        var employee = Employee.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName((request.getFirstName()))
                .dateOfBirth(request.getDateOfBirth())
                .build();

        employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public Optional<Employee> getById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;
    }

    public Optional<Employee> updateEmployee(Long id, EmployeeRequest request) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent()){
            Employee _employee = employee.get();
            _employee.setEmail(request.getEmail());
            _employee.setFirstName(request.getFirstName());
            _employee.setLastName(request.getLastName());
            _employee.setDateOfBirth(request.getDateOfBirth());
            employeeRepository.save(_employee);
        }

        return employee;
    }

    public Optional<Employee> patchRole(Long id, EmployeeEnumRequest enumRequest) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            Employee _employee = employee.get();
           _employee.setRole(enumHelper.roleEnumHelper(enumRequest.getEnumString()));
            employeeRepository.save(_employee);
        }

        return employee;
    }

    public void deleteEmployee(Long id) {

        employeeRepository.deleteById(id);

    }

    public void uploadImages(Long id,MultipartFile[] files) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){

            Employee _employee = employee.get();

            ArrayList <String> fileNames = new ArrayList<>();


            Arrays.stream(files).forEach(file -> {
                fileStorageService.save(file);
                fileNames.add(fIleStorageNameHelper.fileNameTimestamp(file.getOriginalFilename()));
            });

            String[] images = fileNames.toArray(new String[0]);

            _employee.setImages(images);
            employeeRepository.save(_employee);
        }

    }
}
