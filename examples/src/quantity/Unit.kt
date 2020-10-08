/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package quantity

// Understands a specific metric
class Unit {
    private val baseUnitRatio: Double

    companion object {
        private val teaspoon = Unit()
        private val tablespoon = Unit(3, teaspoon)
        private val ounce = Unit(2, tablespoon)
        private val cup = Unit(8, ounce)
        private val pint = Unit(2, cup)
        private val quart = Unit(2, pint)
        private val gallon = Unit(4, quart)

        val Number.teaspoons get() = Quantity(this, teaspoon)
        val Number.tablespoons get() = Quantity(this, tablespoon)
        val Number.ounces get() = Quantity(this, ounce)
        val Number.cups get() = Quantity(this, cup)
        val Number.pints get() = Quantity(this, pint)
        val Number.quarts get() = Quantity(this, quart)
        val Number.gallons get() = Quantity(this, gallon)
    }

    private constructor() {
        baseUnitRatio = 1.0
    }

    private constructor(relativeRatio: Number, relativeUnit: Unit) {
        baseUnitRatio = relativeRatio.toDouble() * relativeUnit.baseUnitRatio
    }

    internal fun convertedAmount(otherAmount: Double, otherUnit: Unit) =
        otherAmount * otherUnit.baseUnitRatio / this.baseUnitRatio

    internal fun hashCode(amount: Double) = (amount * baseUnitRatio / Quantity.DELTA).toLong().hashCode()
}
