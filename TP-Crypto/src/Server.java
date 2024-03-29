import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 4.0
 * Fichier : Server.java - Classe permettant de lire le le fichiers, il faut passer par elle pour encoder ou d�coder le texte
 */

public class Server 
{
	private ArrayList<String> text;
	private char[] cleChoisieStr;
	private int cleChoisieNum;
	private ArrayList<Crypto> algorithmes;
	private int rand;
	private int algoChoosed;
	public static int TENTATIVE_DE_SOUMMISSION = 0;
	public static int MAX_SOUMISSION = 10;

	public Server(String fileName) throws IOException 
	{
		super();
		
		this.text = new ArrayList<String>();
		File file = new File("./" + fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while ((st = br.readLine()) != null) 
		{
			this.text.add(st);
		}
		br.close();
		
		rand = (int)(Math.random() * 8 + 3);
		
		this.cleChoisieStr = Cle.obtenirCleCar(rand);
		this.cleChoisieNum = Cle.obtenirCleNum(Cle.alphabet.length);
		
		this.algorithmes = new ArrayList<Crypto>();
		this.algorithmes.add(new Ceeeeaaaasaaaaaarrr(cleChoisieNum));
		this.algorithmes.add(new Vigenere(new String(cleChoisieStr)));
		this.algorithmes.add(new Xor(new String(cleChoisieStr)));
		this.algorithmes.add(new Dico(new String(cleChoisieStr)));
		
		this.algoChoosed = rand % this.algorithmes.size();
		this.algoChoosed = 0;
		
		if(this.algorithmes.get(this.algoChoosed) instanceof Dico)
		{
			this.cleChoisieStr = Cle.obtenirCleCar(26);
			for(Crypto algo : this.algorithmes)
			{
				Crypto newDico = new Dico(new String(this.cleChoisieStr));
				if(algo.obtenirNomAlgo() == newDico.obtenirNomAlgo())
				{
					algo = newDico;
				}
			}
		}
	}
	
	/*
	 * Encode le fichier contenu dans le serveur
	 */
	public ArrayList<String> obtenirFichierEncode() 
	{
		ArrayList<String> newText = new ArrayList<String>();
		
		newText.add(this.algorithmes.get(algoChoosed).obtenirNomAlgo());
		
		System.out.println("Cle num�rique : " + this.cleChoisieNum + " - Cle alphab�tique : " + new String(this.cleChoisieStr) + " - Algo utilis� : " + this.algorithmes.get(algoChoosed).obtenirNomAlgo());
		
		for(String line : text)
		{
			newText.add(this.algorithmes.get(algoChoosed).encode(line));
		}
		
		return newText;
	}
	
	/*
	 * Permet de valider la phrase pass�e en param�tre
	 */
	public boolean soumettrePhrase(String phrase, int indice)
	{
		return this.text.get(indice) == phrase;
	}
	
	/*
	 * Permet de valider la cl� alphab�tique pass�e en param�tre
	 */
	public boolean soumettreCle(String cle)
	{
		Server.TENTATIVE_DE_SOUMMISSION += 1;
		return this.cleChoisieStr == cle.toCharArray();
	}
	
	/*
	 * Permet de valider la cl� num�rique pass�e en param�tre
	 */
	public boolean soumettreCle(int cle)
	{
		return this.cleChoisieNum == cle;
	}
}
