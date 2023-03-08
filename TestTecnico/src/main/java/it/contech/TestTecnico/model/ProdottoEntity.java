package it.contech.TestTecnico.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "prodotto")
public class ProdottoEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "prezzo")
    private Double prezzo;

}
