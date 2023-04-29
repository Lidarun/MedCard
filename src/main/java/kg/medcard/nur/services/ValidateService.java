package kg.medcard.nur.services;

import org.springframework.validation.ObjectError;

public interface ValidateService {
    ObjectError validPassword(String password, String confirmPassword);
}
