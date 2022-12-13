package com.pavel.forumapplication.Service.impl;

import com.pavel.forumapplication.Dto.RulesDto;
import com.pavel.forumapplication.Entity.RulesEntity;
import com.pavel.forumapplication.Exception.BadRequestException;
import com.pavel.forumapplication.Exception.NotFoundException;
import com.pavel.forumapplication.Mapper.RulesMapper;
import com.pavel.forumapplication.Repository.RulesRepository;
import com.pavel.forumapplication.Service.RulesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RulesServiceImpl implements RulesService {

    private final RulesRepository rulesRepository;

    public RulesServiceImpl(RulesRepository rulesRepository) {
        this.rulesRepository = rulesRepository;
    }

    @Override
    public List<RulesDto> GetAllRules(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<RulesEntity> rulesEntities = rulesRepository.findAll(pageable);
        if(rulesEntities.isEmpty()){
            throw new NotFoundException("list rules is empty!");
        }
        return rulesEntities
                .stream()
                .map(RulesMapper.INSTANCE::RULES_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public RulesDto GetRulesById(Long rules_id) {
        RulesEntity rulesEntity = rulesRepository
                .findById(rules_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for rules id!");
                });
        return RulesMapper.INSTANCE.RULES_DTO(rulesEntity);
    }

    @Override
    @Transactional
    public RulesDto CreateRules(RulesEntity rulesEntity) {
//        rulesRepository
//                .findById_rule(rulesEntity.getId_rule())
//                .ifPresent(ruleid -> {
//                    throw new BadRequestException("rule id is exist!");
//                });
        RulesEntity rules = rulesRepository
                .save(
                        RulesEntity
                                .builder()
                                .title(rulesEntity.getTitle())
                                .description(rulesEntity.getDescription())
                                .id_rule(rulesEntity.getId_rule())
                                .build()
                );
        return RulesMapper.INSTANCE.RULES_DTO(rules);
    }

    @Override
    @Transactional
    public RulesDto EditRules(Long rules_id, RulesEntity rulesEntity) {
        RulesEntity rules = rulesRepository
                .findById(rules_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for rules id!");
                });
//        rulesRepository
//                .findById_rule(rulesEntity.getId_rule())
//                .ifPresent(rule_id -> {
//                    throw new BadRequestException("rule id is exist!");
//                });
        if(!Objects.equals(rules.getId_rule(), rulesEntity.getId_rule())){
            throw new BadRequestException("rule id is not equals!");
        }
        rules.setTitle(rulesEntity.getTitle());
        rules.setDescription(rulesEntity.getDescription());
        rulesRepository.save(rules);
        return RulesMapper.INSTANCE.RULES_DTO(rules);
    }

    @Override
    @Transactional
    public RulesDto DeleteRules(Long rules_id) {
        RulesEntity rules = rulesRepository
                .findById(rules_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("rules id is not found");
                });
        rulesRepository.deleteById(rules_id);
        return RulesMapper.INSTANCE.RULES_DTO(rules);
    }
}
