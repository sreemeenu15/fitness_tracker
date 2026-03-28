package com.sree.fitness_tracker.service;

import com.sree.fitness_tracker.dto.request.ChangePasswordRequest;
import com.sree.fitness_tracker.dto.request.DeleteRequest;
import com.sree.fitness_tracker.dto.request.UpdateUserRequest;
import com.sree.fitness_tracker.dto.response.MessageResponse;
import com.sree.fitness_tracker.dto.response.UserResponse;
import com.sree.fitness_tracker.entity.User;

public interface UserService {

    UserResponse getUserByEmail(String email);

    UserResponse updateUserProfile(String email, UpdateUserRequest request);

    MessageResponse changePassword(String email, ChangePasswordRequest request);

    MessageResponse deleteUser(String email);

    User getUserEntityByEmail(String email);

}
