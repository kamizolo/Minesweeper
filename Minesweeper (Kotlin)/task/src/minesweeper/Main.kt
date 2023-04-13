package minesweeper
import kotlin.random.Random

fun main() {
    println("How many mines do you want on the field?")
    val numMines = readln().toInt()
    var mineInit = numMines
    val field = mutableListOf(
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".")
    )
    val mines = mutableListOf(
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".")
    )
    var minesMarked = 0
    var minesCorrect = 0
    var cellesExplored = 0
    var alive = true
    fun numberFill() {
        var count = 0
        //corners
        if(mines[0][0] != "X") {
            if (mines[0][1] == "X") count++
            if (mines[1][0] == "X") count++
            if (mines[1][1] == "X") count++
            if(count != 0) mines[0][0] = count.toString()

        }

        count = 0
        if(mines[8][8] != "X") {
            if (mines[8][7] == "X") count++
            if (mines[7][8] == "X") count++
            if (mines[7][7] == "X") count++
            if(count != 0) mines[8][8] = count.toString()
        }
        count = 0
        if(mines[8][0] != "X") {
            if (mines[8][1] == "X") count++
            if (mines[7][0] == "X") count++
            if (mines[7][1] == "X") count++
            if(count != 0) mines[8][0] = count.toString()
        }
        count = 0
       if(mines[0][8] != "X") {
            if (mines[1][8] == "X") count++
            if (mines[0][7] == "X") count++
            if (mines[1][7] == "X") count++
            if(count != 0) mines[0][8] = count.toString()
       }
        //mid
        count = 0
        for(x in 1..7){
            for(y in 1..7){
                if(mines[x][y] == "X") continue
                //top
                for(i in y-1..y+1){
                    if(mines[x-1][i] == "X") count++
                }
                //bottom
                for(i in y-1..y+1){
                    if(mines[x+1][i] == "X") count++
                }
                //sides
                if(mines[x][y-1] == "X") count++
                if(mines[x][y+1] == "X") count++

                if(count != 0) mines[x][y] = count.toString()
                count = 0
            }
        }

        //top
        for(x in 1..7){
            if(mines[0][x] == "X") continue
            if(mines[0][x-1] == "X") count++
            if(mines[0][x+1] == "X") count++
            if(mines[1][x-1] == "X") count++
            if(mines[1][x] == "X") count++
            if(mines[1][x+1] == "X") count++
            if(count != 0) mines[0][x] = count.toString()
            count = 0
        }
        //bottom
        for(x in 1..7){
            if(mines[8][x] == "X") continue
            if(mines[8][x-1] == "X") count++
            if(mines[8][x+1] == "X") count++
            if(mines[7][x-1] == "X") count++
            if(mines[7][x] == "X") count++
            if(mines[7][x+1] == "X") count++
            if(count != 0) mines[8][x] = count.toString()
            count = 0
        }
        //sides

        for(x in 1..7){
            if(mines[x][0] == "X") continue
            if(mines[x-1][0] == "X") count++
            if(mines[x+1][0] == "X") count++
            if(mines[x-1][1] == "X") count++
            if(mines[x][1] == "X") count++
            if(mines[x+1][1] == "X") count++
            if(count != 0) mines[x][0] = count.toString()
            count = 0
        }
        for(x in 1..7){
            if(mines[x][8] == "X") continue
            if(mines[x-1][8] == "X") count++
            if(mines[x+1][8] == "X") count++
            if(mines[x-1][7] == "X") count++
            if(mines[x][7] == "X") count++
            if(mines[x+1][7] == "X") count++
            if(count != 0) mines[x][8] = count.toString()
            count = 0
        }
    }
    fun mineSetup() {
        var x = 0
        var y = 0
        var count = 0
        while (mineInit >= 1) {
            x = Random.nextInt(0, 9)
            y = Random.nextInt(0, 9)

            if(mines[x][y] != "X"){
                mines[x][y] = "X"
                mineInit--
            }
            count++
        }
        //println(count)
    }
    fun printField() {
        var x = 1
        println(""" │123456789│
—│—————————│""")
        for(line in field){
            print("${x++}|")
            for(place in line) print(place)
            println("|")
        }
        println("—│—————————│")
    }
    fun printField2() {
        var x = 1
        println(""" │123456789│
—│—————————│""")
        for(line in mines){
            print("${x++}|")
            for(place in line) print(place)
            println("|")
        }
        println("—│—————————│")
    }
    fun mark(x: Int, y: Int) {
        if (field[x][y] == ".") {
            minesMarked++
            if (mines[x][y] == "X") minesCorrect++
            field[x][y] = "*"
        } else if (field[x][y] == "*") {
            minesMarked--
            if (mines[x][y] == "X") minesCorrect--
            field[x][y] = "."
        } else println("Cell not available!")
    }
    fun checkNext(x: Int, y: Int) {
        val c = mines[x][y].toCharArray()
        if(c[0] in '0'..'9') {
            field[x][y] = mines[x][y]
            return
        }else if (mines[x][y] != "."){
            return
        }
        mines[x][y] = "/"
        field[x][y] = "/"
        cellesExplored++
        //top
        if(x != 0) {
            checkNext(x - 1, y)
            if(y != 0) checkNext(x - 1, y - 1)
            if(y != 8) checkNext(x - 1, y + 1)
        }
        //bottom
        if(x != 8) {
            checkNext(x + 1, y)
            if(y != 0) checkNext(x + 1, y - 1)
            if(y != 8) checkNext(x + 1, y + 1)
        }
        //sides
        if(y != 0) {
            checkNext(x, y - 1)
        }
        if(y != 8) {
            checkNext(x, y + 1)
        }

    }
    fun explore(x: Int, y: Int) {
        if(mines[x][y] == "X"){
            alive = false
            println("You stepped on a mine and failed!")
        }else if(mines[x][y] == "/") {
            println("cell is already explored")
        }else if(mines[x][y] == "."){
            checkNext(x, y)
        }else if(mines[x][y].toInt() in 0..9) {
            field[x][y] = mines[x][y]
        }
    }

    fun settMarker() {

        while(alive) {
            println("Set/unset mine marks or claim a cell as free:")
            val cordinates = readln().split(" ")
            if(cordinates[2] == "mine") mark(cordinates[1].toInt() - 1, cordinates[0].toInt() - 1)
            else if(cordinates[2] == "free") explore(cordinates[1].toInt() - 1, cordinates[0].toInt() - 1)
            printField()
            if(minesMarked == numMines && minesMarked == minesCorrect) {
                println("Congratulations! You found all the mines!")
                break
            } else if (cellesExplored - numMines == 81 - numMines) {
                println("Congratulations! You found all the mines!")
                break
            }
        }
    }

    mineSetup()
    numberFill()
    printField()
    printField2()
    settMarker()

}

