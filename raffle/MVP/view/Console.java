package raffle.MVP.view;

import raffle.MVP.model.Toy;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static save.Path.pathDb;

public class Console implements Interface {
    Scanner in;

    public Console() {
        in = new Scanner(System.in);
    }

    @Override
    public void loadMessage() {
        System.out.println("\nДобро пожаловать в новый розыгрыш!");
    }

    @Override
    public int getToyId() {
        System.out.print("Идентификатор игрушки: ");
        return parseInt(in.nextLine());
    }

    @Override
    public String getToyNaming() {
        System.out.print("Название игрушки: ");
        return in.nextLine();
    }

    @Override
    public int getToyQuantity() {
        System.out.print("Количество игрушек: ");
        return parseInt(in.nextLine());
    }

    @Override
    public int getToyWeight() {
        System.out.print("Вес игрушки: ");
        return parseInt(in.nextLine());
    }

    @Override
    public void savedItem() {
        System.out.println("\nИгрушка успешно добавлена");
    }

    @Override
    public void saveError() {
        System.out.println("\nНе удалось добавить игрушку");
    }

    @Override
    public int getRaffleTimes() {
        System.out.print("Сколько раз вы хотите сыграть? ");
        return parseInt(in.nextLine());
    }

    @Override
    public void showGetToy(Toy toy) {
        System.out.print("\nРезультаты розыгрыша: ");
        System.out.println(toy);
    }

    @Override
    public void showAll(List<Toy> toys) {
        System.out.println("\nВсе игрушки для розыгрыша:");
        for (Toy toy : toys) {
            System.out.println(toy);
        }
    }

    @Override
    public void emptyListMessage() {
        System.out.println("Список игрушек пуст!");
    }

    @Override
    public boolean clearAllDecision() {
        boolean f = false;
        System.out.print("\nВы уверены, что очистили все записи (да/нет): ");
        if (in.nextLine().equalsIgnoreCase("Да")) {
            f = true;
        }
        return f;
    }

    @Override
    public void savedAll() {
        System.out.println("\nВсе записи сохранены: " + pathDb);
    }

    @Override
    public int update() {
        return 0;
    }
}

