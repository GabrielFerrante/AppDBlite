package br.edu.ifsp.trabalho1pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.trabalho1pdm.Model.DBAccess;
import br.edu.ifsp.trabalho1pdm.Model.Pessoa;
import br.edu.ifsp.trabalho1pdm.Model.SharedPreferencesMethods;

public class Activity2 extends AppCompatActivity {


    private SharedPreferences preferences;


    TextView lblNome, lblIdade, lblEmail, lblCelular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        lblNome = findViewById(R.id.lblNome);
        lblIdade = findViewById(R.id.lbltIdade);
        lblEmail = findViewById(R.id.txtEmail);
        lblCelular = findViewById(R.id.txtCelular);


        Intent intent = getIntent();
        lblNome.setText("Nome: " + intent.getStringExtra(MainActivity.nomeKEY));
        lblIdade.setText("Idade: "+intent.getStringExtra(MainActivity.idadeKEY));
        lblEmail.setText("Email: "+intent.getStringExtra(MainActivity.emailKEY));
        lblCelular.setText("Celular: "+intent.getStringExtra(MainActivity.celularKEY));

    }
    //Method de IntentExplicit
    public Intent ObjIntentExplicit(){
        Intent intent = new Intent(this, MainActivity.class);

        return intent;
    }
    public void callSecondActivity(){
        startActivity(this.ObjIntentExplicit());
    }
    public boolean savingSharedPreferences(){
        SharedPreferencesMethods msp = new SharedPreferencesMethods(this);

        msp.setStringsKeys(new String[]{MainActivity.nomeKEY,MainActivity.emailKEY,MainActivity.celularKEY});// Keys for strings datas

        msp.setStringsKeysForInt(new String[]{MainActivity.idadeKEY});//Keys for int datas

        msp.setStringsValues(new String[]{"","", ""});//Strings datas
        msp.setIntsValues(new int[]{0}); //Integers datas

        if(msp.sharedPreferencesSaving()){
            this.preferences = msp.sharedPreferencesLoading();
            return true;
        }else{
            return false;
        }
    }
    public void onClickRejeitar(View v){
        savingSharedPreferences();
        callSecondActivity();

    }
    public void onClickSalvar(View v){

        Intent intent = getIntent();
        Pessoa p = new Pessoa(
                0,
                intent.getStringExtra(MainActivity.nomeKEY),
                intent.getStringExtra(MainActivity.emailKEY),
                Integer.parseInt(intent.getStringExtra(MainActivity.idadeKEY)),
                intent.getStringExtra(MainActivity.celularKEY)
                );

        DBAccess dbAccess = new DBAccess(this);
        dbAccess.insertPessoa(p);
        savingSharedPreferences();
        Toast.makeText(this, "Salvo com sucesso!" , Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this,Activity3.class);
        startActivity(it);


    }
}
