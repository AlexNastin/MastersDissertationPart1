package by.bsuir.dissertation.entity.general;

import by.bsuir.dissertation.util.DissertationConstants;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class Car {

    private String id;
    private double speed;

    public Car() {
        id = UUID.randomUUID().toString();
        speed = DissertationConstants.CAR_MOVEMENT.INITIAL_SPEED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.speed, speed) == 0 &&
                Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, speed);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("speed", speed)
                .toString();
    }
}
