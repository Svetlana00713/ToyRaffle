package raffle.model;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<Toy> toys;

    public Service() {
        this.toys = new ArrayList<>();
    }

    public boolean putForRaffle(Toy toy) {
        boolean flag = false;
        if (!toys.contains(toy)) {
            this.toys.add(toy);
            flag = true;
        }
        return flag;
    }

    public void remove(int toyId) {
        if (indexContains(toyId) != -1) {
            toys.remove(indexContains(toyId));
            System.out.println("Успешное удаление!");
        } else
            System.out.println("Идентификатор не найден!");
    }

    public int count() {
        return toys.size();
    }

    public Toy getToy(int index) {
        return toys.get(index);
    }

    private int indexContains(int index) {
        int searchIndex = -1;
        for (Toy toy : toys) {
            if (toy.getId() == index)
                searchIndex = toys.indexOf(toy);
        }
        return searchIndex;
    }

    public List<Toy> getToys() {
        return toys;
    }

    public void setToys(List<Toy> toys) {
        this.toys = toys;
    }
}


