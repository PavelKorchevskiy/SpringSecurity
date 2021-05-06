package org.school.app.repository;

import java.util.List;
import java.util.Optional;
import org.school.app.model.Schoolboy;

public interface RepositoryStudent {

  List<Schoolboy> getAll();

  Optional<Schoolboy> getStudentById(int id);

}
