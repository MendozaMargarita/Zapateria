package entidadesdenegocio;

public class ZapatoEN {

    private int id;
    private String nombre;
    private String marca;
    private String color;
    private String talla;

    public ZapatoEN() {
    }

    public ZapatoEN(int id, String nombre, String marca, String color, String talla) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.color = color;
        this.talla = talla;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }


}
