package com.pavel.forumapplication.Controller;

import com.pavel.forumapplication.Dto.ImageDto;
import com.pavel.forumapplication.Service.ImageSerivce;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {

    private static final String TOPIC_ID = "/topic/{topic_id}";

    private static final String USERS_ID = "/users/{users_id}";
    private final ImageSerivce imageSerivce;


    public ImageController(ImageSerivce imageSerivce) {
        this.imageSerivce = imageSerivce;
    }

    @RequestMapping(value = USERS_ID, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageDto UsersImage(@PathVariable Long users_id,
                               @RequestParam("file")MultipartFile multipartFile) throws IOException {
        return imageSerivce.AddImageUsersDetail(users_id,multipartFile);
    }
}
