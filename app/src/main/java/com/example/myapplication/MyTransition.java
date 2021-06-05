package com.example.myapplication;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;


/**
 * Created by qibin on 16-11-20.
 */


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MyTransition extends Transition {

    private static final String TOP = "top";

    private static final String HEIGHT = "height";

    private long mPositionDuration;
    private long mSizeDuration;

    private TimeInterpolator mPositionInterpolator;
    private TimeInterpolator mSizeInterpolator;


    /**
     * 用来手指动画开始的信息
     * @param transitionValues
     */
    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        /**
         * 拿到view
         */
        View view = transitionValues.view;
        Rect rect = new Rect();
        view.getHitRect(rect);
        /**
         * 获取动画view的高度 和y轴位置
         * 并且把信息保存起来
         */
        transitionValues.values.put(TOP, rect.top);
        transitionValues.values.put(HEIGHT, view.getHeight());

        Log.d("qibin", "start:" + rect.top + ";" + view.getHeight());
    }

    /**
     * 用来收集动画结束的信息
     * @param transitionValues
     */
    @Override
    public void captureEndValues(android.transition.TransitionValues transitionValues) {


        /**
         * 更改保存的信息   把y轴位置改为0
         * 高度等于用一开始的高度
         */
        transitionValues.values.put(TOP, 0);
        transitionValues.values.put(HEIGHT, transitionValues.view.getHeight());

        Log.d("qibin", "end:" + 0 + ";" + transitionValues.view.getHeight());
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, android.transition.TransitionValues startValues, final TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }

        /**
         * 从这里去拿到东环开始和结束
         * 收集到的信息
         */

        final View endView = endValues.view;

        final int startTop = (int) startValues.values.get(TOP);
        final int startHeight = (int) startValues.values.get(HEIGHT);

        final int endTop = (int) endValues.values.get(TOP);
        final int endHeight = (int) endValues.values.get(HEIGHT);

        /**
         * 把view的高度 设置为先前的高度是为了防止
         * 移动过程中  它的高度变成 展开的高度
         */
        ViewCompat.setTranslationY(endView, startTop);
        endView.getLayoutParams().height = startHeight;
        endView.requestLayout();


        /**
         * 移动到顶端的动画
         */
        ValueAnimator positionAnimator = ValueAnimator.ofInt(startTop, endTop);
        if (mPositionDuration > 0) {
            positionAnimator.setDuration(mPositionDuration);
        }
        positionAnimator.setInterpolator(mPositionInterpolator);

        positionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int current = (int) valueAnimator.getAnimatedValue();
                ViewCompat.setTranslationY(endView, current);
            }
        });


        /**
         * 展开动画
         */
        ValueAnimator sizeAnimator = ValueAnimator.ofInt(startHeight, endHeight);
        if (mSizeDuration > 0) {
            sizeAnimator.setDuration(mSizeDuration);
        }
        sizeAnimator.setInterpolator(mSizeInterpolator);

        sizeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int current = (int) valueAnimator.getAnimatedValue();
                /**
                 * 改变自己的高度以后 要求重新绘制
                 */
                endView.getLayoutParams().height = current;
                endView.requestLayout();
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.play(sizeAnimator).after(positionAnimator);
        /**
         * 返回一个动画集合
         */
        return set;
    }

    public void setPositionDuration(long duration) {
        mPositionDuration = duration;
    }

    public void setSizeDuration(long duration) {
        mSizeDuration = duration;
    }

    public void setPositionInterpolator(TimeInterpolator interpolator) {
        mPositionInterpolator = interpolator;
    }

    public void setSizeInterpolator(TimeInterpolator interpolator) {
        mSizeInterpolator = interpolator;
    }




}
