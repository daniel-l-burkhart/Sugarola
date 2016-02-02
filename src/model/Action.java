package model;

/**
 * An enumerator that represents all acitons of the vending machine.
 * 
 * @author danielburkhart
 *
 */
public enum Action {

	/**
	 * Deposits 25 cents to the vending machine.
	 */
	DEPOSITQUARTER,

	/**
	 * Deposits 5 cents to the vending machine.
	 */
	DEPOSITNICKEL,

	/**
	 * Deposits 10 cents to the vending machine.
	 */
	DEPOSITDIME,

	/**
	 * Dispenses a can of sugarola.
	 */
	DISPENSE,

	/**
	 * Represents a quarter returned from the vending machine.
	 */
	RETURNQUARTER,

	/**
	 * Represents a nickel returned from the vending machine.
	 */
	RETURNNICKEL,

	/**
	 * Represents a dime returned from the vending machine.
	 */
	RETURNDIME

}
