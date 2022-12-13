package com.pavel.forumapplication.Controller;

import com.pavel.forumapplication.Dto.BanDto;
import com.pavel.forumapplication.Service.BanService;
import org.mapstruct.control.MappingControl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ban")
public class BanController {

    private static final String USER_ID = "/{user_id}";
    private final BanService banService;

    public BanController(BanService banService) {
        this.banService = banService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private List<BanDto> GetAllBanUsers(@RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size){
        return banService.GetAllBanUsers(page,size);
    }

//    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public BanDto BanUser(@RequestParam(value = "user", required = true) Long user_id,
//                          @RequestParam(value = "author", required = true) Long author_id,
//                          @RequestParam(value = "rule", required = true) Long rules_id){
//        return banService.AddUserToBan(user_id, author_id, rules_id);
//    }

    @RequestMapping(value = USER_ID,method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BanDto DeleteUser(@PathVariable Long user_id){
        return banService.DeleteBan(user_id);
    }
}
