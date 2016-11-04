import java.util.Random;
import java.util.Scanner;

/*
 * UIN 4737
 * COP 3003
 * 9/18/2016
 */

public class lab3 {
	public static Package load_a_package(Scanner scanner) {
		Package pack = null;
		int rand = (new Random()).nextInt(4);
		switch (rand) {
		case 0:
			pack = new Box();
			break;
		case 1:
			pack = new Letter();
			break;
		case 2:
			pack = new MetalCrate();
			break;
		case 3:
			pack = new WoodCrate();
			break;
		}
		pack.input(scanner);
		return pack;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		Package pack = null;
		for (int i = 0; i < 5; i++) {
			System.out.printf("\n**** package %d ****\n", i);
			pack = load_a_package(scanner);
			System.out.printf("The cost of this package is $%.2f\n", pack.cost());
		}
		scanner.close();
	}

	// Package interface defining methods "cost" and "input" whose
	// implementations will be used to
	// return cost and take input
}

interface Package {

	double cost();

	void input(Scanner scanner);

}

// input() prompts the user to input the weight of their box and stores that
// value
// into weight. cost() returns the total cost of the transaction which is the
// weight
// multiplied by boxUnitCost
class Box implements Package {

	final private double boxUnitCost = 1.2;// cost of shipping a 1 pound box in
											// dollars
	private double weight;// weight of the box

	// returns total cost of shipping
	@Override
	public double cost() {
		return weight * boxUnitCost;
	}

	// sets weight to user's input value
	@Override
	public void input(Scanner scanner) {
		System.out.print("Please input the weight for the box (lbs): ");
		weight = scanner.nextDouble();
	}

}

// input() prompts the user to input the number of letter they have to ship and
// stores that value
// into pages. cost() returns the total cost of the transaction which is the
// number of pages
// multiplied by letterUnitCost
class Letter implements Package {

	final private double letterUnitCost = 5; // Cost to ship 1 letter in cents
	private int pages; // number of pages

	// returns the total cost of shipping
	@Override
	public double cost() {
		return pages * letterUnitCost * .01;
	}

	// sets the number of pages to the user's input
	@Override
	public void input(Scanner scanner) {
		System.out.print("Please input the number of pages for the letter (pgs): ");
		pages = scanner.nextInt();
	}

}

// Sets the weight to a user's input value
abstract class Crate implements Package {

	private double weight; // weight of the crate
	private double unitCost; //unit cost of type of crate
	private String crateType = getClass().getSimpleName();// what type of crate
															// it is
	//All Crate subclasses must have a cost method
	public abstract double cost();

	// takes in the user's input weight of the type of crate and sets "weight"
	// to that input
	public void input(Scanner scanner) {
		System.out.print("Please input the weight for the " + crateType + " (lbs): ");
		weight = scanner.nextDouble();
	}

	// Used to get weight for shipping calculations by subclass
	public double getWeight() {
		return weight;
	}
	
	//set cost to ship 1lb
	public void setUnitCost(double unitCost){
		this.unitCost = unitCost;
	}
	
	//get cost to ship 1lb
	public double getUnitCost(){
		return unitCost;
	}

}

class MetalCrate extends Crate {
	
	//set cost to ship a pound
	public MetalCrate(){
		setUnitCost(1.3);
	}
	
	//return cost of shipping
	@Override
	public double cost() {
		return getUnitCost() * getWeight();
	}
}

class WoodCrate extends Crate {

	//set cost to ship a pound
	public WoodCrate(){
		setUnitCost(1.4);
	}
	
	//return cost of shipping
	@Override
	public double cost() {
		return getUnitCost() * getWeight();
	}
}