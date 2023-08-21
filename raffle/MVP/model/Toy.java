package raffle.MVP.model;

@SuppressWarnings("ALL")
public class Toy implements Comparable<Toy> {
    public int id;
    public String name;
    public int quantity;
    public int weight;

    public Toy(int id, String name, int quantity, int weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Идентификатор игрушки:" + id + "; Имя: " + name + "; Количество: " + quantity + "; Вес: " + weight;
    }

    @Override
    public int compareTo(Toy o) {
        return o.getWeight() - this.getWeight();
    }
}

