package im.bcs.task.Interface;

import com.android.volley.VolleyError;

import java.util.ArrayList;

import im.bcs.task.Model.AdvisorInfo;
import im.bcs.task.Model.ReviewInfo;

/**
 * Created by Opriday on 7/5/2018.
 */

public interface IPresenter {

    public void AdvisorList(ArrayList<AdvisorInfo> advisorInfos);
    public void onFailure(VolleyError error);
    public void AdvisorDetailReviewList(ArrayList<ReviewInfo> reviewInfos);
}
