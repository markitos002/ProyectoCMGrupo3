/*
* Paciente.java
*
* Creado el 27 de septiembre de 2021, 21:28
*/

package Modelo;
import java.util.Date;
import java.io.Serializable;

/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class Paciente implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int pers_id;
    private String nombre;
    private String registradoPor;
    private Date fechaCambio;
    private String alergias;

    private String error;

    /** Crea una nueva instancia de Paciente */
    public Paciente() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getPers_id(){
        return this.pers_id;
    }


    public void setPers_id(int pers_id){
        this.pers_id = pers_id;
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

    public String getAlergias(){
        return this.alergias;
    }


    public void setAlergias(String alergias){
        this.alergias = alergias;
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
* 27-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/ 