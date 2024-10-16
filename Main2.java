import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface IObserver {
    void update(String CompanyMarks, double price);
}

interface ISubject {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers(String CompanyMarks, double price);
}

class StockExchange implements ISubject {
    private Map<String, Double> exchangePrices = new HashMap<>();
    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String CompanyMarks, double price) {
        for (IObserver observer : observers) {
            observer.update(CompanyMarks, price);
        }
    }

    public void setExchangePrice(String CompanyMarks, double price) {
        Double currentPrice = exchangePrices.get(CompanyMarks);
        if (currentPrice == null || currentPrice != price) {
            exchangePrices.put(CompanyMarks, price);
            System.out.println("Цена " + CompanyMarks + " обновлена до " + price);
            notifyObservers(CompanyMarks, price);
        }
    }
}

class Robot implements IObserver {
    private String name;

    public Robot(String name) {
        this.name = name;
    }

    @Override
    public void update(String CompanyMarks, double price) {
        if (price < 80) {
            System.out.println(name + " купил " + CompanyMarks + " по цене " + price);
        }
    }
}

class Trader implements IObserver {
    private String name;

    public Trader(String name) {
        this.name = name;
    }

    @Override
    public void update(String CompanyMarks, double price) {
        System.out.println(name + " купил " + CompanyMarks + " по цене " + price);
    }
}

public class Main2 {
    public static void main(String[] args) {
        StockExchange stockExchange = new StockExchange();

        Robot robot1 = new Robot("StockAuto2000");
        Trader trader1 = new Trader("TraderMasterP2P");

        stockExchange.addObserver(robot1);
        stockExchange.addObserver(trader1);

        // Устанавливаем цены и наблюдаем уведомления
        stockExchange.setExchangePrice("Apple", 74.85);
        stockExchange.setExchangePrice("Microsoft", 88.90);
        stockExchange.setExchangePrice("Google", 101.75);

        // Повторно устанавливаем те же цены, чтобы проверить, что уведомлений не будет
        stockExchange.setExchangePrice("Apple", 74.85);
        stockExchange.setExchangePrice("Microsoft", 88.90);

        // Изменение цены для Google должно вызвать уведомление
        stockExchange.setExchangePrice("Google", 100.50);
    }
}
