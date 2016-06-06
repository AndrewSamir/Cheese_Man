package samir.andrew.cheeseman;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView cheese,man,line;
    RelativeLayout main_layout;
    TranslateAnimation translateAnimation;
    final Random rand = new Random();
    private int xDelta;
    int count=0;
DataBase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_layout=(RelativeLayout)findViewById(R.id.layout_main);


        for(int i=0;i<5;i++) {


                int random_cheese_X = rand.nextInt(920 - 10) + 10;
                int random_cheese_speed = rand.nextInt(8000 - 3000) + 3000;


                cheese = new ImageView(this);
                cheese.setX((float) random_cheese_X);
                cheese.setY(10);
                cheese.setImageResource(R.mipmap.ic_launcher);

                main_layout.addView(cheese);

                translateAnimation = new TranslateAnimation(0, 0, 0, 1770);
                translateAnimation.setDuration((long) random_cheese_speed);
                translateAnimation.setRepeatCount(2);
                translateAnimation.setFillAfter(true);

                translateAnimation.setRepeatMode(Animation.RESTART);
            final int finalI = i;
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    count++;
                    GameOver(count);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                    float e = cheese.getX();

                    float w = cheese.getY();
                    cheese.requestLayout();


                }
            });
            cheese.setAnimation(translateAnimation);

        }
        int men_num = rand.nextInt(10 - 5) + 5;

        for(int i=0;i<men_num;i++){

            int random_man_X = rand.nextInt(100 - 10) + 10;
            int random_man_speed = rand.nextInt(8000 - 4000) + 4000;


            man = new ImageView(this);
            man.setY(1370);
            man.setX(random_man_X);
            man.setImageResource(R.drawable.images);

            main_layout.addView(man);

            translateAnimation = new TranslateAnimation(random_man_X, 800, 0, 0);
            translateAnimation.setDuration((long) random_man_speed);
            translateAnimation.setFillAfter(true);

            translateAnimation.setRepeatCount(-1);
            translateAnimation.setInterpolator(new LinearInterpolator());
            translateAnimation.setRepeatMode(Animation.REVERSE);

            man.setAnimation(translateAnimation);


        }

        line = new ImageView(this);
        line.setX(20);
        line.setY(1300);
        line.setMaxWidth(200);
        line.setImageResource(R.drawable.horizontal);

        line.setOnTouchListener(onTouchListener());


        main_layout.addView(line);
    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        break;


                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                main_layout.invalidate();
                return true;
            }
        };
    }

    public void GameOver (int Count){

        if(count<5);
        else{
DB=new DataBase(MainActivity.this);
         boolean bool= DB.addUser("Name",rand.nextInt(100-0)+0);
            if(bool)
            Toast.makeText(MainActivity.this,"GameOver",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,Score.class);
            startActivity(intent);
        }
    }
}
