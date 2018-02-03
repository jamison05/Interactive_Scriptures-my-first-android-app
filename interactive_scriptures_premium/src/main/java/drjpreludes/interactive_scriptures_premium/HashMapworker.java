package drjpreludes.interactive_scriptures_premium;

import net.beadsproject.beads.data.Buffer;

import java.util.HashMap;



public class HashMapworker {
	
	public HashMapworker(int x){
		x=0;
		
	}
	 
	 public Buffer select_buf(int buf1){
	Buffer return_buf = new Buffer(512); //The integer initiates the size of the buffer;
   switch (buf1){
       case 1:
           return_buf= Buffer.SINE;
       break;
       case 2:
           return_buf= Buffer.SQUARE;
       break;
       case 3:
           return_buf= Buffer.TRIANGLE;
       break;
       case 4:
           return_buf= Buffer.SAW;
       break;
       case 5:
           return_buf= Buffer.NOISE;
       break;
   		}

   	return return_buf;}
	
	 float [][] env1_mod = new float [2][5]; 	 
	 Float[] normal_env = new Float[]{0f, .9f,.5f,.001f,0f};	 
	 Float[]  slow_env = new  Float[] {0f, .001f,.5f,.9f,0f};	
	 Float[] heavy_delay = new Float[]{0f, .9f,.5f,.001f,0f};	 
	 Float[] double_hit= new  Float[] {0f, .001f,.5f,.9f,.1f,.4f,.6f,.1f,0f};
	 Float[][]  type_envelope = new Float[5][];
	 HashMap < Integer,Float[] > envelope_m1 = new HashMap < >();
	 HashMap < Integer,Float[] > envelope_m2 = new HashMap < >();
	 public void intiate_worker(){
		 
		 envelope_m1.put(1, normal_env);
		 envelope_m1.put(2, slow_env);
		 envelope_m1.put(3, heavy_delay);
		 envelope_m1.put(4,double_hit);
		
		 type_envelope[0]= new Float[]{0f,.005f,.7f,.5f, .15f};
		 type_envelope[1]= new Float[]{0f,0.001f,.1f,.5f, .9f};
		 type_envelope[2]= new Float[]{0f,.005f,.9f,.5f, 2f};
		 type_envelope[3]= new Float[]{0f,0.001f,.1f,.5f, .9f,0.2f,.5f,.1f,.5f,2f};
		 
		 
		 // {{0.05f,.05f},{.7f,.3f},{.1f,.5f},{.5f,.15f}};
		 
	 }
	 
	 public float [][] envelope_design(int base_sound, int type_env, int time_duration){
	 //So create functions to modify the envelope.	 
		 
	 env1_mod[0][0]=envelope_m1.get(base_sound)[0];
	 env1_mod[0][1]=envelope_m1.get(base_sound)[1];
	 env1_mod[0][2]=envelope_m1.get(base_sound)[2];
	 env1_mod[0][3]=envelope_m1.get(base_sound)[3];
	 env1_mod[0][4]=envelope_m1.get(base_sound)[4];
	 
	 env1_mod[1][0]=type_envelope[type_env][0]*time_duration;
	 env1_mod[1][1]=type_envelope[type_env][1]*   time_duration;
	 env1_mod[1][2]=type_envelope[type_env][2]*   time_duration;
	 env1_mod[1][3]=type_envelope[type_env][3]* time_duration;
	 env1_mod[1][4]=type_envelope[type_env][4]* time_duration;
	 

	 
	 //1 will be mod_attack 
	 

	 return env1_mod;
	 }
	 
	 
	 public void  modify_envelope(int set_value,float add_val){
		 
		 switch (set_value)
		 {
		 case 1:
			 env1_mod[1][1] = env1_mod[1][1]+add_val;
			 break;
		 case 2:
			 env1_mod[1][2]= (env1_mod[1][2]+add_val);
			 break;
		 case 3:	 
			 env1_mod[1][3]= (env1_mod[1][3]+add_val);
			 break;
		 case 4:
			 env1_mod[1][4]= (env1_mod[1][4]+add_val); 		 }
		 
	 }

	

}
