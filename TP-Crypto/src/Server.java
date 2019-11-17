import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Server.java - Classe permettant de lire le le fichiers, il faut passer par elle pour encoder ou décoder le texte
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

	public ArrayList<String> obtenirFichierEncode() 
	{
		ArrayList<String> newText = new ArrayList<String>();
		
		newText.add(this.algorithmes.get(algoChoosed).obtenirNomAlgo());
		
		System.out.println("Cles : " + this.cleChoisieNum + " - " + new String(this.cleChoisieStr) + " Algo : " + this.algorithmes.get(algoChoosed).obtenirNomAlgo());
		
		
		for(String line : text)
		{
			newText.add(this.algorithmes.get(algoChoosed).decode(this.algorithmes.get(algoChoosed).encode(line)));
		}
		
		return newText;
	}
	
	public boolean soumettrePhrase(String phrase, int indice)
	{
		return this.text.get(indice) == phrase;
	}
	
	public boolean soumettreCle(String cle)
	{
		Server.TENTATIVE_DE_SOUMMISSION += 1;
		return this.cleChoisieStr == cle.toCharArray();
	}
	
	public boolean soumettreCle(int cle)
	{
		return this.cleChoisieNum == cle;
	}
}
