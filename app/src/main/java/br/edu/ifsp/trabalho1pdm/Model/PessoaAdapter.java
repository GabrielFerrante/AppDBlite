package br.edu.ifsp.trabalho1pdm.Model;

import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ifsp.trabalho1pdm.Activity3;
import br.edu.ifsp.trabalho1pdm.R;

public class PessoaAdapter implements ListAdapter {
    private Context mContext;
    private ArrayList<Pessoa> mArray;
    private LayoutInflater mInflater;

    public PessoaAdapter(Context context, ArrayList<Pessoa> array){
        this.mArray = array;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mArray.size();
    }

    @Override
    public Object getItem(int position) {
        return mArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView Id, Nome;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.painel_principal, null);
        }
        Id = convertView.findViewById(R.id.lblId);
        Nome = convertView.findViewById(R.id.lblNome);

        final Pessoa p = mArray.get(position);
        Id.setText( String.valueOf(p.getId()));
        Nome.setText( p.getNome());

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(p.getId() > 0 ) {
                    new AlertDialog.Builder(mContext)
                            .setTitle(R.string.atencao)
                            .setMessage(R.string.deseja)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    DBAccess dbAccess = new DBAccess(mContext);
                                    dbAccess.deletePessoa(p.getId());
                                    (( Activity3 )mContext).showPessoas();

                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                }
                else {
                    Toast.makeText(mContext, R.string.impossivel,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
