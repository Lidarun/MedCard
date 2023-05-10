package kg.medcard.nur.service.impl;

import kg.medcard.nur.domain.model.Employee;
import kg.medcard.nur.repository.EmployeeRepository;
import kg.medcard.nur.service.EmployeeService;
import kg.medcard.nur.service.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements ValidateService, EmployeeService {
    private final EmployeeRepository employeeRep;
    @Override
    public ObjectError comparePassword(String password, String confirmPassword) {
        ObjectError error = null;
        if (!password.equals(confirmPassword)) error =
                new ObjectError("global", "Пароли не совпадают, попробуйте снова");

        return error;
    }

    @Override
    public ObjectError validPassword(String password) {

        if (password.isEmpty()) return null;

        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches())
            return new ObjectError("global",
                    "Пароль должен содержать не менее 8-ми символов, " +
                            "в том числе цифры, прописаные и строчные буквы");

        return null;
    }

    @Override
    public ObjectError existEmployee(String email) {

        if (isExists(email))
                    return new ObjectError
                            ("global", "Сотрудник с таким email существует");
        return null;
    }

    @Override
    public void create(Employee employee) {
        employeeRep.save(employee);
    }

    @Override
    public boolean isExists(String email) {
        return employeeRep.existsByEmail(email);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRep.findAll();
    }


}
