package vlu.architect.team7.abcgarage.ABCGarageService.Model;

import javax.persistence.*;

@Entity
@Table(name = "BusTrain")
public final class BusTrain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private String licensePlate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Place getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(Place startPlace) {
        this.startPlace = startPlace;
    }

    public Place getTargetPlace() {
        return targetPlace;
    }

    public void setTargetPlace(Place targetPlace) {
        this.targetPlace = targetPlace;
    }

    private String startTime;

    private String startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startPlace")
    private Place startPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "targetPlace")
    private Place targetPlace;
}
