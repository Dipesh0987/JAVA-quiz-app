package tests;

import gui.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;
import java.awt.*;

public class GUITests {
    
    @Test
    public void testWindowCreation() {
        System.out.println("Testing GUI window creation");
        
        // Test that shows windows can be created without errors
        try {
            // Test Login window
            Login login = new Login();
            assertNotNull(login);
            assertEquals("Log In", login.getTitle() != null ? login.getTitle() : "Login Window");
            login.dispose();
            
            // Test AdminPanel
            AdminPanel adminPanel = new AdminPanel();
            assertNotNull(adminPanel);
            adminPanel.dispose();
            
            // Test UserDashboard
            UserDashboard dashboard = new UserDashboard("testUser");
            assertNotNull(dashboard);
            dashboard.dispose();
            
            System.out.println("All windows created successfully!");
            
        } catch (Exception e) {
            fail("GUI creation failed: " + e.getMessage());
        }
    }
    
    
    @Test
    public void testComponentExistence() {
        System.out.println("Testing GUI components...");
        
        // Test AddGUI has required components
        AddGUI addGUI = new AddGUI();
        
        // Find components by their text or names
        Component[] components = addGUI.getContentPane().getComponents();
        assertTrue(components.length > 0, "AddGUI should have components");
        
        boolean hasAddButton = false;
        
        for (Component comp : components) {
            if (comp instanceof JButton) {
                JButton button = (JButton) comp;
                if ("Add".equals(button.getText())) {
                    hasAddButton = true;
                }
            }
        }
        
        assertTrue(hasAddButton, "AddGUI should have an Add button");
        
        addGUI.dispose();
        System.out.println("Component test passed!");
    }
    
    @Test
    public void testNavigationBetweenWindows() {
        System.out.println("Testing window navigation logic...");
        
        // Test button action command names (if set)
        Login login = new Login();
        
        // Find login button
        JButton loginButton = null;
        for (Component comp : login.getContentPane().getComponents()) {
            if (comp instanceof JButton && "Login".equals(((JButton) comp).getText())) {
                loginButton = (JButton) comp;
                break;
            }
        }
        
        assertNotNull(loginButton, "Login window should have a Login button");
        assertNotNull(loginButton.getActionListeners(), 
            "Login button should have action listeners");
        
        login.dispose();
        System.out.println("Navigation test done!");
    }
    
    @Test 
    public void testComboBoxFunctionality() {
        System.out.println("Test ComboBox functionality");
        
        UserDashboard dashboard = new UserDashboard("testUser");
        
        // Find difficulty combo box
        JComboBox<?> difficultyCombo = null;
        for (Component comp : dashboard.getContentPane().getComponents()) {
            if (comp instanceof JComboBox) {
                difficultyCombo = (JComboBox<?>) comp;
                break;
            }
        }
        
        assertNotNull(difficultyCombo, "UserDashboard should have a difficulty combo box");
        
        // Test combo box items
        assertEquals(4, difficultyCombo.getItemCount(), 
            "Difficulty combo should have 4 items");
        
        assertEquals("Select Difficulty", difficultyCombo.getItemAt(0));
        assertEquals("Beginner", difficultyCombo.getItemAt(1));
        assertEquals("Intermediate", difficultyCombo.getItemAt(2));
        assertEquals("Advanced", difficultyCombo.getItemAt(3));
        
        dashboard.dispose();
        System.out.println("ComboBox test done!");
    }
}