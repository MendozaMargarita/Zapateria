import accesoadatos.ZapatosDAL;
import entidadesdenegocio.ZapatoEN;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;



public class ZapatoDALTest {

    @Test
    public void guardarTest() throws SQLException {
        ZapatoEN zapats = new ZapatoEN();
        zapats.setNombre("Sneakers");
        zapats.setMarca("NIKE");
        zapats.setColor("Blanco");
        zapats.setTalla("39");

        int esperado = 1;
        int actual = ZapatosDAL.guardar(zapats);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void modificarTest() throws SQLException {
        ZapatoEN zapats = new ZapatoEN();
        zapats.setId(2);
        zapats.setNombre("Sneakers");
        zapats.setMarca("NIKE");
        zapats.setColor("Blanco");
        zapats.setTalla("39");

        int esperado = 1;
        int actual = ZapatosDAL.modificar(zapats);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void eliminarTest() throws SQLException {
        ZapatoEN zapats = new ZapatoEN();
        zapats.setId(2); // Asegúrate de que este ID exista en la base de datos para eliminar

        int esperado = 1;
        int actual = ZapatosDAL.eliminar(zapats);
        Assertions.assertEquals(esperado, actual);
    }

    @Test
    public void obtenerTodosTest() throws SQLException {
        int actual = ZapatosDAL.obtenerTodos().size();
        Assertions.assertTrue(actual >= 0); // Esperamos al menos 0 si la base de datos está vacía
    }

    @Test
    public void obtenerDatosFiltradosTest() throws SQLException {
        ZapatoEN zapats = new ZapatoEN();
        zapats.setId(0);
        zapats.setNombre("");

        int actual = ZapatosDAL.obtenerDatosFiltrados(zapats).size();
        Assertions.assertTrue(actual >= 0); // Esperamos al menos 0 si no hay datos que coincidan
    }
}