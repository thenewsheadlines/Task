package im.bcs.task.NetworkAPI;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import im.bcs.task.Interface.IVolley;
import im.bcs.task.NetworkAPI.VolleyControler;

/**
 * Created by Opriday on 7/5/2018.
 */

public class API_Class implements Response.Listener<String>, Response.ErrorListener {

    IVolley iVolley;
    Context context;
    boolean isUserNeedAdvisorList=false,isUserNeedReview=false,isUserNeedProfile=false,isUserSubscribe=false;

    public API_Class(Context context, IVolley iVolley) {
        this.iVolley = iVolley;
        this.context = context;
    }

    public void FetchAdvisorList(String url , Boolean isUserNeedAdvisorList) {
        isUserSubscribe = false;
        isUserNeedProfile = false;
        isUserNeedReview = false;
        this.isUserNeedAdvisorList  = isUserNeedAdvisorList;
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,url, this, this)
        {
            @Override
            protected Map<String, String> getParams() {
                // Post params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("auth_key", "NWtBFkO8zOLHpSpmbCOOZBVobq7j2mAj");
                return params;
            }
        };
        VolleyControler.getInstance(context).onAddRequest(jsonObjectRequest);
    }

    public void FetchAdvisorProfile(String url, final String userId,final Boolean isUserNeedProfile) {
        this.isUserNeedReview = false;
        isUserSubscribe = false;
        isUserNeedAdvisorList = false;
        this.isUserNeedProfile = isUserNeedProfile;
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, this, this) {
            @Override
            protected Map<String, String> getParams() {
                // Post params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("auth_key", "NWtBFkO8zOLHpSpmbCOOZBVobq7j2mAj");
                params.put("id", userId);
                params.put("client_id","141360");
                return params;
            }
        };
        VolleyControler.getInstance(context).onAddRequest(jsonObjectRequest);
    }

    public void FetchAdvisorReviewsList(String url, final String userId, final int page, final Boolean isUserNeedReview) {

        this.isUserNeedReview = isUserNeedReview;
        isUserNeedProfile = false;
        isUserNeedAdvisorList = false;
        isUserSubscribe = false;
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, this, this) {
            @Override
            protected Map<String, String> getParams() {
                // Post params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("auth_key", "NWtBFkO8zOLHpSpmbCOOZBVobq7j2mAj");
                params.put("advisor_id", userId);
                params.put("page", String.valueOf(page));

                return params;
            }
        };
        VolleyControler.getInstance(context).onAddRequest(jsonObjectRequest);
    }
    public void SubscribeAdvisor(String url, final int type, final String userId, final Boolean isUserSubscribe) {
        isUserNeedReview = false;
        isUserNeedProfile = false;
        isUserNeedAdvisorList = false;
        this.isUserSubscribe = isUserSubscribe;

        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST, url, this, this) {
            @Override
            protected Map<String, String> getParams() {
                // Post params to login url

                Map<String, String> params = new HashMap<String, String>();
                params.put("auth_key", "NWtBFkO8zOLHpSpmbCOOZBVobq7j2mAj");
                params.put("advisor_id", userId);
                params.put("client_id","141360");
                params.put("subscribe_type",type + "");
                return params;
            }
        };
        VolleyControler.getInstance(context).onAddRequest(jsonObjectRequest);
    }

    public void FetchQuestionAnswer(String url) {
        isUserSubscribe = false;
        isUserNeedProfile = false;
        isUserNeedReview = false;
        isUserNeedAdvisorList  = false;
        StringRequest jsonObjectRequest = new StringRequest(Request.Method.POST,url, this, this);
        VolleyControler.getInstance(context).onAddRequest(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        iVolley.onFailure(error);
    }

    @Override
    public void onResponse(String response) {
        iVolley.onSuccess(response , isUserNeedProfile , isUserNeedReview , isUserSubscribe , isUserNeedAdvisorList);

    }
}
