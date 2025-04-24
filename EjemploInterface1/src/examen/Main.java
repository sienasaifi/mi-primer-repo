package examen;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opc; 
		boolean correcto=false, llena=false, marcha=false; 
		String fechaTexto, codigo; 
		LocalDate fechaNacimiento=null; 
		Scanner sc=new Scanner(System.in); 
		
		Persona montaña[]=new Persona[4];
		LinkedList<Persona> cola=new LinkedList<>(); 
		
		do {
			System.out.println("1. Llegar a la cola");
			System.out.println("2. Ordenar");
			System.out.println("3. Montar");
			System.out.println("4. Poner en marcha");
			System.out.println("5. Parar");
			System.out.println("6. Mostrar");
			System.out.println("7. Salir");
			opc=sc.nextInt(); 
			sc.nextLine(); // Limpiar buffer
			
			
			switch (opc) {
			
				case 1:
					System.out.println("--LLEGAR A LA COLA---");
					do {
						System.out.println("Anota fecha de nacimiento(aaaa-mm-dd): ");
						fechaTexto=sc.nextLine(); 
						try {
						fechaNacimiento=LocalDate.parse(fechaTexto); 
						correcto=true; 
						}
						catch (DateTimeParseException e){
							System.out.println("Formato incorrecto");
						}
					}while(!correcto); 
					
					int edad=(int) ChronoUnit.YEARS.between(LocalDate.now(), fechaNacimiento); 
					Random r=new Random(); 
					double altura=r.nextDouble(70, 210); 
					int tipo=r.nextInt(3); 
					
					switch (tipo) {
						case 0:
							cola.add(new Normal(altura,edad)); 
							break; 
							
						case 1: 
							cola.add(new ConBono(altura,edad)); 
							break; 
							
						case 2: 
							do {
								System.out.println("Anota el codigo: ");
								codigo=sc.nextLine(); 
							} while(codigo.matches("[A-Z]{1,3}[0-9]{2}")); 
							
							cola.add(new ConPromocion(altura,edad,codigo)); 
							break; 
					}
					break; 
					
				case 2: 
					System.out.println("---ORDENAR COLA---");
					Collections.sort(cola); 
					for(Persona pr:cola){  
						System.out.println(cola);
					} 
					
					break; 
					
				case 3: 
					System.out.println("---MONTAR---");
					if (cola.size()<4) {
						System.out.println("No hay suficinetes personas en la cola");
					}
					else {
						int pos=0;
						while (pos<4) {
							Persona p=cola.removeFirst(); 
							try {
								p.apto();
								double importe=p.pagar();
								System.out.println("Persona paga: "+importe);
								montaña[pos]=p; 
								pos++; 
								llena=true; 
							} catch (SaifiSienaException e) {
								System.out.println("No tiene la altura permitida");
							}
						}
					}
					 
					break;
					
				case 4: 
					System.out.println("---PONER EN MARCHAR---");
					
					if (marcha==false) {
						if (llena==false) {
							System.out.println("Lo sentimos, la atracción no está llena todavía.");
						} else {
							marcha=true; 
							System.out.println("Atracción en marcha...");
						}
					} else {
						System.out.println("Atracción ya está en marcha.");
					}
					break; 
					
				case 5: 
					System.out.println("---PARAR ATRACCIÓN---");
					
					if (marcha==true) {
						System.out.println("Parando la atraccón...");
						
						for (int i=0;i<montaña.length;i++) {
							if (montaña[i] instanceof ConBono) {
								
								if (((ConBono)montaña[i]).getViajes()>0) {
									cola.add(montaña[i]); 
								}
							
								
							}
							montaña[i]=null; 
							
						}
						marcha=false; 
					} else {
						System.out.println("La atracción ya está parada.");
					}
					break; 
					
				case 6: 
					System.out.println("---MOSTRAR---");
					
					Iterator<Persona> it= cola.iterator(); 
					while(it.hasNext()) // Recorrerse el collection sin for 
						System.out.println(it.next().toString());
					
					break; 
					
				default: 
					System.out.println("Opción introducida inválida.");
			}
		}while(opc!=7); 

	}

}
