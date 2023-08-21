package raffle.view;

import raffle.model.Toy;

import java.util.List;

public interface Interface {
    int getToyId();
    String getToyNaming();
    int getToyQuantity();
    int getToyWeight();
    void showAll(List<Toy> toys);
    boolean clearAllDecision();
    void savedAll();
    void savedItem();
    void saveError();
    void emptyListMessage();
    void showGetToy(Toy toy);
    void loadMessage();
    int getRaffleTimes();
    @SuppressWarnings("unused")
    int update();
}
