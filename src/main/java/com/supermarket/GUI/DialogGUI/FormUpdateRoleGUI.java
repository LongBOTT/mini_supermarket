package com.supermarket.GUI.DialogGUI;

import com.supermarket.BLL.RoleBLL;
import com.supermarket.DTO.Role;
import com.supermarket.GUI.components.DataTable;
import com.supermarket.GUI.components.RoundedPanel;

public class FormUpdateRoleGUI extends DialogForm {
    private final RoleBLL roleBLL = new RoleBLL();
    private DataTable dataTable;
    private RoundedPanel formDetail;

    public FormUpdateRoleGUI(Role role) {
    }
}
