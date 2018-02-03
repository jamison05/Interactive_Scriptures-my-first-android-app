package drjpreludes.interactive_scriptures_premium;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

public class Interactive_scriptures_part1 extends AppCompatActivity {
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_scriptures_part1);


        initiatialize_buttons();
        initialize_animation();

        //This will  intialize all buttons.
        load_stringfunc();

    }

    public int sequence_iter=0; //This is the sequence that will be used to progress through the animation.

    //Each animation will have sequence each comment will have a sequuence.
    public void on_exit() {

        mediaPlayer.release();
        mediaPlayer = null;
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.putExtra("key","Exit");
        startActivity(homeIntent);
    }

    public void onDestroy() {



        if (mediaPlayer != null) {


            mediaPlayer.release();
            mediaPlayer = null;
        }
        System.gc();

        super.onDestroy();
    }


    void load_mediaplayer() {


    }
void developer_int (){

    Intent intent1 = new Intent("android.intent.action.RUN.developer_interprets_scroll");
    startActivity(intent1);

}
    MediaPlayer mediaPlayer;


    double start_time_value = 0;

    void initialize_animation() {
    try{
     mediaPlayer= MediaPlayer.create(this, R.raw.genesis_firstpart_combined_2);
    }catch(Exception ex){}
    }

    private static final String TOAST_TEXT = "Please press the continue button. " + "The language to the original Genesis text." ;
    private static final String TOAST_TEXT2 ="Enjoy the sounds (better with HQ audio(e-phones/speakers)).";
    ImageButton continue_button;
    Button skip_button;
    Button skip_button1;
    Button exit_button1;
    ImageButton mute_button;
    ImageView word_panel_eng;
    ImageView word_panel_heb;
    ImageView backdrop;
    TextView scripture_location;
    TextView language;
    Animation switch_text_appear,disappear, backdrop_appear;
    AnimationDrawable eng_anim;
    AnimationDrawable animate_circle;
    int [] hebrew_text= new int[]{R.drawable.gen1v1p1_hebrew_small,R.drawable.gen1v1p2_hebrew_small,R.drawable.gen1v1p3_hebrew_small,R.drawable.gen1v1p2_hebrew_small,R.drawable.gen1v2p3_hebrew_small,R.drawable.gen1v1p3_hebrew_small,R.drawable.gen1v4p1_hebrew_small,R.drawable.gen1v4p2_hebrew_small};
    int [] english_text= new int []{R.drawable.genesis_1_0,R.drawable.genesis_1_1,R.drawable.animated_scripture1,R.drawable.animated_scripture2,R.drawable.animated_scripture3,R.drawable.v1genesis_3_1,R.drawable.v1genesis_3_2,R.drawable.v1genesis_4_1pt2};
    String[] genesis1={"Genesis 1:1","Genesis 1:2", "Genesis 1:3", "Genesis 1:4"};
    ImageView animated_circle;
Button interpret_go;
  int add1=0;

//Animations  Array #2,
    void initiatialize_buttons() {
        scripture_location = (TextView) findViewById(R.id.scrip_loc);
        language = (TextView) findViewById(R.id.written_language);
        switch_text_appear = AnimationUtils.loadAnimation(this, R.anim.appear_anim2);
        disappear= AnimationUtils.loadAnimation(this, R.anim.disappear_anim);
        backdrop_appear=AnimationUtils.loadAnimation(this, R.anim.appear);
        backdrop= (ImageView)findViewById(R.id.backdrop_text);
        continue_button = (ImageButton) findViewById(R.id.back_button_activity);
        interpret_go = (Button) findViewById(R.id.interpret_written);

        mute_button = (ImageButton) findViewById(R.id.mute_button);
        word_panel_eng= (ImageView)findViewById(R.id.english_text_animated);
        word_panel_heb= (ImageView)findViewById(R.id.hebrew_text_animated);
        skip_button=(Button) findViewById(R.id.skip_button);
        animated_circle= (ImageView) findViewById(R.id.animated_circle);
        animated_circle= (ImageView) findViewById(R.id.animated_circle);
        //You are going to animate the two buttons for at least 5 seconds prior.
        continue_button.setOnClickListener(new View.OnClickListener() {


                                               public void onClick(View v) {

                                                   if (!mediaPlayer.isPlaying()) {
                                                       mediaPlayer.start();
                                                   }
                                                   sequence_iter++;
                                                   evaluate_anim_position(sequence_iter);
                                                    add1++;
                                                   if (add1==1){
                                                   animated_circle.setBackgroundResource(R.drawable.ball_motion);
                                                   animate_circle =(AnimationDrawable) animated_circle.getBackground();
                                                   animate_circle.start();}

                                                   if (add1==2){
                                                       animated_circle.setBackgroundResource(R.drawable.ball_motion_slower);
                                                       animate_circle =(AnimationDrawable) animated_circle.getBackground();
                                                       animate_circle.start();
                                                   add1=0;
                                                   }
                                               }
                                           }



                                           );

        //lood on continue button selection.
        mute_button.setOnClickListener(new View.OnClickListener() {


                                           public void onClick(View v) {
                                               if (mediaPlayer.isPlaying()) {
                                                   mediaPlayer.pause();
                                                   mediaPlayer.setVolume(0,0);
                                               }
                                           }
                                       }

        );

        interpret_go.setOnClickListener(new View.OnClickListener() {


                                            public void onClick(View v) {
                                                developer_int();
                                            }
                                        }
        );




        skip_button.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){

                mediaPlayer.release();
                mediaPlayer = null;
                Intent intent1 = new Intent("android.intent.action.RUN.interactive_scriptures2");
                startActivity(intent1);


            }



        });
    }


int seq=0;//This will be the hebrew verion
int seq1_eng=0; //This will be the english version
int iter_language=0;
    private void evaluate_anim_position(int seq1){
        iter_language++;



        if (iter_language==1) {


            switch (seq) {
                case 0:
                    //Use a Value animator to animate alpha reappear the hebrew text of
                    //Have Create an initial toast explaining the the hebrew text is the most original form of the text.
                    interpret_go.setVisibility(View.VISIBLE);
                    language.setVisibility(View.VISIBLE);
                    language.setText("Language:Hebrew");

                    Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
                    backdrop.setBackgroundResource(R.drawable.gold_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(hebrew_text[seq]);
                    word_panel_eng.startAnimation(disappear);
                    word_panel_heb.startAnimation(switch_text_appear);
                    scripture_location.setVisibility(View.VISIBLE);
                    scripture_location.setText(genesis1[0]);
                    break;
                case 1:
                    word_panel_eng.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:Hebrew");
                    backdrop.setBackgroundResource(R.drawable.gold_backdrop);
                    backdrop.startAnimation(backdrop_appear);

                    word_panel_heb.setBackgroundResource(hebrew_text[seq]);
                    word_panel_heb.startAnimation(switch_text_appear);
                    break;
                case 2:
                    word_panel_eng.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:Hebrew");
                    backdrop.setBackgroundResource(R.drawable.gold_backdrop);
                    backdrop.startAnimation(backdrop_appear);

                    word_panel_heb.setBackgroundResource(hebrew_text[seq]);
                    word_panel_heb.startAnimation(switch_text_appear);
                    scripture_location.setText(genesis1[1]);
                    break;
                case 3:
                    word_panel_eng.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:Hebrew");
                    backdrop.setBackgroundResource(R.drawable.gold_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(hebrew_text[seq]);
                    word_panel_heb.startAnimation(switch_text_appear);
                break;
                case 4:
                    word_panel_eng.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:Hebrew");
                    backdrop.setBackgroundResource(R.drawable.gold_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(hebrew_text[seq]);
                    word_panel_heb.startAnimation(switch_text_appear);
                break;
                case 5:
                    word_panel_eng.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:Hebrew");
                    backdrop.setBackgroundResource(R.drawable.gold_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(hebrew_text[seq]);
                    word_panel_heb.startAnimation(switch_text_appear);
                break;
                case 6:
                    word_panel_eng.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:Hebrew");
                    backdrop.setBackgroundResource(R.drawable.gold_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(hebrew_text[seq]);
                    word_panel_heb.startAnimation(switch_text_appear);
                break;
                case 7:
                    word_panel_eng.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:Hebrew");
                    backdrop.setBackgroundResource(R.drawable.gold_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(hebrew_text[seq]);
                    word_panel_heb.startAnimation(switch_text_appear);
                break;
                case 8:
                    word_panel_eng.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:Hebrew");
                    backdrop.setBackgroundResource(R.drawable.gold_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(hebrew_text[seq]);
                    word_panel_heb.startAnimation(switch_text_appear);

                    break;
                case 9:
                    word_panel_eng.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:Hebrew");
                    backdrop.setBackgroundResource(R.drawable.gold_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(hebrew_text[seq]);
                    word_panel_heb.startAnimation(switch_text_appear);
                    makeinterpret_vis();
            }

          seq++;  //This
            if (seq>hebrew_text.length-1){
                seq=0;
            }

        }
        if (iter_language==2) {


            switch (seq1_eng) {
                case 0:
                    //Make  the hebrew text diappear and the english appear.
                    word_panel_heb.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:English");
                    Toast.makeText(this, TOAST_TEXT2, Toast.LENGTH_LONG).show();
                    backdrop.setBackgroundResource(R.drawable.purple_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(english_text[seq1_eng]);
                    word_panel_heb.startAnimation(switch_text_appear);
                    break;
                case 1:
                    word_panel_heb.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:English");
                    backdrop.setBackgroundResource(R.drawable.purple_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(english_text[seq1_eng]);
                    word_panel_heb.startAnimation(switch_text_appear);
                    break;
                case 2:
                    word_panel_heb.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:English");
                    backdrop.setBackgroundResource(R.drawable.purple_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(english_text[seq1_eng]);
                    eng_anim = (AnimationDrawable) word_panel_heb.getBackground();
                    eng_anim.start();
                    word_panel_heb.startAnimation(switch_text_appear);
                    scripture_location.setText(genesis1[1]);
                    break;

                case 3:
                    word_panel_heb.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:English");
                    backdrop.setBackgroundResource(R.drawable.purple_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(english_text[seq1_eng]);
                    eng_anim = (AnimationDrawable) word_panel_heb.getBackground();
                    eng_anim.start();
                    word_panel_heb.startAnimation(switch_text_appear);
                   // scripture_location.setText(genesis1[1]);
                    break;
                case 4:
                    word_panel_heb.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:English");
                    backdrop.setBackgroundResource(R.drawable.purple_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(english_text[seq1_eng]);
                    eng_anim = (AnimationDrawable) word_panel_heb.getBackground();
                    eng_anim.start();
                    word_panel_heb.startAnimation(switch_text_appear);
                    break;
                case 5:
                    word_panel_heb.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:English");
                    backdrop.setBackgroundResource(R.drawable.purple_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(english_text[seq1_eng]);
                    word_panel_heb.startAnimation(switch_text_appear);
                    scripture_location.setText(genesis1[2]);
                    break;
                case 6:
                    word_panel_heb.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:English");
                    backdrop.setBackgroundResource(R.drawable.purple_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(english_text[seq1_eng]);
                    word_panel_heb.startAnimation(switch_text_appear);
                    scripture_location.setText(genesis1[2]);
                    break;
                case 7:
                    word_panel_heb.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:English");
                    backdrop.setBackgroundResource(R.drawable.purple_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(english_text[seq1_eng]);
                    word_panel_heb.startAnimation(switch_text_appear);
                break;
                case 8:
                    word_panel_heb.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:English");
                    backdrop.setBackgroundResource(R.drawable.purple_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(english_text[seq1_eng]);
                    word_panel_heb.startAnimation(switch_text_appear);
                    scripture_location.setText(genesis1[2]);

                    break;
                case 9:
                    word_panel_heb.startAnimation(disappear);
                    backdrop.startAnimation(disappear);
                    language.setText("Language:English");
                    backdrop.setBackgroundResource(R.drawable.purple_backdrop);
                    backdrop.startAnimation(backdrop_appear);
                    word_panel_heb.setBackgroundResource(english_text[seq1_eng]);
                    word_panel_heb.startAnimation(switch_text_appear);
                    scripture_location.setText(genesis1[3]);
                    makeinterpret_vis();
            }



            seq1_eng++;



            if (seq1_eng>english_text.length-1){
                seq1_eng=0;
            }



        }

        if (iter_language==2){

            iter_language=0;
        }
    }

    //So in this program sound and text will be displayed  or played sychronously.  This sychronous programming
    //will correpsond with each other.  You will have text documents and the animation sequences ready to display.
    //You will also have a lock on progresion at certain parts especially if it is promoting for the sound.
    //So first thiing is having a store house of everything  you are going to to say.
    //Characterize my comments, as either directions or open ended thinking/questions.

    //Explain that the animated ball serves as generating sounds while you are thinking.
    //This program deals with very complex aspects of the connnection between music and concepts.
    //I think the way to approach this is to use this is to use this as a learning platform to understand
    //how people deeply think about God.  And how music connects enhances your mindset.

    //Make sure that the layout is complete. Before I expand the layout to multiple buttons.
    //Provide the sequence of the animation and the music.  Have them sychronized as the user presses the next button.
    //Have a mute sound, where you stop the sound.
    //For the exit button make sure that all of the button are in.
    //For aniamtions
        //
    //
public void makeinterpret_vis()
{
    interpret_go.setAlpha(1);
}
    String[] comments= new String[30];
    String[] directive= new String[30];
    public void load_stringfunc(){

        comments[0]="So you are wondering why create an App that displays religous text and" +
                " have a little bit of music playing?  Have this been done so many times before? ";
        comments[1]="And you are right, but this app is a little different.  I hope you enjoy the " +
                "interactive approach that I am using";
        directive[0]="The beginning?  This is where it all starts.  Can you hear a steady rhythm in " +
                "the backdround?";
                directive[0]="This first scripture just gives an overview.";
                directive[1]= "Lets think of God as just coming into existence";
        directive[2]= "Or, in different terms atoms, mass, energy did not exist.";
        directive[3]= "What happens when you press thhat button?                    ";








    }
    public void get_files() {
        // pamnum1.setBackgroundResource(R.drawable.whole_anim1);
        //AnimationDrawable anim_p1_1 = (AnimationDrawable) pamnum1.getBackground();
        int bytesRead = 0;
        int bufferIdx;
        int clipIdx = 0;
        int framesCount = 125000;
        byte[] music = new byte[340000];//size & length of the file
        float[] temp = new float[framesCount];
        float[][] k1 = new float[50000][2];
        int x_add = 0;
        InputStream is = getResources().openRawResource(R.raw.afro_drums6);
        BufferedInputStream bis = new BufferedInputStream(is, 1024);
        DataInputStream dis = new DataInputStream(bis);      //  Create a DataInputStream to read the audio data from the saved file
        try {
            int i = 0;                                                          //  Read the file into the "music" array
            while (dis.available() > 0) {
                bufferIdx = 0;
                bytesRead = dis.readByte();
                clipIdx++;
                music[clipIdx] = dis.readByte();                                      //  This assignment does not reverse the order
                temp[clipIdx] = music[clipIdx] / 32767f;
                x_add++;
                if (x_add == 512) {
                    x_add = 0;
                    i++;
                    k1[i][0] = temp[clipIdx];
                    k1[i][1] = temp[clipIdx];
                }
            }
            dis.close();
        } catch (Exception ex) {


        }


    }


}
