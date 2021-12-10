/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto;

import java.io.IOException;
import java.util.*;

/**
 * Permite con base en la clase DataGenerator generar los registros de alumnos.
 * @author team k
 */
public class RecordGenerator extends DataGenerator{
    List<Signature> asignaturas;
    int numeroCuenta, numeroInscripcion, asignaturasAprobadas, creditosTotales;
    float escolaridad, velocidad, promedio, indicadorEscolar;

    // Construcctores
    public RecordGenerator() {
    }

    public RecordGenerator(List<DataGenerator> datosAlumnos) {
        super(datosAlumnos);
    }

    // Setters y getters
    public void setAsignaturas(List<Signature> asignaturas) {
        this.asignaturas = asignaturas;
    }
    
    public void setPromedio(float promedio) {
        this.promedio = promedio;
    }
    
    public void setEscolaridad(float escolaridad) {
        this.escolaridad = escolaridad;
    }
    
    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }
    
    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setIndicadorEscolar(float indicadorEscolar) {
        this.indicadorEscolar = indicadorEscolar;
    }
    
    // Encapsulando el número de inscripción para que no sea accesible para 
    // nadie mas que para el programa.
    private void setNumeroInscripcion(int numeroInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
    }

    public void setAsignaturasAprobadas(int asignaturasAprobadas) {
        this.asignaturasAprobadas = asignaturasAprobadas;
    }
    
    public List<Signature> getAsignaturas() {
        return asignaturas;
    }

    public float getPromedio() {
        return promedio;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getNumeroInscripcion() {
        return numeroInscripcion;
    }

    public int getAsignaturasAprobadas() {
        return asignaturasAprobadas;
    }

    public float getIndicadorEscolar() {
        return indicadorEscolar;
    }
    
    /**
     * Genera los registros de los alumnos en base a una segundo clase para generar los datos.
     * @param datosAlumnos
     * @param registrosAlumnos
     * @param num
     * @throws IOException 
     */
    public void academicRecord(List<DataGenerator> datosAlumnos, List<RecordGenerator> registrosAlumnos, int num) throws IOException {
        for (int i = 0; i < num; i++) {
            registrosAlumnos.add(null);
        }

        for (int i = 0; i < num; i++) {
            RecordGenerator datos = new RecordGenerator(datosAlumnos);
           
            datos.personalData(datosAlumnos, num);
            semestre = datos.getSemestre();
            asignaturas = signatures();
            escolaridad = escolaridad(semestre);
            promedio = promedio();
            velocidad = speed();
            numeroCuenta = count();
            indicadorEscolar = indicadorEscolar();
            
            datos.setDatosAlumnos(datosAlumnos);
            datos.setAsignaturas(asignaturas);
            datos.setEscolaridad(escolaridad);
            datos.setPromedio(promedio);
            datos.setVelocidad(velocidad);
            datos.setNumeroCuenta(numeroCuenta);
            datos.setIndicadorEscolar(indicadorEscolar);
            datos.setAsignaturasAprobadas(asignaturasAprobadas);
            
            registrosAlumnos.set(i, datos);
        }  
    }
    
    /**
     * Reordena el registro de alumnos de acuerdo a su indicador escolar, para posteriormente darle un 
     * número de inscripción a partir del orden en el que se encuentran.
     * @param registrosDeAlumnos 
     */
    public void registrationNumber(List<RecordGenerator> registrosDeAlumnos){
        DataGenerator ordena = new DataGenerator();
        ordena.QuickSort(registrosDeAlumnos, 0, registrosDeAlumnos.size() - 1);;
        for (int i = 0; i < registrosDeAlumnos.size(); i++) {
            registrosDeAlumnos.get(i).setNumeroInscripcion(i+1);
        }
        System.out.println("<calculos completos>");
    }
   
    /**
     *  Asigna un número de signatures aprobadas y reprobadas a cada alumno del registro.
     * @return Lista de signatures
     * @throws IOException 
     */
    private List<Signature> signatures() throws IOException{
        int n,inscritas,reprobadas;
        List<Signature> posibles;
        Signature asignatura;
        inscritas = semestre*5;
        reprobadas=(int)(Math.random() * (4 - 1)) + 1;
        asignaturasAprobadas= inscritas - reprobadas;
        
        asignatura = new Signature();
        posibles = new ArrayList<>();
        posibles = asignatura.SignatureStruct(posibles, inscritas);
        
        creditosTotales = 0;
        for (int i = 0; i < posibles.size(); i++) {
            creditosTotales += posibles.get(i).creditos;
        }

        for(int i = 0; i < reprobadas; i++) {
            n = new Random().nextInt(posibles.size()-1);
            posibles.remove(n);
        }
        
        return posibles;
    }
    
    /**
     * Calcula la escolaridad de los alumnos en base a sus signatures inscritas y aprobadas.
     * @param semestre
     * @return Escolaridad
     */
    private float escolaridad(int semestre){
        int asignaturasInscritas = semestre*5;
        
        float n = ((float)asignaturasAprobadas/(float)asignaturasInscritas)*100;
        escolaridad = Math.round(n*100)/100f;

        return escolaridad;
    }
    
    /**
     * Calcula el promedio de los alumnos en base a sus calificaiones aprobadaas como no aprobadas.
     * @return Promedio
     */
    private float promedio(){
        float calif = 0;
        for (int i = 0; i < asignaturas.size(); i++) {
            calif += asignaturas.get(i).calificacion;
        }
        
        int inscritas = semestre*5;
        int reprobadas = inscritas - asignaturasAprobadas;
        int califReprobadas = reprobadas*5;
        calif += califReprobadas;
        
        float prom = calif/inscritas;
        promedio = Math.round(prom*100)/100f;
        
        return promedio;
    }
    
    /**
     * Determina la speed del alumno en base a sus creditos.
     * @return Velocidad
     */
    private float speed(){
        int creditosAlumno = 0;
        
        for (int i = 0; i < asignaturas.size(); i++) {
            creditosAlumno += asignaturas.get(i).creditos;
        }

        float n = ((float)creditosAlumno/(float)creditosTotales)*100;
        velocidad = Math.round(n*100)/100f;
        
        return velocidad;
    }
    
    /**
     * Genera un número de cuenta aleatorio a cada alumno.
     * @return Numero de Cuenta
     */
    private int count(){
        
        int n = (int)(Math.random() * (399999 -300000)) + 300000;
        numeroCuenta = n;
        
        return numeroCuenta;
        
    }
    
    /**
     * Determina en base a la speed, escolaridad y promedio el indicador escolar de cada alumno.
     * @return 
     */
    private float indicadorEscolar(){
        return indicadorEscolar=velocidad*escolaridad*promedio;
    }
    
    /**
     * Método con submenu mediante el cual se puede buscar dentro de los regitros algun alumno ya sea 
     * por cuenta, numero de inscripción, nombre o semestre.
     * @param registrosAlumnos 
     */
    public void Search(List<RecordGenerator> registrosAlumnos){
        boolean salir = false;
        Scanner op = new Scanner(System.in);
        boolean localizado = false;
        int opcion2;
        
        while (!salir) {
            System.out.println("\n\n         ============== Localizar Alumno  ==============\n");
            System.out.println(" [1] Nombre Completo");
            System.out.println(" [2] Número de cuenta ");
            System.out.println(" [3] Número de inscripción");
            System.out.println(" [4] Semestre");
            System.out.println(" [5] Salir ");

            try {
                System.out.println("\nIngresa alguna de las opciones mostradas");
                System.out.print("-> ");
                opcion2 = op.nextInt();

                    switch (opcion2) {
                        case 1:
                            System.out.println("\n\n         ================= Nombre Completo =================\n");
                            System.out.println("Cual es el nombre Completo del Alumno: ");
                            String nombre = op.nextLine();
                            for(RecordGenerator datos : registrosAlumnos){
                                if(datos.getNombreCompleto().equals(nombre)){
                                    System.out.println("Coincidencia: " + datos.toString());
                                    localizado = true;
                                }
                            }
                            if(localizado == false)
                                System.out.println("<Alumno no encontrado>");
                            break;
                        case 2:
                            System.out.println("\n\n         ============================ Número de cuenta ============================\n");
                            System.out.println("Cual es el número de cuenta del Alumno: ");
                            int numCuenta = op.nextInt();
                            for(RecordGenerator datos : registrosAlumnos){
                                if(datos.getNumeroCuenta() == numCuenta){
                                    System.out.println("Coincidencia: " + datos.toString());
                                    localizado = true;
                                }
                            }
                            if(localizado == false)
                                System.out.println("<Alumno no encontrado>");
                            break;
                        case 3:
                            System.out.println("\n\n         ============ Número de inscripción ============\n");
                            System.out.println("Cual es el número de inscripción del Alumno: ");
                            int numInscripción = op.nextInt();
                            for(RecordGenerator datos : registrosAlumnos){
                                if(datos.getNumeroInscripcion() == numInscripción){
                                    System.out.println("Coincidencia: " + datos.toString());
                                    localizado = true;
                                }
                            }
                            if(localizado == false)
                                System.out.println("<Alumno no encontrado>");
                            break;
                        case 4:
                            System.out.println("\n\n         ============================ Semestre ============================\n");
                            System.out.println("Cual es el semestre del Alumno: ");
                            int semestre = op.nextInt();
                            for(RecordGenerator n : registrosAlumnos){
                                if(n.getSemestre() == semestre){
                                    System.out.println("Coincidencias: " + n.toString());
                                    localizado = true;
                                }
                            }
                            if(localizado == false)
                                System.out.println("<Alumno no encontrado>");
                            break;
                        case 5:
                            salir = true;
                            break;
                        default:
                            System.out.println("Elige una opción correcta entre 1 y 5");
                    }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                op.next();
            }
        }
    }
    
    /**
     * 
     * @param numeroCuenta
     * @param registrosAlumnos
     * @param alumno
     * @throws IOException 
     */
    public void CRUD(int numeroCuenta, List<RecordGenerator> registrosAlumnos, RecordGenerator alumno) throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        
        while (!salir) {
            System.out.println("\n\n         ============== Editar los registros  ==============\n");
            System.out.println("[1] Datos personales");
            System.out.println("[2] Datos academicos");
            System.out.println("[3] Salir");
            try {
                System.out.println("\nIngresa alguna de las opciones mostradas");
                System.out.print("-> ");
                opcion = sc.nextInt();
                
                switch (opcion) {
                    case 1:
                        CRUD1(registrosAlumnos,alumno);
                        break;
                    case 2:
                        CRUD2(registrosAlumnos,alumno);
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Elige una opción correcta entre 1 y 10");
                    }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sc.next();
            }
        }
    }
    
    private void CRUD1(List<RecordGenerator> registrosAlumnos, RecordGenerator alumno){
        boolean salir2 = false;
        Scanner op = new Scanner(System.in);
        int opcion2=0,numlinea = 0;
        
        for(int i = 0; i < registrosAlumnos.size(); i++){
            if(registrosAlumnos.get(i).numeroCuenta == numeroCuenta){
                alumno = registrosAlumnos.get(i);
                numlinea = i;
            }
        }
        
        while (!salir2) {
            System.out.println("\n\n         ======== Editar los registros Personales =======\n");
            System.out.println(" [1] Nombre Completo");
            System.out.println(" [2] Dirección ");
            System.out.println(" [3] Salir ");

            try {

                System.out.println("\nIngresa alguna de las opciones mostradas");
                System.out.print("-> ");
                opcion2 = op.nextInt();

                    switch (opcion2) {
                        case 1:
                            System.out.println("\n\n         ================= Nombre Completo =================\n");
                            System.out.println("Cual sera el Nombre Completo del Alumno: ");
                            String nombre = new java.util.Scanner(System.in).nextLine();
                            
                            alumno.setNombreCompleto(nombre);
                            registrosAlumnos.set(numlinea, alumno);
                            System.out.println("El registro nuevo es: " + alumno.toString());
                            break;
                        case 2:
                            System.out.println("\n\n         ============================ Dirección ============================\n");
                            System.out.println("Cual sera dirección completa nueva: ");
                            String direccion = new java.util.Scanner(System.in).nextLine();
                            alumno.setDireccionCompleta(direccion);
                            registrosAlumnos.set(numlinea, alumno);
                            System.out.println("El registro nuevo es: " + alumno.toString());
                            break;
                        case 3:
                            salir2 = true;
                            break;
                        default:
                            System.out.println("Elige una opción correcta entre 1 y 4");
                    }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                op.next();
            }
        }
    }
    
    private void CRUD2(List<RecordGenerator> registrosAlumnos, RecordGenerator alumno) throws IOException{
    Scanner sn = new Scanner(System.in);
        boolean salir2 = false;
        Scanner op = new Scanner(System.in);
        int opcion2=0,numlinea = 0;
        
        
        while (!salir2) {
            System.out.println("\n\n         ========= Editar los registros Academicos =========\n");
            System.out.println(" [1] Número de cuenta ");
            System.out.println(" [2] Escolaridad");
            System.out.println(" [3] Velocidad");
            System.out.println(" [4] Promedio");
            System.out.println(" [5] Semestre");
            System.out.println(" [6] Salir ");

            try {

                System.out.println("\nIngresa alguna de las opciones mostradas");
                System.out.print("-> ");
                opcion2 = sn.nextInt();

                    switch (opcion2) {
                        case 1:
                            System.out.println("\n\n         ============================ Número de cuenta ============================\n");
                            System.out.println("Cual sera número de cuenta del Alumno: ");
                            int num = op.nextInt();
                            alumno.setNumeroCuenta(num);
                            registrosAlumnos.set(numlinea, alumno);
                            System.out.println("El registro nuevo es: " + alumno.toString());
                            break;
                        case 2:
                            System.out.println("\n\n         ============ Escolaridad ============\n");
                            System.out.println("Cual sera la Escolaridad: ");
                            float escolar = op.nextFloat();
                            alumno.setEscolaridad(escolar);
                            registrosAlumnos.set(numlinea, alumno);
                            System.out.println("El registro nuevo es: " + alumno.toString());
                            break;
                        case 3:
                            System.out.println("\n\n         ============ Velocidad ============\n");
                            System.out.println("Cual sera la nueva Velocidad: ");
                            float vel = op.nextFloat();
                            alumno.setVelocidad(vel);
                            registrosAlumnos.set(numlinea, alumno);
                            System.out.println("El registro nuevo es: " + alumno.toString());
                            break;
                        case 4:
                            System.out.println("\n\n         ============================ Promedio ============================\n");
                            System.out.println("Cual sera promedio nuevo: ");
                            int prom = op.nextInt();
                            alumno.setPromedio(prom);
                            registrosAlumnos.set(numlinea, alumno);
                            System.out.println("El registro nuevo es: " + alumno.toString());
                            break;
                        case 5:
                            System.out.println("\n\n         ============================ Semestre ============================\n");
                            System.out.println("Cual sera semestre nuevo: ");
                            int semestre = op.nextInt();
                            alumno.setSemestre(semestre);
                            semestre=alumno.getSemestre();
                            asignaturas = signatures();
                            escolaridad = escolaridad(semestre);
                            this.promedio = promedio();
                            velocidad = speed();
                            indicadorEscolar = indicadorEscolar();
                            alumno.setAsignaturas(asignaturas);
                            alumno.setEscolaridad(escolaridad);
                            alumno.setPromedio(this.promedio);
                            alumno.setVelocidad(velocidad);
                            alumno.setIndicadorEscolar(indicadorEscolar);
                            registrosAlumnos.set(numlinea, alumno);
                            System.out.println("El registro nuevo es: " + alumno.toString());
                            break;
                        case 6:
                            salir2 = true;
                            break;
                        default:
                            System.out.println("Elige una opción correcta entre 1 y 7");
                    }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }     
    }
    
    /**
     * Consulta el número de inscripción del alumno
     * @param registrosAlumnos 
     */
    public void ConsultaNumInscrip(List<RecordGenerator> registrosAlumnos){
        System.out.println("Ingresa tu número de cuenta para consultar tu número de Inscripción: ");
        boolean localizado = false;
        
        int numCuenta = new java.util.Scanner(System.in).nextInt();
        
        for(RecordGenerator datosDelAlumno : registrosAlumnos){
            if(datosDelAlumno.getNumeroCuenta() == numCuenta){
                System.out.println("Se encontró al alumno: " +datosDelAlumno.getNombreCompleto()+"\nTu numero de cuenta es: "+datosDelAlumno.getNumeroCuenta()+"\nTu edad es: "+datosDelAlumno.getEdad()
                        +"\nTu numero de Inscripción es: "+datosDelAlumno.getNumeroInscripcion()+"\nTu Dirección es: "+datosDelAlumno.getDireccionCompleta()+"\nTu promedio es: "+datosDelAlumno.getPromedio());
                localizado = true;
            }
        }
        if(localizado == false)
            System.out.println("No te encuentras en la Base de Datos intenta de nuevo");
    }
    
    @Override    
    public String toString() {
        return super.toString() + "\nNúmero de Cuenta: " + numeroCuenta + "\nNúmero de Inscipción: " + numeroInscripcion + "\nPromedio global: " + promedio ;
    }
}
