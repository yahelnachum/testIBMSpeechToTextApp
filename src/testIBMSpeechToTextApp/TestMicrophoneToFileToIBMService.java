package testIBMSpeechToTextApp;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;

public class TestMicrophoneToFileToIBMService {

	public static void main(String[] args) {
        final JavaSoundRecorder recorder = new JavaSoundRecorder();
 
        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                	for(int i = 10; i > 0; i--){
                		System.out.println("Ending in " + i);
                		Thread.sleep(1000);
                	}
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.finish();
            }
        });
 
        stopper.start();
 
        // start recording
        recorder.start();
        
        String username = "86fd34e9-23e7-492e-93bc-ea0218eb963c";
		String password = "RkKUVPzpsIvU";
		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword(username, password);

		String audioFile1 = "./audioFiles/RecordAudio.wav";
		File audio = new File(audioFile1);
		System.out.println("File exists: " + audio.exists());
		Map params = new HashMap();
		params.put("audio", audio);
		params.put("content_type", "audio/wav; rate=44100");
		params.put("word_confidence", false);
		params.put("continuous", false);
		params.put("timestamps", false);
		params.put("inactivity_timeout", 30);
		params.put("max_alternatives", 1);

		SpeechResults transcript = service.recognize(params);

		System.out.println(transcript);
	}
}