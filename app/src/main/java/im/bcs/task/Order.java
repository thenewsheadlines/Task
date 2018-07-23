package im.bcs.task;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import im.bcs.task.Camera2api.CameraActivity;
import im.bcs.task.Util.Constant;

public class Order extends AppCompatActivity implements View.OnClickListener {

    TextView name, shortDescription, instruction, submit, addPhotoVideo;
    Button recordVideo, takePicture, gallery, close;
    EditText question, situation;
    CircleImageView profileImage;
    RelativeLayout imageVideoParentLayout;
    ImageButton media;
    VideoView video;
    ImageView image;
    Dialog mDialog;
    String FilePathForDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mDialog = onCreateDialog();
        InitializeUI();

    }

    private void InitializeUI() {
        name = (TextView) findViewById(R.id.advisor_name_order);
        shortDescription = (TextView) findViewById(R.id.advisor_description_order);
        instruction = (TextView) findViewById(R.id.advisor_instruction_order);
        submit = (TextView) findViewById(R.id.submit_order);
        question = (EditText) findViewById(R.id.specific_question_order);
        situation = (EditText) findViewById(R.id.situtation_order);
        profileImage = (CircleImageView) findViewById(R.id.advisor_profile_image_order);
        media = (ImageButton) findViewById(R.id.media_order);
        recordVideo = (Button) mDialog.findViewById(R.id.newVideo_mediaDialog);
        takePicture = (Button) mDialog.findViewById(R.id.takePicture_mediaDialog);
        gallery = (Button) mDialog.findViewById(R.id.gallery_mediaDialog);
        close = (Button) findViewById(R.id.close_imageVideo_order);
        video = (VideoView) findViewById(R.id.videoView_imageVideo_order);
        image = (ImageView) findViewById(R.id.imageView_imageVideo_order);
        addPhotoVideo = (TextView) findViewById(R.id.addPhotoVideo_order);
        imageVideoParentLayout = (RelativeLayout) findViewById(R.id.imageVideo_ParentLayout);

        Picasso.with(Order.this).load(getIntent().getStringExtra(Constant.ADVISOR_IMAGE)).fit().into(profileImage);
        name.setText(getIntent().getStringExtra(Constant.ADVISOR_NAME));
        shortDescription.setText(getIntent().getStringExtra(Constant.ADVISOR_DESCRIPTION));
        instruction.setText(getIntent().getStringExtra(Constant.ADVISOR_INSTRUCTION));

        media.setOnClickListener(this);
        submit.setOnClickListener(this);
        recordVideo.setOnClickListener(this);
        takePicture.setOnClickListener(this);
        gallery.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    public Dialog onCreateDialog() {
        Dialog dialog = new Dialog(Order.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.media_dialog);
        return dialog;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.media_order) {
            mDialog.show();

        } else if (v.getId() == R.id.submit_order) {
            if (question.length() > 0 && situation.length() > 0){
                Intent orederDetail = new Intent(Order.this, OrderDetail.class);
                orederDetail.putExtra(Constant.ADVISOR_NAME, getIntent().getStringExtra(Constant.ADVISOR_NAME));
                orederDetail.putExtra(Constant.ADVISOR_DESCRIPTION, getIntent().getStringExtra(Constant.ADVISOR_DESCRIPTION));
                orederDetail.putExtra(Constant.ADVISOR_IMAGE, getIntent().getStringExtra(Constant.ADVISOR_IMAGE));
                startActivity(orederDetail);
            }else {
                Toast.makeText(Order.this,"Please enter situation and question",Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.newVideo_mediaDialog) {
            Intent toCamera = new Intent(Order.this, CameraActivity.class);
            toCamera.putExtra(Constant.CAMERA, 1);
            startActivityForResult(toCamera, Constant.CAMERA_VIDEO_CODE);
        } else if (v.getId() == R.id.takePicture_mediaDialog) {
            Intent toCamera = new Intent(Order.this, CameraActivity.class);
            toCamera.putExtra(Constant.CAMERA, 0);
            startActivityForResult(toCamera, Constant.CAMERA_PICTURE_CODE);
        } else if (v.getId() == R.id.gallery_mediaDialog) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            this.setResult(Activity.RESULT_CANCELED,intent);
            startActivityForResult(intent, Constant.CAMERA_GALLERY_CODE);

        } else if (v.getId() == R.id.close_imageVideo_order) {
            onDeleteFile();
            onParentLayoutGone();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onParentLayoutVisible();
        if (requestCode == Constant.CAMERA_VIDEO_CODE) {
            if (resultCode == RESULT_OK) {
                FilePathForDelete = data.getStringExtra("path");
                onVideoVisible();
                video.setVideoPath(FilePathForDelete);
                video.start();
            }
        } else if (requestCode == Constant.CAMERA_PICTURE_CODE) {
            if (resultCode == RESULT_OK) {
                FilePathForDelete = data.getStringExtra("path");
                onImageVisible();
                Bitmap map = BitmapFactory.decodeFile(FilePathForDelete);
                Log.e("order", data.getStringExtra("path").toString());
                image.setImageBitmap(map);
            }

        } else if (requestCode == Constant.CAMERA_GALLERY_CODE) {
            onImageVisible();
            Uri uri = data.getData();
            FilePathForDelete =  getPath(uri);
            Bitmap map = BitmapFactory.decodeFile(getPath(uri));
            Log.e("order","Path:"+getPath(uri));
            image.setImageBitmap(map);
        }
    }
    public String getPath(Uri uri) {

        if( uri == null ) {
            return null;
        }

        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }

        return uri.getPath();
    }
    public void onImageVisible() {
        mDialog.dismiss();
        video.setVisibility(View.GONE);
        image.setVisibility(View.VISIBLE);
    }

    public void onVideoVisible() {
        mDialog.dismiss();
        image.setVisibility(View.GONE);
        video.setVisibility(View.VISIBLE);
    }

    public void onParentLayoutVisible() {
        media.setVisibility(View.GONE);
        addPhotoVideo.setVisibility(View.GONE);
        imageVideoParentLayout.setVisibility(View.VISIBLE);
    }

    public void onParentLayoutGone() {
        imageVideoParentLayout.setVisibility(View.GONE);
        media.setVisibility(View.VISIBLE);
        addPhotoVideo.setVisibility(View.VISIBLE);
    }
    public void onDeleteFile(){
        File fdelete = new File(FilePathForDelete);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                Log.e("Order","file Deleted :" + fdelete.getPath());
            } else {
                Log.e("Order","file not Deleted :" + fdelete.getPath());
            }
        }
        FilePathForDelete = "";
    }
}
