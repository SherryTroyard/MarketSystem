package indi.apst.marketsystem;

public class Fruit {
    private String name;
    private int imageResourceId;

    public static final Fruit[] fruit = {
            new Fruit("Apple", R.drawable.apple),
            new Fruit("Peach", R.drawable.peach)
    };

    private Fruit(String name, int imageResourceId){
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName(){
        return name;
    }

    public int getImageResourceId(){
        return imageResourceId;
    }

}
