import java.awt.*;

/**
 * classe utiliser pour générer un code barre
 * 
 * @author Mahot Antoine 
 * @version 26/10/2017
 */
public class CodeBarre {
	// variables d'instance - remplacez l'exemple qui suit par le votre
	private String entree, sortie;
	private int bordX, bordH, hauteur, largeur;
	
	private String[] T = {"11011001100","11001101100","11001100110","10010011000","10010001100","10001001100",
			"10011001000","10011000100","10001100100","11001001000","11001000100","11000100100",
			"10110011100","10011011100","10011001110","10111001100","10011101100","10011100110",
			"11001110010","11001011100","11001001110","11011100100","11001110100","11101101110",
			"11101001100","11100101100","11100100110","11101100100","11100110100","11100110010",
			"11011011000","11011000110","11000110110","10100011000","10001011000","10001000110",
			"10110001000","10001101000","10001100010","11010001000","11000101000","11000100010",
			"10110111000","10110001110","10001101110","10111011000","10111000110","10001110110",
			"11101110110","11010001110","11000101110","11011101000","11011100010","11011101110",
			"11101011000","11101000110","11100010110","11101101000","11101100010","11100011010",
			"11101111010","11001000010","11110001010","10100110000","10100001100","10010110000",
			"10010000110","10000101100","10000100110","10110010000","10110000100","10011010000",
			"10011000010","10000110100","10000110010","11000010010","11001010000","11101111010",
			"11000010100","10001111010","10100111100","10010111100","10010011110","10111100100",
			"10011110100","10011110010","11110100100","11110010100","11110010010","11011011110",
			"11011110110","11110110110","10101111000","10100011110","10001011110","10111101000",
			"10111100010","11110101000","11110100010","10111011110","10111101110","11101011110",
			"11110101110","11010000100","11010010000","11010011100","1100011101011","11010111000"};

	/**
	 * Constructeur d"objets de classe codeBarre
	 */
	public CodeBarre() {
		// initialisation des variables d"instance
		entree = "00000000000000";
		bordX = 20;
		bordH = 20;
		largeur = 2;
		hauteur = 80;
		initialiser();
	}

	public CodeBarre(String en) {
		// initialisation des variables d"instance
		entree = en;
		bordX = 20;
		bordH = 20;
		largeur = 2;
		hauteur = 80;
	}

	public CodeBarre(String en, int bx, int bh, int ha, int la) {
		// initialisation des variables d"instance
		entree = en;
		bordX = bx;
		bordH = bh;
		hauteur = ha;
		largeur = la;
		initialiser();
	}

	public void modifie(String nouvNb) {
		entree = nouvNb;
		initialiser();
	}

	public void initialiser() 
	{
		sortie = convertir(entree);
	}

	public boolean verifier(String en) 
	{ 
		return true; 
	}

	public String convertir(String en) {
		String codeDebut = "11010011100";
		String codeFin = "1100011101011";
		String code = "";
		String chiffre, control = "";
		int noChiffre, noControl = 0;
		int prmLength = 14;
		int acc = 1;

		//code && character control
		for(int no = 0; no < prmLength-1; no+=2, acc++) {
			chiffre = en.substring(no, no+2);
			noChiffre = Integer.parseInt(chiffre);
			code = code + T[noChiffre];
			if(no == 0)
				noControl = (noChiffre + 105);
			else
				noControl += (noChiffre*acc);
		}
		noControl %= 103;

		// fabrication du code de 95 bits
		control = T[noControl];
		return codeDebut + code + control + codeFin;
	}

	public void dessine(Graphics g) {
		g.setColor(Color.BLACK);
		for(int i=0; i<112; i++) {
			if(sortie.substring(i,i+1).equals("1"))
					g.fillRect(bordX+i*largeur,bordH,largeur,hauteur-hauteur/10);
		}
		g.drawString(entree.substring(0,1),3*bordX/2,bordH+hauteur+hauteur/20);
		for(int i=1; i<entree.length(); i++)
			g.drawString(entree.substring(i,i+1),5*bordX/3+7*i*largeur,bordH+hauteur+hauteur/20);
	}   
}