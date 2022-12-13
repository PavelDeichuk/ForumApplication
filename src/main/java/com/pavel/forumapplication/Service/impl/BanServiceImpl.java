package com.pavel.forumapplication.Service.impl;

import com.pavel.forumapplication.Dto.BanDto;
import com.pavel.forumapplication.Entity.BanEntity;
import com.pavel.forumapplication.Entity.RulesEntity;
import com.pavel.forumapplication.Entity.UsersEntity;
import com.pavel.forumapplication.Exception.NotFoundException;
import com.pavel.forumapplication.Mapper.BanMapper;
import com.pavel.forumapplication.Repository.BanRepository;
import com.pavel.forumapplication.Repository.RulesRepository;
import com.pavel.forumapplication.Repository.UsersRepository;
import com.pavel.forumapplication.Service.BanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BanServiceImpl implements BanService {

    private final BanRepository banRepository;

    private final UsersRepository usersRepository;

    private final RulesRepository rulesRepository;



    public BanServiceImpl(BanRepository banRepository, UsersRepository usersRepository, RulesRepository rulesRepository) {
        this.banRepository = banRepository;
        this.usersRepository = usersRepository;
        this.rulesRepository = rulesRepository;
    }

    @Override
    public List<BanDto> GetAllBanUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<BanEntity> banEntities = banRepository.findAll(pageable);
        if(banEntities.isEmpty()){
            throw new NotFoundException("Ban list is empty!");
        }
        return banEntities
                .stream()
                .map(BanMapper.INSTANCE::BAN_DTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BanDto AddUserToBan(Long ban_user, Long author_ban, Long rule_ban) {
        UsersEntity banUser = usersRepository
                .findById(ban_user)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for ban user!");
                });
        UsersEntity authorBan = usersRepository
                .findById(author_ban)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for author ban");
                });
        RulesEntity rules = rulesRepository
                .findById(rule_ban)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for rule id");
                });
        BanEntity banEntity = BanEntity
                .builder()
                .usersEntity(banUser)
                .author_ban(authorBan)
                .rulesEntity(rules)
                .build();
        banRepository.save(banEntity);
        return BanMapper.INSTANCE.BAN_DTO(banEntity);
    }

    @Override
    @Transactional
    public BanDto DeleteBan(Long ban_user) {
        UsersEntity users = usersRepository
                .findById(ban_user)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user id banned!");
                });
        BanEntity byUsersEntityId = banRepository.findByUsersEntity_Id(users.getId());
        banRepository.deleteById(byUsersEntityId.getId());
        return BanMapper.INSTANCE.BAN_DTO(byUsersEntityId);
    }
}
