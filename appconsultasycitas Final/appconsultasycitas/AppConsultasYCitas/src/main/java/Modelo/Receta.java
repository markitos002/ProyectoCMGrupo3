/*
* Receta.java
*
* Creado el 24 de septiembre de 2021, 08:34
*/

package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class Receta implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idReceta;
    private String cantidadMedicamento;
    private String medicamento;
    private String posologia;
    private String viaAdministracion;
    private String observacion;
    private String registradoPor;
    private Date fechaCambio;
    private int idConsulta;

    private String error;

    /** Crea una nueva instancia de Receta */
    public Receta() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdReceta(){
        return this.idReceta;
    }


    public void setIdReceta(int idReceta){
        this.idReceta = idReceta;
    }

    public String getCantidadMedicamento(){
        return this.cantidadMedicamento;
    }


    public void setCantidadMedicamento(String cantidadMedicamento){
        this.cantidadMedicamento = cantidadMedicamento;
    }

    public String getMedicamento(){
        return this.medicamento;
    }


    public void setMedicamento(String medicamento){
        this.medicamento = medicamento;
    }

    public String getPosologia(){
        return this.posologia;
    }


    public void setPosologia(String posologia){
        this.posologia = posologia;
    }

    public String getViaAdministracion(){
        return this.viaAdministracion;
    }


    public void setViaAdministracion(String viaAdministracion){
        this.viaAdministracion = viaAdministracion;
    }

    public String getObservacion(){
        return this.observacion;
    }


    public void setObservacion(String observacion){
        this.observacion = observacion;
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


    public int getIdConsulta(){
        return this.idConsulta;
    }


    public void setIdConsulta(int idConsulta){
        this.idConsulta = idConsulta;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}


/*
* 24-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/



