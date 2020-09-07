package com.example.fivecontacts.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fivecontacts.R;
import com.example.fivecontacts.main.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends ArrayAdapter<Contact> {

    private Context mContext;
    int mResource;
    private List<Contact> contactList = new ArrayList<>();

    public ContactListAdapter(Context context, int resource, List<Contact> contacts){
        super(context, resource, contacts);
        mContext = context;
        contactList = contacts;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        String name = getItem(position).getName();
        String phone = getItem(position).getPhone();


        Contact contact = new Contact(name, phone);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);


        tvName.setText(name);
        tvPhone.setText(phone);



        return convertView;

    }
}
