/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package quantity

// Understands a specific measurement
class RatioQuantity internal constructor(amount: Number, unit: Unit)
    : IntervalQuantity(amount, unit) {

    operator fun unaryPlus() = this

    operator fun unaryMinus() = RatioQuantity(-amount, unit)

    operator fun plus(other: RatioQuantity) = RatioQuantity(this.amount + convertedAmount(other), unit)

    operator fun minus(other: RatioQuantity) = this + -other
}