/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Action;
import model.DrinkMachine;

/**
 * @author danielburkhart
 *
 */
public class WhenTestingSugarolaMachine {

	private DrinkMachine machine;

	@Test
	public void WhenTestingFourQuartersShouldReturnTrue() {

		this.machine = new DrinkMachine();

		ArrayList<Action> currentTrace = new ArrayList<Action>();
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DISPENSE);
		currentTrace.add(Action.RETURNDIME);
		currentTrace.add(Action.RETURNNICKEL);

		assertTrue(this.machine.performActions(currentTrace));

	}

	@Test
	public void WhenDispensingWithoutReturningShouldReturnFalse() {

		this.machine = new DrinkMachine();

		ArrayList<Action> currentTrace = new ArrayList<Action>();

		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DISPENSE);
		currentTrace.add(Action.DEPOSITQUARTER);

		assertFalse(this.machine.performActions(currentTrace));

	}

	@Test
	public void WhenInsertingLessThan85ShouldReturnFalse() {
		this.machine = new DrinkMachine();

		ArrayList<Action> currentTrace = new ArrayList<Action>();
		currentTrace.add(Action.DEPOSITQUARTER);

		currentTrace.add(Action.DEPOSITQUARTER);

		currentTrace.add(Action.DEPOSITQUARTER);

		currentTrace.add(Action.DISPENSE);

		assertFalse(this.machine.performActions(currentTrace));
	}

	@Test
	public void WhenTestingVariableCoinTypesShouldReturnTrue() {

		this.machine = new DrinkMachine();

		ArrayList<Action> currentTrace = new ArrayList<Action>();
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITDIME);
		currentTrace.add(Action.DEPOSITDIME);
		currentTrace.add(Action.DEPOSITNICKEL);
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DISPENSE);
		currentTrace.add(Action.RETURNDIME);
		currentTrace.add(Action.RETURNNICKEL);

		assertTrue(this.machine.performActions(currentTrace));

	}

	@Test
	public void WhenOverIsGivenAndDispensedButNoChangeIsGivenShouldReturnFalse() {

		this.machine = new DrinkMachine();

		ArrayList<Action> currentTrace = new ArrayList<Action>();

		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITDIME);
		currentTrace.add(Action.DEPOSITDIME);
		currentTrace.add(Action.DEPOSITNICKEL);
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITQUARTER);

		currentTrace.add(Action.DISPENSE);

		assertFalse(this.machine.performActions(currentTrace));
		System.out.println("\n");

	}

	@Test
	public void WhenGivingExactAmountShouldReturnNothing() {
		
		this.machine = new DrinkMachine();

		ArrayList<Action> currentTrace = new ArrayList<Action>();

		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITDIME);
		currentTrace.add(Action.DEPOSITDIME);
		currentTrace.add(Action.DEPOSITDIME);
		currentTrace.add(Action.DEPOSITDIME);
		currentTrace.add(Action.DEPOSITDIME);
		currentTrace.add(Action.DEPOSITDIME);
		currentTrace.add(Action.DISPENSE);

		
		assertTrue(this.machine.performActions(currentTrace));
	}

	private void printOutPossible() {
		System.out.println("Current Actions Now");
		for (Action action : this.machine.getActions()) {
			System.out.println(action);
		}
		System.out.println(" ");

	}

}
