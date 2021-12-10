/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;


/**
 * Clase para la extracción de los datos de los archivos txt, principalmente para 
 * aquellos que presentan un gran peso (bytes).
 * @author team k
 */
public class RandomItems {
    
    //Atributos
    List<String> calles;
    List<String> vivienda;
    List<String> delegaciones;
    List<String> nombres;
    List<String> apellidos;

    public RandomItems() {
        
    }

    public RandomItems(List<String> nombres, List<String> apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    
    /**
     * Obtiene los datos de las viviendas extraidas de un archivo txt.
     * @param vivienda
     * @return Lista con las viviendas
     */
    public List home(List<String> vivienda){
        Scanner Archivo;
        String lecturaLinea;
        
        try {
            Archivo = new Scanner(new FileReader("vivienda.txt"));
            while (Archivo.hasNextLine()) {
                lecturaLinea = Archivo.nextLine();
                vivienda.add(lecturaLinea);
            }
            Archivo.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return vivienda;
    }
    
    /**
     * Obtiene los datos de las calles extraidas de un archivo txt.
     * @param calles
     * @return Listas de calles
     */
    public List street(List<String> calles){
        Scanner archivo;
        String lecturaDeLinea;
        
        try {
            archivo = new Scanner(new FileReader("Calles.txt"));
            while (archivo.hasNextLine()) {
                lecturaDeLinea = archivo.nextLine();
                calles.add(lecturaDeLinea);
            }
            archivo.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Error: " + e.getMessage());
        }
        
        return calles;
    }
}