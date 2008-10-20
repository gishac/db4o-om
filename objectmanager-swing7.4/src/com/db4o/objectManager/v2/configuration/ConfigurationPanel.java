/* Copyright (C) 2004 - 2007 db4objects Inc. http://www.db4o.com */
/**
 * User: gishac
 * Date: Feb 02, 2008
 * Time: 08:19:23 PM
 */

package com.db4o.objectManager.v2.configuration;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import com.db4o.Db4o;
import com.db4o.config.Configuration;
import com.db4o.objectManager.v2.Dashboard;
import com.db4o.objectManager.v2.uiHelper.OptionPaneHelper;
import com.db4o.objectmanager.api.helpers.SerializerHelper;
import com.db4o.objectmanager.configuration.*;
import com.jgoodies.forms.builder.*;
import com.jgoodies.forms.layout.*;
import com.jgoodies.looks.Fonts;

public class ConfigurationPanel {

	private JComboBox _encryptionProvider;
	private ConfigurationProvider _configurationProvider;
	private JCheckBox _unicode;
	private JCheckBox _lockDatabase;
	private JCheckBox _callConstructors;
	private JCheckBox _saveConfigurationBox;
	private JTextField _encryptionPassword;
	private JButton _buildConfiguration;
	private ConfigurationFacade _configuration;
	private JDialog _container;
	private JPanel panel;

	// private Component dashboard;

	/**
	 * This method initializes
	 * 
	 */
	public ConfigurationPanel() {
		super();
		initialize();		
	}
	
	/**
	 * This method initializes
	 * @param configuration In memory configuration or stored configuration
	 */
	public ConfigurationPanel(ConfigurationFacade configuration){
		this();
		_configuration = configuration;
	}
	
	/**
	 * 
	 * @param container The form which contains the panel
	 */
	public ConfigurationPanel(JDialog container){
		this();
		_container = container;
	}
	
	/**
	 * 
	 * @param container The form which contains the panel
	 */
	public ConfigurationPanel(JDialog container,ConfigurationFacade currentConfiguration){
		this(container);
		this.configuration(currentConfiguration);
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		_configurationProvider = new ConfigurationProvider();
	}

	/**
	 * Get the configuration object built by the configuration window
	 * @return
	 */
	public ConfigurationFacade configuration(){
		return this._configuration;
	}
	
	/**
	 * Set the configuration object
	 * @param configurationConfigurationFacade
	 */
	private void configuration(ConfigurationFacade configuration){
		this._configuration = configuration;
	}
	
	/**
	 * Create all the UI elements for the configuration panel
	 * 
	 * @return
	 */
	public JPanel buildPanel() {
		FormLayout layout = new FormLayout(
				"right:max(40dlu;pref), 3dlu, 120dlu, 7dlu,"
						+ "right:max(10dlu;pref), 3dlu, 80dlu", "");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();

		JLabel descriptionLabel = new JLabel("Set Configuration");
		descriptionLabel.setFont(Fonts.WINDOWS_VISTA_96DPI_LARGE);
		descriptionLabel.setForeground(Color.white);
		builder.append(descriptionLabel);

		builder.nextLine();

		builder.append(getLabel("Unicode: "), unicode());

		builder.nextLine();

		builder.append(getLabel("Lock Database file: "), lockDatabase());

		builder.nextLine();

		builder.append(getLabel("Call Constructors: "), callConstructors());

		builder.nextLine();

		builder.append(getLabel("Encryption Provider: "), encryptionProvider(),findEncryptionProvider());

		builder.nextLine();

		builder.append(getLabel("Encryption Password: "), encryptionPassword());

		builder.nextLine();
		
		builder.append(buildConfiguration(),cancel());
		
		builder.nextLine();
		
		builder.append("",saveConfigurationBox());

		panel = builder.getPanel();
		panel.setOpaque(false);
		applyConfiguration();
		return panel;
	}

	/**
	 * Create the checkbox to set unicode configuration
	 * 
	 * @return
	 */
	private JCheckBox unicode() {
		_unicode = new JCheckBox();
		_unicode.setOpaque(false);
		_unicode.setSelected(true);
		return _unicode;
	}

	/**
	 * Create the checkbox to set lock database file configuration
	 * 
	 * @return
	 */
	private JCheckBox lockDatabase() {
		_lockDatabase = new JCheckBox();
		_lockDatabase.setOpaque(false);
		return _lockDatabase;
	}

	/**
	 * Create the checkbox to set callConstructor configuration
	 * 
	 * @return
	 */
	private JCheckBox callConstructors() {
		_callConstructors = new JCheckBox();
		_callConstructors.setOpaque(false);
		_callConstructors.setSelected(true);
		return _callConstructors;
	}

	/**
	 * Create the combobox to select the encription provider
	 * 
	 * @return
	 */
	private JComboBox encryptionProvider() {
		_encryptionProvider = new JComboBox();
		return _encryptionProvider;
	}
	
	/**
	 * Create the checkbox to set if the configuration will be saved
	 * 
	 * @return
	 */
	private JCheckBox saveConfigurationBox() {
		_saveConfigurationBox = new JCheckBox();
		_saveConfigurationBox.setText("Save Configuration");
		_saveConfigurationBox.setOpaque(false);
		return _saveConfigurationBox;
	}
	
	/**
	 * Builds a label to append in the pannel
	 * @param text
	 * @return
	 */
	private JLabel getLabel(String text){
		JLabel label = new JLabel(text);
		label.setForeground(Color.black);
		return label;
	}

	/**
	 * Create the button to search encryption provider
	 * 
	 * @return
	 */
	private JButton findEncryptionProvider() {
		JButton findEncryptionProvider = new JButton("...");
		findEncryptionProvider.setSize(new Dimension(25,25));
		findEncryptionProvider.setMaximumSize(new Dimension(25,25));
		findEncryptionProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jarFileChooser = jarFileChooser();
				int dialogResult = jarFileChooser.showDialog(_container, "Ok");
				if (dialogResult == JFileChooser.APPROVE_OPTION) {
					File file = jarFileChooser.getSelectedFile();
					String path = file.getPath();
					loadEncryptionProvider(path);
				}
			}
		});
		return findEncryptionProvider;
	}
	
	/**
	 * 
	 * @param path
	 */
	private boolean loadEncryptionProvider(String path) {
		if(!new File(path).exists())
			return false;
		JarLoader jarLoader = new JarLoader(path);
		EncryptionProviderComboBoxModel model = new EncryptionProviderComboBoxModel(
				jarLoader.getClasses(path));
		_encryptionProvider.setModel(model);
		if(model.getDataSource().size() > 0)
			_encryptionProvider.setSelectedIndex(0);		
		_encryptionProvider.setRenderer(new EncryptionProviderComboBoxRenderer());
		_configurationProvider.encryptionProviderPath(path);
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	private JButton cancel(){
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				_container.setVisible(false);
				_container.dispose();
			}
			
		});
		return cancel;
	}
	

	/**
	 * Create the select file dialog, to find the jar file to be used as
	 * encryption provider
	 * 
	 * @return
	 */
	private JFileChooser jarFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select your Encryption Provider");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Jar Files", "jar");
		fileChooser.setFileFilter(filter);
		fileChooser.setMultiSelectionEnabled(false);
		return fileChooser;
	}

	/**
	 * Create the textfield for the password
	 * 
	 * @return
	 */
	private JTextField encryptionPassword() {
		_encryptionPassword = new JTextField();
		return _encryptionPassword;
	}

	/**
	 * Build the Configuration with the selected parameters
	 * @return
	 */
	private JButton buildConfiguration() {
		_buildConfiguration = new JButton("Ok");
		_buildConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				_configurationProvider.unicodeEnabled(_unicode.isSelected());
				_configurationProvider.lockDatabase(_lockDatabase.isSelected());
				_configurationProvider.callConstructors(_callConstructors.isSelected());
				_configurationProvider.encryptionPassword(_encryptionPassword.getText());
				try {
					_configurationProvider.encryptionProvider(
							EncryptionProviderHandler.getEncryptionProvider((Class) _encryptionProvider.getSelectedItem(),
									_encryptionPassword.getText()));					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Can't create an instance of the Encryption Provider");
				}
				configuration(_configurationProvider.buildConfiguration());
				saveConfiguration(configuration());
				_container.setVisible(false);
				_container.dispose();
			}

		});
		return _buildConfiguration;
	}
	
	/**
	 * 
	 * @param configuration
	 */
	private void saveConfiguration(ConfigurationFacade configuration){
		if(!_saveConfigurationBox.isSelected())
			return;
		try {
			SerializerHelper.Serialize(configuration, ConfigurationFacade.CONFIGURATION_FILE_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Applies the configuration if it exists
	 */
	public void applyConfiguration()
	{
		if(_configuration == null)
			return;
		_unicode.setSelected(_configuration.unicode());
		_callConstructors.setSelected(_configuration.callConstructors());
		_lockDatabase.setSelected(_configuration.lockDatabaseFile());
		_encryptionPassword.setText(_configuration.encryptionPassword());
		if(_configuration.encriptionProviderPath() != null && _configuration.encriptionProviderPath().trim().length() > 0){
			if(!loadEncryptionProvider(_configuration.encriptionProviderPath())){
				OptionPaneHelper.showErrorMessage(_container, "The file " + _configuration.encriptionProviderPath() + "could not be opened, please load again your encryption provider.", "Encryption provider not found");
			}				
		}
	}
	
}
