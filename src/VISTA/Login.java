/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import CONEXION.Conexion;
import DAO.AgenteDaoImp;
import DTO.Agente;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

/**
 *
 * @author Seba
 */
public class Login extends javax.swing.JFrame {
    
    /**
     * Creates new form Interfaz
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIniciarSesion.doClick();
            }
        };
        txtUsuario.addActionListener(action);
        jPassword.addActionListener(action);
        
        /* Edit Rocha: Lo deje comentado x mientras
        para poder probar los q no son Admin.
        ACORDEMONOS DE BORRAR DSP ESTO!!.
        Borrar esto para eliminar credenciales automáticas*/
        //txtUsuario.setText("ADMIN");
        //jPassword.setText("PASO"); 
    }
    
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("IMG/icono-login-png-6.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalir = new javax.swing.JButton();
        lblIconoLogin = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jPassword = new javax.swing.JPasswordField();
        btnIniciarSesion = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir.setBackground(new java.awt.Color(0, 204, 255));
        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 60, -1));

        lblIconoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/icono-login-png-6.png"))); // NOI18N
        getContentPane().add(lblIconoLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, -1, -1));

        lblUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblUsuario.setText("Usuario :");
        getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        lblContraseña.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblContraseña.setText("Contraseña :");
        getContentPane().add(lblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, -1));

        txtUsuario.setBackground(new java.awt.Color(204, 255, 255));
        getContentPane().add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 160, 30));

        jPassword.setBackground(new java.awt.Color(204, 255, 255));
        getContentPane().add(jPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 160, 30));

        btnIniciarSesion.setBackground(new java.awt.Color(0, 204, 255));
        btnIniciarSesion.setText("Iniciar Sesion");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 420, -1, -1));

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Fondo4.jpg"))); // NOI18N
        getContentPane().add(lblFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    //Boton para iniciar el login del usuario
    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed
        Conexion cx = new Conexion();
        Connection con = cx.getConnection();
        String query = "select usuario, passwd, estado, administrador from agentes where usuario =?  and passwd =?";
        String user = txtUsuario.getText();
        String pass = String.valueOf(jPassword.getPassword());
        
        try {
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, user);
            st.setString(2, pass);
            
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (user.isEmpty()) {
                    this.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese Usuario");
                } else if (pass.isEmpty()) {
                    this.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese contraseña");
                } else if (estado() == 0) {
                    this.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Usuario Inactivo. Contactese con el Administrador.");
                } else {
                    this.setVisible(false);
                    int admin = Administrador();
                    String ses = RunFlag();
                    Agente ag= new Agente();
                    new Index(admin).setVisible(true);
                }
            } else {
                this.setVisible(true);
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecta","Error",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            
        }
    }//GEN-LAST:event_btnIniciarSesionActionPerformed
    /**
     * *
     * Metodo que retorna el valor del estado del agente. Este es usado para ver
     * si el usuario esta activo o no.
     *
     * @return estado si es 0, el usuario esta inactivo, si retorna 1 esta
     * activo.
     */
    public int estado() {
        Conexion cx = new Conexion();
        Connection con = cx.getConnection();
        String query = "select usuario, passwd, estado from agentes where usuario =?  and passwd =?";
        String user = txtUsuario.getText();
        String pass = String.valueOf(jPassword.getPassword());
        int estado;
        try {
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, user);
            st.setString(2, pass);
            
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                estado = rs.getInt("estado");
                return estado;
            } else {
                return estado = 0;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas de conexion, intente mas tarde o comuniquese con el Administrador.");
            return estado = 0;
        }
    }

    /**
     * *
     * Consulta si el usuario tiene perfil de administrador o no. este metodo se
     * usa en el index para ver cuales botones se inicializan.
     *
     * @return
     */
    public int Administrador() {
        Conexion cx = new Conexion();
        Connection con = cx.getConnection();
        String query = "select usuario, passwd, ADMINISTRADOR from agentes where usuario =?  and passwd =?";
        String user = txtUsuario.getText();
        String pass = String.valueOf(jPassword.getPassword());
        int admin;
        try {
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, user);
            st.setString(2, pass);
            
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                admin = rs.getInt("ADMINISTRADOR");
                return admin;
            } else {
                return admin = 0;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas de conexion, intente mas tarde o comuniquese con el Administrador.");
            return admin = 0;
        }
    }
    
    /***
     * Metodo que retorna el RUN del usuario para hacer un control
     * de "Session" 
     * @return runFlag, variable que contiene el RUN.
     */
    public String RunFlag()
    {
        Conexion cx = new Conexion();
        Connection con = cx.getConnection();
        String query = "select usuario, passwd, ADMINISTRADOR from agentes where usuario =?  and passwd =?";
        String user = txtUsuario.getText();
        String pass = String.valueOf(jPassword.getPassword());
        String runFlag;
        try {
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, user);
            st.setString(2, pass);
            
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                runFlag = rs.getString("run");
                return runFlag;
            } else {
                return runFlag = null;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas de conexion, intente mas tarde o comuniquese con el Administrador.");
            return runFlag = "";
        }
    }
    
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
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblIconoLogin;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
