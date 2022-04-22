package net.accessory.paragram.repositories;

import net.accessory.paragram.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    List<User> findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.username LIKE :username OR u.email LIKE :username")
    List<User> findUserByUsernameOrMail(@Param("username") String username);
}
