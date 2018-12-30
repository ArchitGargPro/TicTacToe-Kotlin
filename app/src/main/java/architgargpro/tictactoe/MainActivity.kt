package architgargpro.tictactoe

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()

    private var activePlayer = 1
    private var winner = 0 //1 for win 0 for no winner
    private var numberOfPlayers = 1


    fun click(view: View)
    {
        val selectedButton = view as Button
        var cellID = 0
        when(selectedButton.id)          //this is like switch case
        {
            R.id.button1 -> cellID = 1
            R.id.button2 -> cellID = 2
            R.id.button3 -> cellID = 3
            R.id.button4 -> cellID = 4
            R.id.button5 -> cellID = 5
            R.id.button6 -> cellID = 6
            R.id.button7 -> cellID = 7
            R.id.button8 -> cellID = 8
            R.id.button9 -> cellID = 9
        }

        play(cellID, selectedButton)
    }

    fun reset(view: View)
    {
        frameLayout.visibility = View.VISIBLE
        reset.visibility = View.INVISIBLE
        tableLayout.visibility = View.INVISIBLE

        //enabling all buttons also to end the game
        for (i in 1..9)
        {
            val selectedButton: Button
            when (i)
            {
                    1 -> selectedButton = button1
                    2 -> selectedButton = button2
                    3 -> selectedButton = button3
                    4 -> selectedButton = button4
                    5 -> selectedButton = button5
                    6 -> selectedButton = button6
                    7 -> selectedButton = button7
                    8 -> selectedButton = button8
                    9 -> selectedButton = button9
                    else -> selectedButton = button1
            }
            selectedButton.isEnabled = true
            selectedButton.text = ""

        }

        //reset player 1 and 2 arrays, active player and winner
        activePlayer = 1
        winner = 0
        player1.clear()
        player2.clear()
    }

    fun setNumberOfPlayers(view: View)
    {
        val selectedButton = view as Button

        if(selectedButton.id==R.id.set1)
        {
            numberOfPlayers = 1
        }
        else if(selectedButton.id==R.id.set2)
        {
            numberOfPlayers = 2
        }

        frameLayout.visibility = View.INVISIBLE
        reset.visibility = View.VISIBLE
        tableLayout.visibility = View.VISIBLE
    }

    private fun play(cellID: Int, selectedButton: Button)
    {
        if(activePlayer == 1)
        {
            selectedButton.text = "O"
            selectedButton.setTextColor(Color.parseColor("#ffffff"))
            player1.add(cellID)
            activePlayer = 2

            checkWin()

            // play the automated turn after a small pause
            if(winner == 0 && numberOfPlayers == 1) {

                autoPlay()
            }
        }
        else
        {
            selectedButton.text = "X"
            selectedButton.setTextColor(Color.parseColor("#ffffff"))
            player2.add(cellID)
            activePlayer = 1
        }

        selectedButton.isEnabled = false
        checkWin()
    }

    private fun checkWin()
    {
        if((player1.contains(1) && player1.contains(2) && player1.contains(3)) ||
            (player1.contains(4) && player1.contains(5) && player1.contains(6)) ||
            (player1.contains(7) && player1.contains(8) && player1.contains(9)) ||
            (player1.contains(1) && player1.contains(4) && player1.contains(7)) ||
            (player1.contains(2) && player1.contains(5) && player1.contains(8)) ||
            (player1.contains(3) && player1.contains(6) && player1.contains(9)) ||
            (player1.contains(1) && player1.contains(5) && player1.contains(9)) ||
            (player1.contains(3) && player1.contains(5) && player1.contains(7)))
        {
            winner = 1
        }
        else
            if((player2.contains(1) && player2.contains(2) && player2.contains(3)) ||
                    (player2.contains(4) && player2.contains(5) && player2.contains(6)) ||
                    (player2.contains(7) && player2.contains(8) && player2.contains(9)) ||
                    (player2.contains(1) && player2.contains(4) && player2.contains(7)) ||
                    (player2.contains(2) && player2.contains(5) && player2.contains(8)) ||
                    (player2.contains(3) && player2.contains(6) && player2.contains(9)) ||
                    (player2.contains(1) && player2.contains(5) && player2.contains(9)) ||
                    (player2.contains(3) && player2.contains(5) && player2.contains(7)))
        {
            winner = 2
        }

        if(winner!=0) {
            Toast.makeText(this, "Player " + winner.toString() + " Won", Toast.LENGTH_LONG).show()


            //disabling other buttons also to end the game
            for (i in 1..9)
            {
                if(!(player1.contains(i) || player2.contains(i)))
                {
                    val selectedButton: Button
                    when (i) {
                        1 -> selectedButton = button1
                        2 -> selectedButton = button2
                        3 -> selectedButton = button3
                        4 -> selectedButton = button4
                        5 -> selectedButton = button5
                        6 -> selectedButton = button6
                        7 -> selectedButton = button7
                        8 -> selectedButton = button8
                        9 -> selectedButton = button9
                        else -> selectedButton = button1
                    }
                    selectedButton.isEnabled = false
                }
            }

        }
    }

    private fun autoPlay()
    {
        val emptyCells = ArrayList<Int>()
        for (i in 1..9)
        {
            if(!(player1.contains(i) || player2.contains(i)))
            {
                emptyCells.add(i)
            }
        }

        if(emptyCells.size!=0) {

            val randomIndex = (Math.random() * emptyCells.size).toInt()
            val cellID = emptyCells[randomIndex]

            val selectedButton: Button
            when (cellID) {
                1 -> selectedButton = button1
                2 -> selectedButton = button2
                3 -> selectedButton = button3
                4 -> selectedButton = button4
                5 -> selectedButton = button5
                6 -> selectedButton = button6
                7 -> selectedButton = button7
                8 -> selectedButton = button8
                9 -> selectedButton = button9
                else -> selectedButton = button1
            }

            play(cellID, selectedButton)
        }
    }
}
