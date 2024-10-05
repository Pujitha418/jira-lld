package com.example.jira.services.user;

import com.example.jira.dto.user.AddRoleToUserRequestDto;
import com.example.jira.dto.user.AddRoleToUserResponseDto;
import com.example.jira.dto.user.CreateUserRequestDto;
import com.example.jira.dto.user.CreateUserResponseDto;
import com.example.jira.exceptions.RoleNotFoundException;
import com.example.jira.exceptions.UserNotFoundException;
import com.example.jira.models.user.Role;
import com.example.jira.models.user.User;
import com.example.jira.repository.user.RoleRepository;
import com.example.jira.repository.user.UserRepository;
import com.example.jira.transformers.UserDtoTransformer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Optional<User> getUserRecord(Long userId) {
        return userRepository.findById(userId);
    }

    public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        User user = new User();
        user.setEmail(createUserRequestDto.getEmail());
        user.setRole(null);
        User savedUser = userRepository.save(user);
        return UserDtoTransformer.transformUserEntityToCreateUserResponseDto(savedUser);
    }

    public AddRoleToUserResponseDto assignRoleToUser(AddRoleToUserRequestDto addRoleToUserRequestDto)
            throws UserNotFoundException, RoleNotFoundException {
        //user exists & role exists, assign
        System.out.println("addRoleToUserRequestDto.getUserEmail() = " + addRoleToUserRequestDto.getUserEmail());
        Optional<User> user = userRepository.findUserByEmail(addRoleToUserRequestDto.getUserEmail());
        if (user.isEmpty()) {
            throw new UserNotFoundException(addRoleToUserRequestDto.getUserEmail());
        }
        Optional<Role> role = roleRepository.findByMName(addRoleToUserRequestDto.getRole().toString());
        if (role.isEmpty()) {
            throw new RoleNotFoundException(addRoleToUserRequestDto.getRole().toString());
        }
        user.get().setRole(role.get());
        User savedUser = userRepository.save(user.get());
        return UserDtoTransformer.transformUserEntityToAddRoleToUserResponseDto(savedUser);
    }
}
