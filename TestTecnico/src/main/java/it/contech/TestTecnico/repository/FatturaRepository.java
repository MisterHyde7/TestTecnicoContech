package it.contech.TestTecnico.repository;

import it.contech.TestTecnico.model.FatturaEntity;
import it.contech.TestTecnico.utils.ConstantsProcedures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FatturaRepository extends JpaRepository<FatturaEntity, Long>, JpaSpecificationExecutor<FatturaEntity> {

    //Esecuzione della procedura CRUD che in base alla casistica passata esegue delle operazioni diverse
    @Modifying
    @Query(value = ConstantsProcedures.P_CRUD_FATTURA, nativeQuery = true)
    void procedureCRUD(String typeOp,
                       Long in_id,
                       String in_descrizione);

    //Esecuzione della procedura GET che in base alla casistica passata esegue delle select mirate
    @Query(value = ConstantsProcedures.P_GET_FATTURA, nativeQuery = true)
    List<FatturaEntity> procedureGet(String typeOp,
                                     Long in_id,
                                     String in_descrizione);

}
