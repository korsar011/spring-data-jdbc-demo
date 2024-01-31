package ru.flamexander.spring.data.jdbc.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.flamexander.spring.data.jdbc.demo.dtos.*;
import ru.flamexander.spring.data.jdbc.demo.entities.Author;
import ru.flamexander.spring.data.jdbc.demo.entities.Book;
import ru.flamexander.spring.data.jdbc.demo.entities.Review;
import ru.flamexander.spring.data.jdbc.demo.repositories.BooksRepository;
import ru.flamexander.spring.data.jdbc.demo.repositories.ReviewRepository;
import ru.flamexander.spring.data.jdbc.demo.repositories.SortedBooksRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;

@Service
public class BooksService {
    private final BooksRepository booksRepository;

    private final SortedBooksRepository sortedBooksRepository;



    @Autowired
    public BooksService(BooksRepository booksRepository, SortedBooksRepository sortedBooksRepository) {
        this.booksRepository = booksRepository;
        this.sortedBooksRepository = sortedBooksRepository;
    }


    public List<DetailedBookDto> findAllDetailedBooks() {
        return booksRepository.findAllDetailedBooks();
    }

    public void updateTitleById(Long id, String newTitle) {
        booksRepository.changeTitleById(id, newTitle);
    }

    public PageDto<BookDto> getBooks(long pageNumber, long pageSize) {
        long offset = pageNumber * pageSize;
        List<BookDto> books = booksRepository.findBooksByPage(pageSize, offset);
        long booksCount = booksRepository.count();
        long pageCount = (long) Math.ceil((double) booksCount / pageSize);
        return new PageDto<>(books, pageNumber, pageSize, pageCount, booksCount);
    }

    public PageDto<BookDto> getSortedBooks(long pageNumber, long pageSize) {
        PageRequest pageRequest = PageRequest.of((int) pageNumber, (int) pageSize);
        var page = sortedBooksRepository.findAll(pageRequest);
        var books = page.getContent();
        List<BookDto> booksDto = books.stream().map(b -> new BookDto(b.getId(), b.getTitle())).toList();
        long booksCount = page.getTotalElements();
        var pageCount = page.getTotalPages();
        return new PageDto<>(booksDto, pageNumber, pageSize, pageCount, booksCount);
    }

    public List<BookDto> get100TopBooksByRate (){
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return booksRepository.findTop10ByReviews(oneMonthAgo);
    }


}
