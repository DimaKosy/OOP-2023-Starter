package ie.tudublin;

import java.util.ArrayList;

import com.jogamp.opengl.Threading.Mode;

import processing.core.PApplet;

public class DANI extends PApplet {


	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

    String[] sonnet;
	int SonnetSize = 8;

	ArrayList<Word> Model;

    public String[] writeSonnet()
    {	
		Word Temp;
		for(int i = 0; i < sonnet.length; i++){
			sonnet[i] = new String();
			String ToConcat;
			int index = floor(random(Model.size())) % Model.size();
			//println(index + ":" + Model.get(index).getWord());
			ToConcat = Model.get(index).getWord() + " ";
			sonnet[i] += ToConcat;

			Temp = Model.get(index);
			
			for(int s_Lenght = 1; s_Lenght < 8; s_Lenght++){


				//print("::"+Temp.getWord());
				if(Temp.getFollows().size() == 0){
					continue;
				}
				Temp = findWord(Temp.getFollows().get(floor(random(Temp.getFollows().size())) % Temp.getFollows().size()).getWord());
				ToConcat = Temp.getWord() + " ";
				sonnet[i] += ToConcat;
			}
		}
        return sonnet;
    }

	public void setup() {
		colorMode(HSB);

		Model = new ArrayList<Word>();
		sonnet = new String[SonnetSize];

		loadFile();
		printModel();

		writeSonnet();
		
		for(int i = 0; i < sonnet.length; i++){
			println(sonnet[i].length() +" " +sonnet[i]);
		}
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

		for(int i = 0; i < sonnet.length; i++){
			text(sonnet[i], width/2, height/4+(i+1) *20);
		}
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
				TargetWord = findWord(w);

				print(w);

				if(TargetWord == null){
					TargetWord = new Word(w);
					Model.add(TargetWord);
					//print(" NotFound ");

				}
				else{
					//print(" Found ");
				}

				if(j + 1 >= Lines.length){
					continue;
				}

				newFollow = TargetWord.findWord(Lines[j+1]);

				//println();
				
				if(newFollow == null){
					newFollow = new Follow(Lines[j+1]);
					TargetWord.getFollows().add(newFollow);
					continue;
				}

				newFollow.IncrementCount();


			}
		}
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
