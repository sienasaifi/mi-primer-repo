package examen;

public class Normal extends Persona {

	public Normal(double altura, int edad) {
		super(altura, edad);
		
	}

	@Override
	public double pagar() {
		double importe; 
		
		if (edad>=18)
			importe=4;
		else 
			importe=3; 
		
		totalRecaudado+=importe; 
		return importe;
	}

}
