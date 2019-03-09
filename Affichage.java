import java.awt.Rectangle;
import java.awt.GraphicsEnvironment;
import java.awt.Component;
import java.awt.Container;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.30
// 

public class Affichage extends JFrame
{
    private static Affichage world;
    private PanneauGrille pg;
    
    private Affichage(final int[][] array) {
        super("Chess");
        this.setContentPane((Container)(this.pg = new PanneauGrille(array)));
        this.setSize(1000,1000);
        this.pack();
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        addMouseListener(new Evenement());
    }
    
    public static void afficherPlat(final int[][] monde) {
        if (Affichage.world == null) {
            Affichage.world = new Affichage(monde);
        }
        Affichage.world.pg.monde = monde;
        Affichage.world.repaint();
    }
    
    public static int calcRes(final int[][] array) {
        final Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return Math.max(1, Math.min((int)(maximumWindowBounds.height * 0.8) / array.length, (int)(maximumWindowBounds.width * 0.8) / array[0].length));
    }
    
    static {
        Affichage.world = null;
    }
}
