package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.dto.request.ChangePasswordRequest;
import com.sree.fitness_tracker.dto.request.UpdateUserRequest;
import com.sree.fitness_tracker.dto.response.MessageResponse;
import com.sree.fitness_tracker.dto.response.UserResponse;
import com.sree.fitness_tracker.entity.User;
import com.sree.fitness_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

private final UserRepository userRepository;



    @Override
    public UserResponse updateUserProfile(String email, UpdateUserRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(request.getName());


        userRepository.save(user);

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
    }

    @Override
    public MessageResponse changePassword(String email, ChangePasswordRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getPassword().equals(request.getOldPassword())){
            throw new RuntimeException("Old password incorrect");
        }

        user.setPassword(request.getNewPassword());

        userRepository.save(user);

        return new MessageResponse("Your password has been changed successfully!");
    }

    @Override
    public MessageResponse deleteUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setDeleted(true);
        user.setDeletedAt(LocalDateTime.now());
        user.setActive(false);

        userRepository.save(user);

        return new MessageResponse("User account deleted successfully");
    }

    @Override
    public User getUserEntityByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getUsername());
        response.setEmail(user.getEmail());


        return response;
    }

    }




