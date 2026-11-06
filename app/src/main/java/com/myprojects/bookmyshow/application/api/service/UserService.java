package com.myprojects.bookmyshow.application.api.service;

import com.myprojects.bookmyshow.application.api.dto.UserConfigRequest;
import com.myprojects.bookmyshow.application.api.dto.UserConfigResponse;
import com.myprojects.bookmyshow.application.api.entities.User;
import com.myprojects.bookmyshow.application.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserConfigResponse createUserConfig(UserConfigRequest userConfigRequest) {
    var user = toUser(userConfigRequest);
    var savedUser = userRepository.save(user);
    return getUserConfigResponse(savedUser);
  }

  public UserConfigResponse getUserById(String userId) {
    return userRepository.findById(userId)
        .map(this::getUserConfigResponse)
        .orElseThrow(() -> new RuntimeException("User not found: " + userId));
  }

  public List<UserConfigResponse> getAllUsers() {
    return userRepository.findAll().stream()
        .map(this::getUserConfigResponse)
        .toList();
  }

  private UserConfigResponse getUserConfigResponse(User savedUser) {
    return UserConfigResponse.builder()
        .name(savedUser.getName())
        .phone(savedUser.getPhone())
        .age(savedUser.getAge())
        .email(savedUser.getEmail())
        .build();
  }

  private User toUser(UserConfigRequest userConfigRequest) {
    return User.builder().
        id(generateUserId()).
        name(userConfigRequest.name()).
        phone(userConfigRequest.phone()).
        age(userConfigRequest.age()).
        email(userConfigRequest.email()).
        build();
  }

  private String generateUserId() {
    return "UserID_" + UUID.randomUUID().toString();
  }

}
