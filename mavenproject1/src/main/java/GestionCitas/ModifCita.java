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
public class ModifCita {
    
    private String medico;
    private String paciente;
    private Date fechAnterior;
    private Time horaAnterior;
    private Date fechaNueva;
    private Time horaNueva;

    public ModifCita() {
    }

    public ModifCita(String medico, String paciente, Date fechAnterior, Time horaAnterior, Date fechaNueva, Time horaNueva) {
        this.medico = medico;
        this.paciente = paciente;
        this.fechAnterior = fechAnterior;
        this.horaAnterior = horaAnterior;
        this.fechaNueva = fechaNueva;
        this.horaNueva = horaNueva;
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

    public Date getFechAnterior() {
        return fechAnterior;
    }

    public void setFechAnterior(Date fechAnterior) {
        this.fechAnterior = fechAnterior;
    }

    public Time getHoraAnterior() {
        return horaAnterior;
    }

    public void setHoraAnterior(Time horaAnterior) {
        this.horaAnterior = horaAnterior;
    }

    public Date getFechaNueva() {
        return fechaNueva;
    }

    public void setFechaNueva(Date fechaNueva) {
        this.fechaNueva = fechaNueva;
    }

    public Time getHoraNueva() {
        return horaNueva;
    }

    public void setHoraNueva(Time horaNueva) {
        this.horaNueva = horaNueva;
    }

    @Override
    public String toString() {
        return "ModifCita{" + "medico=" + medico + ", paciente=" + paciente + ", fechAnterior=" + fechAnterior + ", horaAnterior=" + horaAnterior + ", fechaNueva=" + fechaNueva + ", horaNueva=" + horaNueva + '}';
    }
    
    
    
}
