package com.example.fivecontacts.main.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fivecontacts.R;
import com.example.fivecontacts.main.model.User;

public class ListaDeContatos_ListView extends AppCompatActivity {

    ListView lv;
    String[] itens ={"Filha", "Filho", "Netinho"};
    String[] numeros ={"tel:000000003435","tel:2000348835","tel:1003435888" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_contatos);



    }

    protected void preencherListaDeContatos (){
        //Vamos montar o ListView

    }

}