package com.balu0694.firebaseauth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmail, edtPwd;
    Button btnRegister;
    TextView txtLogin;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edtEmail);
        edtPwd = findViewById(R.id.edtPwd);

        btnRegister = findViewById(R.id.btn_register);
        txtLogin = findViewById(R.id.txtLogin);

        progressDialog = new ProgressDialog(this);

        //init click Listener
        btnRegister.setOnClickListener(this);
        txtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnRegister) {
            String stEmail = edtEmail.getText().toString().trim();
            String stPwd = edtPwd.getText().toString().trim();

            if (TextUtils.isEmpty(stEmail)) {
                Toast.makeText(this, "Please enter the email id", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(stPwd)) {
                Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show();
            } else {
                progressDialog.setMessage("Registering User.....");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(stEmail, stPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Couldn't Register. Please check again later", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } else if (view == txtLogin) {
            Toast.makeText(this, "Moving to login activity", Toast.LENGTH_SHORT).show();
        }
    }
}
