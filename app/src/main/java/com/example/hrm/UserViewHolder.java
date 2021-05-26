package com.example.hrm;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private TextView txtUserName;
    private TextView txtUserEmail;
    private TextView txtDate;
    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        txtUserEmail = itemView.findViewById(R.id.txt_email);
        txtUserName = itemView.findViewById(R.id.txt_name);
        txtDate = itemView.findViewById(R.id.txt_date);
    }
    public void setName(String name) {
        this.txtUserName.setText(name);
    }
    public void setEmail(String email) {
        this.txtUserEmail.setText(email);
    }
    public void setDate(String date) {
        this.txtDate.setText(date);
    }
}
