package model;

import java.util.ArrayList;

/**
 * Drink machine class
 * 
 * @author danielburkhart
 *
 */
public class DrinkMachine {

	public static final int NICKEL = 5;

	public static final int DIME = 10;

	public static final int QUARTER = 25;

	public static final int PRICE_OF_SUGAROLA = 85;

	private ArrayList<Action> actionsPossible;

	private ArrayList<Action> quartersInserted;
	private ArrayList<Action> nickelsInserted;
	private ArrayList<Action> dimesInserted;
	private ArrayList<Action> dispensed;
	private ArrayList<Action> returnedNickel;
	private ArrayList<Action> returnedDime;
	private ArrayList<Action> returnedQuarter;

	/**
	 * Constructor of vending machine.
	 */
	public DrinkMachine() {

		this.quartersInserted = new ArrayList<Action>();
		this.nickelsInserted = new ArrayList<Action>();
		this.dimesInserted = new ArrayList<Action>();
		this.dispensed = new ArrayList<Action>();
		this.returnedNickel = new ArrayList<Action>();
		this.returnedDime = new ArrayList<Action>();
		this.returnedQuarter = new ArrayList<Action>();

	}

	/**
	 * Gets the actions available at the current drink machine state.
	 * 
	 * @return An arrayList with all the actions.
	 */
	public ArrayList<Action> getActions() {

		this.actionsPossible = new ArrayList<Action>();

		this.actionsPossible.add(Action.DEPOSITQUARTER);
		this.actionsPossible.add(Action.DEPOSITNICKEL);
		this.actionsPossible.add(Action.DEPOSITDIME);

		if (this.calculateTotalValue() >= PRICE_OF_SUGAROLA) {

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
		int changeDue = totalCredit - PRICE_OF_SUGAROLA;

		while (changeDue > 0) {
			if (changeDue >= QUARTER) {
				this.actionsPossible.add(Action.RETURNQUARTER);
				changeDue -= QUARTER;
			}
			if (changeDue >= DIME) {
				this.actionsPossible.add(Action.RETURNDIME);
				changeDue -= DIME;
			}
			if (changeDue >= NICKEL) {
				this.actionsPossible.add(Action.RETURNNICKEL);
				changeDue -= NICKEL;
			}
		}

	}

	private boolean performaAction(Action action) {

		boolean result = true;

		if (!this.getActions().contains(action)) {
			result = false;
		}

		return result;

	}

	private int calculateTotalValue() {

		int total = (this.quartersInserted.size() * QUARTER) + (this.nickelsInserted.size() * NICKEL)
				+ (this.dimesInserted.size() * DIME);

		return total;
	}

	/**
	 * Performs actions on a list of actions
	 * 
	 * @param trace
	 *            The arrayList passed
	 * @return true if trace passes, false otherwise.
	 */
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
					break;

				case DEPOSITNICKEL:

					this.nickelsInserted.add(Action.DEPOSITNICKEL);
					break;

				case DEPOSITDIME:

					this.dimesInserted.add(Action.DEPOSITDIME);
					break;

				case DISPENSE:

					this.dispensed.add(Action.DISPENSE);
					break;

				case RETURNNICKEL:
					this.returnedNickel.add(Action.RETURNNICKEL);
					break;

				case RETURNDIME:

					this.returnedDime.add(Action.RETURNDIME);
					break;

				case RETURNQUARTER:

					this.returnedQuarter.add(Action.RETURNQUARTER);
					break;
			}

		}
	}

}
