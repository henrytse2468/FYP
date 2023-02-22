package MementoPattern;

public class Originator {
    Object oldRecord = null;  // package protected attribute
	
	public Originator(Object oldRecord){
		this.oldRecord = oldRecord;
	}
}

