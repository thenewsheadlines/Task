package im.bcs.task.Presenters;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import im.bcs.task.Interface.IPresenter;
import im.bcs.task.Interface.IVolley;
import im.bcs.task.Model.AdvisorInfo;
import im.bcs.task.Model.ReviewInfo;
import im.bcs.task.NetworkAPI.API_Class;

/**
 * Created by Opriday on 7/5/2018.
 */

public class Presenter implements IVolley {
    IPresenter iPresenter;
    API_Class api_class;
    String tag = "Presenter";

    public Presenter(IPresenter iPresenter, Context context) {
        api_class = new API_Class(context, this); // Creating API_Class object, passing context and IVolley interface as parmeters
        this.iPresenter = iPresenter;
        //this.iPresenter = (IPresenter) context;
    }

    public void getAdviosrList(String url) {
        // isUserNeedReview = false;
        api_class.FetchAdvisorList(url,true);
    }
    public void getAdvisorProfile(String url, String userId) {
        api_class.FetchAdvisorProfile(url,userId,true);
    }
    public void getAdvisorReviews(String url, String userId, int page) {
        api_class.FetchAdvisorReviewsList(url, userId, page,true);
    }
    public void addSubscribeAdvisor(String url, int type, String userId){
        api_class.SubscribeAdvisor(url,type,userId,true);
    }

    @Override
    public void onSuccess(String response, boolean isUserNeedProfile, boolean isUserNeedReview, boolean isUserSubscribe, boolean isUserNeedAdvisorList) {
        Log.e(tag, "Volley Response:" + response);
        if (isUserNeedReview) {
            iPresenter.AdvisorDetailReviewList(ReviewsJsonParsion(response));
        } else if (isUserNeedAdvisorList || isUserNeedProfile){
            iPresenter.AdvisorList(JsonParsing(response, isUserNeedProfile)); // passing advisor list to main activity because this method also implemented in main activity
        } else if (isUserSubscribe){
            Log.e("Subscribe","Response:"+response.toString());
        }else {
            Log.e("QuestionAsnwer","Response:"+response.toString());
        }
    }

    public ArrayList<AdvisorInfo> JsonParsing(String response, boolean isUserNeedReview) {

        ArrayList<AdvisorInfo> advisorInfoList = new ArrayList<>();
        ArrayList<AdvisorInfo> inActiveList = new ArrayList<>();
        ArrayList<AdvisorInfo> ActiveList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject jsonObject1 = jsonObject.getJSONObject("message");
            JSONArray jsonArray = jsonObject1.names(); // coverts jsonsObjects into array...
            Log.e(tag, jsonArray.toString());
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject getObjectFromJsonArray = jsonObject1.getJSONObject(jsonArray.getString(i));
                AdvisorInfo info = new AdvisorInfo();
                info.setId(getObjectFromJsonArray.getString("id"));
                info.setUsername(getObjectFromJsonArray.getString("username"));
                info.setFirstName(getObjectFromJsonArray.getString("first_name"));
                info.setLastName(getObjectFromJsonArray.getString("last_name"));
                if (getObjectFromJsonArray.toString().contains("email") && isUserNeedReview) {
                    info.setEmail(getObjectFromJsonArray.getString("email")); // check if email has contained in the response or isn't.
                }
                info.setBioData(getObjectFromJsonArray.getString("bio_data"));
                info.setDisplayPicture(getObjectFromJsonArray.getString("display_picture"));
                info.setProfilePicture(getObjectFromJsonArray.getString("profile_picture"));
                info.setIntroVideo(getObjectFromJsonArray.getString("intro_video"));
                info.setVideoThumb(getObjectFromJsonArray.getString("video_thumb"));
                info.setLiveStatus(getObjectFromJsonArray.getString("live_status"));
                info.setLiveRate(getObjectFromJsonArray.getString("live_rate"));
                info.setStatus(getObjectFromJsonArray.getString("status"));
                info.setDeactivated(getObjectFromJsonArray.getString("deactivated"));
                info.setIsPsychic(getObjectFromJsonArray.getString("is_psychic"));
                info.setShortDescription(getObjectFromJsonArray.getString("short_description"));
                info.setInstructions(getObjectFromJsonArray.getString("instructions"));
                info.setDisplayOrder(getObjectFromJsonArray.getString("display_order"));
                info.setFeaturedText(getObjectFromJsonArray.getString("featured_text"));
                info.setRatings(getObjectFromJsonArray.getString("ratings"));
                info.setReviewCount(getObjectFromJsonArray.getString("review_count"));
                if(getObjectFromJsonArray.toString().contains("subscribe_type")) {
                    info.setSubscribeType(getObjectFromJsonArray.getString("subscribe_type"));
                }
                if (info.getLiveStatus().contentEquals("1") || info.getLiveStatus().contentEquals("2")) {
                    advisorInfoList.add(info);
                 } else if (info.getStatus().contentEquals("1")) {
                    ActiveList.add(info);
                } else if (info.getStatus().contentEquals("0")) {
                    inActiveList.add(info);
                }
            }
        } catch (JSONException e) {
            Log.e(tag, "Exception:" + e.toString());
            e.printStackTrace();
        } finally {
            if (ActiveList != null) {
                for (AdvisorInfo mInfo : ActiveList) {
                     advisorInfoList.add(mInfo);
                }
            }
            if (inActiveList != null) {
                for (AdvisorInfo mInfo : inActiveList) {
                     advisorInfoList.add(mInfo);
                }

            }
            ActiveList.clear(); // To prevent from keeping old data.
            inActiveList.clear();
            ActiveList = null; // To prevent memory leak
            inActiveList = null;
        }
        return advisorInfoList;
    }

    private ArrayList<ReviewInfo> ReviewsJsonParsion(String response) {

        ArrayList<ReviewInfo> ReviewInfoList = new ArrayList<>();

        try {
            JSONObject parentObject = new JSONObject(response);
            JSONArray array = parentObject.getJSONArray("message");

            for (int i = 0; i < array.length(); i++) {

                ReviewInfo rInfo = new ReviewInfo();
                JSONObject object = array.getJSONObject(i);
                rInfo.setClient_username(object.getString("client_username"));
                rInfo.setReview(object.getString("review"));
                rInfo.setComment(object.getString("comment"));
                rInfo.setCreated_at(object.getString("created_at"));
                ReviewInfoList.add(rInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ReviewInfoList;
    }


    @Override
    public void onFailure(VolleyError error) {
        Log.i(tag, "Volley Error:" + error);
        iPresenter.onFailure(error);
    }


}
