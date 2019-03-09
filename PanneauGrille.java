import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// 
// Decompiled by Procyon v0.5.30
// 

class PanneauGrille extends JPanel {
	
	private int x;
    private int y;
      
    private int res;
    private BufferedImage worldImage;
    public int[][] monde;
    
    public PanneauGrille(final int[][] monde) {
		
        this.monde = monde;
        this.res = Affichage.calcRes(this.monde);
        this.worldImage = new BufferedImage(this.res * this.monde[0].length, this.res * this.monde.length, 1);
        this.setPreferredSize(new Dimension(this.res * this.monde[0].length, this.res * this.monde.length));
       
        this.addMouseListener(new MouseAdapter(){
		@Override
		public void mouseClicked(MouseEvent e){
			int x = e.getX();
			int y = e.getY();
			System.out.print(x);
		}
		});
	
    }
    
    /*
    public int getX()
            {
                return this.x;
            }
             
            public int getY()
            {
                return this.y;
            }
             
            public void mouseClicked(MouseEvent event) {
                    x = event.getX();
                    y = event.getY();
                    System.out.println(x + " " + y);
                }
             
            //Méthode appelée lors du survol de la souris
            public void mouseEntered(MouseEvent event) { }
             
            //Méthode appelée lorsque la souris sort de la zone du bouton
            public void mouseExited(MouseEvent event) { }
             
            //Méthode appelée lorsque l'on presse le bouton gauche de la souris
            public void mousePressed(MouseEvent event) {
                
                }
             
            //Méthode appelée lorsque l'on relâche le clic de souris
            public void mouseReleased(MouseEvent event) { } */     
    
    
    
    private void dessineMonde(final Graphics graphics) {
        final int length = this.monde.length;
        final int length2 = this.monde[0].length;
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, this.res * length2, this.res * length);
        graphics.setColor(Color.BLACK);
  
		for (int i = 0; i<4; i++){
		for (int j = 0; j <4; j++){	
	
    graphics.drawRect(10+200*j, 10+200*i, 100, 100);
    graphics.fillRect(110+200*j, 10+200*i, 100, 100);
    graphics.drawRect(110+200*j, 110+200*i, 100, 100);
    graphics.fillRect(10+200*j, 110+200*i, 100, 100);
    
	}
	}
	
		Font font = new Font("Courier", Font.BOLD, 50);

		graphics.setFont(font);

		graphics.setColor(Color.red);  
		
        for (int i = 0; i <monde.length; i++){	
		
			for(int j = 0; j<monde[0].length; j++){
				
				switch((monde[i][j]-(monde[i][j])%10)/10){
						
						case 1: graphics.setColor(Color.red);
						break;
						case 2: graphics.setColor(Color.green);
						break;
						default:;
					}
				
				switch((monde[i][j])%10){
					
					
					case 1: //graphics.drawString("T", 100*j+50, 100*i+75);
							if((monde[i][j]-(monde[i][j])%10)/10 == 1){
								try {

										  Image img = ImageIO.read(new File("tourb.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
								}
							if((monde[i][j]-(monde[i][j])%10)/10 == 2){
								try {

										  Image img = ImageIO.read(new File("tourn.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
									}
					break;
					case 2: if((monde[i][j]-(monde[i][j])%10)/10 == 1){
								try {

										  Image img = ImageIO.read(new File("cavb.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
								}
							if((monde[i][j]-(monde[i][j])%10)/10 == 2){
								try {

										  Image img = ImageIO.read(new File("cavn.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
									}
					break;
					case 3: if((monde[i][j]-(monde[i][j])%10)/10 == 1){
								try {

										  Image img = ImageIO.read(new File("foub.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
								}
							if((monde[i][j]-(monde[i][j])%10)/10 == 2){
								try {

										  Image img = ImageIO.read(new File("foun.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
									}
					break;
					case 4: if((monde[i][j]-(monde[i][j])%10)/10 == 1){
								try {

										  Image img = ImageIO.read(new File("reineb.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
								}
							if((monde[i][j]-(monde[i][j])%10)/10 == 2){
								try {

										  Image img = ImageIO.read(new File("reinen.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
									}
					break;
					case 5: if((monde[i][j]-(monde[i][j])%10)/10 == 1){
								try {

										  Image img = ImageIO.read(new File("roib.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
								}
							if((monde[i][j]-(monde[i][j])%10)/10 == 2){
								try {

										  Image img = ImageIO.read(new File("roin.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
									}
					break;
					case 6: if((monde[i][j]-(monde[i][j])%10)/10 == 1){
								try {

										  Image img = ImageIO.read(new File("pionb.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
								}
							if((monde[i][j]-(monde[i][j])%10)/10 == 2){
								try {

										  Image img = ImageIO.read(new File("pionn.png"));
										  graphics.drawImage(img, 100*j+10, 100*i+15, this);
										} catch (IOException e) {

										  e.printStackTrace();

										}
									}
					
					break;
					default:;
				}
			}
		}
        
        
    }
    
    public void paint(final Graphics graphics) {
        if (this.monde == null) {
            System.err.println("ERREUR : LE MONDE \u00c0 AFFICHER EST NULL");
        }
        this.dessineMonde(this.worldImage.getGraphics());
        graphics.drawImage(this.worldImage, 0, 0, null);
    }
    
    
}
