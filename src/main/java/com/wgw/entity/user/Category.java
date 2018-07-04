package com.wgw.entity.user;

import java.util.List;

public class Category {
    private Integer id;
    private String name;
    private Category parent;
    List<Category> childs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<Category> getCategories() {
        return childs;
    }

    public void setCategories(List<Category> categories) {
        this.childs = categories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

}
