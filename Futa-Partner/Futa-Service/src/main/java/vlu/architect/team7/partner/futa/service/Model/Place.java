package vlu.architect.team7.partner.futa.service.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "Place")
public final class Place {

    @Id
    @Column(name = "ID")
    private String placeCode;

    @Column(name = "name")
    private String placeName;
}
