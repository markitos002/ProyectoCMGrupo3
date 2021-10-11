/*
* ConsultaMedica.java
*
* Creado el 09 de octubre de 2021, 21:34
*/

package Modelo;
import java.util.Date;

import java.io.Serializable;


/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class ConsultaMedica implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idConsultaMedica;
    private String motivoConsulta;
    private String sintomatologia;
    private String tiempoEnfermedad;
    private String relatoCronologico;
    private String diagnostico;
    private String antecedentes;
    private String registradoPor;
    private Date fechaCambio;
    private String duracionTratamiento;
    private String observacionReceta;
    private int idCita;
    private int idPaciente;
    private String paciente;
    private int idMedico;
    private String medico;
    private int idMotivoConsulta;

    private String error;

    /** Crea una nueva instancia de ConsultaMedica */
    public ConsultaMedica() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdConsultaMedica(){
        return this.idConsultaMedica;
    }


    public void setIdConsultaMedica(int idConsultaMedica){
        this.idConsultaMedica = idConsultaMedica;
    }

    public String getMotivoConsulta(){
        return this.motivoConsulta;
    }


    public void setMotivoConsulta(String motivoConsulta){
        this.motivoConsulta = motivoConsulta;
    }

    public String getSintomatologia(){
        return this.sintomatologia;
    }


    public void setSintomatologia(String sintomatologia){
        this.sintomatologia = sintomatologia;
    }

    public String getTiempoEnfermedad(){
        return this.tiempoEnfermedad;
    }


    public void setTiempoEnfermedad(String tiempoEnfermedad){
        this.tiempoEnfermedad = tiempoEnfermedad;
    }

    public String getRelatoCronologico(){
        return this.relatoCronologico;
    }


    public void setRelatoCronologico(String relatoCronologico){
        this.relatoCronologico = relatoCronologico;
    }

    public String getDiagnostico(){
        return this.diagnostico;
    }


    public void setDiagnostico(String diagnostico){
        this.diagnostico = diagnostico;
    }

    public String getAntecedentes(){
        return this.antecedentes;
    }


    public void setAntecedentes(String antecedentes){
        this.antecedentes = antecedentes;
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

    public String getDuracionTratamiento(){
        return this.duracionTratamiento;
    }


    public void setDuracionTratamiento(String duracionTratamiento){
        this.duracionTratamiento = duracionTratamiento;
    }

    public String getObservacionReceta(){
        return this.observacionReceta;
    }


    public void setObservacionReceta(String observacionReceta){
        this.observacionReceta = observacionReceta;
    }

    public int getIdCita(){
        return this.idCita;
    }


    public void setIdCita(int idCita){
        this.idCita = idCita;
    }

    public int getIdPaciente(){
        return this.idPaciente;
    }


    public void setIdPaciente(int idPaciente){
        this.idPaciente = idPaciente;
    }

    public int getIdMedico(){
        return this.idMedico;
    }


    public void setIdMedico(int idMedico){
        this.idMedico = idMedico;
    }

    public int getIdMotivoConsulta(){
        return this.idMotivoConsulta;
    }


    public void setIdMotivoConsulta(int idMotivoConsulta){
        this.idMotivoConsulta = idMotivoConsulta;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }
    
    
}


/*
* 09-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/
