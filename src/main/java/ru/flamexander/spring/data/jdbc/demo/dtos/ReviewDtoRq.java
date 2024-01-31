package ru.flamexander.spring.data.jdbc.demo.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDtoRq {
    long bookId;
    @NotBlank
    String authorName;
    String reviewText;
    @Max(value=10)
    @PositiveOrZero
    int reviewRate;
}
