/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchaingfororgandonation;

import static blockchaingfororgandonation.DonarFrame.Encrypt;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEABIRDS-PC
 */
public class AdminHome extends javax.swing.JFrame {

    /**
     * Creates new form AdminHome
     */
    
    DBConnection dbn=new DBConnection();
    Statement st=dbn.stt;
    
    public AdminHome() {
        initComponents();
        
        showBlockchain();
    }
    
    public final void showBlockchain()
    {
        try
        {
            ResultSet rs=st.executeQuery("select * from blockchain");                     
            while(rs.next())
            {
                String blocknumber=rs.getString(1);                        
                String timestamp=rs.getString(2);
                String nonce=rs.getString(3);
                String data=rs.getString(4);                                                 
                String previousblockhash=rs.getString(5);
                String currentblockhash=rs.getString(6);

                if(!(data.contains("Hospital/Pharm")))
                {
                    data="XXXXXXXXXXXXXX";                        
                }

                previousblockhash=previousblockhash.substring(0,(previousblockhash.length()/2))+" "+previousblockhash.substring((previousblockhash.length()/2),(previousblockhash.length()));
                currentblockhash=currentblockhash.substring(0,(currentblockhash.length()/2))+" "+currentblockhash.substring((currentblockhash.length()/2),(currentblockhash.length()));
                
                DefaultTableModel dm=(DefaultTableModel)jTable1.getModel();
                Vector v=new Vector();
                v.add(blocknumber.trim());
                v.add(timestamp.trim());
                v.add(nonce.trim());
                v.add(data.trim());
                v.add(previousblockhash.trim());
                v.add(currentblockhash.trim());
                dm.addRow(v);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        String publickey="",privatekey="";
        try
        {
            ResultSet rs=st.executeQuery("select * from ECCKeys");
            if(rs.next())
            {
                publickey=rs.getString(1);
                privatekey=rs.getString(2);
                
                jTextField1.setText(publickey.trim());
                jTextField2.setText(privatekey.trim());
                
                jButton2.setEnabled(false);
            }                
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            ResultSet rs=st.executeQuery("select * from organdonation");                     
            while(rs.next())
            {
                String name=rs.getString(1);                        
                String age=rs.getString(2);
                String bloodgroup=rs.getString(3);
                String organ=rs.getString(4);                                                 
                String contactDetails=rs.getString(5);                
                String hospitalName=rs.getString(6);
                
                DefaultTableModel dm=(DefaultTableModel)jTable2.getModel();
                Vector v=new Vector();
                v.add(name.trim());
                v.add(age.trim());
                v.add(bloodgroup.trim());
                v.add(organ.trim());
                v.add(contactDetails.trim());                
                v.add(hospitalName.trim());
                dm.addRow(v);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            ResultSet rs=st.executeQuery("select * from medicinedonation");                     
            while(rs.next())
            {
                String DonarName=rs.getString(1);
                String MedicineName=rs.getString(2);                        
                String Manufacturer=rs.getString(3);
                String MedicineType=rs.getString(4);
                String PackSize=rs.getString(5);                                                 
                String Expiry=rs.getString(6);                
		String Description=rs.getString(7); 
                String Status=rs.getString(8);
                String hospitalName=rs.getString(9);
                
                DefaultTableModel dm=(DefaultTableModel)jTable3.getModel();
                Vector v=new Vector();
                v.add(DonarName.trim());
                v.add(MedicineName.trim());
                v.add(Manufacturer.trim());
                v.add(MedicineType.trim());
                v.add(PackSize.trim());
                v.add(Expiry.trim());                
		v.add(Description.trim()); 
                v.add(Status.trim());
                v.add(hospitalName.trim());
                dm.addRow(v);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 0, 51));

        jLabel1.setFont(new java.awt.Font("Algerian", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Admin");

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(282, 282, 282)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Block Number", "Timestamp", "Nonce", "Data", "Previous Block Hash", "Current Block Hash"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(5).setHeaderValue("Current Block Hash");
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Distributed Ledger (Blockchain)", jPanel2);

        jButton2.setText("ECC Keys Generation");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Public Key");

        jLabel3.setText("Private Key");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jTextField2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(405, 405, 405))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(417, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(406, 406, 406))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(46, 46, 46)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        jTabbedPane1.addTab("Key Generation", jPanel3);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Donar Name", "Age", "Blood Group", "Organ", "Contact Details", "Hospital Name"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jButton3.setText("Decrypt and View");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Organ Donar Details", jPanel4);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Donar Name", "Medicine Name", "Manufacturer", "Medicine Type", "PackSize", "Expiry", "Description", "Status", "Hospital Name"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton4.setText("Decrypt and View");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Medicine Donar Details", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        try
        {
            try
            {
                ResultSet rs=st.executeQuery("select * from ECCKeys");
                if(rs.next())
                {
                    String publickey=rs.getString(1);
                    String privatekey=rs.getString(2);
                    
                    jTextField1.setText(publickey.trim());
                    jTextField2.setText(privatekey.trim());
                                        
                    JOptionPane.showMessageDialog(this,"ECC Keys are already generated!");                    
                }
                else
                {                    
                    int size = 32;
                    Random rnd = new Random();
                    BigInteger a = BigInteger.probablePrime(size/2,rnd);
                    BigInteger b = a.nextProbablePrime();       // Elliptic group Ep(a,b)
                    BigInteger nA = a.multiply(b);               
                    BigInteger m = (a.subtract(BigInteger.ONE)).multiply(b.subtract(BigInteger.ONE));
                    BigInteger G = getCoprime(m);   // Generator Point G is belong into Ep(a,b)
                    BigInteger pK = G.modInverse(m);

                    String publickey = G.toString() + "," + nA.toString();
                    String privatekey = pK.toString() + "," + nA.toString();

                    jTextField1.setText(publickey.trim());
                    jTextField2.setText(privatekey.trim());
                    
                    st.executeUpdate("insert into ECCKeys values('"+publickey.trim()+"','"+privatekey.trim()+"')");                                                            
                    
                    JOptionPane.showMessageDialog(this,"ECC Keys are generated Successfully!");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }                
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel dm1=(DefaultTableModel)jTable2.getModel();
        dm1.setRowCount(0);
        
        String privatekey=jTextField2.getText().trim();
        
        String[] s = privatekey.trim().split(",");
        String e2 = s[0];
        String m1 = s[1];
        
        try
        {
            ResultSet rs=st.executeQuery("select * from organdonation");                     
            while(rs.next())
            {
                String encryptedname=rs.getString(1);                        
                String encryptedage=rs.getString(2);
                String encryptedbloodgroup=rs.getString(3);
                String encryptedorgan=rs.getString(4);                                                 
                String encryptedcontactDetails=rs.getString(5); 
                String encryptedhospitalName=rs.getString(6);
                
                String name=Decrypt(encryptedname.trim(),e2.trim(),m1.trim());
                String age=Decrypt(encryptedage.trim(),e2.trim(),m1.trim());
                String bloodgroup=Decrypt(encryptedbloodgroup.trim(),e2.trim(),m1.trim());
                String organ=Decrypt(encryptedorgan.trim(),e2.trim(),m1.trim());
                String contactDetails=Decrypt(encryptedcontactDetails.trim(),e2.trim(),m1.trim());
                String hospitalName=Decrypt(encryptedhospitalName.trim(),e2.trim(),m1.trim());
                
                DefaultTableModel dm=(DefaultTableModel)jTable2.getModel();
                Vector v=new Vector();
                v.add(name.trim());
                v.add(age.trim());
                v.add(bloodgroup.trim());
                v.add(organ.trim());
                v.add(contactDetails.trim());                
                v.add(hospitalName.trim());
                dm.addRow(v);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        jButton3.setEnabled(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel dm1=(DefaultTableModel)jTable3.getModel();
        dm1.setRowCount(0);
        
        String privatekey=jTextField2.getText().trim();
        
        String[] s = privatekey.trim().split(",");
        String e2 = s[0];
        String m1 = s[1];
        
        try
        {
            ResultSet rs=st.executeQuery("select * from medicinedonation");                     
            while(rs.next())
            {
                String encryptedname=rs.getString(1);
                String encryptedMedicineName=rs.getString(2);                        
                String encryptedManufacturer=rs.getString(3);
                String encryptedMedicineType=rs.getString(4);
                String encryptedPackSize=rs.getString(5);                                                 
                String encryptedExpiry=rs.getString(6); 
		String encryptedDescription=rs.getString(7); 
                String Status=rs.getString(8); 
                String encryptedhospitalName=rs.getString(9);
                
                String name=Decrypt(encryptedname.trim(),e2.trim(),m1.trim());
                String MedicineName=Decrypt(encryptedMedicineName.trim(),e2.trim(),m1.trim());
                String Manufacturer=Decrypt(encryptedManufacturer.trim(),e2.trim(),m1.trim());
                String MedicineType=Decrypt(encryptedMedicineType.trim(),e2.trim(),m1.trim());
                String PackSize=Decrypt(encryptedPackSize.trim(),e2.trim(),m1.trim());
                String Expiry=Decrypt(encryptedExpiry.trim(),e2.trim(),m1.trim());
		String Description=Decrypt(encryptedDescription.trim(),e2.trim(),m1.trim());
                String hospitalName=Decrypt(encryptedhospitalName.trim(),e2.trim(),m1.trim());
                
                DefaultTableModel dm=(DefaultTableModel)jTable3.getModel();
                Vector v=new Vector();
                v.add(name.trim());
                v.add(MedicineName.trim());
                v.add(Manufacturer.trim());
                v.add(MedicineType.trim());
                v.add(PackSize.trim());
                v.add(Expiry.trim());                
		v.add(Description.trim());
                if(Status.trim().equals("Pending"))
                {
                    v.add("Click Here to Approved");
                }
                else
                {
                    v.add(Status.trim());
                }
                v.add(hospitalName.trim());
                dm.addRow(v);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        jButton4.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        
        int row = jTable3.rowAtPoint(evt.getPoint());
        int col = jTable3.columnAtPoint(evt.getPoint());
        
        String status = jTable3.getValueAt(row, col).toString().trim();
        if(status.trim().equals("Click Here to Approved"))
        {
            jTable3.setValueAt("Approved", row, col);
            
            String donorname = jTable3.getValueAt(row, 0).toString().trim();
            String medicinename = jTable3.getValueAt(row, 1).toString().trim();
            
            String publickey=jTextField1.getText().trim();
            String[] s5 = publickey.trim().split(",");
            String e1 = s5[0];
            String m1 = s5[1];
            
            String encryptedname=Encrypt(donorname.trim(),e1.trim(),m1.trim());
            String encryptedmedicinename=Encrypt(medicinename.trim(),e1.trim(),m1.trim());
            
            try
            {
                String query="update medicinedonation set Status='"+"Approved"+"' where DonarName='"+encryptedname+"' and MedicineName='"+encryptedmedicinename+"'";
                System.out.println(query.trim());
                st.execute(query.trim());
                JOptionPane.showMessageDialog(this, "Approved Successfully!");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }        
    }//GEN-LAST:event_jTable3MouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    public static BigInteger getCoprime(BigInteger m) 
    {
        Random rnd = new Random();
        int length = m.bitLength()-1;
        BigInteger e = BigInteger.probablePrime(length,rnd);
        while (! (m.gcd(e)).equals(BigInteger.ONE) ) {
           e = BigInteger.probablePrime(length,rnd);
        }
        return e;
    }

    public static String Decrypt(String cipherText, String e2, String m1) 
    {
        String[] cip = cipherText.split(",");    
        String data = "";
        for (int i = 0; i < cip.length; i++)
        {                        
            String dec = new BigInteger(cip[i]).modPow(new BigInteger(e2), new BigInteger(m1)).toString();                                            
            int ascii = Integer.parseInt(dec.trim());
            char ori=(char)ascii;                    
            data = data + ori;
        }
        return data.trim();
    }
}
