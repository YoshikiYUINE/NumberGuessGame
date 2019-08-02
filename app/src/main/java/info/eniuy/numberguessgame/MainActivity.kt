package info.eniuy.numberguessgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val enterLabel = findViewById<TextView>(R.id.enterLabel)
        enterLabel.setOnClickListener {
            val intentToNumberGuessing = Intent(this, NumberGuessingGameActivity::class.java)
            startActivity(intentToNumberGuessing)
        }
    }
}
