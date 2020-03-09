package com.hp.bookaholic.EndUsers.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.bookaholic.EndUsers.Models.AvailableBookListModel;
import com.hp.bookaholic.EndUsers.Models.BooklistModel;
import com.hp.bookaholic.R;

public class BookList_Adapter extends RecyclerView.Adapter<BookList_Adapter.BookVH> {
    private AppPreferences appPreferences;
    AvailableBookListModel availableBookListModel;
    Context context;

    public BookList_Adapter(AvailableBookListModel availableBookListModel, Context context) {
        this.availableBookListModel = availableBookListModel;
        this.context = context;
    }

    @NonNull
    @Override
    public BookVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchview = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylayout, parent, false);
        appPreferences = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));

        return new BookVH(searchview);

    }

    @Override
    public void onBindViewHolder(@NonNull BookVH holder, int position) {
        Glide.with(context)
                .load(availableBookListModel.getAvailable_Details().get(position).getPhoto())
                .placeholder(R.drawable.bookround)
                .into(holder.bookimg);

        holder.bookname.setText(availableBookListModel.getAvailable_Details().get(position).getBook_name());
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

            appPreferences.saveData("book_id", availableBookListModel.getAvailable_Details().get(position).getBook_id());
            appPreferences.saveData("owner_id", availableBookListModel.getAvailable_Details().get(position).getUser_id());

            Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_book_dtails);

        });

    }

    @Override
    public int getItemCount() {
        return availableBookListModel.getAvailable_Details().size();
    }

    class BookVH extends RecyclerView.ViewHolder {
        ImageView bookimg;
        TextView bookname;
        View mView;
        CardView cardView;

        public BookVH(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            bookimg = itemView.findViewById(R.id.product_image);
            bookname = itemView.findViewById(R.id.product_rate);
            cardView = itemView.findViewById(R.id.card);


        }
    }

}
