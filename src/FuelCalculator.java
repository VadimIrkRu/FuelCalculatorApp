public class FuelCalculator {
    // Норма расхода (литров на 1 км), например, 0.1 литра/км
    private double fuelRate = 0.1;

    // Метод, чтобы изменить норму расхода (зима/лето)
    public void setFuelRate(double newRate) {
        this.fuelRate = newRate;
    }

    // Метод, чтобы получить текущую норму расхода
    public double getFuelRate() {
        return fuelRate;
    }

    // Метод для вычисления суточного пробега
    public double calculateDailyMileage(double morningMileage, double eveningMileage) {
        return eveningMileage - morningMileage;
    }

    // Метод для вычисления расхода топлива
    public double calculateFuelConsumption(double dailyMileage) {
        return dailyMileage * fuelRate;
    }

    // Метод для вычисления остатка топлива
    public double calculateRemainingFuel(double previousFuel, double fuelAdded, double fuelConsumed) {
        return previousFuel + fuelAdded - fuelConsumed;
    }
}
