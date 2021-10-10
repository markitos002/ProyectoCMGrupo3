/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Modelo.MotivoConsulta;
import java.util.List;

/**
 *
 * @author jacun
 */
public interface CRUD {
    
    public List listar();
    public List listarPagina(String parametro, int desde, int hasta );
    public Object list(int id);
    public Object consultar(String llave);
    public List listarConParametro(String parametro);
    //public List listarConParametroPagina(String parametro, int desde, int hasta);
    public String add(Object obj);
    public String edit(Object dat);
    public String elim(int id);
    public int calcularPaginas(String parametro);
    
    
}
