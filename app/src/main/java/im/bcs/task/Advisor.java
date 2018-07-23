package im.bcs.task;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.android.volley.VolleyError;

import java.util.ArrayList;

import im.bcs.task.Adapter.GridViewAdapter;
import im.bcs.task.Interface.IPresenter;
import im.bcs.task.Model.AdvisorInfo;
import im.bcs.task.Model.ReviewInfo;
import im.bcs.task.Presenters.Presenter;
import im.bcs.task.Util.Constant;


public class Advisor extends Fragment implements IPresenter {

    GridView gridView;
    ProgressBar progressBar;
    Presenter presenter;
    GridViewAdapter adapter;
    ArrayList<AdvisorInfo> advisorInfoList = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_advisor, container, false);
        gridView = (GridView) v.findViewById(R.id.grid_view);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar_main) ;
        adapter = new GridViewAdapter(getActivity(),advisorInfoList);
        gridView.setAdapter(adapter);
        presenter = new Presenter(this,getActivity());
        presenter.getAdviosrList(Constant.AdvisorsWithoutReview);
        return v;
    }

    @Override
    public void AdvisorList(ArrayList<AdvisorInfo> advisorInfolist) {
        this.advisorInfoList = advisorInfolist;
        adapter.setAdvisorInfoList(advisorInfolist);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(VolleyError error) {

    }

    @Override
    public void AdvisorDetailReviewList(ArrayList<ReviewInfo> reviewInfos) {

    }

}
