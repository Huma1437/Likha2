package economic_freedom.com.likha;

public class NewsFeed_pojo {

    String name;
    String category;
    String product_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    String cost;
    String materials;

    public NewsFeed_pojo(String name, String category, String product_name, String cost, String materials) {
        this.name = name;
        this.category = category;
        this.product_name = product_name;
        this.cost = cost;
        this.materials = materials;
    }


}
