package com.wepa.justnews.controllers;

import com.wepa.justnews.Domain.Category;
import com.wepa.justnews.Domain.Image;
import com.wepa.justnews.Domain.News;
import com.wepa.justnews.Domain.Writer;
import com.wepa.justnews.Repository.CategoryRepository;
import com.wepa.justnews.Repository.NewsRepository;
import com.wepa.justnews.Repository.WriterRepository;
import com.wepa.justnews.service.NewsService;
import com.wepa.justnews.service.ValidatorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class NewsController{

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private ValidatorServices validator;



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

        news.sort(Comparator.comparing(News::getDate));
        model.addAttribute("news", news);

        if (news.size() >= 5) {
            news = news.subList(0, 5);
        }
        model.addAttribute("news", news);
        return "news";
    }

    @GetMapping("/news/{categoryName}")
    public String getCategory(@PathVariable String categoryName, Model model) {
        Category category = categoryRepository.findByName(categoryName);

        List<News> news = new ArrayList<>();
        if(category != null) {
            news = category.getRelatedNews();
        }

        model.addAttribute("news", news);
        return "news";
    }

    @PostMapping("/create")
    @Transactional
    public String createNews(Model model, @RequestParam String title, @RequestParam String leadParagraph, @RequestParam String text,
                             @RequestParam("image") MultipartFile image, @RequestParam String categoryName, @RequestParam String writerName, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date publishDate) throws IOException {

        News create = new News();
        create.setTitle(title);
        create.setLeadParagraph(leadParagraph);
        create.setText(text);
        create.setDate(publishDate);

        //Kirjoittajan asettaminen
        Writer savedWriter = writerRepository.findByName(writerName);

        if(savedWriter == null) {
            Writer newWriter = new Writer();
            newWriter.setName(writerName);
            savedWriter = writerRepository.saveAndFlush(newWriter);
        }

        ArrayList<Writer> relatedWriters = new ArrayList<>();

        relatedWriters.add(savedWriter);

        create.setRelatedWriter(relatedWriters);


        //Kategorian asettamminen
        Category savedCategory = categoryRepository.findByName(categoryName);

        if(savedCategory == null) {
            Category newCategory = new Category();
            newCategory.setName(categoryName);
            savedCategory = categoryRepository.saveAndFlush(newCategory);
        }

        ArrayList<Category> relatedCategories = new ArrayList<>();

        relatedCategories.add(savedCategory);

        create.setRelatedCategory(relatedCategories);


        Image savedImage;

        if(image.getSize() > 0) {
            savedImage = newsService.saveImage(image);
            create.setRelatedImage(savedImage);
        }
        News createdNews;

        if(validator.validateNews(create)) {
            createdNews = newsRepository.save(create);
        } else {
            model.addAttribute("errors", validator.getErrors());
            return "create";
        }

        List<News> relatedNews = savedCategory.getRelatedNews();
        if(relatedNews != null) {
            relatedNews.add(createdNews);
        } else {
            relatedNews = new ArrayList<>();
            relatedNews.add(createdNews);
        }

        savedCategory.setRelatedNews(relatedNews);

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


    @PostMapping("/edit/news")
    public String saveEditNews(Model model, @RequestParam String title, @RequestParam String leadParagraph, @RequestParam String text,
                              @RequestParam Long id, @RequestParam("image") MultipartFile image, @RequestParam String category, @RequestParam String writerName) throws IOException  {

        News editNews = newsRepository.getOne(id);
        editNews.setTitle(title);
        editNews.setLeadParagraph(leadParagraph);
        editNews.setText(text);
        ArrayList<Category> relatedCategory = new ArrayList<>();

        if(writerName != null) {
            Writer existingWriter = writerRepository.findByName(writerName);
            List<Writer> authors = new ArrayList<>();

            if(existingWriter != null && !existingWriter.getName().equals(writerName)) {
                authors.add(existingWriter);
            } else if(existingWriter == null) {
                Writer newAuthor = new Writer();
                newAuthor.setName(writerName);
                List<News> relatedNewsForNewAuthor = new ArrayList<>();
                relatedNewsForNewAuthor.add(editNews);
                newAuthor.setRelatedNews(relatedNewsForNewAuthor);
                newAuthor = writerRepository.saveAndFlush(newAuthor);
                authors.add(newAuthor);
            }

            editNews.setRelatedWriter(authors);
        }

        if(category != null){
            Category savedCategory = categoryRepository.findByName(category);

            if(savedCategory != null) {
                relatedCategory.add(savedCategory);
            } else {
                savedCategory = new Category();
                savedCategory.setName(category);
                List<News> relatedNews = new ArrayList<>();
                relatedNews.add(editNews);
                savedCategory.setRelatedNews(relatedNews);

                savedCategory = categoryRepository.saveAndFlush(savedCategory);

                relatedCategory.add(savedCategory);
            }

            editNews.setRelatedCategory(relatedCategory);
        }

        Image savedImage;

        if(image != null) {
            savedImage = newsService.saveImage(image);
            editNews.setRelatedImage(savedImage);
        }

        newsRepository.saveAndFlush(editNews);

        return "redirect:/news";
    }

    @DeleteMapping("/delete/news/{id}")
    public String deleteNews(@PathVariable long id) {
        News news = newsRepository.findOne(id);
        news.setRelatedCategory(new ArrayList<Category>());
        newsRepository.delete(id);
        return "redirect:/news";
    }

}






