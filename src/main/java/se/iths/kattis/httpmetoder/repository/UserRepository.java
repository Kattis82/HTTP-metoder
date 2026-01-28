package se.iths.kattis.httpmetoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.kattis.httpmetoder.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
