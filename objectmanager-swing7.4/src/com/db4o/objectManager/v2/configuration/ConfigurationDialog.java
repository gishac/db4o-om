/* Copyright (C) 2004 - 2007 db4objects Inc. http://www.db4o.com */
/**
 * User: gishac
 * Date: Feb 02, 2008
 * Time: 08:15:00 PM
 */

package com.db4o.objectManager.v2.configuration;

import java.awt.event.*;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.db4o.config.Configuration;
import com.db4o.objectManager.v2.MainFrame;
import com.db4o.objectManager.v2.custom.BackgroundPanel;
import com.db4o.objectManager.v2.resources.ResourceManager;
import com.db4o.objectmanager.configuration.ConfigurationFacade;
import com.jgoodies.looks.Options;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class ConfigurationDialog extends JDialog {

	private final String TITLE="Configuration";
	private ConfigurationPanel configurationPanel = null;
	private BackgroundPanel mainPanel;
	private ConfigurationFacade configuration;	
		
	/**
	 * This method initializes 
	 * 
	 */
	public ConfigurationDialog(ConfigurationFacade configuration) {
		super();
		this.configuration = configuration;
		initialize();
		this.setModal(true);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		configureUI();
        this.setSize(new Dimension(484, 289));
        this.setContentPane(getConfigurationPanel());
        this.setIconImage(ResourceManager.createImageIcon(ResourceManager.ICONS_PLAIN_16X16 + "configure.png", "configure").getImage());
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() { 
			public void windowClosed(java.awt.event.WindowEvent e) { 
				
			}
		});
	}
	
	/**
	 * 
	 */
	private void configureUI() {
		UIManager.put(Options.USE_SYSTEM_FONTS_APP_KEY, Boolean.TRUE);
		Options.setDefaultIconSize(new Dimension(18, 18));
		String lafName = Options.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lafName);
		} catch (Exception e) {
			System.err.println("Can't set look & feel:" + e);
		}
	}
	

	/**
	 * This method initializes configurationPanel	
	 * 	
	 * @return com.configuration.demo.ConfigurationPanel	
	 */
	private JPanel getConfigurationPanel() {
		if (configurationPanel == null) {
			mainPanel = new BackgroundPanel();
			configurationPanel = new ConfigurationPanel(this,configuration);
			mainPanel.add(configurationPanel.buildPanel());
		}
		return mainPanel;
	}
	
	/**
	 * 
	 * @return Configured object, null if it's not configured
	 */
	public ConfigurationFacade getConfiguration(){
		return configurationPanel.configuration();
	}

}  //  @jve:decl-index=0:visual-constraint="10,10" 
