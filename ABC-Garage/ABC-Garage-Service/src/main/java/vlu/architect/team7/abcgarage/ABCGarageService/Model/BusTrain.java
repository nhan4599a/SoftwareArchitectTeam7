package vlu.architect.team7.abcgarage.ABCGarageService.Model;

import javax.persistence.*;

@Entity
@Table(name = "BusTrain")
public final class BusTrain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private String licensePlate;

    private String startTime;

    private String startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startPlace")
    private Place startPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "targetPlace")
    private Place targetPlace;
}
