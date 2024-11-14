package com.maruzamjunior.applistacurso2.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.maruzamjunior.applistacurso2.R;
import com.maruzamjunior.applistacurso2.controller.PessoaController;
import com.maruzamjunior.applistacurso2.model.Pessoa;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    public static final String NOME_PREFRENCES = "pref_listavip";
    PessoaController controller;

    Pessoa pessoa;

    EditText editPrimeiroNome;
    EditText editSobreNome;
    EditText editCursoDesejado;
    EditText editTelefone;

    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        preferences = getSharedPreferences(NOME_PREFRENCES, 0);
        SharedPreferences.Editor listaVip = preferences.edit();

        controller = new PessoaController();
        controller.toString();

        pessoa = new Pessoa();

        pessoa.setPrimeiroNome(preferences.getString("primeiroNome", ""));
        pessoa.setSobrenome(preferences.getString("sobreNome", ""));
        pessoa.setCursoDesejado(preferences.getString("cursoDesejado", ""));
        pessoa.setTelefoneContato(preferences.getString("telefoneContato", ""));

        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobreNome = findViewById(R.id.editSobreNome);
        editCursoDesejado = findViewById(R.id.editCursoDesejado);
        editTelefone = findViewById(R.id.editTelefone);

        editPrimeiroNome.setText(pessoa.getPrimeiroNome());
        editSobreNome.setText(pessoa.getSobrenome());
        editCursoDesejado.setText(pessoa.getCursoDesejado());
        editTelefone.setText(pessoa.getTelefoneContato());

        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPrimeiroNome.setText(" ");
                editSobreNome.setText(" ");
                editCursoDesejado.setText(" ");
                editTelefone.setText(" ");
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Volte Sempre", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pessoa.setPrimeiroNome(editPrimeiroNome.getText().toString());
                pessoa.setSobrenome(editSobreNome.getText().toString());
                pessoa.setCursoDesejado(editCursoDesejado.getText().toString());
                pessoa.setTelefoneContato(editTelefone.getText().toString());


                Toast.makeText(MainActivity.this,
                        "Dados Salvo" + pessoa.toString(),
                        Toast.LENGTH_LONG).show();

                listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
                listaVip.putString("sobreNome", pessoa.getSobrenome());
                listaVip.putString("cursoDesejado", pessoa.getCursoDesejado());
                listaVip.putString("telefoneContato",pessoa.getTelefoneContato());

                listaVip.apply();


                controller.salvar(pessoa);

            }
        });

        Log.i("PooAndroid", pessoa.toString());


    }

}