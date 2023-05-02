package kg.medcard.nur.service;

import org.springframework.validation.ObjectError;

public interface ValidateService {
    ObjectError validPassword(String password, String confirmPassword);
    ObjectError existEmployee(String email);

}
