package com.pavel.forumapplication.Service.impl;

import com.pavel.forumapplication.Dto.UsersDetailDto;
import com.pavel.forumapplication.Entity.UsersDetailEntity;
import com.pavel.forumapplication.Entity.UsersEntity;
import com.pavel.forumapplication.Mapper.UsersDetailMapper;
import com.pavel.forumapplication.Repository.UsersDetailRepository;
import com.pavel.forumapplication.Repository.UsersRepository;
import com.pavel.forumapplication.Service.UsersDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class UsersDetailServiceImpl implements UsersDetailService {

    private final UsersRepository usersRepository;

    private final UsersDetailRepository usersDetailRepository;

    public UsersDetailServiceImpl(UsersRepository usersRepository, UsersDetailRepository usersDetailRepository) {
        this.usersRepository = usersRepository;
        this.usersDetailRepository = usersDetailRepository;
    }

    @Override
    public List<UsersDetailDto> GetAllUsersDetail(int number, int size) {
        Pageable page = PageRequest.of(number,size);
        Page<UsersDetailEntity> usersDetailEntities = usersDetailRepository.findAll(page);
        if(usersDetailEntities.isEmpty()){
            throw new RuntimeException("Not found for user details list!");
        }
        return usersDetailEntities
                .stream()
                .map(UsersDetailMapper.INSTANCE::USERS_DETAIL_DTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsersDetailDto GetUserDetailById(Long user_id) {
        UsersEntity usersEntity = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for user id!");
                });
        UsersDetailEntity usersDetailEntity = usersDetailRepository
                .findByUsersEntity(usersEntity);
        return UsersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetailEntity);
    }

    @Override
    public UsersDetailDto CreateUserDetail(Long user_id, UsersDetailEntity usersDetailEntity) {
        UsersEntity usersEntity = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for user_id");
                });
        usersEntity.setUsersDetailEntity(usersDetailEntity);
        UsersDetailEntity usersDetail = usersDetailRepository
                .saveAndFlush(
                        UsersDetailEntity
                                .builder()
                                .name(usersDetailEntity.getName())
                                .surname(usersDetailEntity.getSurname())
                                .description(usersDetailEntity.getDescription())
                                .age(usersDetailEntity.getAge())
                                .usersEntity(usersEntity)
                                .build()
                );
        return UsersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetail);
    }

    @Override
    @Transactional
    public UsersDetailDto EditUserDetail(Long user_id, UsersDetailEntity usersDetailEntity) {
        UsersEntity usersEntity = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found for user_id");
                });
        UsersDetailEntity usersDetail = usersDetailRepository
                .findByUsersEntity(usersEntity);
        usersDetail.setAge(usersDetailEntity.getAge());
        usersDetail.setName(usersDetailEntity.getName());
        usersDetail.setSurname(usersDetailEntity.getSurname());
        usersDetail.setDescription(usersDetailEntity.getDescription());
        usersDetailRepository.save(usersDetail);
        return UsersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetail);
    }
}
