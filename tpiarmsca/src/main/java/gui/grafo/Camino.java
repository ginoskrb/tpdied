package gui.grafo;

public class Camino {
	
	private String origen;
	private String destino;
	private String peso;
	private boolean habilitado;
	
	
	public Camino (String origen, String destino, String peso) {
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
		this.habilitado = true;
		
	}
	
	
	public String getPeso() {
		return peso;
	}


	public void setPeso(String peso) {
		this.peso = peso;
		if(Integer.parseInt(peso)==0) {
			this.setHabilitado(false);
		}
	}


	public String getOrigen() {
		return origen;
	}


	public String getDestino() {
		return destino;
	}


	public boolean isHabilitado() {
		return habilitado;
	}


	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	
	
	
	
}
