package im.bcs.task.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;

import im.bcs.task.AdvisorDetail;
import im.bcs.task.Model.AdvisorInfo;
import im.bcs.task.R;

/**
 * Created by Opriday on 7/5/2018.
 */

public class GridViewAdapter extends BaseAdapter {

    ArrayList<AdvisorInfo> advisorInfoList;
    AdvisorInfo info;
    Context context;
    Grid_ViewHolder viewHolder;
    String ImageUrl = "http://live.psychictxt.net/public/advisor_img/";
    int pos;

    public GridViewAdapter(Context context, ArrayList<AdvisorInfo> advisorInfoList) {
        this.advisorInfoList = advisorInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return advisorInfoList.size();
    }

    @Override
    public Object getItem(int i) {

        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        pos = position;

        if (view == null) {
            viewHolder = new Grid_ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_view, null);
            viewHolder.image = (ImageView) view.findViewById(R.id.image);
            viewHolder.credit = (TextView) view.findViewById(R.id.credit);
            viewHolder.status = (ImageView) view.findViewById(R.id.status);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.description = (TextView) view.findViewById(R.id.description);
            viewHolder.chat = (TextView) view.findViewById(R.id.chat);
            view.setTag(viewHolder);

        } else {
            viewHolder = (Grid_ViewHolder) view.getTag();
        }
        if (advisorInfoList.get(position).getStatus().equals("1") && (advisorInfoList.get(position).getLiveStatus().equals("1") || advisorInfoList.get(position).getLiveStatus().equals("2"))) {
            viewHolder.status.setImageDrawable(context.getResources().getDrawable(R.drawable.status_live_background));
        } else if (advisorInfoList.get(position).getStatus().equals("1")) {
            viewHolder.status.setImageDrawable(context.getResources().getDrawable(R.drawable.status_on_background));
        } else if (advisorInfoList.get(position).getStatus().equals("0")) {
            viewHolder.status.setImageDrawable(context.getResources().getDrawable(R.drawable.status_off_background));
        }
        Picasso.with(context).load(ImageUrl + advisorInfoList.get(position).getProfilePicture()).fit().into(viewHolder.image);
        viewHolder.credit.setText(advisorInfoList.get(position).getLiveRate() + " Cr/Min");
        viewHolder.name.setText(advisorInfoList.get(position).getFirstName());
        String t = Html.fromHtml(advisorInfoList.get(position).getShortDescription()).toString();
        String toServerUnicodeEncoded = StringEscapeUtils.unescapeJava(t);
        viewHolder.description.setText(toServerUnicodeEncoded);

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AdvisorDetailActivity = new Intent(context, AdvisorDetail.class);
                AdvisorDetailActivity.putExtra("UserId", advisorInfoList.get(position).getId());
                context.startActivity(AdvisorDetailActivity);

            }
        });
        return view;
    }

    public void setAdvisorInfoList(ArrayList<AdvisorInfo> advisorInfoList) {
        this.advisorInfoList = advisorInfoList;
        notifyDataSetChanged();

    }
}
