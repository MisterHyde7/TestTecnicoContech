package it.contech.TestTecnico.repository;

import it.contech.TestTecnico.model.FatturaEntity;
import it.contech.TestTecnico.utils.ConstantsProcedures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FatturaProdottiRepository extends JpaRepository<FatturaEntity, Long>, JpaSpecificationExecutor<FatturaEntity> {

    //Esecuzione della procedura CRUD che in base alla casistica passata esegue delle operazioni diverse
    @Modifying
    @Query(value = ConstantsProcedures.P_CRUD_FATTURA_PRODOTTI, nativeQuery = true)
    void procedureCRUD(String typeOp,
                       Long in_id_fattura,
                       Long in_id_prodotto);

    //Esecuzione della procedura GET che in base alla casistica passata esegue delle select mirate
    @Query(value = ConstantsProcedures.P_GET_FATTURA_PRODOTTI, nativeQuery = true)
    List<FatturaEntity> procedureGet(String typeOp,
                                     Long in_id_fattura,
                                     Long in_id_prodotto);

}
