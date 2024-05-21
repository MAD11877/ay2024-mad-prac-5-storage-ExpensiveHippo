package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();
        User user = new User("MAD", "MAD Developer", 1, false);

        // Initialize a new User object
        if (extras != null && extras.containsKey("id")) {
            user.setName(extras.getString("name"));
            user.setId(extras.getInt("id"));
            user.setDescription(extras.getString("description"));
            user.setFollowed(extras.getBoolean("followed"));

        }

        // Get the TextViews and Button from the layout
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDesc = findViewById(R.id.tvDesc);
        Button btnFollow = findViewById(R.id.btnFollow);
        Button btnMessage = findViewById(R.id.btnMessage);

        // Set the TextViews with the user's name, description and default button message
        tvName.setText(user.name);
        tvDesc.setText(user.description);

        if (user.followed) {
            btnFollow.setText("Unfollow");
        } else {
            btnFollow.setText("Follow");
        }

        // Set event listener for follow button
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getFollowed()) {
                    btnFollow.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                } else {
                    btnFollow.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                }
                user.toggleFollow();
            }
        });
    }
}