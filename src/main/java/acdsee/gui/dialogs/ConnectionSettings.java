/*
 * ConnectionSettings.java
 *
 * Created on 25. Oktober 2006, 21:33
 */
package acdsee.gui.dialogs;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import acdsee.io.http.ProxySettings;

/**
 *
 * @author A752203_L
 */
public class ConnectionSettings extends javax.swing.JFrame {

    /**
     * Creates new form ConnectionSettings
     */
    public ConnectionSettings() {
        initComponents();
        Set forwardKeys = getFocusTraversalKeys(
                KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
        Set newForwardKeys = new HashSet(forwardKeys);
        newForwardKeys.add(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
        setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
                newForwardKeys);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonHelp = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        buttonOK = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        rb_directConnection = new javax.swing.JRadioButton();
        rb_manuallySetConnectionDetails = new javax.swing.JRadioButton();
        rb_connectionDetection = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        httpProxyPort = new javax.swing.JTextField();
        httpProxyLabel = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        httpProxyPortLabel = new javax.swing.JLabel();
        httpProxyURL = new javax.swing.JTextField();
        cb_useSameProxyForAllProtocols = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        sslProxyLabel = new javax.swing.JLabel();
        sslProxyURL = new javax.swing.JTextField();
        sslProxyPortLabel = new javax.swing.JLabel();
        sslProxyPort = new javax.swing.JTextField();
        ftpProxyPort = new javax.swing.JTextField();
        gopherProxyPort = new javax.swing.JTextField();
        socksHostPort = new javax.swing.JTextField();
        socksPortLabel = new javax.swing.JLabel();
        ftpProxyPortLabel = new javax.swing.JLabel();
        gopherProxyURL = new javax.swing.JTextField();
        ftpProxyURL = new javax.swing.JTextField();
        socksHostURL = new javax.swing.JTextField();
        socksHostLabel = new javax.swing.JLabel();
        gopherProxyLabel = new javax.swing.JLabel();
        ftpProxyLabel = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        gopherProxyPortLabel = new javax.swing.JLabel();
        rb_proxyConfigurationViaURL = new javax.swing.JRadioButton();
        tf_autoProxyConfigurationURL = new javax.swing.JTextField();
        buttonReload = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Verbindungs-Einstellungen");

        buttonHelp.setText("Help");

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonOK.setText("OK");
        buttonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOKActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Proxies fuer den Zugriff auf das Internet konfigurieren"));

        buttonGroup1.add(rb_directConnection);
        rb_directConnection.setSelected(true);
        rb_directConnection.setText("Direkte Verbindung zum Internet");
        rb_directConnection.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rb_directConnection.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rb_directConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_directConnectionActionPerformed(evt);
            }
        });

        buttonGroup1.add(rb_manuallySetConnectionDetails);
        rb_manuallySetConnectionDetails.setText("Manuelle Proxy-Konfiguration");
        rb_manuallySetConnectionDetails.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rb_manuallySetConnectionDetails.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rb_manuallySetConnectionDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_manuallySetConnectionDetailsActionPerformed(evt);
            }
        });

        buttonGroup1.add(rb_connectionDetection);
        rb_connectionDetection.setText("Die Proxy-Einstellungen fuer dieses Netzwerk automatisch erkennen");
        rb_connectionDetection.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rb_connectionDetection.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rb_connectionDetection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_connectionDetectionActionPerformed(evt);
            }
        });

        jPanel2.setEnabled(false);

        jLabel11.setText("Kein Proxy fuer:");
        jLabel11.setEnabled(false);

        httpProxyPort.setEnabled(false);

        httpProxyLabel.setText("HTTP-Proxy:");
        httpProxyLabel.setEnabled(false);

        jTextField11.setEnabled(false);

        httpProxyPortLabel.setText("Port:");
        httpProxyPortLabel.setEnabled(false);

        httpProxyURL.setEnabled(false);

        cb_useSameProxyForAllProtocols.setText("Fuer alle Protokolle diesen Proxyserver verwenden");
        cb_useSameProxyForAllProtocols.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cb_useSameProxyForAllProtocols.setEnabled(false);
        cb_useSameProxyForAllProtocols.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cb_useSameProxyForAllProtocols.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_useSameProxyForAllProtocolsItemStateChanged(evt);
            }
        });

        jLabel12.setText("Beispiel: .mozilla.org, .net.de, 127.0.0.1");
        jLabel12.setEnabled(false);

        sslProxyLabel.setText("SSL-Proxy:");
        sslProxyLabel.setEnabled(false);

        sslProxyURL.setEnabled(false);

        sslProxyPortLabel.setText("Port:");
        sslProxyPortLabel.setEnabled(false);

        sslProxyPort.setEnabled(false);

        ftpProxyPort.setEnabled(false);

        gopherProxyPort.setEnabled(false);

        socksHostPort.setEnabled(false);

        socksPortLabel.setText("Port:");
        socksPortLabel.setEnabled(false);

        ftpProxyPortLabel.setText("Port:");
        ftpProxyPortLabel.setEnabled(false);

        gopherProxyURL.setEnabled(false);

        ftpProxyURL.setEnabled(false);

        socksHostURL.setEnabled(false);

        socksHostLabel.setText("SOCKS-Host:");
        socksHostLabel.setEnabled(false);

        gopherProxyLabel.setText("Gopher-Proxy:");
        gopherProxyLabel.setEnabled(false);

        ftpProxyLabel.setText("FTP-Proxy:");
        ftpProxyLabel.setEnabled(false);

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setText("SOCKS v4");
        jRadioButton4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton4.setEnabled(false);
        jRadioButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setSelected(true);
        jRadioButton5.setText("SOCKS v5");
        jRadioButton5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jRadioButton5.setEnabled(false);
        jRadioButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));

        gopherProxyPortLabel.setText("Port:");
        gopherProxyPortLabel.setEnabled(false);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(ftpProxyLabel)
                    .add(sslProxyLabel)
                    .add(gopherProxyLabel)
                    .add(socksHostLabel))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, ftpProxyURL)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, sslProxyURL)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, gopherProxyURL)
                        .add(socksHostURL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 253, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jRadioButton4)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jRadioButton5)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(socksPortLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(socksHostPort, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(gopherProxyPortLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(gopherProxyPort))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(ftpProxyPortLabel)
                            .add(sslProxyPortLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(ftpProxyPort)
                            .add(sslProxyPort, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(sslProxyLabel)
                    .add(sslProxyPortLabel)
                    .add(sslProxyURL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(sslProxyPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(ftpProxyLabel)
                    .add(ftpProxyPortLabel)
                    .add(ftpProxyURL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(ftpProxyPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(gopherProxyLabel)
                    .add(gopherProxyURL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(gopherProxyPortLabel)
                    .add(gopherProxyPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(socksHostLabel)
                    .add(socksPortLabel)
                    .add(socksHostPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(socksHostURL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jRadioButton4)
                    .add(jRadioButton5)))
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(86, 86, 86)
                .add(jLabel12))
            .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(cb_useSameProxyForAllProtocols)
                            .add(jPanel2Layout.createSequentialGroup()
                                .add(httpProxyLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(httpProxyURL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 254, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(httpProxyPortLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(httpProxyPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 46, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jTextField11)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(httpProxyLabel)
                    .add(httpProxyURL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(httpProxyPortLabel)
                    .add(httpProxyPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cb_useSameProxyForAllProtocols)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel11)
                    .add(jTextField11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 15, Short.MAX_VALUE)
                .add(jLabel12)
                .addContainerGap())
        );

        buttonGroup1.add(rb_proxyConfigurationViaURL);
        rb_proxyConfigurationViaURL.setText("Automatische Proxy-Konfigurations-URL:");
        rb_proxyConfigurationViaURL.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rb_proxyConfigurationViaURL.setMargin(new java.awt.Insets(0, 0, 0, 0));
        rb_proxyConfigurationViaURL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb_proxyConfigurationViaURLItemStateChanged(evt);
            }
        });
        rb_proxyConfigurationViaURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_proxyConfigurationViaURLActionPerformed(evt);
            }
        });

        tf_autoProxyConfigurationURL.setEnabled(false);

        buttonReload.setText("Neu laden");
        buttonReload.setEnabled(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(rb_directConnection)
                    .add(rb_manuallySetConnectionDetails)
                    .add(rb_connectionDetection)
                    .add(rb_proxyConfigurationViaURL)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(17, 17, 17)
                        .add(tf_autoProxyConfigurationURL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 316, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(buttonReload)))
                .add(42, 42, 42))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(rb_directConnection)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rb_connectionDetection)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rb_manuallySetConnectionDetails)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(rb_proxyConfigurationViaURL)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tf_autoProxyConfigurationURL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(buttonReload))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(249, Short.MAX_VALUE)
                .add(buttonOK, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 79, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonCancel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonHelp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {buttonCancel, buttonHelp, buttonOK}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(buttonHelp)
                    .add(buttonCancel)
                    .add(buttonOK))
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("Proxies fuer den Zugriff auf das Internet konfigurieren");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOKActionPerformed

        ProxySettings settings = ProxySettings.getInstance();
        if (settings.isProxyEnabled()) {
            settings.setProxyHost(httpProxyURL.getText());
            settings.setProxyPort(httpProxyPort.getText());
        }

        // TODO add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_buttonOKActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void cb_useSameProxyForAllProtocolsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_useSameProxyForAllProtocolsItemStateChanged
        // TODO add your handling code here:
        String proxyURL = httpProxyURL.getText();
        String proxyPort = httpProxyPort.getText();
        ftpProxyURL.setText(proxyURL);
        ftpProxyPort.setText(proxyPort);
        gopherProxyURL.setText(proxyURL);
        gopherProxyPort.setText(proxyPort);
        socksHostURL.setText(proxyURL);
        socksHostPort.setText(proxyPort);
        sslProxyURL.setText(proxyURL);
        sslProxyPort.setText(proxyPort);
        JCheckBox box = (JCheckBox) evt.getSource();
        Component[] comp = jPanel3.getComponents();
        for (int i = 0, j = comp.length; i < j; i++) {
            comp[i].setEnabled(!box.isSelected());
        }
    }//GEN-LAST:event_cb_useSameProxyForAllProtocolsItemStateChanged

    private void rb_proxyConfigurationViaURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_proxyConfigurationViaURLActionPerformed
        // TODO add your handling code here:
        Component[] comp = jPanel2.getComponents();
        for (int i = 0, j = comp.length; i < j; i++) {
            comp[i].setEnabled(false);
        }
        comp = jPanel3.getComponents();
        for (int i = 0, j = comp.length; i < j; i++) {
            comp[i].setEnabled(false);
        }
    }//GEN-LAST:event_rb_proxyConfigurationViaURLActionPerformed

    private void rb_proxyConfigurationViaURLItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb_proxyConfigurationViaURLItemStateChanged
        // TODO add your handling code here:
        JRadioButton btn = (JRadioButton) evt.getSource();
        tf_autoProxyConfigurationURL.setEnabled(btn.isSelected());
        buttonReload.setEnabled(btn.isSelected());
    }//GEN-LAST:event_rb_proxyConfigurationViaURLItemStateChanged

    private void rb_directConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_directConnectionActionPerformed

        ProxySettings.getInstance().setProxyEnabled(false);

        // TODO add your handling code here:
        Component[] comp = jPanel2.getComponents();
        for (int i = 0, j = comp.length; i < j; i++) {
            comp[i].setEnabled(false);
        }
        comp = jPanel3.getComponents();
        for (int i = 0, j = comp.length; i < j; i++) {
            comp[i].setEnabled(false);
        }
    }//GEN-LAST:event_rb_directConnectionActionPerformed

    private void rb_connectionDetectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_connectionDetectionActionPerformed

        ProxySettings.getInstance().setProxyEnabled(false);

        // TODO add your handling code here:
        Component[] comp = jPanel2.getComponents();
        for (int i = 0, j = comp.length; i < j; i++) {
            comp[i].setEnabled(false);
        }
        comp = jPanel3.getComponents();
        for (int i = 0, j = comp.length; i < j; i++) {
            comp[i].setEnabled(false);
        }
    }//GEN-LAST:event_rb_connectionDetectionActionPerformed

    private void rb_manuallySetConnectionDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_manuallySetConnectionDetailsActionPerformed
        // TODO add your handling code here:

        ProxySettings.getInstance().setProxyEnabled(true);

        Component[] comp = jPanel2.getComponents();
        for (int i = 0, j = comp.length; i < j; i++) {
            comp[i].setEnabled(true);
        }
        comp = jPanel3.getComponents();
        for (int i = 0, j = comp.length; i < j; i++) {
            comp[i].setEnabled(true);
        }
    }//GEN-LAST:event_rb_manuallySetConnectionDetailsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectionSettings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonOK;
    private javax.swing.JButton buttonReload;
    private javax.swing.JCheckBox cb_useSameProxyForAllProtocols;
    private javax.swing.JLabel ftpProxyLabel;
    private javax.swing.JTextField ftpProxyPort;
    private javax.swing.JLabel ftpProxyPortLabel;
    private javax.swing.JTextField ftpProxyURL;
    private javax.swing.JLabel gopherProxyLabel;
    private javax.swing.JTextField gopherProxyPort;
    private javax.swing.JLabel gopherProxyPortLabel;
    private javax.swing.JTextField gopherProxyURL;
    private javax.swing.JLabel httpProxyLabel;
    private javax.swing.JTextField httpProxyPort;
    private javax.swing.JLabel httpProxyPortLabel;
    private javax.swing.JTextField httpProxyURL;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JRadioButton rb_connectionDetection;
    private javax.swing.JRadioButton rb_directConnection;
    private javax.swing.JRadioButton rb_manuallySetConnectionDetails;
    private javax.swing.JRadioButton rb_proxyConfigurationViaURL;
    private javax.swing.JLabel socksHostLabel;
    private javax.swing.JTextField socksHostPort;
    private javax.swing.JTextField socksHostURL;
    private javax.swing.JLabel socksPortLabel;
    private javax.swing.JLabel sslProxyLabel;
    private javax.swing.JTextField sslProxyPort;
    private javax.swing.JLabel sslProxyPortLabel;
    private javax.swing.JTextField sslProxyURL;
    private javax.swing.JTextField tf_autoProxyConfigurationURL;
    // End of variables declaration//GEN-END:variables

}
