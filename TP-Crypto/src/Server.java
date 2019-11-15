import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
		
		this.algorithmes = new ArrayList<Crypto>();
		this.algorithmes.add(new Ceeeeaaaasaaaaaarrr());
		this.algorithmes.add(new Vigenere());
		this.algorithmes.add(new Xor());
		this.algorithmes.add(new Dico());
		
		rand = (int)(Math.random() * Cle.alphabet.length + 1);
		
		algoChoosed = rand % this.algorithmes.size();
		
		this.cleChoisieStr = Cle.obtenirCleCar(rand);
		this.cleChoisieNum = Cle.obtenirCleNum(Cle.alphabet.length);
		
		for(Crypto algo : this.algorithmes)
		{
			algo.setCle(this.cleChoisieNum);
			algo.setCle(new String(this.cleChoisieStr));
		}
	}

	public ArrayList<String> obtenirFichierEncode() 
	{
		ArrayList<String> newText = new ArrayList<String>();
		
		//algoChoosed = 3;
		
		newText.add(this.algorithmes.get(algoChoosed).obtenirNomAlgo());
		
		//System.out.println("Cles : " + this.cleChoisieNum + " - " + new String(this.cleChoisieStr));
		
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
		return this.cleChoisieStr == cle.toCharArray();
	}
	
	public boolean soumettreCle(int cle)
	{
		return this.cleChoisieNum == cle;
	}
}
