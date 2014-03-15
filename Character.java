
public class Character{
    int respect;
    int affection;
    int familiarity;
    int trust;

    public Character(int respect, int affection, int familiarity, int trust){
	this.respect = respect;
	this.affection = affection;
	this.familiarity = familiarity;
	this.trust = trust;
    }
    public void addRespect(int a){
	respect += a;
    }
    public void addAffection(int b){
	affection += b;
    }
    public void addFamiliarity(int c){
	familiarity += c;
    }
    public void addTrust(int d){
	trust += d;
    }
    public String displayInfo(){
	return ("R" + respect + "A" + affection + "F" + familiarity + "T" + trust);
    }
	
}
