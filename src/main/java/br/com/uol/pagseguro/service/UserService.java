package br.com.uol.pagseguro.service;

import br.com.uol.pagseguro.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findAll();

    String getUserSalary(Long id);
}
