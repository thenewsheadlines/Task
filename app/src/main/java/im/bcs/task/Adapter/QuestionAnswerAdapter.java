package im.bcs.task.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import im.bcs.task.Model.QuestionAnswerInfo;
import im.bcs.task.Model.ReviewInfo;
import im.bcs.task.R;

/**
 * Created by Opriday on 7/23/2018.
 */

public class QuestionAnswerAdapter extends ArrayAdapter<QuestionAnswerInfo> {

    Context context;
    ArrayList<QuestionAnswerInfo> QuestionAnsInfo;
    QuestionAnswer_ViewHolder QAvHolder;

    public QuestionAnswerAdapter(@NonNull Context context, int resource,ArrayList<QuestionAnswerInfo> QuestionAnswerInfo) {
        super(context, resource);
        this.context = context;
        this.QuestionAnsInfo = QuestionAnswerInfo;
    }

    @Override
    public int getCount() {
        return QuestionAnsInfo.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            QAvHolder = new QuestionAnswer_ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_faq_orderdetail, null);
            QAvHolder.question = (TextView) convertView.findViewById(R.id.question_FAQ);
            QAvHolder.answer = (TextView) convertView.findViewById(R.id.answer_FAQ);
            convertView.setTag(QAvHolder);
        } else {
            QAvHolder = (QuestionAnswer_ViewHolder) convertView.getTag();
        }
        QAvHolder.question.setText(Html.fromHtml(QuestionAnsInfo.get(position).getQuestion()).toString());
        QAvHolder.answer.setText(Html.fromHtml(QuestionAnsInfo.get(position).getAnswer()).toString());
        return convertView;
    }

    public void setQuestionAnsInfoList(ArrayList<QuestionAnswerInfo> QuestionAnswerInfo) {
        this.QuestionAnsInfo = QuestionAnswerInfo;
        notifyDataSetChanged();

    }
}
