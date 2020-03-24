package com.haanhgs.animation.view;

import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.haanhgs.animation.R;
import com.haanhgs.animation.model.Repo;
import com.haanhgs.animation.viewmodel.MyViewModel;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//check git
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ivOutput)
    ImageView ivOutput;
    @BindView(R.id.bnFadeIn)
    Button bnFadeIn;
    @BindView(R.id.bnFadeOut)
    Button bnFadeOut;
    @BindView(R.id.bnFadeInOut)
    Button bnFadeInOut;
    @BindView(R.id.bnZoomIn)
    Button bnZoomIn;
    @BindView(R.id.bnZoomOut)
    Button bnZoomOut;
    @BindView(R.id.bnRightLeft)
    Button bnRightLeft;
    @BindView(R.id.bnLeftRight)
    Button bnLeftRight;
    @BindView(R.id.bnTopDown)
    Button bnTopDown;
    @BindView(R.id.bnBounce)
    Button bnBounce;
    @BindView(R.id.bnFlash)
    Button bnFlash;
    @BindView(R.id.bnRotateLeft)
    Button bnRotateLeft;
    @BindView(R.id.bnRotateRight)
    Button bnRotateRight;
    @BindView(R.id.sbrDuration)
    SeekBar sbrDuration;
    @BindView(R.id.tvDuration)
    TextView tvDuration;
    @BindView(R.id.bnDecrease)
    ImageButton bnDecrease;
    @BindView(R.id.bnIncrease)
    ImageButton bnIncrease;

    private MyViewModel viewModel;
    private Repo repo;
    private int duration;

    public void hideStatusBarInLandscapeMode(){
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void setupViews(){
        sbrDuration.setProgress(2000);
        tvDuration.setText(String.valueOf(2000));
    }

    private void handleSeekBar(){
        sbrDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                viewModel.setDuration(progress);
                tvDuration.setText(String.valueOf(progress));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        repo = new Repo(this);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.setLiveData();
        setupViews();
        viewModel.getLiveData().observe(this, model -> duration = model.getDuration());
        handleSeekBar();
    }

    private void handleButtons(View view, int duration){
        switch (view.getId()) {
            case R.id.bnFadeIn:
                repo.loadAnimations(repo.getAnimFadeIn(), ivOutput, duration);
                break;
            case R.id.bnFadeOut:
                repo.loadAnimations(repo.getAnimFadeOut(), ivOutput, duration);
                break;
            case R.id.bnFadeInOut:
                repo.loadAnimations(repo.getAnimFadeInOut(), ivOutput, duration);
                break;
            case R.id.bnZoomIn:
                repo.loadAnimations(repo.getAnimZoomIn(), ivOutput, duration);
                break;
            case R.id.bnZoomOut:
                repo.loadAnimations(repo.getAnimZoomOut(), ivOutput, duration);
                break;
            case R.id.bnRightLeft:
                repo.loadAnimations(repo.getAnimLeft(), ivOutput, duration);
                break;
            case R.id.bnLeftRight:
                repo.loadAnimations(repo.getAnimRight(), ivOutput, duration);
                break;
            case R.id.bnTopDown:
                repo.loadAnimations(repo.getAnimTopBot(), ivOutput, duration);
                break;
            case R.id.bnBounce:
                repo.loadAnimations(repo.getAnimBounce(), ivOutput, duration);
                break;
            case R.id.bnFlash:
                repo.loadAnimations(repo.getAnimFlash(), ivOutput, duration);
                break;
            case R.id.bnRotateLeft:
                repo.loadAnimations(repo.getAnimRotateLeft(), ivOutput, duration);
                break;
            case R.id.bnRotateRight:
                repo.loadAnimations(repo.getAnimRotateRight(), ivOutput, duration);
                break;
            case R.id.bnDecrease:
                repo.handleDecreaseButton(sbrDuration);
                break;
            case R.id.bnIncrease:
                repo.handleIncreaseButton(sbrDuration);
                break;
        }
    }

    @OnClick({R.id.bnFadeIn, R.id.bnFadeOut, R.id.bnFadeInOut, R.id.bnZoomIn, R.id.bnZoomOut,
            R.id.bnRightLeft, R.id.bnLeftRight, R.id.bnTopDown, R.id.bnBounce, R.id.bnFlash,
            R.id.bnRotateLeft, R.id.bnRotateRight, R.id.bnDecrease, R.id.bnIncrease})
    public void onViewClicked(View view) {
        handleButtons(view, duration);
    }
}
