package architgargpro.tictactoe

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

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

        Toast.makeText(this, "Button Clicked : " + cellID , Toast.LENGTH_SHORT).show()

        play(cellID, selectedButton)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var activePlayer = 1

    private fun play(cellID: Int, selectedButton: Button)
    {
        if(activePlayer == 1)
        {
            selectedButton.text = "O"
            selectedButton.setTextColor(Color.parseColor("#00574B"))
            player1.add(cellID)
            activePlayer = 2
        }
        else
        {
            selectedButton.text = "X"
            selectedButton.setTextColor(Color.parseColor("#00574B"))
            player2.add(cellID)
            activePlayer = 1
        }

        selectedButton.isEnabled = false
    }
}
