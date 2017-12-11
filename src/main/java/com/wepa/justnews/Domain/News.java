package com.wepa.justnews.Domain;


import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;


@Entity
public class News extends AbstractPersistable<Long> {

    private String title;
    private String leadParagraph;
    private String text;
    private Date date;

    @OneToOne
    private Image relatedImage;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE })
    private List<Category> relatedCategory;

    @ManyToMany
    private List<Writer> relatedWriter;

    public List<Writer> getRelatedWriter() {
        return relatedWriter;
    }

    public List<Category> getRelatedCategory() {
        return relatedCategory;
    }

    @Transactional
    public void setRelatedCategory(List<Category> relatedCategory) {
        this.relatedCategory = relatedCategory;
    }

    @Transactional
    public void setRelatedWriter(List<Writer> relatedWriter) {
        this.relatedWriter = relatedWriter;
    }

    public Image getRelatedImage() {
        return relatedImage;
    }

    @Transactional
    public void setRelatedImage(Image relatedImage) {
        this.relatedImage = relatedImage;
    }


    public String getTitle() {
        return title;
    }

    @Transactional
    public void setTitle(String title) {
        this.title = title;
    }


    public String getLeadParagraph() {
        return leadParagraph;
    }

    @Transactional
    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }


    public String getText() {
        return text;
    }

    @Transactional
    public void setText(String text) {
        this.text = text;
    }



    public Date getDate() {
        return date;
    }

    @Transactional
    public void setDate(Date date) {
        this.date = date;
    }

}