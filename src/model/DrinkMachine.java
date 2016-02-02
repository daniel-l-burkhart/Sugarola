package model;

import java.util.ArrayList;
import java.util.Collections;

public class DrinkMachine {

	private ArrayList<Action> actionsPossible;

	private ArrayList<Action> quartersInserted;
	private ArrayList<Action> nickelsInserted;
	private ArrayList<Action> dimesInserted;
	private ArrayList<Action> dispensed;
	private ArrayList<Action> returnedNickel;
	private ArrayList<Action> returnedDime;
	private ArrayList<Action> returnedQuarter;

	private ArrayList<Action> actionsTaken;

	public DrinkMachine() {

		this.quartersInserted = new ArrayList<Action>();
		this.nickelsInserted = new ArrayList<Action>();
		this.dimesInserted = new ArrayList<Action>();
		this.dispensed = new ArrayList<Action>();
		this.returnedNickel = new ArrayList<Action>();
		this.returnedDime = new ArrayList<Action>();
		this.returnedQuarter = new ArrayList<Action>();

		this.actionsTaken = new ArrayList<Action>();

		/*
		 * You can insert 17 nickels You can insert 8 dimes and 1 nickel You can
		 * insert 3 quarters and one dime and one nickel
		 * 
		 * 1 Quarter = 2 dimes and 1 nickel = 5 nickels. 1 dime = 2 nickels
		 */

	}

	public DrinkMachine(ArrayList<Action> quarters, ArrayList<Action> dimes, ArrayList<Action> nickels,
			ArrayList<Action> dispensed, ArrayList<Action> returnNickels, ArrayList<Action> returnQuarters,
			ArrayList<Action> returnDimes) {

		this.quartersInserted = quarters;
		this.dimesInserted = dimes;
		this.nickelsInserted = nickels;
		this.dispensed = dispensed;
		this.returnedDime = returnDimes;
		this.returnedNickel = returnNickels;
		this.returnedQuarter = returnQuarters;
	}

	public ArrayList<Action> getActions() {

		this.actionsPossible = new ArrayList<Action>();

		this.actionsPossible.add(Action.DEPOSITQUARTER);
		this.actionsPossible.add(Action.DEPOSITNICKEL);
		this.actionsPossible.add(Action.DEPOSITDIME);

		if (this.calculateTotalValue() >= 85) {

			this.actionsPossible.remove(Action.DEPOSITDIME);
			this.actionsPossible.remove(Action.DEPOSITNICKEL);
			this.actionsPossible.remove(Action.DEPOSITQUARTER);
			this.actionsPossible.add(Action.DISPENSE);
			this.calculateChange();

		}

		return this.actionsPossible;
	}

	private void calculateChange() {

		int totalCredit = this.calculateTotalValue();
		int changeDue = totalCredit - 85;

		while (changeDue > 0) {
			if (changeDue >= 25) {
				this.actionsPossible.add(Action.RETURNQUARTER);
				changeDue -= 25;
			}
			if (changeDue >= 10) {
				this.actionsPossible.add(Action.RETURNDIME);
				changeDue -= 10;
			}
			if (changeDue >= 5) {
				this.actionsPossible.add(Action.RETURNNICKEL);
				changeDue -= 5;
			}
		}

	}

	// Find the number of quarters.

	public boolean performaAction(Action action) {

		switch (action) {

			case DEPOSITQUARTER:

				if (this.quartersInserted.size() > 4) {
					return false;
				}

				return true;

			case DEPOSITNICKEL:

				if (this.nickelsInserted.size() > 17) {
					return false;
				}

				return true;

			case DEPOSITDIME:

				if (this.dimesInserted.size() > 9) {
					return false;
				}

				return true;

			case DISPENSE:

				if (this.calculateTotalValue() < 85) {
					return false;
				}

				if (this.calculateTotalValue() > 85) {

					/*
					 * Fix this. Is not working.
					 */
					if (this.actionsTaken.contains(Action.RETURNDIME) == false
							|| this.actionsTaken.contains(Action.RETURNNICKEL) == false
							|| this.actionsTaken.contains(Action.RETURNQUARTER) == false) {
						return false;
					}

				}

				return true;

			case RETURNNICKEL:

				if (this.dispensed.size() < 1) {
					return false;
				}

				if (this.calculateTotalValue() <= 85) {
					return false;
				}
				return true;

			case RETURNDIME:

				if (this.dispensed.size() < 1) {
					return false;
				}

				if (this.calculateTotalValue() <= 85) {
					return false;
				}
				return true;

			case RETURNQUARTER:

				if (this.dispensed.size() < 1) {
					return false;
				}

				if (this.calculateTotalValue() <= 85) {
					return false;
				}
				return true;
		}

		return true;

	}

	private int calculateTotalValue() {

		int total = (this.quartersInserted.size() * 25) + (this.nickelsInserted.size() * 5)
				+ (this.dimesInserted.size() * 10);

		return total;
	}

	public boolean performActions(ArrayList<Action> trace) {

		boolean result = true;

		this.countAllCoins(trace);

		for (Action currAction : trace) {
			result = this.performaAction(currAction);
		}

		return result;

	}

	private void countAllCoins(ArrayList<Action> trace) {
		for (Action currAction : trace) {
			switch (currAction) {

				case DEPOSITQUARTER:
					this.quartersInserted.add(Action.DEPOSITQUARTER);
					this.actionsTaken.add(Action.DEPOSITQUARTER);

					break;
				case DEPOSITNICKEL:
					this.nickelsInserted.add(Action.DEPOSITNICKEL);
					this.actionsTaken.add(Action.DEPOSITNICKEL);

					break;
				case DEPOSITDIME:
					this.dimesInserted.add(Action.DEPOSITDIME);
					this.actionsTaken.add(Action.DEPOSITDIME);

					break;
				case DISPENSE:
					this.dispensed.add(Action.DISPENSE);
					this.actionsTaken.add(Action.DISPENSE);

					break;
				case RETURNNICKEL:
					this.returnedNickel.add(Action.RETURNNICKEL);
					this.actionsTaken.add(Action.RETURNNICKEL);

					break;
				case RETURNDIME:
					this.returnedDime.add(Action.RETURNDIME);
					this.actionsTaken.add(Action.RETURNDIME);

					break;
				case RETURNQUARTER:
					this.returnedQuarter.add(Action.RETURNQUARTER);
					this.actionsTaken.add(Action.RETURNQUARTER);

					break;
			}

		}
	}

}
