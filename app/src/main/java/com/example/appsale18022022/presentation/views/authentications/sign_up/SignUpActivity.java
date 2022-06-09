package com.example.appsale18022022.presentation.views.authentications.sign_up;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.appsale18022022.R;
import com.example.appsale18022022.data.datasources.remote.AppResource;
import com.example.appsale18022022.data.models.User;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText inputName, inputEmail, inputPassword, inputPhone, inputAddress;
    LinearLayout signUp;
    LinearLayout layoutLoading;
    SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputName = findViewById(R.id.textEditName);
        inputEmail = findViewById(R.id.textEditEmail);
        inputPassword = findViewById(R.id.textEditPassword);
        inputPhone = findViewById(R.id.textEditPhone);
        inputAddress = findViewById(R.id.textEditLocation);
        signUp = findViewById(R.id.sign_up);
        layoutLoading = findViewById(R.id.layout_loading);

        signUpViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new SignUpViewModel(getApplicationContext());
            }
        }).get(SignUpViewModel.class);
        event();
    }

    private void event() {
        signUpViewModel.getUserData().observe(this, new Observer<AppResource<User>>() {
            @Override
            public void onChanged(AppResource<User> userAppResource) {
                switch (userAppResource.status) {
                    case LOADING:
                        layoutLoading.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        layoutLoading.setVisibility(View.GONE);
                        finish();
                        break;
                    case ERROR:
                        Toast.makeText(SignUpActivity.this, userAppResource.message, Toast.LENGTH_SHORT).show();
                        layoutLoading.setVisibility(View.GONE);
                        break;
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String name = inputName.getText().toString();
                String address = inputAddress.getText().toString();
                String phone = inputPhone.getText().toString();
                String password = inputPassword.getText().toString();

                if (email.isEmpty() || name.isEmpty() || address.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Bạn chưa truyền đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                signUpViewModel.register(email, password, phone, name, address);
            }
        });
    }
}
