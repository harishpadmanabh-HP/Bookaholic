package com.hp.bookaholic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hp.bookaholic.Models.BooklistModel;
import com.hp.bookaholic.R;

public class Adapter extends RecyclerView.Adapter<Adapter.My_vh> {
    Context context;
    BooklistModel booklistModel;

    public Adapter(Context context, BooklistModel booklistModel) {
        this.context = context;
        this.booklistModel = booklistModel;
    }

    @NonNull
    @Override
    public My_vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categorylayout, parent, false);


        return new My_vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_vh holder, int position) {
        Glide.with(context)
                .load(booklistModel.getBook_Details().get(position).getPhoto())
                .placeholder(R.drawable.bookround)
                .into(holder.book_image);

        holder.book_name.setText(booklistModel.getBook_Details().get(position).getBook_name());


    }

    @Override
    public int getItemCount() {
        return booklistModel.getBook_Details().size();
    }

    class My_vh extends RecyclerView.ViewHolder {
        ImageView book_image;
        TextView book_name;

        public My_vh(@NonNull View itemView) {
            super(itemView);
            book_image = itemView.findViewById(R.id.product_image);
            book_name = itemView.findViewById(R.id.product_rate);
        }
    }
}
