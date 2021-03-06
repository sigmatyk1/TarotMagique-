package com.example.tarotmagique;

import java.io.Serializable;
import java.util.ArrayList;

public class Joueur implements Serializable{

	private static int count = 0;
	private int idJoueur;
	private String nomJoueur;
	private Carte carteActuelle;
	private ArrayList<Carte> atouts;
	private ArrayList<Integer> listePouvoirs;
	private ArrayList<Log> historique;
	private boolean dame;
	private boolean excuse; // Indique si le joueur à l'excuse
	private String regleCartePiochee; // Indique ce que l'on doit faire ce tour

	public Joueur (String nomJoueur) {
		this.setIdJoueur(count++);
		this.idJoueur++;
		this.nomJoueur = nomJoueur;
		this.atouts = new ArrayList<Carte>();
		this.listePouvoirs = new ArrayList<Integer>();
		this.historique = new ArrayList<Log>();
		this.dame = false;
		this.excuse = false;
	}

	// Boolean pour le verre du Roi si on l'augmente
	public boolean piocher(Carte cartePiochee) {
		int carte;
		boolean roi = false;
		this.carteActuelle = cartePiochee;
		this.historique.add(new Log(this.carteActuelle));
		carte = cartePiochee.getNumeroCarte();

		if (carte == 77) {
			this.regleCartePiochee = this.carteActuelle.toString() + "\n"
					+ "Ne dévoile cette carte que lors de son utilisation : "
					+ "tu peux désormais utiliser l'excuse à n'importe quel moment pour annuler et"
					+ " contrer n'importe quel gage ou quantité de liquide à injérer (usage unique)";
			this.excuse = true;
		} else if (carte > 56 && carte < 76) {
			this.regleCartePiochee = this.carteActuelle.toString() + "\n"
					+ "Ne dévoile pas cette carte, elle te servira lors d'un duel !";
			this.atouts.add(new Carte(carte));

		} else if (carte == 56) {
			// Le petit
			this.regleCartePiochee = this.carteActuelle.toString() + "\n"
					+ "Le joueur que tu choisis finit son verre d'un trait !";

		} else if (carte == 76) {
			// Le 21
			this.regleCartePiochee = this.carteActuelle.toString() + "\n"
					+ "Tous les joueurs à l'exception de toi finissent leur(s) verre(s) !";

		} else if (carte == 0 || carte == 14 || carte == 28 || carte == 42) {
			// As
			this.regleCartePiochee = this.carteActuelle.toString() + "\n" + "Finis ton verre cul sec ! Pas de bol !";

		} else if (carte == 1 || carte == 15 || carte == 29 || carte == 43) {
			// Deux
			this.regleCartePiochee = this.carteActuelle.toString() + "\n"
					+ "Le sexe opposé boit 2 gorgées (si les joueurs sont du même sexe, buvez tous 2 gorgées)";

		} else if (carte == 2 || carte == 16 || carte == 30 || carte == 44) {
			// Trois : dynamite
			this.regleCartePiochee = this.carteActuelle.toString() + "\n" + "Dynamite";
			// A voir plus tard pour la gestion dyhnamite

		} else if (carte == 3 || carte == 17 || carte == 31 || carte == 45) {
			// Quatre : gage
			this.regleCartePiochee = this.carteActuelle.toString() + "\n"
					+ "Donne un gage à qui tu veux, le gage doit-être ciblé et impliquer de l'alcool";

		} else if (carte == 4 || carte == 18 || carte == 32 || carte == 46) {
			// Cinq : Never say no to panda
			this.regleCartePiochee = this.carteActuelle.toString() + "\n"
					+ "Never say no to Panda ! Personne n'a le droit de te dire no (peu importe la langue utilisée)";
			this.majListePouvoirs(0);

		} else if (carte == 5 || carte == 19 || carte == 33 || carte == 47) {
			// Six : Caribou !
			this.regleCartePiochee = this.carteActuelle.toString() + "\n"
					+ "Vous êtes le caribou, laissez vous guider par l'appel de la nature !";
			this.majListePouvoirs(1);

		} else if (carte == 6 || carte == 20 || carte == 34 || carte == 48) {
			// Sept : Freeze
			this.regleCartePiochee = this.carteActuelle.toString() + "\n"
					+ "Vous êtes le nouveau Mr. Freeze, dîtes 'FREEZE' quand bon vous semble, le premier à bouger boit une gorgée";
			this.majListePouvoirs(2);

		} else if (carte == 7 || carte == 21 || carte == 35 || carte == 49) {
			// Huit : Inventer une règle
			this.regleCartePiochee = this.carteActuelle.toString() + "\n"
					+ "Inventez une nouvelle règle valable pour toute la partie";

		} else if (carte == 8 || carte == 22 || carte == 36 || carte == 50) {
			// Neuf : J'ai dans ma valise
			this.regleCartePiochee = this.carteActuelle.toString() + "\n" + "Qu'avez vous dans votre valise ?";

		} else if (carte == 9 || carte == 23 || carte == 37 || carte == 51) {
			// Dix : Babylonien
			this.regleCartePiochee = this.carteActuelle.toString() + "\n"
					+ "Faîtes un mot en équipe, commencez par dire la première lettre";

		} else if (carte == 10 || carte == 24 || carte == 38 || carte == 52) {
			// Valet : Maître des pouces
			this.regleCartePiochee = this.carteActuelle.toString() + "\n" + "Vous êtes le Maître des pouces";
			this.majListePouvoirs(3);

		} else if (carte == 11 || carte == 25 || carte == 39 || carte == 53) {
			// Cavalier : Snake eyes
			this.regleCartePiochee = this.carteActuelle.toString() + "\n" + "Snake eyes !";
			this.majListePouvoirs(4);

		} else if (carte == 12 || carte == 26 || carte == 40 || carte == 54) {
			// Dame : Dame
			this.regleCartePiochee = this.carteActuelle.toString() + "\n" + "Vous êtes dame : Vodka, connecting people";
			this.majListePouvoirs(5);
			this.dame = true;

		} else if (carte == 13 || carte == 27 || carte == 41 || carte == 55) {
			// Roi : verre du Roi
			this.regleCartePiochee = this.carteActuelle.toString() + "\n" + "Pour le Roi !!";
			roi = true;
		} else {
			this.regleCartePiochee = "AHAHAHAHA";
		}

		// Test affichage
		System.out.println(this.regleCartePiochee);

		return roi;
	}


	public void regarderAtouts() {
		for (int i = 0; i < this.atouts.size(); i++) {
			System.out.println(this.atouts.get(i).toString());
		}
	}

	public void duel() {
	}


	public void utiliserExcuse() {
		this.regleCartePiochee = "Excuse utilisée ! Que la force soit avec toi !";
		this.excuse = false;
	}

	public void majListePouvoirs(int typePouvoir) {
		for (int i = 0; i < this.listePouvoirs.size(); i++) {
			if (this.listePouvoirs.get(i) == typePouvoir) {
				this.listePouvoirs.remove(i);
			}
		}
		this.listePouvoirs.add(typePouvoir);
	}

	public ArrayList<Integer> getListePouvoirs() {
		return this.listePouvoirs;
	}

	public ArrayList<Carte> getAtouts() {
		return this.atouts;
	}
	
	public ArrayList<Log> getHistorique() {
		return this.historique;
	}


	public int getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(int count) {
		this.idJoueur = count;
	}

	public void setNomJoueur(int nomJoueur) {
		this.nomJoueur += " " + nomJoueur;
	}

	@Override
	public String toString() {
		return this.nomJoueur ;
	}
}
