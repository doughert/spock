package br.com.spocktest.service;

import br.com.spocktest.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    String getUserSalary(Long id);
}
