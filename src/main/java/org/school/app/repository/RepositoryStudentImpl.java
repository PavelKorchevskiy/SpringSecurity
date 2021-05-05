package org.school.app.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.school.app.model.Student;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RepositoryStudentImpl implements RepositoryStudent {

  private Map<Integer, Student> students = new HashMap<>();

  @PostConstruct
  public void init() {
    log.info("init map");
    students.put(1, new Student(1, "Andry", "123", 80));
    students.put(2, new Student(2, "Alex", "123", 90));
    students.put(3, new Student(3, "Kim", "123", 70));
    students.put(4, new Student(4, "Jon", "123", 10));
    students.put(5, new Student(5, "Ron", "123", 60));
  }

  @Override
  public List<Student> getAll() {
    List<Student> students1 = new ArrayList<>(students.values());
    log.info("list size - " + students1.size());
    return students1;
  }

  @Override
  public Optional<Student> getStudentById(int id) {
    return Optional.ofNullable(students.get(id));
  }
}
