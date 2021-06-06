package myapplication


import android.os.Build
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionInflater
import android.transition.TransitionManager
import android.view.Window
import android.widget.Button
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.example.transitiondemo.R

class ThirdActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        window.sharedElementEnterTransition = ChangeBounds()
        window.sharedElementExitTransition = ChangeBounds()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val btn = findViewById<Button>(R.id.btnChange)
        val sceneRoot = findViewById<FrameLayout>(R.id.container)


        /**
         * 场景转换的动画效果
         */
        val scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, this)
        val scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, this)

        var count = 1

        btn.setOnClickListener {
            count = if (count == 1) {
                val transition =
                    TransitionInflater.from(this).inflateTransition(R.transition.explore_transition)
                TransitionManager.go(scene2, transition)
                2
            } else {
                val transition =
                    TransitionInflater.from(this).inflateTransition(R.transition.slide_transition)
                TransitionManager.go(scene1, transition)
                1
            }
        }
    }
}