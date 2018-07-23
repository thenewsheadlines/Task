package im.bcs.task.NetworkAPI;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Opriday on 7/5/2018.
 */

public class VolleyControler {

    RequestQueue requestQueue;
    private static Context context;
    private  static VolleyControler mAPI_Class;

    public VolleyControler(Context context){
    this.context = context;
    }
    public static synchronized VolleyControler getInstance(Context context) {
        if(mAPI_Class == null){
            mAPI_Class = new VolleyControler(context); // creating Instance of singleton class.
        }
        return mAPI_Class;
    }
    public RequestQueue onCreateRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context); // creating request queue
        }
        return requestQueue;
    }
    public<T> void onAddRequest(Request<T> request){
        onCreateRequestQueue().add(request); // adding request to queue.
    }

}
