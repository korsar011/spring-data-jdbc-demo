package ru.flamexander.spring.data.jdbc.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto <T> {
   private List <T> objects;
   private long pageNumber;
   private long pageSize;
   private long pageCount;
   private long booksCount;
}
