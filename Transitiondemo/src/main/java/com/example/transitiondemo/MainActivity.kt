
package myapplication
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.ChangeBounds


import android.util.Pair
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.transitiondemo.R
import myapplication.BaseActivity


class MainActivity : BaseActivity() {

    private lateinit var btn1: Button
    private lateinit var btn2: Button

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        setThisUpWindow()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.txt)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        val imgView = findViewById<ImageView>(R.id.img)

        btn1.setOnClickListener {
            val intent = Intent()
            val pair1 = Pair(imgView as View, imgView.transitionName)
            val pair2 = Pair(textView as View, textView.transitionName)
            intent.setClass(this, SecondActivity::class.java)
            val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                this@MainActivity,
                pair1, pair2
            )
            startActivity(intent, transitionActivityOptions.toBundle())
        }

        btn2.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, ThirdActivity::class.java)
            val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                this@MainActivity
            )
            startActivity(intent, transitionActivityOptions.toBundle())
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setThisUpWindow() {
        window.sharedElementEnterTransition = ChangeBounds()
        window.sharedElementExitTransition = ChangeBounds()
    }
}
