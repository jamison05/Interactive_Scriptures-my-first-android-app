package drjpreludes.interactive_scriptures_premium;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;
public class AudStruct_wave {
	Object function_hold = new Object();
	public ArrayList<Float[]> env1 = new ArrayList<Float[]>();
	int parts_per_seg;
	int num_of_seg;
	Float[] xy_values= new Float[2];
	HashMap < Integer,Object > equations_set1 = new HashMap < >();
	public AudStruct_wave(){
			

	}

	void initiate_hashMap(){
		
		function_hold=funct_1(1, 0, 1); 
		equations_set1.put(1,function_hold);
		
	}
	
	void setup_function( int function_number,int segments, int parts, float func_freq, float func_amplitude,float shift){
	double calc1=0;
		double calc2=0;
		this.parts_per_seg=parts;
		  this.num_of_seg= segments;

		  
		  
		  
switch (function_number){		  

case 1:
for (int j=0; j<segments;j++){
for (int i=0;i<parts;i++){
			calc1=(double)i/parts; //Always convert everything to the same type prior to division.  
			xy_values=(funct_1((double)i/parts,func_freq,func_amplitude));
			  this.env1.add(xy_values);
			  }}

	break;
case 3:
	for (int j=0; j<segments;j++){
		for (int i=0;i<parts;i++){
					  xy_values=(funct_3((double)i/parts,func_freq,func_amplitude));
					  this.env1.add(xy_values);}
		}
	break;
	
case 4:
	for (int j=0; j<segments;j++){
		for (int i=0;i<parts;i++){
					  xy_values=(funct_4((double)i/parts,func_freq,func_amplitude,shift));
					  this.env1.add(xy_values);}
		}
	break;	



//You maximize obtaining the maxmininmum/then parts become ignored. 
//the only thing that you need is the frequency and segments and amplitude. 
//sine function 

}

	}

public float total_size =0;
void setup_function2( float function_number,float segments, float func_freq, float func_amplitude,float x_shift, float y_shift){
double calc1=0;
	double calc2=0;

	  this.num_of_seg= (int)segments;

	
	 float calculated_parts=0;
	 float calc_func_freq=0;
	  //This will be based on a Sine equation
	  //so parts = we are going to find the the  
	 calc_func_freq=(1/func_freq)*(float)(2*Math.PI);  //This will will equal to one maxima and one minima of the sine wave
	 										  //So if you have 5 segments of the function
	 this.total_size = calc_func_freq*segments;

	 
	 //So if calculated parts cannot be less than the maxima and minima average of the function. 
	//for x maximum will be x=calc_func_freq*.25 + next(x)  and x = calc_func_freq*.75+next(x);
	 //func next x()  {min 2* total_size x=}
float next_x=0;	
float maxmin_value=0;
int max_orminx=1;
switch ((int)function_number){		  

case 1:

for (int j=0; j<segments;j++){

		
	if (max_orminx==1){
		maxmin_value=.25f+x_shift;
		max_orminx=2;	
	}else if(max_orminx==2){
		maxmin_value=(.75f-.25f)+x_shift;
		max_orminx=1;	
	}
	
	
	calc1=calc_func_freq*maxmin_value + calc1; //Always convert everything to the same type prior to division.  
		
		
		xy_values=(funct_1(calc1,func_freq,func_amplitude));
		  this.env1.add(xy_values);
		  

//You maximize obtaining the maxmininmum/then parts become ignored. 
//the only thing that you need is the frequency and segments and amplitude. 
//sine function 

}
break;


case 2:
	for (int j=0; j<segments;j++){
		
	if (max_orminx==1){
		maxmin_value=.25f+x_shift;
		max_orminx=2;	
	}else if(max_orminx==2){
		maxmin_value=(.75f-.25f)+x_shift;
		max_orminx=1;	
	}
	calc1=calc_func_freq*maxmin_value + calc1; //Always convert everything to the same type prior to division.  
	
					  xy_values=(funct_2(calc1,func_freq,func_amplitude));
					  this.env1.add(xy_values);
					  
		}
	break;
	

case 5:
	for (int j=0; j<segments;j++){

		
		if (max_orminx==1){
			maxmin_value=.25f+x_shift;
			max_orminx=2;	
		}else if(max_orminx==2){
			maxmin_value=(.75f-.25f)+x_shift;
			max_orminx=1;	
		}
		
		
		calc1=calc_func_freq*maxmin_value + calc1; //Always convert everything to the same type prior to division.  
			
			
			xy_values=(funct_5(calc1,func_freq,func_amplitude,x_shift, y_shift));
			  this.env1.add(xy_values);
			  

	//You maximize obtaining the maxmininmum/then parts become ignored. 
	//the only thing that you need is the frequency and segments and amplitude. 
	//sine function 

	}
	break;
	

case 6:
	for (int j=0; j<segments;j++){
		
	if (max_orminx==1){
		maxmin_value=.25f+x_shift;
		max_orminx=2;	
	}else if(max_orminx==2){
		maxmin_value=(.75f-.25f)+x_shift;
		max_orminx=1;	
	}
	calc1=calc_func_freq*maxmin_value + calc1; //Always convert everything to the same type prior to division.  
	
					  xy_values=(funct_6(calc1,func_freq,func_amplitude,x_shift, y_shift));
					  this.env1.add(xy_values);
					  
		}
	break;
	

}
		
	}
		Float[] funct_1(double x_value, float func_freq, float amplitude){
			Float[] xy_value1 = new Float[2];
			xy_value1[0]=(float) x_value;
			xy_value1[1]= (float) (amplitude*Math.sin(func_freq*x_value)); 
			return xy_value1;
		} 
		
		Float[] funct_2(double x_value, float func_freq, float amplitude){
			Float[] xy_value1 = new Float[2];
			xy_value1[0]=(float) x_value;
			xy_value1[1]= (float) (amplitude*Math.cos(func_freq*x_value)); 
			return xy_value1;
		} 
		
		Float[] funct_3(double x_value, float func_freq, float amplitude){
			Float[] xy_value1 = new Float[2];
		if (x_value <.5){
			
			
			xy_value1[0]=(float) x_value;
			xy_value1[1]= (float) ((4*x_value)/(amplitude)); //amplitude must be between 1 and .1  1 (lower the number the lower the slope
		}
			
			if (x_value >.5){
			xy_value1[0]=(float) x_value;
			xy_value1[1]= (float) ((-4*x_value)/(amplitude))+2;} //amplitude must be between 1 and .1
			return xy_value1;
		} 
		
		Float[] funct_4(double x_value, float func_freq, float amplitude, float shift_1){
			Float[] xy_value1 = new Float[2];
			xy_value1[0]=(float) x_value;
			xy_value1[1]= (float) (amplitude*Math.sin(func_freq*Math.pow(x_value, (2+(x_value*shift_1))))); //shift_1 should run in between -3 and 10 
			return xy_value1;
			//You maximize maximuma and minimum, so baxically parts pecome ignored. 
		}
	
		Float[] funct_5(double x_value, float func_freq, float amplitude,float x_shift, float y_shift){
			Float[] xy_value1 = new Float[2];
			xy_value1[0]=(float) x_value;
			xy_value1[1]= (float) (y_shift+(amplitude*Math.sin(func_freq*x_value+x_shift))); 
			return xy_value1;
		} 
		Float[] funct_6(double x_value, float func_freq, float amplitude,float x_shift, float y_shift){
			Float[] xy_value1 = new Float[2];
			xy_value1[0]=(float) x_value;
			xy_value1[1]= (float) (y_shift+(amplitude*Math.cos(func_freq*x_value+x_shift))); 
			return xy_value1;
		} 
		
		//Here is where you add in both x_shiift and y_shift to have more control over the 

}
