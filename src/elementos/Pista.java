package elementos;

public class Pista {

	public String origem, pista, inferencia;
	public boolean visivel;
	public int nivel;

	public Pista(String origem, String pista, String inferencia, int nivel) {
		super();
		this.origem = origem;
		this.pista = pista;
		this.inferencia = inferencia;
		this.visivel = true;
		this.nivel = nivel;
	}

}
