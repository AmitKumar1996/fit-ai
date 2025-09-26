package com.futness.userService.service;

import com.futness.userService.UserRepository;
import com.futness.userService.dto.RegisterRequest;
import com.futness.userService.dto.UserResponse;
import com.futness.userService.exception.EmailAlreadyExistsException;
import com.futness.userService.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("StrongPass@123");
        request.setFirstName("John");
        request.setLastName("Doe");

        User savedUser = new User();
        savedUser.setId("1");
        savedUser.setEmail(request.getEmail());
        savedUser.setPassword(request.getPassword());
        savedUser.setFirstName(request.getFirstName());
        savedUser.setLastName(request.getLastName());

        when(repository.existsByEmail(request.getEmail())).thenReturn(false);
        when(repository.save(any(User.class))).thenReturn(savedUser);

        UserResponse response = userService.register(request);

        assertEquals("1", response.getId());
        assertEquals("test@example.com", response.getEmail());
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterEmailAlreadyExists() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");

        when(repository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.register(request);
        });

        verify(repository, never()).save(any(User.class));
    }
}
