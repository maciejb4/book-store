package pl.jstk.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import pl.jstk.to.BookTo;

public interface BookService {

    List<BookTo> findAllBooks();
    BookTo findById(Long id);
    List<BookTo> findBooksByTitle(String title);

    BookTo saveBook(BookTo book);
    void deleteBook(Long id);

    @Transactional
    List<BookTo> findBooksByCriteria(BookTo bookTo);
}
