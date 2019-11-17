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
	public static int TENTATIVE_DE_SOUMMISSION = 0;
	private static int MAX_SOUMISSION = 10;

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
		
		System.out.println("Cles : " + this.cleChoisieNum + " - " + new String(this.cleChoisieStr));
		
		
		Xor test = new Xor();
		String keyy = "";
		for(int i = 0; i < this.cleChoisieStr.length; i++) {
			keyy += this.cleChoisieStr[i];
		}
		System.out.println(keyy);
		test.setCle(keyy);
		System.out.println(test.encode("OUIIiiiIII"));
		System.out.println(test.decode(test.encode("OUIIiiiIII")));
		
		char[][] clesTests = Cle.clesMutees(this.cleChoisieStr);
		
		char[] cleSwap = Cle.cleRearrangee(this.cleChoisieStr);
		
		//System.out.println(cleSwap);
		
		String charact = "";
		/*for (int i = 0; i < clesTests.length; i++) {
		    System.out.println(clesTests[i]);
		    charact = "";
		}*/
		
		/*char[] testchars = {'A', 'A', 'A', 'A'};
		char[][] testcharsTable = new char[testchars.length*26][testchars.length];
		
		int alphabet = 0;
		String charact = "";
		char actualChar = 'A';
		int counter = 0;
		int nbTurn = 0;
		char[] tempTable = testchars;
		for(int i = 0; i < testchars.length*26; i++) {
			
			
		}
		
		for(int j = 0; j < testchars.length; j++) {
			for(int k = 0; k < 26; k++) {
				tempTable[j] = (char)(k+'A');
				//System.out.println(tempTable[counter]);
				testcharsTable[nbTurn] = tempTable;
				nbTurn++;
				System.out.println(tempTable);
			}
			tempTable[j] = 'A';
		}*/
		
		
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
		this.TENTATIVE_DE_SOUMMISSION += 1;
		return this.cleChoisieStr == cle.toCharArray();
	}
	
	public boolean soumettreCle(int cle)
	{
		return this.cleChoisieNum == cle;
	}
}
