package vlu.architect.team7.partner.vexere.service.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "BusTrain")
public final class BusTrain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private String licensePlate, startTime, startDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startPlace")
    private Place startPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "targetPlace")
    private Place targetPlace;

    @OneToMany(mappedBy = "bus", fetch = FetchType.EAGER)
    private Set<BusSeat> seats;

    private int cost;
}
