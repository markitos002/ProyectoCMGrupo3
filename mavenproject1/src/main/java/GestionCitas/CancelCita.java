/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCitas;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author marco
 */
public class CancelCita {
    
    private String medico;
    private String paciente;
    private Date fechActual;
    private Time horaActual;
    private Date fechaCancelada;
    private Time horaCancelada;

    public CancelCita() {
    }

    public CancelCita(String medico, String paciente, Date fechActual, Time horaActual, Date fechaCancelada, Time horaCancelada) {
        this.medico = medico;
        this.paciente = paciente;
        this.fechActual = fechActual;
        this.horaActual = horaActual;
        this.fechaCancelada = fechaCancelada;
        this.horaCancelada = horaCancelada;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public Date getFechActual() {
        return fechActual;
    }

    public void setFechActual(Date fechActual) {
        this.fechActual = fechActual;
    }

    public Time getHoraActual() {
        return horaActual;
    }

    public void setHoraActual(Time horaActual) {
        this.horaActual = horaActual;
    }

    public Date getFechaCancelada() {
        return fechaCancelada;
    }

    public void setFechaCancelada(Date fechaCancelada) {
        this.fechaCancelada = fechaCancelada;
    }

    public Time getHoraCancelada() {
        return horaCancelada;
    }

    public void setHoraCancelada(Time horaCancelada) {
        this.horaCancelada = horaCancelada;
    }

    @Override
    public String toString() {
        return "CancelCita{" + "medico=" + medico + ", paciente=" + paciente + ", fechActual=" + fechActual + ", horaActual=" + horaActual + ", fechaCancelada=" + fechaCancelada + ", horaCancelada=" + horaCancelada + '}';
    }
    
    
    
}
