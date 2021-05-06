package org.school.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.school.app.model.auth.UserDetailsImpl;
import org.school.app.repository.SchoolboyDao;
import org.school.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService implements UserDetailsService {
@Autowired
  private UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    log.info("++++++++++" + userRepository.findAll().size());

    return new UserDetailsImpl(
        userRepository.findAll().stream()
            .filter(user -> user.getSchoolboy().getName().equals(s))
            .findAny().orElseThrow(RuntimeException::new));
  }
}
