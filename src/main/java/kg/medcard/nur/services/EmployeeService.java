package kg.medcard.nur.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

@Service
public class EmployeeService implements ValidateService {
    @Override
    public ObjectError validPassword(String password, String confirmPassword) {
        ObjectError error = null;
        if (!password.equals(confirmPassword)) error =
                new ObjectError("global", "Пароли не совпадают");
        return error;
    }
}
