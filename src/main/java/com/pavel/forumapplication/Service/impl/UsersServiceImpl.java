package com.pavel.forumapplication.Service.impl;

import com.pavel.forumapplication.Dto.UsersDto;
import com.pavel.forumapplication.Entity.UsersEntity;
import com.pavel.forumapplication.Mapper.UsersMapper;
import com.pavel.forumapplication.Repository.UsersRepository;
import com.pavel.forumapplication.Service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<UsersDto> GetAllUsers(int number, int size) {
        Pageable page = PageRequest.of(number,size);
        Page<UsersEntity> usersEntities = usersRepository.findAll(page);
        return usersEntities
                .stream()
                .map(UsersMapper.INSTANCE::USERS_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsersDto GetUserById(Long user_id) {
        UsersEntity usersEntity = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for user id");
                });
        return UsersMapper.INSTANCE.USERS_DTO(usersEntity);
    }

    @Override
    public UsersDto ActivateAccount(String activation) {
        UsersEntity users = usersRepository
                .findByActivation(activation)
                .orElseThrow(() -> {
                    throw new RuntimeException("Error to found activation code!");
                });
        users.setRole("USER");
        users.setActivation(null);
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    @Transactional
    public UsersDto CreateUser(UsersEntity usersEntity) {
        usersRepository
                .findByUsername(usersEntity.getUsername())
                .ifPresent(username -> {
                    throw new RuntimeException("username is exist!");
                });
        usersRepository
                .findByEmail(usersEntity.getEmail())
                .ifPresent(email -> {
                    throw new RuntimeException("email is exist!");
                });
        if(!Objects.equals(usersEntity.getPassword(), usersEntity.getPassword2())){
            throw new RuntimeException("password is not equals!");
        }
        UsersEntity users = usersRepository
                .saveAndFlush(
                        UsersEntity
                                .builder()
                                .username(usersEntity.getUsername())
                                .password(usersEntity.getPassword())
                                .email(usersEntity.getEmail())
                                .role("QUEST")
                                .loginIsEmail(usersEntity.isLoginIsEmail())
                                .build()
                );
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    @Transactional
    public UsersDto ChangePassword(String email) {
        UsersEntity users = usersRepository
                .findByEmail(email)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for email!");
                });
        users.setPasstoken(UUID.randomUUID().toString());
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    @Transactional
    public UsersDto ChangeEmail(String email) {
        UsersEntity users = usersRepository
                .findByEmail(email)
                .orElseThrow(() -> {
                    throw new RuntimeException("Error login for username");
                });
        users.setEmailtoken(UUID.randomUUID().toString());
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    @Transactional
    public UsersDto ResetEmail(UsersEntity usersEntity, String email_token) {
        UsersEntity users = usersRepository
                .findByEmailtoken(email_token)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for email token!");
                });
        usersRepository
                .findByEmail(usersEntity.getEmail())
                .ifPresent(email -> {
                    throw new RuntimeException("Email is exist!");
                });
        if(Objects.equals(users.getEmail(), usersEntity.getEmail())){
            throw new RuntimeException("Email is already");
        }
        users.setEmail(usersEntity.getEmail());
        users.setEmailtoken(null);
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    @Transactional
    public UsersDto ResetPassword(UsersEntity usersEntity, String pass_token) {
        UsersEntity users = usersRepository
                .findByPasstoken(pass_token)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for pass token!");
                });
        if(Objects.equals(usersEntity.getPassword(), usersEntity.getPassword2())){
            throw new RuntimeException("Password is not equals!");
        }
        users.setPassword(usersEntity.getPassword());
        users.setPasstoken(null);
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    @Transactional
    public UsersDto EditUser(Long user_id, UsersEntity usersEntity) {
        UsersEntity users_id = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for user_id");
                });
        usersRepository
                .findByUsername(usersEntity.getUsername())
                .ifPresent(username -> {
                    throw new RuntimeException("Username is exist!");
                });
        users_id.setUsername(usersEntity.getUsername());
        users_id.setLoginIsEmail(usersEntity.isLoginIsEmail());
        usersRepository.save(users_id);
        return UsersMapper.INSTANCE.USERS_DTO(users_id);
    }

    @Override
    @Transactional
    public UsersDto DeleteUser(Long user_id) {
       UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for user_id!");
                });
        usersRepository.deleteById(user_id);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }
}
