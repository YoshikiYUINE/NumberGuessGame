package info.eniuy.numberguessgame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_number_guessing_game.*

class NumberGuessingGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guessing_game)
        val randomNumber: Int = (Math.random() * 999).toInt() + 1//random Number 1-100
        val enterButton = findViewById<TextView>(R.id.enterButton)//enterButton
        val editGuessNumber = findViewById<EditText>(R.id.editGuessNumber)
        val commentLabel = findViewById<TextView>(R.id.commentLabel)//commentLabel
        val guessNumberString = editGuessNumber.text//user input guess number
        var count = 10//game remaining 10 times
        //commentLabel.text = String.format(Integer.toString(randomNumber))
        enterButton.setOnClickListener {
            count--//game remaining -1
            //user clicked button then
            goNextRemaining(guessNumberString, randomNumber, commentLabel, count)
        }
    }

    //check lose or win
    private fun goNextRemaining(guessNumberString: Editable, randomNumber: Int, commentLabel: TextView, count: Int) {
        Toast.makeText(applicationContext, getString(R.string.times_remaining, count), Toast.LENGTH_SHORT)
            .show()//toast game remaining
        if (guessNumberString.isNotEmpty()) {//user input guess number is not empty
            val guessNumber: Int = Integer.parseInt(guessNumberString.toString())//convert guess number to integer
            when {
                guessNumber == randomNumber -> {//guess number is correct
                    commentLabel.text = getString(R.string.backToTop)
                    editGuessNumber.isEnabled = false
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.correct_answer, randomNumber),
                        Toast.LENGTH_LONG
                    ).show()//toast correct answer
                    finish()//return main activity
                }
                count == 0 && guessNumber != randomNumber -> {//failed 10 times, is not correct
                    commentLabel.text = getString(R.string.backToTop)
                    editGuessNumber.isEnabled = false
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.user_lose, randomNumber),
                        Toast.LENGTH_LONG
                    ).show()//toast game over
                    finish()//return main activity
                }
                guessNumber > randomNumber && count != 0 -> {//failed random number is less than guess number
                    commentLabel.text = getString(R.string.less_guess_number, guessNumber)
                    editGuessNumber.text.clear()
                }
                guessNumber < randomNumber && count != 0 -> {//failed random number is greater than guess number
                    commentLabel.text = getString(R.string.grater_guess_number, guessNumber)
                    editGuessNumber.text.clear()
                }
            }
        } else {//user input guess number is empty
            when {
                count == 0 -> {//failed 10 times, guess number is empty
                    commentLabel.text = getString(R.string.backToTop)
                    editGuessNumber.isEnabled = false
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.user_lose, randomNumber),
                        Toast.LENGTH_LONG
                    ).show()//toast game over
                    finish()//return main activity
                }
                count != 1 -> {//failed guess number is empty
                    commentLabel.text = getString(R.string.number_guessing_game_label)
                }
            }
        }
    }


}
