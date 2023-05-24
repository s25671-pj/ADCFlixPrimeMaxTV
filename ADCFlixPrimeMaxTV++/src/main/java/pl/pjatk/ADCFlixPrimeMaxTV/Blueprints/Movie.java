package pl.pjatk.ADCFlixPrimeMaxTV.Blueprints;

import jakarta.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
        @NamedQuery(name = "Movie.find1", query = "SELECT m FROM Movie m WHERE m.id = :id"),
        @NamedQuery(name = "Movie.update", query = "UPDATE Movie m SET m.name = :name, m.category = :category, m.price = :price WHERE m.id = :id"),
        @NamedQuery(name = "Movie.delete", query = "DELETE FROM Movie m WHERE m.id = :id")
})
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String name;
    private Double price;

    @Column(name = "is_available", columnDefinition = "TINYINT(1)")
    private Boolean isAvailable;


    public Movie(Long id, String name, Double price, Category category, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public Movie() {

    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
