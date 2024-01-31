package ru.flamexander.spring.data.jdbc.demo.controllers;

import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.spring.data.jdbc.demo.dtos.BookDto;
import ru.flamexander.spring.data.jdbc.demo.dtos.DetailedBookDto;
import ru.flamexander.spring.data.jdbc.demo.dtos.PageDto;
import ru.flamexander.spring.data.jdbc.demo.dtos.SimplestPageDto;
import ru.flamexander.spring.data.jdbc.demo.entities.Book;
import ru.flamexander.spring.data.jdbc.demo.services.BooksService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {
    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public SimplestPageDto<DetailedBookDto> findAllDetailedBooks() {
        return new SimplestPageDto<>(booksService.findAllDetailedBooks());
    }

    @GetMapping("/list")
    public PageDto<BookDto> getBooks (@RequestParam    @Positive Long pageNumber, @RequestParam    @Positive Long pageSize){
        return booksService.getBooks(pageNumber-1,pageSize);
    }
    @GetMapping("/list-new")
    public PageDto<BookDto> getSortedBooks (@RequestParam @Positive Long pageNumber, @RequestParam @Positive Long pageSize){
        return booksService.getSortedBooks(pageNumber-1,pageSize);
    }

    @PatchMapping("/{id}/title")
    public void updateTitleById(@PathVariable Long id, @RequestParam String value) {
        booksService.updateTitleById(id, value);
    }
    @GetMapping("/top10")
    public List<BookDto> getTop10BooksLastMonth() {
        return booksService.get100TopBooksByRate();
    }
}
