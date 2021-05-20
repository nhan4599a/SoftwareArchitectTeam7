package vlu.architect.team7.abc_garage_service.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "BusSeat")
@IdClass(CompositeBusSeatPK.class)
public final class BusSeat {

    @Id
    private int busID;

    @Id
    private int seatNumber;
}
