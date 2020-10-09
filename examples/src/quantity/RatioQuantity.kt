/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package quantity

import kotlin.math.absoluteValue

// Understands a specific measurement
open class RatioQuantity internal constructor(amount: Number, private val unit: Unit) {
    private val amount = amount.toDouble()

    companion object {
        internal const val DELTA = 0.00000001
    }

    override fun equals(other: Any?) = other is RatioQuantity && this.equals(other)

    private fun equals(other: RatioQuantity) =
        this.isCompatible(other) &&
                (this.amount - convertedAmount(other)).absoluteValue < DELTA

    private fun isCompatible(other: RatioQuantity) = this.unit.isCompatible(other.unit)

    override fun hashCode() = unit.hashCode(amount)

    private fun convertedAmount(other: RatioQuantity) = this.unit.convertedAmount(other.amount, other.unit)

    operator fun unaryPlus() = this

    operator fun unaryMinus() = RatioQuantity(-amount, unit)

    operator fun plus(other: RatioQuantity) = RatioQuantity(this.amount + convertedAmount(other), unit)

    operator fun minus(other: RatioQuantity) = this + -other
}