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
import com.hp.bookaholic.EndUsers.Models.MyBooksModel;
import com.hp.bookaholic.R;

public class MyBookAdapter extends RecyclerView.Adapter<MyBookAdapter.MybookVH> {

    MyBooksModel myBooksModel;
    Context context;
    private AppPreferences appPreferences;

    public MyBookAdapter(MyBooksModel myBooksModel, Context context) {
        this.myBooksModel = myBooksModel;
        this.context = context;
    }

    @NonNull
    @Override
    public MybookVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchview = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybookitem, parent, false);
        appPreferences = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));

        return new MybookVH(searchview);
    }

    @Override
    public void onBindViewHolder(@NonNull MybookVH holder, int position) {

        Glide.with(context).load(myBooksModel.getMyBook_Details().get(position).getPhoto()).into(holder.bookimg);
        holder.bookname.setText(myBooksModel.getMyBook_Details().get(position).getBook_name());
holder.cardView.setOnClickListener(v -> {

    appPreferences.saveData("returnbook_id",myBooksModel.getMyBook_Details().get(position).getBook_id());


    Navigation.findNavController(v).navigate(R.id.action_myBooks_to_returnBookFragmnet);

});
    }

    @Override
    public int getItemCount() {
        return myBooksModel.getMyBook_Details().size();
    }

    class MybookVH extends RecyclerView.ViewHolder{
        ImageView bookimg;
        TextView bookname;
        View mView;
        CardView cardView;
        public MybookVH(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            bookimg = itemView.findViewById(R.id.product_image);
            bookname = itemView.findViewById(R.id.product_rate);
            cardView = itemView.findViewById(R.id.card);
        }
    }


}
