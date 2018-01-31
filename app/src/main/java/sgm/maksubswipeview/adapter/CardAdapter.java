package sgm.maksubswipeview.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

import sgm.maksubswipeview.R;
import sgm.maksubswipeview.modul.CardObject;
import sgm.maksubswipeview.view.CircleImageView;

/**
 * Created by DinhDV on 22/01/2018.
 */

public class CardAdapter extends ArrayAdapter<CardObject> {
    private ArrayList<CardObject> mListUser = new ArrayList<>();
    private onActionSendRequest mAction;
    private Context mActivity;

    public CardAdapter(@NonNull Context context, @NonNull ArrayList<CardObject> objects) {
        super(context, 0, objects);
        mListUser = objects;
        mActivity = context;
    }

    public void remove(int index) {
        if (index > -1 && index < mListUser.size()) {
            mListUser.remove(index);
            notifyDataSetChanged();
        }
    }

    //
//    @Override
//    public int getCount() {
//        return mListUser.size();
//    }
//
    @Override
    public CardObject getItem(int position) {
        if (mListUser == null || mListUser.size() == 0) return null;
        return mListUser.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        CardObject user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
            holder = new ViewHolder();
            holder.ivSubcategory = convertView.findViewById(R.id.imv_avatar);
            holder.mTvName = convertView.findViewById(R.id.tv_name);
            holder.mTvDescription = convertView.findViewById(R.id.tv_description);
            holder.mBtnSend = convertView.findViewById(R.id.btn_request_add);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mActivity).asBitmap().load(position % 2 == 0 ? "https://hinhanhdephd.com/wp-content/uploads/2016/08/anh-girl-xinh-1998-1.jpg" : "http://i.a4vn.com/2017/10/18/tong-hop-girl-xinh-vong-1-cang-tron-thang-10-b41a4c.jpg").into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                holder.ivSubcategory.setImageBitmap(resource);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                holder.ivSubcategory.setImageResource(R.mipmap.ic_launcher);
            }
        });
        holder.mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAction != null)
                    mAction.onSend(0);
            }
        });

        holder.mTvName.setText(user.getId());
        holder.mTvDescription.setText("先生の紹介テキスト３行まで。先生の紹介テキスト３行まで先生の紹介テキスト３行まで先生の紹介テキスト３行まで先生の紹介テキスト３行まで先生の紹介テキスト３行まで長いテキストの場合最後にを付けまししょう。先生の...");
        return convertView;
    }

    private class ViewHolder {
        CircleImageView ivSubcategory;
        TextView mTvName, mTvDescription;
        Button mBtnSend;
    }

    public void setListener(onActionSendRequest action) {
        mAction = action;
    }

    public interface onActionSendRequest {
        void onSend(int position);
    }

}
