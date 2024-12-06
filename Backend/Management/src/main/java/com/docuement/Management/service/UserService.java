package com.docuement.Management.service;

import com.docuement.Management.config.AuthConfig;
import com.docuement.Management.dto.req.UserCreateDto;
import com.docuement.Management.dto.resp.UserResponseDto;
import com.docuement.Management.entity.User;
import com.docuement.Management.exception.PasswordMismatch;
import com.docuement.Management.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthConfig authConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public UserResponseDto register(@NotNull UserCreateDto userCreateDto) {
        Optional<User> foundUser = this.userRepository.findByEmail(userCreateDto.getEmail());

        if (foundUser.isEmpty()) {
            System.out.println(userCreateDto.getPassword());
            System.out.println(userCreateDto.getRe_password());
            if (!userCreateDto.getPassword().equals(userCreateDto.getRe_password())) {
                throw new PasswordMismatch("Password is mismatched");
            }
            User user = this.userReqDtoToUserEntity(userCreateDto);
            user.setPassword(authConfig.passwordEncoder().encode(user.getPassword()));
            User createdUser = userRepository.save(user);
            return this.userEntityToUserRespDto(createdUser);
        } else {
            // User already exists, throw an exception
            throw new PasswordMismatch("User with email " + userCreateDto.getEmail() + " already exists");
        }
    }

    public User userReqDtoToUserEntity(UserCreateDto userReqDto){
        User user = this.modelMapper.map(userReqDto, User.class);
        return user;
    }
    public UserResponseDto userEntityToUserRespDto(User user){
        UserResponseDto userRespDto = this.modelMapper.map(user,UserResponseDto.class);
        return userRespDto;
    }

}
