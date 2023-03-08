package it.contech.TestTecnico.repository;

import it.contech.TestTecnico.model.ProdottoEntity;
import it.contech.TestTecnico.utils.ConstantsProcedures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdottoRepository extends JpaRepository<ProdottoEntity, Long>, JpaSpecificationExecutor<ProdottoEntity> {

    //Esecuzione della procedura CRUD che in base alla casistica passata esegue delle operazioni diverse
    @Modifying
    @Query(value = ConstantsProcedures.P_CRUD_PRODOTTO, nativeQuery = true)
    void procedureCRUD(String typeOp,
                       Long in_id,
                       String in_descrizione,
                       Double in_prezzo);

    //Esecuzione della procedura GET che in base alla casistica passata esegue delle select mirate
    @Query(value = ConstantsProcedures.P_GET_PRODOTTO, nativeQuery = true)
    List<ProdottoEntity> procedureGet(String typeOp,
                                   Long in_id,
                                   String in_descrizione,
                                   Double in_prezzo);

}
