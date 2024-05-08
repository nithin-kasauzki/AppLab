package com.example.applab52;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import org.vosk.LibVosk;
import org.vosk.Model;
import org.vosk.android.Recognizer;
import org.vosk.android.StorageService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Model model;
    private Recognizer recognizer;
    private TextView textViewResult;
    private Button buttonRecognize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LibVosk.setLogLevel(LogLevel.INFO);

        textViewResult = findViewById(R.id.textViewResult);
        buttonRecognize = findViewById(R.id.buttonRecognize);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        StorageService.unpack(this, "model-en-us", "model",
                model -> {
                    this.model = model;
                    buttonRecognize.setEnabled(true);
                },
                exception -> Log.e("ModelSetup", "Model unpacking error", exception));

        buttonRecognize.setOnClickListener(v -> recognizeAudioFile("/path/to/your/audio/file.wav"));
    }

    public void recognizeAudioFile(String audioPath) {
        if (model == null) {
            textViewResult.setText("Model is not ready");
            return;
        }

        recognizer = new Recognizer(model, 16000.0f);
        File audio = new File(audioPath);
        StringBuilder result = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(audio))) {
            String line;
            while ((line = br.readLine()) != null) {
                recognizer.acceptWaveForm(line.getBytes(), line.length());
            }
            result.append(recognizer.getResult().getText());
        } catch (IOException e) {
            Log.e("SpeechService", "Audio reading error", e);
        }

        textViewResult.setText(result.toString());
        recognizer.close();
    }
}
