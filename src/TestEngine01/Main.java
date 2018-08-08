package TestEngine01;

import org.lwjgl.opengl.Display;

import Models.RawModel;
import Models.TexturedModel;
import RenderEngine01.DisplayManager;
import RenderEngine01.Loader;
import RenderEngine01.Renderer;
import Shaders.StaticShader;
import Textures.ModelTexture;

public class Main {
	
	public static void main (String args []) {
		
		DisplayManager.createDisplay("testWindow 01");
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
				-0.5f, 0.5f, 0f,//v0
				-0.5f, -0.5f, 0f,//v1
				0.5f, -0.5f, 0f,//v2
				0.5f, 0.5f, 0f,//v3
		};
		
		int[] indices = {
				0,1,3,//top left triangle (v0, v1, v3)
				3,1,2//bottom right triangle (v3, v1, v2)
		};
		
		float[] textureCoords = {	
			0,0,
			0,1,
			1,1,
			1,0
		};
		
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("NickCage01"));
		TexturedModel texturedModel = new TexturedModel (model, texture);
		
		
		while(!Display.isCloseRequested()) {
			
			// game logic
			
			// render all
			shader.start();
			renderer.prepare();
			renderer.render(texturedModel);
			shader.stop();
			DisplayManager.updateDisplay();
			
			
		}
		
		loader.cleanUp();
		shader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
