package pl.daveprojects.quizapp

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        val username = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        val usernameTextView = findViewById<TextView>(R.id.tv_name)
        val scoreTextView = findViewById<TextView>(R.id.tv_score)
        val finishBtn = findViewById<Button>(R.id.btn_finish)

        usernameTextView.text = username
        scoreTextView.text = "Your score is $correctAnswers out of $totalQuestions"

        finishBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}