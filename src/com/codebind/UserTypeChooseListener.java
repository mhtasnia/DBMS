package com.codebind;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.CardLayout;
public class UserTypeChooseListener implements ActionListener{
        private JPanel cardPanel;

        public UserTypeChooseListener(JPanel cardPanel) {
            this.cardPanel = cardPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "StudentLoginPage");
        }
}


