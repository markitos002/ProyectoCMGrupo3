/*
* HorarioCitasDia.java
*
* Creado el 26 de septiembre de 2021, 16:49
*/

package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class HorarioCitasDia implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idHorarioCitasDia;
    private String horaInicio;
    private String minutoInicio;
    private String horaFin;
    private String minutoFin;
    private String activo;
    private String registradoPor;
    private String fechaCambio;
    private String hora;
    private String inicio;
    private String fin;
    private int idConsultorio;    
    private String consultorio;
    private int idPers;  
    private String nombre;
    private String error;

    /** Crea una nueva instancia de HorarioCitasDia */
    public HorarioCitasDia() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdHorarioCitasDia(){
        return this.idHorarioCitasDia;
    }


    public void setIdHorarioCitasDia(int idHorarioCitasDia){
        this.idHorarioCitasDia = idHorarioCitasDia;
    }

    public String getHoraInicio(){
        return this.horaInicio;
    }


    public void setHoraInicio(String horaInicio){
        this.horaInicio = horaInicio;
    }

    public String getMinutoInicio(){
        return this.minutoInicio;
    }


    public void setMinutoInicio(String minutoInicio){
        this.minutoInicio = minutoInicio;
    }

    public String getHoraFin(){
        return this.horaFin;
    }


    public void setHoraFin(String horaFin){
        this.horaFin = horaFin;
    }

    public String getMinutoFin(){
        return this.minutoFin;
    }


    public void setMinutoFin(String minutoFin){
        this.minutoFin = minutoFin;
    }

    public String getActivo(){
        return this.activo;
    }


    public void setActivo(String activo){
        this.activo = activo;
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

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String getHora(){
        return this.hora;
    }


    public void setHora(String hora){
        this.hora = hora;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }


    public int getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(int idConsultorio) {
        this.idConsultorio = idConsultorio;
    }
    
    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public int getIdPers() {
        return idPers;
    }

    public void setIdPers(int idPers) {
        this.idPers = idPers;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}


/*
* 26-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/
