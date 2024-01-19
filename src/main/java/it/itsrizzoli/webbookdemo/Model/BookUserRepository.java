package it.itsrizzoli.webbookdemo.Model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookUserRepository extends CrudRepository<BookUser, Integer> {
    @Query("select bu from BookUser bu inner join  Book b on bu.book.id=:bookId and bu.user.id=:userId")
    Optional<BookUser> findBooksByUserBooks(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
}
