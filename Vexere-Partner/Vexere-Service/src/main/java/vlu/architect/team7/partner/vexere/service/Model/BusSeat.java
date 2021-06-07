package vlu.architect.team7.partner.vexere.service.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "BusSeat")
@IdClass(CompositeBusSeatPK.class)
public final class BusSeat {

    @Id
    @ManyToOne
    @JoinColumn(name = "busID")
    private BusTrain bus;

    @Id
    private int seatNumber;
}
