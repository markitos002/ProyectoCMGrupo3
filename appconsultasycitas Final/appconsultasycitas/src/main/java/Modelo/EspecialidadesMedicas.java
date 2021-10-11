/*
* EspecialidadesMedicas.java
*
* Creado el 04 de octubre de 2021, 05:48
*/

package Modelo;
import java.util.Date;

import java.io.Serializable;

/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class EspecialidadesMedicas implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idEspecialidadesMedicas;
    private String nombre;
    private String registradoPor;
    private Date fechaCambio;

    private String error;

    /** Crea una nueva instancia de EspecialidadesMedicas */
    public EspecialidadesMedicas() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdEspecialidadesMedicas(){
        return this.idEspecialidadesMedicas;
    }


    public void setIdEspecialidadesMedicas(int idEspecialidadesMedicas){
        this.idEspecialidadesMedicas = idEspecialidadesMedicas;
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
}


/*
* 04-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/

