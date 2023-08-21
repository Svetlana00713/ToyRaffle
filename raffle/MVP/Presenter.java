package raffle.MVP;

import raffle.MVP.model.Raffle;
import raffle.MVP.model.Toy;
import raffle.MVP.view.Interface;
import save.Path;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Presenter {
    private final Raffle raffle;
    private final Interface view;


    public Presenter(Interface view, String pathDb) {
        this.view = view;
        raffle = new Raffle (pathDb);
    }

    public void putForRaffle() {
        if (raffle.currentService().putForRaffle (
                new Toy (view.getToyId(), view.getToyNaming(), view.getToyQuantity(), view.getToyWeight() ) ))
            view.savedItem();
        else
            view.saveError();
    }

    public void deleteFromRaffle() {
        if (raffle.currentService.getToys().size() == 0)
            view.emptyListMessage();
        else
            raffle.currentService().remove(view.getToyId());
    }

    public void getRaffle() {
        PriorityQueue<Toy> priorityQueue = new PriorityQueue<>();
        Toy raffledToy;
        List<Toy> raffledToys = new ArrayList<>();
        if (raffle.currentService.getToys().size() != 0) {
            int times = view.getRaffleTimes();
            priorityQueue.addAll(raffle.currentService().getToys());
            while (priorityQueue.size() < times) {
                priorityQueue.addAll(raffle.currentService().getToys());
            }
            for (int i = 0; i < times; i++) {
                raffledToy = priorityQueue.remove();
                view.showGetToy (raffledToy);
                raffledToys.add (raffledToy);
            }
            raffle.saveResult (Path.pathResult, raffledToys );
        } else
            view.emptyListMessage ();
    }

    public void showAll() {
        if (raffle.currentService.getToys().size() == 0)
            view.emptyListMessage();
        else
            view.showAll (raffle.currentService.getToys());
    }

    public void clearAll() {
        if (raffle.currentService.getToys().size() == 0)
            view.emptyListMessage();
        else {
            if (view.clearAllDecision()) {
                raffle.currentService.getToys().clear();
                System.out.println ("Списки очищены!");
            }
        }
    }

    public void saveToFile() {
        raffle.save();
        view.savedAll();
    }

    public void loadFromFile() {
        raffle.load();
        view.loadMessage();
    }

    @SuppressWarnings("unused")
    public int update(Scanner scanner, Collection<? extends Toy> toys, String pathDb) {
        System.out.print ( "Введите ID игрушки: " );
        int toyId = scanner.nextInt ();
        scanner.nextLine ();
        System.out.print ( "Введите новый вес игрушки: " );
        String newWeightStr = scanner.nextLine();
        int newWeight;
        System.out.print ( "Введите новое количество игрушек: " );
        String newQuantityStr = scanner.nextLine();
        int newQuantity;
        try {
            newWeight = Integer.parseInt(newWeightStr);
            newQuantity= Integer.parseInt(newQuantityStr);
        } catch (NumberFormatException e) {
            System.out.println("Некорректное значение для нового веса игрушки.");
            System.out.println("Некорректное значение для нового количества");
            return 0;
        }
        if (raffle.currentService.getToys() != null) {
            for (Toy toy : raffle.currentService.getToys()) {
                if (toy.getId() == toyId) {
                    toy.setWeight (newWeight);
                    toy.setQuantity(newQuantity);
                    break;
                }
            }
        } else {
            List<Toy> toyList = new ArrayList<>(toys);
            raffle.currentService.setToys(toyList);
        }
        try (FileWriter writer = new FileWriter(pathDb, false)) {
            for (Toy toy : raffle.currentService.getToys()) {
                writer.append(String.format("%d\n", toy.getId()));
                writer.append(String.format("%s\n", toy.getName()));
                writer.append(String.format("%d\n", toy.getQuantity()));
                writer.append(String.format("%d\n", toy.getWeight()));
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        view.update ();
        System.out.println ( "Вес игрушки успешно изменен!" );
        System.out.println ("Количество игрушек успешно изменено!" );
        return toyId;
    }
}


