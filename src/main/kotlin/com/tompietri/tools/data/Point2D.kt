package com.tompietri.tools.data

data class Point2D(val x: Int, val y: Int) : Comparable<Point2D> {
    override fun compareTo(other: Point2D): Int {
        if(this.y == other.y) {
            if (this.x < other.x) {
                return -1
            } else {
                return 1
            }
        } else if (this.y < other.y) {
            return -1
        } else {
            return 1
        }
    }
}
