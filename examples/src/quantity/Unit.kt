/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package quantity

// Understands a specific metric
class Unit {
    private val baseUnit: Unit
    private val baseUnitRatio: Double
    private val offset: Double

    companion object {
        private val teaspoon = Unit()
        private val tablespoon = Unit(3, teaspoon)
        private val ounce = Unit(2, tablespoon)
        private val cup = Unit(8, ounce)
        private val pint = Unit(2, cup)
        private val quart = Unit(2, pint)
        private val gallon = Unit(4, quart)

        private val inch = Unit()
        private val foot = Unit(12, inch)
        private val yard = Unit(3, foot)
        private val chain = Unit(22, yard)
        private val furlong = Unit(10, chain)
        private val mile = Unit(8, furlong)

        private val celsius = Unit()
        private val fahrenheit = Unit(5/9.0, 32, celsius)
        private val gasMark = Unit(125/9.0, -218.0/25, celsius)
        private val kelvin = Unit(1, 273.15, celsius)
        private val rankine = Unit(5/9.0, 491.67, celsius)

        val Number.teaspoons get() = RatioQuantity(this, teaspoon)
        val Number.tablespoons get() = RatioQuantity(this, tablespoon)
        val Number.ounces get() = RatioQuantity(this, ounce)
        val Number.cups get() = RatioQuantity(this, cup)
        val Number.pints get() = RatioQuantity(this, pint)
        val Number.quarts get() = RatioQuantity(this, quart)
        val Number.gallons get() = RatioQuantity(this, gallon)

        val Number.inches get() = RatioQuantity(this, inch)
        val Number.feet get() = RatioQuantity(this, foot)
        val Number.yards get() = RatioQuantity(this, yard)
        val Number.chains get() = RatioQuantity(this, chain)
        val Number.furlongs get() = RatioQuantity(this, furlong)
        val Number.miles get() = RatioQuantity(this, mile)

        val Number.celsius get() = IntervalQuantity(this, Unit.celsius)
        val Number.fahrenheit get() = IntervalQuantity(this, Unit.fahrenheit)
        val Number.gasMark get() = IntervalQuantity(this, Unit.gasMark)
        val Number.kelvin get() = IntervalQuantity(this, Unit.kelvin)
        val Number.rankine get() = IntervalQuantity(this, Unit.rankine)
    }

    private constructor() {
        baseUnit = this
        baseUnitRatio = 1.0
        offset = 0.0
    }

    private constructor(relativeRatio: Number, relativeUnit: Unit) :
            this(relativeRatio, 0.0, relativeUnit)

    private constructor(relativeRatio: Number, offset: Number, relativeUnit: Unit) {
        baseUnit = relativeUnit.baseUnit
        baseUnitRatio = relativeRatio.toDouble() * relativeUnit.baseUnitRatio
        this.offset = offset.toDouble()
    }

    internal fun convertedAmount(otherAmount: Double, otherUnit: Unit) =
        ((otherAmount - otherUnit.offset) * otherUnit.baseUnitRatio / this.baseUnitRatio + this.offset).also {
            require(this.isCompatible(otherUnit)) { "Incompatible unit types" }
        }

    internal fun hashCode(amount: Double) =
        ((amount - offset) * baseUnitRatio / IntervalQuantity.DELTA).toLong().hashCode()

    internal fun isCompatible(other: Unit) = this.baseUnit == other.baseUnit
}
