package kg.medcard.nur.service;

import kg.medcard.nur.domain.model.Employee;

import java.util.List;

public interface EmployeeService extends ValidateService{
    void  create(Employee employee);
    boolean isExists(String email);
    List<Employee> findAll();
}
