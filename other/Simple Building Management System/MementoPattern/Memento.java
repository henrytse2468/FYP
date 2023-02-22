package MementoPattern;

public class Memento {
    private Object oldRecord;
    Originator orig;    
	
	public Memento(Originator o) {
        orig = o;
		this.oldRecord = orig.oldRecord; // save originator’s state
	}
	
	public Object restore(){
		orig.oldRecord = this.oldRecord;
        return this.oldRecord;
	 }
    
}
