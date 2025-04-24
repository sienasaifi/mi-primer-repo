package examen;

public class ConBono extends Persona {
	
	private int viajes; 

	public ConBono(double altura, int edad) {
		super(altura, edad);
		this.viajes=3; 
		
	}

	@Override
	public double pagar() {
		
		double importe=0; 
		if (viajes>0) {
			if (viajes>1)
				return importe; 
			else {
				viajes--; 
				if (edad>18) {
					importe=10; 
				}else { 
					importe=4; 
				}
			}
		}
		else 
			System.out.println("Lo sentimos, no tienes suficientes viajes");
		
		totalRecaudado+=importe; 
		return importe;
	}

	public int getViajes() {
		return viajes;
	}
	
	

}
