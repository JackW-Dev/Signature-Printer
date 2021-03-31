import java.io.File
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    sigMakerMedium(scanner)
    println()
    sigMakerRoman(scanner)
}

fun sigMakerMedium(scanner: Scanner) {
    print("Full name: ")
    val name = scanner.nextLine().toLowerCase()
    print("Title: ")
    val title = scanner.nextLine()
    var longTitle = false
    var padding = 0
    var padCheck = 0

    var borderLength: Int = 5 + name.length
    for (letter in name) {
        borderLength += when (letter) {
            'i' -> 1
            'j' -> 2
            't' -> 3
            'w', 'y' -> 5
            else -> 4
        }
    }

    if (title.length >= borderLength - 2) {
        longTitle = true
    }

    if (longTitle) {
        padding  = (title.length + 6 - borderLength) / 2
        padCheck = padding * 2 + borderLength
        borderLength = title.length + 6
    }

    repeat(borderLength) {
        print("*")
    }
    println()

    for (i in 0..2) {
        print("*  ")
        if (longTitle) {
            repeat(padding) {
                print(" ")
            }
        }
        for (letter in name) {
            asciiPrinter(letter, i)
            print(" ")
        }
        if (longTitle) {
            repeat(padding) {
                print(" ")
            }
            if (borderLength != padCheck) {
                print(" ")
            }
        }
        print(" *\n")
    }

    padding = (borderLength - title.length)/2 - 1

    print("*")

    repeat(padding) {
        print(" ")
    }

    print(title)

    repeat(padding) {
        print(" ")
    }

    if (borderLength % 2 != 0 && title.length % 2 == 0 || borderLength % 2 == 0 && title.length % 2 != 0) {
        print(" ")
    }

    println("*")

    repeat(borderLength) {
        print("*")
    }
}

fun asciiPrinter(letter: Char, layer: Int) {
    when (letter) {
        'a' -> print(arrayOf("____", "|__|", "|  |")[layer])
        'b' -> print(arrayOf("___ ", "|__]", "|__]")[layer])
        'c' -> print(arrayOf("____", "|   ", "|___")[layer])
        'd' -> print(arrayOf("___ ", "|  \\", "|__/")[layer])
        'e' -> print(arrayOf("____", "|___", "|___")[layer])
        'f' -> print(arrayOf("____", "|___", "|   ")[layer])
        'g' -> print(arrayOf("____", "| __", "|__]")[layer])
        'h' -> print(arrayOf("_  _", "|__|", "|  |")[layer])
        'i' -> print(arrayOf("_", "|", "|")[layer])
        'j' -> print(arrayOf(" _", " |", "_|")[layer])
        'k' -> print(arrayOf("_  _", "|_/ ", "| \\_")[layer])
        'l' -> print(arrayOf("_   ", "|   ", "|___")[layer])
        'm' -> print(arrayOf("_  _", "|\\/|", "|  |")[layer])
        'n' -> print(arrayOf("_  _", "|\\ |", "| \\|")[layer])
        'o' -> print(arrayOf("____", "|  |", "|__|")[layer])
        'p' -> print(arrayOf("___ ", "|__]", "|   ")[layer])
        'q' -> print(arrayOf("____", "|  |", "|_\\|")[layer])
        'r' -> print(arrayOf("____", "|__/", "|  \\")[layer])
        's' -> print(arrayOf("____", "[__ ", "___]")[layer])
        't' -> print(arrayOf("___", " | ", " | ")[layer])
        'u' -> print(arrayOf("_  _", "|  |", "|__|")[layer])
        'v' -> print(arrayOf("_  _", "|  |", " \\/ ")[layer])
        'w' -> print(arrayOf("_ _ _", "| | |", "|_|_|")[layer])
        'x' -> print(arrayOf("_  _", " \\/ ", "_/\\_")[layer])
        'y' -> print(arrayOf("_   _", " \\_/ ", "  |  ")[layer])
        'z' -> print(arrayOf("___ ", "  / ", " /__")[layer])
        ' ' -> print(arrayOf("    ", "    ", "    ")[layer])
    }
}

fun sigMakerRoman(scanner: Scanner) {
    val romanLineArray = File("src/main/resources/roman.txt").readLines(Charsets.US_ASCII)
    val mediumLineArray = File("src/main/resources/medium.txt").readLines(Charsets.US_ASCII)

    print("Full name: ")
    val name = scanner.nextLine()
    print("Title: ")
    val title = scanner.nextLine().toLowerCase()

    var borderLength: Int
    val padding: Int

    var nameAsciiLength = 0
    for (letter in name) {
        nameAsciiLength += when (letter) {
            'i', 'l', 'I' -> 6
            'f', 'j', 't', 'J' -> 8
            'r', 's' -> 9
            'a', 'c', 'e', 'o', ' ' -> 10
            'b', 'd', 'g', 'p', 'q', 'z' -> 11
            'h', 'k', 'n', 'u', 'v', 'x', 'y', 'S' -> 12
            'B', 'C', 'E', 'F', 'K', 'L', 'O', 'P', 'R' -> 13
            'D', 'G', 'H', 'T', 'U', 'Y', 'Z' -> 14
            'N', 'X' -> 15
            'A', 'Q', 'V' -> 16
            'w', 'M' -> 17
            'm' -> 18
            'W' -> 25
            else -> 0
        }
    }

    borderLength = nameAsciiLength + 8

    var titleAsciiLength = 0
    for (letter in title) {
        titleAsciiLength += when (letter) {
            'i' -> 2
            'j' -> 3
            't' -> 4
            'w', 'y' -> 6
            else -> 5
        }
    }

    var titleLonger = false

    if(titleAsciiLength > nameAsciiLength) {
        titleLonger = true
        borderLength = titleAsciiLength + 8
        padding = (borderLength - nameAsciiLength - 8) / 2

    } else {
        padding = (borderLength - titleAsciiLength - 8) / 2
    }

    repeat(borderLength) {
        print("8")
    }


    for (i in 0..9) {
        print("\n88  ")

        if (titleLonger) {
            repeat(padding) {
                print(" ")
            }
        }

        for (letter in name) {
            when(letter) {
                ' ' -> print("          ")
                else -> {
                    for (line in romanLineArray.indices) {
                        if (romanLineArray[line].first() == letter && romanLineArray[line][1] == ' ') {
                            print(romanLineArray[line + i + 1])
                        }
                    }
                }
            }
        }

        if (titleLonger) {
            if (nameAsciiLength % 2 != 0 && borderLength % 2 == 0 || nameAsciiLength % 2 == 0 && borderLength % 2 != 0){
                print(" ")
            }
            repeat(padding) {
                print(" ")
            }
        }

        print("  88")
    }

    println("")

    for (i in 0..2) {
        print("88  ")

        if(!titleLonger) {
            repeat(padding) {
                print(" ")
            }
        }

        for (letter in title) {
            when (letter) {
                ' ' -> print("     ")
                else -> {
                    for (line in mediumLineArray.indices) {
                        if (mediumLineArray[line].first() == letter && mediumLineArray[line][1] == ' ') {
                            print(mediumLineArray[line + i + 1])
                        }
                    }
                }
            }
        }

        if(!titleLonger) {
            if(borderLength % 2 != 0 && titleAsciiLength % 2 == 0 || borderLength % 2 == 0 && titleAsciiLength % 2 != 0) {
                print(" ")
            }
            repeat(padding) {
                print(" ")
            }
        }

        print("  88\n")
    }

    repeat(borderLength) {
        print("8")
    }
}
