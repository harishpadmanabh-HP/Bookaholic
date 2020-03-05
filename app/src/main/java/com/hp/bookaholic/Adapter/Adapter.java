package com.hp.bookaholic.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.Book_dtails;
import com.hp.bookaholic.Models.BookdetailsModel;
import com.hp.bookaholic.Models.BooklistModel;
import com.hp.bookaholic.R;

public class Adapter extends RecyclerView.Adapter<Adapter.My_vh> {
    Context context;
    BooklistModel booklistModel;
AppPreferences appPreferences;
    public Adapter(Context context, BooklistModel booklistModel) {
        this.context = context;
        this.booklistModel = booklistModel;
    }

    @NonNull
    @Override
    public My_vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categorylayout, parent, false);
        appPreferences    = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));


        return new My_vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_vh holder, int position) {
        holder.book_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPreferences.saveData("book_id",booklistModel.getBook_Details().get(position).getBook_id());
                appPreferences.saveData("user_id",booklistModel.getBook_Details().get(position).getUser_id());


Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_book_dtails);

            }
        });
        Glide.with(context)
                .load(booklistModel.getBook_Details().get(position).getPhoto())
                .placeholder(R.drawable.bookround)
                .into(holder.book_image);

        holder.book_name.setText(booklistModel.getBook_Details().get(position).getBook_name());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        appPreferences.saveData("book_id",booklistModel.getBook_Details().get(position).getBook_id());
        appPreferences.saveData("user_id",booklistModel.getBook_Details().get(position).getUser_id());

        NavHostFragment.findNavController(new Book_dtails());


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
