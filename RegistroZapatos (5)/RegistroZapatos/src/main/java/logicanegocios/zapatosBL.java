package logicanegocios;

import accesoadatos.ZapatosDAL;
import entidadesdenegocio.ZapatoEN;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class zapatosBL {
    public int guardar(ZapatoEN zapatos) throws SQLException {
        return ZapatosDAL.guardar(zapatos);
    }

    public int modificar(ZapatoEN zapatos) throws SQLException {
        return ZapatosDAL.modificar(zapatos);
    }

    public int eliminar(ZapatoEN zapatos) throws SQLException {
        return ZapatosDAL.eliminar(zapatos);
    }

    public ArrayList<ZapatoEN> obtenerTodos() throws SQLException {
        return ZapatosDAL.obtenerTodos();
    }

    public ArrayList<ZapatoEN> obtenerDatosFiltrados(ZapatoEN zapatos) throws SQLException {
        return ZapatosDAL.obtenerDatosFiltrados(zapatos);
    }


}