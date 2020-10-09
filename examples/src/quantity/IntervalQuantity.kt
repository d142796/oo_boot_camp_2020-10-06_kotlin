/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package quantity

import kotlin.math.absoluteValue

// Understands a specific measurement
open class IntervalQuantity internal constructor(amount: Number, private val unit: Unit) {
    private val amount = amount.toDouble()

    companion object {
        internal const val DELTA = 0.00000001
    }

    override fun equals(other: Any?) = other is IntervalQuantity && this.equals(other)

    private fun equals(other: IntervalQuantity) =
        this.isCompatible(other) &&
                (this.amount - convertedAmount(other)).absoluteValue < DELTA

    private fun isCompatible(other: IntervalQuantity) = this.unit.isCompatible(other.unit)

    override fun hashCode() = unit.hashCode(amount)

    private fun convertedAmount(other: IntervalQuantity) = this.unit.convertedAmount(other.amount, other.unit)
}