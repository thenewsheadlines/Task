package im.bcs.task.Presenters;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import im.bcs.task.Interface.IFAQ_Presenter;
import im.bcs.task.Interface.IPresenter;
import im.bcs.task.Interface.IVolley;
import im.bcs.task.Model.QuestionAnswerInfo;
import im.bcs.task.NetworkAPI.API_Class;

/**
 * Created by Opriday on 7/23/2018.
 */

public class FAQ_Presenter implements IVolley {

    IFAQ_Presenter ifaqPresenter;
    API_Class api_class;
    Context context;
    String tag = "Presenter";

    public FAQ_Presenter(IFAQ_Presenter ifaqPresenter) {
        this.ifaqPresenter = ifaqPresenter;
        context = (Context) ifaqPresenter;
        api_class = new API_Class(context, this); // Creating API_Class object, passing context and IVolley interface as parmeters
    }

    public void getAnswerQuestion(String url){
        api_class.FetchQuestionAnswer(url);
    }

    @Override
    public void onSuccess(String response, boolean isUserNeedProfile, boolean isUserNeedReviews, boolean isUserSubscribe, boolean isUserNeedAdvisorList) {
        ArrayList<QuestionAnswerInfo> QuestionAnswerInfo = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("message");
            for (int i = 0; i<array.length(); i++){
                QuestionAnswerInfo info = new QuestionAnswerInfo();
                JSONObject jsonObject = array.getJSONObject(i);
                info.setQuestion(jsonObject.getString("question"));
                info.setAnswer(jsonObject.getString("answer"));
                QuestionAnswerInfo.add(info);
                Log.e("FAQ","question = "+jsonObject.getString("question"));
                Log.e("FAQ","Asnwer = "+jsonObject.getString("answer"));
            }
            Log.e("FAQ","Array Size:"+array.length());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        onSetQuestionAnsList(QuestionAnswerInfo);
    }

    @Override
    public void onFailure(VolleyError error) {
        Log.e("FAQ","VolleyError:"+error.getMessage());
    }

    public void onSetQuestionAnsList(ArrayList<QuestionAnswerInfo> QuestionAnswerInfo){
        ifaqPresenter.onGetFAQInfoList(QuestionAnswerInfo);
    }
}
