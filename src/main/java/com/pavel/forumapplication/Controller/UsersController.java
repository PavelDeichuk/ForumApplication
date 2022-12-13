package com.pavel.forumapplication.Controller;

import com.pavel.forumapplication.Dto.UsersDto;
import com.pavel.forumapplication.Entity.UsersEntity;
import com.pavel.forumapplication.Service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private static final String USER_ID = "/{user_id}";

    private static final String ACTIVATE = "/activate";

    private static final String CHANGE = "/change";

    private static final String RESET = "/reset";
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsersDto> GetAllUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "10") int size){
        return usersService.GetAllUsers(page, size);
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto GetUserById(@PathVariable Long user_id){
        return usersService.GetUserById(user_id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto CreateUser(@Valid @RequestBody UsersEntity usersEntity, BindingResult bindingResult){
        return usersService.CreateUser(usersEntity, bindingResult);
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto EditUser(@PathVariable Long user_id,
                             @Valid @RequestBody UsersEntity usersEntity,
                             BindingResult bindingResult){
        return usersService.EditUser(user_id, usersEntity, bindingResult);
    }

    @RequestMapping(value = ACTIVATE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto ActivateUser(@RequestParam("user") String activation){
        return usersService.ActivateAccount(activation);
    }

    @RequestMapping(value = CHANGE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto ChangePassword(@RequestParam("email") String email){
        return usersService.ChangePassword(email);
    }

    @RequestMapping(value = RESET,method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto ResetPassword(@RequestBody UsersEntity usersEntity,
                                  @RequestParam("pass-token") String pass_token){
        return usersService.ResetPassword(usersEntity,pass_token);
    }

    @RequestMapping(value = USER_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersDto DeleteUser(@PathVariable Long user_id){
        return usersService.DeleteUser(user_id);
    }
}
