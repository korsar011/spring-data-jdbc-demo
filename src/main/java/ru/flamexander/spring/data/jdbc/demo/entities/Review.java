package ru.flamexander.spring.data.jdbc.demo.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;



@Data
@NoArgsConstructor
@Table("REVIEWS")
public class Review {
@Id
long id;
@Positive
long bookId;
@NotBlank
String authorName;
@NotBlank
String reviewText;
@Max(value=10)
@PositiveOrZero
int reviewRate;
LocalDate reviewDate;
}
