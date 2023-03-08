package it.contech.TestTecnico.dto;

import it.contech.TestTecnico.model.FatturaEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FatturaDto {

    private Long id;
    private String descrizione;
    private List<ProdottoDto> listaProdotti;

    public static FatturaDto fromEntity(FatturaEntity input) {
        FatturaDto result = FatturaDto.emptyDto();
        if (input == null) {
            return result;
        }

        result.setId(input.getId());
        result.setDescrizione(input.getDescrizione());
        result.setListaProdotti(ProdottoDto.listFromEntity(input.getListaProdotti()));

        return result;
    }

    public static List<FatturaDto> listFromEntity(List<FatturaEntity> listInput) {
        return !listInput.isEmpty() ?
                listInput.stream().map(FatturaDto::fromEntity).collect(Collectors.toList()) :
                new ArrayList<>();
    }


    public static FatturaDto emptyDto() {

        return FatturaDto
                .builder()
                .listaProdotti(new ArrayList<>())
                .build();
    }

}
