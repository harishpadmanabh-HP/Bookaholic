package com.hp.bookaholic.EndUsers.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.EndUsers.Models.BooklistModel;
import com.hp.bookaholic.R;

public class Adapter extends RecyclerView.Adapter<Adapter.My_vh> {
    Context context;
    BooklistModel booklistModel;
AppPreferences appPreferences;
NavController navController = null;
    public Adapter(Context context, BooklistModel booklistModel) {
        this.context = context;
        this.booklistModel = booklistModel;
    }

    @NonNull
    @Override
    public My_vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchview=LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylayout, parent, false);
        appPreferences    = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));


        return new My_vh(searchview);
    }

    @Override
    public void onBindViewHolder(@NonNull My_vh holder, int position) {
        Glide.with(context)
                .load(booklistModel.getBook_Details().get(position).getPhoto())
                .placeholder(R.drawable.bookround)
                .into(holder.book_image);

        holder.book_name.setText(booklistModel.getBook_Details().get(position).getBook_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
        appPreferences.saveData("book_id",booklistModel.getBook_Details().get(position).getBook_id());
        appPreferences.saveData("user_id",booklistModel.getBook_Details().get(position).getUser_id());

        Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_testffrag);

    }
});

    }

    @Override
    public int getItemCount() {
        return booklistModel.getBook_Details().size();
    }

    class My_vh extends RecyclerView.ViewHolder {
        ImageView book_image;
        TextView book_name;
     //   AppPreferences appPreferences;

        public My_vh(@NonNull View itemView) {
            super(itemView);
            book_image = itemView.findViewById(R.id.product_image);
            book_name = itemView.findViewById(R.id.product_rate);
        }
    }
}
