



package com.example.imagetovolley;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import android.Manifest;
        import android.app.Activity;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.util.Base64;
        import android.util.Log;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Toast;

        import com.android.volley.AuthFailureError;
        import com.android.volley.DefaultRetryPolicy;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;
        import com.karumi.dexter.Dexter;
        import com.karumi.dexter.PermissionToken;
        import com.karumi.dexter.listener.PermissionDeniedResponse;
        import com.karumi.dexter.listener.PermissionGrantedResponse;
        import com.karumi.dexter.listener.PermissionRequest;
        import com.karumi.dexter.listener.single.PermissionListener;

        import java.io.ByteArrayOutputStream;
        import java.security.Permission;
        import java.util.HashMap;
        import java.util.Map;

        import de.hdodenhof.circleimageview.CircleImageView;

public class welcome extends Activity
{
    MediaPlayer mediaplayer;


    EditText t1,t2;
    CircleImageView img;
    ImageView btncamera;
    Button btnupload;
    Bitmap bitmap;
    String encodedimage;
    private static final String apiurl="http://172.16.45.111:5000/predict";
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

       Bundle extra = getIntent().getExtras();
      final String value = extra.getString("key");

        String tag ="Encoding";

        Log.d(tag, value);

        img=(CircleImageView)findViewById(R.id.profile_image);

        btncamera=(ImageView)findViewById(R.id.sbmit_camera);
        btnupload=(Button)findViewById(R.id.sbmit_upload);

        Button stop=(Button)findViewById(R.id.button4);

         Button pause = findViewById(R.id.button6);


        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult( intent,111);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadtoserver();
            }
        });

    }





    public void playSong(String response) {


        String tag ="Encoding";
        String message = "--------------------Start  PLayinfg-------";
        Log.d(tag, message);
        String tag9 ="MyVolley";

       String happy = "happy";
        Bundle extra = getIntent().getExtras();
        final String value = extra.getString("key");
        Log.d(tag, value);

        String a = "tamil";
        String b = "english";
        Log.d(tag, a);
        if(a.equals(value) && response.equals("happy") || a.equals(value) && response.equals("normal")){


            String tag2 ="Encoding";
            String message2 = "--------------------Tamil-------------";
            Log.d(tag2, message2);
            mediaplayer  = MediaPlayer.create(this,R.raw.jolly);

            mediaplayer.start();

        }
        if(a.equals(value) && response.equals("sad") || a.equals(value) && response.equals("fear")){
            String tag2 ="Encoding";
            String message2 = "--------------------Tamil-------------";
            Log.d(tag2, message2);
            mediaplayer  = MediaPlayer.create(this,R.raw.indruneyaga);

            mediaplayer.start();

        }
        if(a.equals(value) && response.equals("angry")){
            String tag2 ="Encoding";
            String message2 = "--------------------Tamil-------------";
            Log.d(tag2, message2);
            mediaplayer  = MediaPlayer.create(this,R.raw.kolaveri);

            mediaplayer.start();

        }
        if(a.equals(value) && response.equals("neutral")){
            String tag2 ="Encoding";
            String message2 = "--------------------Tamil-------------";
            Log.d(tag2, message2);
            mediaplayer  = MediaPlayer.create(this,R.raw.ashique);

            mediaplayer.start();

        }
        if(b.equals(value) && response.equals("fear")){
            String tag2 ="Encoding";
            String message2 = "--------------------Tamil-------------";
            Log.d(tag2, message2);
            mediaplayer  = MediaPlayer.create(this,R.raw.badguy);

            mediaplayer.start();

        }
        if(b.equals(value)&& response.equals("happy")) {
            String tag2 ="Encoding";
            String message2 = "--------------------English--------";
            Log.d(tag2, message2);
            mediaplayer = MediaPlayer.create(this, R.raw.senorita);

            mediaplayer.start();

        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==111 && resultCode==RESULT_OK)
        {
            bitmap=(Bitmap)data.getExtras().get("data");
            img.setImageBitmap(bitmap);
            encodebitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodebitmap(Bitmap bitmap)
    {
        String tag ="Encoding";
        String message = "---------------------Encoding--------";
        Log.d(tag, message);
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] byteofimages=byteArrayOutputStream.toByteArray();
        encodedimage=android.util.Base64.encodeToString(byteofimages, Base64.DEFAULT);
    }

    private void uploadtoserver()
    {


        StringRequest request=new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                img.setImageResource(R.drawable.ic_launcher_background);
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                playSong(response);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String response = "neutral";
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                playSong(response);
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> map=new HashMap<String, String>();
                map.put("t1","detect img");

                map.put("image",encodedimage);
                String tag ="MyVolley";
                String message = "---------------------api--------";
                Log.d(tag, message);
                return map;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                9000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }

    public void stopped() {


            String tag = "Encode";
            String message = "--------------------Stop Playinfg-------";
            Log.d(tag, message);
    mediaplayer = MediaPlayer.create(this, R.raw.senorita);
            mediaplayer.release();
            mediaplayer = null;

    }

    public void stop(View view){
        stopped();

    }
    @Override
    protected void onStop() {
        super.onStop();
        stopped();
    }


    public void pause(View view) {

        mediaplayer.pause();
    }

    public void play(View view) {

        mediaplayer.start();
    }
}
