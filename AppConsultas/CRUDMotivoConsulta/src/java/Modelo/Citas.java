/*
* Citas.java
*
* Creado el 25 de septiembre de 2021, 23:19
*/

package Modelo;
import java.io.Serializable;
import java.util.Date;


/**
*
* @autor GRUPO DE DESARROLLO 3 - MISION MINTIC
*/


public class Citas implements Serializable{

    /**
     * Atributos de la clase ".
     */
    private int idCitas;
    private int idPaciente;
    private String paciente;
    private int idHorarioCitas;
    private String hora;
    private int idMedico;
    private String medico;
    private int idConsultorio;
    private String lugar;
    private int idTipoCita;
    private String tipoCita;    
    private String observacion;
    private String registradoPor;
    private Date fechaCambio;
    private String fecha;
    private String motivoConsulta;
    private int idMotivoConsulta;
    private String motivoCons;

    private String error;

    /** Crea una nueva instancia de Citas */
    public Citas() {
    }

    /**
     * Getters y Setters de la clase ".
     */

    public int getIdCitas(){
        return this.idCitas;
    }


    public void setIdCitas(int idCitas){
        this.idCitas = idCitas;
    }

    public int getIdPaciente(){
        return this.idPaciente;
    }


    public void setIdPaciente(int idPaciente){
        this.idPaciente = idPaciente;
    }

    
    public String getPaciente(){
        return this.paciente;
    }

    public void setPaciente(String paciente){
        this.paciente = paciente;
    }

    
    public int getIdHorarioCitas(){
        return this.idHorarioCitas;
    }


    public void setIdHorarioCitas(int idHorarioCitas){
        this.idHorarioCitas = idHorarioCitas;
    }

    public String getHora(){
        return this.hora;
    }


    public void setHora(String hora){
        this.hora = hora;
    }
    
    public int getIdMedico(){
        return this.idMedico;
    }


    public void setIdMedico(int idMedico){
        this.idMedico = idMedico;
    }

    public String getMedico(){
        return this.medico;
    }


    public void setMedico(String medico){
        this.medico = medico;
    }
    
    public int getIdConsultorio(){
        return this.idConsultorio;
    }


    public void setIdConsultorio(int idConsultorio){
        this.idConsultorio = idConsultorio;
    }

        
    public String getLugar(){
        return this.lugar;
    }


    public void setLugar(String lugar){
        this.lugar = lugar;
    }
    
    public int getIdTipoCita(){
        return this.idTipoCita;
    }


    public void setIdTipoCita(int idTipoCita){
        this.idTipoCita = idTipoCita;
    }
    
    public String getTipoCita(){
        return this.tipoCita;
    }


    public void setTipoCita(String tipoCita){
        this.tipoCita = tipoCita;
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


    public void setFechaCambio2(Date fechaCambio){
        this.fechaCambio = fechaCambio;
    }

    public String getFecha(){
        return this.fecha;
    }


    public void setFecha(String fecha){
        this.fecha = fecha;
    }


    public void setFecha2(String fecha){
        this.fecha = fecha;
    }

    public String getMotivoConsulta(){
        return this.motivoConsulta;
    }


    public void setMotivoConsulta(String motivoConsulta){
        this.motivoConsulta = motivoConsulta;
    }

    public int getIdMotivoConsulta(){
        return this.idMotivoConsulta;
    }


    public void setIdMotivoConsulta(int idMotivoConsulta){
        this.idMotivoConsulta = idMotivoConsulta;
    }

    public String getMotivoCons(){
        return this.motivoCons;
    }


    public void setMotivoCons(String motivoCons){
        this.motivoCons = motivoCons;
    }
    
    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }
}


/*
* 25-SEPTIEMBRE-2021 @: GRUPO DE DESARROLLO 3 &: creación
*/
