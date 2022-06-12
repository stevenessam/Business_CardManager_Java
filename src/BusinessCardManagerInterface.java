package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.image.BufferedImage;

/**
 * {@link BusinessCardInformation}
 * {@link JFrame}
 * {@link InterfacesController}
 * {@link JPanel}
 */
public class BusinessCardManagerInterface extends JFrame {
    private final InterfacesController interfacesController = new InterfacesController();
    private final JPanel jPanel;
    private final CardLayout cardLayout;
    private String session;

    public BusinessCardManagerInterface() {

        session = null;
        setTitle("Business Card Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        this.cardLayout = new CardLayout();
        this.jPanel = new JPanel();
        jPanel.setLayout(cardLayout);
        JPanel home = new Home();
        JPanel login = new Login();
        JPanel profile = new CreateAccount();
        JPanel userInterface = new UserInterface();
        JPanel addNewBusinessCard = new AddNewBusinessCard();

        jPanel.add("home", home);
        jPanel.add("login", login);
        jPanel.add("userInterface", userInterface);
        jPanel.add("profile", profile);
        jPanel.add("addNewBusinessCard", addNewBusinessCard);
        cardLayout.show(jPanel, "home");
        add(jPanel);
        setVisible(true);
    }

    /**
     * {@link Home}
     * {@link JPanel}
     */
    public class Home extends JPanel {

        public Home() {

            BufferedImage myPicture;
            try {
                myPicture = ImageIO.read(new File("src/Epitech.png"));
                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                picLabel.setIcon(new ImageIcon(
                        new ImageIcon("src/Epitech.png").getImage().getScaledInstance(300, 100, Image.SCALE_DEFAULT)));

                add(picLabel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            setLayout(new GridBagLayout());

            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

            JPanel buttons = new JPanel(new GridBagLayout());

            JButton login = new JButton("Login");
            login.addActionListener(e -> cardLayout.show(jPanel, "login"));
            buttons.add(login, gridBagConstraints);

            JButton newUser = new JButton("SignUp");
            newUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(jPanel, "profile");
                }
            });

            buttons.add(newUser, gridBagConstraints);

            gridBagConstraints.weighty = 1;
            add(buttons, gridBagConstraints);
        }
    }

    /**
     * Class {@link Login}
     * {@link JPanel}
     */
    public class Login extends JPanel {

        /**
         * {@link Login}
         */
        public Login() {

            setLayout(new GridBagLayout());

            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

            JPanel menu = new JPanel(new GridBagLayout());

            JLabel name = new JLabel("Name :");
            menu.add(name, gridBagConstraints);
            JTextField nameTextField = new JTextField();
            menu.add(nameTextField, gridBagConstraints);

            JLabel password = new JLabel("Password :");
            menu.add(password, gridBagConstraints);
            JTextField passwordField = new JPasswordField();
            menu.add(passwordField, gridBagConstraints);

            JLabel empty = new JLabel("");
            menu.add(empty, gridBagConstraints);
            empty.setBorder(new EmptyBorder(10, 10, 10, 10));

            JButton login = new JButton("Login");
            login.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (interfacesController.login(nameTextField.getText(), passwordField.getText())) {
                        session = nameTextField.getText();
                        cardLayout.show(jPanel, "userInterface");

                        nameTextField.setText("");
                        passwordField.setText("");
                    }
                }
            });
            menu.add(login, gridBagConstraints);

            gridBagConstraints.weighty = 1;
            add(menu, gridBagConstraints);

            JButton home = new JButton("Exit");
            home.addActionListener(e -> cardLayout.show(jPanel, "home"));
            menu.add(home, gridBagConstraints);
        }
    }

    /**
     * {@link CreateAccount}
     * {@link JPanel}
     */
    public class CreateAccount extends JPanel {

        public CreateAccount() {

            setLayout(new GridBagLayout());

            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

            JPanel menu = new JPanel(new GridBagLayout());

            JLabel name = new JLabel("Name :");

            menu.add(name, gridBagConstraints);
            JTextField nameTextField = new JTextField();
            menu.add(nameTextField, gridBagConstraints);

            JLabel company = new JLabel("Company Name :");

            menu.add(company, gridBagConstraints);
            JTextField companyTextField = new JTextField();
            menu.add(companyTextField, gridBagConstraints);

            JLabel email = new JLabel("Email Address :");

            menu.add(email, gridBagConstraints);
            JTextField emailTextField = new JTextField();
            menu.add(emailTextField, gridBagConstraints);

            JLabel tel = new JLabel("Telephone number :");

            menu.add(tel, gridBagConstraints);
            JTextField telephoneTextField = new JTextField();
            menu.add(telephoneTextField, gridBagConstraints);

            JLabel password = new JLabel("Password :");

            menu.add(password, gridBagConstraints);
            JTextField passwordField = new JPasswordField();
            menu.add(passwordField, gridBagConstraints);

            JLabel empty = new JLabel("");
            menu.add(empty, gridBagConstraints);
            empty.setBorder(new EmptyBorder(10, 10, 10, 10));

            JButton create = new JButton("Create Account");
            create.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!(nameTextField.getText().equals("") || passwordField.getText().equals(""))) {
                        UserInformation userInformation = UserInformation.builder()
                                .name(nameTextField.getText())
                                .company(companyTextField.getText())
                                .email(emailTextField.getText())
                                .number(telephoneTextField.getText())
                                .build();

                        interfacesController.newUser(userInformation, nameTextField.getText(), passwordField.getText(),
                                true);
                        session = nameTextField.getText();

                        cardLayout.show(jPanel, "userInterface");

                        nameTextField.setText("");
                        companyTextField.setText("");
                        emailTextField.setText("");
                        telephoneTextField.setText("");
                        passwordField.setText("");

                    }

                }
            });
            menu.add(create, gridBagConstraints);

            JButton home = new JButton("Exit");
            home.addActionListener(e -> cardLayout.show(jPanel, "home"));
            menu.add(home, gridBagConstraints);

            gridBagConstraints.weighty = 1;
            add(menu, gridBagConstraints);
        }

    }

    /**
     * {@link UserInterface}
     * {@link JPanel}
     */
    public class UserInterface extends JPanel {
        public UserInterface() {

            setLayout(new GridBagLayout());

            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

            JPanel buttons = new JPanel(new GridBagLayout());

            JButton myBc = new JButton("Profile");
            myBc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel businessCardInformation = new BusinessCardInformation();
                    jPanel.add("businessCardInformation", businessCardInformation);
                    cardLayout.show(jPanel, "businessCardInformation");
                }
            });
            buttons.add(myBc, gridBagConstraints);

            JButton newBc = new JButton("Add New Business Card");
            newBc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    cardLayout.show(jPanel, "addNewBusinessCard");
                }
            });
            buttons.add(newBc, gridBagConstraints);

            JButton lib = new JButton("Library");
            lib.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel library = new Library();
                    jPanel.add("library", library);
                    cardLayout.show(jPanel, "library");
                }
            });
            buttons.add(lib, gridBagConstraints);

            gridBagConstraints.weighty = 1;
            add(buttons, gridBagConstraints);

            JButton logout = new JButton("Logout");
            logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    interfacesController.getUserProfile(session).setConnected(false);
                    session = null;

                    cardLayout.show(jPanel, "home");
                }
            });
            buttons.add(logout, gridBagConstraints);
        }
    }

    /**
     * {@link BusinessCardInformation}
     * {@link JPanel}
     */
    public class BusinessCardInformation extends JPanel {
        public BusinessCardInformation() {

            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());

            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

            JPanel panel = new JPanel(new FlowLayout());

            JTextArea bc = new JTextArea(interfacesController.getUserProfile(session).userInformationDisplay());
            bc.setEditable(false);
            panel.add(bc);

            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> cardLayout.show(jPanel, "userInterface"));
            panel.add(exit);

            gridBagConstraints.weighty = 1;
            add(panel, gridBagConstraints);
        }
    }

    /**
     * {@link AddNewBusinessCard}
     * {@link JPanel}
     */
    public class AddNewBusinessCard extends JPanel {
        public AddNewBusinessCard() {
            setLayout(new GridBagLayout());

            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

            JPanel menu = new JPanel(new GridBagLayout());

            JLabel name = new JLabel("Name :");
            menu.add(name, gridBagConstraints);
            JTextField nameTextField = new JTextField();
            menu.add(nameTextField, gridBagConstraints);

            JLabel company = new JLabel("Company Name :");
            menu.add(company, gridBagConstraints);
            JTextField companyTextField = new JTextField();
            menu.add(companyTextField, gridBagConstraints);

            JLabel email = new JLabel("Email Address :");
            menu.add(email, gridBagConstraints);
            JTextField emailTextField = new JTextField();
            menu.add(emailTextField, gridBagConstraints);

            JLabel tel = new JLabel("Telephone Number :");
            menu.add(tel, gridBagConstraints);
            JTextField telephoneTextField = new JTextField();
            menu.add(telephoneTextField, gridBagConstraints);

            JLabel empty = new JLabel("");
            menu.add(empty, gridBagConstraints);
            empty.setBorder(new EmptyBorder(10, 10, 10, 10));

            JButton add = new JButton("Add Business Card");
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!emailTextField.getText().equals("")) {
                        UserInformation userInformation = UserInformation.builder()
                                .name(nameTextField.getText())
                                .company(companyTextField.getText())
                                .email(emailTextField.getText())
                                .number(telephoneTextField.getText())
                                .build();

                        interfacesController.getUserProfile(session).addUserInformation(userInformation);

                        cardLayout.show(jPanel, "userInterface");

                        nameTextField.setText("");
                        companyTextField.setText("");
                        emailTextField.setText("");
                        telephoneTextField.setText("");

                    }

                }
            });
            menu.add(add, gridBagConstraints);

            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> cardLayout.show(jPanel, "userInterface"));
            menu.add(exit, gridBagConstraints);

            gridBagConstraints.weighty = 1;
            add(menu, gridBagConstraints);
        }
    }

    /**
     * {@link Library}
     * {@link JPanel}
     */
    public class Library extends JPanel {
        public Library() {
            List<UserInformation> lib = interfacesController.getUserProfile(session).getLibrary();

            setLayout(new GridBagLayout());

            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

            gridBagConstraints.anchor = GridBagConstraints.CENTER;
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

            JPanel library = new JPanel(new GridBagLayout());

            for (UserInformation userInformation : lib) {
                JTextArea bc = new JTextArea(userInformation.toString());
                bc.setEditable(false);
                library.add(bc);
            }

            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> cardLayout.show(jPanel, "userInterface"));
            library.add(exit);

            gridBagConstraints.weighty = 1;
            add(library, gridBagConstraints);
        }
    }

}
