/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionFuncionarios;

import java.util.Date;

/**
 *
 * @author marco
 */
public class funcionarios {
    
    private int cedula;
   private String lugarExp;
   private Date FechaExp;
   private String primerNombre;
   private String SegundoNombre; 
   private String primerApellido;
   private String segundoApellido;
   private String Sexo;
   private Date fechaNacimiento;
   private String lugarNacimiento;
   private String estadoCivil;
   private String ciudadResidencia;
   private String direccion;
   private String email;
   private int telefonoCelular;
   private int teléfono;
   private String Rh;
   private String tipoSangre;

    public funcionarios() {
    }

    public funcionarios(int cedula, String lugarExp, Date FechaExp, String primerNombre, String SegundoNombre, String primerApellido, String segundoApellido, String Sexo, Date fechaNacimiento, String lugarNacimiento, String estadoCivil, String ciudadResidencia, String direccion, String email, int telefonoCelular, int teléfono, String Rh, String tipoSangre) {
        this.cedula = cedula;
        this.lugarExp = lugarExp;
        this.FechaExp = FechaExp;
        this.primerNombre = primerNombre;
        this.SegundoNombre = SegundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.Sexo = Sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.estadoCivil = estadoCivil;
        this.ciudadResidencia = ciudadResidencia;
        this.direccion = direccion;
        this.email = email;
        this.telefonoCelular = telefonoCelular;
        this.teléfono = teléfono;
        this.Rh = Rh;
        this.tipoSangre = tipoSangre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getLugarExp() {
        return lugarExp;
    }

    public void setLugarExp(String lugarExp) {
        this.lugarExp = lugarExp;
    }

    public Date getFechaExp() {
        return FechaExp;
    }

    public void setFechaExp(Date FechaExp) {
        this.FechaExp = FechaExp;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return SegundoNombre;
    }

    public void setSegundoNombre(String SegundoNombre) {
        this.SegundoNombre = SegundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(int telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public int getTeléfono() {
        return teléfono;
    }

    public void setTeléfono(int teléfono) {
        this.teléfono = teléfono;
    }

    public String getRh() {
        return Rh;
    }

    public void setRh(String Rh) {
        this.Rh = Rh;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    @Override
    public String toString() {
        return "funcionarios{" + "cedula=" + cedula + ", lugarExp=" + lugarExp + ", FechaExp=" + FechaExp + ", primerNombre=" + primerNombre + ", SegundoNombre=" + SegundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", Sexo=" + Sexo + ", fechaNacimiento=" + fechaNacimiento + ", lugarNacimiento=" + lugarNacimiento + ", estadoCivil=" + estadoCivil + ", ciudadResidencia=" + ciudadResidencia + ", direccion=" + direccion + ", email=" + email + ", telefonoCelular=" + telefonoCelular + ", tel\u00e9fono=" + teléfono + ", Rh=" + Rh + ", tipoSangre=" + tipoSangre + '}';
    }
   
   
    
}
