/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchaingfororgandonation;

import static blockchaingfororgandonation.AdminHome.Decrypt;
import static blockchaingfororgandonation.DonarFrame.Encrypt;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SEABIRDS-PC
 */
public class HospitalFrame extends javax.swing.JFrame {

    /**
     * Creates new form HospitalFrame
     */
    
    DBConnection dbn=new DBConnection();
    Statement st=dbn.stt;
    String Hash;
    String name;
    
    public HospitalFrame(String ha, String na) {
        initComponents();
        
        Hash=ha.trim();
        name=na.trim();
        
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

                if(!(Hash.trim().equals(currentblockhash.trim())))
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
        
        String publicKey="",privatekey="";
        try
        {
            ResultSet rs=st.executeQuery("select * from ecckeys");
            if(rs.next())
            {
                publicKey=rs.getString(1);
                privatekey=rs.getString(2);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        String[] s5 = publicKey.trim().split(",");
        String e1 = s5[0];
        String m1 = s5[1];
        String encryptedhospitalName=Encrypt(name.trim(),e1.trim(),m1.trim());
        
        String[] s = privatekey.trim().split(",");
        String e2 = s[0];
        m1 = s[1];
                
        try
        {
            ResultSet rs=st.executeQuery("select * from organdonation where HospitalName='"+encryptedhospitalName.trim()+"'");                     
            while(rs.next())
            {
                String encryptedname=rs.getString(1);                        
                String encryptedage=rs.getString(2);
                String encryptedbloodgroup=rs.getString(3);
                String encryptedorgan=rs.getString(4);                                                 
                String encryptedcontactDetails=rs.getString(5); 
                //String encryptedhospitalName=rs.getString(6);
                
                String name1=Decrypt(encryptedname.trim(),e2.trim(),m1.trim());
                String age=Decrypt(encryptedage.trim(),e2.trim(),m1.trim());
                String bloodgroup=Decrypt(encryptedbloodgroup.trim(),e2.trim(),m1.trim());
                String organ=Decrypt(encryptedorgan.trim(),e2.trim(),m1.trim());
                String contactDetails=Decrypt(encryptedcontactDetails.trim(),e2.trim(),m1.trim());
                //String hospitalName=Decrypt(encryptedhospitalName.trim(),e2.trim(),m1.trim());
                
                DefaultTableModel dm=(DefaultTableModel)jTable2.getModel();
                Vector v=new Vector();
                v.add(name1.trim());
                v.add(age.trim());
                v.add(bloodgroup.trim());
                v.add(organ.trim());
                v.add(contactDetails.trim());                
                v.add(name.trim());
                dm.addRow(v);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            ResultSet rs=st.executeQuery("select * from medicinedonation where HospitalName='"+encryptedhospitalName.trim()+"'");                                         
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
                //String encryptedhospitalName=rs.getString(9);
                
                String name1=Decrypt(encryptedname.trim(),e2.trim(),m1.trim());
                String MedicineName=Decrypt(encryptedMedicineName.trim(),e2.trim(),m1.trim());
                String Manufacturer=Decrypt(encryptedManufacturer.trim(),e2.trim(),m1.trim());
                String MedicineType=Decrypt(encryptedMedicineType.trim(),e2.trim(),m1.trim());
                String PackSize=Decrypt(encryptedPackSize.trim(),e2.trim(),m1.trim());
                String Expiry=Decrypt(encryptedExpiry.trim(),e2.trim(),m1.trim());
		String Description=Decrypt(encryptedDescription.trim(),e2.trim(),m1.trim());
                //String hospitalName=Decrypt(encryptedhospitalName.trim(),e2.trim(),m1.trim());
                
                if(Status.trim().equals("Approved"))
                {
                    DefaultTableModel dm=(DefaultTableModel)jTable3.getModel();
                    Vector v=new Vector();
                    v.add(name1.trim());
                    v.add(MedicineName.trim());
                    v.add(Manufacturer.trim());
                    v.add(MedicineType.trim());
                    v.add(PackSize.trim());
                    v.add(Expiry.trim());                
                    v.add(Description.trim());                
                    v.add(Status.trim());                
                    v.add(name.trim());
                    dm.addRow(v);
                }
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 51, 0));

        jLabel1.setFont(new java.awt.Font("Algerian", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Hospital/Pharm");

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
                .addGap(181, 181, 181)
                .addComponent(jButton1)
                .addGap(48, 48, 48))
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Distributed Ledger (Blockchain)", jPanel2);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Donar Name", "Age", "Blood Group", "Organ", "Contact Details", "Hospital Name"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(26, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(32, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Organ Donar Details", jPanel3);

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(33, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(32, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Medicine Donar Details", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:      
       
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
            java.util.logging.Logger.getLogger(HospitalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HospitalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HospitalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HospitalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new HospitalFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
