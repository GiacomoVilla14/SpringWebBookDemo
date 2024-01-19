package it.itsrizzoli.webbookdemo.Model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findBySurname(String surname);

    User findUserById(Integer id);


    @Query("select u from User u where username= :username and password = :password")
    public User login(String username, String password);


}
