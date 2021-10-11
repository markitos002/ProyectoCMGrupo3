/*
* Departamento.java
*
* Creado el 10 de octubre de 2021, 08:08
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


public class Departamento{

    /**
     * Atributos de la clase ".
     */
    private int idDepartamento;
    private String nombre;
    private int idPais;
    private String pais;
    private String registradoPor;
    private String fechaCambio;

    private String error;

    /** Crea una nueva instancia de Departamento */
    public Departamento() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdDepartamento(){
        return this.idDepartamento;
    }


    public void setIdDepartamento(int idDepartamento){
        this.idDepartamento = idDepartamento;
    }

    public String getNombre(){
        return this.nombre;
    }


    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getIdPais(){
        return this.idPais;
    }


    public void setIdPais(int idPais){
        this.idPais = idPais;
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

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    
}


/*
* 10-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/
