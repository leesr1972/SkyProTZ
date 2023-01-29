package sky.pro.java.skyprotz.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "socks")
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String color;

    @NotNull
    private int cottonPart;

    public Sock(Long id, String color, int cottonPart) {
        this.id = id;
        this.color = color;
        this.cottonPart = cottonPart;
    }

    public Sock() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return cottonPart == sock.cottonPart && Objects.equals(id, sock.id) && Objects.equals(color, sock.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, cottonPart);
    }

    @Override
    public String toString() {
        return "Sock{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", cottonPart=" + cottonPart +
                '}';
    }
}
