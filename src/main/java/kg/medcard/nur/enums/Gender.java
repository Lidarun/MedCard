package kg.medcard.nur.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Муж."),
    FEMALE("Жен.");

    private final String value;

    Gender(String value) {
        this.value = value;
    }
}
