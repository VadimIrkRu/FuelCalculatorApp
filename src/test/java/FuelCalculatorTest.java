import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuelCalculatorTest {

    @Test
    void testSetAndGetFuelRate() {
        FuelCalculator calculator = new FuelCalculator();
        calculator.setFuelRate(0.12);  // Установим норму расхода

        double expected = 0.12;  // Ожидаем, что норма расхода будет 0.12
        double actual = calculator.getFuelRate();  // Получаем норму расхода

        assertEquals(expected, actual, "Норма расхода топлива должна быть 0.12");
    }

    @Test
    void testCalculateDailyMileage() {
        FuelCalculator calculator = new FuelCalculator();
        double morningMileage = 100.0;  // Утренний пробег
        double eveningMileage = 150.0;  // Вечерний пробег

        double expected = 50.0;  // Суточный пробег должен быть 150 - 100 = 50
        double actual = calculator.calculateDailyMileage(morningMileage, eveningMileage);

        assertEquals(expected, actual, "Суточный пробег должен быть 50 км");
    }

    @Test
    void testCalculateFuelConsumption() {
        FuelCalculator calculator = new FuelCalculator();
        double dailyMileage = 50.0;  // Суточный пробег
        double expected = 5.0;  // При норме расхода 0.1 литра на км 50 км потребуют 5 литров

        double actual = calculator.calculateFuelConsumption(dailyMileage);

        assertEquals(expected, actual, "Расход топлива должен быть 5 литров");
    }

    @Test
    void testCalculateRemainingFuel() {
        FuelCalculator calculator = new FuelCalculator();
        double previousFuel = 20.0;  // Топливо, которое было до начала дня
        double fuelAdded = 10.0;     // Топливо, добавленное за день
        double fuelConsumed = 12.0;  // Топливо, которое было потрачено

        double expected = 18.0;  // Остаток топлива должен быть 20 + 10 - 12 = 18
        double actual = calculator.calculateRemainingFuel(previousFuel, fuelAdded, fuelConsumed);

        assertEquals(expected, actual, "Остаток топлива должен быть 18 литров");
    }
}
