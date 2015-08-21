package com.example.eymbla.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    private String hello;
    private String pseudo = "Stroumph";
    Uri uri;
    Uri uriDefault = Uri.parse("geo:0,0?q=Place+Charles+de+Gaulle+%2C+PARIS");
    private TextView monTexte;
    private EditText monEdit;
    private Button bTest;
    private Button bTestNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hello = getResources().getString(R.string.hello_world);
        hello = hello.replace("!123PRE", pseudo);

        monTexte = (TextView)findViewById(R.id.textViewWelcome);
        monTexte.setText(hello);

        monEdit = (EditText)findViewById(R.id.editTextWelcome);

        /**
         * TextView text = new TextView(this);
         * text.setText(hello);
         * setContentView(text);
         **/

        bTest = (Button)findViewById(R.id.buttonTestWelcome);
        bTest.setOnTouchListener(this);
        bTest.setOnClickListener(this);
        bTestNav = (Button)findViewById(R.id.buttonTestNav);
        bTestNav.setOnClickListener(this);

        Button bBig = (Button)findViewById(R.id.buttonBig);
        bBig.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                // Comme l'évènement nous donne la vue concernée par le toucher, on le récupère et on le caste en Button
                Button bouton = (Button)view;
                // On récupère la largeur du bouton
                int largeur = bouton.getWidth();
                // On récupère la hauteur du bouton
                int hauteur = bouton.getHeight();
                // On récupère la coordonnée sur l'abscisse (X) de l'évènement
                float x = event.getX();
                // On récupère la coordonnée sur l'ordonnée (Y) de l'évènement
                float y = event.getY();
                // Puis on change la taille du texte selon la formule indiquée dans l'énoncé
                bouton.setTextSize(Math.abs(x - largeur / 2) + Math.abs(y - hauteur / 2));
                // Le toucher est fini, on veut continuer à détecter les touchers d'après
                return true;
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public void onClick(View v) {
        Toast toast;
        // On récupère l'identifiant de la vue, et en fonction de cet identifiant…
        switch(v.getId()) {
            // Si l'identifiant de la vue est celui du premier bouton
            case R.id.buttonTestWelcome:
                toast = Toast.makeText(getApplicationContext(), "Hello toast!", Toast.LENGTH_SHORT);
                toast.show();
    /* Agir pour bouton 1 */
                break;
            // Si l'identifiant de la vue est celui du deuxième bouton
            case R.id.buttonTestNav:
                String sEdit = monEdit.getText().toString();
                if(sEdit.length()!=0) {
                    String geo = "geo:0,0?q=" +
                            sEdit.replace(" ", "+");
                    uri = Uri.parse(geo);
                }
                else uri = uriDefault;
                toast = Toast.makeText(getApplicationContext(), "(" + sEdit.length() + ") " + uri.toString(), Toast.LENGTH_SHORT);
                toast.show();
                showMap(uri);
    /* Agir pour bouton 2 */
                break;
    /* etc. */
        }
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
