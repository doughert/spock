package br.com.spocktest.repository;

import br.com.spocktest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);

    List<User> findAll();
}
