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

	/**
	 * Tests a "Sunny day case" of 4 quarters and correct return value.
	 */
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

	/**
	 * Tests whether inserting without returning should return false.
	 */
	@Test
	public void WhenInsertingWithoutReturningShouldReturnFalse() {

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

	/**
	 * Less than 85 and dispense should return false
	 */
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

	/**
	 * Testing variable types of coins should return true.
	 */
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

	/**
	 * Insert a LARGE number of coins and correct change should return true
	 */
	@Test
	public void WhenDepositingAnyAmountAboveSodaPriceShouldReturnTrue(){
		
		this.machine = new DrinkMachine();
		ArrayList<Action> currentTrace = new ArrayList<Action>();
		
		for(int i = 0; i<100; i++){
			currentTrace.add(Action.DEPOSITDIME);
			currentTrace.add(Action.DEPOSITNICKEL);
		}
		
		currentTrace.add(Action.DISPENSE);
		
		for(int j = 0; j<282; j++){
			currentTrace.add(Action.RETURNNICKEL);
		}
		
		assertTrue(this.machine.performActions(currentTrace));
		
		
	}


	/**
	 * Tests exact amount with return should return false.
	 */
	@Test
	public void WhenExactAmountIsGivenReturnChangeShouldReturnFalse() {

		this.machine = new DrinkMachine();
		ArrayList<Action> currentTrace = new ArrayList<Action>();

		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DEPOSITDIME);
		currentTrace.add(Action.DEPOSITQUARTER);
		currentTrace.add(Action.DISPENSE);
		currentTrace.add(Action.RETURNDIME);

		assertFalse(this.machine.performActions(currentTrace));
	}

	/**
	 * Return is done without insert should return false.
	 */
	@Test
	public void WhenReturnIsDoneWithoutInsertShouldReturnFalse() {
		
		this.machine = new DrinkMachine();
		ArrayList<Action> currentTrace = new ArrayList<Action>();
		
		currentTrace.add(Action.RETURNDIME);
		
		assertFalse(this.machine.performActions(currentTrace));
	}


}
