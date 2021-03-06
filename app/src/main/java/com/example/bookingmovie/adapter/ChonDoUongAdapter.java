package com.example.bookingmovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookingmovie.R;
import com.example.bookingmovie.model.DichVu;

import java.util.ArrayList;
import java.util.List;

public class ChonDoUongAdapter extends RecyclerView.Adapter<ChonDoUongAdapter.ChonDoUongViewHodler> {
    private Context mContext;
    private List<DichVu> mThucUong;
    private ArrayList<Integer> mSelectedList = new ArrayList<>();

    public ChonDoUongAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<DichVu> list){
        this.mThucUong=list;
        notifyDataSetChanged();
    }

    public interface OnSelectedChangedListener {
        void onSelectedChanged(List<Integer> selects);
    }
    private OnSelectedChangedListener mListener;
    public void setOnSelectedChangedListener(OnSelectedChangedListener listener) {
        mListener = listener;
    }
    @NonNull
    @Override
    public ChonDoUongViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khuyen_mai,parent,false);
        return  new ChonDoUongViewHodler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChonDoUongViewHodler holder, int position) {
        DichVu thucUong= mThucUong.get(position);

        if(thucUong==null){
            return;
        }
        Glide.with(mContext).load(thucUong.getImgdichvu()).into(holder.imgThucUong);
        // holder.imgThucUong.setImageResource(thucUong.getImgdichvu());
        holder.tvTenThucUong.setText(thucUong.getTenDichVu());
        holder.tvNoidung.setText(thucUong.getNoidungDV());
        holder.tvCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add();
                int sl=thucUong.getSoluong();
                sl=sl+1;
                thucUong.setSoluong(sl);
                holder.tvSoLuong.setText(String.valueOf(sl));
                mSelectedList.add(position);
                notifyDataSetChanged();
                if(mListener!=null) mListener.onSelectedChanged(mSelectedList);
            }
        });
        holder.tvTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sub();
                int sl=thucUong.getSoluong();
                if(sl>0){
                    sl=sl-1;
                    thucUong.setSoluong(sl);
                    holder.tvSoLuong.setText(String.valueOf(sl));
                    if(mSelectedList.size()>0) {
                        mSelectedList.remove((Integer)position);
                        notifyDataSetChanged();
                        if (mListener != null) mListener.onSelectedChanged(mSelectedList);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mThucUong.size()!=0){
            return mThucUong.size();
        }
        return 0;
    }


    public class ChonDoUongViewHodler extends RecyclerView.ViewHolder{
        private ImageView imgThucUong;
        private TextView tvTenThucUong;
        private TextView tvNoidung;
        private TextView tvSoLuong;
        private TextView tvCong;
        private TextView tvTru;
        public ChonDoUongViewHodler(@NonNull View itemView) {
            super(itemView);
            imgThucUong=itemView.findViewById(R.id.img_thucuong);
            tvTenThucUong=itemView.findViewById(R.id.tv_tenThucUong);
            tvNoidung=itemView.findViewById(R.id.tv_noidung);
            tvSoLuong=itemView.findViewById(R.id.tv_soluong);
            tvCong=itemView.findViewById(R.id.tv_cong);
            tvTru=itemView.findViewById(R.id.tv_tru);

        }
    }
}
//cha bỏ cái quay lại chỗ đó đi đc k
//nhưng nếu thế thì chỗ đó hơi kì
//chỗ đso thì vẫ để á
//bỏ chỗ kia thôi chứ để thấy lỗi á kiểu qay lại đvé
//thì thì phải tạo 2 trang ticket, mình đang cho 2 cái cùng hiển thị một cái
//theo mk tạo 2 trng di