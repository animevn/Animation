package com.haanhgs.animation.view;

import android.app.Activity;
import android.os.Build;
import android.view.Surface;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;
import com.haanhgs.animation.R;

public class Helper {

    private final Activity activity;

    private static final int INTERVAL = 500;
    private Animation animBounce;
    private Animation animFadeIn;
    private Animation animFadeInOut;
    private Animation animFadeOut;
    private Animation animFlash;
    private Animation animRight;
    private Animation animLeft;
    private Animation animRotateRight;
    private Animation animRotateLeft;
    private Animation animTopBot;
    private Animation animZoomIn;
    private Animation animZoomOut;

    @SuppressWarnings("deprecation")
    public void hideStatusBarInLandscapeMode(){
        if (activity.getDisplay() != null){
            int rotation = activity.getDisplay().getRotation();
            if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                    final WindowInsetsController controller = activity.getWindow()
                            .getInsetsController();
                    if (controller != null){
                        controller.hide(WindowInsets.Type.statusBars());
                    }
                }else {
                    activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            }
        }
    }

    private void initAnimation(){
        animFadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(activity, R.anim.fade_out);
        animFadeInOut = AnimationUtils.loadAnimation(activity, R.anim.fade_in_out);
        animZoomIn = AnimationUtils.loadAnimation(activity, R.anim.zoom_in);
        animZoomOut = AnimationUtils.loadAnimation(activity, R.anim.zoom_out);
        animRight = AnimationUtils.loadAnimation(activity, R.anim.right_left);
        animTopBot = AnimationUtils.loadAnimation(activity, R.anim.top_bot);
        animLeft = AnimationUtils.loadAnimation(activity, R.anim.left_right);
        animBounce = AnimationUtils.loadAnimation(activity, R.anim.bounce);
        animFlash = AnimationUtils.loadAnimation(activity, R.anim.flash);
        animRotateLeft = AnimationUtils.loadAnimation(activity, R.anim.rotate_left);
        animRotateRight = AnimationUtils.loadAnimation(activity, R.anim.rotate_right);
    }

    public Helper(Activity activity) {
        this.activity = activity;
        hideStatusBarInLandscapeMode();
        initAnimation();
    }

    public void loadAnimations(Animation animation, ImageView ivOutput, int duration){
        animation.setDuration(duration);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
        });
        ivOutput.startAnimation(animation);
    }

    public void handleIncreaseButton(SeekBar sbrDuration){
        int progress = sbrDuration.getProgress();
        if (progress + INTERVAL > sbrDuration.getMax()){
            sbrDuration.setProgress(sbrDuration.getMax());
        }else {
            sbrDuration.setProgress(progress + INTERVAL);
        }
    }

    public void handleDecreaseButton(SeekBar sbrDuration){
        int progress = sbrDuration.getProgress();
        if (progress - INTERVAL < 0){
            sbrDuration.setProgress(0);
        }else {
            sbrDuration.setProgress(progress - INTERVAL);
        }
    }

    public Animation getAnimBounce() {
        return animBounce;
    }

    public Animation getAnimFadeIn() {
        return animFadeIn;
    }

    public Animation getAnimFadeInOut() {
        return animFadeInOut;
    }

    public Animation getAnimFadeOut() {
        return animFadeOut;
    }

    public Animation getAnimFlash() {
        return animFlash;
    }

    public Animation getAnimRight() {
        return animRight;
    }

    public Animation getAnimLeft() {
        return animLeft;
    }

    public Animation getAnimRotateRight() {
        return animRotateRight;
    }

    public Animation getAnimRotateLeft() {
        return animRotateLeft;
    }

    public Animation getAnimTopBot() {
        return animTopBot;
    }

    public Animation getAnimZoomIn() {
        return animZoomIn;
    }

    public Animation getAnimZoomOut() {
        return animZoomOut;
    }

}
