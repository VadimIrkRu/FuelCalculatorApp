import java.time.LocalDate;

public class DailyRecord {
    private LocalDate date;
    private double morningMileage;
    private double eveningMileage;
    private double dailyMileage;
    private double fuelConsumption;
    private double fuelAdded;
    private double remainingFuel;

    public DailyRecord(LocalDate date, double morningMileage, double eveningMileage,
                       double dailyMileage, double fuelConsumption, double fuelAdded, double remainingFuel) {
        this.date = date;
        this.morningMileage = morningMileage;
        this.eveningMileage = eveningMileage;
        this.dailyMileage = dailyMileage;
        this.fuelConsumption = fuelConsumption;
        this.fuelAdded = fuelAdded;
        this.remainingFuel = remainingFuel;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getMorningMileage() {
        return morningMileage;
    }

    public double getEveningMileage() {
        return eveningMileage;
    }

    public double getDailyMileage() {
        return dailyMileage;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public double getFuelAdded() {
        return fuelAdded;
    }

    public double getRemainingFuel() {
        return remainingFuel;
    }

    public void update(double morningMileage, double eveningMileage, double dailyMileage,
                       double fuelConsumption, double fuelAdded, double remainingFuel) {
        this.morningMileage = morningMileage;
        this.eveningMileage = eveningMileage;
        this.dailyMileage = dailyMileage;
        this.fuelConsumption = fuelConsumption;
        this.fuelAdded = fuelAdded;
        this.remainingFuel = remainingFuel;
    }
}
