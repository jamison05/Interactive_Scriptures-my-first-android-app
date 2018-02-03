package drjpreludes.interactive_scriptures_premium;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.AudioUtils;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Reverb;
import net.beadsproject.beads.ugens.WavePlayer;

public class interactive_scriptures_part2 extends AppCompatActivity {
private AdView mAdView;


    public void onDestroy() {



        if (soundPool != null) {

            soundPool.release();

        }
        System.gc();

        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_workshop2);


        //Initialize Buttons for some reason you can't go back
        initialize_buttons();
        soundp1_init(this);
        intialize_string_array();






    }

    //For the drums don't record anything just use the same drums
    //Just play a  drum in animation, play values every quarter with 7/8. Or every eight/note
    //With 7/8 you basically have to play 7 you only play 7 of those notes before the masure is done so it ends early.
ImageButton back_button_lesson;
    ImageView graph_wave_form;
    TextView Discussion1;
    TextView Discussion2;
    void initialize_buttons() {


        skip_button = (Button) findViewById(R.id.skip_button);
        back_activity_button = (Button) findViewById(R.id.back_button_activity);
        continue_button= (ImageButton) findViewById(R.id.continue_button);
        play_sound=(ImageButton) findViewById(R.id.temp_play_button);
        stop_sound= (ImageButton)findViewById(R.id.stop_play);
        back_button_lesson= (ImageButton)findViewById(R.id.back_button_lesson);
            discussion_text1= (TextView) findViewById(R.id.Discussion_1);
            discussion_text2= (TextView) findViewById(R.id.Discussion_2);

        answer_select1 = (RadioButton) findViewById(R.id.radioButton);
        answer_select2 = (RadioButton) findViewById(R.id.radioButton2);
        answer_select3 = (RadioButton) findViewById(R.id.radioButton3);
        answer_select4 = (RadioButton) findViewById(R.id.radioButton4);
        graph_wave_form = (ImageView)findViewById(R.id.graph_wave_form);
        Discussion1= (TextView) findViewById(R.id.Discussion_1);
        Discussion2= (TextView) findViewById(R.id.Discussion_2);
        graph_wave_form = (ImageView) findViewById(R.id.graph_wave_form);

        //setup_seek();
        skip_button.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){

                Intent intent1 = new Intent("android.intent.action.RUN.interactive_scriptures3");
                startActivity(intent1);


            }



        });


        back_activity_button.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){

                Intent intent1 = new Intent("android.intent.action.RUN.interactive_scriptures1");
                startActivity(intent1);


            }



        });



        play_sound.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){



                if (master_seq_step<1){

                }
                if (master_seq_step>=1&& master_seq_step<4){
                soundPool.play(sine_wave,.8f, .7f,1,0,1 );

                }


                if (master_seq_step>=4&& master_seq_step<=6){
                    soundPool.play(square_wave,.7f, .9f,1,0,1 );

                }

                if (master_seq_step>=6 &&master_seq_step<9){
                    soundPool.play(saw_wave,.8f, .8f,1,0,1 );
                    change_checked(3);
                }


                if (master_seq_step>=9 &&master_seq_step<11){
                    soundPool.play(part1_sine1,.8f, .8f,1,0,1 );


                }

                if (master_seq_step>=11 &&master_seq_step<12){
                    soundPool.play(part1_sine2,.8f, .8f,1,0,1 );


                }
                if (master_seq_step>=13 && master_seq_step <15){

                    soundPool.play(synth_sine_saw,.9f, .8f, 1, 0,1);
                }


                if (master_seq_step>=17 &&master_seq_step<18){

                    rhythms_engine.start();
                }

                if (master_seq_step>=18 &&master_seq_step<19){

                    rhythms_engine.start();
                }

                if (master_seq_step>=19 &&master_seq_step<=20){

                    rhythms_engine.start();
                }

            }



        });



        stop_sound.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){
            if (rhythms_engine!= null){
                rhythms_engine.end();
                turn_off_soundPoool();

                }
            }


        });



        continue_button.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){

             master_seq_step++;
             evaluate_step_position(master_seq_step);
            }



        });


        back_button_lesson.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){
                if (master_seq_step!=0) {
                    master_seq_step--  ;
                    evaluate_step_position(master_seq_step);
                }
                }



        });
    }
int seekbar_value=0;
void turn_off_soundPoool(){
    if (soundPool!=null){
        soundPool.autoPause();


    }


}
ValueAnimator rhythms_engine;
ValueAnimator sine_saw_adjuster;
float sine_vol_l, sine_vol_r;
float saw_vol_l, saw_vol_r;
 SeekBar seekbar;
void load_answerlabels(String[] labels){

    try {
     answer_select1.setText(labels[0]);
     answer_select2.setText(labels[1]);
     answer_select3.setText(labels[2]);
     answer_select4.setText(labels[3]);
 }catch(Exception ex){}
 }
void clear_labels (){
    answer_select1.setText("");
    answer_select2.setText("");
    answer_select3.setText("");
    answer_select4.setText("");
}
int key_sig=0; //if 4/4 then 1 if 7/8 then 2; if 5/4 then 3
int[] key_sigdur= new int[]{0,3000,4800,3187};
    int[] ending_float_value= new int[]{0,1000,875, 1250};
    float current1=0;
    void initialize_rhythm_animator(int ky_sig){
    if (rhythms_engine==null) {
        rhythms_engine = new ValueAnimator();
        rhythms_engine= ValueAnimator.ofFloat(0f, ending_float_value[key_sig]);
        rhythms_engine.setDuration(key_sigdur[key_sig]);
        rhythms_engine.setInterpolator(new LinearInterpolator());
        rhythms_engine.setRepeatCount(-1);

    }else{
        if (rhythms_engine.isRunning()){rhythms_engine.end();}
        rhythms_engine = new ValueAnimator();
        rhythms_engine= ValueAnimator.ofFloat(0f, ending_float_value[key_sig]);
        rhythms_engine.setDuration(key_sigdur[key_sig]);
        rhythms_engine.setInterpolator(new LinearInterpolator());
        rhythms_engine.setRepeatCount(-1);
    }


    rhythms_engine.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {


        @Override
        public void onAnimationUpdate(ValueAnimator animation_core_tempo) {
        if (key_sig==1){
            current1 = (float) (animation_core_tempo.getAnimatedValue());

            if(current1>0&&current1<10){//Set this to 875 as far as the float , To get it to the rihght time signature double the duration.
                snd_play(1);// This is the low drum pitch

            }


            if(current1>245&&current1<255){
               snd_play(2);// This is the high drum pitch

            }
            if(current1>495&&current1<505){
                snd_play(2);// This is the high drum pitch

            }

            if(current1>745&&current1<755){
                snd_play(2);// This is the high drum pitch

            }


        }

            if (key_sig==2){  //Set this or really for 750
                current1 = (float) (animation_core_tempo.getAnimatedValue());
                if(current1>0&&current1<10){
                    snd_play(1);// This is the low drum pitch
                }
                if(current1>120&&current1<130){
                    snd_play(2);// This is the high drum pitch
                }
                if(current1>245&&current1<255){
                    snd_play(2);// This is the high drum pitch
                }

                if(current1>370&&current1<380){
                    snd_play(2);// This is the high drum pitch
                }


                if(current1>495&&current1<505){
                    snd_play(2);// This is the high drum pitch
                }


                if(current1>620&&current1<630){
                    snd_play(2);// This is the high drum pitch
                }

                if(current1>745&&current1<755){
                    snd_play(2);// This is the high drum pitch
                }
            }


        if (key_sig==3){


            current1 = (float) (animation_core_tempo.getAnimatedValue());
            if(current1>0&&current1<10){//Set this to 875 as far as the float , To get it to the rihght time signature double the duration.
                snd_play(1);// This is the low drum pitch

            }


            if(current1>245&&current1<255){
                snd_play(2);// This is the high drum pitch

            }
            if(current1>495&&current1<505){
                snd_play(2);// This is the high drum pitch

            }

            if(current1>745&&current1<755){
                snd_play(2);// This is the high drum pitch

            }

            if(current1>955&&current1<1000){
                snd_play(2);// This is the high drum pitch

            }
        }
     }


    });

    }
public void snd_play(int note){

    if (note==1){
        soundPool.play(drum_low,.8f, .8f,1,0, 1);
    }
    if (note==2){
        soundPool.play(drum_high,.9f, .6f, 1,0,1);
    }


}
    void change_checked(int select_num){

    if (select_num==1) {
        answer_select1.setChecked(true); answer_select2.setChecked(false); answer_select3.setChecked(false);
        answer_select4.setChecked(false);
    }


    if (select_num==2) {
        answer_select1.setChecked(false); answer_select2.setChecked(true); answer_select3.setChecked(false);
        answer_select4.setChecked(false);
    }

    if (select_num==3) {
        answer_select1.setChecked(false); answer_select2.setChecked(false); answer_select3.setChecked(true);
        answer_select4.setChecked(false);
    }


    if (select_num==4) {
        answer_select1.setChecked(false); answer_select2.setChecked(false); answer_select3.setChecked(false);
        answer_select4.setChecked(true);
    }


}


    void intialize_string_array(){



        text_collections[1]= "Welcome to a semi-tutorial of sound and rhytm applied to concepts.  Selective basics of sound synthesis " +
                "and how it can be applied to concepts will be covered. Press the brown continue icon to continue throughout the App \n\n" +
                "";
        text_collections[2]= "Let's start. Press organe/yellow play button to generate a single sound.\n\n";
        text_collections[3]= "Now this is a low pitched sound wave.  Below is a image of a Sine wave." +
                "If you remember trig it is represented by the function y=sin(x).\n\n";
        text_collections[4]="Now play this sound, what does it sound like?" +
                "It is the same pitch as the Sine wave.\n\n ";
        text_collections[5]="Below is an image of a SQUARE wave. Can you hear the difference.  " +
                "I personally consider it like being a robot and this sound is definetely synthesized\n\n"   ;
        text_collections[6]="Now play this sound, what does it sound like?\n\n";

        text_collections[7]="Below is an image of a Saw wave. I hope you can definitely here the difference.  " +
                "It is more volatile compared to the other waveforms." +
                "Its almost like the sound is literally gnawing on something.  But it also sounds a little like a synthetic trumpet\n\n";
        text_collections[8]="Below is an image of a Saw wave.  Hopefully you can here the difference.  " +
                "It is has more dramatic drops compared to the other waveforms.  " +
                "Its almost like the sound is literally gnawing on something.\n\n";
        text_collections[9]= "If you listended to the music and viewed the animations in part1 there " +
                "was a low pitched wave at the beginning.  To refresh your memory click on (other) and play wave 1. " +
                "Guess the waveform?\n\n";
        text_collections[10]="It is a sine wave.  In associating the sound to an idea, What would a SINE wave represent? It represents coolness, smoothness. This is my view  of the beginning and " +
                "the creator of this Universe.  An alternative way of putting it, the Sine wave is like smooth flowing water or liquid.\n\n";
        text_collections[11]="As the sounds progress, you started to hear more complicated sounds such as drums. " +
                "Also, the continuous background waves start to get more ragged as if they are becoming more energized. " +
                " The musical concept I was trying to represent is  how God was gaining more traction.  The wave goes from " +
                "a more Sine wave to more of sawtooth background wave.  Click on wave 2" +
                " from the selction choices\n\n";
        text_collections[12]="You could also hear panning, or the spatial distribution of the sounds between left and right headphone, or to multi-speaker systems in gneral.\n\n";
        text_collections[13]="Play this sound. \n\n";
        text_collections[14]="This sound is a  Sine wave at first that has been converted to sawtooth wave.  So, you not only can modify this difference you can also quantify it as well. " +
                "This is what is so good about sound and its plasticity. There are numerous sounds where you can apply different " +
                "concepts and that you can freely experiment.  In Part 3, application will be applied to " +
                "using the waveforms.";
        text_collections[15]="Do you know little bit more about waveforms?  When you listen to different types of sounds " +
                "or music guess or think of what type of waveform it is.  The other important fact, sounds from  electronic devices such as cell phones and computers are geneated through numbers and functions.\n\n";
        text_collections[16]="Now let's briefly discuss rhythm.\n\n";
        text_collections[17]="Individuals that have taken some type of music class know that there are a certain " +
                "amount/count of beats in a measure.  Press the button in order to play common rhythms (key signatures)4 beats to every measure.\n\n";
        text_collections[18]="Now play this rhythm. (Press pause at any time) \n\n";
        text_collections[19]="Did you notice a difference?  Now play the other initial sound again.\n\n";
        text_collections[20]="The beats making sense are just  a little off or that the fell is a litte different.  It represents a 7/8 time measure, where 7 beats " +
                "have an eighth note measurement 7/8.  But the key is that it is an unequal ratio "+ "and that the beat/measure ratio is below 1 " +
                " or 7/8.   Also, this rhythm may sound off, and it is posibly due to the musical mind “trying to catch up\n\n";
        text_collections[21]="But there are other notations like 5/4 ratios and 7/4 ratios respectively:  either you are rushing, or you just " +
                "feel like you are very out of rhythm.  Take Five (5/4) by Dave Brubeck has a jazzy swing feel of a driving rhythm, athogh " +
                "the swing makes it more musically relevant.\n\n";
        text_collections[22]="In reference to the music at the beginning of the APP, this concept of time measurement was used.With a 7/8 measurement" +
                " in the beginning of the rhythm,  the song was then slowly and gradually shifted to a 4/4 ratio." +
                "You can listen again to see if you hear the shift.  It is even more prominent at the climax of the song.  " +
                "The representation of 7/8 alludes to God trying to press into something but not fully achieving it. " +
                " The 7/8 timing ratio is not an equal beat number to total beats ratio.  But it can also be viewed as God is " +
                "“working it out” on how to create the universe. After God has gained traction and light was emitted, that is " +
                "when the rhythm shifted to a straight 4/4 no-questions-asked beat.  The shift to 4/4 happens earlier but " +
                "it was complicated by the improvisations in the rhythms.\n\n";
        text_collections[23]="I hope that you enjoyed the song or rhythms or I hope that you understand the logic of utilizing elements of synthesis and rhythm" +
                "to achieve modeling an idea, concept, or an emotion.  All of sounds and rhythms cannot follow this type of system, and should just " +
                "be enjoyed as purely music.  In Part 3, I will give a snapshot of problems, where instead of answering with language you answer with a sound.\n\n";
    }


String[] text_collections = new String[25];

    ImageButton mute_button;
    ImageButton continue_button;
    Button skip_button;
    ImageButton play_sound;
    Button back_activity_button;

    ImageButton stop_sound;

    TextView discussion_text1;
    TextView discussion_text2;
    TextView discussion_text3;

    RadioButton answer_select1;
    RadioButton answer_select2;
    RadioButton answer_select3;
    RadioButton answer_select4;


    public int master_seq_step=1;//This will be the hebrew verion
    int seq1_eng=0; //This will be the english version
    int image_iter=0;
    int sound_iter=0;

    private void evaluate_step_position(int state_1){

    //Iterates through the the the string array that has already been initialized.
        switch (master_seq_step){
            case 0:
                discussion_text1.setText(text_collections[master_seq_step]);

                break;
            case 1:
                discussion_text1.setText(text_collections[master_seq_step]);
                break;
            case 2:
                discussion_text1.setText(text_collections[master_seq_step]);
                change_checked(1);

                break;
            case 3:
                discussion_text1.setText(text_collections[master_seq_step]);

                break;
            case 4:

                discussion_text1.setText(text_collections[master_seq_step]);
                break;
            case 5:
                discussion_text1.setText(text_collections[master_seq_step]);
                change_checked(2);
                break;
            case 6:
                discussion_text1.setText(text_collections[master_seq_step]);
                break;
            case 7:
                discussion_text1.setText(text_collections[master_seq_step]);
                change_checked(3);
                break;
            case 8:
                discussion_text1.setText(text_collections[master_seq_step]);

                break;
            case 9:
                discussion_text1.setText(text_collections[master_seq_step]);
                answer_select4.setText("Low pitched Sine wave 1");
                change_checked(4);

                break;
            case 10:
                discussion_text1.setText(text_collections[master_seq_step]);
                break;
            case 11:
                discussion_text1.setText(text_collections[master_seq_step]);
                change_checked(4);
                answer_select4.setText("Low pitched Sine wave 2");
                break;
            case 12:
                discussion_text1.setText(text_collections[master_seq_step]);
                break;
            case 13:
                discussion_text1.setText(text_collections[master_seq_step]);
              //  sine_saw_adjustor();
                break;
            case 14:
                discussion_text1.setText(text_collections[master_seq_step]);
                break;
            case 15:
                discussion_text1.setText(text_collections[master_seq_step]);
                break;
            case 16:
                discussion_text1.setText(text_collections[master_seq_step]);
                clear_labels();
                String[] answer_labels2= new String[]{"Sine wave","Square wave", "Sawtooth wave","Other wave"};
                load_answerlabels(answer_labels2);
                change_checked(4);
                break;
            case 17:
                discussion_text1.setText(text_collections[master_seq_step]);
                clear_labels();
                key_sig= 1;
                String[] answer_labels= new String[]{"4/4 rhhythm 1","7/8  rhythm 2", "5/4 rhythm 3"};
                load_answerlabels(answer_labels);
                change_checked(1);
                initialize_rhythm_animator(1);
                break;
            case 18:
                discussion_text1.setText(text_collections[master_seq_step]);
                key_sig=2;
                initialize_rhythm_animator(2);
                change_checked(2);
                break;
            case 19:
                discussion_text1.setText(text_collections[master_seq_step]);
                key_sig=3;
                initialize_rhythm_animator(3);
                change_checked(3);
                break;
            case 20:
                discussion_text1.setText(text_collections[master_seq_step]);
                break;
            case 21:
                discussion_text1.setText(text_collections[master_seq_step]);
                break;
            case 22:
                discussion_text1.setText(text_collections[master_seq_step]);
                break;
            case 23:
                discussion_text1.setText(text_collections[master_seq_step]);
                break;
            case 24:
                discussion_text1.setText(text_collections[master_seq_step]);
                break;
        }


     if (master_seq_step<2){
        graph_wave_form.setBackgroundResource(R.drawable.graph_only);
     }
        if (master_seq_step>2&& master_seq_step<5){
            graph_wave_form.setBackgroundResource(R.drawable.sine_wave);
           // synthesize_sound(1);
        }


        if (master_seq_step>=5&& master_seq_step<=6){
            graph_wave_form.setBackgroundResource(R.drawable.square_wave);
            //synthesize_sound(2);
        }

        if (master_seq_step>=7&& master_seq_step<=9){
            graph_wave_form.setBackgroundResource(R.drawable.sawtooth_wave);
            //synthesize_sound(3);
        }

        if (master_seq_step >10){
            graph_wave_form.setBackgroundResource(R.drawable.graph_only);
            //synthesize_sound(3);
        }
    }

    short[] saudioval= new short[50000];

    float[] faudioval= new float[50000];
    short[][] k1 = new short[200][];
    short[][] k2 = new short[200][];
    AudioContext ac= new AudioContext(50000);
    AudioUtils audiotransform = new AudioUtils();
    AudioTrack at = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_FLOAT, 400000, AudioTrack.MODE_STREAM);

    public void synthesize_sound(int wave_pick){
    //This sub will produce a raaw sine, square waves.




//    for (int j=0; j<=193;j++){
  //      k1[j]=  Arrays.copyOfRange(this.audioval,iter_st, iter_st + iter_fn);
    //    k2[j]=  Arrays.copyOfRange(this.audioval2,iter_st, iter_st + iter_fn);
      //  iter_st+=iter_fn;

    //}


    switch (wave_pick){
        case 1:
            audio_sine();
        break;
        case 2:
            audio_square();
            break;
        case 3:
            audio_saw();
        break;

    }

}


public void audio_sine(){     WavePlayer wav1 = new WavePlayer(ac, 100f, Buffer.SINE);
    Envelope env1= new Envelope(ac, 0f);
    env1.addSegment(.01f, 50);
    env1.addSegment(.1f, 250);
    env1.addSegment(.8f, 1000);
    env1.addSegment(.3f, 250);
    env1.addSegment(.01f, 50);
    env1.addSegment(0, 50);

    Gain gain1= new Gain (ac,1,env1);

    gain1.addInput(wav1);
    Reverb rev1=  new Reverb(ac);
    rev1.setSize(.8f);
    rev1.setDamping(.4f);
    rev1.addInput(gain1);
    ac.out.addInput(rev1);
    //ac.out.update();
    ac.out.calculateBuffer();
    faudioval=ac.out.getOutBuffer(0);

    audiotransform.floatToShort(saudioval,faudioval);}
    public void audio_square(){
        WavePlayer wav1 = new WavePlayer(ac, 100f, Buffer.SQUARE);
        Envelope env1= new Envelope(ac, 0f);
        env1.addSegment(.01f, 50);
        env1.addSegment(.1f, 250);
        env1.addSegment(.8f, 1000);
        env1.addSegment(.3f, 250);
        env1.addSegment(.01f, 50);
        env1.addSegment(0, 50);

        Gain gain1= new Gain (ac,1,env1);

        gain1.addInput(wav1);
        Reverb rev1=  new Reverb(ac);
        rev1.setSize(.8f);
        rev1.setDamping(.4f);
        rev1.addInput(gain1);
        ac.out.addInput(rev1);
        ac.out.update();
        ac.out.calculateBuffer();
        faudioval=ac.out.getOutBuffer(0);

        audiotransform.floatToShort(saudioval,faudioval);

    }
    public void audio_saw(){
        WavePlayer wav1 = new WavePlayer(ac, 100f, Buffer.SAW);
        Envelope env1= new Envelope(ac, 0f);
        env1.addSegment(.01f, 50);
        env1.addSegment(.1f, 250);
        env1.addSegment(.8f, 1000);
        env1.addSegment(.3f, 250);
        env1.addSegment(.01f, 50);
        env1.addSegment(0, 50);

        Gain gain1= new Gain (ac,1,env1);

        gain1.addInput(wav1);
        Reverb rev1=  new Reverb(ac);
        rev1.setSize(.8f);
        rev1.setDamping(.4f);
        rev1.addInput(gain1);
        ac.out.addInput(rev1);
        ac.out.update();
        ac.out.calculateBuffer();
        faudioval=ac.out.getOutBuffer(0);

        audiotransform.floatToShort(saudioval,faudioval);

    }

    SoundPool soundPool;
    int sine_wave, saw_wave, square_wave, drum_low, drum_high, part1_sine1, part1_sine2;
    int long_sine, long_saw, long_square;
    int synth_sine_saw, synth_saw;

    private void soundp1_init(Context context){

        //I cannot extend this claass so this will be where all of the different types of drums will be
        //I will have at most 5 different types of drums playing.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttrib).setMaxStreams(6).build();

        }
        else {

            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        }





        sine_wave = soundPool.load(this, R.raw.sine_wave1, 1);
        saw_wave = soundPool.load(this, R.raw.sawtooth_wave1, 1);
        square_wave = soundPool.load(this, R.raw.square_wave1, 1);
        drum_low = soundPool.load(this, R.raw.afro_drums12w, 1);
        drum_high= soundPool.load(this, R.raw.afro_drums6, 1);
        part1_sine1= soundPool.load(this, R.raw.lp1_wave_continous, 1);
        part1_sine2 = soundPool.load(this, R.raw.low4_scrip1, 1);
        long_sine = soundPool.load(this, R.raw.sine_wave_3m, 1);
        long_square = soundPool.load(this, R.raw.square_wave1m, 1);
        long_saw = soundPool.load(this, R.raw.sawtooth_wave1m, 1);
        synth_sine_saw = soundPool.load(this, R.raw.wave_to_sine,1);

        //MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.bassdrum_2);
        //mediaPlayer.start(); // no need to call prepare(); create() does that for you
        //mediaPlayer.reset();

        ;


        AudioContext ac = new AudioContext();

        // load the source sample from a file

    }


}
