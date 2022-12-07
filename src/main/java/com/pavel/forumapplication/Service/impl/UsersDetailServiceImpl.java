package com.pavel.forumapplication.Service.impl;

import com.pavel.forumapplication.Dto.UsersDetailDto;
import com.pavel.forumapplication.Entity.UsersDetailEntity;
import com.pavel.forumapplication.Exception.NotFoundException;
import com.pavel.forumapplication.Mapper.UsersDetailMapper;
import com.pavel.forumapplication.Repository.UsersDetailRepository;
import com.pavel.forumapplication.Service.UsersDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersDetailServiceImpl implements UsersDetailService {

    private final UsersDetailRepository usersDetailRepository;

    public UsersDetailServiceImpl(UsersDetailRepository usersDetailRepository) {
        this.usersDetailRepository = usersDetailRepository;
    }

    @Override
    public List<UsersDetailDto> GetAllUsersDetail(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<UsersDetailEntity> usersDetailEntities = usersDetailRepository.findAll(pageable);
        if(usersDetailEntities.isEmpty()){
            throw new NotFoundException("List users detail empty!");
        }
        return usersDetailEntities
                .stream()
                .map(UsersDetailMapper.INSTANCE::USERS_DETAIL_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsersDetailDto GetUsersDetailById(Long user_id) {
        UsersDetailEntity usersDetail = usersDetailRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user detail id!");
                });
        return UsersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetail);
    }

    @Override
    @Transactional
    public UsersDetailDto CreateUserDetail(UsersDetailEntity usersDetailEntity) {
        UsersDetailEntity usersDetail = usersDetailRepository
                .saveAndFlush(
                        UsersDetailEntity
                                .builder()
                                .name(usersDetailEntity.getName())
                                .surname(usersDetailEntity.getSurname())
                                .age(usersDetailEntity.getAge())
                                .description(usersDetailEntity.getDescription())
                                .birthday(usersDetailEntity.getBirthday())
                                .build()
                );
        return UsersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetail);
    }

    @Override
    @Transactional
    public UsersDetailDto EditUserDetail(Long user_id, UsersDetailEntity usersDetailEntity) {
        UsersDetailEntity usersDetail = usersDetailRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        usersDetail.setName(usersDetailEntity.getName());
        usersDetail.setSurname(usersDetailEntity.getSurname());
        usersDetail.setAge(usersDetailEntity.getAge());
        usersDetail.setDescription(usersDetailEntity.getDescription());
        usersDetail.setBirthday(usersDetailEntity.getBirthday());
        usersDetailRepository.save(usersDetail);
        return UsersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetail);
    }

    @Override
    @Transactional
    public UsersDetailDto DeleteUserDetail(Long user_id) {
        UsersDetailEntity usersDetail = usersDetailRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user id!");
                });
        usersDetailRepository.deleteById(user_id);
        return UsersDetailMapper.INSTANCE.USERS_DETAIL_DTO(usersDetail);
    }
}
