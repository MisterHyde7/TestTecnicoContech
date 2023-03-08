package it.contech.TestTecnico.controller;

import it.contech.TestTecnico.dto.ProdottoDto;
import it.contech.TestTecnico.service.ProdottiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/prodotti")
public class ProdottiController {

    //Injection dei service
    @Autowired
    ProdottiService prodottiService;

    //Ritorna la lista completa dei prodotti
    @GetMapping("/getAllProdotti")
    public ResponseEntity<List<ProdottoDto>> getAllProdotti() {

        return ResponseEntity.ok(prodottiService.getAllProdotti());

    }

    //Ritorna il prodotto con lo stesso id
    @GetMapping("/getProdottoById")
    public ResponseEntity<ProdottoDto> getProdottoById(Long idProdotto) {

        return ResponseEntity.ok(prodottiService.getProdottoById(idProdotto));

    }

    //Ritorna la lista di prodotti con la stessa descrizione
    @GetMapping("/getProdottoByDescrizione")
    public ResponseEntity<List<ProdottoDto>> getProdottoByDescrizione(String descrizioneProdotto) {

        return ResponseEntity.ok(prodottiService.getProdottoByDescrizione(descrizioneProdotto));

    }

    //Ritorna la lista di prodotti completa dopo averci aggiunto il prodotto ricevuto dal Front-End
    @PostMapping("/createProdotto")
    public ResponseEntity<List<ProdottoDto>> createProdotto(@RequestBody ProdottoDto fattura) {

        return ResponseEntity.ok(prodottiService.createProdotto(fattura));

    }

    //Esegue una modifica del prodotto ricevuto dal Front-End e lo ritorna aggiornato
    @PostMapping("/editProdotto")
    public ResponseEntity<ProdottoDto> editProdotto(@RequestBody ProdottoDto prodotto) {

        //Aggiorna il vecchio prodotto con i nuovi dati appena caricati
        prodotto = prodottiService.editProdottoById(prodotto);

        return ResponseEntity.ok(prodottiService.editProdottoById(prodotto));

    }

    //Elimina il prodotto con id corrispondente
    @PostMapping("/deleteProdotto")
    public ResponseEntity<List<ProdottoDto>> deleteProdotto(@RequestBody Long idProdotto) {

        return ResponseEntity.ok(prodottiService.deleteProdotto(idProdotto));

    }

}
