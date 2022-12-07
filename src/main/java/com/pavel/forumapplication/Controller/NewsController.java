package com.pavel.forumapplication.Controller;

import com.pavel.forumapplication.Dto.NewsDto;
import com.pavel.forumapplication.Entity.NewsEntity;
import com.pavel.forumapplication.Service.NewsService;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

    private static final String NEWS_ID = "/{news_id}";

    private final NewsService newsService;


    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NewsDto> GetAllNews(@RequestParam(value = "page",defaultValue = "0") int page,
                                    @RequestParam(value = "size", defaultValue = "10") int size){
        return newsService.GetAllNews(page,size);
    }
    @RequestMapping(value = NEWS_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public NewsDto GetNewsById(@PathVariable Long news_id){
        return newsService.GetNewsById(news_id);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public NewsDto CreateNews(@RequestBody NewsEntity newsEntity,
                              @AuthenticationPrincipal String username){
        return newsService.CreateNews(newsEntity, username);
    }

    @RequestMapping(value = NEWS_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public NewsDto EditNews(@PathVariable Long news_id, @RequestBody NewsEntity newsEntity){
        return newsService.EditNews(news_id, newsEntity);
    }

    @RequestMapping(value = NEWS_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public NewsDto DeleteNews(@PathVariable Long news_id){
        return newsService.DeleteNews(news_id);
    }

}
