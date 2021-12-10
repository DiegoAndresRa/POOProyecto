/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto;

import java.io.IOException;
import java.nio.file.*;
import java.util. *;

/**
 * Extrae los datos del archivo txt y posteriormente les asigna unaa calificación 
 * aleatoria.
 * @author team k
 */
public class Signature{
    protected String asignatura;
    protected int creditos, semestre;
    protected float calificacion;

    public Signature() {
    }

    public void setAsignatura(String signature) {
        asignatura = signature;
    }

    public void setCreditos(int credits) {
        creditos = credits;
    }

    public void setSemestre(int semestres) {
        semestre = semestres;
    }

    public void setCalificacion(float congratulation) {
        calificacion = congratulation;
    }

    /**
     * Genera datos aleatorios sobre las asignaturas posibles en cada semestre.
     * @param asignaturas
     * @param n
     * @return Lista de asignaturas
     */
    public List<Signature> SignatureStruct(List<Signature> asignaturas, int n){
        
        String lecturaDeLinea;
        int elementos = 0;
        
        while(elementos < n){
            Signature datosAsignatura = new Signature();
            try {
                lecturaDeLinea = Files.readAllLines(Paths.get("DatosDeAsignaturas.txt")).get(elementos);
                String[] claves = lecturaDeLinea.split(",", 3);

                datosAsignatura.setAsignatura(claves[0]);
                datosAsignatura.setCreditos(Integer.parseInt(claves[1]));
                datosAsignatura.setSemestre(Integer.parseInt(claves[2]));
                datosAsignatura.setCalificacion(new Random().nextInt(11 - 6)+6);
                
                asignaturas.add(elementos, datosAsignatura);
                
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
            elementos++;
        }
        
        return asignaturas;
    }
    
}
