import java.time.LocalDate;

public class TestFuelRecords {
    public static void main(String[] args) {
        // Создаём "папку"
        FuelRecords records = new FuelRecords();

        // Создаём "работника" для вычислений
        FuelCalculator calculator = new FuelCalculator();
        calculator.setFuelRate(0.12); // Норма расхода 0.12

        // Данные для первого дня (15.04.2025)
        LocalDate date1 = LocalDate.of(2025, 4, 15);
        double morningMileage1 = 1000;
        double eveningMileage1 = 1050;
        double dailyMileage1 = calculator.calculateDailyMileage(morningMileage1, eveningMileage1);
        double fuelConsumption1 = calculator.calculateFuelConsumption(dailyMileage1);
        double fuelAdded1 = 0;
        double remainingFuel1 = calculator.calculateRemainingFuel(50, fuelAdded1, fuelConsumption1);

        // Создаём запись за первый день
        DailyRecord record1 = new DailyRecord(date1, morningMileage1, eveningMileage1,
                dailyMileage1, fuelConsumption1, fuelAdded1, remainingFuel1);
        records.addRecord(record1);

        // Данные для второго дня (16.04.2025)
        LocalDate date2 = LocalDate.of(2025, 4, 16);
        double morningMileage2 = 1050;
        double eveningMileage2 = 1110;
        double dailyMileage2 = calculator.calculateDailyMileage(morningMileage2, eveningMileage2);
        double fuelConsumption2 = calculator.calculateFuelConsumption(dailyMileage2);
        double fuelAdded2 = 10;
        double remainingFuel2 = calculator.calculateRemainingFuel(remainingFuel1, fuelAdded2, fuelConsumption2);

        // Создаём запись за второй день
        DailyRecord record2 = new DailyRecord(date2, morningMileage2, eveningMileage2,
                dailyMileage2, fuelConsumption2, fuelAdded2, remainingFuel2);
        records.addRecord(record2);

        // Выводим все записи
        System.out.println("Записи в папке:");
        for (DailyRecord record : records.getAllRecords()) {
            System.out.println(record.getDate() + " | Пробег утром: " + record.getMorningMileage() +
                    " | Пробег вечером: " + record.getEveningMileage() +
                    " | Суточный пробег: " + record.getDailyMileage() +
                    " | Расход: " + record.getFuelConsumption() +
                    " | Заправка: " + record.getFuelAdded() +
                    " | Остаток: " + record.getRemainingFuel());
        }
    }
}
