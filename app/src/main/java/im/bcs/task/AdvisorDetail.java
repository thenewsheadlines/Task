package im.bcs.task;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import im.bcs.task.Adapter.ReviewAdapter;
import im.bcs.task.Interface.IPresenter;
import im.bcs.task.Model.AdvisorInfo;
import im.bcs.task.Model.ReviewInfo;
import im.bcs.task.Presenters.Presenter;
import im.bcs.task.Util.Constant;

public class AdvisorDetail extends AppCompatActivity implements IPresenter, View.OnClickListener {


    TextView mNotifyMe, mCreditDetail, mAdvisor_name, mChatWithMe, mTxtMeQuestion, mTotalRieviews;
    TextView footer;
    Button mCancel, mNotifyEveryTime, mNextTimeOnly, mNever, mSave;
    ReviewAdapter adapter;
    ArrayList<AdvisorInfo> advisorInfoList = new ArrayList<>();
    ArrayList<ReviewInfo> ReviewInfoList = new ArrayList<>();
    CircleImageView mAdvisorProfileImage;
    WebView mAboutMe;
    VideoView mVideoView;
    ListView mListView;
    ProgressBar progressBar;
    LinearLayout advisorDetailView;
    Presenter presenter;
    String getUserId;
    Dialog mDialog;
    View v;
    int page = 0, size = 0;
    int type = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor_detail);
        mDialog = onCreateDialog();
        IntializeComponents();
        getUserId = getIntent().getStringExtra("UserId");
        presenter = new Presenter(this, AdvisorDetail.this);
        presenter.getAdvisorProfile(Constant.AdvisorProfileUrl, getUserId);
        adapter = new ReviewAdapter(AdvisorDetail.this, R.layout.advisordetail_review_layout, ReviewInfoList);
        mListView.setAdapter(adapter);
        mListView.addFooterView(v);

    }

    private void IntializeComponents() {

        progressBar = (ProgressBar) findViewById(R.id.progressBar_ad);
        advisorDetailView = (LinearLayout) findViewById(R.id.advisorDetailView);
        mVideoView = (VideoView) findViewById(R.id.videoView_adetail);
        mNotifyMe = (TextView) findViewById(R.id.notifyMe_adetail);
        mCreditDetail = (TextView) findViewById(R.id.credit_adetail);
        mAdvisor_name = (TextView) findViewById(R.id.advisor_name_adetail);
        mChatWithMe = (TextView) findViewById(R.id.chatWithMe_adetail);
        mTxtMeQuestion = (TextView) findViewById(R.id.TxtMeAQuestion_adetail);
        mAboutMe = (WebView) findViewById(R.id.about_me_adetail);
        mTotalRieviews = (TextView) findViewById(R.id.totalReviews_adetail);
        mAdvisorProfileImage = (CircleImageView) findViewById(R.id.advisor_profile_image);
        mListView = (ListView) findViewById(R.id.listView_adetail);
        v = LayoutInflater.from(AdvisorDetail.this).inflate(R.layout.footer_listview_ad, null, false);
        footer = (TextView) v.findViewById(R.id.footer_ad);
        mCancel = (Button) mDialog.findViewById(R.id.cancel_dialog);
        mNotifyEveryTime = (Button) mDialog.findViewById(R.id.notifyEveryTime_dialog);
        mNextTimeOnly = (Button) mDialog.findViewById(R.id.nextTimeOnly_dialog);
        mNever = (Button) mDialog.findViewById(R.id.never_dialog);
        mSave = (Button) mDialog.findViewById(R.id.save_dialog);
        footer.setOnClickListener(this);
        mNotifyMe.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mNotifyEveryTime.setOnClickListener(this);
        mNextTimeOnly.setOnClickListener(this);
        mNever.setOnClickListener(this);
        mSave.setOnClickListener(this);
        mTxtMeQuestion.setOnClickListener(this);
    }

    @Override
    public void AdvisorList(ArrayList<AdvisorInfo> advisorInfolist) {
        this.advisorInfoList = advisorInfolist;
        presenter.getAdvisorReviews(Constant.AdvisorReviewsUrl, getUserId, page);
        setDetails();
        type = Integer.parseInt(advisorInfoList.get(0).getSubscribeType());
        Log.e("Subscribe_type",advisorInfoList.get(0).getSubscribeType());
        setSubcribeType(String.valueOf(type));
    }


    @Override
    public void AdvisorDetailReviewList(ArrayList<ReviewInfo> ReviewInfolist) {
        this.ReviewInfoList.addAll(ReviewInfolist); // it will addd new reviews with old reviews.
        adapter.setReviewInfoList(this.ReviewInfoList);
    }

    @Override
    public void onFailure(VolleyError error) {
    }

    private void setDetails() {
        //  setAdapterSize(Integer.parseInt(advisorInfoList.get(0).getReviewCount().toString()));
        mAdvisor_name.setText(advisorInfoList.get(0).getUsername());
        // String getBioDataFromHtml = Html.fromHtml(advisorInfoList.get(0).getBioData()).toString(); // using that line doesn't show Headings In Html on webview.
        //  Log.e("BIODATA",getBioDataFromHtml);
        String decodeBioData = StringEscapeUtils.unescapeJava(advisorInfoList.get(0).getBioData());
        mAboutMe.loadDataWithBaseURL(null, decodeBioData, "text/html", "UTF-8", null);
        mTotalRieviews.setText("REVIEWS(" + advisorInfoList.get(0).getReviewCount().toString() + ")");
        Picasso.with(AdvisorDetail.this).load(Constant.ImageUrl + advisorInfoList.get(0).getDisplayPicture()).fit().into(mAdvisorProfileImage);
        progressBar.setVisibility(View.GONE);
        advisorDetailView.setVisibility(View.VISIBLE);
    }

    public void setSubcribeType(String SubscribeType) {

        if (SubscribeType.contentEquals("2")) {
            mNotifyEveryTime.setBackground(getResources().getDrawable(R.drawable.selectedbut_dialog_background));
        } else if (SubscribeType.contentEquals("1")) {
            mNextTimeOnly.setBackground(getResources().getDrawable(R.drawable.selectedbut_dialog_background));
        } else if (SubscribeType.contentEquals("0")) {
            mNever.setBackground(getResources().getDrawable(R.drawable.selectedbut_dialog_background));
        }
    }

    public void clearSubScribeTypeBack() {
        mNotifyEveryTime.setBackground(getResources().getDrawable(R.drawable.notselectedbut_dialog_background));
        mNextTimeOnly.setBackground(getResources().getDrawable(R.drawable.notselectedbut_dialog_background));
        mNever.setBackground(getResources().getDrawable(R.drawable.notselectedbut_dialog_background));
    }

    public Dialog onCreateDialog() {
        Dialog dialog = new Dialog(AdvisorDetail.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.notfiy_dialog);
        dialog.setCancelable(false);
        return dialog;
    }

    public int pageNumber(int totalItemsCount) {
        page = (int) Math.ceil((double) totalItemsCount / 20);
        Log.e("Ceil-PageNumber", "" + page);
        return page;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.footer_ad) {
            int psge_no = pageNumber(adapter.getCount()); // get adapter size...
            presenter.getAdvisorReviews(Constant.AdvisorReviewsUrl, getUserId, pageNumber(psge_no));
        } else if (v.getId() == R.id.notifyMe_adetail) {
            mDialog.show();
        } else if (v.getId() == R.id.cancel_dialog) {
            mDialog.dismiss();
        } else if (v.getId() == R.id.notifyEveryTime_dialog) {
            type = 2;
            clearSubScribeTypeBack();
            setSubcribeType(String.valueOf(type));
        } else if (v.getId() == R.id.nextTimeOnly_dialog) {
            type = 1;
            clearSubScribeTypeBack();
            setSubcribeType(String.valueOf(type));
        } else if (v.getId() == R.id.never_dialog) {
            type = 0;
            clearSubScribeTypeBack();
            setSubcribeType(String.valueOf(type));
        } else if (v.getId() == R.id.save_dialog) {
          //  onSaveSubscribeType(String.valueOf(type));
            presenter.addSubscribeAdvisor(Constant.AdvisorSubscribeUrl,type,advisorInfoList.get(0).getId());
            mDialog.dismiss();
        } else if (v.getId() == R.id.TxtMeAQuestion_adetail){
            String t = Html.fromHtml(advisorInfoList.get(0).getShortDescription()).toString();
            String shortDescription = StringEscapeUtils.unescapeJava(t);
            Intent toOrderActivity = new Intent(AdvisorDetail.this,Order.class);
            toOrderActivity.putExtra(Constant.ADVISOR_NAME,advisorInfoList.get(0).getFirstName());
            toOrderActivity.putExtra(Constant.ADVISOR_DESCRIPTION,shortDescription);
            toOrderActivity.putExtra(Constant.ADVISOR_IMAGE,Constant.ImageUrl + advisorInfoList.get(0).getDisplayPicture());
            toOrderActivity.putExtra(Constant.ADVISOR_INSTRUCTION,advisorInfoList.get(0).getInstructions());
            startActivity(toOrderActivity);
        }

    }
}
