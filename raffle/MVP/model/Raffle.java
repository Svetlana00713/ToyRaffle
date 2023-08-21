package raffle.MVP.model;

import java.io.*;
import java.util.List;

public class Raffle {
    public final Service currentService;
    private final String path;

    public Raffle(String path) {
        currentService = new Service();
        this.path = path;
    }

    public void load() {
        try {
            File file = new File(path);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String toyId = reader.readLine();
            while (toyId != null) {
                String name = reader.readLine();
                String quantity = reader.readLine();
                String weight = reader.readLine();
                this.currentService.putForRaffle(new Toy(Integer.parseInt(toyId), name, Integer.parseInt(quantity), Integer.parseInt(weight)));
                toyId = reader.readLine();
            }
            reader.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Ошибка. Пожалуйста, создайте файл " + path);
            e.printStackTrace();
        }
    }

    public void save() {
        try (FileWriter writer = new FileWriter(path, false)) {
            for (int i = 0; i < currentService.count(); i++) {
                Toy toy = currentService.getToy(i);
                writer.append(String.format("%s\n", toy.getId()));
                writer.append(String.format("%s\n", toy.getName()));
                writer.append(String.format("%s\n", toy.getQuantity()));
                writer.append(String.format("%s\n", toy.getWeight()));
            }
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Service currentService() {
        return this.currentService;
    }

    public void saveResult(String pathResult, List<Toy> toysList) {
        try (FileWriter writer = new FileWriter(pathResult, false)) {
            for (Toy toy : toysList) {
                writer.append(String.format("%s  ", toy.getId()));
                writer.append(String.format("%s  ", toy.getName()));
                writer.append(String.format("%s  ", toy.getQuantity()));
                writer.append(String.format("%s  ", toy.getWeight()));
                writer.append("\n");
                writer.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
