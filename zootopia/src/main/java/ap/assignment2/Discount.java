package ap.assignment2;

public class Discount {
    /*
     * Discount class to store all attributes, getters and setters required
     */
    private String category;
    private int percent;
    private String code;

    public Discount(String category, int percent) {
        this.category = category;
        this.percent = percent;
        this.code = category.split(" ")[0].toUpperCase() + percent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        this.setCode(category.split(" ")[0].toUpperCase() + percent);
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
        this.setCode(category.split(" ")[0].toUpperCase() + percent);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
