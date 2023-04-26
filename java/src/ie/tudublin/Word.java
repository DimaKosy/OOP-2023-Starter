package ie.tudublin;

import java.util.ArrayList;

public class Word {
    private String Word;
    private ArrayList<Follow> follows;

    public Word(String Word){
        this.Word = Word;

        follows = new ArrayList<Follow>();
    }

    public Follow findWord(String Search){
		for(Follow f: follows){
            System.out.print(f.getWord() +":"+Search);
			if(f.getWord().equals(Search)){
                //System.out.print(" :: " + f +" ");
				return f;//returns the word object if found
			}
		}

		return null;
	}

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public ArrayList<Follow> getFollows() {
        return follows;
    }   
    
    public String toString(){
        String Temp;

        Temp = Word;
        for(Follow f:follows){
            Temp += "["+f.getWord()+"("+f.getCount()+")"+"]";
        }
        return Temp;
    }
}
