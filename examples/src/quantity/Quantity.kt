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

    private fun equals(other: Quantity) = (this.amount - convertedAmount(other)).absoluteValue < DELTA

    override fun hashCode() = unit.hashCode(amount)

    private fun convertedAmount(other: Quantity) = this.unit.convertedAmount(other.amount, other.unit)
}