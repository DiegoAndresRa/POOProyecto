/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.shape.Path;

/**
 * Clase para la generación aleatoria de cada uno de los datos que comprenden 
 * a un alumnos de la facultad.
 * @author team k
 */
public class DataGenerator extends RandomItems{
    
    int semestre, codigoPostal, numeroInterior,edad;
    String direccionCompleta, nombreCompleto,calle, vivienda, delegacion, estadoDeLaRepublica;
    List<DataGenerator> datosAlumnos;
    
    public DataGenerator() {
    }
    
    /**
     * Manda a llamar al constructor de su padre para identar los nombre y apellidos
     * @param nombres
     * @param apellidos 
     */
    public DataGenerator(List<String> nombres, List<String> apellidos) {
        super(nombres, apellidos);
    }

    public DataGenerator(List<DataGenerator> datosAlumno) {
        this.datosAlumnos = datosAlumno;
    }

    public DataGenerator(int semestre, int edad, String direccion, String nombres) {
        this.semestre = semestre;
        this.edad = edad;
        this.direccionCompleta = direccion;
        this.nombreCompleto = nombres;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public List<DataGenerator> getDatosAlumnos() {
        return datosAlumnos;
    }

    public void setDatosAlumnos(List<DataGenerator> datosAlumnos) {
        this.datosAlumnos = datosAlumnos;
    }
    
    /**
     * Genera datos aleatorios para un estudiante.
     * @param datosAlumnos
     * @param cantAlumnos 
     */
    public void personalData(List<DataGenerator> datosAlumnos, int cantAlumnos) throws IOException{
        for (int i = 0; i < cantAlumnos; i++) {
            datosAlumnos.add(null);
        }
        DataGenerator datos = new DataGenerator();
        
        for (int i = 0; i < cantAlumnos; i++) {
            DataGenerator datos2 = new DataGenerator();
            
            nombreCompleto = names();
            semestre = semestre();
            edad = age();
            direccionCompleta = addres();

            datos2.setNombreCompleto(nombreCompleto);
            datos2.setSemestre(semestre);
            datos2.setEdad(edad);
            datos2.setDireccionCompleta(direccionCompleta);
    
            datosAlumnos.set(i, datos2);
        }
        datos.setDatosAlumnos(datosAlumnos);
    }
    
    /**
     * Lectura y asignación del un nombre aleatorio de un archivo txt.
     * @return Nombre completo 
     */
    private String names() throws IOException{
        String name, subName;
        name = Files.readAllLines(Paths.get("Nombres.txt")).get(new Random().nextInt(49));
        subName = Files.readAllLines(Paths.get("Apellidos.txt")).get(new Random().nextInt(49));
        nombreCompleto = name + " " + subName;
        
        return nombreCompleto;
    } 
    
    /**
     * Genera un número de semestre de 1 a 12.
     * @return Número de Semestre
     */
    private int semestre(){
        int numSemestre = new Random().nextInt(11 - 1) + 1;
        semestre = numSemestre;
        return semestre;
    }
    
    /**
     * Retorna un número referente a la age del alumno tomando como premisa 
 el semestre que se encuentra cursando.
     * @return Edad
     */
    private int age(){
        Random aleatorio = new Random();
        int edad2;
        
        if(semestre == 1 || semestre == 2){
            edad2 = aleatorio.nextInt(21 - 18) + 18;
            edad = edad2;
        }
        if(semestre == 3 || semestre == 4){
            edad2 = aleatorio.nextInt(23 - 19) + 19;
            edad = edad2;  
        }
        if(semestre == 5 || semestre == 6){
            edad2 = aleatorio.nextInt(25 - 21) + 21;
            edad = edad2;
        }
        if(semestre == 7 || semestre == 8){
            edad2 = aleatorio.nextInt(27 - 23) + 23;
            edad = edad2;
        }
        if(semestre == 9 || semestre == 10){
            edad2 = aleatorio.nextInt(28 - 24) + 24;
            edad = edad2;
        }

        return edad;
    }
     
    /**
     * Genera una dirección aleatoria a travez de la abstracción de datos aleatorios 
     * de archvos txt.
     * @return 
     */
    private String addres() throws IOException{
        List<String> asen,calles;
        String delegation;
        DataGenerator l;
        
        l = new DataGenerator();
        asen = new ArrayList(500);
        asen = l.home(asen);
        vivienda = asen.get(new Random().nextInt(499));
        
        estadoDeLaRepublica = "CDMX";
        codigoPostal = new Random().nextInt(18000- 01000) + 01000;
        numeroInterior = new Random().nextInt(30);
        
        
        delegation = Files.readAllLines(Paths.get("Delegaciones.txt")).get(new Random().nextInt(15));
        delegacion = delegation;

        calles = new ArrayList(500);
        calles = l.street(calles);
        calle = calles.get(new Random().nextInt(499));
        
        direccionCompleta = calle + "//" + numeroInterior + "//" + vivienda + "//" + delegacion + "//" + estadoDeLaRepublica + "//" + codigoPostal;
        
        return direccionCompleta;
    }
    
    /**
     * Ordenando claves mediantes una métodologia QuickSort
     * @param arr
     * @param low
     * @param high 
     */
    public void QuickSort(List<RecordGenerator> arr, int low, int high){
        if (low < high){
            int pi = particion(arr, low, high);
            QuickSort(arr, low, pi-1);
            QuickSort(arr, pi+1, high);
        }
    }
    
    private int particion(List<RecordGenerator> arr, int low, int high){
        RecordGenerator pivot = arr.get(high);
        int i = (low-1);
        for (int j=low; j<high; j++){
            if (arr.get(j).getIndicadorEscolar() >= pivot.getIndicadorEscolar()) {
                i++;
                swap(arr, i,j);
            }
        }
        RecordGenerator temp = arr.get(i+1);
        arr.set(i+1, arr.get(high));
        arr.set(high, temp);
        return i+1;
    }
    
    private void swap (List<RecordGenerator> arr, int i, int j){
        RecordGenerator temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
    
    
    @Override
    public String toString() {
        return "\nNombre: " + nombreCompleto + "\nEdad: " + edad + "\nSemestre: " + semestre +  "\nDirección: " + direccionCompleta;
    }
}