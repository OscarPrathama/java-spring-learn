package com.coba.spring_restapi_1.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_product")
/**
 * Serializable ??? -> https://www.baeldung.com/jpa-entities-serializable#:~:text=Serializable%20is%20one%20of%20the,store%20them%20in%20persistent%20memory.
 * */
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required !")
    @Column(name = "product_name", nullable = false, length = 100)
    private String name;

    @NotEmpty(message = "Description is required !")
    @Lob // long desc
    @Column(name = "product_description", nullable = true, length = 255)
    private String description;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    private Category category;

    @ManyToMany
    @JoinTable(
        name = "tbl_product_supplier",
        joinColumns = @JoinColumn(name="product_id"),
        inverseJoinColumns = @JoinColumn(name="supplier_id")
    )
    // @JsonManagedReference
    private Set<Supplier> suppliers;


    /* CONSTRUCTOR */
    public Product() {
    }

    public Product(Long id, String name, String desc, double price) {
        this.id = id;
        this.name = name;
        this.description = desc;
        this.price = price;
    }


    /* GETTER & SETTERS */
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
