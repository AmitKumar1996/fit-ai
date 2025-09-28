package com.futness.userService.service;

import com.futness.userService.repository.UserRepository;
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

// ✅ This class tests the UserService class using JUnit5 + Mockito
public class UserServiceTest {

    // 🔹 @Mock creates a fake version of UserRepository
    //    Instead of hitting the real database, it will return "mocked" data
    @Mock
    private UserRepository repository;

    // 🔹 @InjectMocks creates an instance of UserService
    //    and automatically injects the mocked UserRepository into it
    @InjectMocks
    private UserService userService;

    // 🔹 This method runs before each test
    //    It initializes all the @Mock and @InjectMocks annotations
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Test case: Successful user registration
    @Test
    void testRegisterSuccess() {
        // 1. Create a RegisterRequest object (input for service method)
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("StrongPass@123");
        request.setFirstName("John");
        request.setLastName("Doe");

        // 2. Create a User object that simulates what the database would return after saving
        User savedUser = new User();
        savedUser.setId("1");
        savedUser.setEmail(request.getEmail());
        savedUser.setPassword(request.getPassword());
        savedUser.setFirstName(request.getFirstName());
        savedUser.setLastName(request.getLastName());

        // 3. Mock repository behavior
        //    - Pretend that email does not exist
        when(repository.existsByEmail(request.getEmail())).thenReturn(false);
        //    - Pretend that saving a user will return the savedUser object
        when(repository.save(any(User.class))).thenReturn(savedUser);

        // 4. Call the method under test
        UserResponse response = userService.register(request);

        // 5. Assertions (verify expected results)
        assertEquals("1", response.getId()); // ID should be "1"
        assertEquals("test@example.com", response.getEmail()); // Email should match
        // 6. Verify repository.save() was called exactly once
        verify(repository, times(1)).save(any(User.class));
    }

    // ✅ Test case: Trying to register with an already existing email
    @Test
    void testRegisterEmailAlreadyExists() {
        // 1. Create a RegisterRequest with duplicate email
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");

        // 2. Mock repository behavior
        //    - Pretend that this email already exists
        when(repository.existsByEmail(request.getEmail())).thenReturn(true);

        // 3. Assert that calling register() throws EmailAlreadyExistsException
        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.register(request);
        });

        // 4. Verify repository.save() was never called
        verify(repository, never()).save(any(User.class));
    }
}
