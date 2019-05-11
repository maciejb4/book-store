package pl.jstk.repository;


import java.util.List;

import org.springframework.stereotype.Repository;
import pl.jstk.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select book from Book book where upper(book.title) like concat(upper(:title), '%')")
    List<Book> findBookByTitle(@Param("title") String title);

}
