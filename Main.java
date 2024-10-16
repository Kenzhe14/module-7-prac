import java.util.Scanner;
interface ICostCalculationStrategy{
    double calculate(int passangers, int km, int tclass, int discount);
}
class Bus implements ICostCalculationStrategy{
    //1-econom 2-business 3-first
    @Override
    public double calculate(int passangers, int km, int tclass, int discount) {
        double cost = 0;
        switch (tclass){
            case 1:
                cost = 10*passangers + 5*km + 50 - discount;
                break;
            case 2:
                cost = 10*passangers + 10*km + 100 - discount;
                break;
            case 3:
                cost = 10*passangers + 10*km + 200 - discount;
                break;
        }
        return cost;
    }
}
class Train implements ICostCalculationStrategy{
    //1-econom 2-business 3-first
    @Override
    public double calculate(int passangers, int km, int tclass, int discount) {
        double cost = 0;
        switch (tclass){
            case 1:
                cost = 20*passangers + 5*km + 50 - discount;
                break;
            case 2:
                cost = 20*passangers + 10*km + 150 - discount;
                break;
            case 3:
                cost = 20*passangers + 10*km + 200 - discount;
                break;
        }
        return cost;
    }
}
class Plane implements ICostCalculationStrategy{
    //1-econom 2-business 3-first
    @Override
    public double calculate(int passangers, int km, int tclass, int discount) {
        double cost = 0;
        switch (tclass){
            case 1:
                cost = 30*passangers + 10*km + 100 - discount;
                break;
            case 2:
                cost = 40*passangers + 10*km + 200 - discount;
                break;
            case 3:
                cost = 50*passangers + 10*km + 300 - discount;
                break;
        }
        return cost;
    }
}
class TravelBookingContext{
    private ICostCalculationStrategy calculationStrategy;

    public void setTravelingStrategy(ICostCalculationStrategy calculationStrategy){
        this.calculationStrategy = calculationStrategy;
    }
    public double calculateCost (int passangers, int km, int tclass, int discount){
        if(calculationStrategy == null){
            System.out.println("Not selected calsulating stragegy");
        }
        return calculationStrategy.calculate(passangers,km,tclass,discount);
    }
}
public class Main {
    public static void main(String[] args) {
        TravelBookingContext travelBookingContext = new TravelBookingContext();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - Bus. 2 - Train. 3 - Plane. 4 - exit");
        int choise = scanner.nextInt();
        switch  (choise){
            case 1:
                travelBookingContext.setTravelingStrategy(new Bus());
                break;
            case 2:
                travelBookingContext.setTravelingStrategy(new Train());
                break;
            case 3:
                travelBookingContext.setTravelingStrategy(new Plane());
                break;
            default:
                break;
        }
        System.out.println("Passangers = ");
        int passangers = scanner.nextInt();
        System.out.println("Km = ");
        int km = scanner.nextInt();
        System.out.println("Transport class = 1-econom 2-business 3-first ");
        int tclass = scanner.nextInt();
        System.out.println("Discount = ");
        int discount = scanner.nextInt();
        double cost = travelBookingContext.calculateCost(passangers, km, tclass, discount);
        System.out.printf("Price = : %.2f%n", cost);
    }
}
