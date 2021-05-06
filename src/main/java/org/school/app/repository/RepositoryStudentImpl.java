package org.school.app.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.school.app.model.Schoolboy;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class RepositoryStudentImpl implements RepositoryStudent {

  private Map<Integer, Schoolboy> students = new HashMap<>();

  @PostConstruct
  public void init() {
    log.info("init map");
    students.put(1, new Schoolboy(1, "Andry", 80));
    students.put(2, new Schoolboy(2, "Alex", 90));
    students.put(3, new Schoolboy(3, "Kim",70));
    students.put(4, new Schoolboy(4, "Jon",0));
    students.put(5, new Schoolboy(5, "Ron",60));
  }

  @Override
  public List<Schoolboy> getAll() {
    List<Schoolboy> students1 = new ArrayList<>(students.values());
    log.info("list size - " + students1.size());
    return students1;
  }

  @Override
  public Optional<Schoolboy> getStudentById(int id) {
    return Optional.ofNullable(students.get(id));
  }
}
