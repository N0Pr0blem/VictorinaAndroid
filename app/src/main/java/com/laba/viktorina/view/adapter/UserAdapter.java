package com.laba.viktorina.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laba.viktorina.R;
import com.laba.viktorina.data.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private List<User> users;

    public UserAdapter(List<User> users){
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_user_score, parent, false);
        return new UserViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.btnScore.setText(user.getScore().toString());
        holder.txtUsername.setText(user.getName());
        holder.txtDifficulty.setText(user.getDifficulty().name());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView txtUsername;
        TextView txtDifficulty;
        Button btnScore;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername = itemView.findViewById(R.id.txt_username);
            btnScore = itemView.findViewById(R.id.btn_score);
            txtDifficulty = itemView.findViewById(R.id.txt_difficulty);
        }
    }
}
