package net.accessory.paragram.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @Column(name = "image_url")
    private String image_url;

    private Double price = 0.0d;

    private Double sale_rate = 0.0d;

    private int quantity;

    private String author;

    private boolean active;

    private String created_at;

    private String last_edited_at;

    @OneToMany(targetEntity = ProductImage.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore()
    private List<ProductImage> images;

    @Column(name = "description", length = 1000)
    private String description;

    public Product() {
    }

    @ManyToOne()
    @JoinColumn(name = "brand", referencedColumnName = "brand_id")
    private Brand brand;

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Double getSale_price() {
        return price-(price*(sale_rate/100));
    }

    public void setSale_price(Double sale_price) {
    }

    public Double getSale_rate() {
        return sale_rate;
    }

    public void setSale_rate(Double sale_rate) {
        this.sale_rate = sale_rate;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLast_edited_at() {
        return last_edited_at;
    }

    public void setLast_edited_at(String last_edited_at) {
        this.last_edited_at = last_edited_at;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
