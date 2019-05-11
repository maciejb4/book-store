package pl.jstk.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.jstk.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

    private BookRepository bookRepository;

    @Autowired
    public CustomRepositoryImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> findBooksByCriteria(Book book) {
        List<Book> bookList = bookRepository.findAll();
        List<Book> collect = bookList.stream()
                .filter(entity -> book.getTitle() == null || entity.getTitle()
                        .toUpperCase()
                        .contains(book.getTitle()
                                .toUpperCase()))
                .filter(entity2 -> book.getStatus() == null || entity2.getStatus()
                        .toString()
                        .toUpperCase()
                        .contains(book.getStatus()
                                .toString()
                                .toUpperCase()))
                .filter(entity3 -> book.getCategories().isEmpty() || entity3.getCategories().containsAll(book.getCategories())).collect(Collectors.toList());

        return collect;
    }
}
