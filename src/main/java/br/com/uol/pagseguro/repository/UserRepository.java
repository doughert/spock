package br.com.uol.pagseguro.repository;

import br.com.uol.pagseguro.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);

    List<User> findAll();
}
