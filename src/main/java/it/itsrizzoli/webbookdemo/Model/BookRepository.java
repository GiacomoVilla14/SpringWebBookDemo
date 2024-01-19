package it.itsrizzoli.webbookdemo.Model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends CrudRepository<Book, Integer> {

    //Set<Book> findByUserId(int userId);
    Optional<Book> findById(int bookId);

    @Query("select b from Book b inner join BookUser ub on b.id = ub.book.id and ub.user.id = :userId")
    List<Book> findBooksByBookUser(@Param("userId") Integer userId);
}
