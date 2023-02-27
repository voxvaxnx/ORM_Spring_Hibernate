package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Model")
    private String model;
    @Column(name = "Series")
    private int series;
    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY)

    private User user;

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return model + ' ' + series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (getId() != car.getId()) return false;
        if (getSeries() != car.getSeries()) return false;
        if (!getModel().equals(car.getModel())) return false;
        return getUser().equals(car.getUser());
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getModel().hashCode();
        result = 31 * result + getSeries();
        result = 31 * result + getUser().hashCode();
        return result;
    }
}
