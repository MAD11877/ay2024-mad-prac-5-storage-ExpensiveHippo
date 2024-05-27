package sg.edu.np.mad.madpractical5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    ArrayList<User> userList;
    Context parentContext;
    public UserAdapter(ArrayList<User> input, Context context) {
        this.userList = input;
        this.parentContext = context;
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_activity_list, parent, false);

        return new UserViewHolder(item);
    }

    public void onBindViewHolder(UserViewHolder holder, int position) {
        User s = userList.get(position);
        String sName = s.getName();
        int sId = s.getId();
        String sDesc = s.getDescription();
        Boolean sFollowed = s.getFollowed();

        holder.name.setText(sName);
        holder.desc.setText(s.getDescription());
        if (!sName.endsWith("7")) {
            holder.largeIcon.setVisibility(View.GONE);
        }
        holder.smallIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("here");
                AlertDialog.Builder builder = new AlertDialog.Builder(parentContext);

                builder.setTitle("Profile");
                builder.setMessage(sName);
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle info = new Bundle();
                        info.putString("name", sName);
                        info.putInt("id", sId);
                        info.putString("description", sDesc);
                        info.putBoolean("followed", sFollowed);
                        Intent viewProfile = new Intent(parentContext, MainActivity.class);
                        viewProfile.putExtras(info);
                        parentContext.startActivity(viewProfile);
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    public int getItemCount() {
        return userList.size();
    }
}
