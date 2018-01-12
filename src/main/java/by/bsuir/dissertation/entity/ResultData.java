package by.bsuir.dissertation.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import by.bsuir.dissertation.entity.graph.Node;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "result")
public class ResultData {

    @Id
    private String id;

    @Field
    private Timestamp date;

    @DBRef
    private Car car;

    @DBRef
    private Node node;

    public ResultData() {
    }

    public ResultData(String id, Timestamp date, Car car, Node node) {
        this.id = id;
        this.date = date;
        this.car = car;
        this.node = node;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultData)) return false;
        ResultData that = (ResultData) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(car, that.car) &&
                Objects.equals(node, that.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, car, node);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", date=").append(date);
        sb.append(", car=").append(car);
        sb.append(", node=").append(node);
        sb.append('}');
        return sb.toString();
    }
}
