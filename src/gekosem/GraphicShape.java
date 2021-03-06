package gekosem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Point2D;

import gekosem.painter.InputOutputPainter;
import gekosem.painter.ShapePainter;

@SuppressWarnings("serial")
public abstract class GraphicShape extends GraphicElement {

	
	private Dimension size;
	private Point2D position;
	
	protected double rotation;
	protected boolean fliped;
	
	private int input;
	private int output;
	
	private InputOutputElement in1;
	private InputOutputElement in2;
	private InputOutputElement out;
	
	private boolean in1Ok = true;
	private boolean in2ok = true;
	private boolean outOk = true;
	
	private Dimension initSize;
	private double scale;
	
	public GraphicShape(Point2D pos, Dimension size, Stroke stroke, 
			Paint paint, Color strokeColor, int inputNo, int outputNo){
		super(stroke, paint, strokeColor);
		this.size = size;
		this.position = pos;
		this.input = inputNo;
		this.initSize = size;
		this.output = outputNo;
		this.rotation = 0;
		this.fliped = false;
		this.setStrokeColor(strokeColor);
		
		
		 if(inputNo == 2){
			 Point2D ioPos = new Point2D.Double(position.getX(), position.getY()+(getSize().getHeight()/3));
			 in1 = (InputOutputElement) InputOutputElement.defaultPaint(ioPos, stroke, paint, this, 1, 1);

			 ioPos = new Point2D.Double(position.getX(), position.getY()+(2*getSize().getHeight()/3));
			 in2 = (InputOutputElement) InputOutputElement.defaultPaint(ioPos, stroke, paint, this, 2, 1); 
		 }else{
			 Point2D ioPos = new Point2D.Double(position.getX(), position.getY()+(getSize().getHeight()/2));
			 in1 = (InputOutputElement) InputOutputElement.defaultPaint(ioPos, stroke, paint, this, 1, 1);
		 }
		 
		 Point2D ioPos = new Point2D.Double(position.getX()+size.width, position.getY()+(getSize().getHeight()/2));
		 out = InputOutputElement.defaultPaint(ioPos, stroke, paint, this, 1, 0);
	}
	
	public GraphicShape(GraphicShape element) {
		super(element);
		// TODO Auto-generated constructor stub
		this.size = element.getSize();
		this.position = element.getPosition();
		this.input = element.getInput();
		this.output = element.getOutput();
		this.out = element.getOut();
		this.in1 = element.getIn1();
		this.in2 = element.getIn2();
		this.in1Ok = element.isIn1Ok();
		this.in2ok = element.isIn2ok();
		this.outOk = element.isOutOk();
		this.initSize = element.getSize();
		this.scale = element.getScale();
		
		if(!(this instanceof NotElement)){
			 Point2D ioPos = new Point2D.Double(position.getX(), position.getY()+(getSize().getHeight()/3));
			 in1 = (InputOutputElement) InputOutputElement.defaultPaint(ioPos, stroke, getPaint(), this, 1, 1);

			 ioPos = new Point2D.Double(position.getX(), position.getY()+(2*getSize().getHeight()/3));
			 in2 = (InputOutputElement) InputOutputElement.defaultPaint(ioPos, stroke, getPaint(), this, 2, 1); 
		 }else{
			 Point2D ioPos = new Point2D.Double(position.getX(), position.getY()+(getSize().getHeight()/2));
			 in1 = (InputOutputElement) InputOutputElement.defaultPaint(ioPos, stroke, getPaint(), this, 1, 1);
		 }
		 
		 Point2D ioPos = new Point2D.Double(position.getX()+size.width, position.getY()+(getSize().getHeight()/2));
		 out = InputOutputElement.defaultPaint(ioPos, stroke, getPaint() , this, 1, 0);
	}
	
	@Override
	public GraphicShape clone() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getRotation() {
		return rotation;
	}
	
	public boolean isFliped() {
		return fliped;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
		
		if (Math.abs(rotation) == 2 * Math.PI)
			this.rotation = 0;
		 
		if (fliped) fliped = false;
		else fliped = true;
	}
	
	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
		 if(input == 2){
			 Point2D pos = new Point2D.Double(position.getX(), position.getY()+(getSize().getHeight()/3));
			 this.in1.setPosition(pos);
			 pos = new Point2D.Double(position.getX(), position.getY()+(2*getSize().getHeight()/3));
			 this.in2.setPosition(pos);
		 }else{
			 Point2D pos = new Point2D.Double(position.getX(), position.getY()+(getSize().getHeight()/2));
			 this.in1.setPosition(pos);
		 }
		 
		 Point2D pos = new Point2D.Double(position.getX()+size.width, position.getY()+(getSize().getHeight()/2));
		 this.out.setPosition(pos);

	}

	public int getInput() {
		return input;
	}

	public void setInput(int input) {
		this.input = input;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	public InputOutputElement getIn1() {
		return in1;
	}

	public void setIn1(InputOutputElement in1) {
		this.in1 = in1;
	}

	public InputOutputElement getIn2() {
		return in2;
	}

	public void setIn2(InputOutputElement in2) {
		this.in2 = in2;
	}

	public InputOutputElement getOut() {
		return out;
	}

	public void setOut(InputOutputElement out) {
		this.out = out;
	}

	public boolean isIn1Ok() {
		return in1Ok;
	}

	public void setIn1Ok(boolean in1Ok) {
		this.in1Ok = in1Ok;
	}

	public boolean isIn2ok() {
		return in2ok;
	}

	public void setIn2ok(boolean in2ok) {
		this.in2ok = in2ok;
	}

	public boolean isOutOk() {
		return outOk;
	}

	public void setOutOk(boolean outOk) {
		this.outOk = outOk;
	}

	public Dimension getInitSize() {
		return initSize;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
		setPosition(position);
	}

	public void setInitSize(Dimension initSize) {
		this.initSize = initSize;
	}
	
	public String getDimension(){
		return "H: " + this.size.getHeight() + " W: " + this.size.getWidth();
	}
	
}
