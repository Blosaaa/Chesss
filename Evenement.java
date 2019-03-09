import java.awt.event.MouseListener;
import java.awt.event.MouseEvent; 

public class Evenement implements MouseListener { 
	
	@Override public void mouseClicked(MouseEvent e) { 
		if (e.getButton()==MouseEvent.BUTTON3) System.out.printf("(x=%d,y=%d)",e.getX(),e.getY()); } 
	@Override public void mousePressed(MouseEvent e) {} 
	@Override public void mouseReleased(MouseEvent e) {} 
	@Override public void mouseEntered(MouseEvent e) {} 
	@Override public void mouseExited(MouseEvent e){}
	private void mousePress(MouseEvent e){
		
		int li = e.getX();
		
	}
		
	}
