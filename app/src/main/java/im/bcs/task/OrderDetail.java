package im.bcs.task;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import im.bcs.task.Adapter.QuestionAnswerAdapter;
import im.bcs.task.Interface.IFAQ_Presenter;
import im.bcs.task.Model.QuestionAnswerInfo;
import im.bcs.task.Presenters.FAQ_Presenter;
import im.bcs.task.Util.Constant;

public class OrderDetail extends AppCompatActivity implements View.OnClickListener,IFAQ_Presenter {
    CircleImageView advisorProfile;
    TextView adviosrName,advisorDescription,textReading,enhancedVideo,premiumReading,submit;
    ListView listView;
    ArrayList<QuestionAnswerInfo> QuestionAnswerInfo = new ArrayList<>();
    QuestionAnswerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        IntializeUI();

        FAQ_Presenter presenter = new FAQ_Presenter(this);
        presenter.getAnswerQuestion(Constant.FAQ_Url);
        adapter = new QuestionAnswerAdapter(this,R.layout.item_faq_orderdetail,QuestionAnswerInfo);
        listView.setAdapter(adapter);
    }

    private void IntializeUI() {
        advisorProfile = (CircleImageView) findViewById(R.id.advisor_profile_image_orderDetail);
        adviosrName = (TextView) findViewById(R.id.advisor_name_orderDetail);
        advisorDescription = (TextView) findViewById(R.id.advisor_description_orderDetail);
        textReading = (TextView) findViewById(R.id.textReading_orderDetail);
        enhancedVideo = (TextView) findViewById(R.id.enhancedVideo_orderDetail);
        premiumReading = (TextView) findViewById(R.id.premiumReading_orderDetail);
        submit = (TextView) findViewById(R.id.submit_orderDetail);
        listView = (ListView) findViewById(R.id.listView_orderDetail);

        Picasso.with(OrderDetail.this).load(getIntent().getStringExtra(Constant.ADVISOR_IMAGE)).fit().into(advisorProfile);
        adviosrName.setText(getIntent().getStringExtra(Constant.ADVISOR_NAME));
        advisorDescription.setText(getIntent().getStringExtra(Constant.ADVISOR_DESCRIPTION));


        textReading.setOnClickListener(this);
        enhancedVideo.setOnClickListener(this);
        premiumReading.setOnClickListener(this);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.textReading_orderDetail){

        }else if (v.getId() == R.id.enhancedVideo_orderDetail){

        } else if (v.getId() == R.id.premiumReading_orderDetail){

        }else if (v.getId() == R.id.submit_orderDetail){

        }
    }

    @Override
    public void onGetFAQInfoList(ArrayList<QuestionAnswerInfo> QuestionAnswerInfo) {
        adapter.setQuestionAnsInfoList(QuestionAnswerInfo);
    }
}
