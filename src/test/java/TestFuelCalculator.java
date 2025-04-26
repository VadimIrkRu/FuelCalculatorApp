public class TestFuelCalculator {
    public static void main(String[] args) {
        // Создаём "работника"
        FuelCalculator calculator = new FuelCalculator();

        // Проверяем текущую норму расхода
        System.out.println("Текущая норма расхода: " + calculator.getFuelRate() + " литра/км"); // Должно быть 0.1

        // Меняем норму расхода на зимнюю
        calculator.setFuelRate(0.12); // Новая норма 0.12 литра/км
        System.out.println("Новая норма расхода: " + calculator.getFuelRate() + " литра/км"); // Должно быть 0.12

        // Даём данные для теста
        double morningMileage = 1000; // Пробег утром
        double eveningMileage = 1050; // Пробег вечером
        double previousFuel = 50;     // Остаток топлива утром
        double fuelAdded = 0;         // Заправка (пока 0)

        // Считаем суточный пробег
        double dailyMileage = calculator.calculateDailyMileage(morningMileage, eveningMileage);
        System.out.println("Суточный пробег: " + dailyMileage + " км"); // Должно быть 50 км

        // Считаем расход (теперь норма 0.12)
        double fuelConsumption = calculator.calculateFuelConsumption(dailyMileage);
        System.out.println("Расход топлива: " + fuelConsumption + " литров"); // Должно быть 6 литров

        // Считаем остаток
        double remainingFuel = calculator.calculateRemainingFuel(previousFuel, fuelAdded, fuelConsumption);
        System.out.println("Остаток топлива: " + remainingFuel + " литров"); // Должно быть 44 литра
    }
}
