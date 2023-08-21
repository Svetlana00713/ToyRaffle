package raffle.MVP.view;

import raffle.MVP.Presenter;
import raffle.MVP.model.Toy;
import save.Path;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Menu {
    protected static Collection<? extends Toy> toyNewWeight = new ArrayList<>();
    protected static Collection<? extends Toy> toyNewQuantity = new ArrayList<> ();

    public static void buttonClick() {
        Presenter presenter = new Presenter(new Console(), Path.pathDb);
        Scanner scanner = new Scanner (System.in);
        presenter.loadFromFile();

        String command;
        while (true) {

            command = prompt("""

                     1 - Добавьте игрушку к розыгрышу
                     2 - Удалите игрушку из розыгрыша
                     3 - Проведите розыгрыш и покажите результаты
                     4 - Покажите список игрушек
                     5 - Очистите все записи
                     6 - Сохраните все записи 
                     7 - Загрузите новый сеанс 
                     8 - Измените вес (вероятность выигрыша) и/или количество игрушек
                     9 - Выход
                    Сделайте свой выбор:\s""");
            if (command.equals("9")) {
                System.out.println("\nДо новых встреч!");
                return;
            }
            try {
                switch (command) {
                    case "1" -> presenter.putForRaffle();
                    case "2" -> presenter.deleteFromRaffle();
                    case "3" -> presenter.getRaffle();
                    case "4" -> presenter.showAll();
                    case "5" -> presenter.clearAll();
                    case "6" -> presenter.saveToFile();
                    case "7" -> presenter.loadFromFile();
                    case "8" -> presenter.update(scanner, toyNewWeight, toyNewQuantity.toString () );
                    default -> System.out.println("\nКоманда не найдена!");
                }
            } catch (Exception e) {
                System.out.println("Ошибка. " + e.getMessage());
            }
        }
    }

    private static String prompt(String message) {
        Scanner in = new Scanner( System.in );
        System.out.print(message);
        return in.nextLine();
    }

    private static Toy toyCreate() {
        int id = Integer.parseInt(prompt("Идентификатор игрушки: "));
        String name = prompt("Название игрушки: ");
        String quantity = prompt ("Количество игрушек");
        String weight = prompt("Вес игрушки: ");
        return new Toy(id, name, Integer.parseInt(quantity), Integer.parseInt(weight));
    }
}

