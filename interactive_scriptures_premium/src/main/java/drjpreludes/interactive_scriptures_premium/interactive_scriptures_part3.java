package drjpreludes.interactive_scriptures_premium;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import net.beadsproject.beads.core.AudioContext;

public class interactive_scriptures_part3 extends AppCompatActivity {
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
        setContentView(R.layout.sound_workshop);


        //Initialize Buttons for some reason you can't go back
        initialize_buttons();
        soundp1_init(this);
        intialize_string_array();

    }

//

    void initialize_buttons(){
        discussion_text1 =(TextView) findViewById(R.id.Question_text);
        discussion_text2 =(TextView) findViewById(R.id.Interpretation_Text);
        answer_select1 = (RadioButton) findViewById(R.id.radioButton);
        answer_select2 = (RadioButton) findViewById(R.id.radioButton2);
        answer_select3 = (RadioButton) findViewById(R.id.radioButton3);
        answer_select4 = (RadioButton) findViewById(R.id.radioButton4);
        continue_button=(Button) findViewById(R.id.continue_button);
        play_button=(Button) findViewById(R.id.play_sound);
        stop_button=  (Button) findViewById(R.id.stop_sound);
        back_button= (Button) findViewById(R.id.back_button);
        activity_back_button= (Button) findViewById(R.id.back_activity_button);

        activity_back_button.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){

                Intent intent1 = new Intent("android.intent.action.RUN.interactive_scriptures2");
                startActivity(intent1);
            }



        });



        play_button.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){

                if (master_seq_step<8){
                    switch(answer_selected){
                        case 1:
                            soundPool.play(sine_wave,.8f, .7f,1,0,1 );
                            break;
                        case 2:
                            soundPool.play(square_wave,.7f, .9f,1,0,1 );
                            break;
                        case 3:
                            soundPool.play(saw_wave,.8f, .8f,1,0,1 );
                            break;
                        case 4:
                            soundPool.play(synth_sine_saw,.9f, .8f, 1, 0,1);
                            break;
                    }
                }


                if (master_seq_step>=8){
                    switch(answer_selected){
                        case 1:
                            key_sig=1;
                            initialize_rhythm_animator(1);
                            rhythms_engine.start();
                            break;
                        case 2:
                            key_sig=2;
                            initialize_rhythm_animator(2);
                            rhythms_engine.start();
                            break;
                        case 3:
                            key_sig=3;
                            initialize_rhythm_animator(3);
                            rhythms_engine.start();
                            break;
                        case 4:
                    }
                }
            }



        });



        stop_button.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){
                if (rhythms_engine!= null){
                    rhythms_engine.end();
                    turn_off_soundPoool();

                }
            }


        });



        continue_button.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){
            if (iter_continue==2){iter_continue=0;}
                iter_continue++;
              if (iter_continue==1) {
                  master_seq_step++;
                  evaluate_step_position(master_seq_step);
                  //clear_checked();
              }
               if (iter_continue==2) {
                   master_seq_step_disc++;
                   evaluate_step_position(master_seq_step);

               }
            }



        });


        back_button.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){
              iter_back++;
                if (master_seq_step!=0||master_seq_step_disc!=0) {

                 if (iter_continue ==1){
                     master_seq_step--;
                     evaluate_step_position(master_seq_step);
                        iter_continue=2;
                    }
                    if (iter_continue ==2){
                        master_seq_step_disc--;
                        evaluate_step_position(master_seq_step);
                        iter_continue=1;
                    }



                }
            }



        });



        answer_select1.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){
             //If it is selected obtain its value  and then forward and makea  connector to onPlay so that it can read i
             //and then play the value.
          //  answer_select1.getText();
            answer_selected=1;


            }



        });

        answer_select2.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){
                //If it is selected obtain its value  and then forward and makea  connector to onPlay so that it can read i
                //and then play the value.
                //  answer_select1.getText();
                answer_selected=2;


            }



        });

        answer_select3.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){
                //If it is selected obtain its value  and then forward and makea  connector to onPlay so that it can read i
                //and then play the value.
                //  answer_select1.getText();
                answer_selected=3;


            }



        });
        answer_select4.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){
                //If it is selected obtain its value  and then forward and makea  connector to onPlay so that it can read i
                //and then play the value.
                //  answer_select1.getText();
                answer_selected=4;


            }



        });
    }

public int answer_selected=0;

    void sound_routine (){
        //This function will initialize all of the onclick radiolabel listeners where whenever music plays
        //it will select the value of the listener to play or it will select key sig, to play.


    }
    void load_answerlabels(String[] labels){

        try {
            answer_select1.setText(labels[0]);
            answer_select2.setText(labels[1]);
            answer_select3.setText(labels[2]);
            answer_select4.setText(labels[3]);
        }catch(Exception ex){}
    }


    int iter_continue=0;
    int iter_back=0;
    void clear_labels (){
        answer_select1.setText("");
        answer_select2.setText("");
        answer_select3.setText("");
        answer_select4.setText("");
    }
    void clear_checked(){
        answer_select1.setChecked(false);
        answer_select2.setChecked(false);
        answer_select3.setChecked(false);
        answer_select4.setChecked(false);
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



        text_collections[0]= "Here is where fundamental learning begins, applying your knowledge."  +
                "This will be a little different than your average question and instead responding by language " +
                "you will respond by musical language.  Let's begin. \n\n";

        question_collections[1]="How would you answer the following question through a musical response: only take into account the waveforms (Sine, Sawtooth, and Square)?\n"+
        "Pick the closest sound that can describe the sensation of your favorite dark or milk chocolate oozing in your mouth as you are consuming it?\n\n";
        answer_collections[1]="Developer’s answer:  I would say that it could be described by a Sine wave, " +
                "especially for me personally.  The warm chocolate oozing especially, if it has no nuts in it.  " +
                "If it had nuts,it would have a slight tinge of Sawtooth and possibly the Sine waves going in and out.  " +
                "You could also view a sawtooth wave as the initial euphoria of tasting that chocolate.  " +
                "There can be multiple answers.\n\n";

        question_collections[2]="Describe in sound a piece of metal?\n\n";
        answer_collections[2]="Because metal is rigid and hard, I can rule out Sine wave.  in my opinion if the metal is not " +
                "doing anything not breaking down anything, then I would describe it as a Square waveform. \n\n";


        question_collections[3]="Describe in sound a worn down brake pad that is making a lot of noise and producing a lot of friction?\n\n";
        answer_collections[3]="I would say it would be a Sawtooth where it would have " +
                "lot’s of friction that is not represented by a Sine wave or Square wave. Another interesting " +
                "aspect is that the sound mechanism of  horns, especially brass horns, is produced by secondary friction/sound of lip vibrations.\n\n";

        question_collections[4]="Describe in sound a nightclub where the decorations are filled with geometric shapes.\n\n";
        answer_collections[4]="Geometric shapes do not have the same feel as friction nor smoothness.   Because, " +
                "shapes have precise boundaries that does not cause friction, except for the circles(--and they are more mathematically represented bythe sine/cos wave).  The Square waveform I think best represents geometric shapes of the 3 waveforms presented.  This may be subjective but this is how I view it. " +
                "  In my opinion, techno music has a lot of sounds that are patterned from the Square waveform, this as a side point.\n\n";

        question_collections[5]="Here is the hardest question for this version of the App.   " +
                "Describe in sound, the sudden loss of eyesight due to a parasite eating away at valuable eye tissue.  " +
                "What type of waveform would represent this unfortunate incident?  Or can their be multiple waveforms " +
                "represented?  Think about all of the waveforms discussed.\n\n ";

        answer_collections[5]="To begin, there is a lot to this answer.This is where multiple sounds would be best in modeling what is happening" +
                "and you would have to incorporate time into the solution.  Also, the sounds must play simultaneously. " +
                "So let’s break this down.   You have flesh eating parasites that eat away at tissue in a human eye. " +
                "The act of eating away at the tissue should be represented by a sawtooth wave (of friction) which is the second dominant sound.  " +
                "This sawtooth gradually diminishes as more of the eyesight is gone.\n\n"

;

        answer_collections[6]="cont'd";
        answer_collections[6]="Next, the square sound that represents definition or borders,--in this case visual images. It is being decreased as the parasite eats at the eye.   The square waveform would be the predominant waveform initially.  " +
                "Because of the parasite, the Square waveform is now replaced by the Sine waveform." +
                "As you recall the Sine waveform represents  no defining points (ir ia smoooth)and in this case it would indicate more of a blur in visual eyesight." +
                "Play the synth to hear the sound representation.\n\n" ;
        answer_collections[7]="cont'd";
        answer_collections[7]="Additional factors can also contribute to this model or the answer.  You could also think of how a drug/medicine, age of the victim, location of the parasites, and  etc.  impacts each of the elements." +
                "Alternatively, there can also be a differential equation generated by this same problem.  " +
                "Nevertheless, sound may have an advantage in being more approachable, and it also has a diversity of parameters  that can aid in finding/understanding solutions, treatment, and the problem itself.\n\n";
        question_collections[8]="We are now going to go through rhythm, in a few simple questions. Remember the concept of a full ratio or complete time signature vs and incomplete ratio and a fraction in terms of key signature." +
                ".  Lete begin:" +
                "Which rhythm would represent: a car that has an ineffective piston in its motor?  Remember the concept of a full ratio" +
                "verses on unfilled ratio of beats/beat length ratio .\n\n";
                answer_collections[8]="I would pick the 7/8  time signature.  Meaning that car is not performing at full efficiency." +
                        "I would think that an 8/8 or 4/4 time signature would represent an effective motor or piston.\n\n";;
        question_collections[9]="Which rhythm would represent animals dying off due to pollution and climate change.";
        answer_collections[9]="This is the same concept of organisms are not operating at their normal capacity."+
                "There may be more complicated rhythms involved to give more complexity to the problem."+"The key variables are time and the environment.\n\n";
        question_collections[10]="Which rhythm would represent river water driving a turbine for electricity?";
        answer_collections[10]="This will be 4/4 since you mostly think about a river having a steady pace of movement " +
                "even if the river speed picks up, the beat per measure stays the same,which the tempo increases.  But lets say that water is being " +
                "diverted by human activity, then I will say that it goes to 7/8 time meter because it is not functioning at full capcity. " +
                "Even if there is a lot of water being dumped to the river with heavy rainfalls.\n\n";

        question_collections[11]="If you don't agree, it's okay. Rhythm is hard.";
        answer_collections[11]="A side note:  Rhythm may be harder to use for modeling compared to a waveforms.  But it is based off musc time and relativity.  E=mc^2  from Einstein incorporates time, mass, and energy all into one equation where they are related.  Also, Quantum Theory also deals with time: that is simultaneous, in subatomic particles.  Since rhythm is inherently built on time and relative entities," +
                " the development of quantum computer algorithms may can benefit from possibly modeling these algorithms with rhythmic entities.  These algoriths may be very plastic, but tightly regulated, as such in music.  In which, this may ultimately lead to an efficient and powerful quantum computer." +
                " With both of these two scientific theories/fields, training the next generation of innovators using multifaceted approaches that are more experimental such as rhythms may help in learning how to develop powerful Quantum computer algorithms and additional modeling strategies in Physics and Math.  Although, it will be challenging but fun." +
                " For a musician fluctuating rhythms are difficult. " +
                " For scientists solving problems using relativity and quantum theory are also difficult." +
                " Time is tough, but is very powerful."

;

        question_collections[12]="Now this question is in reverse: you have the sound, now give the idea:\n" +
                "Knowing the difference between 7/8 rhythm and an 4/4 rhythm, how would you describe a thinking being represented in a musical context.  In the musical context the rhythm fluctates from a 7/8 to 4/4 key signature back to 7/8 to 4/4 and then rhythm goes into 5/4 continously. Think about it freely and then look at the developer's answer.\n\n";
        answer_collections[12]="I would say that it is a description of a struggle in which once you get out of that struggle: you press forward steadily, and surely, and out of sync with your surroundings, represented by the 5/4 time signature.   You are always one beat ahead, which is also an advantage.  This is also because you are trying to be your best and dominate your rhythm in life.\n" +
                "Thanks for spending the time to experience this App.  If you like it, add  the premium version to your collection add-free or if you want more Apps similar to this one or that you like the idea.  \n\n\n";
    }

    int key_sig=0; //if 4/4 then 1 if 7/8 then 2; if 5/4 then 3
    int[] key_sigdur= new int[]{0,3000,4800,3187};
    int[] ending_float_value= new int[]{0,1000,875, 1250};
    float current1=0;
   ValueAnimator rhythms_engine;
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
String[] text_collections = new String[3];

    String[] question_collections = new String[20];
    String[] answer_collections= new String[20];
    Button exit_button1;
    ImageButton mute_button;
    Button continue_button;
    Button back_button;
    Button activity_back_button;
    Button play_button;
    Button stop_button;


    TextView discussion_text1;
    TextView discussion_text2;
    TextView discussion_text3;

    RadioButton answer_select1;
    RadioButton answer_select2;
    RadioButton answer_select3;
    RadioButton answer_select4;



    public int master_seq_step=0;int master_seq_step_disc=0;
    int seq1_eng=0;
    int image_iter=0;
    int sound_iter=0;
    private void evaluate_step_position(int state_1) {

        //Iterates through the the the string array that has already been initialized.
     if (iter_continue==1) {
         switch (master_seq_step) {
             case 0:
                 discussion_text1.setText(question_collections[master_seq_step]);

                 break;
             case 1:
                 discussion_text1.setText(question_collections[master_seq_step]);
                 break;
             case 2:
                 discussion_text1.setText(question_collections[master_seq_step]);


                 break;
             case 3:
                 discussion_text1.setText(question_collections[master_seq_step]);

                 break;
             case 4:

                 discussion_text1.setText(question_collections[master_seq_step]);
                 break;
             case 5:
                 discussion_text1.setText(question_collections[master_seq_step]);

                 break;
             case 6:
                 discussion_text1.setText(question_collections[master_seq_step]);
                 break;
             case 7:
                 discussion_text1.setText(question_collections[master_seq_step]);
                 clear_labels();
                 String[] answer_labels2 = new String[]{"Sine wave", "Square wave", "Sawtooth wave", "Other wave"};
                 load_answerlabels(answer_labels2);
                 break;
             case 8:
                 discussion_text1.setText(question_collections[master_seq_step]);
                 clear_labels();
                 String[] answer_labels = new String[]{"4/4 rhhythm 1", "7/8  rhythm 2", "5/4 rhythm 3"};
                 load_answerlabels(answer_labels);
                 break;
             case 9:
                 discussion_text1.setText(question_collections[master_seq_step]);
                 break;
             case 10:
                 discussion_text1.setText(question_collections[master_seq_step]);
                 break;
             case 11:
                 discussion_text1.setText(question_collections[master_seq_step]);
                 break;
             case 12:
                 discussion_text1.setText(question_collections[master_seq_step]);
                 break;
             case 13:
                 discussion_text1.setText(question_collections[master_seq_step]);
         }
     }

      if (iter_continue==2){
        switch (master_seq_step_disc) {
            case 0:
                discussion_text2.setText(answer_collections[master_seq_step]);

                break;
            case 1:
                change_checked(1); //The answe will be given;
                discussion_text2.setText(answer_collections[master_seq_step]);
                break;
            case 2:
                discussion_text2.setText(answer_collections[master_seq_step]);
                change_checked(2);

                break;
            case 3:
                discussion_text2.setText(answer_collections[master_seq_step]);
                change_checked(3);
                break;
            case 4:
                change_checked(2);

                discussion_text2.setText(answer_collections[master_seq_step]);
                break;
            case 5:
                discussion_text2.setText(answer_collections[master_seq_step]);
                change_checked(4);
                break;
            case 6:
                discussion_text2.setText(answer_collections[master_seq_step]);
                change_checked(4);
                break;
            case 7:
                discussion_text2.setText(answer_collections[master_seq_step]);
                answer_select4.setText("Square to Sine-diminishing Saw. \n");
                change_checked(4);
                break;
            case 8:
                discussion_text2.setText(answer_collections[master_seq_step]);
                change_checked(2);
                break;
            case 9:
                discussion_text2.setText(answer_collections[master_seq_step]);

                change_checked(2);

                break;
            case 10:
                discussion_text2.setText(answer_collections[master_seq_step]);
                change_checked(1);
                break;
            case 11:
                discussion_text2.setText(answer_collections[master_seq_step]);
                change_checked(4);
                answer_select4.setText("n/a");
                break;
            case 12:
                discussion_text2.setText(answer_collections[master_seq_step]);
                break;
            case 13:
                discussion_text2.setText(answer_collections[master_seq_step]);
                //  sine_saw_adjustor();
                break;
            case 14:
                discussion_text2.setText(answer_collections[master_seq_step]);
                break;
            case 15:
                discussion_text2.setText(answer_collections[master_seq_step]);
                break;
        }
    }

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
    void turn_off_soundPoool(){
        if (soundPool!=null){
            soundPool.autoPause();

            //soundPool.play();
        }


    }
}
