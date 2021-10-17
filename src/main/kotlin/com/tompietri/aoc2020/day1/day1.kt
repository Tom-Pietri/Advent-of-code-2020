package com.tompietri.aoc2020.day1

fun day1FirstSolution(input: List<Int>): Int {
    for(i in 0 until input.size - 1) {
       for(j in i until input.size) {
           if(input[i] + input[j] == 2020) {
               return input[i] * input[j]
           }
       }
    }
    return -1
}

fun day1SecondSolution(input: List<Int>) : Int {
    for(i in 0 until input.size - 2) {
        for(j in i until input.size - 1) {
            for(k in j until input.size) {
                if(input[i] + input[j] + input[k] == 2020) {
                    return input[i] * input[j] * input[k]
                }
            }
        }
    }
    return -1
}
