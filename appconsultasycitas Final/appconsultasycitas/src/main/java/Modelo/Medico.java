/*
* Medico.java
*
* Creado el 28 de septiembre de 2021, 23:10
*/

package Modelo;
import java.util.Date;

import java.io.Serializable;


/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class Medico implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idPers;
    private String nroRegistro;
    private String activo;
    private String nombre;
    private String registradoPor;
    private Date fechaCambio;

    private String error;

    /** Crea una nueva instancia de Medico */
    public Medico() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdPers(){
        return this.idPers;
    }


    public void setIdPers(int idPers){
        this.idPers = idPers;
    }

    public String getNroRegistro(){
        return this.nroRegistro;
    }


    public void setNroRegistro(String nroRegistro){
        this.nroRegistro = nroRegistro;
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

    public Date getFechaCambio(){
        return this.fechaCambio;
    }


    public void setFechaCambio(Date fechaCambio){
        this.fechaCambio = fechaCambio;
    }


    public void setFechaCambio2(Date fechaCambio){
        this.fechaCambio = fechaCambio;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


/*
* 28-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/

