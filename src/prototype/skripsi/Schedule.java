/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Schedule extends javax.swing.JFrame {
    public DefaultTableModel tabModel;
//    public Statement st;
//    public ResultSet rs;
//    Connection cn = config.Connection.configDB();
    
    

    /**
     * Creates new form FORMschedule
     */
    public Schedule() {
        initComponents();
        dataFromDataBaseToComboBox();
        dataComboBoxteknisi();
        schedule_table();
        kosong();
        AutoNomor();
    }
    
     public void AutoNomor(){
         try{
//           
             String sql = "SELECT * FROM schedule ORDER BY id_schedule DESC";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
             if (res.next()) {
                 String kd = res.getString("id_schedule").substring(5);
                 String AN = "" + (Integer.parseInt(kd)+1);
                 String Nol = "";
                
                 
                if (AN.length()==1) {
                     Nol ="00";
                 }if (AN.length()==2) {
                     Nol ="0";
                 } if (AN.length()==3) {
                     Nol="";
                 }
                 txt_id.setText("REP_"+Nol+AN);
                 
             } else {
                 txt_id.setText("REPH_001");
             }
         } catch (Exception e){
             JOptionPane.showMessageDialog(rootPane, e);
         }
     }
    
    
    private void makePreview (String vName){
        try {
            String gambarkop = getClass().getResource("/assets/tritanu_head.png").toString();
            String gambarkopsmall = getClass().getResource("/assets/tritanusmall.png").toString();
            String sql = "SELECT * FROM schedule";
            java.sql.Connection conn=(Connection)config.configDB();        
            String locFile = "src/Report/";
            String namaFile = locFile + vName + ".jasper";
            HashMap parameter = new HashMap();
            parameter.put("kop", gambarkop);
            parameter.put("kopsmall", gambarkopsmall);
            parameter.put("companyname", String.valueOf(Namacompany.getSelectedItem()));
            File report_file = new File (namaFile);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report_file.getPath());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn);
            JasperViewer.viewReport(jasperPrint, false );
            JasperViewer.setDefaultLookAndFeelDecorated(true);
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

}
    
    

    private void schedule_table(){ 
    DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Schedule");
        model.addColumn("Date");
        model.addColumn("Company Name");
        model.addColumn("Activity");
        model.addColumn("Name Technician");
        model.addColumn("Code Machine");
        model.addColumn("Error Code");
        model.addColumn("Information");
        model.addColumn("Addres");
        //menampilkan data database kedalam tabel
    try {
            int no=1;
            String sql = "select * from schedule";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9)});
            }
            tb_schedule.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    Edit.setEnabled(false);
    Delete.setEnabled(false);
    }
    
    
    
    
    
    
    
    
    
    
    
    
         public void dataComboBoxteknisi(){
         try {
            String sql = "SELECT * FROM teknisi";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                teknisiCB.addItem(res.getString("nama_teknisi"));
            }
            
            res.last();
            int jumlahdata = res.getRow();
            res.first();
            
        } catch (SQLException e) {
        }
         
     }
     
    
         
         
         
         
     public void dataFromDataBaseToComboBox(){
         try {

            String sql = "SELECT * FROM client";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            
            
            while(res.next()){
                Namacompany.addItem(res.getString("company_name"));
//            Object[] ob = new Object[2];
//            ob[0] = res.getString(1);
//            
//            Namacompany.addItem(ob[3]);
            
            }
//                res.close(); stm.close();
            res.last();
            int jumlahdata = res.getRow();
            res.first();
            
        } catch (SQLException e) {
             System.out.println(e.getMessage());
        }
         
     }  
    
        private void kosong(){
        txt_id.setText(null);
        tanggal.setDate(null);
        Namacompany.setSelectedItem(null);
        activityCB.setSelectedItem(null);
        teknisiCB.setSelectedItem(null);  
        codemesin.setText(null);
        errorcode.setText(null);
        informasi.setText(null);
        txtAddres.setText(null);
        } 
     
     
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        NAME = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        NAME1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        NAME2 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tb_schedule = new javax.swing.JTable();
        Save = new javax.swing.JButton();
        Edit = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        NAME5 = new javax.swing.JLabel();
        tanggal = new com.toedter.calendar.JDateChooser();
        NAME4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        teknisiCB = new javax.swing.JComboBox<String>();
        jLabel12 = new javax.swing.JLabel();
        errorcode = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        informasi = new javax.swing.JTextArea();
        NAME6 = new javax.swing.JLabel();
        activityCB = new javax.swing.JComboBox<String>();
        NAME3 = new javax.swing.JLabel();
        Namacompany = new javax.swing.JComboBox<String>();
        jLabel15 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtAddres = new javax.swing.JTextField();
        codemesin = new javax.swing.JTextField();
        CLEAR = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        cetak = new javax.swing.JLabel();
        schedule = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        NAME.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NAME.setText("COMPANY NAME");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("PHONE");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("ADDRES");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("EMAIL");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("SAVE");

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("EDIT");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setText("DELETE");

        NAME1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NAME1.setText("STAFF NAME");

        NAME2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NAME2.setText("CODE MACHINE");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel2.setText("CLIENT ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(354, 354, 354))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton3))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(NAME1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(58, 58, 58)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                            .addComponent(jTextField4)
                                            .addComponent(jTextField3))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(NAME2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField5)
                                    .addComponent(jScrollPane1))))
                        .addGap(100, 100, 100))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NAME, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NAME2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NAME1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/tritanu_head.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(555, 555, 555)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tb_schedule.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_schedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_scheduleMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tb_schedule);

        Save.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Save.setText("SAVE");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        Edit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Edit.setText("EDIT");
        Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditActionPerformed(evt);
            }
        });

        Delete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Delete.setText("DELETE");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel3.setText("SCHEDULE");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 21, -1, -1));

        NAME5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        NAME5.setText("DATE");
        jPanel4.add(NAME5, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 108, 103, 15));
        jPanel4.add(tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 103, 167, -1));

        NAME4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        NAME4.setText("TECHNICIAN");
        jPanel4.add(NAME4, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 220, 103, 15));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel14.setText("CODE MACHINE");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 317, -1, 23));
        jPanel4.add(teknisiCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 217, 167, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel12.setText("ERROR CODE");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 287, -1, -1));
        jPanel4.add(errorcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 283, 167, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel13.setText("IFORMATION");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 351, -1, -1));

        informasi.setColumns(20);
        informasi.setLineWrap(true);
        informasi.setRows(2);
        informasi.setTabSize(5);
        jScrollPane5.setViewportView(informasi);

        jPanel4.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 351, 170, -1));

        NAME6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        NAME6.setText("ACTIVITY");
        jPanel4.add(NAME6, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 182, 103, 15));

        activityCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Service", "Maintanance", "Installation", "Consumable" }));
        jPanel4.add(activityCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 179, 167, -1));

        NAME3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        NAME3.setText("COMPANY NAME");
        jPanel4.add(NAME3, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 144, 103, 15));

        Namacompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamacompanyActionPerformed(evt);
            }
        });
        jPanel4.add(Namacompany, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 141, 167, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel15.setText("ID SCHEDULE");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 67, -1, 23));

        txt_id.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jPanel4.add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 69, 167, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel8.setText("ADDRES");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 263, -1, -1));
        jPanel4.add(txtAddres, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 255, 167, -1));
        jPanel4.add(codemesin, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 318, 167, -1));

        CLEAR.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        CLEAR.setText("REFRESH");
        CLEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARActionPerformed(evt);
            }
        });

        btnCari.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCari.setText("SEARCH");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        cetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cetakMouseClicked(evt);
            }
        });

        schedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/oke/CETAK SCHEDULE.png"))); // NOI18N
        schedule.setText("jLabel5");
        schedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scheduleMouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/oke/SCHEDULE.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cetak)
                .addGap(282, 282, 282))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(Save)
                        .addGap(18, 18, 18)
                        .addComponent(Edit)
                        .addGap(18, 18, 18)
                        .addComponent(Delete)
                        .addGap(18, 18, 18)
                        .addComponent(CLEAR)
                        .addGap(41, 41, 41)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCari)
                        .addGap(88, 88, 88))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(189, 189, 189)
                                .addComponent(schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(134, 134, 134)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Save)
                            .addComponent(Edit)
                            .addComponent(Delete)
                            .addComponent(CLEAR)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCari))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(schedule, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cetak)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void NamacompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamacompanyActionPerformed
        // TODO add your handling code here:
            String ids = String.valueOf(Namacompany.getSelectedItem());
        String vnm = "";
        try {
            String sql;
            sql ="SELECT * FROM client where company_name='"+ids+"'";
            //             st=conn.createStatement();
            //             rs=st.executeQuery(sql);
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);

            while(res.next()){
                ids = res.getString("company_name");
              //  codemesin.setText(res.getString("code_machine"));
                txtAddres.setText(res.getString("addres"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
    }//GEN-LAST:event_NamacompanyActionPerformed

    
    
    
    
    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
         String tampilan= "yyyy-MM-dd";
       SimpleDateFormat fm = new SimpleDateFormat(tampilan);
       String tanggalan = String.valueOf(fm.format(tanggal.getDate()));
       
       try {
            String sql = "INSERT INTO schedule VALUES ('"+txt_id.getText()+"','"+ tanggalan+"','"+Namacompany.getSelectedItem()+"','"+activityCB.getSelectedItem()+"','"+teknisiCB.getSelectedItem()+"','"+codemesin.getText()+"','"+errorcode.getText()+"','"+informasi.getText()+"','"+txtAddres.getText()+"')";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());       
        }  
        schedule_table();
        kosong();
        AutoNomor();
    }//GEN-LAST:event_SaveActionPerformed

    
    
    
    
    
    
    
    
    private void EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditActionPerformed
       
        int jawab; 
        if ((jawab= JOptionPane.showConfirmDialog(null,"are you sure?" , "confirm", JOptionPane.YES_NO_OPTION))==0) {
        String tampilan= "yyyy-MM-dd";
       SimpleDateFormat fm = new SimpleDateFormat(tampilan);
       String tanggalan = String.valueOf(fm.format(tanggal.getDate()));
        try {
            String sql ="UPDATE schedule SET id_schedule = '"+txt_id.getText()+"', Date_act = '"+ tanggalan+"',company_name = '"+ Namacompany.getSelectedItem()+"', activity = '"+ activityCB.getSelectedItem()+"', technician_name = '"+ teknisiCB.getSelectedItem()+"', code_machine = '"+ codemesin.getText()+"',error_code = '"+ errorcode.getText()+"',information = '"+ informasi.getText()+"',addres = '"+ txtAddres.getText()+"' WHERE id_schedule = '"+txt_id.getText()+"' ";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }
        schedule_table();
        kosong();
        AutoNomor();
    }//GEN-LAST:event_EditActionPerformed

    
    
    
    
    
    
    
    
    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        int jawab; 
        if ((jawab= JOptionPane.showConfirmDialog(null,"are you sure?" , "confirm", JOptionPane.YES_NO_OPTION))==0) {
            try {
            String sql ="delete from schedule where id_schedule='"+txt_id.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        schedule_table();
        kosong();
        }
    }//GEN-LAST:event_DeleteActionPerformed

    
    
    
    
    
    private void tb_scheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_scheduleMouseClicked
        // TODO add your handling code here:
        
        int baris = tb_schedule.rowAtPoint(evt.getPoint());
        
        String id = tb_schedule.getValueAt(baris, 0).toString();
        txt_id.setText(id);
      
        String tanggalkerja = tb_schedule.getValueAt(baris, 1).toString();     
        tanggal.setDate(Date.valueOf(tanggalkerja));
        
        String company = tb_schedule.getValueAt(baris, 2).toString();
        Namacompany.setSelectedItem(company);
      
        String aktifitas = tb_schedule.getValueAt(baris, 3).toString();
        activityCB.setSelectedItem(aktifitas);
        
        String teknisi = tb_schedule.getValueAt(baris, 4).toString();
        teknisiCB.setSelectedItem(teknisi);
        
        String code = tb_schedule.getValueAt(baris, 5).toString();
        codemesin.setText(code);
        
        String error = tb_schedule.getValueAt(baris, 6).toString();
        errorcode.setText(error);     
        
        String information = tb_schedule.getValueAt(baris, 7).toString();
        informasi.setText(information);   
        
        String alamat = tb_schedule.getValueAt(baris, 8).toString();
        txtAddres.setText(alamat);
        
        Edit.setEnabled(true);
    Delete.setEnabled(true);
    Save.setEnabled(false);
    }//GEN-LAST:event_tb_scheduleMouseClicked

    
    
    
    private void CLEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARActionPerformed
        // TODO add your handling code here:
        schedule_table();
        kosong();
        Edit.setEnabled(false);
        Delete.setEnabled(false);
        Save.setEnabled(true);
        AutoNomor();
    }//GEN-LAST:event_CLEARActionPerformed

    
    
    
    
    
    
    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
         
        
   DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Schedule");
        model.addColumn("Date");
        model.addColumn("Company Name");
        model.addColumn("Activity");
        model.addColumn("Name Technician");
        model.addColumn("Code Machine");
        model.addColumn("Error Code");
        model.addColumn("Information");
        model.addColumn("Addres");
        
        try {

            String sql = "select * from schedule where id_schedule like '%" + txtCari.getText() + "%' "+ "OR company_name like '%" +txtCari.getText() + "%'  "+ "OR activity like '%" +txtCari.getText() + "%'  "+ "OR technician_name like '%" +txtCari.getText() + "%'   "+ "OR code_machine like '%" +txtCari.getText() + "%'  "+ "OR error_code like '%" +txtCari.getText() + "%'   "+ "OR addres like '%" +txtCari.getText() + "%'  ";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9)});

            }
            tb_schedule.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_btnCariActionPerformed

    private void cetakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakMouseClicked
        // cetak
        makePreview("client");               
    }//GEN-LAST:event_cetakMouseClicked

    private void scheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scheduleMouseClicked
        // TODO add your handling code here:
        makePreview("DeliveryOrder");
    }//GEN-LAST:event_scheduleMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        makePreview("reportSchedule");
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
          mainMenu fs = new mainMenu();
        fs.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel4MouseClicked

    
    
  
    
    
    
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Schedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Schedule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CLEAR;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Edit;
    private javax.swing.JLabel NAME;
    private javax.swing.JLabel NAME1;
    private javax.swing.JLabel NAME2;
    private javax.swing.JLabel NAME3;
    private javax.swing.JLabel NAME4;
    private javax.swing.JLabel NAME5;
    private javax.swing.JLabel NAME6;
    private javax.swing.JComboBox<String> Namacompany;
    private javax.swing.JButton Save;
    private javax.swing.JComboBox<String> activityCB;
    private javax.swing.JButton btnCari;
    private javax.swing.JLabel cetak;
    private javax.swing.JTextField codemesin;
    private javax.swing.JTextField errorcode;
    private javax.swing.JTextArea informasi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel schedule;
    private com.toedter.calendar.JDateChooser tanggal;
    private javax.swing.JTable tb_schedule;
    private javax.swing.JComboBox<String> teknisiCB;
    private javax.swing.JTextField txtAddres;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables
}
