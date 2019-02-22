package architgargpro.tictactoe

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
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
    private var p1score = 0
    private var p2score = 0
    private var autoPlayActive = false


    fun click(view: View)
    {
        val selectedButton = view as Button
        var cellID = 0
        when(selectedButton.id)
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

    override fun onBackPressed() {
        frameLayout.visibility = View.VISIBLE
        reset.visibility = View.INVISIBLE
        tableLayout.visibility = View.INVISIBLE
        gameLayout.visibility = View.INVISIBLE
        p1score = 0
        p2score = 0
        setScore()
    }

    fun reset(view: View)
    {
        val winnerText: TextView = winnerText
        winnerText.text = ""

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
        gameLayout.visibility = View.VISIBLE
        reset.visibility = View.VISIBLE
        tableLayout.visibility = View.VISIBLE
        reset(view)
    }

    private fun play(cellID: Int, selectedButton: Button)
    {
        val resetButton: Button = reset
        resetButton.text = getString(R.string.reset)

        if(activePlayer == 1)
        {
            selectedButton.text = "O"
            selectedButton.setTextColor(Color.parseColor("#ffffff"))
            player1.add(cellID)
            activePlayer = 2

            checkWin()

            // play the automated turn
            if(winner == 0 && numberOfPlayers == 1) {
                autoPlay()
                autoPlayActive = false
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

        if (autoPlayActive == false) {
            if (winner == 1) {
                p1score += 1
            } else if (winner == 2) {
                p2score += 1
            }
        }
        setScore()
    }

    private fun setScore()
    {
        val p1scr: TextView = player1_score
        p1scr.text = p1score.toString()

        val p2scr: TextView = player2_score
        p2scr.text = p2score.toString()
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
//            p1_score+=1
            Log.i("here",player1.toString())
            Log.i("here",player2.toString())
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
//            p2_score+=1
            Log.i("here",player1.toString())
            Log.i("here",player2.toString())
        }

        if(winner!=0) {
//            Toast.makeText(this, "Player " + winner.toString() + " Won", Toast.LENGTH_LONG).show()

            val winnerText: TextView = winnerText
            val str = "Player " + winner.toString() + " Won"
            winnerText.text = str

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

            val resetButton: Button = reset
            resetButton.text = getString(R.string.newGame)
        }
    }

    private fun autoPlay()
    {
        autoPlayActive = true
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
