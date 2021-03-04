import java.util.ArrayList;

public class Pokemon {
	
    private String name, typeOne, typeTwo;
    private int entryNum, baseHp, baseAtt, baseDef, baseSpA, baseSpD, baseSpeed;
    private boolean isMega, isGigantamax;
 

    public Pokemon() {
    	
    }
    

    public Pokemon(int entryNum, String name, String typeOne, String typeTwo, int baseHp, int baseAtt, int baseDef, int baseSpA, int baseSpD, int baseSpeed) {

    	this.entryNum = entryNum;
    	this.typeOne = typeOne;
    	this.typeTwo = typeTwo;
    	this.name = name;
    	this.baseHp = baseHp;
    	this. baseAtt = baseAtt;
    	this.baseDef = baseDef;
    	this.baseSpA = baseSpA;
    	this.baseSpD = baseSpD;
    	this.baseSpeed = baseSpeed;
    }
    

    @Override
    public Pokemon clone() {
    	
    	Pokemon pokemon = new Pokemon();
    	
		pokemon.name = this.name;
		pokemon.entryNum = this.entryNum;
		pokemon.typeOne = this.typeOne;
		pokemon.typeTwo = this.typeTwo;
		pokemon.baseHp = this.baseHp;
		pokemon.baseAtt = this.baseAtt;
		pokemon.baseDef = this.baseDef;
		pokemon.baseSpA = this.baseSpA;
		pokemon.baseSpD = this.baseSpD;
		pokemon.baseSpeed = this.baseSpeed;
		
		return pokemon;
    }
    

	public int getEntryNum() {
		return entryNum;
	}
	

	public void setEntryNum(int entryNum) {
		this.entryNum = entryNum;
	}


	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	

	public String getTypeOne() {
		return typeOne;
	}
	

	public void setTypeOne(String typeOne) {
		this.typeOne = typeOne;
	}


	public String getTypeTwo() {
		return typeTwo;
	}
	

	public void setTypeTwo(String typeTwo) {
		this.typeTwo = typeTwo;
	}


	public int getBaseHp() {
		return baseHp;
	}

	
	public void setBaseHp(int baseHp) {
		this.baseHp = baseHp;
	}


	public int getBaseAtt() {
		return baseAtt;
	}
	

	public void setBaseAtt(int baseAtt) {
		this.baseAtt = baseAtt;
	}


	public int getBaseDef() {
		return baseDef;
	}
	

	public void setBaseDef(int baseDef) {
		this.baseDef = baseDef;
	}


	public int getBaseSpA() {
		return baseSpA;
	}
	

	public void setBaseSpA(int baseSpA) {
		this.baseSpA = baseSpA;
	}


	public int getBaseSpD() {
		return baseSpD;
	}
	

	public void setBaseSpD(int baseSpD) {
		this.baseSpD = baseSpD;
	}
	

	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	
	public int getBaseSpeed() {
		return baseSpeed;
	}


	public boolean isMega() {
		return isMega;
	}


	public void setMega(boolean isMega) {
		this.isMega = isMega;
	}


	public boolean isGigantamax() {
		return isGigantamax;
	}


	public void setGigantamax(boolean isGigantamax) {
		this.isGigantamax = isGigantamax;
	}
	

	public int getBaseTotal() {
    	return baseHp + baseAtt + baseDef + baseSpA + baseSpD + baseSpeed;
    }
	

	public boolean hasMega() {

        ArrayList<Pokemon> megaDex = new Dex().getMegaDex();

		for(Pokemon pokemon : megaDex) {
			if(pokemon.entryNum <= entryNum) {
				if(pokemon.entryNum == entryNum) {
					return true;
				}
			}

			else {
				break;
			}
		}
		
		return false;
	}


    public boolean hasSecondMega() {

        final int DEX_SIZE = Dex.megaDexSize();

        for(int i = 0; i < DEX_SIZE; i++) {
        	if(Dex.getMegaIndex(this) != DEX_SIZE - 1) {
				if(entryNum == Dex.getMegaByIndex(Dex.getMegaIndex(this) + 1).entryNum) {
					return true;
				}

        		else {
					break;
				}
        	}
        }

        return false;
    }
    

    public boolean hasGigantamax() {
    	
    	int[] gigantamaxEntryNums = { 3, 6, 9, 12, 25, 52, 68, 94, 99, 131, 133, 143, 569, 809, 812, 815, 818, 
    			823, 826, 834, 839, 841, 844, 849, 851, 858, 861, 869, 879, 884, 892 };
    	
    	for(int i = 0; i < gigantamaxEntryNums.length; i++) {
    		if(gigantamaxEntryNums[i] <= entryNum) {
	    		if(entryNum == gigantamaxEntryNums[i]) {
					return true;
				}
    		}

    		else {
				break;
			}
    	}
    	
    	return false;
    }
}