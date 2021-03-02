import java.util.ArrayList;

public class Dex {

	private static ArrayList<Pokemon> nationalDex;
	private static ArrayList<Pokemon> megaDex;
	

	public Dex() {
		nationalDex = new ArrayList<>();
		megaDex = new ArrayList<>();

		PokeData.setPokeData(nationalDex, "NationalDex");
		PokeData.setPokeData(megaDex, "MegaDex");
	}


	public static ArrayList<Pokemon> getNationalDex() {
		return nationalDex;
	}


	public static ArrayList<Pokemon> getMegaDex() {
		return megaDex;
	}

	
	public static Pokemon getPokemonByEntry(int entryNum) {
		return nationalDex.get(entryNum - 1).clone();
	}

	
	public static Pokemon getPokemonByName(String name) {
		
		for(int i = 1; i <= nationalDex.size(); i++) {
			if(name.equalsIgnoreCase(getPokemonByEntry(i).getName())) {
				return nationalDex.get(i - 1);
			}
		}
		
		return null;
	}

	
    public static int getMegaIndex(Pokemon pokemon) {
    	
        for(int i = 0; i < megaDex.size(); i++) {
            if(megaDex.get(i).getEntryNum() == pokemon.getEntryNum()) {
				return i;
			}
        }
        
        return 0;
    }

	
	public static Pokemon getMegaByIndex(int index) {
		return megaDex.get(index);
	}
}
