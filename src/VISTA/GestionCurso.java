/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import CONEXION.Conexion;
import DAO.ApoderadoDaoImp;
import DAO.ColegioDaoImp;
import DAO.CursoDaoImp;
import DTO.Colegio;
import DTO.Curso;
import VISTA.CONTROLES.ButtonColumn;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Seba
 */
public class GestionCurso extends javax.swing.JFrame {

    ArrayList<Curso> listaCursos;
    ArrayList<Colegio> listaColegios;
    Conexion obj = new Conexion();
    Colegio colegio;
    Curso curso;
    DefaultTableModel modelo;
    int flag;

    /**
     * Creates new form GestionCurso
     *
     * @param admin
     */
    public GestionCurso(int admin) {
        Admin(admin);
        initComponents();
        this.setLocationRelativeTo(null);
        MostrarCursos();
        datosCombobox();
    }

    /**
     * *
     * Metodo para ver si el usuario posee perfil de administrador.
     *
     * @param admin
     * @return
     */
    public boolean Admin(int admin) {
        this.flag = admin;
        if (admin == 1) {
            flag = 1;
            return true;
        } else {
            flag = 0;
            return false;
        }
    }

    /*ocultar guardar y cancelar, mostrar agregar*/
    public void ResetBotones() {
        btnAgregarCurso.setVisible(true);
        btnCancelar.setVisible(false);
        btnGuardar.setVisible(false);
    }

    /*metodo nuevo pa limpiar*/
    public void LimpiarFormulario() {
        txtCurso.setText(null);
        txtDescripcion.setText(null);
        txtMonto.setText(null);//lo dejo mientras, pero deberíamos borrarlo
        
    }

    public void MostrarCursos() {
        listaCursos = new CursoDaoImp().listar();
        modelo = new DefaultTableModel();
        modelo.addColumn("");
        modelo.addColumn("ID");
        modelo.addColumn("MONTO RECAUDADO");
        modelo.addColumn("COLEGIO");
        modelo.addColumn("DESCRIPCION");
        modelo.addColumn("");
        if (listaCursos.size() > 0) {
            for (Curso curso : listaCursos) {
                modelo.addRow(new Object[]{
                    "EDITAR",
                    curso.getId_curso(),
                    curso.getMonto_recaudado(),
                    curso.getColegio().getNombre(),
                    curso.getDescripcion(),
                    "ELIMINAR"}
                );
            }
            tablaCursos.setModel(modelo);

            /* AGREGA LA ACCION DEL BOTTON ELIMINAR*/
            Action borrar = new AbstractAction() {
                @Override
                /*ESTE ES EL METODO DEL BOTON CUANDO SE PRESIONA*/
                public void actionPerformed(ActionEvent e) {
                    int fila = Integer.valueOf(e.getActionCommand());
                    Curso curso = new Curso();
                    curso = listaCursos.get(fila);
                    new CursoDaoImp().eliminar(curso);
                    LimpiarFormulario();
                    tablaCursos.clearSelection();
                    MostrarCursos();
                    ResetBotones();

                }

            };
            /*ESTA PARTE ES LA QUE AGREGA EL BOTTON ELIMINAR CON ACCION DECLARADA ANTERIORMENTE*/
            ButtonColumn buttonEliminar = new ButtonColumn(tablaCursos, borrar, 5);
            buttonEliminar.setMnemonic(KeyEvent.VK_D);

            /* AGREGA LA ACCION AL BOTTON editar*/
            Action editar = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int fila = Integer.valueOf(e.getActionCommand());
                    Curso curso = new Curso();
                    curso = listaCursos.get(fila);
                    txtMonto.setText(String.valueOf(curso.getMonto_recaudado()));
                    txtDescripcion.setText(curso.getDescripcion());
                    cbColegio.setSelectedItem(colegio.getNombre());
                    btnAgregarCurso.setVisible(false);
                    btnCancelar.setVisible(true);
                    btnGuardar.setVisible(true);
                }

            };
            /*AGREGA EL BOTTON EDITAR A LA COLUMNA CON LA ACCION DECLARADA ANTERIORMENTE*/
            ButtonColumn buttonEditar = new ButtonColumn(tablaCursos, editar, 0);
            buttonEditar.setMnemonic(KeyEvent.VK_D);
        }

    }

    /**
     * *
     * Metodo para poblar el comboBox con los nombre de los colegios.
     *
     */
    public void datosCombobox() {
        listaColegios = new ColegioDaoImp().listar();
        for (Colegio colegio : listaColegios) {
            cbColegio.addItem(colegio.getNombre());
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

        PanelTitulo = new javax.swing.JPanel();
        lblAVOT = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        PanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        PanelInsertar = new javax.swing.JPanel();
        txtCurso = new javax.swing.JTextField();
        btnAgregarCurso = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbColegio = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        PanelTitulo.setBackground(new java.awt.Color(30, 160, 250));

        lblAVOT.setBackground(new java.awt.Color(255, 255, 255));
        lblAVOT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblAVOT.setForeground(new java.awt.Color(255, 255, 255));
        lblAVOT.setText("A.V.O.T.");

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelTituloLayout = new javax.swing.GroupLayout(PanelTitulo);
        PanelTitulo.setLayout(PanelTituloLayout);
        PanelTituloLayout.setHorizontalGroup(
            PanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAVOT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInicio)
                .addContainerGap())
        );
        PanelTituloLayout.setVerticalGroup(
            PanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAVOT)
                    .addComponent(btnInicio))
                .addContainerGap())
        );

        PanelTabla.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista Cursos"));

        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaCursos);

        javax.swing.GroupLayout PanelTablaLayout = new javax.swing.GroupLayout(PanelTabla);
        PanelTabla.setLayout(PanelTablaLayout);
        PanelTablaLayout.setHorizontalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelTablaLayout.setVerticalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelInsertar.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Curso"));

        btnAgregarCurso.setText("Agregar");
        btnAgregarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCursoActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre Curso");

        cbColegio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbColegioActionPerformed(evt);
            }
        });

        jLabel9.setText("Colegio");

        jLabel10.setText("Monto Recaudado");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        jLabel2.setText("Descripcion");

        btnCancelar.setText("Cancelar");

        btnGuardar.setText("Guardar");

        javax.swing.GroupLayout PanelInsertarLayout = new javax.swing.GroupLayout(PanelInsertar);
        PanelInsertar.setLayout(PanelInsertarLayout);
        PanelInsertarLayout.setHorizontalGroup(
            PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInsertarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInsertarLayout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInsertarLayout.createSequentialGroup()
                        .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMonto)
                            .addComponent(cbColegio, 0, 140, Short.MAX_VALUE)
                            .addComponent(txtCurso)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInsertarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregarCurso)
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInsertarLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        PanelInsertarLayout.setVerticalGroup(
            PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInsertarLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbColegio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(32, 32, 32)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(38, 38, 38)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(btnAgregarCurso)
                .addGap(2, 2, 2)
                .addGroup(PanelInsertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        Admin(flag);
        this.setVisible(false);
        Index x = new Index(flag);
        x.setVisible(true);
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnAgregarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCursoActionPerformed
        colegio = listaColegios.get(cbColegio.getSelectedIndex());
        curso = new Curso();
//        JOptionPane.showMessageDialog(null, colegio.getId_colegio());
//        curso.setMonto_recaudado(Integer.parseInt(txtMonto.getText())); //deberiamos borrar ese pero esperaré para hacerlo
        curso.setDescripcion(txtDescripcion.getText());
        curso.setColegio(colegio);
        new CursoDaoImp().insertar(curso);
    }//GEN-LAST:event_btnAgregarCursoActionPerformed

    private void cbColegioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbColegioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbColegioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelInsertar;
    private javax.swing.JPanel PanelTabla;
    private javax.swing.JPanel PanelTitulo;
    private javax.swing.JButton btnAgregarCurso;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnInicio;
    private javax.swing.JComboBox<String> cbColegio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAVOT;
    private javax.swing.JTable tablaCursos;
    private javax.swing.JTextField txtCurso;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}
