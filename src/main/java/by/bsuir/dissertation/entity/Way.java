package by.bsuir.dissertation.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Document(collection = "way")
public class Way {

    @Id
    private String id = UUID.randomUUID().toString();

    @Field
    private String name;

    @Field
    private int numberNodes;

    @Field
    private Map<Integer, PartWay> fullWay = new HashMap<>();

    public Way() {
    }

    public Way(String id, String name, int numberNodes, Map<Integer, PartWay> fullWay) {
        this.id = id;
        this.name = name;
        this.numberNodes = numberNodes;
        this.fullWay = fullWay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Way way = (Way) o;
        return id == way.id &&
                numberNodes == way.numberNodes &&
                Objects.equals(name, way.name) &&
                Objects.equals(fullWay, way.fullWay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberNodes, fullWay);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WayRepository{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", numberNodes=").append(numberNodes);
        sb.append(", fullWay=").append(fullWay);
        sb.append('}');
        return sb.toString();
    }
}
