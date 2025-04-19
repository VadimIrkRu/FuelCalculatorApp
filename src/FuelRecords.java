import java.time.LocalDate; // Добавляем импорт
import java.util.ArrayList;
import java.util.List;

public class FuelRecords {
    private List<DailyRecord> records = new ArrayList<>();

    public void addRecord(DailyRecord record) {
        records.add(record);
    }

    public List<DailyRecord> getAllRecords() {
        return records;
    }

    public DailyRecord getRecordByDate(LocalDate date) {
        for (DailyRecord record : records) {
            if (record.getDate().equals(date)) {
                return record;
            }
        }
        return null;
    }

    public void updateRecord(LocalDate date, double morningMileage, double eveningMileage,
                             double dailyMileage, double fuelConsumption, double fuelAdded, double remainingFuel) {
        DailyRecord record = getRecordByDate(date);
        if (record != null) {
            record.update(morningMileage, eveningMileage, dailyMileage, fuelConsumption, fuelAdded, remainingFuel);
        }
    }
}
