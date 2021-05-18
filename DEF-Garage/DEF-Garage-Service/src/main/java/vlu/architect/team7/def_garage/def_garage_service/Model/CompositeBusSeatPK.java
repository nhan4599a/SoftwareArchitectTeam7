package vlu.architect.team7.def_garage.def_garage_service.Model;

import java.io.Serializable;
import java.util.Objects;

public final class CompositeBusSeatPK implements Serializable {
    private int busID, seatNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeBusSeatPK that = (CompositeBusSeatPK) o;
        return busID == that.busID && seatNumber == that.seatNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(busID, seatNumber);
    }
}
