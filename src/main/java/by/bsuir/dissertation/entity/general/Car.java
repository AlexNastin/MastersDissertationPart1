package by.bsuir.dissertation.entity.general;

import by.bsuir.dissertation.util.DissertationConstants;
import lombok.Data;

import java.util.UUID;

@Data
public class Car {

    private String id;
    private double speed;

    public Car() {
        id = UUID.randomUUID().toString();
        speed = DissertationConstants.CAR_MOVEMENT.INITIAL_SPEED;
    }
}
