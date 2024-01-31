package ru.flamexander.spring.data.jdbc.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.flamexander.spring.data.jdbc.demo.entities.Book;

public interface SortedBooksRepository extends PagingAndSortingRepository<Book, Long> {

}
