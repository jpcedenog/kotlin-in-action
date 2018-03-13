package main;

import chapters.chapter2.main
/*
import chapters.chapter3
import chapters.chapter4
import chapters.chapter5
*/
import java.util.Scanner

fun main(args: Array<String>){
    println("Enter chapter: ")
    val sysin = Scanner(System.`in`)
    val chapter = sysin.nextLine()

    when(chapter.toInt()){
        2 -> chapters.chapter2.main(arrayOf("JP Cedeno"))
        /*
        3 -> chapters.chapter3.main("JP Cedeno")
        4 -> chapters.chapter4.main("JP Cedeno")
        5 -> chapters.chapter5.main("JP Cedeno")
        */
        else -> println("Unknown chapter!")
    }
}