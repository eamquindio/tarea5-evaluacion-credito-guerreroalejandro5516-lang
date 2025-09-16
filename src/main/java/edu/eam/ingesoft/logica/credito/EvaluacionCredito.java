package edu.eam.ingesoft.logica.credito;

public class EvaluacionCredito {

    private String nombreSolicitante;
    private double ingresosMensuales;
    private int numeroCreditosActivos;
    private int puntajeCredito;
    private double valorCreditoSolicitado;
    private boolean tieneCodedor;

    public EvaluacionCredito(String nombreSolicitante, double ingresosMensuales,
                             int numeroCreditosActivos, int puntajeCredito,
                             double valorCreditoSolicitado, boolean tieneCodedor) {
        this.nombreSolicitante = nombreSolicitante;
        this.ingresosMensuales = ingresosMensuales;
        this.numeroCreditosActivos = numeroCreditosActivos;
        this.puntajeCredito = puntajeCredito;
        this.valorCreditoSolicitado = valorCreditoSolicitado;
        this.tieneCodedor = tieneCodedor;
    }

    // Tasa mensual en porcentaje
    public double calcularTasaMensual(double tasaNominalAnual) {
        return tasaNominalAnual / 12.0;
    }

    // Cuota con intereses (fórmula francesa)
    public double calcularCuotaMensual(double tasaNominalAnual, int plazoMeses) {
        double tasaMensualPorcentaje = calcularTasaMensual(tasaNominalAnual);
        double im = tasaMensualPorcentaje / 100.0; // convertir a decimal

        if (im == 0) {
            return calcularCuotaMensaualSinInteres(tasaNominalAnual, plazoMeses);
        }

        return (valorCreditoSolicitado * (im * Math.pow(1 + im, plazoMeses))) /
                (Math.pow(1 + im, plazoMeses) - 1);
    }

    // Cuota sin intereses
    public double calcularCuotaMensaualSinInteres(double tasaNominalAnual, int plazoMeses) {
        return valorCreditoSolicitado / plazoMeses;
    }

    // Evaluación de aprobación
    public boolean evaluarAprobacion(double tasaNominalAnual, int plazoMeses) {
        double cuotaMensual = calcularCuotaMensual(tasaNominalAnual, plazoMeses);

        if (puntajeCredito < 500) {
            return false;
        }

        if (puntajeCredito >= 500 && puntajeCredito <= 700) {
            if (!tieneCodedor) {
                return false;
            }
            return cuotaMensual <= ingresosMensuales * 0.25;
        }

        if (puntajeCredito > 700) {
            if (numeroCreditosActivos >= 2) {
                return false;
            }
            return cuotaMensual <= ingresosMensuales * 0.30;
        }

        return false;
    }

    // Getters y setters
    public String getNombreSolicitante() { return nombreSolicitante; }
    public void setNombreSolicitante(String nombreSolicitante) { this.nombreSolicitante = nombreSolicitante; }
    public double getIngresosMensuales() { return ingresosMensuales; }
    public void setIngresosMensuales(double ingresosMensuales) { this.ingresosMensuales = ingresosMensuales; }
    public int getNumeroCreditosActivos() { return numeroCreditosActivos; }
    public void setNumeroCreditosActivos(int numeroCreditosActivos) { this.numeroCreditosActivos = numeroCreditosActivos; }
    public int getPuntajeCredito() { return puntajeCredito; }
    public void setPuntajeCredito(int puntajeCredito) { this.puntajeCredito = puntajeCredito; }
    public double getValorCreditoSolicitado() { return valorCreditoSolicitado; }
    public void setValorCreditoSolicitado(double valorCreditoSolicitado) { this.valorCreditoSolicitado = valorCreditoSolicitado; }
    public boolean isTieneCodedor() { return tieneCodedor; }
    public void setTieneCodedor(boolean tieneCodedor) { this.tieneCodedor = tieneCodedor; }
}
