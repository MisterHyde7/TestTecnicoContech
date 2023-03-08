package it.contech.TestTecnico.service.impl;

import it.contech.TestTecnico.dto.FatturaDto;
import it.contech.TestTecnico.repository.FatturaProdottiRepository;
import it.contech.TestTecnico.repository.FatturaRepository;
import it.contech.TestTecnico.service.FattureService;
import it.contech.TestTecnico.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FattureServiceImpl implements FattureService {

    //Injection dei service
    @Autowired
    FatturaRepository fatturaRepository;

    @Autowired
    FatturaProdottiRepository fatturaProdottiRepository;

    //Ritorna la lista completa delle fatture
    @Override
    public List<FatturaDto> getAllFatture() {

        //Prende la lista di entity e le trasforma in Dto
        return FatturaDto.listFromEntity(

                //Chiama la procedura di GET con la tipologia GET_ALL
                fatturaRepository.procedureGet(
                        Constants.TYPE_GET_ALL,
                        null,
                        null));

    }

    //Ritorna la fattura con lo stesso id
    @Override
    public FatturaDto getFatturaById(Long idFattura) {

        //Trasforma l'entity in Dto
        return FatturaDto.fromEntity(

                //Chiama la procedura di GET e recupera l'entity tramite id
                fatturaRepository.procedureGet(
                                Constants.TYPE_GET_BY_ID,
                                idFattura,
                                null)

                        //Prende la stream di dati e ritorna il primo risultato essendo l'id unico
                        .stream().findFirst().orElse(null));

    }



    //Ritorna la lista di fatture con la stessa descrizione
    @Override
    public List<FatturaDto> getFatturaByDescrizione(String descrizioneFattura) {

        //Prende la lista di entity e le trasforma in Dto
        return FatturaDto.listFromEntity(

                //Chiama la procedura di GET e recupera la lista di entity tramite descrizione
                fatturaRepository.procedureGet(
                        Constants.TYPE_GET_BY_DESCRIZIONE,
                        null,
                        descrizioneFattura));

    }

    //Ritorna la lista di fatture completa dopo averci aggiunto la fattura ricevuta dal Front-End
    @Override
    @Transactional
    public List<FatturaDto> createFattura(FatturaDto fattura) {

        //Chiama la procedura di CRUD con casistica CREATE per effettuare la INSERT nel DataBase
        fatturaRepository.procedureCRUD(
                Constants.TYPE_CREATE, null, fattura.getDescrizione());

        //Ritorna la lista di prodotti completa
        return getAllFatture();

    }

    //Esegue una modifica della fattura ricevuta dal Front-End e la ritorna aggiornata
    @Override
    @Transactional
    public FatturaDto editFatturaById(FatturaDto fattura) {

        //Chiama la procedura di CRUD con casistica UPDATE per effettuare una UPDATE a DataBase
        fatturaRepository.procedureCRUD(
                Constants.TYPE_EDIT_BY_ID, fattura.getId(), fattura.getDescrizione());

        //Ritorna il prodotto aggiornato
        return getFatturaById(fattura.getId());

    }

    //Elimina la fattura con id corrispondente
    @Override
    @Transactional
    public List<FatturaDto> deleteFattura(Long idFattura) {

        //Chiama la procedura di CRUD con casistica DELETE per effettuare l'eliminazione della riga a DataBase
        // con tutti i puntamenti nella join table
        fatturaRepository.procedureCRUD(
                Constants.TYPE_DELETE, idFattura, null);

        //Ritorna la lista di prodotti completa al netto dell'elemento appena eliminato
        return getAllFatture();

    }

    @Override
    @Transactional
    public FatturaDto editProdottiInFattura(FatturaDto fattura) {

        //Ripulisce i prodotti collegati alla fattura
        fatturaProdottiRepository.procedureCRUD(
                Constants.TYPE_CLEAR, fattura.getId(), null);

        //Ciclo i prodotti e aggiorno i collegamenti alla join table
        fattura.getListaProdotti().forEach(prodotto -> {

            //Chiama la procedura di CRUD con casistica INSERT per effettuare una scrittura a DataBase
            fatturaProdottiRepository.procedureCRUD(
                    Constants.TYPE_EDIT_PRODUCTS, fattura.getId(), prodotto.getId());

        });

        //Ritorna il prodotto aggiornato
        return getFatturaById(fattura.getId());

    }

}
