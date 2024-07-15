  package Logica;
  import java.util.Date;
  
  public class Cliente extends Persona {
   private int idCliente;
   

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idCliente){
        this.idCliente=idCliente;
    }
    
    public Cliente(String nombre, String apellidPa, String apellidoMa,
      String colonia, String calle, String telefono, String correo, int numCasa, int cp,
      Date fechaNacimiento,int idCliente) {
       
        super(nombre, apellidPa, apellidoMa, colonia, calle, telefono, correo, numCasa, cp, fechaNacimiento);
        this.idCliente = idCliente;
    }

  }