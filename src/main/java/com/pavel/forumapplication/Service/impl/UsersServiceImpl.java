package com.pavel.forumapplication.Service.impl;

import com.pavel.forumapplication.Dto.UsersDto;
import com.pavel.forumapplication.Email.EmailSender;
import com.pavel.forumapplication.Entity.UsersDetailEntity;
import com.pavel.forumapplication.Entity.UsersEntity;
import com.pavel.forumapplication.Exception.BadRequestException;
import com.pavel.forumapplication.Exception.NotFoundException;
import com.pavel.forumapplication.Mapper.UsersMapper;
import com.pavel.forumapplication.Repository.UsersDetailRepository;
import com.pavel.forumapplication.Repository.UsersRepository;
import com.pavel.forumapplication.Service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final UsersDetailRepository usersDetailRepository;


    private final EmailSender emailSender;


    public UsersServiceImpl(UsersRepository usersRepository, UsersDetailRepository usersDetailRepository, EmailSender emailSender) {
        this.usersRepository = usersRepository;
        this.usersDetailRepository = usersDetailRepository;
        this.emailSender = emailSender;
    }

    @Override
    public List<UsersDto> GetAllUsers(int number, int size) {
        Pageable page = PageRequest.of(number,size);
        Page<UsersEntity> usersEntities = usersRepository.findAll(page);
        if(usersEntities.isEmpty()){
            throw new NotFoundException("users list is empty!");
        }
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
                    throw new NotFoundException("Not found for user id");
                });
        return UsersMapper.INSTANCE.USERS_DTO(usersEntity);
    }

    @Override
    public UsersDto ActivateAccount(String activation) {
        UsersEntity users = usersRepository
                .findByActivation(activation)
                .orElseThrow(() -> {
                    throw new BadRequestException("Error to found activation code!");
                });
        users.setRole("USER");
        users.setActivation(null);
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    @Transactional
    public UsersDto CreateUser(UsersEntity usersEntity, BindingResult bindingResult) {
        usersRepository
                .findByUsername(usersEntity.getUsername())
                .ifPresent(username -> {
                    throw new BadRequestException("username is exist!");
                });
        usersRepository
                .findByEmail(usersEntity.getEmail())
                .ifPresent(email -> {
                    throw new BadRequestException("email is exist!");
                });
        if(!Objects.equals(usersEntity.getPassword(), usersEntity.getPassword2())){
            throw new BadRequestException("password is not equals!");
        }
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError errors : fieldErrors){
                throw new BadRequestException(errors.getObjectName() + " " + errors.getDefaultMessage());
            }
        }
        UsersDetailEntity usersDetail = new UsersDetailEntity();
        UsersEntity users = usersRepository
                .save(
                        UsersEntity
                                .builder()
                                .username(usersEntity.getUsername())
                                .password(usersEntity.getPassword())
                                .role("QUEST")
                                .email(usersEntity.getEmail())
                                .usersDetailEntity(usersDetail)
                                .build()
                );
        usersDetail.setUsersEntity(users);
        usersDetailRepository.saveAndFlush(usersDetail);
        //emailSender.Send("deychuk@inbox.ru", usersEntity.getEmail(), "Activate account", usersEntity.getActivation());
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    @Transactional
    public UsersDto ChangePassword(String email) {
        UsersEntity users = usersRepository
                .findByEmail(email)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for email!");
                });
        users.setPasstoken(UUID.randomUUID().toString());
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    @Transactional
    public UsersDto ChangeEmail(String email) {
        // send to code to new email
       return null;
    }

    @Override
    public UsersDto ActivateNewEmail(String activation_email) {
        UsersEntity users = usersRepository
                .findByEmailtoken(activation_email)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for email token");
                });
        users.setEmail(null);
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    @Transactional
    public UsersDto ResetPassword(UsersEntity usersEntity, String pass_token) {
        UsersEntity users = usersRepository
                .findByPasstoken(pass_token)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for pass token!");
                });
        if(Objects.equals(usersEntity.getPassword(), usersEntity.getPassword2())){
            throw new BadRequestException("Password is not equals!");
        }
        users.setPassword(usersEntity.getPassword());
        users.setPasstoken(null);
        usersRepository.save(users);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }

    @Override
    @Transactional
    public UsersDto EditUser(Long user_id, UsersEntity usersEntity, BindingResult bindingResult) {
        UsersEntity users_id = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        usersRepository
                .findByUsername(usersEntity.getUsername())
                .ifPresent(username -> {
                    throw new BadRequestException("Username is exist!");
                });
        if(bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error :  fieldErrors){
                throw new BadRequestException(error.getObjectName() + " " + error.getDefaultMessage());
            }
        }
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
                    throw new NotFoundException("Not found for user_id!");
                });
        usersRepository.deleteById(user_id);
        return UsersMapper.INSTANCE.USERS_DTO(users);
    }
}
