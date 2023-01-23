package com.sarad.sampleRest.employee;

import com.sarad.sampleRest.entity.Employee;
import com.sarad.sampleRest.exception.ResourceNotFoundException;
import com.sarad.sampleRest.fileStorage.FileStorageService;
import com.sarad.sampleRest.helper.EnumHelper;
import com.sarad.sampleRest.helper.FIleStorageNameHelper;
import com.sarad.sampleRest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public Employee add(EmployeeRequest request) {
        var employee = Employee.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName((request.getFirstName()))
                .dateOfBirth(request.getDateOfBirth())
                .build();

        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));
    }

    public Employee updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));

        employee.setEmail(request.getEmail());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setDateOfBirth(request.getDateOfBirth());
        employeeRepository.save(employee);
        return employee;
    }

    public Employee patchRole(Long id, EmployeeEnumRequest enumRequest) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));

        employee.setRole(enumHelper.roleEnumHelper(enumRequest.getEnumString()));
        employeeRepository.save(employee);
        return employee;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee uploadImages(Long id,MultipartFile[] files) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user with id = " + id));

            ArrayList <String> fileNames = new ArrayList<>();


            Arrays.stream(files).forEach(file -> {
                fileStorageService.save(file);
                fileNames.add(fIleStorageNameHelper.fileNameTimestamp(file.getOriginalFilename()));
            });

            String[] images = fileNames.toArray(new String[0]);

            employee.setImages(images);
            return employeeRepository.save(employee);

    }
}
