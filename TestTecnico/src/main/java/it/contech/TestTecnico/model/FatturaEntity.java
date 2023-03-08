package it.contech.TestTecnico.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "fattura")
public class FatturaEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "descrizione")
    private String descrizione;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "fattura_prodotti",
            joinColumns = @JoinColumn(name = "id_fattura"),
            inverseJoinColumns = @JoinColumn(name = "id_prodotto"))
    private List<ProdottoEntity> listaProdotti;

}
