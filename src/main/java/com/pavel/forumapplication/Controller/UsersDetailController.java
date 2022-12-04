package com.pavel.forumapplication.Controller;

import com.pavel.forumapplication.Dto.UsersDetailDto;
import com.pavel.forumapplication.Entity.UsersDetailEntity;
import com.pavel.forumapplication.Service.UsersDetailService;
import org.mapstruct.control.MappingControl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users-detail")
public class UsersDetailController {

    private static final String USER_ID = "/{user_id}";
    private final UsersDetailService usersDetailService;

    public UsersDetailController(UsersDetailService usersDetailService) {
        this.usersDetailService = usersDetailService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsersDetailDto> GetAllUsersDetail(@RequestParam(value = "number", defaultValue = "1") int number,
                                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        return usersDetailService.GetAllUsersDetail(number,size);
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDetailDto GetUserDetailById(@PathVariable Long user_id){
        return usersDetailService.GetUserDetailById(user_id);
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDetailDto CreateUserDetail(@PathVariable Long user_id,
                                           @RequestBody UsersDetailEntity usersDetailEntity){
        return usersDetailService.CreateUserDetail(user_id, usersDetailEntity);
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDetailDto EditUserDetail(@PathVariable Long user_id,
                                         @RequestBody UsersDetailEntity usersDetailEntity){
        return usersDetailService.EditUserDetail(user_id,usersDetailEntity);
    }
}
