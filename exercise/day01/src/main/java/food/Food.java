package food;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Supplier;

public record Food(
        LocalDate expirationDate,
        Boolean approvedForConsumption,
        UUID inspectorId
) {
    public boolean isEdible(Supplier<LocalDate> dateSupplier) {
        var givenDate = dateSupplier.get();
        return isExpired(givenDate) &&
                approvedForConsumption &&
                inspectorIdIsProvided();
    }

    private boolean isExpired(LocalDate date) {
        return expirationDate.isAfter(date);
    }

    private boolean inspectorIdIsProvided() {
        return inspectorId != null;
    }
}
