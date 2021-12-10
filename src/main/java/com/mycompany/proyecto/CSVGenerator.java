/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Implementación para guardar los datos dentro de un archivo instanciado dentro 
 * de la misma carpeta del proyecto (programa).
 * @author team k
 */
public class CSVGenerator{
    /**
     * Guardar Registros en un archivo con extensión csv.
     * @param registrosDeAlumnos 
     */
    public void guardarRegistros(List<RecordGenerator> registrosDeAlumnos){
        try{
            //nombreArchivo = JOptionPane.showInputDialog("Ingresa el nombre del archivo y agrega su extención");
            
            PrintWriter escrituraArchivo = new PrintWriter(new FileWriter(new java.util.Scanner(System.in).nextLine()));
            
            escrituraArchivo.println("Número de Inscripción,Nombre completo,Edad,Semestre,Número de cuenta,Dirección,Asignaturas Aprobadas,Promedio,Escolaridad,Velocidad");
            
            for(RecordGenerator registros : registrosDeAlumnos)
                escrituraArchivo.println(registros.numeroInscripcion+","+registros.nombreCompleto+","+registros.edad+","+registros.semestre+","+registros.numeroCuenta+","+registros.direccionCompleta+", En el semestre "+(registros.semestre)+" se tiene "+registros.asignaturasAprobadas+","+
                registros.promedio+","+registros.escolaridad+","+registros.velocidad);
            
            escrituraArchivo.close();
        } 
        catch(IOException error) {
            System.out.println(error.getMessage());
        } 
    }

}
