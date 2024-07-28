package Logica.Objetos;
import java.util.Date;

public class TipoMovimiento extends Movimiento {
    // ATRIBUTOS
    private int idTipoMovimiento;
    private String nombre,descripcion;
  
    // CONSTRUCTORES 
    public TipoMovimiento(int idMovimiento, String comentario, String estado, Date fechaMov,int idTipoMovimiento, String nombre, String descripcion) {
        super(idMovimiento,comentario,estado,fechaMov);
        this.idTipoMovimiento = idTipoMovimiento;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // METODOS

    // GETTERS AND SETTERS

    public int getIdTipoMovimiento() {
        return this.idTipoMovimiento;
    }

    public void setIdTipoMovimiento(int idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}