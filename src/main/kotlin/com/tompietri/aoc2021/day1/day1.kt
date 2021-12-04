package com.tompietri.aoc2021.day1

fun day1FirstSolution(input: List<Int>) = input.zipWithNext().count { it.first < it.second }

fun day1SecondSolution(input: List<Int>) = day1FirstSolution(input.windowed(3).map { it.sum() })
