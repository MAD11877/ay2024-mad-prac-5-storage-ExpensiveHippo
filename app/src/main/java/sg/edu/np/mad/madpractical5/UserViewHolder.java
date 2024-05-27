package sg.edu.np.mad.madpractical5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView desc;
    ImageView smallIcon;
    ImageView largeIcon;
    public UserViewHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.tvName);
        desc = itemView.findViewById(R.id.tvDesc);
        smallIcon = itemView.findViewById(R.id.ivSmallIcon);
        largeIcon = itemView.findViewById(R.id.ivLargeIcon);
    }
}
