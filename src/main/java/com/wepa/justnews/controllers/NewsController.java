package com.wepa.justnews.controllers;

import com.wepa.justnews.Domain.Image;
import com.wepa.justnews.Domain.News;
import com.wepa.justnews.Repository.NewsRepository;
import com.wepa.justnews.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class NewsController{

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ImageService imageService;



    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/news";
    }

    @GetMapping("/create")
    public String create() {
        return "create";

    }

    @GetMapping("/news")
    public String getNews(Model model) {
        List<News> news = newsRepository.findAll();

        model.addAttribute("news", news);
        return "news";
    }

    @PostMapping("/create")
    @Transactional
    public String createNews(Model model, @RequestParam String title, @RequestParam String leadParagraph, @RequestParam String text,
                             @RequestParam("image") MultipartFile image) throws IOException {

        News create = new News();
        create.setTitle(title);
        create.setLeadParagraph(leadParagraph);
        create.setText(text);

        Image savedImage;

        if(image != null) {
            savedImage = imageService.saveImage(image);
            create.setRelatedImage(savedImage);
        }

        //create.setCategory(category);
        newsRepository.saveAndFlush(create);
        return "redirect:/news";
    }

    @GetMapping("/show/news/{id}")
    public String getShow(Model model, @PathVariable Long id){
        News show = newsRepository.findOne(id);
        model.addAttribute("show", show);
        return "show";
        }

    @GetMapping("/edit/news/{id}")
    public String editNewss(@PathVariable Long id, Model model) {
        News editNews = newsRepository.getOne(id);
        model.addAttribute("editNews", editNews);
        return "edit";
    }

   /* @GetMapping("/category")
    public String categoryChoices(Model model, @PathVariable String category) {
        List<News> news = newsRepository.findAllBy

        model.addAttribute("category", category);
        return "edit";
    }
*/
    @PostMapping("/edit/news")
    public String saveEditNews(Model model, @RequestParam String title, @RequestParam String leadParagraph, @RequestParam String text,
                              @RequestParam Long id, @RequestParam("image") MultipartFile image) throws IOException  {

        News editNews = newsRepository.getOne(id);
        editNews.setTitle(title);
        editNews.setLeadParagraph(leadParagraph);
        editNews.setText(text);

        Image savedImage;

        if(image != null) {
            savedImage = imageService.saveImage(image);
            editNews.setRelatedImage(savedImage);
        }

        newsRepository.saveAndFlush(editNews);

        return "redirect:/news";
    }

    @DeleteMapping("/delete/news/{id}")
    public String deleteNews(@PathVariable long id) {
        newsRepository.delete(id);
        return "redirect:/news";
    }

}






