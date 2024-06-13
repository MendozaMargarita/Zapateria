package interfazgrafica;

import entidadesdenegocio.ZapatoEN;
import logicanegocios.zapatosBL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatenimientoZapatos  extends JFrame {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtMarca;
    private JTextField txtColor;
    private JTextField txtTalla;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JButton btnSalir;
    private JRadioButton rbdId;
    private JRadioButton rbdNombre;
    private JRadioButton rbdMarca;
    private JTextField txtCriterio;
    private JButton btnBuscar;
    private JPanel JPrincipal;
    private JTable jtZapatos;
    private ButtonGroup criterioBusqueda;

    ArrayList<ZapatoEN> ListaZapatos;
    ZapatoEN zapato;
    zapatosBL zapatoBL = new zapatosBL();

    public static void main(String[] args) throws SQLException {
        new MatenimientoZapatos();
    }

    public MatenimientoZapatos() throws SQLException {
        inicializar();
        actualizarForm();
        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try{
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                txtNombre.setEnabled(true);
                txtMarca.setEnabled(true);
                txtColor.setEnabled(true);
                txtTalla.setEnabled(true);
                txtNombre.grabFocus();
                btnGuardar.setEnabled(true);
                btnNuevo.setEnabled(false);
                btnCancelar.setEnabled(true);
            }
        });
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               ZapatoEN zapats = new ZapatoEN();
               zapats = new ZapatoEN();
                zapats.setNombre(txtNombre.getText());
                zapats.setMarca(txtMarca.getText());
                zapats.setTalla(txtTalla.getText());
                zapats.setColor(txtColor.getText());

                try{
                    zapatoBL.guardar(zapats);
                    JOptionPane.showMessageDialog(null, "Se guardó con éxito");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ZapatoEN zapats = new ZapatoEN();
                zapats = new ZapatoEN();
                zapats.setNombre(txtNombre.getText());
                zapats.setMarca(txtMarca.getText());
                zapats.setTalla(txtTalla.getText());
                zapats.setColor(txtColor.getText());
                try{
                    zapatoBL.modificar(zapats);
                    JOptionPane.showMessageDialog(null, "Se modificó con éxito");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jtZapatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = jtZapatos.getSelectedRow();
                txtId.setText(jtZapatos.getValueAt(fila, 0).toString());
                txtNombre.setText(jtZapatos.getValueAt(fila, 1).toString());
                txtMarca.setText(jtZapatos.getValueAt(fila, 2).toString());
                txtTalla.setText(jtZapatos.getValueAt(fila, 3).toString());
                txtColor.setText(jtZapatos.getValueAt(fila, 4).toString());



                txtNombre.setEnabled(true);
                txtMarca.setEnabled(true);
                txtTalla.setEnabled(true);
                txtColor.setEnabled(true);


                btnNuevo.setEnabled(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnCancelar.setEnabled(true);
            }
        });
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ZapatoEN zapats = new ZapatoEN();
                zapats.setId(Integer.parseInt(txtId.getText()));
                try{
                    zapatoBL.eliminar(zapats);
                    JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(txtCriterio.getText().equals("") || (!rbdId.isSelected() &&
                        !rbdNombre.isSelected() && !rbdMarca.isSelected()) ){
                    JOptionPane.showMessageDialog(null,
                            "Seleccione un criterio de búsqueda o escriba el valor a buscar");
                }

                zapato  = new ZapatoEN();

                if(rbdId.isSelected()){
                    zapato.setId(Integer.parseInt(txtCriterio.getText()));
                    try{
                        llenarTabla(zapatoBL.obtenerDatosFiltrados(zapato));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(rbdNombre.isSelected()){
                    zapato.setNombre(txtCriterio.getText());
                    try{
                        llenarTabla(zapatoBL.obtenerDatosFiltrados(zapato));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(rbdMarca.isSelected()){
                    zapato.setMarca(txtCriterio.getText());
                    try{
                        llenarTabla(zapatoBL.obtenerDatosFiltrados(zapato));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    void inicializar(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 700);
        setTitle("Mantenimiento Zapatos");
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        criterioBusqueda = new ButtonGroup();
        criterioBusqueda.add(rbdId);
        criterioBusqueda.add(rbdNombre);
        criterioBusqueda.add(rbdMarca);

        setContentPane(JPrincipal);
        setVisible(true);
    }

    void llenarTabla(ArrayList<ZapatoEN> zapatosBL){
        Object[] objects = new Object[5];
        ListaZapatos = new ArrayList<>();
        String[] encabezado = {"ID", "NOMBRE", "MARCA", "TALLA", "COLOR"};
        DefaultTableModel tm = new DefaultTableModel(null, encabezado);
        ListaZapatos.addAll(zapatosBL);
        for(ZapatoEN item : ListaZapatos){
            objects[0] = item.getId();
            objects[1] = item.getNombre();
            objects[2] = item.getMarca();
            objects[3] = item.getTalla();
            objects[4] = item.getColor();

            tm.addRow(objects);
        }
        jtZapatos.setModel(tm);
    }

    void actualizarForm() throws SQLException {
        txtId.setText("");
        txtNombre.setText("");
        txtMarca.setText("");
        txtTalla.setText("");
        txtColor.setText("");


        txtId.setEnabled(false);
        txtNombre.setEnabled(false);
        txtMarca.setEnabled(false);
        txtTalla.setEnabled(false);
        txtColor.setEnabled(false);


        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);

        txtCriterio.setText("");
        criterioBusqueda.clearSelection();

        llenarTabla(zapatoBL.obtenerTodos());
    }
}


