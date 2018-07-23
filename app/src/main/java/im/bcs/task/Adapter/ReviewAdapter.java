package im.bcs.task.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import im.bcs.task.Model.ReviewInfo;
import im.bcs.task.R;

/**
 * Created by Opriday on 7/6/2018.
 */

public class ReviewAdapter extends ArrayAdapter<ReviewInfo> {

    Context context;
    ArrayList<ReviewInfo> ReviewInfoList;
    Review_ViewHolder viewHolder;

    public ReviewAdapter(@NonNull Context context, int resource, ArrayList<ReviewInfo> ReviewInfoList) {
        super(context, resource);
        this.context = context;
        this.ReviewInfoList = ReviewInfoList;
    }

    @Override
    public int getCount() {
        return ReviewInfoList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new Review_ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.advisordetail_review_layout, null);
            viewHolder.username = (TextView) convertView.findViewById(R.id.username_review);
            viewHolder.comment = (TextView) convertView.findViewById(R.id.comment_review);
            viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar_review);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Review_ViewHolder) convertView.getTag();
        }
        viewHolder.username.setText(ReviewInfoList.get(position).getClient_username());
        viewHolder.comment.setText(ReviewInfoList.get(position).getComment());
        viewHolder.ratingBar.setRating(Float.parseFloat(ReviewInfoList.get(position).getReview().toString()));

        return convertView;
    }

    public void setReviewInfoList(ArrayList<ReviewInfo> ReviewInfoList) {
        this.ReviewInfoList = ReviewInfoList;
        notifyDataSetChanged();

    }
}
