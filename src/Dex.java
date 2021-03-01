import java.util.ArrayList;

public class Dex extends ArrayList<Pokemon> {
	
	private static final long serialVersionUID = 1L;
	
	
	
	public Dex() {

	}



	public void fillNationalDex() {
		
		PokeData pokeData = new PokeData();
		
		pokeData.setPokeData(this, "NationalDex");
	}
	
	
	
	public void fillMegaDex() {
		
		PokeData pokeData = new PokeData();
		
		pokeData.setPokeData(this, "MegaDex");
	}
	
	
	
	public Pokemon getPokemonByEntry(int entryNum) {
		
		ArrayList<Pokemon> nationalDex = new ArrayList<>();

		for(int i = 0; i < this.size(); i++) {
			nationalDex.add(this.get(i).clone());
		}

		return nationalDex.get(entryNum - 1);
	}
	
	
	
	public Pokemon getPokemonByName(String name) {
		
		for(int i = 1; i <= this.size(); i++) {
			if(name.equalsIgnoreCase(this.getPokemonByEntry(i).getName())) {
				return this.get(i - 1);
			}
		}
		
		return null;
	}
	
	
	
    public int getMegaIndex(Pokemon pokemon) {
    	
        for(int i = 0; i < this.size(); i++) {
            if(this.get(i).getEntryNum() == pokemon.getEntryNum()) {
				return i;
			}
        }
        
        return 0;
    }
	
	
	
	public Pokemon getMegaByIndex(int index) {
		return this.get(index);
	}
}