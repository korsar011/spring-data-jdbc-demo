package ru.flamexander.spring.data.jdbc.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.spring.data.jdbc.demo.dtos.*;
import ru.flamexander.spring.data.jdbc.demo.entities.Review;
import ru.flamexander.spring.data.jdbc.demo.exceptions.ResourceNotFoundException;
import ru.flamexander.spring.data.jdbc.demo.services.ReviewService;

import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewsCotroller {
    private final ReviewService reviewService;

    private static final Function<Review, ReviewDto> MAP_TO_DTO_FUNCTION = r -> new ReviewDto(r.getId(), r.getAuthorName(), r.getReviewRate());

    @Autowired
    public ReviewsCotroller(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/list")
    public List<ReviewDto> getReviews (@RequestParam long bookId){
        return reviewService.getReviewsByBookId(bookId).stream().map(MAP_TO_DTO_FUNCTION).toList();
    }

    @PostMapping
    public void createNewReview(@RequestBody ReviewDtoRq reviewDtoRq) {
        reviewService.addReviewToBook(reviewDtoRq);
    }
}
