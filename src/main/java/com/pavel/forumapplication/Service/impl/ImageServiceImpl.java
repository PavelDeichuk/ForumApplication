package com.pavel.forumapplication.Service.impl;

import com.pavel.forumapplication.Dto.ImageDto;
import com.pavel.forumapplication.Entity.CommentEntity;
import com.pavel.forumapplication.Entity.ImageEntity;
import com.pavel.forumapplication.Entity.TopicEntity;
import com.pavel.forumapplication.Entity.UsersDetailEntity;
import com.pavel.forumapplication.Exception.NotFoundException;
import com.pavel.forumapplication.Mapper.CommentMapper;
import com.pavel.forumapplication.Mapper.ImageMapper;
import com.pavel.forumapplication.Repository.CommentRepository;
import com.pavel.forumapplication.Repository.ImageRepository;
import com.pavel.forumapplication.Repository.TopicRepository;
import com.pavel.forumapplication.Repository.UsersDetailRepository;
import com.pavel.forumapplication.Service.ImageSerivce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageSerivce {

    private final UsersDetailRepository usersDetailRepository;

    private final CommentRepository commentRepository;

    private final TopicRepository topicRepository;

    private final ImageRepository imageRepository;

    public ImageServiceImpl(UsersDetailRepository usersDetailRepository, CommentRepository commentRepository, TopicRepository topicRepository, ImageRepository imageRepository) {
        this.usersDetailRepository = usersDetailRepository;
        this.commentRepository = commentRepository;
        this.topicRepository = topicRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    @Transactional
    public ImageDto AddImageUsersDetail(Long users_id, MultipartFile multipartFile) throws IOException {
        UsersDetailEntity usersDetailEntity = usersDetailRepository
                .findById(users_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user id!");
                });
        if(!multipartFile.isEmpty()){
            File file = new File("images//" + multipartFile.getOriginalFilename());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            ImageEntity imageEntity = imageRepository
                    .save(
                            ImageEntity
                                    .builder()
                                    .name(multipartFile.getOriginalFilename())
                                    .bytes(multipartFile.getBytes())
                                    .usersDetailEntity(usersDetailEntity)
                                    .build()
                    );
            usersDetailEntity.setImageEntity(imageEntity);
            usersDetailRepository.saveAndFlush(usersDetailEntity);
            return ImageMapper.INSTANCE.IMAGE_DTO(imageEntity);
        }
        return null;
    }

    @Override
    @Transactional
    public ImageDto AddImageComment(Long comment_id, MultipartFile multipartFile) throws IOException {
        CommentEntity commentEntity = commentRepository
                .findById(comment_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for comment id!");
                });
        if(!multipartFile.isEmpty()){
            File file = new File("images//" + multipartFile.getOriginalFilename());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            ImageEntity imageEntity = imageRepository
                    .save(
                            ImageEntity
                                    .builder()
                                    .name(multipartFile.getOriginalFilename())
                                    .bytes(multipartFile.getBytes())
                                    .commentEntity(commentEntity)
                                    .build()
                    );
            commentEntity.setImageEntity(imageEntity);
            commentRepository.saveAndFlush(commentEntity);
            return ImageMapper.INSTANCE.IMAGE_DTO(imageEntity);
        }
        return null;
    }

    @Override
    @Transactional
    public ImageDto AddImageTopic(Long topic_id, MultipartFile multipartFile) throws IOException {
        TopicEntity topic = topicRepository
                .findById(topic_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for topic id!");
                });
        if(!multipartFile.isEmpty()){
            File file = new File("images// " + multipartFile.getOriginalFilename());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            ImageEntity imageEntity = imageRepository
                    .saveAndFlush(
                            ImageEntity
                                    .builder()
                                    .name(multipartFile.getOriginalFilename())
                                    .bytes(multipartFile.getBytes())
                                    .topicEntity(topic)
                                    .build()
                    );
            return ImageMapper.INSTANCE.IMAGE_DTO(imageEntity);
        }
        return null;
    }

    @Override
    public byte[] GetImageByName(String name) {
        ImageEntity imageEntity = imageRepository
                .findByName(name)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found image name!");
                });
        return imageEntity.getBytes();
    }
}
