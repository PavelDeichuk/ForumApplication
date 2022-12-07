package com.pavel.forumapplication.Service;

import com.pavel.forumapplication.Dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ImageSerivce {
    ImageDto AddImageUsersDetail(Long users_id,MultipartFile multipartFile) throws IOException;

    ImageDto AddImageComment(Long comment_id, MultipartFile multipartFile);

    ImageDto AddImageTopic(Long topic_id, MultipartFile multipartFile) throws IOException;

    byte[] GetImageByName(String name);
}
