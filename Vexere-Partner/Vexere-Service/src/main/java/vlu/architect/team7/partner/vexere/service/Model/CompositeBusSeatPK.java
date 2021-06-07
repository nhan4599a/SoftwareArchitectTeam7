package vlu.architect.team7.partner.vexere.service.Model;

import java.io.Serializable;
import java.util.Objects;

public final class CompositeBusSeatPK implements Serializable {

    private BusTrain bus;
    private int seatNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeBusSeatPK that = (CompositeBusSeatPK) o;
        return bus.getID() == that.bus.getID() && seatNumber == that.seatNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bus, seatNumber);
    }
}
