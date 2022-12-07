package com.pavel.forumapplication.Controller;

import com.pavel.forumapplication.Dto.UsersDetailDto;
import com.pavel.forumapplication.Entity.UsersDetailEntity;
import com.pavel.forumapplication.Service.UsersDetailService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-detail")
public class UsersDetailController {

    private static final String USER_DETAIL_ID = "/{user_id}";
    private final UsersDetailService usersDetailService;

    public UsersDetailController(UsersDetailService usersDetailService) {
        this.usersDetailService = usersDetailService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsersDetailDto> GetAllUsersDetail(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "size", defaultValue = "10") int size){
        return usersDetailService.GetAllUsersDetail(page,size);
    }

    @RequestMapping(value = USER_DETAIL_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDetailDto GetUserDetailById(@PathVariable Long user_id){
        return usersDetailService.GetUsersDetailById(user_id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDetailDto CreateUserDetail(@RequestBody UsersDetailEntity usersDetailEntity){
        return usersDetailService.CreateUserDetail(usersDetailEntity);
    }

    @RequestMapping(value = USER_DETAIL_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDetailDto EditUser(@PathVariable Long user_id, @RequestBody UsersDetailEntity usersDetailEntity){
        return usersDetailService.EditUserDetail(user_id, usersDetailEntity);
    }

    @RequestMapping(value = USER_DETAIL_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDetailDto DeleteUser(@PathVariable Long user_id){
        return usersDetailService.DeleteUserDetail(user_id);
    }
}
