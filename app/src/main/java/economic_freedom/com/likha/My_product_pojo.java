package economic_freedom.com.likha;


public class My_product_pojo {

    String name;
    String product_name;
    String category;
    String cost;
    String material;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }




    public My_product_pojo(String name, String product_name, String category, String cost, String material) {
        this.name = name;
        this.product_name = product_name;
        this.category = category;
        this.cost = cost;
        this.material = material;

    }


}
