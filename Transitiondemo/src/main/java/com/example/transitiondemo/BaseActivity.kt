package myapplication
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpWindow()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setUpWindow() {
        window.let {
            /**
             * 设置分享元素的转场
              */
            it.sharedElementEnterTransition
            it.sharedElementExitTransition
            it.sharedElementReenterTransition
            it.sharedElementReturnTransition
            it.allowEnterTransitionOverlap = false
            it.allowReturnTransitionOverlap = false
//            it.exitTransition =
//                TransitionInflater.from(this).inflateTransition(R.transition.fade_transition)
            it.exitTransition = Slide().apply {
                duration = 200
                slideEdge = Gravity.END
            }

            it.enterTransition = Slide().apply {
                duration = 200
                slideEdge = Gravity.START

            }

            it.reenterTransition = Slide().apply {
                duration = 200
                slideEdge = Gravity.END
            }

            it.returnTransition = Slide().apply {
                duration = 200
                slideEdge = Gravity.START
            }
        }
    }
}