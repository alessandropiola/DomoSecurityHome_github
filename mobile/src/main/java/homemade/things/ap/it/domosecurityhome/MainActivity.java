package homemade.things.ap.it.domosecurityhome;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.graphics.Color.*;
import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT;


public class MainActivity extends AppCompatActivity {

    home canvas1;
    private Rect rectTapp1 = new Rect(120, 560, 230, 710);
    private Rect rectTapp2 = new Rect(310, 380, 400, 485);
    private Rect rectLuceTenda = new Rect(65, 65, 130, 130);
    TextView txtDispo;
    private int intDispo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvas1 = findViewById(R.id.home);

        canvas1.intMolt = 1.5f;

        Button btnApri = findViewById(R.id.apri);
        Button btnChiudi = findViewById(R.id.chiudi);
        Button btnApriT1 = findViewById(R.id.apriT1);
        Button btnChiudiT1 = findViewById(R.id.chiudiT1);
        Button btnApriT2 = findViewById(R.id.apriT2);
        Button btnChiudiT2 = findViewById(R.id.chiudiT2);

        Button btnStop = findViewById(R.id.stop);
        Button btnFloor = findViewById(R.id.floor);
        txtDispo = findViewById((R.id.setDisp));

        btnFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("XY", "piano!");
                Log.i("XY", "piano!"+canvas1.intPiano);
                txtDispo.setText("---");
                intDispo = 0;
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        if (canvas1.intPiano==2) {
                            canvas1.intPiano=1;
                            canvas1.intMolt = 1.5f;
                        } else if (canvas1.intPiano==1) {
                            canvas1.intPiano=2;
                            canvas1.intMolt = 1.1f;
                        }

                        canvas1.invalidate();

                    }
                });
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetTaskDone().execute("http://192.168.1.201/cm?cmnd=Power1%20Off","","");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power1%20Off","","http://192.168.1.202/cm?cmnd=Power2%20Off");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power3%20Off","","http://192.168.1.202/cm?cmnd=Power4%20Off");
                new GetTaskDone().execute("http://192.168.1.203/cm?cmnd=Power1%20Off","","http://192.168.1.203/cm?cmnd=Power2%20Off");

                new GetTaskDone().execute("http://192.168.1.204/cm?cmnd=Power1%20Off","","http://192.168.1.204/cm?cmnd=Power2%20Off");
                new GetTaskDone().execute("http://192.168.1.205/cm?cmnd=Power1%20Off","","http://192.168.1.205/cm?cmnd=Power2%20Off");
                new GetTaskDone().execute("http://192.168.1.206/cm?cmnd=Power1%20Off","","http://192.168.1.206/cm?cmnd=Power2%20Off");
                new GetTaskDone().execute("http://192.168.1.207/cm?cmnd=Power1%20Off","","http://192.168.1.207/cm?cmnd=Power2%20Off");
            }
        });

        btnApri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intDispo == 1){
                    new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power1%20Off","","http://192.168.1.202/cm?cmnd=Power2%20On");
                }
                if (intDispo == 2){
                    new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power3%20Off","","http://192.168.1.202/cm?cmnd=Power4%20On");
                }
                if (intDispo == 3){
                    new GetTaskDone().execute("http://192.168.1.201/cm?cmnd=Power%20On","","");
                }
                if (intDispo == 0){
                    Toast.makeText(getApplicationContext(), "Seleziona prima il\n      dispositivo",
                            Toast.LENGTH_LONG).show();

                }


            }


        });

        btnChiudi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (intDispo == 1){
                    new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power2%20Off","","http://192.168.1.202/cm?cmnd=Power1%20On");
                }
                if (intDispo == 2){
                    new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power4%20Off","","http://192.168.1.202/cm?cmnd=Power3%20On");
                }
                if (intDispo == 3){
                    new GetTaskDone().execute("http://192.168.1.201/cm?cmnd=Power%20Off","","");
                }
                if (intDispo == 0){
                    Toast.makeText(getApplicationContext(), "Seleziona prima il\n     dispositivo",
                            Toast.LENGTH_LONG).show();

                }
            }
        });


        btnApriT1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power1%20Off","","http://192.168.1.202/cm?cmnd=Power2%20On");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power3%20Off","","http://192.168.1.202/cm?cmnd=Power4%20On");
                return false;
            }
        });
        btnChiudiT1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power2%20Off","","http://192.168.1.202/cm?cmnd=Power1%20On");
                new GetTaskDone().execute("http://192.168.1.202/cm?cmnd=Power4%20Off","","http://192.168.1.202/cm?cmnd=Power3%20On");
                return false;
            }
        });
        btnApriT2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new GetTaskDone().execute("http://192.168.1.204/cm?cmnd=Power2%20Off","","http://192.168.1.204/cm?cmnd=Power1%20On");
                new GetTaskDone().execute("http://192.168.1.205/cm?cmnd=Power2%20Off","","http://192.168.1.205/cm?cmnd=Power1%20On");
                return false;
            }
        });
        btnChiudiT2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new GetTaskDone().execute("http://192.168.1.204/cm?cmnd=Power%20Off","","http://192.168.1.204/cm?cmnd=Power2%20On");
                new GetTaskDone().execute("http://192.168.1.205/cm?cmnd=Power%20Off","","http://192.168.1.205/cm?cmnd=Power2%20On");
                return false;
            }
        });



    }

    protected class GetTaskDone extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(), "Invio comando al dispositivo",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params){
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
                Log.i("HTTP",result);
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return params[2];
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Toast.makeText(getApplicationContext(), result,Toast.LENGTH_LONG).show();
            new GetTaskDone2().execute(result);

        }

    }

    protected class GetTaskDone2 extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           //Toast.makeText(getApplicationContext(), "Invio comando al dispositivo2",Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params){
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
                Log.i("HTTP",result);
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int)event.getX();
        int y = (int)event.getY();

        if (event.getAction() == MotionEvent.ACTION_UP) {
                Log.i("XY","x:"+x +" y:"+y);
                if (rectTapp1.contains(x,y)) {
                    Log.i("XY","BOOM1!");
                    txtDispo.setText("Tapparella1");
                    intDispo = 1;
                }
                if (rectTapp2.contains(x,y)) {
                    Log.i("XY", "BOOM2!");
                    txtDispo.setText("Tapparella2");
                    intDispo = 2;
                }
            if (rectLuceTenda.contains(x,y)) {
                Log.i("XY", "BOOM2!");
                txtDispo.setText("LuceTenda");
                intDispo = 3;
            }

        }



        return false;
    }


}
