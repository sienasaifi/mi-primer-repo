package sienasaificlases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

import sienasaificlases.Tarjeta;
import sienasaificlases.NoEncontradoException;

public class MainTarjeta {

	public static void main(String[] args) {
		// Siena Saifi Julio
		// TODO Auto-generated method stud
		final int NTAR=3; 
		int opc1, ultimaTarjeta=0, pos=0, opc2, pos2=0; 
		String numTarjeta, titular, clave, fechaTexto; 
		String regex1="[A-Z]{2}[]0-9]{4}", regex2="[0-9]{4}"; 
		double saldo, importe; 
		boolean correcto=true;  
		LocalDate fechaValidez=null, fechaActual=LocalDate.now(); 
		DateTimeFormatter formato; 
		Tarjeta tarjetas[]=new Tarjeta[NTAR]; 
		Scanner sc=new Scanner(System.in); 
		sc.useLocale(Locale.ENGLISH); 
		
		
		do {
			System.out.println("--- MENU ---");
			System.out.println("1. Dar de alta una tarjeta");
			System.out.println("2. Realizar un pago");
			System.out.println("3. Comprobar si dos trajetas pertenecen al mismo titular y borrar la más antigua");
			System.out.println("4. Recargar tarjeta");
			System.out.println("5. Bloquear o desbloquear tarjeta");
			System.out.println("6. Mostrar información de todas las tarjetas");
			System.out.println("7. Mostrar el número de las tarjetas bloqueadas");
			System.out.println("8. Salir");
			opc1=sc.nextInt(); 
			
			
			switch (opc1) {
				case 1: 
					System.out.println("--- DAR DE ALTA TARJETA ---");
					
					if (ultimaTarjeta==NTAR)
						System.out.println("No es posible añadir más tarjetas");
					else {
						sc.nextLine(); // Limpio buffer 
						
						do {
							correcto=true; 
							System.out.println("Anota el número de la tarjeta: ");
							numTarjeta=sc.nextLine(); 
							
							if (!numTarjeta.matches(regex1)) {
								correcto=false; 
								System.out.println("Error. El número de la tarjeta no sigue el formato correcto");
							}
						
						}while (!correcto); 
						
						System.out.println("Anota el titular: ");
						titular=sc.nextLine(); 
						
						do {
							correcto=true; 
							System.out.println("Anota la clave de la tarjeta: ");
							clave=sc.nextLine(); 
							
							if (clave.matches(regex2)) {
								correcto=false; 
								System.out.println("Error. La de la tarjeta no sigue el formato correcto");
							}
						
						}while (!correcto); 
						
						sc.nextLine(); // Limpiar buffer
						System.out.println("Anota el saldo de la tajeta: ");
						saldo=sc.nextDouble(); 
						sc.nextLine(); // Limpiar buffer
						do {
							correcto=true; 
							System.out.println("Anote su fecha de nacimiento (yyyy-LL-dd): ");
							fechaTexto=sc.nextLine(); 
							
							//Pasar la fecha de un String a un Date 
							//Si la fecha no tiene el formato requerido se repite el bucle hasta tener el formato
							try {
							fechaValidez=LocalDate.parse(fechaTexto); 
							}
							catch (DateTimeParseException e) {
							 correcto=false;
							 continue; 
							}
						
						} while(!correcto); 
						
						tarjetas[ultimaTarjeta]=new Tarjeta(numTarjeta,titular,clave,saldo,fechaValidez); 
						
						ultimaTarjeta++; 
					}
					break; 
					
				case 2: 
					System.out.println("--- REALIZAR UN PAGO ---");
					
					System.out.println("Anota el número de la tarjeta: ");
					numTarjeta=sc.nextLine(); 
					
					try {
						pos=buscar(tarjetas,numTarjeta);
					} catch (NoEncontradoException e) {
						System.out.println(e.getMessage());
					} 
					
					System.out.println("Anota la clave de la tarjeta: ");
					clave=sc.nextLine(); 
					
					sc.nextLine(); // Limpiar buffer
					System.out.println("Anota el importe que quieres pagar: ");
					importe=sc.nextDouble(); 
					
					tarjetas[pos].pagar(importe,clave,fechaActual);
					break; 
					
				case 3: 
					System.out.println("--- COMPROBACIÓN DE DOS TARJETAS ---");
					
					System.out.println("Anota el número de la tarjeta: ");
					numTarjeta=sc.nextLine(); 
					
					try {
						pos=buscar(tarjetas,numTarjeta);
					} catch (NoEncontradoException e) {
						System.out.println(e.getMessage());
					} 
					
					System.out.println("Anota el número de la otra tarjeta: ");
					numTarjeta=sc.nextLine(); 
					
					try {
						pos2=buscar(tarjetas,numTarjeta);
					} catch (NoEncontradoException e) {
						System.out.println(e.getMessage());
					} 
					
					if (tarjetas[pos].getTitular().equalsIgnoreCase(tarjetas[pos2].getTitular())) {
						System.out.println("Ambas tarjetas tienen el mismo titular");
						System.out.println("Se va ha proceder a dar de baja la más antigua...");
						
						if (tarjetas[pos].getFechaValidez().isBefore(tarjetas[pos2].getFechaValidez())) {
							
							for (int i=pos;i<ultimaTarjeta-1;i++) 
								tarjetas[i]=tarjetas[i+1]; 
							
							tarjetas[ultimaTarjeta-1]=null;
							ultimaTarjeta--; 
							
						}
						
						if (tarjetas[pos2].getFechaValidez().isBefore(tarjetas[pos].getFechaValidez())) {
							
							for (int i=pos2;i<ultimaTarjeta-1;i++) 
								tarjetas[i]=tarjetas[i+1]; 
							
							tarjetas[ultimaTarjeta-1]=null;
							ultimaTarjeta--; 
							
						}
						
					}
					else
						System.out.println("Las tarjetas anotadas no tienen el mismo titular");
					
					break; 
						
					
				case 4: 
					System.out.println("--- RECARGAR TARJETA ---");
					
					System.out.println("Anota el número de la tarjeta: ");
					numTarjeta=sc.nextLine(); 
					
					try {
						pos=buscar(tarjetas,numTarjeta);
					} catch (NoEncontradoException e) {
						System.out.println(e.getMessage());
					} 
					sc.nextLine(); // Limpiar buffer
					System.out.println("Anota el importe que quieres ingresar: ");
					importe=sc.nextDouble(); 
					
					tarjetas[pos].recargarTarjeta(importe,fechaActual);
					break; 
				
				case 5: 
					System.out.println("--- BLOQUEAR O DESBLOQUEAR TARJETA ---");
					
					System.out.println("Anota el número de la tarjeta: ");
					numTarjeta=sc.nextLine(); 
					
					try {
						pos=buscar(tarjetas,numTarjeta);
					} catch (NoEncontradoException e) {
						System.out.println(e.getMessage());
					} 
					
					sc.nextLine(); // Limpiar buffer
					System.out.println("1. Bloquear");
					System.out.println("2. Desbloquear");
					opc2=sc.nextInt(); 
					
					tarjetas[pos].bloquearTarjeta(opc2);
					break; 
					
				case 6: 
					System.out.println("--- MOSTAR INFORMACIÓN DE TODAS LAS TARJETAS ---");
					for (int i=0;i<ultimaTarjeta;i++)
						System.out.println(tarjetas[i].toString());
					break;
				
				case 7: 
					System.out.println("--- TARJETAS BLOQUEDAS ---");
					System.out.println("Actaulmente hay "+Tarjeta.getBloqueadas()+" bloquedas");
					break; 
					
				default: 
					System.out.println("Error. Opción anotada no válida");
					break; 
					
			}
		}while (opc1!=8);
		
		
	}
	
	
	public static int buscar(Tarjeta tarjetas[], String numTarjeta) throws NoEncontradoException {
		for (int i=0;i<tarjetas.length;i++) {
			if (tarjetas[i].getNumTarjeta().equalsIgnoreCase(numTarjeta))
				return i; 
		}
		throw new NoEncontradoException("Error. Tarjeta anotado no encontrada"); 
	}

}










