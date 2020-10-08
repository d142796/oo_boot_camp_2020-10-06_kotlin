/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package quantity

import kotlin.math.absoluteValue

// Understands a specific measurement
open class Quantity internal constructor(amount: Number, private val unit: Unit) {
    private val amount = amount.toDouble()

    companion object {
        internal const val DELTA = 0.00000001
    }

    override fun equals(other: Any?) = other is Quantity && this.equals(other)

    private fun equals(other: Quantity) =
        this.isCompatible(other) &&
                (this.amount - convertedAmount(other)).absoluteValue < DELTA

    private fun isCompatible(other: Quantity) = this.unit.isCompatible(other.unit)

    override fun hashCode() = unit.hashCode(amount)

    private fun convertedAmount(other: Quantity) = this.unit.convertedAmount(other.amount, other.unit)

    operator fun unaryPlus() = this

    operator fun unaryMinus() = Quantity(-amount, unit)

    operator fun plus(other: Quantity) = Quantity(this.amount + convertedAmount(other), unit)

    operator fun minus(other: Quantity) = this + -other
}