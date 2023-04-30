package kg.medcard.nur.service;

import kg.medcard.nur.domain.model.Employee;

public interface EmployeeService extends ValidateService{
    void  create(Employee employee);
    boolean isExists(String email);
}
