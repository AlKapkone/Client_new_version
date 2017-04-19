package client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Gui extends JFrame {

    private static DefaultTableModel dtm;

    public Gui() {
        initComponents();
        jTable1.setModel(dtm = new DefaultTableModel());

        dtm.addColumn("ID");
        dtm.addColumn("Ім'я");
        dtm.addColumn("Email");
        getUsers();
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Add user");
        jButton1.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                jButton1ActionPerformed(evt);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        jButton2.setText("Delete user");
        jButton2.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton2ActionPerformed(evt);
        });

        jButton3.setText("Update user");
        jButton3.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton3ActionPerformed(evt);
        });

        jButton4.setText("Get All users");
        jButton4.addActionListener((java.awt.event.ActionEvent evt) -> {
            jButton4ActionPerformed(evt);
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 20)); // NOI18N
        jLabel1.setText("User's List");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(172, 172, 172)
                                                .addComponent(jLabel1))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 55555;
    private static StartClient startClient;

    public static void start() {
        startClient = new StartClient(HOST, PORT);
        startClient.start();
    }

    //------ADD user operation
    public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        JTextField zField = new JTextField(2);
        JTextField xField = new JTextField(15);
        JTextField yField = new JTextField(15);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("ID:"));
        myPanel.add(zField);
        myPanel.add(new JLabel("Name:"));
        myPanel.add(xField);
        myPanel.add(new JLabel("Email:"));
        myPanel.add(yField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter New User's information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            User user = new User(
                    Integer.parseInt(zField.getText()),
                    xField.getText(),
                    yField.getText()
            );
            System.out.println("ID is: " + user.getId());
            System.out.println("The name is: " + user.getName());
            System.out.println("The email is: " + user.getEmail());
            startClient.addUser(user);
        }

        Object row[] = new Object[3];
        row[0] = zField.getText();
        row[1] = xField.getText();
        row[2] = yField.getText();
        dtm.addRow(row);
    }

    //------DELETE user operation
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int t[] = jTable1.getSelectedRows();
        if (t.length > 1) {
            JOptionPane.showMessageDialog(null, "Please select only one row");
        } else if (t.length == 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {

//            String row = (String) dtm.getValueAt(t[0],1);

            User user = new User(
                    Integer.parseInt((String) dtm.getValueAt(t[0],0)),
                    (String) dtm.getValueAt(t[0],1),
                    (String) dtm.getValueAt(t[0],2)
            );


            startClient.deleteUser(user);
            getUsers();
            dtm.removeRow(t[0]);
        }
    }

    //------UPDATE user operation
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int t[] = jTable1.getSelectedRows();
        if (t.length == 0) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
//            for (int i = t.length - 1; i >= 0; i--) {

                User user = new User(
                        Integer.parseInt((String) dtm.getValueAt(t[0],0)),
                        (String) dtm.getValueAt(t[0],1),
                        (String) dtm.getValueAt(t[0],2)
                );
                startClient.updateUser(user);
//            }
        }
    }

    //------GET user operation
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        for(int i = dtm.getRowCount() - 1; i > 0; i--){
            dtm.removeRow(i);
        }
        getUsers();
    }

    private static void getUsers(){
        String allUsers = startClient.getAllUser();
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<List<User>>() {
        }.getType();
        List<User> usersList = gson.fromJson(allUsers, type);

        for(User us : usersList){
            Object row[] = new Object[3];
            row[0] =String.valueOf(us.getId());
            row[1] = us.getName();
            row[2] = us.getEmail();

            dtm.addRow(row);
        }
    }
    
    
    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
