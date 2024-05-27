package sg.edu.np.mad.madpractical5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        FirebaseDatabase db = FirebaseDatabase.getInstance("https://madpractical5-8b3a4-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference ref = db.getReference("Users/mad");
        Log.i("Firebase", String.valueOf(ref));
        Button btnLogin = findViewById(R.id.btnLogin);
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(etUsername.getText());
                String password = String.valueOf(etPassword.getText());

                etUsername.getText().clear();
                etPassword.getText().clear();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Username or Password field is empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, String.valueOf(task.getException()), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        DataSnapshot snapshot = task.getResult();
                        if (snapshot.exists()) {
                            String checkUsername = snapshot.child("username").getValue(String.class);
                            String checkPassword = snapshot.child("password").getValue(String.class);

                            if (username.equals(checkUsername) && password.equals(checkPassword)) {
                                Intent viewList = new Intent(LoginActivity.this, ListActivity.class);
                                startActivity(viewList);
                            }
                        }
                    }
                });
            }
        });
    }
}