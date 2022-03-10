package com.github.olegik1719.learnup.operasales.lesson17.model;

public class Opera {
    private String name;
    private String author;
    private String description;

    public Opera() {
    }

    public Opera(String name, String author, String description) {
        this.name = name;
        this.author = author;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
