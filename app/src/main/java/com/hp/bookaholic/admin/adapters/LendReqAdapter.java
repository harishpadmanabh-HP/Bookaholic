package com.hp.bookaholic.admin.adapters;

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
import com.hp.bookaholic.R;
import com.hp.bookaholic.admin.modelsAdmin.Lend_Requests_Model;

public class LendReqAdapter extends RecyclerView.Adapter<LendReqAdapter.LendVH> {

    Lend_Requests_Model lend_requests_model;
    Context context;
    private AppPreferences appPreferences;

    public LendReqAdapter(Lend_Requests_Model lend_requests_model, Context context) {
        this.lend_requests_model = lend_requests_model;
        this.context = context;
    }

    @NonNull
    @Override
    public LendVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View searchview = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylayout, parent, false);
        appPreferences = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));

        return new LendVH(searchview);
    }

    @Override
    public void onBindViewHolder(@NonNull LendVH holder, int position) {

        Glide.with(context).load(lend_requests_model.getBook_Details().get(position).getPhoto()).into(holder.bookimg);
        holder.bookname.setText(lend_requests_model.getBook_Details().get(position).getBook_name());

        holder.cardView.setOnClickListener(v -> {

            appPreferences.saveData("book_id",lend_requests_model.getBook_Details().get(position).getBook_id() );
            appPreferences.saveData("user_id", lend_requests_model.getBook_Details().get(position).getUser_id());
            appPreferences.saveData("user_name", lend_requests_model.getBook_Details().get(position).getUser_details().getName());
            appPreferences.saveData("user_email", lend_requests_model.getBook_Details().get(position).getUser_details().getEmail());
            appPreferences.saveData("user_phone", lend_requests_model.getBook_Details().get(position).getUser_details().getPhone());
            appPreferences.saveData("user_address", lend_requests_model.getBook_Details().get(position).getUser_details().getAddress());
            Navigation.findNavController(v).navigate(R.id.action_lendRequestFrag_to_lendRequestDetails);


        });



    }

    @Override
    public int getItemCount() {
        return lend_requests_model.getBook_Details().size();
    }

    class LendVH extends RecyclerView.ViewHolder{
        ImageView bookimg;
        TextView bookname;
        View mView;
        CardView cardView;
        public LendVH(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            bookimg = itemView.findViewById(R.id.product_image);
            bookname = itemView.findViewById(R.id.product_rate);
            cardView = itemView.findViewById(R.id.card);

        }
    }
}
