package kg.medcard.nur.service;

import org.springframework.validation.ObjectError;

public interface ValidateService {
    ObjectError comparePassword(String password, String confirmPassword);
    ObjectError validPassword(String password);
    ObjectError existEmployee(String email);

}
