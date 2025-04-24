package examen;

public abstract class Persona implements Comparable<Persona>{

	public abstract double pagar(); 
	
	private double altura;
	protected int edad;
	protected static double totalRecaudado=0; 
	
	public Persona(double altura, int edad) {
		super();
		this.altura = altura;
		this.edad = edad;
	} 
	
	public void apto() throws SaifiSienaException {
		
		if (altura<100 || altura>190)
			throw new SaifiSienaException(null); 
	}
	
	public int compareTo(Persona pr) {
		if(edad==pr.edad) {
			if (altura<pr.altura)
				return 1; 
			else 
				return 0; 
		} else if(edad<pr.edad)  
			return 1;  
		else  
			return -1;  
	}

	@Override
	public String toString() {
		return "Persona [altura=" + altura + ", edad=" + edad + "]";
	}

	
}
