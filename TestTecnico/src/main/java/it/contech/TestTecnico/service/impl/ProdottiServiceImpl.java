package it.contech.TestTecnico.service.impl;

import it.contech.TestTecnico.dto.ProdottoDto;
import it.contech.TestTecnico.repository.ProdottoRepository;
import it.contech.TestTecnico.service.ProdottiService;
import it.contech.TestTecnico.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdottiServiceImpl implements ProdottiService {

    //Injection dei service
    @Autowired
    ProdottoRepository prodottoRepository;

    //Ritorna la lista completa dei prodotti
    @Override
    public List<ProdottoDto> getAllProdotti() {

        //Prende la lista di entity e le trasforma in Dto
        return ProdottoDto.listFromEntity(

                //Chiama la procedura di GET con la tipologia GET_ALL
                prodottoRepository.procedureGet(
                        Constants.TYPE_GET_ALL,
                        null,
                        null,
                        null));

    }

    //Ritorna il prodotto con lo stesso id
    @Override
    public ProdottoDto getProdottoById(Long idProdotto) {

        //Trasforma l'entity in Dto
        return ProdottoDto.fromEntity(

                //Chiama la procedura di GET e recupera l'entity tramite id
                prodottoRepository.procedureGet(
                                Constants.TYPE_GET_BY_ID,
                                idProdotto,
                                null,
                                null)

                        //Prende la stream di dati e ritorna il primo risultato essendo l'id unico
                        .stream().findFirst().orElse(null));

    }

    //Ritorna la lista di prodotti con la stessa descrizione
    @Override
    public List<ProdottoDto> getProdottoByDescrizione(String descrizioneProdotto) {

        //Prende la lista di entity e le trasforma in Dto
        return ProdottoDto.listFromEntity(

                //Chiama la procedura di GET e recupera la lista di entity tramite descrizione
                prodottoRepository.procedureGet(
                                Constants.TYPE_GET_BY_DESCRIZIONE,
                                null,
                                descrizioneProdotto,
                                null));

    }

    //Ritorna la lista di prodotti completa dopo averci aggiunto il prodotto ricevuto dal Front-End
    @Override
    public List<ProdottoDto> createProdotto(ProdottoDto prodotto) {

        //Chiama la procedura di CRUD con casistica CREATE per effettuare la INSERT nel DataBase
        prodottoRepository.procedureCRUD(
                Constants.TYPE_CREATE, null, prodotto.getDescrizione(), prodotto.getPrezzo());

        //Ritorna la lista di prodotti completa
        return getAllProdotti();

    }

    //Esegue una modifica del prodotto ricevuto dal Front-End e lo ritorna aggiornato
    @Override
    @Transactional
    public ProdottoDto editProdottoById(ProdottoDto prodotto) {

        //Chiama la procedura di CRUD con casistica UPDATE per effettuare una UPDATE a DataBase
        prodottoRepository.procedureCRUD(
                Constants.TYPE_EDIT_BY_ID, prodotto.getId(), prodotto.getDescrizione(), prodotto.getPrezzo());

        //Ritorna il prodotto aggiornato
        return getProdottoById(prodotto.getId());

    }

    //Elimina il prodotto con id corrispondente
    @Override
    public List<ProdottoDto> deleteProdotto(Long idProdotto) {

        //Chiama la procedura di CRUD con casistica DELETE per effettuare l'eliminazione della riga a DataBase
        prodottoRepository.procedureCRUD(
                Constants.TYPE_DELETE, idProdotto, null, null);

        //Ritorna la lista di prodotti completa al netto dell'elemento appena eliminato
        return getAllProdotti();

    }

}
