package ie.tudublin;

import java.util.ArrayList;

import com.jogamp.opengl.Threading.Mode;

import ddf.minim.ugens.Line;
import processing.core.PApplet;

public class DANI extends PApplet {


	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

    String[] sonnet;

	ArrayList<Word> Model;

    public String[] writeSonnet()
    {
        return null;
    }

	public void setup() {
		colorMode(HSB);

		Model = new ArrayList<Word>();
		loadFile();
	}

	public void keyPressed() {

	}

	float off = 0;

	public void draw() 
    {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
        textAlign(CENTER, CENTER);
	}

	public void loadFile(){
		String[] Poem;
		String[] Lines;

		Word TargetWord;
		Follow newFollow;

		Poem = loadStrings("small.txt"); // Load a text file into a String array


		for(int i = 0; i < Poem.length; i++){
			String s = Poem[i];
			s.replaceAll("[^\\w\\s]",""); 
			s = s.toLowerCase(); // Convert a string to lower case 
			Lines = split(s, ' ');

			for(int j = 0; j < Lines.length; j++){
				String w = Lines[j];
				String TempLine[];
				TargetWord = findWord(w);

				print(w);

				if(TargetWord == null){
					TargetWord = new Word(w);
					Model.add(TargetWord);
					println(" NotFound ");

				}
				else{
					println(" Found ");
				}

				newFollow = TargetWord.findWord(w);

				if(j + 1 >= Lines.length){
					continue;
				}

				if(newFollow == null){
					newFollow = new Follow(Lines[j+1]);
					TargetWord.getFollows().add(newFollow);
					continue;
				}

				newFollow.IncrementCount();

			}
		}


		printModel();
		//println(Lines);
	}

	public void printModel(){
		println("\nSize: " + Model.size());

		for(Word m: Model){
			print(m.getWord() + ":");
			for(Follow f: m.getFollows()){

				System.out.print(f.getWord() + "(" + f.getCount() + ") ");
			}
			println();
		}
    }

	public Word findWord(String Search){
		for(Word w: Model){

			if(w.getWord().equals(Search)){
				return w;
			}
		}

		return null;
	}
}
