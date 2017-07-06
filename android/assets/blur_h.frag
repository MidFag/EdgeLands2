//#ifdef позвол€ет коду работать на слабых телефонах, и мощных пк.≈сли шейдер используетс€ на телефоне(GL_ES) то  
//используетс€ низка€ разр€дность (точность) данных.(highp Ц высока€ точность; mediump Ц средн€€ точность; lowp Ц низка€ точность)
#ifdef GL_ES   
    #define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
// sampler2D это специальный формат данных в  glsl дл€ доступа к текстуре
uniform sampler2D u_texture;
uniform sampler2D u_texture2;

uniform LOWP float x;
uniform LOWP float y;
uniform LOWP float dst;

uniform LOWP float uTime;
uniform LOWP float zoom;
void main(){

	gl_FragColor.rgb=

	texture2D(u_texture, v_texCoords+vec2(0.040,0))*0.0588+//0
	texture2D(u_texture, v_texCoords+vec2(0.030,0))*0.088+//1
	texture2D(u_texture, v_texCoords+vec2(0.020,0))*0.117+//2
	texture2D(u_texture, v_texCoords+vec2(0.010,0))*0.147+//3
	texture2D(u_texture, v_texCoords+vec2(0.000,0))*0.176+//4
	texture2D(u_texture, v_texCoords+vec2(0.010,0))*0.147+//3
	texture2D(u_texture, v_texCoords+vec2(0.020,0))*0.117+//2
	texture2D(u_texture, v_texCoords+vec2(0.030,0))*0.088+//1
	texture2D(u_texture, v_texCoords+vec2(0.040,0))*0.0588;//0
	gl_FragColor.a=texture2D(u_texture, v_texCoords).a;
	gl_FragColor*=v_color;

}