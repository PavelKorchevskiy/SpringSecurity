package org.school.app.repository;

import java.util.Optional;
import org.school.app.model.Schoolboy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolboyDao extends JpaRepository<Schoolboy, Integer> {

  Optional<Schoolboy> findByName(String name);

  Optional<Schoolboy> findById(int id);
}
