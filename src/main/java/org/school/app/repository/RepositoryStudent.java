package org.school.app.repository;

import java.util.List;
import java.util.Optional;
import org.school.app.model.Student;

public interface RepositoryStudent {

  List<Student> getAll();

  Optional<Student> getStudentById(int id);

}
