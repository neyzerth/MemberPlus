package Presentacion.Menus;

import Logica.Sesion;
import Logica.Objetos.Beneficio;
import Logica.Objetos.Movimiento;
import Logica.Objetos.Nivel;
import Logica.Objetos.Tarjeta;
import Presentacion.Despliegue.*;
import Presentacion.Formato.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

//------------ MODULO PRINCIPAL ---------
public class ModMembresia {
    public static void desplegarMenu() {
        boolean salir = false;

        do {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Membresias <")));
            String[] modulos = {
                    Color.morado("Administrar Tarjetas"),
                    Color.morado("Administrar Niveles de Membresias"),
                    Color.morado("Administrar Beneficios"),
                    Color.morado("Administrar Movimientos")
            };

            Cuadro menuTarjeta = new Cuadro();
            if (Sesion.getRol().equals("administrador"))
                menuTarjeta = new Cuadro(modulos);
            else
                menuTarjeta = new Cuadro(modulos[0], modulos[1], modulos[2]);

            menuTarjeta.agregarSalir();
            menuTarjeta.imprimirCuadroNum();

            System.out.println();

            int opc = Leer.entero(Color.cian(Texto.espacio(1) + "> Seleccione una opción: "));

            
            switch (opc) {
                case 1:
                    SubmodTarjeta.desplegarMenu();
                    break;
                case 2:
                    SubmodNivel.desplegarMenu();
                    break;
                case 3:
                    SubmodBeneficio.desplegarMenu();
                    break;

                case 4:
                    if(Sesion.getRol().equals("administrador")){
                        SubmodMovimiento.desplegarMenu();
                        break;
                    }
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
                    break;
                case 0:
                    salir = true;
                    break;
            }
        } while (!salir);

    }
}

// ------------ MODULO DE TARJETA ---------
class SubmodTarjeta extends Menu {

    public SubmodTarjeta() {
        super("Tarjeta", "Tarjetas");
        String [] modulos = {
                Color.morado("Lista de " + modPlur),
                Color.morado("Información de " + modSing),
                Color.morado("Renovar " + modSing),
                Color.morado("Eliminar " + modSing)

        };
        //Desplegar menu de manera distinta para el administrador
        if (Sesion.getRol().equals("administrador")) 
            opciones = new Cuadro(modulos); //todos
        else
            opciones = new Cuadro(modulos[0], modulos[1], modulos[2]);
        this.opciones.agregarSalir();
    }

    public static void desplegarMenu() {
        SubmodTarjeta modTarjeta = new SubmodTarjeta();
        modTarjeta.menu();
    }

    @Override
    public boolean conexionMenus(int opcion) {
        switch (opcion) {
            case 1:
                menuVerTodos();
                break;
            case 2:
                menuVerUno();
                break;
            case 3:
                menuActualizar();
                break;
            case 4:
                if(Sesion.getRol().equals("adnimistrador")){
                    menuEliminar();
                    break;
                }


            default:
                System.out.println();
                System.out.println(Color.rojo(" Opción inválida, por favor intente de nuevo."));
                Texto.esperar(1);
                break;
                
            case 0:
                return true;
        }
        return false;

    }

    @Override
    public void menuVerUno() {
        Cuadro info = new Cuadro(Color.morado(" Informacion de " + modSing));

        Texto.limpiarPantalla();
        info.imprimirCuadro();

        tabla();

        System.out.println();
        String numTarjetaStr = Leer.cadena(Color.cian(" > Numero de " + modSing + " a ver: "));
        String numTarjeta = numTarjetaStr.replace(" ", "");

        if (!tabla(numTarjeta)) {
            System.out.println();
            Texto.esperarEnter(Color.rojo(" No existe " + modSing + " con el numero " + numTarjeta + "..."));
            return;
        }

        Texto.esperarEnter();
    }

    @Override
    public void menuActualizar() {

        Cuadro actualizar = new Cuadro(Color.morado(" Renovar informacion de " + modSing));
        Texto.limpiarPantalla();

        actualizar.imprimirCuadro();

        System.out.println();
        String numTarjetaStr = Leer.cadena(Color.cian(" > Numero de " + modSing + " a modificar: "));
        String numTarjeta = numTarjetaStr.replace(" ", "");

        if (!tabla(numTarjeta)) {
            System.out.println();
            Texto.esperarEnter(
                    Color.rojo(Color.negrita(" No existe " + modSing + " con el numero " + numTarjeta + "...")));
            return;
        }

        Tarjeta tarjeta = Tarjeta.importarTarjeta(numTarjeta);
        boolean salir = false;

        do {
            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" ¿Que desea realizar?")));
            System.out.println(Color.cian(" Renovar $" + tarjeta.nivel.getAnualidad() + "[1] - Cambiar nivel [2] - Salir [3]"));
            int opc = Leer.entero("> ");
            switch (opc) {
                case 1:

                    System.out.println(Color.rojo(" Fecha de vencimiento: " + tarjeta.getFecVen()));

                    LocalDate fechaActual = LocalDate.now();
                    LocalDate fechaVencimiento = tarjeta.getFecVen().toLocalDate();

                    long mesesRestantes = ChronoUnit.MONTHS.between(fechaActual, fechaVencimiento);
                    if (mesesRestantes <= 2) {
                        tarjeta.renovar();
                        System.out.println();
                        System.out.println(Color.verde(" Tarjeta renovada con exito..."));
                        tarjeta.actualizarTarjeta();
                        System.out.println();
                        System.out.println(Color.rojo(" Nueva fecha de vencimiento: " + tarjeta.getFecVen()));
                        Movimiento.renovacion(Color.amarillo(" Renovacion de tarjeta"), tarjeta);
                    } else {
                        System.out.println();
                        System.out.println(Color.rojo(Color.negrita(" No es posible renovar la tarjeta todavía.")));
                    }
                    Texto.esperarEnter();

                    break;
                case 2:
                    System.out.println(Color.amarillo(" Nivel actual: " + tarjeta.nivel.getNombre()));
                    SubmodNivel modNivel = new SubmodNivel();
                    modNivel.tabla();
                    System.out.println();
                    int idNivel = Leer.entero(Color.cian(" > ID del nuevo nivel: "));
                    Nivel nivel = null;
                    if(!Nivel.validarNivel(idNivel)){
                        System.out.println("Ese nivel no existe");
                        break;
                    }          

                    nivel = Nivel.importarNiveles(idNivel);
                    tarjeta.nivel = nivel;
                    if (tarjeta.actualizarTarjeta()) {
                        System.out.println(Color.verde(" Nuevo nivel: " + tarjeta.nivel.getNombre()));
                        Movimiento.renovacion(Color.amarillo(" Cambiar nivel"), tarjeta);
                    } else {
                        System.out.println(Color.rojo(" Error al cambiar nivel "));
                    }

                    break;
                case 3:
                    salir = true;
                    break;

                default:
                    System.out.println();
                    Texto.esperarEnter(Color.rojo(" Opcion no valida"));
                    break;
            }
        } while (!salir);
    }

    @Override
    public void menuEliminar() {
        boolean repetir = false;

        Cuadro eliminar = new Cuadro(
                Color.morado(" Eliminar " + modSing));

        do {
            Texto.limpiarPantalla();

            eliminar.imprimirCuadro();
            tabla();

            System.out.println();
            String numTarjetaStr = Leer.cadena(Color.cian(" > Numero de " + modSing + " a eliminar: "));
            String numTarjeta = numTarjetaStr.replace(" ", "");

            if (!tabla(numTarjeta)) {
                System.out.println();
                Texto.esperarEnter(
                        Color.rojo(Color.negrita(" No existe " + modSing + " con numero " + numTarjeta + "...")));
                repetir = true;
                return;
            }

            System.out.println();
            System.out.println(Color.rojo(Color.negrita(" ¿Seguro que desea eliminar este " + modSing + "?")));
            boolean conf = Leer.cadena(Color.rojo(" SI[s]  NO[n]: ")).toLowerCase().equals("s");
            System.out.println();

            if (conf) {
                Tarjeta tarjeta = Tarjeta.importarTarjeta(numTarjeta);
                Movimiento.cancelacion(Color.rojo(" Cancelacion de membresia"), tarjeta);
                if (eliminar(numTarjeta)) {
                    tabla();
                    System.out.println();
                    Texto.esperarEnter(Color.verde(" " + modSing + " eliminado con exito"));

                    repetir = false;
                    return;
                } else
                    System.out.println();
                Texto.esperarEnter(Color.rojo(" Error al eliminar " + modSing));
            }
        } while (repetir);
    }

    @Override
    public boolean registrar() {
        return false;
    }

    @Override
    public boolean actualizar(int id) {
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return Tarjeta.eliminarTarjeta(id);
    }

    public boolean eliminar(String numTarjeta) {
        return Tarjeta.cancelarTarjeta(numTarjeta);
    }

    @Override
    public void tabla() {
        Tarjeta[] tarjetas = Tarjeta.importarTarjeta();

        tabla = new Tabla(Color.amarillo("Numero de tarjeta"), Color.amarillo("Fecha vencimiento"),
                Color.amarillo("Cliente"), Color.amarillo("Nivel"));

        for (Tarjeta tarjeta : tarjetas) {
            tabla.agregarFila(
                    Texto.tarjeta(tarjeta.getNumTarjeta()),
                    Texto.fecha(tarjeta.getFecVen()),
                    tarjeta.cliente.getNombre(),
                    tarjeta.nivel.getNombre());
        }

        tabla.imprimirTablaSimple();
    }

    @Override
    public boolean tabla(int id) {
        if (!Tarjeta.validarIdTarjeta(id))
            return false;
        Tarjeta tarjeta = Tarjeta.importarTarjeta(id);
        tabla = new Tabla(Color.amarillo("Numero de tarjeta"), Color.amarillo("Cliente"), Color.amarillo("Nivel"),
                Color.amarillo("Saldo"), Color.amarillo("Puntos"), Color.amarillo("Fecha de vencimiento"));

        tabla.agregarFila(
                Texto.tarjeta(tarjeta.getNumTarjeta()),
                tarjeta.cliente.getNombre(),
                tarjeta.nivel.getNombre(),
                tarjeta.getSaldo(),
                tarjeta.getPuntos(),
                Texto.fecha(tarjeta.getFecVen()));

        tabla.imprimirTablaSimple();
        return true;
    }

    public boolean tabla(String numTarjeta) {
        if (!Tarjeta.validarNumTarjeta(numTarjeta))
            return false;
        Tarjeta tarjeta = Tarjeta.importarTarjeta(numTarjeta);
        return tabla(tarjeta.getIdTarjeta());
    }

    public int obtenerIdUsuarioActual() {
        return Sesion.getId();

    }

}

// ------------ MODULO DE NIVEL ---------
class SubmodNivel extends Menu {

    public SubmodNivel() {
        super("Nivel", "Niveles");
    }

    public static void desplegarNiveles() {
        SubmodNivel niveles = new SubmodNivel();
        niveles.tabla();
    }

    public static void desplegarMenu() {
        SubmodNivel modNivel = new SubmodNivel();
        modNivel.menu();
    }

    @Override
    public boolean registrar() {
        Nivel nivel = new Nivel();
        nombre(nivel);
        apertura(nivel);
        anualidad(nivel);
        beneficio(nivel);

        return nivel.insertarNivel();
    }

    @Override
    public boolean actualizar(int id) {
        Nivel nivel = Nivel.importarNiveles(id);
        pedirDatos(nivel);

        if (nivel == null)
            return false;

        nivel.setIdNivel(id);
        return nivel.actualizarNivel();
    }

    @Override
    public boolean eliminar(int id) {
        return Nivel.eliminarNivel(id);
    }

    @Override
    public void tabla() {
        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre"), Color.amarillo("Anualidad"));

        Nivel[] niveles = Nivel.importarNiveles();

        for (Nivel nivel : niveles) {
            tabla.agregarFila(
                    nivel.getIdNivel(),
                    nivel.getNombre(),
                    Texto.moneda(nivel.getAnualidad()));
        }
        tabla.imprimirTablaSimple();

    }

    @Override
    public boolean tabla(int id) {
        if (!Nivel.validarNivel(id))
            return false;
        Nivel nivel = Nivel.importarNiveles(id);

        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre"), Color.amarillo("Costo Apertura"),
                Color.amarillo("Anualidad"), Color.amarillo("Beneficios"));

        tabla.agregarFila(
                nivel.getIdNivel(),
                nivel.getNombre(),
                Texto.moneda(nivel.getCostoApertura()),
                Texto.moneda(nivel.getAnualidad()),
                nombresBeneficios(nivel.getBeneficios())

        );
        tabla.imprimirTablaSimple();

        return true;
    }

    public Nivel pedirDatos(Nivel nivel) {
        System.out.println();
        int opc;
        do {
            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" ¿Que desea actualizar?")));
            System.out.println();
            Cuadro nivelCuadro = new Cuadro(Color.amarillo("Nombre de nivel"), Color.amarillo("Costo de Apertura"), Color.amarillo("Costo de Anualidad"), Color.amarillo("Agregar Beneficios"));
            nivelCuadro.agregarSalir();
            nivelCuadro.imprimirCuadroNum();
            opc = Leer.entero(Color.cian(" > "));
            switch (opc) {
                case 1: nombre(nivel);
                    break;
                case 2: apertura(nivel);
                    break;
                case 3: anualidad(nivel);
                    break;
                case 4: beneficio(nivel);
                    break;
                case 0: break;
            
                default:
                    System.out.println();
                    System.out.println(Color.rojo(Color.negrita(" Opcion no valida")));
                    break;
            }
        } while (opc!=0);
        
        return nivel;
        
    }

    public static void nombre(Nivel nivel){
        boolean repetir = false;
        do {
            repetir = false;
            try {
                nivel.setNombre(Leer.cadena(Color.cian(Color.negrita(" > Nombre del nivel: "))));
            } catch (Exception e) {
                System.out.println(Color.rojo(Color.negrita(e.getMessage())));
                repetir = true;
            }
        } while (repetir);
    }
    public static void apertura(Nivel nivel){
        boolean repetir = false;
        do {
            repetir = false;
            try {
                nivel.setCostoApertura(Leer.entero(Color.cian(Color.negrita(" > Costo de apertura: $  "))));

            } catch (Exception e) {
                System.out.println(Color.rojo(Color.negrita(e.getMessage())));
                repetir = true;
            }
        } while (repetir);
    }
    public static void anualidad(Nivel nivel){
        boolean repetir = false;
        do {
            repetir = false;
            try {
                nivel.setAnualidad(Leer.cadena(Color.cian(Color.negrita(" > Costo de la anualidad: $  "))));
            } catch (Exception e) {
                System.out.println(Color.rojo(Color.negrita(e.getMessage())));
                repetir = true;
            }
        } while (repetir);
    }
    public static void beneficio(Nivel nivel){
        boolean repetir = false;
        do {
            repetir = false;
            try {
                SubmodBeneficio verBeneficios = new SubmodBeneficio();
                boolean agregarNivel = false;
                verBeneficios.tabla();
                System.out.println();
                do {
                    System.out.println();
                    int idBeneficio = Leer.entero(Color.cian(" > Selecciona ID del beneficio: "));
                    if (Beneficio.validarBeneficio(idBeneficio)) {
                        try {
                            nivel.agregarBeneficio(idBeneficio);
                            System.out.println(Color.amarillo(" > ¿Desea agregar otro beneficio?"));
                            String opc = Leer.cadena(Color.amarillo(" > SI[s] NO[n]: "));
                            agregarNivel = opc.toLowerCase().equals("s");
                        } catch (Exception e) {
                            System.out.println(Color.rojo(Color.negrita(e.getMessage())));
                        }
                    } else {
                        System.out.println();
                        System.out.println(Color.rojo("  El beneficio no existe"));
                        agregarNivel = true;
                    }
                } while (agregarNivel);
            } catch (Exception e) {
                System.out.println(Color.rojo(Color.negrita(e.getMessage())));
                repetir = true;
            }
        } while (repetir);
    }

    public String nombresBeneficios(Beneficio[] beneficios) {
        String nomBeneficios = "";
        for (int i = 0; i < beneficios.length; i++) {
            nomBeneficios += beneficios[i].getNombre();
            if ((i < beneficios.length - 1))
                nomBeneficios += " - ";
        }
        return nomBeneficios;
    }

}

// ------------ MODULO DE BENEFICIO ---------
class SubmodBeneficio extends Menu {

    public SubmodBeneficio() {
        super("Beneficio", "Beneficios");
    }

    public static void desplegarMenu() {
        SubmodBeneficio menuBeneficio = new SubmodBeneficio();
        menuBeneficio.menu();
    }

    @Override
    public void tabla() {
        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre"), Color.amarillo("Puntos"),
                Color.amarillo("Cashback"), Color.amarillo("Descuento"));

        Beneficio[] beneficios = Beneficio.importarBeneficios();

        for (Beneficio beneficio : beneficios) {
            tabla.agregarFila(
                    beneficio.getIdBeneficio(),
                    beneficio.getNombre(),
                    beneficio.getPorcPuntos() + "%",
                    beneficio.getPorcCashBack() + "%",
                    beneficio.getPorcDescuento() + "%");
        }

        tabla.imprimirTablaSimple();
    }

    @Override
    public boolean tabla(int id) {
        if (!Beneficio.validarBeneficio(id))
            return false;

        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre"), Color.amarillo("Fecha de inicio"),
                Color.amarillo("Fecha de vencimiento"), Color.amarillo("Puntos"), Color.amarillo("Cashback"),
                Color.amarillo("Descuento"));

        Beneficio beneficio = Beneficio.importarBeneficios(id);

        tabla.agregarFila(
                beneficio.getIdBeneficio(),
                beneficio.getNombre(),
                beneficio.getFecInicio(),
                beneficio.getFecVen(),
                beneficio.getPorcPuntos() + "%",
                beneficio.getPorcCashBack() + "%",
                beneficio.getPorcDescuento() + "%");

        tabla.imprimirTablaSimple();
        return true;
    }

    @Override
    public boolean registrar() {
        Beneficio beneficio = new Beneficio();
        nombre(beneficio);
        fechaInicio(beneficio);
        fechaVencimiento(beneficio);
        puntos(beneficio);
        cashback(beneficio);
        descuento(beneficio);

        beneficio.insetarBeneficio();
        return tabla(beneficio.getIdBeneficio());
        
    }

    @Override
    public boolean actualizar(int id) {
        Beneficio beneficio = Beneficio.importarBeneficios(id);
        if (beneficio == null) {
            System.out.println();
            Texto.esperarEnter(Color.rojo(" Beneficio no encontrado"));
            return false;
        }

        pedirDatos(beneficio);

        beneficio.actualizarBeneficio();
        return tabla(id);
    }

    @Override
    public boolean eliminar(int id) {
        return Beneficio.eliminarBeneficio(id);
    }

    public Beneficio pedirDatos(Beneficio beneficio) {
        System.out.println();
        int opc;
        do {
            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" ¿Que desea actualizar?")));
            System.out.println();
            Cuadro beneCuadro = new Cuadro(Color.amarillo("Nombre"), Color.amarillo("FechaInicio"), Color.amarillo("FechaVencimiento"), Color.amarillo("Puntos"), Color.amarillo("Cashback"), Color.amarillo("Descuento"));
            beneCuadro.agregarSalir();
            beneCuadro.imprimirCuadroNum();
            
            opc = Leer.entero(Color.cian(" > "));
            switch (opc) {
                case 1: nombre(beneficio);
                    break;
                case 2: fechaInicio(beneficio);
                    break;
                case 3: fechaVencimiento(beneficio);
                    break;
                case 4: puntos(beneficio);
                    break;
                case 5: cashback(beneficio);
                    break;
                case 6: descuento(beneficio);
                    break;
                case 0: break;
            
                default:
                    System.out.println();
                    System.out.println(Color.rojo(Color.negrita(" Opcion no valida")));
                    break;
            }
        } while (opc!=0);
        
        return beneficio;

    }

    public static void nombre(Beneficio beneficio){
        boolean repetir = false;
        do {
            repetir = false;
            try {
                beneficio.setNombre(Leer.cadena(Color.cian(Color.negrita(" > Nombre del beneficio: "))));
            } catch (Exception e) {
                System.out.println(Color.rojo(Color.negrita(e.getMessage())));
                repetir = true;
            }
        } while (repetir);

    }

    public static void fechaInicio(Beneficio beneficio){
        boolean repetir = false;
        do {
            repetir = false;
            try {
                System.out.println(Color.amarillo(Color.negrita(" Fecha de inicio")));
                int dia = Leer.entero(Color.cian(Color.negrita(" > Dia: ")));
                int mes = Leer.entero(Color.cian(Color.negrita(" > Mes: ")));
                int anio = Leer.entero(Color.cian(Color.negrita(" > Año: ")));
                beneficio.setFecInicio(dia, mes, anio);
            } catch (Exception e) {
                System.out.println(Color.rojo(Color.negrita(e.getMessage())));
                repetir = true;
            }
        } while (repetir);

    }

    public static void fechaVencimiento(Beneficio beneficio){
        boolean repetir = false;
        do {
            repetir = false;
            try {
                System.out.println(Color.amarillo(Color.negrita(" Fecha de vencimiento")));
                int dia = Leer.entero(Color.cian(Color.negrita(" > Dia: ")));
                int mes = Leer.entero(Color.cian(Color.negrita(" > Mes: ")));
                int anio = Leer.entero(Color.cian(Color.negrita(" > Año: ")));
                beneficio.setFecVen(dia, mes, anio);
            } catch (Exception e) {
                System.out.println(Color.rojo(Color.negrita(e.getMessage())));
                repetir = true;
            }
        } while (repetir);

    }
    public static void puntos(Beneficio beneficio){
        boolean repetir = false;
        do {
            repetir = false;
            try {
                beneficio.setPorcPuntos(Leer.entero(Color.cian(Color.negrita(" > Porcentaje de puntos: "))));

            } catch (Exception e) {
                System.out.println(Color.rojo(Color.negrita(e.getMessage())));
                repetir = true;
            }
        } while (repetir);
    }
    public static void cashback(Beneficio beneficio){
        boolean repetir = false;
        do {
            repetir = false;
            try {
                beneficio.setPorcCashBack(Leer.entero(Color.cian(Color.negrita(" > Porcentaje de CashBack: "))));


            } catch (Exception e) {
                System.out.println(Color.rojo(Color.negrita(e.getMessage())));
                repetir = true;
            }
        } while (repetir);
    }
    public static void descuento(Beneficio beneficio){
        boolean repetir = false;
        do {
            repetir = false;
            try {
                beneficio.setPorcDescuento(Leer.entero(Color.cian(Color.negrita(" > Porcentaje de Descuento: "))));
            } catch (Exception e) {
                System.out.println(Color.rojo(Color.negrita(e.getMessage())));
                repetir = true;
            }
        } while (repetir);
    }

}

// ------------ MODULO DE MOVIMIENTO ---------
class SubmodMovimiento extends Menu {

    public SubmodMovimiento() {
        super("Movimiento", "Movimientos");
    }

    public static void desplegarMenu() {
        SubmodMovimiento menuMovimiento = new SubmodMovimiento();
        menuMovimiento.menuVerTodos();
    }

    @Override
    public void tabla() {
        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Estado"),
                Color.amarillo("Fecha"), Color.amarillo("Usuario"), Color.amarillo("Tipo Movimiento"));

        Movimiento[] movimientos = Movimiento.importarMovimientos();

        for (Movimiento movimiento : movimientos) {
            tabla.agregarFila(
                    movimiento.getId_movimiento(),
                    movimiento.getEstado(),
                    movimiento.getFechaMov(),
                    movimiento.usuario.getNomUsuario(),
                    movimiento.tipo.getNombre());
        }

        tabla.imprimirTablaSimple();
    }

    @Override
    public boolean tabla(int id) {
        if (!Movimiento.validarMovimiento(id)) {
            return false;
        }

        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Comentario"), Color.amarillo("Estado"),
                Color.amarillo("Fecha"), Color.amarillo("Usuario"), Color.amarillo("Tipo Movimiento"));

        Movimiento movimiento = Movimiento.importarMovimientos(id);

        tabla.agregarFila(
                movimiento.getId_movimiento(),
                movimiento.getComentario(),
                movimiento.getEstado(),
                movimiento.getFechaMov(),
                movimiento.usuario.getNomUsuario(),
                movimiento.tipo.getNombre());

        tabla.imprimirTablaSimple();
        return true;
    }

    @Override
    public boolean registrar() {

        return false;
    }

    @Override
    public boolean actualizar(int id) {
 
        
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return false;
    }
}
