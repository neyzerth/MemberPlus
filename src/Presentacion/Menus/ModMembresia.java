package Presentacion.Menus;

import Logica.Sesion;
import Logica.Objetos.Beneficio;
import Logica.Objetos.Movimiento;
import Logica.Objetos.Nivel;
import Logica.Objetos.Tarjeta;
import Logica.Objetos.TipoMovimiento;
import Presentacion.Despliegue.*;
import Presentacion.Formato.*;

//------------ MODULO PRINCIPAL ---------
public class ModMembresia {
    public static void menu() {
        boolean salir = false;

        while (!salir) {
            Texto.limpiarPantalla();
            System.out.println(Color.morado(Color.negrita(Texto.espacio(8) + "> Módulo de Tarjeta <")));

            Cuadro tarjeta = new Cuadro(
                    Color.morado("Administrar tarjetas"),
                    Color.morado("Administrar Niveles de membresias"),
                    Color.morado("Administrar beneficios"),
                    Color.morado("Administrar Movimientos"),
                    Color.rojo("Volver al menú principal"));

            tarjeta.imprimirCuadroNum();

            System.out.println();

            int option = Leer.entero(Color.cian(Texto.espacio(1) + "> Seleccione una opción: "));

            switch (option) {
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
                    SubmodMovimiento.desplegarMenu();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println(Color.rojo("Opción inválida, por favor intente de nuevo."));
            }
        }

    }
}

// ------------ MODULO DE TARJETA ---------
class SubmodTarjeta extends Menu {

    public SubmodTarjeta() {
        super("Tarjeta", "Tarjetas");
        this.opciones = new Cuadro(
                Color.morado("Lista de " + modPlur),
                Color.morado("Información de " + modSing),
                Color.morado("Renovar " + modSing),
                Color.morado("Eliminar " + modSing),
                Color.rojo("Volver"));
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
                menuEliminar();
                break;

            case 5:
                return true;

            default:
                System.out.println();
                System.out.println(Color.rojo(" Opción inválida, por favor intente de nuevo."));
                Texto.esperar(1);
                break;
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
        String numTarjeta = Leer.cadena(Color.cian(" > Numero de " + modSing + " a ver: "));
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
        String numTarjeta = Leer.cadena(Color.cian(" > Numero de " + modSing + " a modificar: "));

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
            System.out.println(" ¿Que desea realizar?");
            System.out.println("Renovar $" + tarjeta.nivel.getAnualidad() + "[1] - Cambiar nivel [2] - Salir [3]");
            int opc = Leer.entero("> ");
            switch (opc) {
                case 1:

                    System.out.println("Fecha de vencimiento: " + tarjeta.getFecVen());
                    tarjeta.renovar();
                    System.out.println("Nueva fecha de vencimiento: " + tarjeta.getFecVen());
                    tarjeta.actualizarTarjeta();
                    System.out.println(Color.cian("Tarjeta renovada con exito..."));
                    Movimiento.registrarMovimiento("Renovacion de tarjeta", tarjeta, 2);// Checar lo de el id
                    Texto.esperarEnter();

                    break;
                case 2:
                    System.out.println("Nivel actual: " + tarjeta.nivel.getNombre());
                    SubmodNivel modNivel = new SubmodNivel();
                    modNivel.tabla();
                    int idNivel = Leer.entero("> ID del nuevo nivel: ");
                    Nivel nivel = Nivel.importarNiveles(idNivel);
                    tarjeta.nivel = nivel;
                    if (tarjeta.actualizarTarjeta()) {
                        System.out.println(Color.verde("Nuevo nivel: " + tarjeta.nivel.getNombre()));
                        Movimiento.registrarMovimiento("Cambiar nivel", tarjeta, 2);// Checar lo de el id
                    } else {
                        System.out.println(Color.rojo("Error al cambiar nivel "));
                    }

                    break;
                case 3:
                    salir = true;
                    break;

                default:
                    Texto.esperarEnter("Opcion no valida");
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
            String numTarjeta = Leer.cadena(Color.cian(" > Numero de " + modSing + " a eliminar: "));

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
                Movimiento.registrarMovimiento("Eliminacion de membresia", tarjeta, 3);
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
        return Tarjeta.eliminarTarjeta(numTarjeta);
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
        Nivel nivel = pedirDatos();

        if (nivel == null)
            return false;

        return nivel.insertarNivel();
    }

    @Override
    public boolean actualizar(int id) {
        Nivel nivel = pedirDatos();
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
        Nivel nivel = Nivel.importarNiveles(id);
        if (!nivel.validarNivel())
            return false;

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

    public Nivel pedirDatos() {
        Nivel nivel = new Nivel();
        try {
            nivel.setNombre(Leer.cadena(Color.cian(Color.negrita(" > Nombre del nivel: "))));
            nivel.setCostoApertura(Leer.entero(Color.cian(Color.negrita(" > Costo de apertura: $"))));
            nivel.setAnualidad(Leer.cadena(Color.cian(Color.negrita(" > Costo de la anualidad: $"))));
            System.out.println();
            SubmodBeneficio verBeneficios = new SubmodBeneficio();

            boolean agregarNivel = false;
            verBeneficios.tabla();
            do {
                System.out.println();
                int idBeneficio = Leer.entero(Color.cian(" > Selecciona ID del beneficio: "));
                if (Beneficio.validarBeneficio(idBeneficio)) {
                    try {
                        nivel.agregarBeneficio(idBeneficio);
                        System.out.println();
                        System.out.println(Color.amarillo(" Desea agregar otro beneficio?"));
                        agregarNivel = 1 == Leer.entero(Color.amarillo(Color.negrita(" > SI[1] NO[2]: ")));

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } while (agregarNivel);

            return nivel;
        } catch (Exception e) {
            Texto.esperarEnter(Color.rojo(Color.negrita(" Dato no valido")));
            return null;
        }
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
        try {
            beneficio = pedirDatos();

            if (beneficio != null) {
                beneficio.insetarBeneficio();
                tabla(beneficio.getIdBeneficio());
                return true;
            }

        } catch (Exception e) {
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita("Dato incorrecto")));
        }

        return false;
    }

    @Override
    public boolean actualizar(int id) {
        Beneficio beneficio = Beneficio.importarBeneficios(id);
        if (beneficio == null) {
            System.out.println();
            Texto.esperarEnter(Color.rojo("Beneficio no encontrado"));
            return false;
        }

        try {
            beneficio = pedirDatos();

            if (beneficio != null) {
                tabla(id);
                return beneficio.actualizarBeneficio();
            }

        } catch (Exception e) {
            Texto.esperarEnter("Dato incorrecto");
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return Beneficio.eliminarBeneficio(id);
    }

    public Beneficio pedirDatos() {
        Beneficio beneficio = new Beneficio();
        int dia, mes, anio;
        try {
            System.out.println();
            beneficio.setNombre(Leer.cadena(Color.cian(Color.negrita(" > Nombre del beneficio: "))));
            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Fecha de inicio")));
            dia = Leer.entero(Color.cian(Color.negrita(" > Dia: ")));
            mes = Leer.entero(Color.cian(Color.negrita(" > Mes: ")));
            anio = Leer.entero(Color.cian(Color.negrita(" > Año: ")));
            beneficio.setFecInicio(dia, mes, anio);

            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Fecha de vencimiento")));
            dia = Leer.entero(Color.cian(Color.negrita(" > Dia: ")));
            mes = Leer.entero(Color.cian(Color.negrita(" > Mes: ")));
            anio = Leer.entero(Color.cian(Color.negrita(" > Año: ")));
            beneficio.setFecVen(dia, mes, anio);

            System.out.println();
            System.out.println(Color.amarillo(Color.negrita(" Beneficios")));
            beneficio.setPorcPuntos(Leer.entero(Color.cian(Color.negrita(" > Porcentaje de puntos: "))));
            beneficio.setPorcCashBack(Leer.entero(Color.cian(Color.negrita(" > Porcentaje de CashBack: "))));
            beneficio.setPorcDescuento(Leer.entero(Color.cian(Color.negrita(" > Porcentaje de Descuento: "))));

        } catch (Exception e) {
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita("Dato incorrecto")));
            return null;
        }

        return beneficio;

    }
}

// Submodulo tipo de movimiento
class SubmodTipoMovimiento extends Menu {

    public SubmodTipoMovimiento() {
        super("Tipo Movimiento", "Tipos de Movimiento");
    }

    public static void desplegarMenu() {
        SubmodTipoMovimiento menuTipoMovimiento = new SubmodTipoMovimiento();
        menuTipoMovimiento.menu();
    }

    @Override
    public void tabla() {
        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre"));

        TipoMovimiento[] tipoMovimientos = TipoMovimiento.importarTipoMovimientos();

        for (TipoMovimiento tipoMovimiento : tipoMovimientos) {
            tabla.agregarFila(
                    tipoMovimiento.getIdTipoMovimiento(),
                    tipoMovimiento.getNombre());
        }

        tabla.imprimirTablaSimple();
    }

    @Override
    public boolean tabla(int id) {
        if (!TipoMovimiento.importarTipoMovimientos(id).validarTipoMovimiento())
            return false;

        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Nombre"), Color.amarillo("Descripción"));

        TipoMovimiento tipoMovimiento = TipoMovimiento.importarTipoMovimientos(id);

        tabla.agregarFila(
                tipoMovimiento.getIdTipoMovimiento(),
                tipoMovimiento.getNombre(),
                tipoMovimiento.getDescripcion());

        tabla.imprimirTablaSimple();
        return true;
    }

    @Override
    public boolean registrar() {
        TipoMovimiento tipoMovimiento = pedirDatos();

        if (tipoMovimiento == null) {
            return false;
        }

        return tipoMovimiento.insertarTipoMovimiento();
    }

    @Override
    public boolean actualizar(int id) {
        TipoMovimiento tipoMovimiento = pedirDatos();

        if (tipoMovimiento == null) {
            return false;
        }

        tipoMovimiento.setIdTipoMovimiento(id);
        return tipoMovimiento.actualizarTipoMovimiento();
    }

    @Override
    public boolean eliminar(int id) {
        return TipoMovimiento.eliminarTipoMovimiento(id);
    }

    public TipoMovimiento pedirDatos() {
        TipoMovimiento tipoMovimiento = new TipoMovimiento(0, "", "");
        try {
            System.out.println();
            tipoMovimiento.setNombre(Leer.cadena(Color.cian(Color.negrita(" > Nombre del tipo de movimiento: "))));
            System.out.println();
            tipoMovimiento
                    .setDescripcion(Leer.cadena(Color.cian(Color.negrita(" > Descripción del tipo de movimiento: "))));

        } catch (Exception e) {
            System.out.println();
            Texto.esperarEnter(Color.rojo(Color.negrita("Dato incorrecto")));
            return null;
        }

        return tipoMovimiento;
    }
}

class SubmodMovimiento extends Menu {

    public SubmodMovimiento() {
        super("Movimiento", "Movimientos");
    }

    public static void desplegarMenu() {
        SubmodMovimiento menuMovimiento = new SubmodMovimiento();
        menuMovimiento.menu();
    }

    @Override
    public void tabla() {
        tabla = new Tabla(Color.amarillo("ID"), Color.amarillo("Comentario"), Color.amarillo("Estado"),
                Color.amarillo("Fecha"), Color.amarillo("Usuario"), Color.amarillo("Tipo Movimiento"));

        Movimiento[] movimientos = Movimiento.importarMovimientos();

        for (Movimiento movimiento : movimientos) {
            tabla.agregarFila(
                    movimiento.getId_movimiento(),
                    movimiento.getComentario(),
                    movimiento.getEstado(),
                    movimiento.getFechaMov());
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
                movimiento.getFechaMov());

        tabla.imprimirTablaSimple();
        return true;
    }

    @Override
    public boolean registrar() {
        // Movimiento movimiento = pedirDatos();

        // if (movimiento == null) {
        // return false;
        // }

        // return movimiento.insertarMovimiento();
        return false;
    }

    @Override
    public boolean actualizar(int id) {
        // Movimiento movimiento = pedirDatos();

        // if (movimiento == null) {
        // return false;
        // }

        // movimiento.setId_movimiento(id);
        // return movimiento.actualizarMovimiento(id);
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        // return Movimiento.eliminarMovimiento(id);
        return false;
    }
}
