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
	private String cleChoisieStr;
	private int cleChoisieNum;
	private ArrayList<Crypto> algorithmes;

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
		
		this.cleChoisieStr = "";
		this.cleChoisieNum = 0;
		
		this.algorithmes = new ArrayList<Crypto>();
		this.algorithmes.add(new Ceeeeaaaasaaaaaarrr());
		this.algorithmes.add(new Vigenere());
		this.algorithmes.add(new Xor());
		this.algorithmes.add(new Dico());
	}

	public ArrayList<String> obtenirFichierEncode() 
	{
		int random = (int)(Math.random() * this.algorithmes.size() + 1);
		ArrayList<String> newText = new ArrayList<String>();
		
		newText.add(this.algorithmes.get(random-1).getClass().toString());
		for(String line : text)
		{
			newText.add(this.algorithmes.get(random-1).encode(line));
		}
		
		return newText;
	}
	
	public boolean soumettrePhrase(String phrase, int indice)
	{
		this.text.add(indice, phrase);
		return false;
	}
	
	public boolean soumettreCle(String cle)
	{
		return false;
	}
	
	public boolean soumettreCle(int cle)
	{
		return false;
	}
}
