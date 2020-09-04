package br.edu.ifsp.trabalho1pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ifsp.trabalho1pdm.Model.Pessoa;
import br.edu.ifsp.trabalho1pdm.Model.SharedPreferencesMethods;

public class MainActivity extends AppCompatActivity {
    public static final String nomeKEY = "Nome";
    public static final String idadeKEY = "Idade";
    public static final String emailKEY = "Email";
    public static final String celularKEY = "Celular";

    public static final String[] keyListForStrings= {nomeKEY,emailKEY,celularKEY};
    public static final String[] keyListForInts = {idadeKEY};

    private SharedPreferences preferences;

    EditText txtNome, txtIdade, txtEmail, txtCelular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        txtNome = findViewById(R.id.txtNome);
        txtIdade = findViewById(R.id.txtIdade);
        txtEmail = findViewById(R.id.txtEmail);
        txtCelular = findViewById(R.id.txtCelular);

        SharedPreferencesMethods spm = new SharedPreferencesMethods(this);
        this.preferences = spm.sharedPreferencesLoading();
        if(this.loadingSharedPreferences()){
            Toast.makeText(this,"Dados recuperados",Toast.LENGTH_SHORT).show();
        }


    }
    protected  void onResume() {

        super.onResume();
        txtCelular.addTextChangedListener(Mascaras.mask(txtCelular, Mascaras.FORMAT_FONE));
    }

    protected boolean verifyEmptys(){
        return txtNome.getText().toString().equals("")
                || txtCelular.getText().toString().equals("")
                || txtIdade.getText().toString().equals("")
                || txtEmail.getText().toString().equals("");
    }
    public Pessoa creatingPessoa(){
        //Creating object
        return new Pessoa(
                txtNome.getText().toString(),
                txtEmail.getText().toString(),
                Integer.parseInt(txtIdade.getText().toString()),
                txtCelular.getText().toString());

    }
    public boolean savingSharedPreferences(Pessoa p){
        SharedPreferencesMethods msp = new SharedPreferencesMethods(this);

        msp.setStringsKeys(keyListForStrings);// Keys for strings datas
        msp.setStringsKeysForInt(keyListForInts);//Keys for int datas

        msp.setStringsValues(new String[]{p.getNome(),p.getEmail(), p.getCelular()});//Strings datas
        msp.setIntsValues(new int[]{p.getIdade()}); //Integers datas

        if(msp.sharedPreferencesSaving()){
            this.preferences = msp.sharedPreferencesLoading();
            return true;
        }else{
            return false;
        }
    }
    public boolean loadingSharedPreferences(){
        try{
            txtNome.setText(preferences.getString(nomeKEY,""));
            txtEmail.setText(preferences.getString(emailKEY,""));
            txtCelular.setText(preferences.getString(celularKEY,""));
            txtIdade.setText(String.valueOf(preferences.getInt(idadeKEY,0)));

            return true;
        }catch (Exception e){
            return false;
        }

    }
    //Method de IntentExplicit
    public Intent ObjIntentExplicit(){
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(nomeKEY,txtNome.getText().toString());
        intent.putExtra(idadeKEY,txtIdade.getText().toString());
        intent.putExtra(emailKEY, txtEmail.getText().toString());
        intent.putExtra(celularKEY, txtCelular.getText().toString());
        return intent;
    }
    public void callSecondActivity(){
        startActivity(this.ObjIntentExplicit());
    }
    public void onClickContinuar(View v){
        if(!verifyEmptys()){
            Pessoa p = creatingPessoa();

            if(savingSharedPreferences(p)){
                Toast.makeText(this,"Succefully - SharedPreferences!",Toast.LENGTH_SHORT).show();
                callSecondActivity();

            }else{
                Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Campos não marcados",Toast.LENGTH_SHORT).show();
        }
    }





}
