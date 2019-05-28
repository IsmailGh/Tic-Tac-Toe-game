import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class TicTacToe extends javax.swing.JFrame {
    private javax.swing.JButton btnExit;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel mainPanel;

    int[][] status;
    JButton[][] buttons;
    int select_count = 0;
    Random rand = new Random();
    String name = "Ismail Ghelle";
    public TicTacToe(Boolean isFirst) {
        grid();
        this.setLocationRelativeTo(null);
        this.setTitle(name);
        
        status = new int[3][];
        buttons = new JButton[3][];
        select_count = 0;
        for(int i = 0; i < 3; i ++) {
            status[i] = new int[3];
            buttons[i] = new JButton[3];
            for(int j = 0; j < 3; j ++) {
                status[i][j] = 0;
            }
        }
        btnExit.setVisible(false);
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });    
        draw();
        if(isFirst == false) {
            runCom();
        }
    }
    
    private void endGame(int dir) {
        for(int i = 0; i < 3; i ++) for(int j = 0; j < 3; j ++) {
            buttons[i][j].setEnabled(false);
        }
        if(dir == 0) {
            this.getContentPane().setBackground(Color.yellow);
        }
        else if(dir == 1) {
            this.getContentPane().setBackground(Color.green);
        }
        else {
            this.getContentPane().setBackground(Color.red);
        }
        btnExit.setVisible(true);
    }
    
    
    private void runCom() {
        List<Integer> remain = new ArrayList<>();
        int cnt = 0;
        for(int i = 0; i < 3; i ++) for(int j = 0; j < 3; j ++) {
            if(status[i][j] == 0) {
                status[i][j] = 2;
                if(checkWin(2) == true) {
                    drawButton(i, j, 2);
                    return ;
                }
                status[i][j] = 0;
                cnt ++;
                remain.add(i * 3 + j);
            }
        }
        
        for(int i = 0; i < 3; i ++) for(int j = 0; j < 3; j ++) {
            if(status[i][j] == 0) {
                status[i][j] = 1;
                if(checkWin(1) == true) {
                    drawButton(i, j, 2);
                    return ;
                }
                status[i][j] = 0;
            }
        }
        
        int id = remain.get(rand.nextInt(cnt));
        
        drawButton(id / 3, id % 3, 2);
    }
    
    public Boolean drawButton(int x, int y, int dir) {
        if(dir == 1) {
            buttons[x][y].setText("O");
        }
        else {
            buttons[x][y].setText("X");
        }
        
        status[x][y] = dir;
        buttons[x][y].setEnabled(false);
        int val = 0;
        
        select_count ++;
        if(checkWin(dir) == true) {
            endGame(dir);
            return true;
        }
        else if(select_count == 9) {
            endGame(0);
            return true;
        }
        return false;
    }
    
    public void waiting() {
        try {
            Thread.sleep(200);
        }
        catch(Exception ex) {
            
        }
    }
    public boolean checkWin(int dir) {
        for(int i = 0; i < 3; i ++) {
            boolean answer = true;
            for(int j = 0; j < 3; j ++) {
                if(status[i][j] != dir) {
                    answer = false;
                }
            }
            if(answer == true) {
                return true;
            }
        }
        for(int i = 0; i < 3; i ++) {
            boolean answer = true;
            for(int j = 0; j < 3; j ++) {
                if(status[j][i] != dir) {
                    answer = false;
                }
            }
            if(answer == true) {
                return true;
            }
        }
        boolean answer = true;
        for(int i = 0; i < 3; i ++) {
            if(status[i][i] != dir) {
                answer = false;
            }
        }
        if(answer == true) {
            return true;
        }
        answer = true;
        for(int i = 0; i < 3; i ++) {
            if(status[i][2 - i] != dir) {
                answer = false;
            }
        }
        if(answer == true) {
            return true;
        }
        return false;
    }
    
    public void draw() {
        int width_unit = 0;
        int height_unit = 0;
        width_unit = mainPanel.getWidth() / 3;
        height_unit = mainPanel.getHeight() / 3;
        Font font = new Font("Verdana", Font.BOLD, width_unit - 30);
        for(int i = 0; i < 3; i ++) for(int j = 0; j < 3; j ++) {
            int pos_x = width_unit * i;
            int pos_y = height_unit * j;
            buttons[i][j] = new JButton();
            mainPanel.add(buttons[i][j]);
            buttons[i][j].setBounds(pos_x, pos_y, width_unit, height_unit);
            
            buttons[i][j].setFont(font);
            final int x = i;
            final int y = j;
            buttons[i][j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(drawButton(x, y, 1) == false) {
                        waiting();
                        runCom();
                    }
                }
            });    
        }
    }
    
    //Grid
    private void grid() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        mainPanel = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 169, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
        );

        btnExit.setText("Exit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String args[]) {
        TicTacToe toe = new TicTacToe(true);
        toe.show();
    } 
}
