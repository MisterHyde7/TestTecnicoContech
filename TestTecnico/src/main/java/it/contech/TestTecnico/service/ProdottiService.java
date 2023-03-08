package it.contech.TestTecnico.service;

import it.contech.TestTecnico.dto.ProdottoDto;

import java.util.List;

public interface ProdottiService {

    //Ritorna la lista completa dei prodotti
    List<ProdottoDto> getAllProdotti();

    //Ritorna il prodotto con lo stesso id
    ProdottoDto getProdottoById(Long idProdotto);

    //Ritorna la lista di prodotti completa dopo averci aggiunto il prodotto ricevuto dal Front-End
    List<ProdottoDto> createProdotto(ProdottoDto fattura);

    //Esegue una modifica del prodotto ricevuto dal Front-End e lo ritorna aggiornato
    ProdottoDto editProdottoById(ProdottoDto prodotto);

    //Elimina il prodotto con id corrispondente
    List<ProdottoDto> deleteProdotto(Long idProdotto);

    //Ritorna la lista di prodotti con la stessa descrizione
    List<ProdottoDto> getProdottoByDescrizione(String descrizioneProdotto);
}
