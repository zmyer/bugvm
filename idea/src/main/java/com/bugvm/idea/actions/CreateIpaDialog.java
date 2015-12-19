/*
 * Copyright (C) 2015 RoboVM AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package com.bugvm.idea.actions;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDialog;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;
import com.bugvm.compiler.config.Arch;
import com.bugvm.compiler.target.ios.DeviceType;
import com.bugvm.compiler.target.ios.ProvisioningProfile;
import com.bugvm.compiler.target.ios.SigningIdentity;
import com.bugvm.idea.BugVmPlugin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by badlogic on 01/04/15.
 */
public class CreateIpaDialog extends DialogWrapper {
    private static final String MODULE_NAME = "bugvm.ipaConfig.moduleName";
    private static final String SIGNING_IDENTITY = "bugvm.ipaConfig.signingIdentity";
    private static final String PROVISIONING_PROFILE = "bugvm.ipaConfig.provisioningProfile";
    private static final String ARCHS = "bugvm.ipaConfig.archs";
    private static final String DESTINATION_DIR = "bugvm.ipaConfig.destinationDir";

    private static final String ARCHS_ALL = "All - 32-bit (thumbv7) + 64-bit (arm64)";
    private static final String ARCHS_32BIT = "32-bit (thumbv7)";
    private static final String ARCHS_64BIT = "64-bit (arm64)";
    private JPanel panel;
    private JComboBox archs;
    private JComboBox signingIdentity;
    private JComboBox provisioningProfile;
    private JButton browseButton;
    private JTextField destinationDir;
    private JComboBox module;
    private Project project;

    protected CreateIpaDialog(@Nullable Project project) {
        super(project);
        this.project = project;
        init();
        setTitle("BugVM: Create IPA");
        populateControls();
    }

    private void populateControls() {
        PropertiesComponent properties = PropertiesComponent.getInstance(project);
        String configModule = properties.getValue(MODULE_NAME, "");
        String configSigning = properties.getValue(SIGNING_IDENTITY, "");
        String configProvisioning = properties.getValue(PROVISIONING_PROFILE, "");
        String configDestDir = properties.getValue(DESTINATION_DIR, "");
        String configArchs = properties.getValue(ARCHS, "");

        for(Module module: BugVmPlugin.getBugVmModules(project)) {
            this.module.addItem(module.getName());
            if(module.getName().equals(configModule)) {
                this.module.setSelectedIndex(this.module.getItemCount()-1);
            }
        }

        // populate signing identities
        for(SigningIdentity identity: SigningIdentity.list()) {
            signingIdentity.addItem(identity.getName());
            if(identity.getName().equals(configSigning)) {
                this.signingIdentity.setSelectedIndex(this.signingIdentity.getItemCount()-1);
            }
        }

        // populate provisioning profiles
        for(ProvisioningProfile profile: ProvisioningProfile.list()) {
            provisioningProfile.addItem(profile.getName());
            if(profile.getName().equals(configProvisioning)) {
                this.provisioningProfile.setSelectedIndex(this.provisioningProfile.getItemCount()-1);
            }
        }

        // populate architectures
        for(String arch: new String[] { ARCHS_ALL, ARCHS_32BIT, ARCHS_64BIT }) {
            archs.addItem(arch);
            if(arch.equals(configProvisioning)) {
                this.archs.setSelectedIndex(this.archs.getItemCount()-1);
            }
        }

        if(!configDestDir.isEmpty()) {
            destinationDir.setText(configDestDir);
        }

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileChooserDialog fileChooser = FileChooserFactory.getInstance()
                        .createFileChooser(new FileChooserDescriptor(true, false, false, false, false, false) {
                            @Override
                            public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
                                return file.isDirectory();
                            }

                            @Override
                            public boolean isFileSelectable(VirtualFile file) {
                                return file.isDirectory();
                            }
                        }, null, panel);
                VirtualFile[] dir = fileChooser.choose(project);
                if (dir != null && dir.length > 0) {
                    destinationDir.setText(dir[0].getCanonicalPath());
                }
            }
        });
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel;
    }

    @Nullable
    @Override
    protected ValidationInfo doValidate() {
        if(this.module.getSelectedIndex() == -1) {
            return new ValidationInfo("No BugVM module selected");
        }
        if(this.destinationDir.getText() == null || this.destinationDir.getText().length() == 0) {
            return new ValidationInfo("Specify a destination directory");
        }
        File destDir = new File(this.destinationDir.getText());
        if(!destDir.exists()) {
            return new ValidationInfo("Destination directory does not exist");
        }
        if(!destDir.isDirectory()) {
            return new ValidationInfo("Destination is not a directory");
        }
        if(signingIdentity.getItemCount() == 0) {
            return new ValidationInfo("No signing identity found");
        }
        if(provisioningProfile.getItemCount() == 0) {
            return new ValidationInfo("No provisioning profile found");
        }
        return null;
    }

    public CreateIpaAction.IpaConfig getIpaConfig() {
        saveProperties();
        Module module = ModuleManager.getInstance(project).findModuleByName(this.module.getSelectedItem().toString());
        String signingIdentity = this.signingIdentity.getSelectedItem().toString();
        String provisioningProile = this.provisioningProfile.getSelectedItem().toString();
        List<Arch> archs = new ArrayList<Arch>();
        if(this.archs.getSelectedItem().toString().equals(ARCHS_ALL)) {
            archs.add(Arch.thumbv7);
            archs.add(Arch.arm64);
        } else if(this.archs.getSelectedItem().toString().equals(ARCHS_32BIT)) {
            archs.add(Arch.thumbv7);
        } else if(this.archs.getSelectedItem().toString().equals(ARCHS_ALL)) {
            archs.add(Arch.arm64);
        }
        return new CreateIpaAction.IpaConfig(module, new File(this.destinationDir.getText()), signingIdentity, provisioningProile, archs);
    }

    private void saveProperties() {
        PropertiesComponent properties = PropertiesComponent.getInstance(project);
        properties.setValue(MODULE_NAME, module.getSelectedItem().toString());
        properties.setValue(SIGNING_IDENTITY, signingIdentity.getSelectedItem().toString());
        properties.setValue(PROVISIONING_PROFILE, provisioningProfile.getSelectedItem().toString());
        properties.setValue(ARCHS, archs.getSelectedItem().toString());
        properties.setValue(DESTINATION_DIR, destinationDir.getText());
    }
}
