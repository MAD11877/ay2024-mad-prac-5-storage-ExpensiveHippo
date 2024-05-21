package sg.edu.np.mad.madpractical5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Generate Users
        ArrayList<User> userArrayList =  new ArrayList<>();
        for (int i = 0; i < 20; i ++) {
            String name = "Name" + String.valueOf(new Random().nextInt(9999999)) ;
            String description = "Description" + String.valueOf(new Random().nextInt(9999999));
            boolean followed = new Random().nextBoolean();

            User temp = new User(name, description, i, followed);
            userArrayList.add(temp);
        }

        RecyclerView rView = findViewById(R.id.rView);
        UserAdapter userAdapter = new UserAdapter(userArrayList, ListActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ListActivity.this);
        rView.setAdapter(userAdapter);
        rView.setLayoutManager(layoutManager);
        rView.setItemAnimator(new DefaultItemAnimator());
    }
}