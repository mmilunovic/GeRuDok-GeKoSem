package gekosem.actions;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import gekosem.Gekosem;
import gekosem.GraphicElement;
import gekosem.GraphicShape;
import gekosem.LinkElement;
import gekosem.painter.ElementPainter;
import gekosem.painter.LinkPainter;
import gekosem.select.SlotELemenetsSelection;
import gui.model.Element;
import gui.model.Slot;
import gui.observer.NotificationObserver;

public class PPasteAction extends AbstractAction implements MouseListener{
	
	Gekosem g;
	Slot slot;
	private ArrayList<Element> elementi = new ArrayList<>();
	private ArrayList<GraphicElement> klonovi = new ArrayList<>();
	
	public PPasteAction(Gekosem g, Slot slot) {
		this.g = g;
		this.slot = slot;
		this.klonovi = new ArrayList<>();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(elementi.size());
		this.elementi = g.getGekosemClipboard().getElements();
		for(int i = 0 ; i < elementi.size() ; i++){
			klonovi.add(((GraphicElement)elementi.get(i)).clone());
			//klonovi.set(i, ((GraphicElement)elementi.get(i)).clone());
			//GraphicElement element = ((GraphicElement)elementi.get(i));
			//slot.addChild(element.clone());
		}
		for(int i = 0 ; i < elementi.size() ; i++){
			if(elementi.get(i) instanceof LinkElement){
				for(int j = 0 ; j < elementi.size() ; j++){
					if(elementi.get(j) instanceof GraphicShape){
						if(((LinkElement)elementi.get(i)).getStart() == (GraphicShape)elementi.get(j)){
							((LinkElement)klonovi.get(i)).setStart((GraphicShape)klonovi.get(j));
							
						}else if(((LinkElement)elementi.get(i)).getEnd() == (GraphicShape)elementi.get(j)){
							((LinkElement)klonovi.get(i)).setEnd((GraphicShape)klonovi.get(j));
						}
					}
					((LinkElement)klonovi.get(i)).setOut(((LinkElement)klonovi.get(i)).getStart().getOut());
					((LinkElement)klonovi.get(i)).setIn(((LinkElement)klonovi.get(i)).getEnd().getIn1());
				}
			//((LinkElement)klonovi.get(i)).setEnd(((LinkElement)elementi.get(i)).getEnd());
			//	((LinkElement)klonovi.get(i)).setStart(((LinkElement)elementi.get(i)).getStart());
				((LinkElement)klonovi.get(i)).setPainter(new LinkPainter((LinkElement)klonovi.get(i)));
				
			}
		}
	/**	Object requestor = new Object();
		Transferable clipboardContent =  g.getClipboard().getContents(requestor);
		System.out.println(clipboardContent);
		//ArrayList<Element> elements = g.getClipboard().getContents(requestor);
		if(clipboardContent != null){
			try {
				ArrayList<Element> tempElements = (ArrayList<Element>)clipboardContent.getTransferData(SlotELemenetsSelection.elementFlavor);
				System.out.println(tempElements.size());
				for(int i = 0 ; i < tempElements.size() ; i++){
					//if()
				}
				
				for(int i = 0 ; i < tempElements.size() ; i++){
					GraphicElement element = ((GraphicElement)tempElements.get(i)).clone();
					if(element instanceof LinkElement){
					//	for(int i = 0 ; i < tempElements.size() ; i++){
							
					//	}
					}else{
						slot.addChild(element);
						slot.notifyObservers(NotificationObserver.ADD, element);
					}
				}
			} catch (UnsupportedFlavorException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}**/
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0 ; i < elementi.size() ; i++){
			((GraphicShape)klonovi.get(i)).setPosition(e.getPoint());
			slot.addChild(klonovi.get(i));
		}		
	}	

}
