package ghostvr.unityandroidplugin;

import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Divyanshu on 6/9/17.
 */

public class ROSThreadPlugin implements Runnable {
    private ServerSocket serverSocket;
    private boolean flag = true;
    private float[] dataArray;

    @Override
    public void run() {

        dataArray = new float[7];

        try {
            serverSocket = new ServerSocket(8888);

            while (flag)
                getClientData();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getClientData() throws IOException {
        Socket clientSocket = serverSocket.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String message;

        while(true) {
            message = br.readLine();

            if (message.equals("stop")){
                clientSocket.close();
                break;

            } else {
                String words[] = message.split(",");

                for (int i = 1; i < words.length; ++i){
                    dataArray[i-1] = Float.parseFloat(words[i]);
                }

                Log.d("Input", message + "\nTime elapsed: " + SystemClock.elapsedRealtimeNanos());
            }
        }
    }

    public float[] getPointArray(){
        float[] pointArray = new float[]{dataArray[0], dataArray[1], dataArray[2]};
        Log.d("Output", "Points: " + pointArray.toString());

        return pointArray;
    }
    
    public float[] getQuatArray(){
        float[] quatArray = new float[]{dataArray[3], dataArray[4], dataArray[5], dataArray[6]};
        Log.d("Output", "Quat: " + quatArray.toString());

        return quatArray;
    }
}
