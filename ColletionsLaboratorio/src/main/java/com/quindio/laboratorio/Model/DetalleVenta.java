package com.quindio.laboratorio.Model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DetalleVenta {

    private LocalDate fechaVenta;

    public DetalleVenta() {
        this.fechaVenta = LocalDate.now();
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }


}