package gameEngine;

import java.util.ArrayList;

import building.BuildingAbstract;

public class GameData {
	// Base
	public static ArrayList<BuildingAbstract> baseArray = new ArrayList<BuildingAbstract>();
	/*
	 * [base, base, base ...]
	 * Taille : Nombre de base joueur dans le plateau de jeu
	 * O = neutre, 1 = joueur, 2 = m�chant
	 */
	
	// Graphic
	public static ArrayList<Integer> lineSelect = new ArrayList<Integer>();	
	/*
	 * [flag de dessin, x1 (point d�part), y1 (point d�part), x2 (point arriv�), y2 (point arriv�)]
	 */
}
