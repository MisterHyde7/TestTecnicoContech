package it.contech.TestTecnico.controller;

import it.contech.TestTecnico.dto.FatturaDto;
import it.contech.TestTecnico.service.FattureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/fatture")
public class FattureController {

    //Injection dei service
    @Autowired
    FattureService fattureService;

    //Ritorna la lista completa delle fatture
    @GetMapping("/getAllFatture")
    public ResponseEntity<List<FatturaDto>> getAllFatture() {

        return ResponseEntity.ok(fattureService.getAllFatture());

    }

    //Ritorna la lista di fatture con la stessa descrizione
    @GetMapping("/getFatturaByDescrizione")
    public ResponseEntity<List<FatturaDto>> getFatturaByDescrizione(String descrizioneFattura) {

        return ResponseEntity.ok(fattureService.getFatturaByDescrizione(descrizioneFattura));

    }

    //Ritorna la fattura con lo stesso id
    @GetMapping("/getFatturaById")
    public ResponseEntity<FatturaDto> getFatturaById(Long idFattura) {

        return ResponseEntity.ok(fattureService.getFatturaById(idFattura));

    }

    //Ritorna la lista di fatture completa dopo averci aggiunto la fattura ricevuto dal Front-End
    @PostMapping("/createFattura")
    public ResponseEntity<List<FatturaDto>> createFattura(@RequestBody FatturaDto fattura) {

        return ResponseEntity.ok(fattureService.createFattura(fattura));

    }

    //Esegue una modifica della fattura ricevuta dal Front-End e la ritorna aggiornata
    @PostMapping("/editFattura")
    public ResponseEntity<FatturaDto> editFattura(@RequestBody FatturaDto fattura) {

        //Aggiorna la vecchia fattura con i nuovi dati appena caricati
        fattura = fattureService.editFatturaById(fattura);

        return ResponseEntity.ok(fattura);

    }

    //Elimina la fattura con id corrispondente
    @PostMapping("/deleteFattura")
    public ResponseEntity<List<FatturaDto>> deleteFattura(@RequestBody Long idFattura) {

        //Elimina la fattura e tutti i puntamenti ai prodotti associati
        return ResponseEntity.ok(fattureService.deleteFattura(idFattura));

    }

    //Aggiorna la fattura con la nuova lista di prodotti
    @PostMapping("/editProdottiInFattura")
    public ResponseEntity<FatturaDto> editProdottiInFattura(@RequestBody FatturaDto fattura) {

        //Aggiorna la lista di prodotti nella fattura e la restituisce aggiornata
        return ResponseEntity.ok(fattureService.editProdottiInFattura(fattura));

    }

    //Ritorna il costo della fattura con lo stesso id
    @GetMapping("/getCostoFatturaById")
    public ResponseEntity<Double> getCostoFatturaById(Long idFattura) {

        return ResponseEntity.ok(fattureService.getCostoFatturaById(idFattura));

    }

}
