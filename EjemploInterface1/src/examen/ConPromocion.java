package examen;

public class ConPromocion extends Persona {

	private String codigo; 
	
	public ConPromocion(double altura, int edad, String codigo) {
		super(altura, edad);
		this.codigo=codigo; 
	}

	@Override
	public double pagar() {
		String precio=codigo.substring(-2); 
		double importe =Integer.parseInt(precio); 
		totalRecaudado+=importe; 
		return importe;
	}

}
