package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.RulesDto;
import com.pavel.forumapplication.Entity.RulesEntity;

import java.util.List;

public interface RulesService {
    List<RulesDto> GetAllRules(int page, int size);

    RulesDto GetRulesById(Long rules_id);

    RulesDto CreateRules(RulesEntity rulesEntity);

    RulesDto EditRules(Long rules_id, RulesEntity rulesEntity);

    RulesDto DeleteRules(Long rules_id);
}
