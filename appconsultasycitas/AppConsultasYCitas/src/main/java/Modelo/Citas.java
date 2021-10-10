/*
* Citas.java
*
* Creado el 07 de octubre de 2021, 14:43
*/

package Modelo;
import java.util.Date;

import java.io.Serializable;

/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class Citas implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idCitas;
    private int idPaciente;
    private int idHoraCitasDia;
    private int idMedico;
    private int idConsultorio;
    private int idTipoCita;
    private String observacion;
    private String registradoPor;
    private String fechaCambio;
    private String motivoDeConsulta;
    private String especialidad;
    private int idEspecialidad;

    private String paciente;
    private String hora;
    private String medico;
    private String lugar;
    private String tipoCita;    
    private String fecha;
    
    private String error;

    /** Crea una nueva instancia de Citas */
    public Citas() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdCitas(){
        return this.idCitas;
    }


    public void setIdCitas(int idCitas){
        this.idCitas = idCitas;
    }

    public int getIdPaciente(){
        return this.idPaciente;
    }


    public void setIdPaciente(int idPaciente){
        this.idPaciente = idPaciente;
    }

    public int getIdHoraCitasDia(){
        return this.idHoraCitasDia;
    }


    public void setIdHoraCitasDia(int idHoraCitasDia){
        this.idHoraCitasDia = idHoraCitasDia;
    }

    public int getIdMedico(){
        return this.idMedico;
    }


    public void setIdMedico(int idMedico){
        this.idMedico = idMedico;
    }

    public int getIdConsultorio(){
        return this.idConsultorio;
    }


    public void setIdConsultorio(int idConsultorio){
        this.idConsultorio = idConsultorio;
    }

    public int getIdTipoCita(){
        return this.idTipoCita;
    }


    public void setIdTipoCita(int idTipoCita){
        this.idTipoCita = idTipoCita;
    }

    public String getObservacion(){
        return this.observacion;
    }


    public void setObservacion(String observacion){
        this.observacion = observacion;
    }

    public String getRegistradoPor(){
        return this.registradoPor;
    }


    public void setRegistradoPor(String registradoPor){
        this.registradoPor = registradoPor;
    }

    public String getFechaCambio(){
        return this.fechaCambio;
    }


    public void setFechaCambio(String fechaCambio){
        this.fechaCambio = fechaCambio;
    }


    public void setFechaCambio2(String fechaCambio){
        this.fechaCambio = fechaCambio;
    }

    public String getFecha(){
        return this.fecha;
    }


    public void setFecha(String fecha){
        this.fecha = fecha;
    }


    public void setFecha2(String fecha){
        this.fecha = fecha;
    }

    public String getMotivoDeConsulta(){
        return this.motivoDeConsulta;
    }


    public void setMotivoDeConsulta(String motivoDeConsulta){
        this.motivoDeConsulta = motivoDeConsulta;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEespecialidad) {
        this.idEspecialidad = idEespecialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

}


/*
* 07-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/ 

