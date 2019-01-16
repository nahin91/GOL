package gol;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.SwingUtilities;
public class GameOfLife extends javax.swing.JFrame {

    final int wid=200, hei=100;
    boolean[][] currentMove =  new boolean[100][200], nextMove = new boolean[100][200];
    boolean play;
    Image offScreenImg;
    Graphics offScreenGraph;
    
    public GameOfLife() {
        initComponents();
        offScreenImg = createImage(jPanel1.getWidth(), jPanel1.getHeight());
        offScreenGraph = offScreenImg.getGraphics();
        Timer time= new Timer();
        TimerTask task= new TimerTask() {
//sabir-cse@sust.edu
            @Override
            public void run() {
                if(play){
                    for(int i=0; i<hei; i++){
                        for(int j=0; j<wid; j++){
                            nextMove[i][j] = decide(i, j);
                        }
                    }
                    for(int i=0; i<hei; i++){
                        for(int j=0; j<wid; j++){
                            currentMove[i][j] = nextMove[i][j];
                        }
                    }
                    repain();
                }
            }
        };
        time.scheduleAtFixedRate(task, 0, 100);
        repain();
    }
    
    private boolean decide(int i, int j){
        int neighbors = 0;
        //LEFT side check
        if(j>0){                                                //jodi present cell ta akdom left most na hoy tahole er left side neighbou gula check korbo
            if(currentMove[i][j-1]) neighbors++;            //jodi left side a living cell pawa jay tahole neighbor++
            if(i>0) if(currentMove[i-1][j-1]) neighbors++;  //present position jodi beginning position na hoy that is top&left most cornar na hoy tahole neighbor++
            if(i<hei-1) if(currentMove[i+1][j-1]) neighbors++; //jodi bottom left corner na hoy
        }
        
        //RIGHT side check
        if(j < wid-1){
            if(currentMove[i][j+1]) neighbors++;
            if(i>0) if(currentMove[i-1][j+1]) neighbors++;
            if(i<hei-1) if(currentMove[i+1][j+1]) neighbors++;
        }
        
        if(i>0) if(currentMove[i-1][j]) neighbors++;      //checking top neighbor
        if(i<hei-1) if(currentMove[i+1][j]) neighbors++;    //check bottom neighbor
        if(neighbors == 3) return true;               // LIVE CONDITION
        if(currentMove[i][j] && neighbors==2) return true;
        return false;
    }

    private  void repain(){
       offScreenGraph.setColor(jPanel1.getBackground());
       offScreenGraph.fillRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());      
       offScreenGraph.setColor(Color.BLACK);
       
       for(int i=1; i<hei; i++ ){
           int y = i * jPanel1.getHeight() / hei;
           offScreenGraph.drawLine(0, y, jPanel1.getWidth(), y);    // .drawLine{ (0th column,1st row), (column number according to width position, still 1st row) }
       }
       for(int j=1 ; j<wid; j++){
           int x = j * jPanel1.getWidth() / wid;
           offScreenGraph.drawLine(x, 0, x, jPanel1.getHeight());   // .drawLine{ (x th row, 0 th column), (still x row, column number according to height position ) }
       }
       
      //pointing out the cell 
       for(int i=0; i<hei; i++){                        // i for y   that is for column
           for(int j=0; j<wid; j++){                    // j for x   that is for row
               if(currentMove[i][j]){
                offScreenGraph.setColor(Color.YELLOW);
                int y = i * jPanel1.getHeight() / hei;
                int x = j * jPanel1.getWidth() / wid;
                offScreenGraph.fillRect(x, y, jPanel1.getWidth()/wid, jPanel1.getHeight()/hei);  // koto gula aktesi
               }
           }
       }
      //done 
      
       jPanel1.getGraphics().drawImage(offScreenImg, 0, 0, jPanel1);
       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        jButton1.setText("play");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(0, 504, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
       int j = wid * evt.getX() / jPanel1.getWidth() ;    // position of x devided by physical width of the panel  &  multiplied by no of cell horizontally
       int i = hei * evt.getY() / jPanel1.getHeight();    // position of y devided by physical height of the panel  &  multiplied by no of cell vertically
       currentMove[i][j] = !currentMove[i][j];            // toggle korar jonno
       repain();      
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        offScreenImg = createImage(jPanel1.getWidth(), jPanel1.getHeight());
        offScreenGraph = offScreenImg.getGraphics();
        repain();
    }//GEN-LAST:event_jPanel1ComponentResized

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        play = !play;
        if(play) jButton1.setText(("Pause"));
        else jButton1.setText("Play");
        repain();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        currentMove =  new boolean[100][200];
        repain();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
       int j = wid * evt.getX() / jPanel1.getWidth() ;
       int i = hei * evt.getY() / jPanel1.getHeight();
       if(SwingUtilities.isLeftMouseButton(evt)) currentMove[i][j] = true;
       else currentMove[i][j] = false;
       repain();
    }//GEN-LAST:event_jPanel1MouseDragged

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameOfLife.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameOfLife.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameOfLife.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameOfLife.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameOfLife().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
