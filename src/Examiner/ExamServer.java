/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examiner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SUDIP
 */
public class ExamServer extends javax.swing.JFrame {

    /**
     * Creates new form examserver
     */
       DefaultTableModel dm;  
    public ExamServer() {
        initComponents();
        this.setLocationRelativeTo(null);
                try
        {
            s=new ServerSocket(2005);
            slist=new ArrayList<>();
            nlist=new ArrayList<>();
            marks=new ArrayList<>();
            h=new HandleClient();
            h.start();
            //((DefaultTableModel)namelist.getModel()).setValueAt("Raj[7]", 0, 0);
        }catch(Exception e){}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        showlist = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        showlist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Students' Name", "Marks"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(showlist);
        if (showlist.getColumnModel().getColumnCount() > 0) {
            showlist.getColumnModel().getColumn(0).setResizable(false);
            showlist.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jButton1)
                .addContainerGap(234, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
                try {
            s.close();
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(ExamServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
      int reply=JOptionPane.showConfirmDialog(null, "Are you want to exit?","Select an option!!",JOptionPane.YES_NO_OPTION);
        if(reply==JOptionPane.YES_OPTION)
          {
            try{
        s.close();
        System.exit(0);
           }catch(Exception e){}
       
           }
    }//GEN-LAST:event_jButton1ActionPerformed
   
    /**
     * @param args the command line arguments
     */
    public void loadList()
  {
      while(showlist.getRowCount()>0)
      {
          ((DefaultTableModel)showlist.getModel()).removeRow(0);
      }
      int row=0;
      for(int x=0;x<nlist.size();x++)
      {
          if(!nlist.get(x).equals(""))
          {
                ((DefaultTableModel)showlist.getModel()).addRow(new Object[]{});
                ((DefaultTableModel)showlist.getModel()).setValueAt(nlist.get(x), row, 0);
               row++;
          }
      }
            int row1=0;
            int x1=0;
            for(int x2=0;x2<=marks.size();x2++)
      {
          if(!marks.get(x2).equals(""))
          {
                ((DefaultTableModel)showlist.getModel()).addColumn(new Object[]{});
                ((DefaultTableModel)showlist.getModel()).setValueAt(marks.get(x2), row1, x1+1);
               row1++;
               x1++;
          }
      }
  }


  private ServerSocket s;
  private Socket c;
  private String name;
  private ArrayList<Socket> slist;
  private ArrayList<String> nlist;
  private ArrayList<String> marks;
  private BufferedReader brc;
  private PrintWriter out;
  private HandleClient h;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable showlist;
    // End of variables declaration//GEN-END:variables
class HandleClient extends Thread
{
   private int tid;
   private int tid1;
    public void SendToAllClients(String message)
    {
        Socket so;
        PrintWriter out;
        for(int x=0;x<slist.size();x++)
        {
            try{
                so=slist.get(x);
                //out=new PrintWriter(so.getOutputStream(),true);
                //out.println(message);
            }catch(Exception e){}
        }
    }
    class ProcessClient extends Thread
    {
        private int id;
        private String name;
        private Socket c;
        private BufferedReader brc;
            private Object display;
        public ProcessClient(int id,String name,Socket c)
        {
            this.id=id;
            this.name=name;
            this.c=c;
        }
        public void run()
        {
            String msg;
            try{
            brc=new BufferedReader(new InputStreamReader(c.getInputStream()));
            loadList();
            nlist.set(id, "");
            loadList();
           }catch(Exception e){}
        }
    }
   
    public void run()
    {
        while(true)
        {
            try {
                
                c=s.accept();
                brc=new BufferedReader(new InputStreamReader(c.getInputStream()));
                name=brc.readLine();
                slist.add(c);
                nlist.add(name+"["+tid+"]");
                ProcessClient t=new ProcessClient(tid, name, c);
                t.start();
               tid++;
                
            } catch (IOException ex) {
                Logger.getLogger(ExamServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    }
}