public class Evidence {
    private String name;
    private String description;
    private int id;

   public Evidence(String name, String description,int id){
        this.name=name;
        this.description=description;
        this.id=id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
    }
}
