package ru.flamexander.spring.data.jdbc.demo.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.flamexander.spring.data.jdbc.demo.dtos.BookDto;
import ru.flamexander.spring.data.jdbc.demo.dtos.DetailedBookDto;
import ru.flamexander.spring.data.jdbc.demo.entities.Book;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BooksRepository extends ListCrudRepository<Book, Long> {
    @Query(
            "select b.id, b.title, b.genre, b.averageRate, a.full_name as author_name, bd.description from BOOKS b " +
                    " left join AUTHORS a on b.author_id = a.id " +
                    " left join BOOKS_DETAILS bd on bd.book_id = b.id"
    )
    List<DetailedBookDto> findAllDetailedBooks();

    @Query("update books set title = :title where id = :id")
    @Modifying
    void changeTitleById(Long id, String title);

    @Modifying
    @Query("UPDATE BOOKS SET average_rate = (SELECT AVG(review_rate) FROM REVIEWS WHERE book_id = :bookId) WHERE id = :bookId")
    void updateAverageRate(Long bookId);



    @Query(
            "select b.id, b.title from BOOKS b " +
                  " limit :pageSize offset :offset"
    )
    List<BookDto> findBooksByPage(long pageSize, long offset);

    @Query("SELECT b.id, b.title, AVG(r.review_rate) AS average_rate " +
            "FROM books b " +
            "LEFT JOIN reviews r ON r.book_id = b.id " +
            "WHERE r.review_date >= CURRENT_DATE - INTERVAL '1' MONTH " +
            "GROUP BY b.id, b.title " +
            "ORDER BY average_rate DESC " +
            "LIMIT 10")
   List<BookDto> findTop10ByReviews(LocalDate oneMonthAgo);
}