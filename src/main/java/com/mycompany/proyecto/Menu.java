
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author team k
 */
package com.mycompany.proyecto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;

public class Menu {    
    public static void main(String[] args) throws IOException {
        Scanner sn = new Scanner(System.in);
        boolean salir = false, salir2 = false;

        int opcion=0, numeroroAGenerar = 0,opcion2=0;
        
        List<DataGenerator> datosAlumnos = new ArrayList();
        List<RecordGenerator> registrosAlumnos = new ArrayList();
        RecordGenerator registrosParaLaGestion = new RecordGenerator();
        
        while (!salir) {
            System.out.println("\n\n         ==================  G E S T O R   E S C O L A R ==================");
            System.out.println("                                 B R A I N   B E A M                           \n");
            System.out.println(" [1] Alumno");
            System.out.println(" [2] Administrador");
            System.out.println(" [3] Salir");
            try {    
                System.out.println("\nIngresa alguna de las opciones mostradas");
                System.out.print("-> ");
                opcion = sn.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.out.println("\n\n         ============== A L U M N O  ==============\n");
                        registrosParaLaGestion.ConsultaNumInscrip(registrosAlumnos);
                        break;
                    case 2:
                        while (!salir2) {
                        System.out.println("\n\n         ============== A D M I N I S T R A D O R  ==============\n");
                        System.out.println(" [1] Generar registro de los alumnos");
                        System.out.println(" [2] Calcular números de Inscripción");
                        System.out.println(" [3] Localizar Alumno ");
                        System.out.println(" [4] Editar los registros");
                        System.out.println(" [5] Guardar registro (.csv)");
                        System.out.println(" [6] Salir ");


                            try {

                                System.out.println("\nIngresa alguna de las opciones mostradas");
                                System.out.print("-> ");
                                opcion2 = sn.nextInt();

                                switch (opcion2) {
                                    case 1:
                                        System.out.println("\n\n         ================= Generar registro de los alumnos =================");
                                        System.out.println("Ingrese el numero de registros a Generar");
                                        numeroroAGenerar = sn.nextInt();

                                        System.out.println("\nSe han generado "+numeroroAGenerar+" registros");
                                        registrosParaLaGestion.academicRecord(datosAlumnos, registrosAlumnos, numeroroAGenerar);
                                        break;
                                    case 2:
                                        System.out.println("\n\n         ============ Calcular números de Inscripción ============");
                                        registrosParaLaGestion.registrationNumber(registrosAlumnos);
                                        break;
                                    case 3:
                                        System.out.println("\n\n         ============================ Localizar Alumno ============================");
                                        registrosParaLaGestion.Search(registrosAlumnos);
                                        break;
                                    case 4:
                                        System.out.println("\n\n         ============ Editar los registros ============");
                                        System.out.println("Cual es el numero de cuenta del alumno: ");
                                        int numCuenta = sn.nextInt();
                                        registrosParaLaGestion.CRUD(numCuenta, registrosAlumnos, registrosParaLaGestion);
                                        break;
                                    case 5:
                                        System.out.println("\n\n         ============================ Guardar registro (.csv) ============================");
                                        CSVGenerator archivo = new CSVGenerator();
                                        System.out.println("Ingresa el nombre del archivo y agrega su extención");
                                        archivo.guardarRegistros(registrosAlumnos);
                                        System.out.println("Se han guardado los registros en el archivo indicado");
                                        break;
                                    case 6:
                                        salir2 = true;
                                        break;
                                    default:
                                        System.out.println("Elige una opción correcta entre 1 y 6");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Debes insertar un número");
                                sn.next();
                            }
                        }
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("Elige una opción correcta entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }
}
       

