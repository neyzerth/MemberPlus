package Logica.Objetos;

import Logica.FormatoFecha;
import Persistencia.Tablas.CompraEnt;
import Persistencia.Tablas.Compra_BeneficioEnt;

import java.sql.Date;

public class Compra {
    // ATRIBUTOS
    private int idCompra, puntos;
    private Date fechaCompra;
    private float total, subtotal, cashback, descuento;
    public Tarjeta tarjeta;
    public Beneficio[] beneficios;

    // CONSTRUCTORES
    public Compra(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
        this.beneficios = this.tarjeta.nivel.beneficios;
    }

    public Compra(String numTarjeta) {
        // Manda a llamar el constructor con parametro Tarjeta
        this(Tarjeta.importarTarjeta(numTarjeta));
    }

    public Compra(int idCompra, Date fechaCompra, int puntos,
            float descuento, float cashback, Tarjeta tarjeta,
            float subtotal, float total, Beneficio[] beneficios) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.puntos = puntos;
        this.descuento = descuento;
        this.cashback = cashback;
        this.tarjeta = tarjeta;
        this.subtotal = subtotal;
        this.total = total;
        this.beneficios = beneficios;
    }

    // COMUNICACION CON PERSISTENCIA
    public static Compra importarCompras(Object[] datos) {

        Compra compra = new Compra(
                (int) datos[0],
                (Date) datos[1],
                (int) datos[2],
                (float) datos[3],
                (float) datos[4],
                (Tarjeta) datos[5],
                (float) datos[6],
                (float) datos[7],
                (Beneficio[]) datos[8]);
        return compra;
    }

    public static Compra importarCompras(int id) {
        CompraEnt compraBd = new CompraEnt();
        Object[] datos = compraBd.obtenerCompraPorIdDB(id);

        return importarCompras(datos);
    }

    public static Compra[] importCompras() {
        CompraEnt compraBd = new CompraEnt();
        Compra[] compras = new Compra[compraBd.obtenerCantRegistros()];
        Object[][] datos = compraBd.ejecutarSelect();
        for (int i = 0; i < datos.length; i++) {
            compras[i] = importarCompras(datos[i]);
        }
        return compras;
    }

    // CRUD COMPRA
    public boolean insertarCompras() {
        CompraEnt compra = new CompraEnt();
        this.tarjeta.actualizarTarjeta();
        int idCompra = compra.insertarCompraDB(fechaCompra, puntos, descuento, cashback, tarjeta.getIdTarjeta(),
                subtotal, total);
        if (idCompra > 0) {
            this.idCompra = idCompra;
            Compra_BeneficioEnt comBen = new Compra_BeneficioEnt();
            for (Beneficio ben : beneficios) {
                comBen.insertarCompra_BeneficioDB(this.idCompra, ben.getIdBeneficio());
            }
        }
        return idCompra > 0;
    }

    public boolean actualizarCompra() {
        CompraEnt compra = new CompraEnt();
        return compra.actualizarCompraDB(idCompra, fechaCompra, puntos, descuento, cashback, tarjeta.getIdTarjeta(),
                subtotal, total);
    }

    public boolean validarCompra() {
        CompraEnt compra = new CompraEnt();
        return compra.existeCompra(fechaCompra, tarjeta.getNumTarjeta());
    }

    public static boolean eliminarCompra(int idCompra) {
        CompraEnt compra = new CompraEnt();
        return compra.eliminarCompraDB(idCompra);
    }

    // METODOS
    public void empezarVenta(float total) {
        setFechaCompra(FormatoFecha.fechaActual());
        setTotal(total);
        setSubtotal(total);
        setBeneficios(tarjeta.nivel.beneficios);
        calcularBeneficios();
    }

    private void calcularBeneficios() {
        float porcPuntos = 0;
        float porcCashback = 0;
        float porcDescuento = 0;

        for (Beneficio beneficio : this.beneficios) {
            porcPuntos = Math.max(beneficio.getPorcPuntos(), porcPuntos);
            porcCashback = Math.max(beneficio.getPorcCashBack(), porcCashback);
            porcDescuento = Math.max(beneficio.getPorcDescuento(), porcDescuento);
        }
        setPuntos((int) (porcPuntos / 100 * total));
        tarjeta.sumarPuntos(this.puntos);

        setCashback(porcCashback / 100 * total);
        tarjeta.sumarSaldo(this.cashback);

        setDescuento(porcDescuento / 100 * total);
        setSubtotal(this.total - this.descuento);
    }

    public boolean tuvoBeneficios() {
        return puntos > 0 || cashback > 0 || descuento > 0;
    }

    public float usarBeneficios(boolean puntos, boolean saldo) {
        float saldoRestante = 0;
        if (puntos) {
            subtotal -= (int) (tarjeta.getPuntosConvertidos());
            tarjeta.usarPuntos();
        }
        if (saldo) {
            float saldoDisponible = tarjeta.getSaldo();
            if (subtotal >= saldoDisponible) {
                subtotal -= saldoDisponible;
                tarjeta.usarSaldo();
            } else {
                saldoRestante = saldoDisponible - subtotal;
                tarjeta.setSaldo(saldoRestante);
                subtotal = 0;

            }
        }
        return saldoRestante;

    }

    // GETTERS AND SETTERS

    public int getIdCompra() {
        if (this.idCompra > 0)
            return this.idCompra;

        CompraEnt compraBd = new CompraEnt();
        Object[] datos = compraBd.ejecutarSelectPorAtributos(
                this.fechaCompra, this.puntos, this.descuento, this.cashback,
                this.tarjeta.getIdTarjeta(), this.subtotal, this.total);

        Compra compra = Compra.importarCompras(datos);

        this.idCompra = compra.getIdCompra();
        return this.idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public void setPuntos(int porcentajePunto) {
        if (porcentajePunto < 0)
            throw new IllegalArgumentException("El porcentaje no puede ser inferioir a 0");
        this.puntos = porcentajePunto;
    }

    public void setPorcentajePunto(String porcentajePuntoStr) {
        try {
            int porcentajePunto = Integer.parseInt(porcentajePuntoStr);
            this.setPuntos(porcentajePunto);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El porcentaje no es válido");
        }
    }

    public float getDescuento() {
        return this.descuento;
    }

    public void setDescuento(float descuento) {
        if (descuento < 0)
            throw new IllegalArgumentException("El descuento no puede ser inferioir a 0");
        this.descuento = descuento;
    }

    public Date getFechaCompra() {
        return this.fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        if (fechaCompra == null)
            throw new IllegalArgumentException("La fecha de compra no puede estar vacía");
        this.fechaCompra = fechaCompra;
    }

    public void setFechaCompra(String fechaCompraStr) {
        this.fechaCompra = FormatoFecha.fecha(fechaCompraStr);
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float total) {
        if (total <= 0)
            throw new IllegalArgumentException("El total debe ser mayor 0");
        this.total = total;
    }

    public void setTotal(String totalStr) {
        try {
            int total = Integer.parseInt(totalStr);
            this.setTotal(total);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El total no es válido");
        }
    }

    public float getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getCashback() {
        return this.cashback;
    }

    public void setCashback(float cashback) {
        this.cashback = cashback;
    }

    public Tarjeta getTarjeta() {
        return this.tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Beneficio[] getBeneficios() {
        return this.beneficios;
    }

    public void setBeneficios(Beneficio[] beneficios) {
        this.beneficios = beneficios;
    }

}