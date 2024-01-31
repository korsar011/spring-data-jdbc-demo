package ru.flamexander.spring.data.jdbc.demo.dtos;

import jakarta.validation.constraints.Positive;

public class BookDto {
    private Long id;

    private String title;

    private double averageRate;

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BookDto() {
    }

    public BookDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
