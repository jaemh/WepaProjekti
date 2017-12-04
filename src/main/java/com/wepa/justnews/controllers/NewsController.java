package com.wepa.justnews.controllers;


        import com.wepa.justnews.Domain.News;
        import com.wepa.justnews.Repository.NewsRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import java.util.Date;


@Controller
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/")
    public String getNews(Model model){
        model.addAttribute(newsRepository.findAll());
        return "news";
    }

    @GetMapping("/news/add")
    public String addNewsForm(Model model){
        return "addNews";
    }

    @PostMapping("/news/add")
    public String addNews(Model model, @RequestParam String title, @RequestParam String leadParagraph, @RequestParam String text,
                          @RequestParam String image, @RequestParam Date date) {

        News addNews = new News();

        addNews.setTitle(title);
        addNews.setLeadParagraph(leadParagraph);
        addNews.setText(text);
        addNews.setImage(image);
        addNews.setDate(date);

        return "addNews";

    }

    @GetMapping("/editNews/{id}")
    public String editNews(@PathVariable Long id, Model model) {
        News editNews = newsRepository.findOne(id);
        model.addAttribute("editNews", editNews);
        return "editNews";
    }

    @PostMapping("/news/edit")
    public String savEditNews(Model model, @RequestParam String title, @RequestParam String leadParagraph, @RequestParam String text,
                              @RequestParam String image, @RequestParam Date date, @PathVariable Long id) {

        News saveEditNews = newsRepository.getOne(id);
        saveEditNews.setTitle(title);
        saveEditNews.setLeadParagraph(leadParagraph);
        saveEditNews.setImage(image);
        saveEditNews.setText(text);
        saveEditNews.setDate(date);

        return "editNews";
    }

    @DeleteMapping("/news/delete/{id}")
    public String deleteNews(@PathVariable long id) {
        newsRepository.delete(id);
        return "redirect:/news";
    }

}






