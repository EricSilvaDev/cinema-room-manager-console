package cinema

fun main() {

    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
    val cinema: MutableList<MutableList<Char>> = mutableListOf()
    val label = mutableListOf<Char>()
    var choice: Int = 1
    var purchased = 0
    var currentIncome: Int = 0
    var totalIncome: Int
    var percentage = 0.0

    totalIncome =
            when {
                rows * seats < 61 -> rows * seats * 10
                else -> (rows / 2) * seats * 10 + (rows - rows / 2) * seats * 8
            }

    for (i in 0..seats) {
        val c: Char
        if (i == 0) {
            c = ' '
            label.add(c)
        } else {
            c = Character.forDigit(i, 10)
            label.add(c)
        }
    }

    cinema.add(label)

    for (i in 0..rows - 1) {
        val fila = MutableList(seats) { 'S' }
        val f = Character.forDigit(1 + i, 10)
        fila.add(0, f)
        cinema.add(fila)
    }

    while (choice != 0) {
        println(
                "1. Show the seats" +
                        "\n" +
                        "2. Buy a ticket" +
                        "\n" +
                        "3. Statistics" +
                        "\n" +
                        "0. Exit"
        )
        choice = readln().toInt()
        if (choice == 1) {

            println("Cinema:")
            for (i in 0..rows) {
                println(cinema[i].joinToString(" "))
            }
        } else if (choice == 2) {
            var ok = false

            while (!ok) {
                println("Enter a row number:")
                val rowC = readln().toInt()
                println("Enter a seat number in that row:")
                val seatC = readln().toInt()

                if (rowC > rows || seatC > seats) {
                    println("Wrong input!")
                } else if (cinema[rowC][seatC] == 'B') {
                    println("That ticket has already been purchased")
                    choice = 2
                } else {
                    ok = true
                    val price: Int

                    if ((rows * seats) < 61) {
                        price = 10
                    } else if (rowC > rows / 2) {
                        price = 8
                    } else {
                        price = 10
                    }

                    purchased++
                    currentIncome += price
                    println("Ticket price: $${price}")
                    cinema[rowC][seatC] = 'B'
                }
            }
        } else if (choice == 3) {
            percentage = purchased / (seats * rows).toDouble()
            val result = "%.2f".format(percentage * 100)
            println(
                    "Number of purchased tickets: ${purchased}" +
                            "\n" +
                            "Percentage: ${result}%" +
                            "\n" +
                            "Current income: \$${currentIncome}" +
                            "\n" +
                            "Total income: $${totalIncome}"
            )
        } else if (choice != 0) {
            println("Wrong input!")
        } else choice = 0
    }
}
