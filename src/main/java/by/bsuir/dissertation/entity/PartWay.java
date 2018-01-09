package by.bsuir.dissertation.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class PartWay {

    private String id;

    private double distance;

    private boolean isCamera;

    private String region;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartWay partWay = (PartWay) o;
        return id == partWay.id &&
                Double.compare(partWay.distance, distance) == 0 &&
                isCamera == partWay.isCamera &&
                Objects.equals(region, partWay.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, distance, isCamera, region);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PartWay{");
        sb.append("id=").append(id);
        sb.append(", distance=").append(distance);
        sb.append(", isCamera=").append(isCamera);
        sb.append(", region='").append(region).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
