/*
* RolPersona.java
*
* Creado el 01 de octubre de 2021, 21:14
*/

package Modelo;
import java.util.Date;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Date;

/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class RolPersona{

    /**
     * Atributos de la clase ".
     */
    private int idRolPersona;
    private int idRol;
    private int idPers;
    private String rol;
    private String nombre;
    private String registradoPor;
    private Date fechaCambio;

    private String error;

    /** Crea una nueva instancia de RolPersona */
    public RolPersona() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdRolPersona(){
        return this.idRolPersona;
    }


    public void setIdRolPersona(int idRolPersona){
        this.idRolPersona = idRolPersona;
    }

    public int getIdRol(){
        return this.idRol;
    }


    public void setIdRol(int idRol){
        this.idRol = idRol;
    }

    public int getIdPers(){
        return this.idPers;
    }


    public void setIdPers(int idPers){
        this.idPers = idPers;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}


/*
* 01-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/ 