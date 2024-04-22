package views;

import controller.MainController;
import controller.MenuItem;
import models.Employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ManagerHomeView extends JFrame {
    private JScrollPane jScrollPane1;
    private JPanel menus;
    private JPanel panelBody;
    private JPanel panelHeader;
    private JPanel panelMenu;
    private JPanel paneCu;
    private JLabel lbID;
    private JLabel lbName;
    private Employee employee;
    private MainController mainController;
    private JLabel lblDate;

//    public static void main(String[] args) {
//        new ManagerHomeView(new Employee()).setVisible(true);
//    }

    public ManagerHomeView(Employee e) {
        this.employee = e;
        mainController = new MainController();
        setTitle("Quản trị");
        setSize(new Dimension(871, 473));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/logo.png"));
        setIconImage(icon.getImage());
        paneCu = new HomeView();
        panelBody.add(paneCu);
        execute();
        menus.setBackground(new Color(153, 255, 255));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(ManagerHomeView.this, "Bạn có chắc chắn muốn đóng cửa sổ?",
                        "Xác nhận đóng cửa sổ", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

    }

    private void execute() {

        ImageIcon iconSetting = new ImageIcon(getClass().getResource("/icons/settings.png"));
        ImageIcon iconSubMenu = new ImageIcon(getClass().getResource("/icons/plus.png"));
        ImageIcon iconKH = new ImageIcon(getClass().getResource("/icons/KH.png"));
        ImageIcon iconNV = new ImageIcon(getClass().getResource("/icons/NV.png"));
        ImageIcon iconSP = new ImageIcon(getClass().getResource("/icons/SP.png"));
        ImageIcon iconKM = new ImageIcon(getClass().getResource("/icons/KM.png"));
        ImageIcon iconHD = new ImageIcon(getClass().getResource("/icons/bill.png"));
        ImageIcon iconTK = new ImageIcon(getClass().getResource("/icons/TK.png"));
        ImageIcon iconDX = new ImageIcon(getClass().getResource("/icons/DX.png"));

        ImageIcon iconBH = new ImageIcon(getClass().getResource("/icons/banhang.png"));
        MenuItem QLBH = new MenuItem(iconBH, "Quản lý bán hàng", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                switchToPanel(new SaleManagerView(employee));

            }
        });
        MenuItem subQLLoaiSanPham = new MenuItem(iconSubMenu, "Loại sản phẩm", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new ProductTypeView());
            }
        });
        MenuItem subQLNhaCungCap = new MenuItem(iconSubMenu, "Nhà cung cấp", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new SupplierView());

            }
        });
        MenuItem subQLTacGia = new MenuItem(iconSubMenu, "Tác giả", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new AuthorView());
            }
        });

        MenuItem subQLTheLoai = new MenuItem(iconSubMenu, "Thể loại", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new CategoryView());
            }
        });
        MenuItem DangXuat = new MenuItem(iconDX, "Đăng xuất", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int hoiNhac = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất", "Cảnh báo",
                        JOptionPane.YES_NO_OPTION);
                if (hoiNhac == JOptionPane.YES_OPTION) {
                    LoginView view = new LoginView();
                    view.setVisible(true);
                    dispose();
                }

            }
        });
        MenuItem QLSP = new MenuItem(iconSP, "Sản phẩm", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new MerchandiseView());

            }
        }, subQLLoaiSanPham, subQLNhaCungCap, subQLTacGia, subQLTheLoai);

        MenuItem QLNV = new MenuItem(iconNV, "Quản lý nhân sự", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new EmployeeManagementView());

            }
        });
        MenuItem QLKH = new MenuItem(iconKH, "Quản lý khách hàng", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                switchToPanel(new CustomerManagementView());

            }
        });

        MenuItem KM = new MenuItem(iconKM, "Chương trình khuyến mãi", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new PromotionView());
            }
        });
        MenuItem QLHD = new MenuItem(iconHD, "Quản lý hóa đơn", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                switchToPanel(new BillsManagement());
//                switchToPanel(new QuanLyHoaDonView());
            }
        });

        MenuItem subThongKeDoanhThu = new MenuItem(iconSubMenu, "Thống kê doanh thu", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new ManagerHomeStatistics());
//                switchToPanel(new ThongKeDoanhThuNhanVienView());

            }
        });
        MenuItem subThongKeSanPham = new MenuItem(iconSubMenu, "Thống kê sản phẩm", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                switchToPanel(new ThongKeSanPhamQuanLyView());
            }
        });

        MenuItem subThongKeSach = new MenuItem(iconSubMenu, "Thống kê sách", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                switchToPanel(new ThongKeSachQuanLyView());
            }
        });

        MenuItem subThongKeKhachHang = new MenuItem(iconSubMenu, "Thống kê khách hàng", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                switchToPanel(new TKKHView());
            }
        });


        MenuItem ThongKe = new MenuItem(iconTK, "Thống kê", null, subThongKeDoanhThu, subThongKeSanPham, subThongKeSach,
                subThongKeKhachHang);

        MenuItem subGiaoDien = new MenuItem(iconSubMenu, "Giao diện", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(new HomeView());

            }
        });
        MenuItem subCaiDatDMK = new MenuItem(iconSubMenu, "Đổi mật khẩu", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                QuanLy ql = daoQL.getQuanLy(headerQL.getId());
//                SetPassWordQuanLyView frame = new SetPassWordQuanLyView(ql);
//                switchToPanel(new HomeView());
//                frame.setVisible(true);
            }
        });
        MenuItem subCaiDatHDSD = new MenuItem(iconSubMenu, "Hướng dẫn sử dụng", null);
        MenuItem CaiDat = new MenuItem(iconSetting, "Cài đặt", null, subGiaoDien, subCaiDatDMK);

        addMenu(QLSP, QLBH, QLNV, QLKH, KM, QLHD, ThongKe, CaiDat, DangXuat);
        QLBH.setBackground(new Color(153, 255, 255));
        QLSP.setBackground(new Color(153, 255, 255));
        QLNV.setBackground(new Color(153, 255, 255));
        QLKH.setBackground(new Color(153, 255, 255));
        KM.setBackground(new Color(153, 255, 255));
        QLHD.setBackground(new Color(153, 255, 255));
        ThongKe.setBackground(new Color(153, 255, 255));
        CaiDat.setBackground(new Color(153, 255, 255));
        DangXuat.setBackground(new Color(153, 255, 255));
//		QLBH.setBackground(new Color(153,255,255));

    }

    private void switchToPanel(JPanel newPanel) {
        panelBody.remove(paneCu); // Loại bỏ panel hiện tại
        paneCu = newPanel; // Cập nhật panel hiện tại
        panelBody.add(paneCu); // Thêm panel mới vào panelBody
        panelBody.repaint();
        panelBody.revalidate();
    }

    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);

            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        menus.revalidate();
    }

    private void initComponents() {
        panelHeader = new JPanel(new BorderLayout());
        panelMenu = new JPanel();
        jScrollPane1 = new JScrollPane();
        menus = new JPanel();
        panelBody = new JPanel();
        lbID = new JLabel(": " + employee.getIdEmployee());
        lbID.setFont(new Font("Arial", Font.ITALIC, 15));
        lbName = new JLabel(": " + employee.getName());
        lbName.setFont(new Font("Arial", Font.ITALIC, 15));
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(": dd/MM/yyyy");
        String dateTiemString = now.format(formatter);
        lblDate = new JLabel(dateTiemString);
        lblDate.setFont(new Font("Arial", Font.ITALIC, 20));

        JPanel pnDate = new JPanel();
        pnDate.add(lblDate);
        JPanel pnTenID = new JPanel(new BorderLayout());
        JPanel pnChen = new JPanel();
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panelHeader.setBackground(new Color(225, 223, 223));
        panelHeader.setPreferredSize(new Dimension(561, 50));
        pnTenID.add(lbID, BorderLayout.NORTH);
        pnTenID.add(lbName, BorderLayout.CENTER);
        ImageIcon iconid = new ImageIcon(getClass().getResource("/icons/id.png"));
        ImageIcon iconTen = new ImageIcon(getClass().getResource("/icons/Ten.png"));
        ImageIcon iconNgayThang = new ImageIcon(getClass().getResource("/icons/calendar.png"));
        lbID.setIcon(iconid);
        lbName.setIcon(iconTen);
        lblDate.setIcon(iconNgayThang);

        panelHeader.add(pnTenID, BorderLayout.WEST);
        panelHeader.add(pnChen, BorderLayout.CENTER);
        panelHeader.add(pnDate, BorderLayout.EAST);
        getContentPane().add(panelHeader, BorderLayout.PAGE_START);
        jScrollPane1.setBorder(null);
        menus.setLayout(new BoxLayout(menus, BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);
        GroupLayout panelMenuLayout = new GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE));
        panelMenuLayout.setVerticalGroup(panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE));

        getContentPane().add(panelMenu, BorderLayout.LINE_START);
        panelBody.setLayout(new BorderLayout());
        getContentPane().add(panelBody, BorderLayout.CENTER);
    }
}