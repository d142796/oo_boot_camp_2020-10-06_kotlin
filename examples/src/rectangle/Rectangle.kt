/*
 * Copyright (c) 2020 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

package rectangle

// Understands a four-sided polygon with sides at right angles
class Rectangle(length: Number, width: Number) {
    private val length = length.toDouble()
    private val width = width.toDouble()

    init {
        require(length.toDouble() > 0 && width.toDouble() > 0)
        { "Each dimension must be a positive number" }
    }

    fun area() = length * width
    val area get() = area()

    fun perimeter() = 2 * (length + width)
    val perimeter get() = perimeter()
}