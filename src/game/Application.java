package game;

import game.engine.displays.Display;
import game.engine.positions.FancyGroundFactory;
import game.engine.positions.GameMap;
import game.engine.positions.Location;
import game.engine.positions.World;
import game.actions.siteofgrace.ActivateSiteOfLostGrace;
import game.characters.enemies.boss.GodrickTheGrafted;
import game.characters.players.Player;
import game.characters.players.archetype.*;
import game.environments.*;
import game.items.GoldenSeed;
import game.items.rune.GoldenRune;
import game.trader.FingerReaderEnia;
import game.trader.MerchantKale;
import game.utils.FancyMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Ziheng Liao, Harshath Muruganantham, Ho Seng
 *
 */
public class Application {

	/**
	 * The main method to start the game.
	 * @param args args
	 */
	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Graveyard(), new GustOfWind(), new PuddleOfWater(), new SiteOfLostGrace(), new Cliff(), new Barrack(), new SummonSign(), new Cage());

		List<String> limgraveMap = Arrays.asList(
				"......................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................=...............................................",
				"........++++......................###___###................................",
				"........+++++++...................________#................................",
				"..........+++.....................#________................................",
				"............+++...................#_______#................................",
				".............+....................###___###................................",
				"............++......................#___#..................................",
				"..............+...................=........................................",
				"..............++.................................................=.........",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++.............................................."
		);

		List<String> stormveilMap = Arrays.asList(
				"...........................................................................",
				"..................<...............<........................................",
				"...........................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++"
		);

		List<String> roundtableMap = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######"
		);

		List<String> bossRoomMap = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				"..=......................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++"
		);



		GameMap limgrave = new GameMap(groundFactory, limgraveMap);
		GameMap stormveil = new GameMap(groundFactory, stormveilMap);
		GameMap roundtable = new GameMap(groundFactory, roundtableMap);
		GameMap bossRoom = new GameMap(groundFactory, bossRoomMap);
		world.addGameMap(limgrave);
		world.addGameMap(stormveil);
		world.addGameMap(roundtable);
		world.addGameMap(bossRoom);
		limgrave.at(37,13).setGround(new GoldenFogDoor(stormveil, stormveil.at(39,22), "Stormveil Castle"));
		limgrave.at(39,13).setGround(new GoldenFogDoor(roundtable, roundtable.at(9, 10), "Roundtable Hold"));

		stormveil.at(39,22).setGround(new GoldenFogDoor(limgrave, limgrave.at(37, 13), "Limgrave"));
		stormveil.at(35,22).setGround(new GoldenFogDoor(bossRoom, bossRoom.at( 0, 3), "Godrick the Grafted"));

		roundtable.at(9,10).setGround(new GoldenFogDoor(limgrave, limgrave.at(39, 13), "Limgrave"));
		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		
		String selection;
		Scanner sel = new Scanner(System.in);

		while (true){
			try {
				System.out.println("Select your role:\na: Astrologer\nb: Bandit\ns: Samurai\nw: Wretch");
				selection = sel.nextLine();
				if (!selection.equals("b") && !selection.equals("s") && !selection.equals("w") && !selection.equals("a")) {
					System.out.println("Selection not b,s,w or a");
				} else {
					break;
				}
			} catch (Exception exception) {
				System.out.println("Invalid input");
			}
		}

		Archetype job = null;

		switch (selection) {
			case "s":
				job = new Samurai();
				break;
			case "b":
				job = new Bandit();
				break;
			case "w":
				job = new Wretch();
				break;
			case "a":
				job = new Astrologer();
				break;
			default:
				System.out.println("Selection not in range");
		}
		Player player = new Player("Tarnished", '@', job, limgrave);
		limgrave.at(40, 12).addActor(MerchantKale.getInstance());

		FingerReaderEnia enia = new FingerReaderEnia();
		roundtable.at(9, 1).addActor(enia);

		bossRoom.at(10,4).addActor(new GodrickTheGrafted(bossRoom.at(10,4)));


		world.addPlayer(player, limgrave.at(36, 10));
		Location location = limgrave.at(38,11);
		SiteOfLostGrace firstStep = new SiteOfLostGrace("First Step", location);
		location.setGround(firstStep);
		player.setLastRestedSiteLocation(location);
		player.setLastRestedSiteMap(limgrave);
		ActivateSiteOfLostGrace activateSiteOfLostGrace = new ActivateSiteOfLostGrace(firstStep);
		activateSiteOfLostGrace.execute(player,limgrave);

		stormveil.at(37,22).setGround(new SiteOfLostGrace("Stormveil Main Gate",stormveil.at(37,22)));
		roundtable.at(9,5).setGround(new SiteOfLostGrace("Table of Lost Grace",roundtable.at(9,5)));

		limgrave.at(2,0).setGround(new Graveyard());
		limgrave.at(3,0).setGround(new Graveyard());
		limgrave.at(4,0).setGround(new Graveyard());
		limgrave.at(5,0).setGround(new Graveyard());
		limgrave.at(2,2).setGround(new Graveyard());
		limgrave.at(3,2).setGround(new Graveyard());
		limgrave.at(4,2).setGround(new Graveyard());
		limgrave.at(5,2).setGround(new Graveyard());
		limgrave.at(46,12).setGround(new Graveyard());
		limgrave.at(47,12).setGround(new Graveyard());
		limgrave.at(48,12).setGround(new Graveyard());
		limgrave.at(49,12).setGround(new Graveyard());
		limgrave.at(46,10).setGround(new Graveyard());
		limgrave.at(47,10).setGround(new Graveyard());
		limgrave.at(48,10).setGround(new Graveyard());
		limgrave.at(49,10).setGround(new Graveyard());

		limgrave.at(54,0).setGround(new PuddleOfWater());
		limgrave.at(55,0).setGround(new PuddleOfWater());
		limgrave.at(56,0).setGround(new PuddleOfWater());
		limgrave.at(57,0).setGround(new PuddleOfWater());
		limgrave.at(58,0).setGround(new PuddleOfWater());
		limgrave.at(54,1).setGround(new PuddleOfWater());
		limgrave.at(55,1).setGround(new PuddleOfWater());
		limgrave.at(56,1).setGround(new PuddleOfWater());
		limgrave.at(57,1).setGround(new PuddleOfWater());
		limgrave.at(58,1).setGround(new PuddleOfWater());
		limgrave.at(54,2).setGround(new PuddleOfWater());
		limgrave.at(55,2).setGround(new PuddleOfWater());
		limgrave.at(56,2).setGround(new PuddleOfWater());
		limgrave.at(57,2).setGround(new PuddleOfWater());
		limgrave.at(58,2).setGround(new PuddleOfWater());
		limgrave.at(54,3).setGround(new PuddleOfWater());
		limgrave.at(55,3).setGround(new PuddleOfWater());
		limgrave.at(56,3).setGround(new PuddleOfWater());
		limgrave.at(57,3).setGround(new PuddleOfWater());
		limgrave.at(58,3).setGround(new PuddleOfWater());
		limgrave.at(0,12).setGround(new PuddleOfWater());
		limgrave.at(1,12).setGround(new PuddleOfWater());
		limgrave.at(2,12).setGround(new PuddleOfWater());
		limgrave.at(3,12).setGround(new PuddleOfWater());
		limgrave.at(0,13).setGround(new PuddleOfWater());
		limgrave.at(1,13).setGround(new PuddleOfWater());
		limgrave.at(2,13).setGround(new PuddleOfWater());
		limgrave.at(3,13).setGround(new PuddleOfWater());
		limgrave.at(0,14).setGround(new PuddleOfWater());
		limgrave.at(1,14).setGround(new PuddleOfWater());
		limgrave.at(2,14).setGround(new PuddleOfWater());
		limgrave.at(3,14).setGround(new PuddleOfWater());

		limgrave.at(20,20).setGround(new GustOfWind());
		limgrave.at(21,20).setGround(new GustOfWind());
		limgrave.at(20,21).setGround(new GustOfWind());
		limgrave.at(21,21).setGround(new GustOfWind());
		limgrave.at(53,18).setGround(new GustOfWind());
		limgrave.at(54,18).setGround(new GustOfWind());
		limgrave.at(53,19).setGround(new GustOfWind());
		limgrave.at(54,19).setGround(new GustOfWind());

		limgrave.at(26,2).addItem(new GoldenRune());
		limgrave.at(69,19).addItem(new GoldenRune());
		limgrave.at(73,10).addItem(new GoldenRune());
		limgrave.at(3,21).addItem(new GoldenRune());
		stormveil.at(74,0).addItem(new GoldenRune());
		stormveil.at(0,3).addItem(new GoldenRune());
		stormveil.at(8,9).addItem(new GoldenRune());
		stormveil.at(74,9).addItem(new GoldenRune());
		stormveil.at(32,21).addItem(new GoldenRune());

		roundtable.at(3,4).addItem(new GoldenSeed());
		roundtable.at(12,2).addItem(new GoldenSeed());
		limgrave.at(32,4).addItem(new GoldenSeed());
		limgrave.at(10,22).addItem(new GoldenSeed());
		world.run();
	}
}
