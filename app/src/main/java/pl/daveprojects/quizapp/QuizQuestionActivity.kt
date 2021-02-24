package pl.daveprojects.quizapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        setupQuestions()
    }

    private fun setupQuestions() {
        val questions = Constants.getQuestions()
        val currentPosition = 1
        val question: Question? = questions[currentPosition - 1]

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = currentPosition

        val progressBarText = findViewById<TextView>(R.id.tv_progress)
        progressBarText.text = "$currentPosition" + "/" + progressBar.max

        val questionTextView = findViewById<TextView>(R.id.tv_question)
        questionTextView.text = question!!.question

        val imageView = findViewById<ImageView>(R.id.iv_image)
        imageView.setImageResource(question.image)

        val optOne = findViewById<TextView>(R.id.tv_option_one)
        optOne.text = question.optionOne
        val optTwo = findViewById<TextView>(R.id.tv_option_two)
        optTwo.text = question.optionTwo
        val optThree = findViewById<TextView>(R.id.tv_option_three)
        optThree.text = question.optionOThree
        val optFour = findViewById<TextView>(R.id.tv_option_four)
        optFour.text = question.optionFour
    }
}