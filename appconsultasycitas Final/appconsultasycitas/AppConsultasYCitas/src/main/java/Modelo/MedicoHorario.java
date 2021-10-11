/*
* MedicoHorario.java
*
* Creado el 03 de octubre de 2021, 23:16
*/

package Modelo;
import java.util.Date;

import java.io.Serializable;


/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class MedicoHorario implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idMedicoHorario;
    private int idPers;
    private String medico; 
    private int hoat_idHorarioTrabajo;
    private String registradoPor;
    private Date fechaCambio;
    private int idConsultorio;
    private String consultorio;

    private String error;

    /** Crea una nueva instancia de MedicoHorario */
    public MedicoHorario() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdMedicoHorario(){
        return this.idMedicoHorario;
    }


    public void setIdMedicoHorario(int idMedicoHorario){
        this.idMedicoHorario = idMedicoHorario;
    }

    public int getIdPers(){
        return this.idPers;
    }


    public void setIdPers(int idPers){
        this.idPers = idPers;
    }

    public int getHoat_idHorarioTrabajo(){
        return this.hoat_idHorarioTrabajo;
    }


    public void setHoat_idHorarioTrabajo(int hoat_idHorarioTrabajo){
        this.hoat_idHorarioTrabajo = hoat_idHorarioTrabajo;
    }

    public String getRegistradoPor(){
        return this.registradoPor;
    }


    public void setRegistradoPor(String registradoPor){
        this.registradoPor = registradoPor;
    }

    public Date getFechaCambio(){
        return this.fechaCambio;
    }


    public void setFechaCambio(Date fechaCambio){
        this.fechaCambio = fechaCambio;
    }


    public void setFechaCambio2(Date fechaCambio){
        this.fechaCambio = fechaCambio;
    }

    public int getIdConsultorio(){
        return this.idConsultorio;
    }


    public void setIdConsultorio(int idConsultorio){
        this.idConsultorio = idConsultorio;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }
    
    
}


/*
* 03-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/
