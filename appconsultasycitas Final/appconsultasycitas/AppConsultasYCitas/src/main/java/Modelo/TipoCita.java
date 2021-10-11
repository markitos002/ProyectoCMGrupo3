/*
* TipoCita.java
*
* Creado el 26 de septiembre de 2021, 21:30
*/

package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class TipoCita implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idTipoCita;
    private String nombre;
    private String registradoPor;
    private String fechaCambio;

    private String error;

    /** Crea una nueva instancia de TipoCita */
    public TipoCita() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdTipoCita(){
        return this.idTipoCita;
    }


    public void setIdTipoCita(int idTipoCita){
        this.idTipoCita = idTipoCita;
    }

    public String getNombre(){
        return this.nombre;
    }


    public void setNombre(String nombre){
        this.nombre = nombre;
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
}


/*
* 26-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/ 