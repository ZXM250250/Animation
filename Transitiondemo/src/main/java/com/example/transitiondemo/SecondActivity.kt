

package myapplication

import android.os.Build
import android.os.Bundle
import android.transition.ChangeBounds
import android.view.Window
import androidx.annotation.RequiresApi
import com.example.transitiondemo.R
import myapplication.BaseActivity


class SecondActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        window.sharedElementEnterTransition = ChangeBounds()
        window.sharedElementExitTransition = ChangeBounds()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        title = "SecondActivity"
    }
}
