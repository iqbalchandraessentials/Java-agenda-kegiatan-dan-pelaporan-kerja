
package prototype.skripsi;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel; 
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.util.HashMap;
import login.mainMenu;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author IqbalChandraEssens
 */
public class Report extends javax.swing.JFrame {
     public DefaultTableModel tabModel;
        
     
    public Report() {
        initComponents();
        report_table();
        dataComboBoxtSchedule();
        dataComboBoxtTechnician();
        dataComboBoxtCompany();
       // dataComboBoxtActivity();
        kosong();
        AutoNomor();
        
    }

    
    
    private void makePreview (String vName){
        try {
            String sql = "SELECT * FROM teknisi";
            java.sql.Connection conn=(Connection)config.configDB();        
            String locFile = "src/Report/";
            String namaFile = locFile + vName + ".jasper";
            HashMap parameter = new HashMap();
            //parameter.put("companyname", String.valueOf(Namacompany.getSelectedItem()));
            File report_file = new File (namaFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn);
            JasperViewer.viewReport(jasperPrint, false );
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
      }
    
    
      public void AutoNomor(){
         try{
//           
             String sql = "SELECT * FROM report_teknisi ORDER BY id_report DESC";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
             if (res.next()) {
                 String kd = res.getString("id_report").substring(5);
                 String AN = "" + (Integer.parseInt(kd)+1);
                 String Nol = "";
                
                 
                if (AN.length()==1) {
                     Nol ="00";
                 }if (AN.length()==2) {
                     Nol ="0";
                 } if (AN.length()==3) {
                     Nol="";
                 }
                 txtIdReport.setText("REP_"+Nol+AN);
                 
             } else {
                 txtIdReport.setText("REPH_001");
             }
         } catch (Exception e){
             JOptionPane.showMessageDialog(rootPane, e);
         }
     }
    
    
    
      private void report_table(){ 
    DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID REPORT");
        model.addColumn("DATE");
        model.addColumn("ID SCHEDULE");
        model.addColumn("COMPANY NAME");
        model.addColumn("TECHNICIAN");
        model.addColumn("CODE MACHINE");
        model.addColumn("ERROR CODE");
        model.addColumn("INFORMATION");
        model.addColumn("ADDRES");
        model.addColumn("STATUS");
        
        //menampilkan data database kedalam tabel
    try {
            int no=1;
            String sql = "select * from report_teknisi";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10)});
            }
            tbReport.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    btnDelete.setEnabled(false);
    btnEdit.setEnabled(false);
    }
    
    

         public void dataComboBoxtSchedule(){
             int n=cbIdSchedule.getItemCount();
            int i=0;
            try {
                while(i<=n) {
                    cbIdSchedule.removeItemAt(i+1);
                    i++;
                }
                } catch (Exception e) {
            }
            
         try {
            String sql = "SELECT schedule.id_schedule FROM schedule";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                cbIdSchedule.addItem(res.getString("id_schedule"));
            }            
            res.last();
            int jumlahdata = res.getRow();
            res.first();      
        } catch (SQLException e) {
        }
        
         try {
            String sql = "SELECT report_teknisi.id_schedule FROM report_teknisi";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                cbIdSchedule.removeItem(res.getString("id_schedule"));
            }            
            res.last();
            int jumlahdata = res.getRow();
            res.first();      
        } catch (SQLException e) {
        }
         
     }
    
       public void dataComboBoxtCompany(){
         try {
            String sql = "SELECT * FROM client";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                cbCompany.addItem(res.getString("company_name"));
            }            
            res.last();
            int jumlahdata = res.getRow();
            res.first();      
        } catch (SQLException e) {
        }
         
     }
         
         
         
         
      public void dataComboBoxtTechnician(){
         try {
            String sql = "SELECT * FROM teknisi";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                cbTeknisi.addItem(res.getString("nama_teknisi"));
            }          
            res.last();
            int jumlahdata = res.getRow();
            res.first();
            
        } catch (SQLException e) {
        }       
     }
   
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        NAME3 = new javax.swing.JLabel();
        NAME5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jComboBox1 = new javax.swing.JComboBox<String>();
        NAME4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<String>();
        jLabel12 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        head = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbReport = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        NAME6 = new javax.swing.JLabel();
        NAME7 = new javax.swing.JLabel();
        jdDate = new com.toedter.calendar.JDateChooser();
        cbIdSchedule = new javax.swing.JComboBox<String>();
        NAME8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        cbTeknisi = new javax.swing.JComboBox<String>();
        jLabel16 = new javax.swing.JLabel();
        txtError = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtInformation = new javax.swing.JTextArea();
        NAME9 = new javax.swing.JLabel();
        cbStatus = new javax.swing.JComboBox<String>();
        jLabel2 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIdReport = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbCompany = new javax.swing.JComboBox<String>();
        jLabel1 = new javax.swing.JLabel();
        activityCB = new javax.swing.JComboBox<String>();
        refresh = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btncari = new javax.swing.JButton();
        report = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "TITLE 6"
            }
        ));
        jScrollPane4.setViewportView(jTable3);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("SAVE");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("EDIT");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("DELETE");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setText("CLIENT ");

        NAME3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NAME3.setText("COMPANY NAME");

        NAME5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NAME5.setText("COMPANY NAME");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        NAME4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NAME4.setText("TECHNICIAN");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("CODE MACHINE");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("ERROR CODE");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("IFORMATION");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(139, 139, 139))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(75, 75, 75)
                                .addComponent(jTextField8))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(NAME3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(NAME5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(NAME4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(16, 16, 16)))
                        .addGap(68, 68, 68))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(89, 89, 89)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jTextField7)
                                .addGap(68, 68, 68))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(24, Short.MAX_VALUE))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NAME5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NAME3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NAME4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addComponent(jButton6))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        head.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/tritanu_head.png"))); // NOI18N
        head.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                headMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(527, 527, 527)
                .addComponent(head)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(head, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "TITLE 6", "dfd", "nudfll", "nudfll", "nulldff"
            }
        ));
        tbReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbReportMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbReport);

        jPanel5.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 121, 920, 277));

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel5.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 58, -1, -1));

        btnEdit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel5.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 58, -1, -1));

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel5.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(601, 58, -1, -1));

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel4.setText("REPORT FIELD");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        NAME6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        NAME6.setText("COMPANY NAME");
        jPanel6.add(NAME6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 186, -1, 15));

        NAME7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        NAME7.setText("DATE");
        jPanel6.add(NAME7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 88, -1, 15));
        jPanel6.add(jdDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 88, 150, -1));

        cbIdSchedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbIdScheduleMouseClicked(evt);
            }
        });
        cbIdSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIdScheduleActionPerformed(evt);
            }
        });
        jPanel6.add(cbIdSchedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 152, 150, -1));

        NAME8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        NAME8.setText("TECHNICIAN");
        jPanel6.add(NAME8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 217, 68, 15));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel15.setText("CODE MACHINE");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 307, -1, 23));
        jPanel6.add(txtCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 308, 153, -1));

        cbTeknisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-----------" }));
        jPanel6.add(cbTeknisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 214, 150, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setText("ERROR CODE");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 276, -1, -1));
        jPanel6.add(txtError, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 276, 150, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel17.setText("IFORMATION");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 372, -1, -1));

        txtInformation.setColumns(5);
        txtInformation.setLineWrap(true);
        txtInformation.setRows(4);
        txtInformation.setTabSize(7);
        jScrollPane3.setViewportView(txtInformation);

        jPanel6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 372, 153, 88));

        NAME9.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        NAME9.setText("STATUS");
        jPanel6.add(NAME9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 124, -1, 15));

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "IN PROGRES", "COMPLETE", " " }));
        jPanel6.add(cbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 121, 150, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel2.setText("ADDRES");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 344, 68, -1));
        jPanel6.add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 341, 153, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel5.setText("ID REPORT");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 54, -1, -1));
        jPanel6.add(txtIdReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 50, 150, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setText("ID SCHEDULE");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 156, -1, -1));

        cbCompany.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cbCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCompanyActionPerformed(evt);
            }
        });
        jPanel6.add(cbCompany, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 183, 150, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel1.setText("ACTIVITY");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 245, -1, -1));

        activityCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Service", "Maintanance", "Installation", "Consumable" }));
        jPanel6.add(activityCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 245, 150, -1));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 11, 293, 506));

        refresh.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        refresh.setText("REFRESH");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel5.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(682, 58, -1, -1));
        jPanel5.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(943, 59, 137, -1));

        btncari.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btncari.setText("SEARCH");
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });
        jPanel5.add(btncari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1086, 58, -1, -1));

        report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/oke/CETAK REPORT.png"))); // NOI18N
        report.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportMouseClicked(evt);
            }
        });
        jPanel5.add(report, new org.netbeans.lib.awtextra.AbsoluteConstraints(767, 416, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1324, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
      private void kosong(){
        txtIdReport.setText(null);
        jdDate.setDate(null);
        cbIdSchedule.setSelectedItem(null);
        cbCompany.setSelectedItem(null);
        cbStatus.setSelectedItem(null);  
        cbTeknisi.setSelectedItem(null);  
        txtError.setText(null);
        txtCode.setText(null);
        txtInformation.setText(null);
        txtAddress.setText(null);
        }
    
      private void CLEARActionPerformed(java.awt.event.ActionEvent evt) {                                      
        // TODO add your handling code here:
        report_table();
        kosong();
    }   
    
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

       String tampilan= "yyyy-MM-dd";
       SimpleDateFormat fm = new SimpleDateFormat(tampilan);
       String tanggalan = String.valueOf(fm.format(jdDate.getDate()));
       
       try {
            String sql = "INSERT INTO report_teknisi VALUES('"+txtIdReport.getText()+"','"+ tanggalan+"','"+cbIdSchedule.getSelectedItem()+"','"+cbCompany.getSelectedItem()+"','"+cbTeknisi.getSelectedItem()+"','"+txtCode.getText()+"','"+txtError.getText()+"','"+txtInformation.getText()+"','"+txtAddress.getText()+"','"+cbStatus.getSelectedItem()+"')";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());       
        }  
        report_table();
        kosong();
        AutoNomor();
    }//GEN-LAST:event_btnSaveActionPerformed

    
    
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
          int jawab; 
        if ((jawab= JOptionPane.showConfirmDialog(null,"are you sure?" , "confirm", JOptionPane.YES_NO_OPTION))==0) {
        String tampilan= "yyyy-MM-dd";
       SimpleDateFormat fm = new SimpleDateFormat(tampilan);
       String tanggalan = String.valueOf(fm.format(jdDate.getDate()));
        try {
            String sql ="UPDATE report_teknisi SET id_report= '"+txtIdReport.getText()+"', date = '"+ tanggalan+"', id_schedule = '"+ cbIdSchedule.getSelectedItem()+"', company_name = '"+ cbCompany.getSelectedItem()+"', status = '"+ cbStatus.getSelectedItem()+"', technician = '"+ cbTeknisi.getSelectedItem()+"',error_code = '"+ txtError.getText()+"',code_machine = '"+ txtCode.getText()+"', addres = '"+ txtAddress.getText()+"', information = '"+ txtInformation.getText()+"' WHERE id_report= '"+txtIdReport.getText()+"' ";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }
        report_table();
        kosong();
        AutoNomor();
        dataComboBoxtSchedule();
          // txtIdReport.setEnabled(false);
        
    }//GEN-LAST:event_btnEditActionPerformed

    
    
    
    
    
    
    private void tbReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbReportMouseClicked
        // TODO add your handling code here:
         int baris = tbReport.rowAtPoint(evt.getPoint());
        
        String id = tbReport.getValueAt(baris, 0).toString();
        txtIdReport.setText(id);
      
        String tanggalkerja = tbReport.getValueAt(baris, 1).toString();     
        jdDate.setDate(Date.valueOf(tanggalkerja));
        
        String schedule = tbReport.getValueAt(baris, 2).toString();
        cbIdSchedule.addItem(schedule);
        cbIdSchedule.setSelectedItem(schedule);
        
        
         String company = tbReport.getValueAt(baris, 3).toString();
        cbCompany.setSelectedItem(company);
        
         String teknisi = tbReport.getValueAt(baris, 4).toString();
        cbTeknisi.setSelectedItem(teknisi);
        
         String code = tbReport.getValueAt(baris, 5).toString();
        txtCode.setText(code);
        
        
          String error = tbReport.getValueAt(baris, 6).toString();
        txtError.setText(error);
        
     
          String information = tbReport.getValueAt(baris, 7).toString();
        txtInformation.setText(information);
        
         String addres = tbReport.getValueAt(baris, 8).toString();
        txtAddress.setText(addres);
        
     
        
         String status = tbReport.getValueAt(baris, 9).toString();
        cbStatus.setSelectedItem(status);
      
        
        
        btnSave.setEnabled(false);
        txtIdReport.setEnabled(false);
        btnDelete.setEnabled(true);
        btnEdit.setEnabled(true);
       
    }//GEN-LAST:event_tbReportMouseClicked

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        kosong();
        report_table();
        btnSave.setEnabled(true);
        txtIdReport.setEnabled(true);
        AutoNomor();
        dataComboBoxtSchedule();
    }//GEN-LAST:event_refreshActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        
        
        int jawab; 
        if ((jawab= JOptionPane.showConfirmDialog(null,"are you sure?" , "confirm", JOptionPane.YES_NO_OPTION))==0) {
            try {
            String sql ="delete from report_teknisi where id_report='"+txtIdReport.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        report_table();
        kosong();
        AutoNomor();
        
        }
        
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cbCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCompanyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCompanyActionPerformed

    private void cbIdScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIdScheduleActionPerformed
        // TODO add your handling code here:
        String ids = String.valueOf(cbIdSchedule.getSelectedItem());
        String vnm = "";
        try {
            String sql;
            sql ="SELECT * FROM schedule where id_schedule='"+ids+"' ";
            //             st=conn.createStatement();
            //             rs=st.executeQuery(sql);
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);

            while(res.next()){
                ids = res.getString("id_schedule");
                vnm = res.getString("company_name");
                cbIdSchedule.setSelectedItem(ids);
                cbCompany.setSelectedItem(vnm);
                txtError.setText(res.getString("error_code"));
                txtCode.setText(res.getString("code_machine"));
                txtAddress.setText(res.getString("addres"));
                cbTeknisi.setSelectedItem(res.getString("technician_name"));
                activityCB.setSelectedItem(res.getString("activity"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_cbIdScheduleActionPerformed

    private void cbIdScheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbIdScheduleMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cbIdScheduleMouseClicked

    
    
    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
        DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ID REPORT");
        model.addColumn("DATE");
        model.addColumn("ID SCHEDULE");
        model.addColumn("COMPANY NAME");
        model.addColumn("TECHNICIAN");
        model.addColumn("CODE MACHINE");
        model.addColumn("ERROR CODE");
        model.addColumn("INFORMATION");
        model.addColumn("ADDRES");
        model.addColumn("STATUS");
        try {

            String sql = "select * from report_teknisi where id_report like '%" + txtCari.getText() + "%' "+ "OR id_schedule like '%" +txtCari.getText() + "%'  "+ "OR company_name like '%" +txtCari.getText() + "%'  "+ "OR status like '%" +txtCari.getText() + "%'   "+ "OR technician like '%" +txtCari.getText() + "%'  "+ "OR error_code like '%" +txtCari.getText() + "%'   "+ "OR code_machine like '%" +txtCari.getText() + "%'  "+ "OR addres like '%" +txtCari.getText() + "%' ";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9),res.getString(10)});
            }
            tbReport.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btncariActionPerformed

    
    
    
    private void reportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportMouseClicked
        // TODO add your handling code here:
        makePreview("teknisi");
    }//GEN-LAST:event_reportMouseClicked

    private void headMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headMouseClicked
        // TODO add your handling code here:
                 mainMenu fs = new mainMenu();
        fs.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_headMouseClicked

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Report().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NAME3;
    private javax.swing.JLabel NAME4;
    private javax.swing.JLabel NAME5;
    private javax.swing.JLabel NAME6;
    private javax.swing.JLabel NAME7;
    private javax.swing.JLabel NAME8;
    private javax.swing.JLabel NAME9;
    private javax.swing.JComboBox<String> activityCB;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btncari;
    private javax.swing.JComboBox<String> cbCompany;
    private javax.swing.JComboBox<String> cbIdSchedule;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JComboBox<String> cbTeknisi;
    private javax.swing.JLabel head;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private com.toedter.calendar.JDateChooser jdDate;
    private javax.swing.JButton refresh;
    private javax.swing.JLabel report;
    private javax.swing.JTable tbReport;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtError;
    private javax.swing.JTextField txtIdReport;
    private javax.swing.JTextArea txtInformation;
    // End of variables declaration//GEN-END:variables
}
