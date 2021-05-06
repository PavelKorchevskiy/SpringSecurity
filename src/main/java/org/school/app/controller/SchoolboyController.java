package org.school.app.controller;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.school.app.model.Schoolboy;
import org.school.app.repository.RepositoryStudent;
import org.school.app.repository.SchoolboyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SchoolboyController {

  @Autowired
  private SchoolboyDao repository;

  @GetMapping("/schoolboy/{id}")
  public ModelAndView getStudent(@PathVariable int id) {
    log.info("get one student controller");
    ModelAndView modelAndView = new ModelAndView();
    Optional<Schoolboy> optional = repository.findById(id);
    if (optional.isPresent()) {
      modelAndView.addObject("student", optional.get().toString());
    } else {
      modelAndView.addObject("student", "student not found");
    }
    modelAndView.setViewName("StudentsProfile");
    return modelAndView;
  }

  @GetMapping("/schoolboys")
  public ModelAndView getAll(Authentication authentication) {
    log.info("get all students controller");
    ModelAndView modelAndView = new ModelAndView();
    StringBuilder allStudents = new StringBuilder();
    repository.findAll().forEach(s -> allStudents.append(s.toString()).append("<br/>"));
    modelAndView.addObject("allStudents", allStudents.toString());
    modelAndView.addObject("auth", "name - " + authentication.getName()
        + ", principal - " + authentication.getPrincipal());
    modelAndView.setViewName("AllStudents");
    return modelAndView;
  }
}
