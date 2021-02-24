package pl.daveprojects.quizapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private var currentPosition: Int = 1
    private var questions: ArrayList<Question>? = Constants.getQuestions()
    private var selectedOption: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        val optOne = findViewById<TextView>(R.id.tv_option_one)
        val optTwo = findViewById<TextView>(R.id.tv_option_two)
        val optThree = findViewById<TextView>(R.id.tv_option_three)
        val optFour = findViewById<TextView>(R.id.tv_option_four)
        val submitBtn = findViewById<TextView>(R.id.btn_submit)
        optOne.setOnClickListener(this)
        optTwo.setOnClickListener(this)
        optThree.setOnClickListener(this)
        optFour.setOnClickListener(this)
        submitBtn.setOnClickListener(this)

        setQuestion()
    }

    private fun setQuestion() {
        val question: Question? = questions!![currentPosition - 1]
        defaultOptionsView()

        val submitBtn = findViewById<Button>(R.id.btn_submit)
        if (currentPosition == questions!!.size) {
            submitBtn.text = "FINISH"
        } else {
            submitBtn.text = "SUBMIT"
        }

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

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, findViewById(R.id.tv_option_one))
        options.add(1, findViewById(R.id.tv_option_two))
        options.add(2, findViewById(R.id.tv_option_three))
        options.add(3, findViewById(R.id.tv_option_four))
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_option_one -> selectedOptionView(v as TextView, 1)
            R.id.tv_option_two -> selectedOptionView(v as TextView, 2)
            R.id.tv_option_three -> selectedOptionView(v as TextView, 3)
            R.id.tv_option_four -> selectedOptionView(v as TextView, 4)
            R.id.btn_submit -> {
                if (selectedOption == 0) {
                    currentPosition++
                    when {
                        currentPosition <= questions!!.size -> setQuestion()
                        else -> Toast.makeText(
                            this,
                            "You have successfully compleated quiz",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    val question = questions?.get(currentPosition - 1)
                    if (question!!.correctAnswer != selectedOption) {
                        answerView(selectedOption, R.drawable.wrong_option_border_bg)
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)
                    if (currentPosition == questions!!.size) {
                        (v as Button).text = "FINISH"
                    } else {
                        (v as Button).text = "GO TO NEXT QUESTION"
                    }
                    selectedOption = 0
                }
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()
        selectedOption = selectedOptionNumber
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.typeface = Typeface.DEFAULT_BOLD
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun answerView(answer: Int, drawableView: Int) {
        var option: TextView? = null
        when (answer) {
            1 -> option = findViewById<TextView>(R.id.tv_option_one)
            2 -> option = findViewById<TextView>(R.id.tv_option_two)
            3 -> option = findViewById<TextView>(R.id.tv_option_three)
            4 -> option = findViewById<TextView>(R.id.tv_option_four)
        }
        option?.background = ContextCompat.getDrawable(this, drawableView)
    }
}