package it.contech.TestTecnico.dto;

import it.contech.TestTecnico.model.ProdottoEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProdottoDto {

    private Long id;
    private String descrizione;
    private Double prezzo;

    public static ProdottoDto fromEntity(ProdottoEntity input) {
        ProdottoDto result = ProdottoDto.emptyDto();
        if (input == null) {
            return result;
        }

        result.setId(input.getId());
        result.setDescrizione(input.getDescrizione());
        result.setPrezzo(input.getPrezzo());

        return result;
    }

    public static List<ProdottoDto> listFromEntity(List<ProdottoEntity> listInput) {
        return !listInput.isEmpty() ?
                listInput.stream().map(ProdottoDto::fromEntity).collect(Collectors.toList()) :
                new ArrayList<>();
    }


    public static ProdottoDto emptyDto() {

        return ProdottoDto
                .builder()
                .build();
    }

}
