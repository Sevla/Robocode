package IFAL;
import robocode.*;
import robocode.WinEvent;
import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Nightmare - a robot by (Emmanuel Alves)
 */
public class Nightmare extends Robot
{
	int turnDirection = 1;
	/**
	 * run: Nightmare's default behavior
	 */
	public void run() {

		setColors(Color.black,Color.red,Color.blue); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			turnRight(15 * turnDirection);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		double dist = e.getDistance();
		double angle = e.getBearing();

		if (dist <= 200) {
			fire(3);
		}
		else if (dist <= 250) {
			fire(2);
		}
		else if (dist <= 300) {
			fire(1);
		}
		else if (dist <= 350) {
			fire(.5);
		}
		else {
			fire(.2);
		}
		
		if (angle >= 0) { //caso o ângulo seja maior do que 0
			turnDirection = 1; //ele gira em sentido horário
		} else {
			turnDirection = -1; //caso contrário, sentido anti-horário
		}
		
		turnRight(angle);//!modifiquei de turnRight para turnGunRight
		ahead(dist - 100); //calcula a distância pro inimigo e avança		
	}
	
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() >= 0) { // verifica o ângulo do oponente
			turnDirection = 1; // e toma a direção
		} else {
			turnDirection = -1;
		}
		turnRight(e.getBearing());
		ahead(40); // O robo avança pra se chocar com o adversário
	}
	
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		turnRight(90);
	}
	
	public void onWin(WinEvent e) {
		for (int i = 0; i < 50; i++) {
			turnRight(30);
			turnLeft(30);
		}
	}	
}
