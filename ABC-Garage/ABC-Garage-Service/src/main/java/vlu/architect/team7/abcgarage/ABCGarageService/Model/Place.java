package vlu.architect.team7.abcgarage.ABCGarageService.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Place")
public final class Place {

    @Id
    @Column(name = "ID")
    private String placeCode;

    @Column(name = "name")
    private String placeName;
}
