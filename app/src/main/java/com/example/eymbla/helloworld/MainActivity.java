package com.example.eymbla.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eymbla.helloworld.R;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    private String hello = null;
    private String pseudo = "Stroumph";
    private TextView monTexte = null;
    private Button bTest = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hello = getResources().getString(R.string.hello_world);
        hello = hello.replace("!123PRE", pseudo);

        monTexte = (TextView)findViewById(R.id.textViewWelcome);
        monTexte.setText(hello);

        /**
         * TextView text = new TextView(this);
         * text.setText(hello);
         * setContentView(text);
         **/

        bTest = (Button)findViewById(R.id.buttonTestWelcome);
        bTest.setOnTouchListener(this);
        bTest.setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {
        // On récupère l'identifiant de la vue, et en fonction de cet identifiant…
        switch(v.getId()) {
            // Si l'identifiant de la vue est celui du premier bouton
            case R.id.buttonTestWelcome:
                Toast toast = Toast.makeText(getApplicationContext(), "Hello toast!", Toast.LENGTH_SHORT);
                toast.show();
    /* Agir pour bouton 1 */
                break;
            // Si l'identifiant de la vue est celui du deuxième bouton
            case R.id.buttonTestWelcome2:
    /* Agir pour bouton 2 */
                break;
    /* etc. */
        }
    }
}
