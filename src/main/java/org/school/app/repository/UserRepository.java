package org.school.app.repository;

import java.util.Optional;
import org.school.app.model.Schoolboy;
import org.school.app.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
