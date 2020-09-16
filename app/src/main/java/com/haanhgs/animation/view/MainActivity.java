package com.haanhgs.animation.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.haanhgs.animation.R;
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
    private Helper helper;
    private int duration;

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
        helper = new Helper(this);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.setLiveData();
        setupViews();
        viewModel.getLiveData().observe(this, model -> duration = model.getDuration());
        handleSeekBar();
    }

    private void handleButtons(View view, int duration){
        switch (view.getId()) {
            case R.id.bnFadeIn:
                helper.loadAnimations(helper.getAnimFadeIn(), ivOutput, duration);
                break;
            case R.id.bnFadeOut:
                helper.loadAnimations(helper.getAnimFadeOut(), ivOutput, duration);
                break;
            case R.id.bnFadeInOut:
                helper.loadAnimations(helper.getAnimFadeInOut(), ivOutput, duration);
                break;
            case R.id.bnZoomIn:
                helper.loadAnimations(helper.getAnimZoomIn(), ivOutput, duration);
                break;
            case R.id.bnZoomOut:
                helper.loadAnimations(helper.getAnimZoomOut(), ivOutput, duration);
                break;
            case R.id.bnRightLeft:
                helper.loadAnimations(helper.getAnimLeft(), ivOutput, duration);
                break;
            case R.id.bnLeftRight:
                helper.loadAnimations(helper.getAnimRight(), ivOutput, duration);
                break;
            case R.id.bnTopDown:
                helper.loadAnimations(helper.getAnimTopBot(), ivOutput, duration);
                break;
            case R.id.bnBounce:
                helper.loadAnimations(helper.getAnimBounce(), ivOutput, duration);
                break;
            case R.id.bnFlash:
                helper.loadAnimations(helper.getAnimFlash(), ivOutput, duration);
                break;
            case R.id.bnRotateLeft:
                helper.loadAnimations(helper.getAnimRotateLeft(), ivOutput, duration);
                break;
            case R.id.bnRotateRight:
                helper.loadAnimations(helper.getAnimRotateRight(), ivOutput, duration);
                break;
            case R.id.bnDecrease:
                helper.handleDecreaseButton(sbrDuration);
                break;
            case R.id.bnIncrease:
                helper.handleIncreaseButton(sbrDuration);
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
