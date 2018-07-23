package im.bcs.task.Interface;

import com.android.volley.VolleyError;

/**
 * Created by Opriday on 7/5/2018.
 */

public interface IVolley {

    public void onSuccess(String response, boolean isUserNeedProfile, boolean isUserNeedReviews, boolean isUserSubscribe, boolean isUserNeedAdvisorList);
    public void onFailure(VolleyError error);
}
