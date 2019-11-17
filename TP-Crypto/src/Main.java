import java.io.IOException;
import java.util.ArrayList;

/*
 * Auteur : Yanis Ouled Moussa, Justin Garcia
 * Version : 1.0
 * Fichier : Main.java - Classe principale du programme, elle permet de lancer les algorithmes développés pour le projet
 */

public class Main 
{
	public static void main(String[] args) throws IOException 
	{
		Server s = new Server("Shakespeare.txt");
		ArrayList<String> texteEncode = new ArrayList<String>();
		boolean isKeyNum = false;
		boolean isKeyPhrase = false;
		boolean isKeyDico = false;
		
		for (String string : s. obtenirFichierEncode())
		{
			texteEncode.add(string);
		}
		
		System.out.println("test");
		
		Decodeur decodeur = new Decodeur("english_quadgrams.txt");
		
		Resultat resultNum = decodeur.trouveCleNumerique(texteEncode, 50);
		
		System.out.println("test2");
		
		for(Object cle : resultNum.getCles())
		{
			if(s.soumettreCle((int)cle))
			{
				System.out.println("Cle : " + cle + " Algo : " + resultNum.getAlgo());
				isKeyNum = true;
			}
		}
		
		if(!isKeyNum)
		{
			ArrayList<Resultat> resultsPhrases = decodeur.trouveClePhrase(texteEncode, 200);
			int i = 0;
			
			for(Resultat result : resultsPhrases)
			{
				if(s.soumettreCle((String)result.getCles().get(i)))
				{
					System.out.println("Cle : " + result.getCles().get(i) + " Algo : " + result.getAlgo());
					isKeyPhrase = true;
				}
				i++;
				
				if(s.TENTATIVE_DE_SOUMMISSION < s.MAX_SOUMISSION)
				{
					break;
				}
			}
			
			if(!isKeyPhrase)
			{
				Resultat resultDico = decodeur.trouveDictionnaire(texteEncode, 850);
				
				for(Object cle : resultDico.getCles())
				{
					if(s.soumettreCle((String)cle))
					{
						System.out.println("Cle : " + cle + " Algo : " + resultDico.getAlgo());
						isKeyDico = true;
					}
				}
				
				if(!isKeyDico)
				{
					System.out.println("Aucune clé n'a été trouvée !");
				}
			}
		}
		
		
	}
}
