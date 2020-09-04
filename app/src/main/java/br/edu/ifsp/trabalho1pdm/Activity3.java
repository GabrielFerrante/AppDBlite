package br.edu.ifsp.trabalho1pdm;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ifsp.trabalho1pdm.Model.DBAccess;
import br.edu.ifsp.trabalho1pdm.Model.Pessoa;
import br.edu.ifsp.trabalho1pdm.Model.PessoaAdapter;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showPessoas();
    }
    @Override
    protected void onResume() {
        super.onResume();
        showPessoas();
    }

    public void showPessoas() {
        ListView lvPessoas = findViewById(R.id.listViewPessoa);

        DBAccess dbAccess = new DBAccess(this);
        ArrayList<Pessoa> arrPessoas = dbAccess.selectAll();
        PessoaAdapter adapter = new PessoaAdapter(this, arrPessoas);
        lvPessoas.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void enviarMensagem(){
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Trabalho 1 realizado com sucesso");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }catch (Exception e){
            Toast.makeText(this,"Falha em escolher app para enviar mensagem",Toast.LENGTH_SHORT).show();
        }

    }
    private void abrirSite(){
        try {
            String url = "http://www.google.com";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this,"Falha em abrir site",Toast.LENGTH_SHORT).show();
        }
    }
    private void inserirFakison(){
        DBAccess db = new DBAccess(this);

        db.insertPessoa(new Pessoa(
                "Fakison",
                "fakeperson@fakenews.com",
                171,
                "71171171")
        );
    }
    private void addFake(){
        try {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.atencao)
                    .setMessage(R.string.inserir)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            inserirFakison();
                            showPessoas();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();

        }catch (Exception e){
            Toast.makeText(this,"Falha em adicionar pessoa Fake",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_enviarMensagem:
                enviarMensagem();
                break;
            case R.id.action_abrirSite:
                abrirSite();
                break;
            case R.id.action_addFake:
                addFake();
                break;
            case R.id.action_voltar:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            default:

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
