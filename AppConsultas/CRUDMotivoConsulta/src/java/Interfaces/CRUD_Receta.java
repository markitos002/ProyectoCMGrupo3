/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Modelo.Receta;
import java.util.List;

/**
 *
 * @author jacun
 */
public interface CRUD_Receta {
    
    public List listar();
    public List listarPagina(String parametro, int desde, int hasta );
    public Object list(int id);
    public List listarConParametro(String parametro);
    //public List listarConParametroPagina(String parametro, int desde, int hasta);
    public String add(Receta receta);
    public String edit(Receta receta);
    public String elim(int id);
    public int calcularPaginas(String parametro);
    
    
}