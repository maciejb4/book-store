package pl.jstk.repository;

import pl.jstk.entity.Book;

import java.util.List;

public interface CustomRepository {

    public List<Book> findBooksByCriteria(Book book);
}
