package br.com.spocktest.service.impl;

import br.com.spocktest.model.User;
import br.com.spocktest.repository.UserRepository;
import br.com.spocktest.exception.NotFoundException;
import br.com.spocktest.model.enums.JobTitle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    public void shouldFindById() {
        Long id = 3L;
        User user = User.builder().id(id).build();

        when(repository.findById(id)).thenReturn(Optional.of(user));

        Assertions.assertEquals(service.findById(id), user);

        verify(repository, times(1)).findById(id);
    }

    @Test
    public void shouldFindByIdAndThrowNotFoundException() {
        Long id = 3L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class,
                () -> service.findById(id));

        verify(repository, times(1)).findById(id);
        assertEquals(exception.getMessage(), String.format("User with id %s was not found", id));
    }

    @Test
    public void shouldFindAll() {
        User firstUser = User.builder().id(1L).build();
        User secondUser = User.builder().id(2L).build();

        List<User> users = Arrays.asList(firstUser, secondUser);

        when(repository.findAll()).thenReturn(users);

        assertEquals(service.findAll(), users);

        verify(repository, times(1)).findAll();
    }

    @Test
    public void shouldGetUserSalaryWhenUserIsAManager() {
        Long id = 3L;

        User manager = User.builder().id(id).jobTitle(JobTitle.MANAGER).build();

        when(repository.findById(id)).thenReturn(Optional.of(manager));

        assertEquals(service.getUserSalary(id), "R$ 50.000,00");

        verify(repository, times(1)).findById(id);
    }

    @Test
    public void shouldGetUserSalaryWhenUserIsACoordinator() {
        Long id = 3L;

        User manager = User.builder().id(id).jobTitle(JobTitle.COORDINATOR).build();

        when(repository.findById(id)).thenReturn(Optional.of(manager));

        assertEquals(service.getUserSalary(id), "R$ 30.000,00");

        verify(repository, times(1)).findById(id);
    }

}