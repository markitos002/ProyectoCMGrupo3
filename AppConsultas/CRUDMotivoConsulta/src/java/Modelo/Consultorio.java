/*
* Consultorio.java
*
* Creado el 29 de septiembre de 2021, 07:01
*/

package Modelo;
import java.util.Date;

import java.io.Serializable;


/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class Consultorio implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idConsultorio;
    private String nombre;
    private String direccion;
    private String telefono;
    private String registradopor;
    private Date fechacambio;
    private int idCiudad;
    private String ciudad;
    private String error;

    /** Crea una nueva instancia de Consultorio */
    public Consultorio() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdConsultorio(){
        return this.idConsultorio;
    }


    public void setIdConsultorio(int idConsultorio){
        this.idConsultorio = idConsultorio;
    }

    public String getNombre(){
        return this.nombre;
    }


    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDireccion(){
        return this.direccion;
    }


    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getTelefono(){
        return this.telefono;
    }


    public void setTelefono(String telefono){
        this.telefono = telefono;
    }

    public String getRegistradopor(){
        return this.registradopor;
    }


    public void setRegistradopor(String registradopor){
        this.registradopor = registradopor;
    }

    public Date getFechacambio(){
        return this.fechacambio;
    }


    public void setFechacambio(Date fechacambio){
        this.fechacambio = fechacambio;
    }


    public void setFechacambio2(Date fechacambio){
        this.fechacambio = fechacambio;
    }

    public int getIdCiudad(){
        return this.idCiudad;
    }


    public void setIdCiudad(int idCiudad){
        this.idCiudad = idCiudad;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}


/*
* 29-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/ 