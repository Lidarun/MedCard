package kg.medcard.nur.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import kg.medcard.nur.enums.Gender;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotEmpty(message = "email не должен быть пустым")
    @Email(message = "Пожалуйста, введите действительный адрес электронной почты")
    String email;

    @NotEmpty(message = "Имя не должно быть пустым")
    String name;

    @NotEmpty(message = "Фамилия не должно быть пустым")
    String surname;
    String patronymic;

    @NotNull(message = "Укажите пол")
    Gender gender;

    @Past(message = "Неверная дата")
//    @NotNull(message = "Укажите дату рождения")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    LocalDate birthday;

    @NotEmpty(message = "Пароль не должен быть пустым")
//    @Size(min = 8, message = "Длина пароли должен быть мин. 8 символов")
    String password;

    @Transient
    @NotEmpty(message = "Повторный пароль не должен быть пустым")
    String confirmPassword;
}
