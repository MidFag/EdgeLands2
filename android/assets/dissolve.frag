//#ifdef позвол€ет коду работать на слабых телефонах, и мощных пк.≈сли шейдер используетс€ на телефоне(GL_ES) то  
//используетс€ низка€ разр€дность (точность) данных.(highp Ц высока€ точность; mediump Ц средн€€ точность; lowp Ц низка€ точность)
#ifdef GL_ES   
    #define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif

varying LOWP vec4 v_color;
varying LOWP float gray;
varying LOWP float post_gray;

varying vec2 v_texCoords;
// sampler2D это специальный формат данных в  glsl дл€ доступа к текстуре
uniform sampler2D u_texture;
uniform sampler2D u_texture2;


uniform LOWP float value;
uniform LOWP float diff;
uniform LOWP float diff2;
void main(){



	
	
	
		
		value*=1.2;
		diff=value+0.05-(texture2D(u_texture, v_texCoords).r);
		diff*=64;
		diff=clamp(diff,0,1);

		
		diff2=value+0.05-(texture2D(u_texture, v_texCoords).r);
		diff2*=16;
		diff2=clamp(diff2,0.025,1);
		
		
		gl_FragColor=texture2D(u_texture2, v_texCoords)*v_color;
		gl_FragColor.rgb*=diff2;
		gl_FragColor.a*=diff*v_color+value;
		
		//gl_FragColor.a=texture2D(u_texture, v_texCoords).r;

	

	
/*
	value=(1-value)/2;
	gl_FragColor.r+=(1-gray)*value;
	gl_FragColor.g+=(gray)*value;
	gl_FragColor.b+=(gray)*value;*/
	
	
	

}