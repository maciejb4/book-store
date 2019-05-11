package pl.jstk.mapper.impl;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import pl.jstk.entity.Book;
import pl.jstk.mapper.BookMapper;
import pl.jstk.to.BookTo;

import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookTo map2To(Book book) {
        if (book != null) {
            return new BookTo(
                    book.getId(),
                    book.getTitle(),
                    new HashSet<>(book.getAuthors()),
                    book.getDescription(),
                    book.getStatus(),
                    new HashSet<>(book.getCategories()));
        }
        return null;
    }

    @Override
    public Book map(BookTo bookTo) {
        if (bookTo != null) {
            if(bookTo.getAuthors() == null && bookTo.getCategories() == null) {
                return new Book(
                        bookTo.getId(),
                        bookTo.getTitle(),
                        new HashSet<>(),
                        bookTo.getDescription(),
                        bookTo.getStatus(),
                        new HashSet<>());
            }
            if(bookTo.getAuthors() == null) {
                return new Book(
                        bookTo.getId(),
                        bookTo.getTitle(),
                        new HashSet<>(),
                        bookTo.getDescription(),
                        bookTo.getStatus(),
                        new HashSet<>(bookTo.getCategories()));
            }
            if(bookTo.getCategories() == null) {
                return new Book(
                        bookTo.getId(),
                        bookTo.getTitle(),
                        new HashSet<>(bookTo.getAuthors()),
                        bookTo.getDescription(),
                        bookTo.getStatus(),
                        new HashSet<>());
            }

            return new Book(
                    bookTo.getId(),
                    bookTo.getTitle(),
                    new HashSet<>(bookTo.getAuthors()),
                    bookTo.getDescription(),
                    bookTo.getStatus(),
                    new HashSet<>(bookTo.getCategories()));
        }
        return null;
    }

    @Override
    public List<BookTo> map2To(List<Book> bookEntities) {
        return bookEntities.stream().map(this::map2To).collect(Collectors.toList());
    }

    @Override
    public List<Book> map2Entity(List<BookTo> bookEntities) {
        return bookEntities.stream().map(this::map).collect(Collectors.toList());
    }
}
