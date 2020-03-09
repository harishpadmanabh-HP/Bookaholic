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
import com.hp.bookaholic.admin.modelsAdmin.Booklists_For_Approval_Model;

public class ApprovalList_Adapter extends RecyclerView.Adapter<ApprovalList_Adapter.ApprovalVH> {

    Context context;
    Booklists_For_Approval_Model booklists_for_approval_model;
    private AppPreferences appPreferences;

    public ApprovalList_Adapter(Context context, Booklists_For_Approval_Model booklists_for_approval_model) {
        this.context = context;
        this.booklists_for_approval_model = booklists_for_approval_model;
    }


    @NonNull
    @Override
    public ApprovalVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View searchview = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylayout, parent, false);
        appPreferences = AppPreferences.getInstance(context, context.getResources().getString(R.string.app_name));

        return new ApprovalVH(searchview);
    }

    @Override
    public void onBindViewHolder(@NonNull ApprovalVH holder, int position) {

        Glide.with(context).load(booklists_for_approval_model.getBook_Details().get(position).getPhoto()).into(holder.bookimg);
        holder.bookname.setText(booklists_for_approval_model.getBook_Details().get(position).getBook_name());

        holder.cardView.setOnClickListener(v -> {

            appPreferences.saveData("book_id",booklists_for_approval_model.getBook_Details().get(position).getBook_id() );
            appPreferences.saveData("user_id", booklists_for_approval_model.getBook_Details().get(position).getUser_id());
            appPreferences.saveData("user_name", booklists_for_approval_model.getBook_Details().get(position).getUser_details().getName());
            appPreferences.saveData("user_email", booklists_for_approval_model.getBook_Details().get(position).getUser_details().getEmail());
            appPreferences.saveData("user_phone", booklists_for_approval_model.getBook_Details().get(position).getUser_details().getPhone());
            appPreferences.saveData("user_address", booklists_for_approval_model.getBook_Details().get(position).getUser_details().getAddress());


            Navigation.findNavController(v).navigate(R.id.action_approve_books_frag_to_approvalFrag);

        });


    }

    @Override
    public int getItemCount() {
        return booklists_for_approval_model.getBook_Details().size();
    }

    class ApprovalVH extends RecyclerView.ViewHolder{
        ImageView bookimg;
        TextView bookname;
        View mView;
        CardView cardView;
        public ApprovalVH(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            bookimg = itemView.findViewById(R.id.product_image);
            bookname = itemView.findViewById(R.id.product_rate);
            cardView = itemView.findViewById(R.id.card);

        }
    }

}
