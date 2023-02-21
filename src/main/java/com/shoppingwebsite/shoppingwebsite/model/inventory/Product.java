package com.shoppingwebsite.shoppingwebsite.model.inventory;

public class Product {
    private Long id;
    private String name;
    private String viewDescription;
    private String fullDescription;
    private Double price;
    private String img;
    private Long quantity;

    public Product(Long id, String name, String viewDescription,String fullDescription , Double price, String img, Long quantity) {
        this.id = id;
        this.name = name;
        this.viewDescription = viewDescription;
        this.fullDescription = fullDescription;
        this.price = price;
        this.img = img;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getViewDescription() {
        return viewDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public Double getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setViewDescription(String viewDescription) {
        this.viewDescription = viewDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

