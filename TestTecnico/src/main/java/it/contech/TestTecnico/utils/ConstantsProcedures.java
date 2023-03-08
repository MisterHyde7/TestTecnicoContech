package it.contech.TestTecnico.utils;

public class ConstantsProcedures {

    public static final String P_CRUD_FATTURA = "call p_crud_fattura(:typeOp, " +
            ":in_id, " +
            ":in_descrizione)";

    public static final String P_GET_FATTURA = "call p_get_fattura(:typeOp, " +
            ":in_id, " +
            ":in_descrizione)";

    public static final String P_CRUD_PRODOTTO = "call p_crud_prodotto(:typeOp, " +
            ":in_id, " +
            ":in_descrizione, " +
            ":in_prezzo)";

    public static final String P_GET_PRODOTTO = "call p_get_prodotto(:typeOp, " +
            ":in_id, " +
            ":in_descrizione, " +
            ":in_prezzo)";

    public static final String P_CRUD_FATTURA_PRODOTTI = "call p_crud_fattura_prodotti(:typeOp, " +
            ":in_id_fattura, " +
            ":in_id_prodotto)";

    public static final String P_GET_FATTURA_PRODOTTI = "call p_get_fattura_prodotti(:typeOp, " +
            ":in_id_fattura, " +
            ":in_id_prodotto)";

}
