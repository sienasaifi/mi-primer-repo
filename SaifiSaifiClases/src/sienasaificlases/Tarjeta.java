package sienasaificlases;

import java.time.LocalDate;

// Siena Saifi Julio

public class Tarjeta {
	
	private String numTarjeta;
	private String titular; 
	private String clave;
	private double saldo; 
	private LocalDate fechaValidez;
	private int bloqueada; 
	private static int bloqueadas=0; 
	
	public Tarjeta(String numTarjeta, String titular, String clave, double saldo, LocalDate fechaValidez) {
		
		this.numTarjeta = numTarjeta;
		this.titular = titular;
		this.clave = clave;
		this.saldo = saldo;
		this.fechaValidez = fechaValidez;
		this.bloqueada=0; // si es 0 la tarjeta no está bloqueda, si el 1 la tarjeta está bloqueda
		
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void pagar(double importe, String clave, LocalDate fechaActual) {
		// Comprobar que no este caducada
		
		if (bloqueada==0) {
			if (clave.equalsIgnoreCase(this.clave)) {
				System.out.println("Clave correcta. Se va a proceder al pago");
				
				if (fechaValidez.isAfter(fechaActual)) {
				
					if (saldo>importe) {
						System.out.println("Pago correctamente realizado");
						saldo-=importe; 
					}
					else 
						System.out.println("No tienes suficiente saldo para realizar este pago");
				}
				else 
					System.out.println("Tarjeta caducada. ");
	
			} 
			else 
				System.out.println("Clave incorrecta. ");
				
		}
		else 
			System.out.println("TARJETA BLOQUEDA");
		
	}
	
	public void recargarTarjeta(double importe, LocalDate fechaActual) {
		
		if (bloqueada==0) {
			if (fechaValidez.isAfter(fechaActual))
				saldo+=importe; 
			else 
				System.out.println("Tarjeta caducada. ");
		}
		else 
			System.out.println("TARJETA BLOQUEDA");
	}
	
	public void bloquearTarjeta(int opc2) {
		
		if (opc2==1) {
			if (bloqueada==1)
				System.out.println("Error. Tarjeta anotada ya estaba bloqueda");
			if (bloqueada==0) {
				System.out.println("Tarjeta bloqueda correctamente");
				bloqueadas++; 
				bloqueada=1; 
			}
		}
		
		if (opc2==2) {
			if (bloqueada==1) {
				System.out.println("Tarjeta desbloqueda correctamente");
				bloqueadas--; 
				bloqueada=2; 
			}
			if (bloqueada==0)
				System.out.println("Error. Tarjeta anotada ya estaba desbloqueda");
		}
		
	}
	
	@Override
	public String toString() {
		return "Tarjeta [numTarjeta=" + numTarjeta + ", titular=" + titular + ", clave= ****, saldo=" + saldo
				+ ", fechaValidez=" + fechaValidez + ", bloqueada=" + bloqueada + "]";
	}

	public static int getBloqueadas() {
		return bloqueadas;
	}

	public String getTitular() {
		return titular;
	}

	public LocalDate getFechaValidez() {
		return fechaValidez;
	}
	
	
	
}









