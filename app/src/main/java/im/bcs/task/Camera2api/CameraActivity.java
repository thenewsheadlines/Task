/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.bcs.task.Camera2api;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

import im.bcs.task.Util.Constant;
import im.bcs.task.R;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class CameraActivity extends Activity {

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        bundle = new Bundle();
        int getCameraType = getIntent().getIntExtra(Constant.CAMERA,-1);
        if (null == savedInstanceState) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Camera2VideoFragment mCamera2VideoFragment = new Camera2VideoFragment();
            if(getCameraType != -1){
                bundle.putInt(Constant.CAMERA,getCameraType);
                mCamera2VideoFragment.setArguments(bundle);
            }
            transaction.replace(R.id.container, mCamera2VideoFragment).commit();
        }
    }

}
