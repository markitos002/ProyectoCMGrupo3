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
public class DatosCita {
    
    private String medico;
    private String paciente;
    private Date fecha;
    private Time hora;

    public DatosCita() {
    }

    public DatosCita(String medico, String paciente, Date fecha, Time hora) {
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "DatosCita{" + "medico=" + medico + ", paciente=" + paciente + ", fecha=" + fecha + ", hora=" + hora + '}';
    }
    
}
