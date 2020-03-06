package com.hp.bookaholic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.florent37.shapeofview.shapes.ArcView;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.Models.BooklistModel;
import com.hp.bookaholic.R;

public class BookList_Adapter extends RecyclerView.Adapter<BookList_Adapter.BookVH> {
    private AppPreferences appPreferences;

    public BookList_Adapter(BooklistModel booklistModel, Context context) {
        this.booklistModel = booklistModel;
        this.context = context;
    }

    BooklistModel booklistModel;
    Context context;


    @NonNull
    @Override
    public BookVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchview= LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylayout, parent, false);
        appPreferences    = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));

        return new BookVH(searchview);

    }

    @Override
    public void onBindViewHolder(@NonNull BookVH holder, int position) {
        Glide.with(context)
                .load(booklistModel.getBook_Details().get(position).getPhoto())
                .placeholder(R.drawable.bookround)
                .into(holder.bookimg);

        holder.bookname.setText(booklistModel.getBook_Details().get(position).getBook_name());
//
//holder.bookimg.setOnClickListener(v -> {
//  //  Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
//    appPreferences.saveData("book_id",booklistModel.getBook_Details().get(position).getBook_id());
//    appPreferences.saveData("user_id",booklistModel.getBook_Details().get(position).getUser_id());
//
//    Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_book_dtails);
//
//});

holder.cardView.setOnClickListener(v -> {
    Toast.makeText(context, "card click", Toast.LENGTH_SHORT).show();
});

    }

    @Override
    public int getItemCount() {
        return booklistModel.getBook_Details().size();
    }

    class BookVH extends RecyclerView.ViewHolder{
ImageView bookimg;
TextView bookname;
View mView;
CardView cardView;

        public BookVH(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            bookimg=itemView.findViewById(R.id.product_image);
            bookname=itemView.findViewById(R.id.product_rate);
            cardView=itemView.findViewById(R.id.card);



        }
    }

}
