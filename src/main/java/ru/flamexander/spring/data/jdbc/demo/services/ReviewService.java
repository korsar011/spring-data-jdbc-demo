package ru.flamexander.spring.data.jdbc.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.flamexander.spring.data.jdbc.demo.dtos.ReviewDto;
import ru.flamexander.spring.data.jdbc.demo.dtos.ReviewDtoRq;
import ru.flamexander.spring.data.jdbc.demo.entities.Book;
import ru.flamexander.spring.data.jdbc.demo.entities.Review;
import ru.flamexander.spring.data.jdbc.demo.repositories.BooksRepository;
import ru.flamexander.spring.data.jdbc.demo.repositories.ReviewRepository;
import ru.flamexander.spring.data.jdbc.demo.repositories.SortedBooksRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.OptionalDouble;

@Service

public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BooksRepository booksRepository;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository, BooksRepository booksRepository) {
        this.reviewRepository = reviewRepository;
        this.booksRepository = booksRepository;
    }

//    public OptionalDouble countAverageRate (Book book){
//        return book.getReviews().stream().mapToInt(Review::getReviewRate).average();
//    }

    public void addReviewToBook(ReviewDtoRq reviewDtoRq) {

        Review review = new Review();
        review.setBookId(reviewDtoRq.getBookId());
        review.setAuthorName(reviewDtoRq.getAuthorName());
        review.setReviewText(reviewDtoRq.getReviewText());
        review.setReviewRate(reviewDtoRq.getReviewRate());
        review.setReviewDate(LocalDate.now());


        reviewRepository.save(review);
        booksRepository.updateAverageRate(review.getBookId());

    }

    public List<Review> getReviewsByBookId(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }
}
