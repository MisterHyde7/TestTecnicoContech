package it.contech.TestTecnico.service;

import it.contech.TestTecnico.dto.FatturaDto;

import java.util.List;

public interface FattureService {

    //Ritorna la lista completa delle fatture
    List<FatturaDto> getAllFatture();

    //Ritorna la fattura con lo stesso id
    FatturaDto getFatturaById(Long idFattura);

    //Ritorna la lista di fatture con la stessa descrizione
    List<FatturaDto> getFatturaByDescrizione(String descrizioneFattura);

    //Ritorna la lista di fatture completa dopo averci aggiunto la fattura ricevuta dal Front-End
    List<FatturaDto> createFattura(FatturaDto fattura);

    //Esegue una modifica della fattura ricevuta dal Front-End e la ritorna aggiornata
    FatturaDto editFatturaById(FatturaDto fattura);

    //Elimina la fattura con id corrispondente
    List<FatturaDto> deleteFattura(Long idFattura);

    FatturaDto editProdottiInFattura(FatturaDto fattura);
}
