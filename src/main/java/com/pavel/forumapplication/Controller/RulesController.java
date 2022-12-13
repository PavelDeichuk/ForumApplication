package com.pavel.forumapplication.Controller;

import com.pavel.forumapplication.Dto.RulesDto;
import com.pavel.forumapplication.Entity.RulesEntity;
import com.pavel.forumapplication.Service.RulesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rules")
public class RulesController {
    private final RulesService rulesService;

    private static final String RULES_ID = "/{rules_id}";

    public RulesController(RulesService rulesService) {
        this.rulesService = rulesService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RulesDto> GetAllRules(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size){
        return rulesService.GetAllRules(page, size);
    }

    @RequestMapping(value = RULES_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public RulesDto GetRulesById(@PathVariable Long rules_id){
        return rulesService.GetRulesById(rules_id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RulesDto CreateRules(@RequestBody RulesEntity rulesEntity){
        return rulesService.CreateRules(rulesEntity);
    }

    @RequestMapping(value = RULES_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public RulesDto EditRules(@PathVariable Long rules_id,
                              @RequestBody RulesEntity rulesEntity){
        return rulesService.EditRules(rules_id, rulesEntity);
    }

    @RequestMapping(value = RULES_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RulesDto DeleteRules(@PathVariable Long rules_id){
        return rulesService.DeleteRules(rules_id);
    }
}
