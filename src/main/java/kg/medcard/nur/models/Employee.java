package kg.medcard.nur.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message = "email не может быть пустым")
    @Email(message = "Пожалуйста, введите действительный адрес электронной почты")
    String email;

    @NotEmpty(message = "Имя не может быть пустым")
    String name;

    @NotEmpty(message = "Фамилия не может быть пустым")
    String surname;
    String patronymic;

    @NotEmpty(message = "Пароль не может быть пустым")
    String password;

    @Transient
    String confirmPassword;
}
