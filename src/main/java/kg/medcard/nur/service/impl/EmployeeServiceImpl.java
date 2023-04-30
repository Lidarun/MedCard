package kg.medcard.nur.service.impl;

import kg.medcard.nur.domain.model.Employee;
import kg.medcard.nur.repository.EmployeeRepository;
import kg.medcard.nur.service.EmployeeService;
import kg.medcard.nur.service.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements ValidateService, EmployeeService {
    private final EmployeeRepository employeeRep;
    @Override
    public ObjectError validPassword(String password, String confirmPassword) {
        ObjectError error = null;
        if (!password.equals(confirmPassword)) error =
                new ObjectError("global", "Пароли не совпадают");
        return error;
    }

    @Override
    public void create(Employee employee) {
        employeeRep.save(employee);
    }

    @Override
    public boolean isExists(String email) {
        return employeeRep.existsByEmail(email);
    }


}
