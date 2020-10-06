/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package probability

import kotlin.math.absoluteValue

// Understands the likelihood of something specific occurring
class Chance(likelihoodAsFraction: Number) {
    private val fraction = likelihoodAsFraction.toDouble()

    init {
        require(fraction in 0.0..1.0) { "Likelihood must be a fraction between 0.0 and 1.0, inclusive"}
    }

    companion object {
        private const val CERTAIN_FRACTION = 1.0
    }

    override fun equals(other: Any?) = other is Chance && this.equals(other)

    private fun equals(other: Chance) = this.fraction == other.fraction

    override fun hashCode() = fraction.hashCode()

    operator fun not() = Chance(CERTAIN_FRACTION - fraction)

    fun and(other: Chance) = Chance(this.fraction * other.fraction)
}