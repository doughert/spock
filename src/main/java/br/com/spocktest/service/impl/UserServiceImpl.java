package br.com.spocktest.service.impl;

import br.com.spocktest.repository.UserRepository;
import br.com.spocktest.service.UserService;
import br.com.spocktest.exception.NotFoundException;
import br.com.spocktest.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("User with id %s was not found", id)));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public String getUserSalary(Long id) {
        User user = findById(id);

        String salary = "";

        switch (user.getJobTitle()) {
            case MANAGER:
                salary = "R$ 50.000,00";
                break;
            case COORDINATOR:
                salary = "R$ 30.000,00";
                break;
            case SPECIALIST:
                salary = "R$ 20.000,00";
                break;
            case ENGINEER:
                salary = "R$ 1.000,00";
                break;
        }

        return salary;
    }

}
