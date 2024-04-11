package views;

import com.toedter.calendar.JDateChooser;
import controller.MainController;
import dao.impl.AccountDaoImpl;
import dao.impl.EmployeeDaoImpl;
import dao.impl.RoleDaoImpl;
import models.Account;
import models.Employee;
import models.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import util.GeneratorIDAuto;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static javax.swing.JColorChooser.showDialog;

public class EmployeeManagementView extends JPanel implements KeyListener, MouseListener, ActionListener {



    private JDateChooser chooserNgaySinh;
    private JTextField txtTenNV;
    private JTextField txtsdt;
    private JTextField txtEmail;
    private JTextField txtDiaChi;
    private JTextField txtTimKiem;
    private JTextField txtID;
    private JLabel lbTenNV;
    private JLabel lbsdt;
    private JLabel lbEmail;
    private JLabel lbDiaChi;
    private JLabel lbNgaySinh;
    private JLabel lbGioiTinh;
    private JLabel lbID;

    private JLabel lbTimKiem;
    private JRadioButton rbNam;
    private JRadioButton rbNu;
    private JComboBox<Object> cbTrangThai,cbChucVu;
    private JLabel lbChucVu;
    private JLabel lbTrangThai;
    private JTable tableNhanVien;
    private DefaultTableModel modelNhanVien;
    private JButton btnMoBangThemChucVu;
    private JButton btnThemChucVu;
    private JButton btnXoaChucVu;
    private JButton btnThemNV;
    private JButton btnCapNhatNV;
    private JButton btnXoaNV;
    private JButton btnLamMoi;
    private JButton btnXemTatCa;
    private JButton btnXuatExecl;
    private JFrame frameThemChucVu;
    private JDialog dlogThemChucVu;
    private JLabel lbIdChucVu;
    private JTextField txtIdChucVu;
    private JLabel lbTenChucVu;
    private JTextField txtTenChucVu;
    private JTable tbChucVu;
    private DefaultTableModel modelChucVu;
    private SimpleDateFormat dfNgaySinh;
    private MainController mainController;
    private GeneratorIDAuto autoID;

    private JTabbedPane tabbedPane;
    public EmployeeManagementView() {
        dfNgaySinh = new SimpleDateFormat("dd/MM/yyyy");
        mainController=new MainController();
        autoID=new GeneratorIDAuto();
        Employee employee = new Employee();
        Role role=new Role();
        Account account = new Account();
        setLayout(new BorderLayout());
        JPanel pnNouth = new JPanel(new BorderLayout());
        JPanel pnSounth = new JPanel(new BorderLayout());
        JPanel pnTitle = new JPanel();
        JPanel pnInfo = new JPanel(new GridLayout(5, 1, 5, 5));
        JPanel pnChucNang = new JPanel(new GridLayout(1, 4, 10, 10));
        JPanel pnTimKiem = new JPanel(new GridLayout(1, 3, 10, 10));
        JPanel pntbNV = new JPanel();
//		Tiêu đề
        JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN SỰ");
        lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTieuDe.setForeground(new Color(26, 102, 227));
        pnTitle.add(lblTieuDe);
        pnNouth.add(pnTitle, BorderLayout.NORTH);
        add(pnNouth, BorderLayout.NORTH);

        lbID = new JLabel("ID nhân sự:");
        lbTenNV = new JLabel("Tên nhân sự:");
        txtTenNV = new JTextField();
        lbsdt = new JLabel("Số điện thoại:");
        txtsdt = new JTextField();
        lbEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        lbDiaChi = new JLabel("Địa chỉ:");
        txtDiaChi = new JTextField();
        lbChucVu = new JLabel("Chức vụ:");
        lbTrangThai = new JLabel("Trạng thái làm việc:");
        lbGioiTinh = new JLabel("Giới tính:");
        lbNgaySinh = new JLabel("Ngày sinh:");
        txtID = new JTextField();
        txtID.setEditable(false);
        cbChucVu=new JComboBox<>();
        cbTrangThai = new JComboBox<Object>(new Object[]{"Đang làm việc", "Đã nghỉ việc"});
        btnMoBangThemChucVu=new JButton("THÊM CHỨC VỤ MỚI");

        chooserNgaySinh = new JDateChooser();
        chooserNgaySinh.getCalendarButton().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        chooserNgaySinh.setBounds(100, 310, 200, 40);
        chooserNgaySinh.setDateFormatString("dd/MM/yyyy");
        chooserNgaySinh.setBorder(new LineBorder(new Color(114, 23, 153), 1, true));
        chooserNgaySinh.setFont(new Font("SansSerif", Font.PLAIN, 15));
        chooserNgaySinh.getCalendarButton().setPreferredSize(new Dimension(30, 24));
        chooserNgaySinh.getCalendarButton().setBackground(new Color(102, 0, 153));
        chooserNgaySinh.getCalendarButton().setToolTipText("Chọn ngày sinh");
        rbNam = new JRadioButton("Nam");
        rbNu = new JRadioButton("Nữ");
        //Tạo button groud giới tính
        ButtonGroup groupGT = new ButtonGroup();
        groupGT.add(rbNam);
        groupGT.add(rbNu);
        pnInfo.add(lbID);
        pnInfo.add(txtID);
        pnInfo.add(lbTenNV);
        pnInfo.add(txtTenNV);
        pnInfo.add(lbsdt);
        pnInfo.add(txtsdt);
        pnInfo.add(lbEmail);
        pnInfo.add(txtEmail);
        pnInfo.add(lbDiaChi);
        pnInfo.add(txtDiaChi);
        pnInfo.add(lbNgaySinh);
        pnInfo.add(chooserNgaySinh);
        pnInfo.add(lbTrangThai);
        pnInfo.add(cbTrangThai);

        pnInfo.add(lbChucVu);
        pnInfo.add(cbChucVu);
        pnInfo.add(lbGioiTinh);
        pnInfo.setBorder(BorderFactory.createTitledBorder("Thông tin nhân sự"));

        pnInfo.add(rbNam);
        pnInfo.add(rbNu);
        pnInfo.add(btnMoBangThemChucVu);

        pnNouth.add(pnInfo, BorderLayout.CENTER);

        lbTimKiem = new JLabel("Tìm kiếm nhân sự:");
        txtTimKiem = new JTextField();
        pnTimKiem.add(lbTimKiem);
        pnTimKiem.add(txtTimKiem);
        ImageIcon iconThem = new ImageIcon(getClass().getResource("/icons/add.png"));
        ImageIcon iconCapNhat = new ImageIcon(getClass().getResource("/icons/capnhat.png"));
        ImageIcon iconLamMoi = new ImageIcon(getClass().getResource("/icons/lammoi.png"));
        ImageIcon iconXoa = new ImageIcon(getClass().getResource("/icons/xoa.png"));
        btnThemNV = new JButton("THÊM NHÂN SỰ");
        btnThemNV.setIcon(iconThem);
        btnCapNhatNV = new JButton("CẬP NHẬT THÔNG TIN NHÂN SỰ");
        btnCapNhatNV.setIcon(iconCapNhat);
        btnXoaNV = new JButton("XÓA NHÂN SỰ");
        btnXuatExecl = new JButton("XUẤT EXCEL");
        btnXoaNV.setIcon(iconXoa);
        btnLamMoi = new JButton("LÀM MỚI");
        btnLamMoi.setIcon(iconLamMoi);
        btnXemTatCa = new JButton("XEM TẤT CẢ");
        btnMoBangThemChucVu.setIcon(iconThem);
        pnChucNang.add(btnThemNV);
        pnChucNang.add(btnCapNhatNV);
        pnChucNang.add(btnXoaNV);
        pnChucNang.add(btnLamMoi);
        pnChucNang.add(btnXuatExecl);
        pnChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
        pnNouth.add(pnChucNang, BorderLayout.SOUTH);
        add(pnSounth, BorderLayout.CENTER);
        pnSounth.add(pnTimKiem, BorderLayout.NORTH);
        pnTimKiem.add(btnXemTatCa);

        modelNhanVien = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa các ô trong bảng
            }
        };
        tableNhanVien = new JTable();
        modelNhanVien.addColumn("ID nhân sự");
        modelNhanVien.addColumn("Tên nhân sự");
        modelNhanVien.addColumn("Số điện thoại");
        modelNhanVien.addColumn("Email");
        modelNhanVien.addColumn("Địa chỉ");
        modelNhanVien.addColumn("Ngày sinh");
        modelNhanVien.addColumn("Giới tính");
        modelNhanVien.addColumn("Chức vụ");
        modelNhanVien.addColumn("Trạng thái làm việc");
        tableNhanVien.setModel(modelNhanVien);
        JScrollPane scrollPane = new JScrollPane(tableNhanVien);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách nhân sự"));
        pnSounth.add(scrollPane, BorderLayout.CENTER);
        //Cửa số thêm chức vụ
        JPanel pnNhapChucVu = new JPanel(new GridLayout(2,1,10,10));
        JPanel pnButton = new JPanel(new GridLayout(1,4,5,5));
        lbIdChucVu=new JLabel("ID:");
        lbTenChucVu=new JLabel("Tên chức vụ:");
        txtIdChucVu=new JTextField();
        txtIdChucVu.setEditable(false);
        txtTenChucVu=new JTextField();
        pnNhapChucVu.add(lbIdChucVu);
        pnNhapChucVu.add(txtIdChucVu);
        pnNhapChucVu.add(lbTenChucVu);
        pnNhapChucVu.add(txtTenChucVu);
        //Tạo bảng chức vụ trong cửa sổ
        tbChucVu = new JTable();
        modelChucVu = new DefaultTableModel();
        modelChucVu.addColumn("ID");
        modelChucVu.addColumn("Tên chức vụ");
        tbChucVu.setModel(modelChucVu);
        JScrollPane scrollTbChucVu = new JScrollPane(tbChucVu);
        btnThemChucVu=new JButton("THÊM");
        btnXoaChucVu=new JButton("XÓA");
        pnButton.add(btnThemChucVu);
        pnButton.add(btnXoaChucVu);
        JPanel pnChucVu = new JPanel(new BorderLayout());
        pnChucVu.add(pnNhapChucVu,BorderLayout.NORTH);
        pnChucVu.add(scrollTbChucVu,BorderLayout.CENTER);
        pnChucVu.add(pnButton,BorderLayout.SOUTH);


        frameThemChucVu=new JFrame();
        dlogThemChucVu= new JDialog( frameThemChucVu, "CHỨC VỤ", null);
        dlogThemChucVu.add(pnChucVu);

        txtID.setToolTipText("ID + Date + XXXX");
        txtTenNV.setToolTipText("Chỉ nhận chữ");
        txtEmail.setToolTipText("Điền mail hợp lệ");
        txtsdt.setToolTipText("10 số bắt đầu bằng 0 hoặc +84");
        txtDiaChi.setToolTipText("Nhận số và chữ");
        rbNam.setToolTipText("Chọn 1 trong 2");
        rbNu.setToolTipText("Chọn 1 trong 2");
        chooserNgaySinh.setToolTipText("Trước ngày hiện tại");

        chooserNgaySinh.setDate(new java.util.Date());
        btnLamMoi.addActionListener(this);
        btnThemNV.addActionListener(this);
        btnCapNhatNV.addActionListener(this);
        btnXoaNV.addActionListener(this);
        btnXemTatCa.addActionListener(this);
        btnXuatExecl.addActionListener(this);
        btnMoBangThemChucVu.addActionListener(this);
        btnThemChucVu.addActionListener(this);
        tableNhanVien.addMouseListener(this);
        tableNhanVien.addKeyListener(this);
        txtTimKiem.addKeyListener(this);
        txtTenNV.addKeyListener(this);
        txtDiaChi.addKeyListener(this);
        txtEmail.addKeyListener(this);
        txtsdt.addKeyListener(this);
        txtTenNV.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                txtID.setText(autoID.autoID("E"));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        loadComboxBoxRole();
        loadDataRoleTabble();
        loadData();
    }
    private void addRole(){
        String tenChucVu = txtTenChucVu.getText();
        Role role=new Role(tenChucVu);
        mainController.addRole(role);
        JOptionPane.showMessageDialog(this,"Thêm thành công");
        modelChucVu.addRow(new Object[] {role.getIdRole(),role.getRoleName()});
        loadComboxBoxRole();
        loadDataRoleTabble();
        txtTenChucVu.setText("");
    }
    private void loadDataRoleTabble() {
        modelChucVu.setRowCount(0);
        for (Role role : mainController.getAllRole() ) {
            modelChucVu.addRow(new Object[] { role.getIdRole(),role.getRoleName()
            });

        }
    }
    private void addEmployee() {

        String id = txtID.getText();
        String ten = txtTenNV.getText();
        String email= txtEmail.getText();
        String sdt=txtsdt.getText();
        String diaChi=txtDiaChi.getText();
        java.util.Date date = chooserNgaySinh.getDate();
        Date ngaySinh = new Date(date.getYear(), date.getMonth(), date.getDate());
        String GioiTinh=rbNam.isSelected()?"Nam":"Nữ";
        String trangThai=cbTrangThai.getSelectedItem().toString();

        String chucVuSelected=cbChucVu.getSelectedItem().toString();
        Role chucVu= mainController.findRoleByText(chucVuSelected);
        Date ngayLap = new Date(System.currentTimeMillis());
        String matkhau="1111";
        Employee employee = new Employee(id,ten,sdt,diaChi,email,ngaySinh,GioiTinh,trangThai,chucVu);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        String hasdPassword = passwordEncoder.encode(matkhau);
        Account tk=new Account(employee,matkhau,ngayLap);
        System.out.println(hasdPassword);

        if(valiDate()) {
            mainController.addEmployee(employee);
            mainController.addAccount(tk);
            modelNhanVien.addRow(new Object[] {employee.getIdEmployee(), ten, sdt,email, diaChi,dfNgaySinh.format(employee.getBirth()),employee.getGender(),chucVu.getRoleName(),trangThai });
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        }
    }
    private void loadData() {
        modelNhanVien.setRowCount(0);
        for (Employee employee : mainController.getAllEmployees()) {
            modelNhanVien.addRow(new Object[] { employee.getIdEmployee(), employee.getName(),employee.getPhone(), employee.getEmail(),employee.getAddress(),dfNgaySinh.format(employee.getBirth()),employee.getGender(),employee.getRole().getRoleName(),employee.getStatus()
            });

        }
    }
    private void updateEmployee() {
        String id = modelNhanVien.getValueAt(tableNhanVien.getSelectedRow(), 0).toString();
        String ten = txtTenNV.getText();
        String diaChi = txtDiaChi.getText();
        String soDienThoai = txtsdt.getText();
        String email = txtEmail.getText();
        java.util.Date date = chooserNgaySinh.getDate();
        Date ngaySinh = new Date(date.getYear(), date.getMonth(), date.getDate());
        String gioiTinh=rbNam.isSelected()?"Nam":"Nữ";
        String trangThaiValue=cbTrangThai.getSelectedItem().toString();
        String chucVuSelected =cbChucVu.getSelectedItem().toString();
        Role chucVu = mainController.findRoleByText(chucVuSelected);
        Employee employee=new Employee(id,ten,soDienThoai,diaChi,email,ngaySinh,gioiTinh,trangThaiValue,chucVu);
        if(valiDate()) {
            try {
                mainController.updateEmployee(employee);
                loadData();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        }

    }
    public boolean valiDate() {

        String ten = txtTenNV.getText().trim();


        Boolean gioiTinh;
        if(rbNam.isSelected() || rbNu.isSelected()) {
            gioiTinh=true;
        }
        else {
            gioiTinh=false;
        }
        java.util.Date ngaySinh = chooserNgaySinh.getDate();
        java.util.Date ngayHienTai=new java.util.Date();
        String diaChi = txtDiaChi.getText().trim();
        String email = txtEmail.getText().trim();
        String soDienThoai = txtsdt.getText().trim();


        if(ten.equals("") || diaChi.equals("")|| email.equals("") || soDienThoai.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ!", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            txtTenNV.requestFocus();

            return false;
        }
        if (!(ten.length() > 0 && ten.matches("^[A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*(?:[ ][A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđ]*)*$"))) {
            JOptionPane.showMessageDialog(this, "Tên phải viết hoa và không chứa số", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            txtTenNV.selectAll();
            txtTenNV.requestFocus();
            return false;
        }

        if (!(diaChi.length() > 0 && diaChi.matches("^[^!@#$%^&*()+-]*$"))) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa kí tự đặc biệt", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            txtDiaChi.requestFocus();
            txtDiaChi.selectAll();
            return false;
        }
        if (!(email.length() > 0 && email.matches("^[A-Za-z][A-Za-z0-9@.]*$"))) {
            JOptionPane.showMessageDialog(this, "Email phải bắt đầu bằng chữ và không được chứa kí tự đặc biệt", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            txtEmail.selectAll();
            return false;
        }
        if (!(ngaySinh!=null  && (ngayHienTai.getYear()-ngaySinh.getYear()>18))) {
            JOptionPane.showMessageDialog(this, "Nhân viên chưa đủ 18 tuổi", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            chooserNgaySinh.requestFocus();

            return false;
        }
        if(!(soDienThoai.length()>0 && soDienThoai.matches("^(0|\\+84)[0-9]{9}$")))
        {
            JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 số và bắt đầu bằng 0 hoặc +84", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);
            txtsdt.requestFocus();
            txtsdt.selectAll();
            return false;
        }
        if(gioiTinh==false)
        {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính", "Thông báo",
                    JOptionPane.WARNING_MESSAGE);

            return false;
        }
        return true;
    }
    private void showDialog(JFrame FrameParent,JDialog dialog) {

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    private void loadComboxBoxRole() {
        cbChucVu.removeAllItems();
        for (Role role : mainController.getAllRole()) {
            cbChucVu.addItem(role.getRoleName());
        }
    }
    private void reload() {
        txtID.setText("");
        txtTenNV.setText("");
        txtDiaChi.setText("");
        txtsdt.setText("");
        txtEmail.setText("");
        rbNam.setSelected(true);
        rbNu.setSelected(false);
        cbChucVu.setSelectedItem(false);
        cbTrangThai.setSelectedItem("Đang làm việc");
        chooserNgaySinh.setDate(new java.util.Date());
        txtTenNV.requestFocus();
        loadData();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if(o.equals(btnMoBangThemChucVu)) {
            showDialog(frameThemChucVu,dlogThemChucVu);
        }
        if(o.equals(btnThemChucVu)) {
            addRole();
        }
        if(o.equals(btnThemNV)) {
            addEmployee();
        }
        if(o.equals(btnCapNhatNV)) {
            updateEmployee();
        }
        if(o.equals(btnLamMoi)) {
            reload();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tableNhanVien.getSelectedRow();
        txtID.setText(modelNhanVien.getValueAt(row, 0).toString());
        txtTenNV.setText(modelNhanVien.getValueAt(row, 1).toString());
        txtsdt.setText(modelNhanVien.getValueAt(row, 2).toString());
        txtEmail.setText(modelNhanVien.getValueAt(row, 3).toString());
        txtDiaChi.setText(modelNhanVien.getValueAt(row, 4).toString());
        String ngaySinh=modelNhanVien.getValueAt(row, 5).toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date valueNgaySinh = null;
        try {
            valueNgaySinh =dateFormat.parse(ngaySinh);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        chooserNgaySinh.setDate(valueNgaySinh);
        rbNam.setSelected(modelNhanVien.getValueAt(row, 6).toString()=="Nam");
        rbNu.setSelected(modelNhanVien.getValueAt(row,6).toString()=="Nữ");
        cbChucVu.setSelectedItem(modelNhanVien.getValueAt(row,7).toString());
        cbTrangThai.setSelectedItem(modelNhanVien.getValueAt(row,8).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}