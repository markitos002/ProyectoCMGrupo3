/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionEspecialidades;

import java.util.Date;

/**
 *
 * @author marco
 */
public class EspecialidadMed {
    
    private String nombreEsp;
    private String tipoEsp;
    private Date agendaEsp;

    public EspecialidadMed() {
    }

    public EspecialidadMed(String nombreEsp, String tipoEsp, Date agendaEsp) {
        this.nombreEsp = nombreEsp;
        this.tipoEsp = tipoEsp;
        this.agendaEsp = agendaEsp;
    }

    public String getNombreEsp() {
        return nombreEsp;
    }

    public void setNombreEsp(String nombreEsp) {
        this.nombreEsp = nombreEsp;
    }

    public String getTipoEsp() {
        return tipoEsp;
    }

    public void setTipoEsp(String tipoEsp) {
        this.tipoEsp = tipoEsp;
    }

    public Date getAgendaEsp() {
        return agendaEsp;
    }

    public void setAgendaEsp(Date agendaEsp) {
        this.agendaEsp = agendaEsp;
    }

    @Override
    public String toString() {
        return "EspecialidadMed{" + "nombreEsp=" + nombreEsp + ", tipoEsp=" + tipoEsp + ", agendaEsp=" + agendaEsp + '}';
    }
    
    
}
