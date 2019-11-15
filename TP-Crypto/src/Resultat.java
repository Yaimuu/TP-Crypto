import java.util.ArrayList;

public class Resultat {
	private String algo;
	private ArrayList cles = new ArrayList();
	private ArrayList<Double> scores = new ArrayList<Double>();
	
	
	public Resultat(String algo) {
		this.algo = algo;
	}
	
	public void ajoute(String cle, double score) {
		
	}
	
	public void ajoute(int cle, double score) {
		
	}

	public String getAlgo() {
		return algo;
	}

	public void setAlgo(String algo) {
		this.algo = algo;
	}

	public ArrayList getCles() {
		return cles;
	}

	public void setCles(ArrayList cles) {
		this.cles = cles;
	}
}
