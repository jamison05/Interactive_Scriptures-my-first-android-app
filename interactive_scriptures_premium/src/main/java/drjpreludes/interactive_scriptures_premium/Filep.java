package drjpreludes.interactive_scriptures_premium;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.BiquadFilter;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.LPRezFilter;
import net.beadsproject.beads.ugens.WavePlayer;

import java.util.HashMap;

public class Filep {
	AudioContext ac= new AudioContext(100000);
	public int ac_size=20000;//default ac_size;
	public static void  main(String[] args) {
		// TODO Auto-generated method stub

		


		
	    //The structure of the program will have several layers but there is the main 
		//The program  will have 3 sets of Hashmaps
		//1. The HasH map will recognize unique numbers and then associate a UGen to it.
		//2. The HashH map will then take that unique number then associate that to a properties of the UGen
		// a. These properties will consist of setBuffer, setFrequnecya, and other modifications specifically within the UGen
		// b. the last is that it will create a bead object for the particular Ugen can access, from its unique number. It will also 
		//have a unique target that will automatically associaate or connect to it.  
		
		// Create all components of the wave, create the complex envelope with filtering, you can also modify 
		//It will be alot but at least you are not creating if conditions.  From the hand given numbers, to the waves, ultra fine envelopes 


		//for (Map.Entry < String, Font > entry : map.entrySet()) { String key = entry.getKey(); Font value = entry.getValue(); // ... }
       Filep j= new Filep();
       j.obtain();
       j.play_sound();
       j.sine_wave1_complexpan1(44, 100000);
       

	}

	private  void obtain(){
		
	
		
	}

    private float pitchToFrequency(float wav1_pitch) {
		/*
		 *  MIDI pitch number to frequency conversion equation from
		 *  http://newt.phys.unsw.edu.au/jw/notes.html
		 */
        double exponent = (wav1_pitch - 69.0) / 12.0;
        return (float) (Math.pow(2, exponent) * 436.0f);
    }

    public void play_sound(){
    	
		float wav1_pitch=67;
		Envelope iter1env = new Envelope (ac,0);
		
		WavePlayer wav1= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav2= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav3= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav4= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav5= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav6= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav7= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav8= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav9= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav10= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav11= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav12= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav13= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		WavePlayer wav14= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		Envelope env_wav2_2 = new Envelope(ac, 0);
		
		for (int i=0; i<8;i++){
			
			env_wav2_2.addSegment((float)i/8f, (float)i*100);
	
		}
		
		Gain gain22_wav = new Gain(ac,1,env_wav2_2);
		gain22_wav.addInput(wav2);
		gain22_wav.update();
		
		
		WavePlayer wav16= new WavePlayer(ac,pitchToFrequency(wav1_pitch),Buffer.SINE);
		Envelope env_wav1_1 = new Envelope(ac, 0);
		Envelope env_wav1_2 = new Envelope(ac, 0);//Here is the envelope modulatin gain
		Envelope env_wav1_3 = new Envelope(ac, 0);//Here is the envelope modulating one filter 
		Envelope env_wav1_4 = new Envelope(ac, 0);//Here is a enevelope modulating the 2econd filer
		Envelope env_wav1_5 = new Envelope(ac, 0);//Here is a envelope modulating the Panner

		
		
		BiquadFilter peakFilter_wav1 = new BiquadFilter(ac, BiquadFilter.BP_PEAK, 300.0f, -300.0f);
		peakFilter_wav1.addInput(wav1);																			  //End value is Quality where 1 is low lets everything in																				  //high numbers only let what is around the cut off in.  We will play with this filter to bring a little presence. 
		BiquadFilter skirtFilter_wav1 = new BiquadFilter(ac, BiquadFilter.BP_SKIRT, 300.0f, 300.0f); //Skirt has a range where sounds are falling off. 
		LPRezFilter lprezFilter_wav1 = new LPRezFilter(ac, 300, .25f);//The last value is the resonannce which can flow from 0 to 1. 
		
		//Modulating the filters with the panning will be very important. 
		//I will have the input 
		
		
		Envelope env_wav2_1 = new Envelope(ac, 0);
		//Here is the envelope modulatin gain
		Envelope env_wav2_3 = new Envelope(ac, 0);//Here is the envelope modulating one filter 
		Envelope env_wav2_4 = new Envelope(ac, 0);//Here is a enevelope modulating the 2econd filer
		Envelope env_wav2_5 = new Envelope(ac, 0);//Here is a envelope modulating the Panner
		
		
		Envelope env_wav3_1 = new Envelope(ac, 0);
		Envelope env_wav3_2 = new Envelope(ac, 0);//Here is the envelope modulatin gain
		Envelope env_wav3_3 = new Envelope(ac, 0);//Here is the envelope modulating one filter 
		Envelope env_wav3_4 = new Envelope(ac, 0);//Here is a enevelope modulating the 2econd filer
		Envelope env_wav3_5 = new Envelope(ac, 0);//Here is a envelope modulating the Panner
		Gain gain3_wav = new Gain(ac,1,env_wav3_2);
		
		Envelope env_wav4_1 = new Envelope(ac, 0);
		Envelope env_wav4_2 = new Envelope(ac, 0);//Here is the envelope modulatin gain
		Envelope env_wav4_3 = new Envelope(ac, 0);//Here is the envelope modulating one filter 
		Envelope env_wav4_4 = new Envelope(ac, 0);//Here is a enevelope modulating the 2econd filer
		Envelope env_wav4_5 = new Envelope(ac, 0);//Here is a envelope modulating the Panner
		Gain gain4_wav = new Gain(ac,1,env_wav4_2);
		gain4_wav.setGain(-1f);
		AudStruct_wave mod_buf_eq1 = new AudStruct_wave();
		mod_buf_eq1.setup_function(1, 5, 10,.1f,.5f, 0);//All of the equations you will get good at.  
		Float[] obtain_1 = new Float[2];
		Float[] obtain_2= new Float[2];
		Envelope env_wav2_21 = new Envelope(ac, 0);
		float time_duration =1000;
		
		for (int k=0; k < mod_buf_eq1.env1.size();k++){
			obtain_1=mod_buf_eq1.env1.get(k);
			if (k<mod_buf_eq1.env1.size()-1){
			obtain_2=mod_buf_eq1.env1.get(k+1);}else{
				obtain_2[1]=1f;
			}
			env_wav2_21.addSegment(obtain_1[1],(obtain_2[0]-obtain_1[0])*time_duration);
		}
		env_wav2_21.addSegment(0,100f);
		Gain gain2_wav = new Gain(ac,1,env_wav2_21);     
		gain2_wav.addInput(wav1);

   		//ac.out.addInput(gain2_wav);
   	//ac.out.update();
   		//ac.start();
		
		for (int i =0;i <10; i++){
				
			 iter1env.addSegment((1/(i+1)),(i*10));
			}
	 	
        int norm1_env=1;
		HashMapworker lter =new HashMapworker(1);
		lter.intiate_worker();
	   	 float [][] a_env1_mod = new float [2][5]; 	
	   	 a_env1_mod=lter.envelope_design(2,1,2000);//Standard envelope
	   	 //After you obtain the float values you just directly add it to the envelope.
	   	 for (int e1a=0;e1a<a_env1_mod[0].length;e1a++){
	   		env_wav1_1.addSegment(a_env1_mod[0][e1a],a_env1_mod[1][e1a]);

	   	 }
	   	 
			Gain gain1_wav = new Gain(ac,1,env_wav1_1);
			gain1_wav.addInput(wav1);
	   		
	   		//ac.out.addInput(gain2_wav);
	   		//ac.out.update();
	   		//ac.start();
    }
    
   float [][] sine_wave1_complexpan1(int wav1_pitch, float wave_size){
	   float [][] buffer_wave1 = new float[2][ac_size];
	   //Probably is better to break down the waves
	    int norm1_env=1;

		WavePlayer wav1 = new WavePlayer(ac, pitchToFrequency(wav1_pitch), Buffer.SINE);
		WavePlayer wav2 = new WavePlayer(ac, pitchToFrequency(wav1_pitch), Buffer.SQUARE);
		WavePlayer wav3 = new WavePlayer(ac, pitchToFrequency(wav1_pitch), Buffer.TRIANGLE);
		WavePlayer wav4 = new WavePlayer(ac, pitchToFrequency(wav1_pitch), Buffer.SINE);
		WavePlayer wav5 = new WavePlayer(ac, pitchToFrequency(wav1_pitch), Buffer.SINE);
	
		//With this you can possibly be able to create te value with 
		HashMapworker lter =new HashMapworker(1);
		
		lter.intiate_worker();
		
		Envelope env_wav1_1 = new Envelope(ac, 0);
		Envelope env_wav1_2 = new Envelope(ac, 0);//Here is the envelope modulatin gain
		Envelope env_wav1_3 = new Envelope(ac, 0);//Here is the envelope modulating one filter 
		Envelope env_wav1_4 = new Envelope(ac, 0);//Here is a enevelope modulating the 2econd filer
		Envelope env_wav1_5 = new Envelope(ac, 0);//Here is a envelope modulating the Panner
	   
		
		float [][] a_env1_mod = new float [2][5]; 	
	   	 a_env1_mod=lter.envelope_design(2,1,1000);//Standard envelope
	   	 //After you obtain the float values you just directly add it to the envelope.
	   	 for (int e1a=0;e1a<a_env1_mod[0].length;e1a++){
	   		env_wav1_1.addSegment(a_env1_mod[0][e1a],a_env1_mod[1][e1a]); //Manual in addition of segments  
	   	 }
	   	 
			Gain gain1_wav = new Gain(ac,1,env_wav1_1);
			gain1_wav.addInput(wav1);
	   		gain1_wav.calculateBuffer();
	   		gain1_wav.update();
	   
	   		AudStruct_wave mod_buf_eq1 = new AudStruct_wave();
			//The equation will modulate the wave
	   		
	   		//(function_number,int segments, int parts, float func_freq, float func_amplitude,float shift){
	   		mod_buf_eq1.setup_function(1, 2, 10, 5, 1, 0);//All of the equations you will get good at.  
			Float[] obtain_1 = new Float[2];
			Float[] obtain_2= new Float[2];
			Envelope env_wav2_21 = new Envelope(ac, 0);
			float time_duration =5000;
			
			for (int k=0; k < mod_buf_eq1.env1.size();k++){
				obtain_1=mod_buf_eq1.env1.get(k);
				if (k<mod_buf_eq1.env1.size()-1){
				obtain_2=mod_buf_eq1.env1.get(k+1);}else{
					obtain_2[1]=1f;
				}
				env_wav2_21.addSegment(obtain_1[1],(obtain_2[0]-obtain_1[0])*time_duration);
			}
			
			

			
			float[] arg1= new float[512];
	
			AudStruct_wave pf_wav1= new AudStruct_wave();
					//( int function_number,int segments, int parts, float func_freq, float func_amplitude,float shift){
					
					pf_wav1.setup_function(1, 1, 4, 1, 1, 0);//All of the equations you will get good at.  
					Envelope pf_wav1_env = new Envelope(ac, 0);
					time_duration =50;
				
					for (int k=0; k < pf_wav1.env1.size();k++){
						
						obtain_1=pf_wav1.env1.get(k);		
							if (k<pf_wav1.env1.size()-1){
							obtain_2=pf_wav1.env1.get(k+1);}else{
								obtain_2[1]=1f;
							}
							pf_wav1_env.addSegment(Math.abs(obtain_1[1]*250),(obtain_2[0]-obtain_1[0])*time_duration); //This is quick modifications of the envelope because the value is at 250 it must also remain positive
						}
					time_duration =100;		
					pf_wav1.setup_function(1, 1, 4, 3, .8f, 0);
					
					for (int k=0; k < pf_wav1.env1.size();k++){
						obtain_1=pf_wav1.env1.get(k);
					
						if (k<pf_wav1.env1.size()-1){
						obtain_2=pf_wav1.env1.get(k+1);}else{
							obtain_2[1]=1f;
						}
						pf_wav1_env.addSegment(Math.abs(obtain_1[1]*250),(obtain_2[0]-obtain_1[0])*time_duration);
					}		
					
					
					time_duration =300;		
					pf_wav1.setup_function(1, 1, 8, 12, .8f, 0);
					
					for (int k=0; k < pf_wav1.env1.size();k++){
						obtain_1=pf_wav1.env1.get(k);
					
						if (k<pf_wav1.env1.size()-1){
						obtain_2=pf_wav1.env1.get(k+1);}else{
							obtain_2[1]=1f;
						}
						if(obtain_1[1]<0){obtain_1[1]=1f;}
						pf_wav1_env.addSegment(Math.abs(obtain_1[1]*250),(obtain_2[0]-obtain_1[0])*time_duration);
					}		
					
					
					pf_wav1.setup_function(2, 1, 4, 1, 1, 0);//All of the equations you will get good at.  
					Envelope pf_wav2_env = new Envelope(ac, 0);
					time_duration =50;
					for (int k=0; k < pf_wav1.env1.size();k++){
							obtain_1=pf_wav1.env1.get(k);
						
							if (k<pf_wav1.env1.size()-1){
							obtain_2=pf_wav1.env1.get(k+1);}else{    //This is a filter modification
								obtain_2[1]=1f;
							}
							pf_wav2_env.addSegment(obtain_1[1],(obtain_2[0]-obtain_1[0])*time_duration); //This is quick modifications of the envelope because the value is at 250 it must also remain positive
						}
					time_duration =100;		
					pf_wav1.setup_function(2, 1, 4, 3, .8f, 0);
					
					for (int k=0; k < pf_wav1.env1.size();k++){
						obtain_1=pf_wav1.env1.get(k);
					
						if (k<pf_wav1.env1.size()-1){
						obtain_2=pf_wav1.env1.get(k+1);}else{
							obtain_2[1]=1f;
						}
						pf_wav2_env.addSegment(obtain_1[1],(obtain_2[0]-obtain_1[0])*time_duration);
					}		
					
					
					time_duration =700;		
					pf_wav1.setup_function(2, 1, 8, 12, .8f, 0);
					
					for (int k=0; k < pf_wav1.env1.size();k++){
						obtain_1=pf_wav1.env1.get(k);
					
						if (k<pf_wav1.env1.size()-1){
						obtain_2=pf_wav1.env1.get(k+1);}else{
							obtain_2[1]=1f;
						}
						if(obtain_1[1]<0){obtain_1[1]=1f;}
						pf_wav2_env.addSegment(obtain_1[1],(obtain_2[0]-obtain_1[0])*time_duration);
					}		
					
				
					AudStruct_wave gen_mod_1_wav= new AudStruct_wave();	
					Envelope gen_mod1_env = new Envelope(ac, 0);
					time_duration =150;
					//setup_function2 function number, part, segment, frequency, amplitude, x_shift, y_shhift
					gen_mod_1_wav.setup_function2(1,  5,3.14f*4, (float) .05, 0,-.5f);  //This is a sine wave with 10 segments or th
																			   //With setup function the value cannot get less than 3.14  for the frequency it can 
					//So the envelope itself cannot give you the variability that you would like.  So what 
	               
					//With with 6.28 or pi/ frequency) * segment number will get the length of time so 
					//in this case it 2ill be 2.5 times the time duration.  whoch will equation will yield 125 ms 
					for (int k=0; k < pf_wav1.env1.size();k++){
						
						obtain_1=pf_wav1.env1.get(k);		
							if (k<pf_wav1.env1.size()-1){
							obtain_2=pf_wav1.env1.get(k+1);}else{
								obtain_2[1]=1f;
							}
							gen_mod1_env.addSegment(obtain_1[1],(obtain_2[0]-obtain_1[0])*time_duration); //This is quick modifications of the envelope because the value is at 250 it must also remain positive
						}
	             
	               gen_mod_1_wav.setup_function2(1f, 5f, 3.14f*7f,0.25f, 0f,-0.25f);
	               time_duration =150;
					for (int k=0; k < pf_wav1.env1.size();k++){
					
					obtain_1=pf_wav1.env1.get(k);		
					if (k<pf_wav1.env1.size()-1){
					obtain_2=pf_wav1.env1.get(k+1);}else{
					obtain_2[1]=1f;
					}
					gen_mod1_env.addSegment(obtain_1[1],(obtain_2[0]-obtain_1[0])*time_duration); //This is quick modifications of the envelope because the value is at 250 it must also remain positive
					}

				    gen_mod_1_wav.setup_function2(1, 10,  3.14f*9.0f,.5f, 0.0f,-.1f);
					time_duration =150;  
					for (int k=0; k < pf_wav1.env1.size();k++){
					
					obtain_1=pf_wav1.env1.get(k);		
					if (k<pf_wav1.env1.size()-1){
					obtain_2=pf_wav1.env1.get(k+1);}else{
					obtain_2[1]=1f;
					}
					gen_mod1_env.addSegment(obtain_1[1],(obtain_2[0]-obtain_1[0])*time_duration); //This is quick modifications of the envelope because the value is at 250 it must also remain positive
					}

					
					
					
					
					//This wave cyclically contracts //opposite of the previous.
					//This will be feed again to panner. 
					Envelope gen_mod2_env = new Envelope(ac, 0);
					time_duration =150;
					//setup_function2 function number, part, segment, frequency, amplitude, x_shift, y_shhift
					gen_mod_1_wav.setup_function2(1,  5,3.14f*15, (float) .8, 0,0f);  //This is a sine wave with 10 segments or th
																			   //With setup function the value cannot get less than 3.14  for the frequency it can 
					//So the envelope itself cannot give you the variability that you would like.  So what 
	               for (int k=0; k < pf_wav1.env1.size();k++){
						
						obtain_1=pf_wav1.env1.get(k);		
							if (k<pf_wav1.env1.size()-1){
							obtain_2=pf_wav1.env1.get(k+1);}else{
								obtain_2[1]=1f;
							}
							gen_mod2_env.addSegment(obtain_1[1],(obtain_2[0]-obtain_1[0])*time_duration); //This is quick modifications of the envelope because the value is at 250 it must also remain positive
						}
	             
	               gen_mod_1_wav.setup_function2(1f, 5f, 3.14f*3f,0.45f, 0f,.25f);
	               time_duration =150;
					for (int k=0; k < pf_wav1.env1.size();k++){
					
					obtain_1=pf_wav1.env1.get(k);		
					if (k<pf_wav1.env1.size()-1){
					obtain_2=pf_wav1.env1.get(k+1);}else{
					obtain_2[1]=1f;
					}
					gen_mod2_env.addSegment(obtain_1[1],(obtain_2[0]-obtain_1[0])*time_duration); //This is quick modifications of the envelope because the value is at 250 it must also remain positive
					}

				    gen_mod_1_wav.setup_function2(1, 2,  3.14f*2f,2f, 0.0f,-.1f);
					time_duration =150;  
					for (int k=0; k < pf_wav1.env1.size();k++){
					
					obtain_1=pf_wav1.env1.get(k);		
					if (k<pf_wav1.env1.size()-1){
					obtain_2=pf_wav1.env1.get(k+1);}else{
					obtain_2[1]=1f;
					}
					gen_mod2_env.addSegment(obtain_1[1],(obtain_2[0]-obtain_1[0])*time_duration); //This is quick modifications of the envelope because the value is at 250 it must also remain positive
					}

					//The gen_md1 segment will modulate the panning to condense it first and then  
					
					
					Envelope pf_wav1_env1 = new Envelope(ac, 0);
					Envelope pf_wav1_env2 = new Envelope(ac, 0);
					Envelope pf_wav1_env3 = new Envelope(ac, 0);
			
			//That was the simple way of coding it which will be nice  for  simple audio effects.  Now lets say that you want
			//4 differents effects all at the same time panning, filtering 1, filtering 2, gain and reverb  will all be added in. 
			//The key that I am trying to figure out is what is the pitch an
					
					
					
			
			BiquadFilter peakFilter_wav1 = new BiquadFilter(ac, BiquadFilter.BP_PEAK, wav1_pitch, pf_wav1_env);
			env_wav2_21.addSegment(0,100f);
			Gain gain2_wav = new Gain(ac,1,env_wav2_21);     
			gain2_wav.addInput(wav1);
	   		gain2_wav.calculateBuffer();
	   		gain2_wav.update();
	   		ac.out.addInput(gain2_wav);
	   		ac.out.update();
	   		ac.start();
	   
	   return buffer_wave1;
	   
	   
	   
   } 

   
   
     
  // The value that will be received by the rhythm maker time signature 4/4= 64= 64th beats the fastest that possibly can be played. 
  //1/64  which means  so  if you have a time signature 
   
   int time_sig1=4;
   int time_sig2=4;
   int tot_bits_measure=0;
   void  run_time_signature(){
	  
	  tot_bits_measure= (time_sig1/time_sig2)*64*2;
	   Integer [] midi_array_difference = new Integer [tot_bits_measure];
		HashMap <Integer,Integer > forward_1 = new HashMap < >();
		

	   
   }   

void play_soundpool(int drum){
	//Play soundpool the data. 
	
}
}



