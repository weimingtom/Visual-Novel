public class Girl {
	private int respect;
	private int affection;
	private int familiarity;
	private int trust;
	
	public Girl(int respect, int affection, int familiarity, int trust){
		this.respect = respect;
		this.affection = affection;
		this.familiarity = familiarity;
		this.trust = trust;
	}
	
	public void addRespect(int respect) {this.respect += respect;}
	public void addAffection(int affection) {this.affection += affection;}
	public void addFamiliarity(int familiarity) {this.familiarity += familiarity;}
	public void addTrust(int trust) {this.trust += trust;}
	
	public int getRespect() {return respect;}
	public int getAffection() {return affection;}
	public int getFamiliarity() {return familiarity;}
	public int getTrust() {return trust;}
	
	public void setRespect(int respect) {this.respect = respect;}
	public void setAffection(int affection) {this.affection = affection;}
	public void setFamiliarity(int familiarity) {this.familiarity = familiarity;}
	public void setTrust(int trust) {this.trust = trust;}
	
	public String toString() {return "R:" + respect + " A:" + affection + " F:" + familiarity + " T:" + trust;}
}
