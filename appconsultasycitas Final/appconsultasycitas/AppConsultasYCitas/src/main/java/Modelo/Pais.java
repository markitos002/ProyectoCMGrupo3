/*
* Pais.java
*
* Creado el 09 de octubre de 2021, 09:29
*/

package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class Pais implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idPais;
    private String nombre;
    private String registradoPor;
    private String fechaCambio;

    private String error;

    /** Crea una nueva instancia de Pais */
    public Pais() {
    }

    /**
     * Getters y Setters de la clase ".
     */
    
    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(String registradoPor) {
        this.registradoPor = registradoPor;
    }

    public String getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(String fechaCambio) {
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
* 09-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/

