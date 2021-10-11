/*
* Usuario.java
*
* Creado el 01 de octubre de 2021, 07:17
*/

package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class Usuario implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idUsuario;
    private int idRope;
    private String usuario;
    private String nombre;
    private String mail;
    private String contrasena;
    private String registradoPor;
    private String fechaCambio;
    private String identificacion;
    private int idRol;
    private String paginaInicio;
    private String rol;
    private String error;

    /** Crea una nueva instancia de Usuario */
    public Usuario() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdUsuario(){
        return this.idUsuario;
    }


    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }

    public int getIdRope(){
        return this.idRope;
    }


    public void setIdRope(int idRope){
        this.idRope = idRope;
    }

    public String getUsuario(){
        return this.usuario;
    }


    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getContrasena(){
        return this.contrasena;
    }


    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getPaginaInicio() {
        return paginaInicio;
    }

    public void setPaginaInicio(String paginaInicio) {
        this.paginaInicio = paginaInicio;
    }
 
}


/*
* 01-OCTUBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/

