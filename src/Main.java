import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FuelCalculator calculator = new FuelCalculator();
        FuelRecords records = new FuelRecords();

        // Спрашиваем норму расхода
        System.out.println("Введите норму расхода (литров/км, например, 0.1 для лета, 0.12 для зимы):");
        double fuelRate = scanner.nextDouble();
        calculator.setFuelRate(fuelRate);

        // Спрашиваем начальный остаток топлива
        System.out.println("Введите начальный остаток топлива (литров):");
        double initialFuel = scanner.nextDouble();
        double previousFuel = initialFuel; // Сохраняем начальный остаток

        // Бесконечный цикл для ввода данных
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить данные за день");
            System.out.println("2. Корректировать данные за прошлый день");
            System.out.println("3. Показать таблицу");
            System.out.println("4. Выход");
            int choice = scanner.nextInt();

            if (choice == 4) {
                break; // Выход из программы
            }

            if (choice == 1) {
                // Добавление данных за день
                System.out.println("Введите дату (например, 15.04.2025):");
                scanner.nextLine(); // Очищаем буфер
                String dateStr = scanner.nextLine();
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                // Начальный остаток (берём из последней записи или используем введённый)
                if (!records.getAllRecords().isEmpty()) {
                    DailyRecord lastRecord = records.getAllRecords().get(records.getAllRecords().size() - 1);
                    previousFuel = lastRecord.getRemainingFuel();
                }

                System.out.println("Введите пробег утром (км):");
                double morningMileage = scanner.nextDouble();
                System.out.println("Введите пробег вечером (км):");
                double eveningMileage = scanner.nextDouble();
                System.out.println("Введите заправку (литров):");
                double fuelAdded = scanner.nextDouble();

                // Вычисления
                double dailyMileage = calculator.calculateDailyMileage(morningMileage, eveningMileage);
                double fuelConsumption = calculator.calculateFuelConsumption(dailyMileage);
                double remainingFuel = calculator.calculateRemainingFuel(previousFuel, fuelAdded, fuelConsumption);

                // Сохраняем запись
                DailyRecord record = new DailyRecord(date, morningMileage, eveningMileage,
                        dailyMileage, fuelConsumption, fuelAdded, remainingFuel);
                records.addRecord(record);
                System.out.println("Данные добавлены!");
            }
            else if (choice == 2) {
                // Корректировка данных
                System.out.println("Введите дату для корректировки (например, 15.04.2025):");
                scanner.nextLine(); // Очищаем буфер
                String dateStr = scanner.nextLine();
                LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

                DailyRecord record = records.getRecordByDate(date);
                if (record == null) {
                    System.out.println("Запись за эту дату не найдена!");
                    continue;
                }

                System.out.println("Введите новый пробег утром (км):");
                double morningMileage = scanner.nextDouble();
                System.out.println("Введите новый пробег вечером (км):");
                double eveningMileage = scanner.nextDouble();
                System.out.println("Введите новую заправку (литров):");
                double fuelAdded = scanner.nextDouble();

                // Пересчёт для этой записи
                double dailyMileage = calculator.calculateDailyMileage(morningMileage, eveningMileage);
                double fuelConsumption = calculator.calculateFuelConsumption(dailyMileage);

                // Находим начальный остаток для этой записи
                int recordIndex = records.getAllRecords().indexOf(record);
                if (recordIndex > 0) {
                    previousFuel = records.getAllRecords().get(recordIndex - 1).getRemainingFuel();
                } else {
                    previousFuel = initialFuel; // Используем введённый начальный остаток
                }

                double remainingFuel = calculator.calculateRemainingFuel(previousFuel, fuelAdded, fuelConsumption);
                records.updateRecord(date, morningMileage, eveningMileage, dailyMileage, fuelConsumption, fuelAdded, remainingFuel);

                // Пересчитываем остатки для всех последующих записей
                for (int i = recordIndex + 1; i < records.getAllRecords().size(); i++) {
                    DailyRecord nextRecord = records.getAllRecords().get(i);
                    previousFuel = records.getAllRecords().get(i - 1).getRemainingFuel();
                    fuelAdded = nextRecord.getFuelAdded();
                    fuelConsumption = nextRecord.getFuelConsumption();
                    remainingFuel = calculator.calculateRemainingFuel(previousFuel, fuelAdded, fuelConsumption);
                    records.updateRecord(nextRecord.getDate(), nextRecord.getMorningMileage(), nextRecord.getEveningMileage(),
                            nextRecord.getDailyMileage(), fuelConsumption, fuelAdded, remainingFuel);
                }

                System.out.println("Данные обновлены!");
            }
            else if (choice == 3) {
                // Вывод таблицы
                System.out.println("Норма расхода: " + calculator.getFuelRate() + " литра/км");
                System.out.println("Дата       | Пробег утро | Пробег вечер | Суточный пробег | Расход | Заправка | Остаток");
                for (DailyRecord record : records.getAllRecords()) {
                    System.out.printf("%s | %-11.1f | %-12.1f | %-15.1f | %-6.1f | %-8.1f | %-7.1f%n",
                            record.getDate(), record.getMorningMileage(), record.getEveningMileage(),
                            record.getDailyMileage(), record.getFuelConsumption(), record.getFuelAdded(),
                            record.getRemainingFuel());
                }
            }
        }
        scanner.close();
    }
}