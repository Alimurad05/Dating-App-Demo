package az.turing.tinderapp.mapper;

import az.turing.tinderapp.dto.UserDto;
import az.turing.tinderapp.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements EntityMapper<User,UserDto>{
    @Override
    public User toEntity(UserDto dto) {
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }


    @Override
    public UserDto toDto(User entity) {
        return UserDto.builder()
                .username(entity.getUsername())
                .email(entity.getEmail())
                .build();
    }
}
