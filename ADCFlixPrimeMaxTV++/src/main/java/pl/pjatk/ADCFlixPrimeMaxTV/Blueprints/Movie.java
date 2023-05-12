package pl.pjatk.ADCFlixPrimeMaxTV.Blueprints;

public class Movie {

    private int id;
    //private static int incId = 1;
    private Category category;
    private String name;
    private Double price;


    public Movie(int id, String name, Double price, Category category) {
        //this.id = incId++;
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Integer getId() {
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

    public void setId(Integer id) {
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
}
