import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javafx.util.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.Math;
/*
Brandon Namphong
9-25-2019
 */
public class jdbcpostgreSQLGUI {

  public static double toRadians(double x) {
      // For q3
      return x * Math.PI / 180;
  }

  public static Pair<Double, Double> projectCoords(Pair<Double, Double> lonlat) {
      // for q3 - Returns x and y coordinates when given latitude and longitude

      double radius = 6378100;
      // 1 degree latitude in meters
      double dist = Math.PI * radius / 180;
      double lat = lonlat.getValue();
      double lon = lonlat.getKey();

      double y = lat * dist;
      double x = lon * dist * Math.cos(toRadians(lat));

      return new Pair<>(x, y);
  }

  public static double calcSpan(ArrayList<Pair<Double, Double>> coord) {
      // For q3

      double area = 0;
      // Only 2 locations -> find distance between them
      if (coord.size() <= 2) {
          return area;
      }
      else {
          // Calculate the area of a polygon with n>2 verticies
          // 2 * Area = Sum i=0 to n-1 { x_i * y_i+1 - x_i+1 * y_i }
          for (int i=0; i<coord.size()-1; i++) {
            Pair<Double, Double> c1 = projectCoords(coord.get(i));
            Pair<Double, Double> c2 = projectCoords(coord.get(i+1));

            area += (c1.getKey() * c2.getValue()) - (c2.getKey() * c1.getValue());
          }

          area = area / 2;
      }

      return Math.abs(area);
  }

  public static void main(String args[]) {
    dbSetup my = new dbSetup();

    JFrame f = new JFrame();
    JLabel title = new JLabel("Java Database Team D20");
    JLabel blabel = new JLabel("Business");
    JLabel checklabel = new JLabel("Check Ins");
    JLabel elitelabel = new JLabel("Elite Users");
    JLabel hourlabel = new JLabel("Hours");
    JLabel loclabel = new JLabel("Location");
    JLabel revlabel = new JLabel("Reviews");
    JLabel tiplabel = new JLabel("Tips");
    JLabel userlabel = new JLabel("Users");
    JCheckBox b1 = new JCheckBox("Business ID");
    JCheckBox b2 = new JCheckBox("Name");
    JCheckBox b3 = new JCheckBox("Address");
    JCheckBox b4 = new JCheckBox("City");
    JCheckBox b5 = new JCheckBox("State");
    JCheckBox b6 = new JCheckBox("Postal Code");
    JCheckBox b7 = new JCheckBox("Latitude");
    JCheckBox b8 = new JCheckBox("Longitude");
    JCheckBox b9 = new JCheckBox("Stars");
    JCheckBox b10 = new JCheckBox("Review Count");
    JCheckBox b11 = new JCheckBox("Is Open");
    JCheckBox b12 = new JCheckBox("Categories");
    JCheckBox b13 = new JCheckBox("Monday hours");
    JCheckBox b14 = new JCheckBox("Tuesday hours");
    JCheckBox b15 = new JCheckBox("Wednesday hours");
    JCheckBox b16 = new JCheckBox("Thursday hours");
    JCheckBox b17 = new JCheckBox("Friday hours");
    JCheckBox b18 = new JCheckBox("Saturdays hours");
    JCheckBox b19 = new JCheckBox("Sunday hours");
    JCheckBox c1 = new JCheckBox("Business ID");
    JCheckBox c2 = new JCheckBox("Date");
    JCheckBox e1 = new JCheckBox("User ID");
    JCheckBox e2 = new JCheckBox("Name");
    JCheckBox e3 = new JCheckBox("Elite");
    JCheckBox e4 = new JCheckBox("Review Count");
    JCheckBox e5 = new JCheckBox("Yelping Since");
    JCheckBox e6 = new JCheckBox("Friends");
    JCheckBox e7 = new JCheckBox("Fans");
    JCheckBox e8 = new JCheckBox("Average Stars");
    JCheckBox h1 = new JCheckBox("Business ID");
    JCheckBox h2 = new JCheckBox("Is Open");
    JCheckBox h3 = new JCheckBox("Monday hours");
    JCheckBox h4 = new JCheckBox("Tuesday hours");
    JCheckBox h5 = new JCheckBox("Wednesday hours");
    JCheckBox h6 = new JCheckBox("Thursday hours");
    JCheckBox h7 = new JCheckBox("Friday hours");
    JCheckBox h8 = new JCheckBox("Saturday hours");
    JCheckBox h9 = new JCheckBox("Sunday hours");
    JCheckBox l1 = new JCheckBox("Business ID");
    JCheckBox l2 = new JCheckBox("Address");
    JCheckBox l3 = new JCheckBox("State");
    JCheckBox l4 = new JCheckBox("City");
    JCheckBox l5 = new JCheckBox("Postal Code");
    JCheckBox l6 = new JCheckBox("Latitude");
    JCheckBox l7 = new JCheckBox("Longitude");
    JCheckBox r1 = new JCheckBox("Review ID");
    JCheckBox r2 = new JCheckBox("User ID");
    JCheckBox r3 = new JCheckBox("Business ID");
    JCheckBox r4 = new JCheckBox("Stars");
    JCheckBox r5 = new JCheckBox("Useful");
    JCheckBox r6 = new JCheckBox("Funny");
    JCheckBox r7 = new JCheckBox("Cool");
    JCheckBox r8 = new JCheckBox("Description");
    JCheckBox r9 = new JCheckBox("Date");
    JCheckBox t1 = new JCheckBox("User ID");
    JCheckBox t2 = new JCheckBox("Business ID");
    JCheckBox t3 = new JCheckBox("Text");
    JCheckBox t4 = new JCheckBox("Date");
    JCheckBox t5 = new JCheckBox("Compliment Count");
    JCheckBox u1 = new JCheckBox("User ID");
    JCheckBox u2 = new JCheckBox("Name");
    JCheckBox u3 = new JCheckBox("Review Count");
    JCheckBox u4 = new JCheckBox("Yelping Since");
    JCheckBox u5 = new JCheckBox("Elite");
    JCheckBox u6 = new JCheckBox("Friends");
    JCheckBox u7 = new JCheckBox("Fans");
    JCheckBox u8 = new JCheckBox("Average Stars");
    JTextField tb1, tb2, tb3, tb4, tb5, tb6, tb7, tb8, tb9, tb10, tb11, tb12, tb13, tb14, tb15, tb16, tb17, tb18, tb19;
    JTextField tc1, tc2;
    JTextField te1, te2, te3, te4, te5, te6, te7, te8;
    JTextField th1, th2, th3, th4, th5, th6, th7, th8, th9;
    JTextField tl1, tl2, tl3, tl4, tl5, tl6, tl7;
    JTextField tr1, tr2, tr3, tr4, tr5, tr6, tr7, tr8, tr9;
    JTextField tt1, tt2, tt3, tt4, tt5;
    JTextField tu1, tu2, tu3, tu4, tu5, tu6, tu7, tu8;
    tb1 = new JTextField(); tb2 = new JTextField(); tb3 = new JTextField(); tb4 = new JTextField(); tb5 = new JTextField();
    tb6 = new JTextField(); tb7 = new JTextField(); tb8 = new JTextField(); tb9 = new JTextField(); tb10 = new JTextField();
    tb11 = new JTextField(); tb12 = new JTextField(); tb13 = new JTextField(); tb14 = new JTextField(); tb15 = new JTextField();
    tb16 = new JTextField(); tb17 = new JTextField(); tb18 = new JTextField(); tb19 = new JTextField();
    tc1 = new JTextField(); tc2 = new JTextField();
    te1 = new JTextField(); te2 = new JTextField(); te3 = new JTextField(); te4 = new JTextField(); te5 = new JTextField(); te6 = new JTextField(); te7 = new JTextField(); te8 = new JTextField();
    th1 = new JTextField(); th2 = new JTextField(); th3 = new JTextField(); th4 = new JTextField(); th5 = new JTextField(); th6 = new JTextField(); th7 = new JTextField(); th8 = new JTextField(); th9 = new JTextField();
    tl1 = new JTextField(); tl2 = new JTextField(); tl3 = new JTextField(); tl4 = new JTextField(); tl5 = new JTextField(); tl6 = new JTextField(); tl7 = new JTextField();
    tr1 = new JTextField(); tr2 = new JTextField(); tr3 = new JTextField(); tr4 = new JTextField(); tr5 = new JTextField(); tr6 = new JTextField(); tr7 = new JTextField(); tr8 = new JTextField(); tr9 = new JTextField();
    tt1 = new JTextField(); tt2 = new JTextField(); tt3 = new JTextField(); tt4 = new JTextField(); tt5 = new JTextField();
    tu1 = new JTextField(); tu2 = new JTextField(); tu3 = new JTextField(); tu4 = new JTextField(); tu5 = new JTextField(); tu6 = new JTextField(); tu7 = new JTextField(); tu8 = new JTextField();

    JButton but1 = new JButton("Enter Query");
    JButton but2 = new JButton("Clear");
    JCheckBox out1 = new JCheckBox("Output to File");
    JLabel liml = new JLabel("Limit");
    JTextField tlim = new JTextField();
    f.add(but1); f.add(but2); f.add(out1); f.add(liml); f.add(tlim);
    but1.setBounds(530,500,110,40);
    but2.setBounds(660,500,100,40);
    out1.setBounds(780,500,130,20);
    liml.setBounds(785,530,50,20);
    tlim.setBounds(830,530,80,20);

    JLabel query = new JLabel("label");
    query.setBounds(210,460,1310,40);
    f.add(query);

    f.add(title);
    f.add(blabel); f.add(b1); f.add(b2); f.add(b3); f.add(b4); f.add(b5); f.add(b6); f.add(b7); f.add(b8); f.add(b9); f.add(b10); f.add(b11); f.add(b12); f.add(b13); f.add(b14); f.add(b15); f.add(b16); f.add(b17); f.add(b18); f.add(b19);
    f.add(checklabel); f.add(c1); f.add(c2);
    f.add(elitelabel); f.add(e1); f.add(e2); f.add(e3); f.add(e4); f.add(e5); f.add(e6); f.add(e7); f.add(e8);
    f.add(hourlabel); f.add(h1); f.add(h2); f.add(h3); f.add(h4); f.add(h5); f.add(h6); f.add(h7); f.add(h8); f.add(h9);
    f.add(loclabel); f.add(l1); f.add(l2); f.add(l3); f.add(l4); f.add(l5); f.add(l6); f.add(l7);
    f.add(revlabel); f.add(r1); f.add(r2); f.add(r3); f.add(r4); f.add(r5); f.add(r6); f.add(r7); f.add(r8); f.add(r9);
    f.add(tiplabel); f.add(t1); f.add(t2); f.add(t3); f.add(t4); f.add(t5);
    f.add(userlabel); f.add(u1); f.add(u2); f.add(u3); f.add(u4); f.add(u5); f.add(u6); f.add(u7); f.add(u8);
    f.add(tb1); f.add(tb2); f.add(tb3);  f.add(tb4);  f.add(tb5);  f.add(tb6);  f.add(tb7);  f.add(tb8);  f.add(tb9);  f.add(tb10); f.add(tb11);  f.add(tb12);  f.add(tb13);  f.add(tb14);  f.add(tb15);  f.add(tb16); f.add(tb17); f.add(tb18);  f.add(tb19);
    f.add(tc1); f.add(tc2);
    f.add(te1); f.add(te2); f.add(te3); f.add(te4); f.add(te5); f.add(te6); f.add(te7); f.add(te8);
    f.add(th1); f.add(th2); f.add(th3); f.add(th4); f.add(th5); f.add(th6); f.add(th7); f.add(th8); f.add(th9);
    f.add(tl1); f.add(tl2); f.add(tl3); f.add(tl4); f.add(tl5); f.add(tl6); f.add(tl7);
    f.add(tr1); f.add(tr2); f.add(tr3); f.add(tr4); f.add(tr5); f.add(tr6); f.add(tr7); f.add(tr8); f.add(tr9);
    f.add(tt1); f.add(tt2); f.add(tt3); f.add(tt4); f.add(tt5);
    f.add(tu1); f.add(tu2); f.add(tu3); f.add(tu4); f.add(tu5); f.add(tu6); f.add(tu7); f.add(tu8);
    f.setSize(1400,900); //width, height
    f.setLayout(null);
    f.setVisible(true);

    title.setBounds(540,10,400,50);
    title.setFont(new Font("Verdana", Font.PLAIN, 22));
    blabel.setBounds(90,70,100,50); //x-axis, y-axis, width, height
    b1.setBounds(20,110,130,20); tb1.setBounds(150,110,50,20);
    b2.setBounds(20,130,130,20); tb2.setBounds(150,130,50,20);
    b3.setBounds(20,150,130,20); tb3.setBounds(150,150,50,20);
    b4.setBounds(20,170,130,20); tb4.setBounds(150,170,50,20);
    b5.setBounds(20,190,130,20); tb5.setBounds(150,190,50,20);
    b6.setBounds(20,210,130,20); tb6.setBounds(150,210,50,20);
    b7.setBounds(20,230,130,20); tb7.setBounds(150,230,50,20);
    b8.setBounds(20,250,130,20); tb8.setBounds(150,250,50,20);
    b9.setBounds(20,270,130,20); tb9.setBounds(150,270,50,20);
    b10.setBounds(20,290,130,20); tb10.setBounds(150,290,50,20);
    b11.setBounds(20,310,130,20); tb11.setBounds(150,310,50,20);
    b12.setBounds(20,330,130,20); tb12.setBounds(150,330,50,20);
    b13.setBounds(20,350,130,20); tb13.setBounds(150,350,50,20);
    b14.setBounds(20,370,130,20); tb14.setBounds(150,370,50,20);
    b15.setBounds(20,390,130,20); tb15.setBounds(150,390,50,20);
    b16.setBounds(20,410,130,20); tb16.setBounds(150,410,50,20);
    b17.setBounds(20,430,130,20); tb17.setBounds(150,430,50,20);
    b18.setBounds(20,450,130,20); tb18.setBounds(150,450,50,20);
    b19.setBounds(20,470,130,20); tb19.setBounds(150,470,50,20);
    checklabel.setBounds(280,70,100,50);
    c1.setBounds(220,110,100,20); tc1.setBounds(330,110,50,20);
    c2.setBounds(220,130,100,20); tc2.setBounds(330,130,50,20);
    elitelabel.setBounds(280,170,100,20);
    e1.setBounds(220,200,110,20); te1.setBounds(330,200,50,20);
    e2.setBounds(220,220,110,20); te2.setBounds(330,220,50,20);
    e3.setBounds(220,240,110,20); te3.setBounds(330,240,50,20);
    e4.setBounds(220,260,110,20); te4.setBounds(330,260,50,20);
    e5.setBounds(220,280,110,20); te5.setBounds(330,280,50,20);
    e6.setBounds(220,300,110,20); te6.setBounds(330,300,50,20);
    e7.setBounds(220,320,110,20); te7.setBounds(330,320,50,20);
    e8.setBounds(220,340,110,20); te8.setBounds(330,340,50,20);
    hourlabel.setBounds(460,70,100,50);
    h1.setBounds(390,110,130,20); th1.setBounds(520,110,50,20);
    h2.setBounds(390,130,130,20); th2.setBounds(520,130,50,20);
    h3.setBounds(390,150,130,20); th3.setBounds(520,150,50,20);
    h4.setBounds(390,170,130,20); th4.setBounds(520,170,50,20);
    h5.setBounds(390,190,130,20); th5.setBounds(520,190,50,20);
    h6.setBounds(390,210,130,20); th6.setBounds(520,210,50,20);
    h7.setBounds(390,230,130,20); th7.setBounds(520,230,50,20);
    h8.setBounds(390,250,130,20); th8.setBounds(520,250,50,20);
    h9.setBounds(390,270,130,20); th9.setBounds(520,270,50,20);
    loclabel.setBounds(650,70,100,50);
    l1.setBounds(590,110,100,20); tl1.setBounds(700,110,50,20);
    l2.setBounds(590,130,100,20); tl2.setBounds(700,130,50,20);
    l3.setBounds(590,150,100,20); tl3.setBounds(700,150,50,20);
    l4.setBounds(590,170,100,20); tl4.setBounds(700,170,50,20);
    l5.setBounds(590,190,100,20); tl5.setBounds(700,190,50,20);
    l6.setBounds(590,210,100,20); tl6.setBounds(700,210,50,20);
    l7.setBounds(590,230,100,20); tl7.setBounds(700,230,50,20);
    revlabel.setBounds(830,70,100,50);
    r1.setBounds(770,110,100,20); tr1.setBounds(880,110,50,20);
    r2.setBounds(770,130,100,20); tr2.setBounds(880,130,50,20);
    r3.setBounds(770,150,100,20); tr3.setBounds(880,150,50,20);
    r4.setBounds(770,170,100,20); tr4.setBounds(880,170,50,20);
    r5.setBounds(770,190,100,20); tr5.setBounds(880,190,50,20);
    r6.setBounds(770,210,100,20); tr6.setBounds(880,210,50,20);
    r7.setBounds(770,230,100,20); tr7.setBounds(880,230,50,20);
    r8.setBounds(770,250,100,20); tr8.setBounds(880,250,50,20);
    r9.setBounds(770,270,100,20); tr9.setBounds(880,270,50,20);
    tiplabel.setBounds(1040,70,100,50);
    t1.setBounds(950,110,140,20); tt1.setBounds(1090,110,50,20);
    t2.setBounds(950,130,140,20); tt2.setBounds(1090,130,50,20);
    t3.setBounds(950,150,140,20); tt3.setBounds(1090,150,50,20);
    t4.setBounds(950,170,140,20); tt4.setBounds(1090,170,50,20);
    t5.setBounds(950,190,140,20); tt5.setBounds(1090,190,50,20);
    userlabel.setBounds(1220,70,100,50);
    u1.setBounds(1160,110,110,20); tu1.setBounds(1280,110,50,20);
    u2.setBounds(1160,130,110,20); tu2.setBounds(1280,130,50,20);
    u3.setBounds(1160,150,110,20); tu3.setBounds(1280,150,50,20);
    u4.setBounds(1160,170,110,20); tu4.setBounds(1280,170,50,20);
    u5.setBounds(1160,190,110,20); tu5.setBounds(1280,190,50,20);
    u6.setBounds(1160,210,110,20); tu6.setBounds(1280,210,50,20);
    u7.setBounds(1160,230,110,20); tu7.setBounds(1280,230,50,20);
    u8.setBounds(1160,250,110,20); tu8.setBounds(1280,250,50,20);

    ArrayList<String> columns = new ArrayList<String>();
    ArrayList<String> tables = new ArrayList<String>();
    ArrayList<String> where = new ArrayList<String>();

   but1.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
      Connection conn = null; //Building the connection
      try {
          Class.forName("org.postgresql.Driver");
          conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/section_904_d20",
            my.user, my.pswd);
      } catch (Exception e1) {
          e1.printStackTrace();
          System.err.println(e1.getClass().getName()+": "+e1.getMessage());
          System.exit(0);
      }
      JOptionPane.showMessageDialog(null,"Opened database successfully");


      //Check checkboxes
      if(b1.isSelected() && !columns.contains("business.business_id")) {
          columns.add("business.business_id");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb1.getText().isEmpty()) {
            where.add("business.business_id='"+ tb1.getText() + "'");
          }
      }
      if(b2.isSelected() && !columns.contains("business.name")) {
          columns.add("business.name");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb2.getText().isEmpty()) {
            where.add("business.name='"+ tb2.getText() + "'");
          }
      }
      if(b3.isSelected() && !columns.contains("business.address")) {
          columns.add("business.address");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb3.getText().isEmpty()) {
            where.add("business.address='"+ tb3.getText() + "'");
          }
      }

      if(b4.isSelected() && !columns.contains("business.city")) {
          columns.add("business.city");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb4.getText().isEmpty()) {
            where.add("business.city='"+ tb4.getText() + "'");
          }
      }

      if(b5.isSelected() && !columns.contains("business.state")) {
          columns.add("business.state");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb5.getText().isEmpty()) {
            where.add("business.state='"+ tb5.getText() + "'");
          }
      }

      if(b6.isSelected() && !columns.contains("business.postal_code")) {
          columns.add("business.postal_code");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb6.getText().isEmpty()) {
            where.add("business.postal_code='"+ tb6.getText() + "'");
          }
      }

      if(b7.isSelected() && !columns.contains("business.latitude")) {
          columns.add("business.latitude");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb7.getText().isEmpty()) {
            where.add("business.latitude='"+ tb7.getText() + "'");
          }
      }

      if(b8.isSelected() && !columns.contains("business.longitude")) {
          columns.add("business.longitude");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb8.getText().isEmpty()) {
            where.add("business.longitude='"+ tb8.getText() + "'");
          }
      }

      if(b9.isSelected() && !columns.contains("business.stars")) {
          columns.add("business.stars");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb9.getText().isEmpty()) {
            where.add("business.stars='"+ tb9.getText() + "'");
          }
      }

      if(b10.isSelected() && !columns.contains("business.review_count")) {
          columns.add("business.review_count");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb10.getText().isEmpty()) {
            where.add("business.review_count='"+ tb10.getText() + "'");
          }
      }

      if(b11.isSelected() && !columns.contains("business.is_open")) {
          columns.add("business.is_open");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb11.getText().isEmpty()) {
            where.add("business.is_open='"+ tb11.getText() + "'");
          }
      }

      if(b12.isSelected() && !columns.contains("business.categories")) {
          columns.add("business.categories");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb12.getText().isEmpty()) {
            where.add("business.categories='"+ tb12.getText() + "'");
          }
      }

      if(b13.isSelected() && !columns.contains("business.mondayhours")) {
          columns.add("business.mondayhours");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb13.getText().isEmpty()) {
            where.add("business.mondayhours='"+ tb13.getText() + "'");
          }
      }

      if(b14.isSelected() && !columns.contains("business.tuesdayhours")) {
          columns.add("business.tuesdayhours");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb14.getText().isEmpty()) {
            where.add("business.tuesdayhours='"+ tb14.getText() + "'");
          }
      }

      if(b15.isSelected() && !columns.contains("business.wednesdayhours")) {
          columns.add("business.wednesdayhours");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb15.getText().isEmpty()) {
            where.add("business.wednesdayhours='"+ tb15.getText() + "'");
          }
      }

      if(b16.isSelected() && !columns.contains("business.thursdayhours")) {
          columns.add("business.thursdayhours");
          if(!tables.contains("business")) {
            tables.add("business");
          }if(!tb16.getText().isEmpty()) {
            where.add("business.thursdayhours='"+ tb16.getText() + "'");
          }
      }

      if(b17.isSelected() && !columns.contains("business.fridayhours")) {
          columns.add("business.fridayhours");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb17.getText().isEmpty()) {
            where.add("business.fridayhours='"+ tb17.getText() + "'");
          }
      }

      if(b18.isSelected() && !columns.contains("business.saturdayhours")) {
          columns.add("business.saturdayhours");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb18.getText().isEmpty()) {
            where.add("business.saturdayhours='"+ tb18.getText() + "'");
          }
      }

      if(b19.isSelected() && !columns.contains("business.sundayhours")) {
          columns.add("business.sundayhours");
          if(!tables.contains("business")) {
            tables.add("business");
          }
          if(!tb19.getText().isEmpty()) {
            where.add("business.sundayhours='"+ tb19.getText() + "'");
          }
      }


      // Checkin checkboxes
      if(c1.isSelected() && !columns.contains("checkin.business_id")) {
          columns.add("checkin.business_id");
          if(!tables.contains("checkin")) {
            tables.add("checkin");
          }
          if(!tc1.getText().isEmpty()) {
            where.add("checkin.business_id'"+ tc1.getText() + "'");
          }
      }

      if(c2.isSelected() && !columns.contains("checkin.date")) {
          columns.add("checkin.date");
          if(!tables.contains("checkin")) {
            tables.add("checkin");
          }
          if(!tc2.getText().isEmpty()) {
            where.add("checkin.date='"+ tc2.getText() + "'");
          }
      }


      // Elite Users checkboxes
      if(e1.isSelected() && !columns.contains("elite_users.user_id")) {
          columns.add("elite_users.user_id");
          if(!tables.contains("elite_users")) {
            tables.add("elite_users");
          }
          if(!te1.getText().isEmpty()) {
            where.add("elite_users.user_id='"+ te1.getText() + "'");
          }
      }

      if(e2.isSelected() && !columns.contains("elite_users.name")) {
          columns.add("elite_users.name");
          if(!tables.contains("elite_users")) {
            tables.add("elite_users");
          }
          if(!te2.getText().isEmpty()) {
            where.add("elite_users.name='"+ te2.getText() + "'");
          }
      }

      if(e3.isSelected() && !columns.contains("elite_users.elite")) {
          columns.add("elite_users.elite");
          if(!tables.contains("elite_users")) {
            tables.add("elite_users");
          }
          if(!te3.getText().isEmpty()) {
            where.add("elite_users.elite='"+ te3.getText() + "'");
          }
      }

      if(e4.isSelected() && !columns.contains("elite_users.review_count")) {
          columns.add("elite_users.review_count");
          if(!tables.contains("elite_users")) {
            tables.add("elite_users");
          }
          if(!te4.getText().isEmpty()) {
            where.add("elite_users.review_count='"+ te4.getText() + "'");
          }
      }

      if(e5.isSelected() && !columns.contains("elite_users.yelping_since")) {
          columns.add("elite_users.yelping_since");
          if(!tables.contains("elite_users")) {
            tables.add("elite_users");
          }
          if(!te5.getText().isEmpty()) {
            where.add("elite_users.yelpinh_since='"+ te5.getText() + "'");
          }
      }

      if(e6.isSelected() && !columns.contains("elite_users.friends")) {
          columns.add("elite_users.friends");
          if(!tables.contains("elite_users")) {
            tables.add("elite_users");
          }
          if(!te6.getText().isEmpty()) {
            where.add("elite_users.friends='"+ te6.getText() + "'");
          }
      }

      if(e7.isSelected() && !columns.contains("elite_users.fans")) {
          columns.add("elite_users.fans");
          if(!tables.contains("elite_users")) {
            tables.add("elite_users");
          }
          if(!te7.getText().isEmpty()) {
            where.add("elite_users.fans='"+ te7.getText() + "'");
          }
      }

      if(e8.isSelected() && !columns.contains("elite_users.average_stars")) {
          columns.add("elite_users.average_stars");
          if(!tables.contains("elite_users")) {
            tables.add("elite_users");
          }
          if(!te8.getText().isEmpty()) {
            where.add("elite_users.average_stars='"+ te8.getText() + "'");
          }
      }

      // Hours checkboxes
      if(h1.isSelected() && !columns.contains("hours.business_id")) {
          columns.add("hours.business_id");
          if(!tables.contains("hours")) {
            tables.add("hours");
          }
          if(!th1.getText().isEmpty()) {
            where.add("hours.business_id='"+ th1.getText() + "'");
          }
      }

      if(h2.isSelected() && !columns.contains("hours.is_open")) {
          columns.add("hours.is_open");
          if(!tables.contains("hours")) {
            tables.add("hours");
          }
          if(!th2.getText().isEmpty()) {
            where.add("hours.is_open='"+ th2.getText() + "'");
          }
      }

      if(h3.isSelected() && !columns.contains("hours.mondayhours")) {
          columns.add("hours.mondayhours");
          if(!tables.contains("hours")) {
            tables.add("hours");
          }
          if(!th3.getText().isEmpty()) {
            where.add("hours.mondayhours='"+ th3.getText() + "'");
          }
      }

      if(h4.isSelected() && !columns.contains("hours.tuesdayhours")) {
          columns.add("hours.tuesdayhours");
          if(!tables.contains("hours")) {
            tables.add("hours");
          }
          if(!th4.getText().isEmpty()) {
            where.add("hours.tuesdayhours='"+ th4.getText() + "'");
          }
      }

      if(h5.isSelected() && !columns.contains("hours.wednesdayhours")) {
          columns.add("hours.wednesdayhours");
          if(!tables.contains("hours")) {
            tables.add("hours");
          }
          if(!th5.getText().isEmpty()) {
            where.add("hours.wednesdayhours='"+ th5.getText() + "'");
          }
      }

      if(h6.isSelected() && !columns.contains("hours.thursdayhours")) {
          columns.add("hours.thursdayhours");
          if(!tables.contains("hours")) {
            tables.add("hours");
          }
          if(!th6.getText().isEmpty()) {
            where.add("hours.thursdayhours='"+ th6.getText() + "'");
          }
      }

      if(h7.isSelected() && !columns.contains("hours.fridayhours")) {
          columns.add("hours.fridayhours");
          if(!tables.contains("hours")) {
            tables.add("hours");
          }
          if(!th7.getText().isEmpty()) {
            where.add("hours.fridayhours='"+ th7.getText() + "'");
          }
      }

      if(h8.isSelected() && !columns.contains("hours.saturdayhours")) {
          columns.add("hours.saturdayhours");
          if(!tables.contains("hours")) {
            tables.add("hours");
          }
          if(!th8.getText().isEmpty()) {
            where.add("hours.saturdayhours='"+ th8.getText() + "'");
          }
      }

      if(h9.isSelected() && !columns.contains("hours.sundayhours")) {
          columns.add("hours.sundayhours");
          if(!tables.contains("hours")) {
            tables.add("hours");
          }
          if(!th9.getText().isEmpty()) {
            where.add("hours.sundayhours='"+ th9.getText() + "'");
          }
      }


      // Location checkboxes
      if(l1.isSelected() && !columns.contains("location.business_id")) {
          columns.add("location.business_id");
          if(!tables.contains("location")) {
            tables.add("location");
          }
          if(!tl1.getText().isEmpty()) {
            where.add("location.business_id='"+ tl1.getText() + "'");
          }
      }

      if(l2.isSelected() && !columns.contains("location.address")) {
          columns.add("location.address");
          if(!tables.contains("location")) {
            tables.add("location");
          }
          if(!tl2.getText().isEmpty()) {
            where.add("location.address='"+ tl2.getText() + "'");
          }
      }

      if(l3.isSelected() && !columns.contains("location.state")) {
          columns.add("location.state");
          if(!tables.contains("location")) {
            tables.add("location");
          }
          if(!tl3.getText().isEmpty()) {
            where.add("location.state='"+ tl3.getText() + "'");
          }
      }

      if(l4.isSelected() && !columns.contains("location.city")) {
          columns.add("location.city");
          if(!tables.contains("location")) {
            tables.add("location");
          }
          if(!tl4.getText().isEmpty()) {
            where.add("location.city='"+ tl4.getText() + "'");
          }
      }

      if(l5.isSelected() && !columns.contains("location.postal_code")) {
          columns.add("location.postal_code");
          if(!tables.contains("location")) {
            tables.add("location");
          }
          if(!tl5.getText().isEmpty()) {
            where.add("location.postal_code='"+ tl5.getText() + "'");
          }
      }

      if(l6.isSelected() && !columns.contains("location.latitude")) {
          columns.add("location.latitude");
          if(!tables.contains("location")) {
            tables.add("location");
          }
          if(!tl6.getText().isEmpty()) {
            where.add("location.latitude='"+ tl6.getText() + "'");
          }
      }

      if(l7.isSelected() && !columns.contains("location.longitude")) {
          columns.add("location.longitude");
          if(!tables.contains("location")) {
            tables.add("location");
          }
          if(!tl7.getText().isEmpty()) {
            where.add("location.longitude='"+ tl7.getText() + "'");
          }
      }

      // Reviews checkboxes
      if(r1.isSelected() && !columns.contains("reviews.review_id")) {
          columns.add("reviews.review_id");
          if(!tables.contains("reviews")) {
            tables.add("reviews");
          }
          if(!tr1.getText().isEmpty()) {
            where.add("reviews.review_id='"+ tr1.getText() + "'");
          }
      }

      if(r2.isSelected() && !columns.contains("reviews.user_id")) {
          columns.add("reviews.user_id");
          if(!tables.contains("reviews")) {
            tables.add("reviews");
          }
          if(!tr2.getText().isEmpty()) {
            where.add("reviews.user_id='"+ tr2.getText() + "'");
          }
      }

      if(r3.isSelected() && !columns.contains("reviews.business_id")) {
          columns.add("reviews.business_id");
          if(!tables.contains("reviews")) {
            tables.add("reviews");
          }
          if(!tr3.getText().isEmpty()) {
            where.add("reviews.business_id='"+ tr3.getText() + "'");
          }
      }

      if(r4.isSelected() && !columns.contains("reviews.stars")) {
          columns.add("reviews.stars");
          if(!tables.contains("reviews")) {
            tables.add("reviews");
          }
          if(!tr4.getText().isEmpty()) {
            where.add("reviews.stars='"+ tr4.getText() + "'");
          }
      }

      if(r5.isSelected() && !columns.contains("reviews.useful")) {
          columns.add("reviews.useful");
          if(!tables.contains("reviews")) {
            tables.add("reviews");
          }
          if(!tr5.getText().isEmpty()) {
            where.add("reviews.useful='"+ tr5.getText() + "'");
          }
      }

      if(r6.isSelected() && !columns.contains("reviews.funny")) {
          columns.add("reviews.funny");
          if(!tables.contains("reviews")) {
            tables.add("reviews");
          }
          if(!tr6.getText().isEmpty()) {
            where.add("reviews.funny='"+ tr6.getText() + "'");
          }
      }

      if(r7.isSelected() && !columns.contains("reviews.cool")) {
          columns.add("reviews.cool");
          if(!tables.contains("reviews")) {
            tables.add("reviews");
          }
          if(!tr7.getText().isEmpty()) {
            where.add("reviews.cool='"+ tr7.getText() + "'");
          }
      }

      if(r8.isSelected() && !columns.contains("reviews.description")) {
          columns.add("reviews.description");
          if(!tables.contains("reviews")) {
            tables.add("reviews");
          }
          if(!tr8.getText().isEmpty()) {
            where.add("reviews.description='"+ tr8.getText() + "'");
          }
      }

      if(r9.isSelected() && !columns.contains("reviews.date")) {
          columns.add("reviews.date");
          if(!tables.contains("reviews")) {
            tables.add("reviews");
          }
          if(!tr9.getText().isEmpty()) {
            where.add("reviews.date='"+ tr9.getText() + "'");
          }
      }


      // Tips checkboxes
      if(t1.isSelected() && !columns.contains("tips.user_id")) {
          columns.add("tips.user_id");
          if(!tables.contains("tips")) {
            tables.add("tips");
          }
          if(!tt1.getText().isEmpty()) {
            where.add("tips.user_id='"+ tt1.getText() + "'");
          }
      }

      if(t2.isSelected() && !columns.contains("tips.business_id")) {
          columns.add("tips.business_id");
          if(!tables.contains("tips")) {
            tables.add("tips");
          }
          if(!tt2.getText().isEmpty()) {
            where.add("tips.business_id='"+ tt2.getText() + "'");
          }
      }

      if(t3.isSelected() && !columns.contains("tips.text")) {
          columns.add("tips.text");
          if(!tables.contains("tips")) {
            tables.add("tips");
          }
          if(!tt3.getText().isEmpty()) {
            where.add("tips.text='"+ tt3.getText() + "'");
          }
      }

      if(t4.isSelected() && !columns.contains("tips.date")) {
          columns.add("tips.date");
          if(!tables.contains("tips")) {
            tables.add("tips");
          }
          if(!tt4.getText().isEmpty()) {
            where.add("tips.date='"+ tt4.getText() + "'");
          }
      }

      if(t5.isSelected() && !columns.contains("tips.compliment_count")) {
          columns.add("tips.compliment_count");
          if(!tables.contains("tips")) {
            tables.add("tips");
          }
          if(!tt5.getText().isEmpty()) {
            where.add("tips.compliment_count='"+ tt5.getText() + "'");
          }
      }

      //Users Checkboxes
      if(u1.isSelected() && !columns.contains("users.user_id")) {
          columns.add("users.user_id");
          if(!tables.contains("users")) {
            tables.add("users");
          }
          if(!tu1.getText().isEmpty()) {
            where.add("users.user_id='"+ tu1.getText() + "'");
          }
      }

      if(u2.isSelected() && !columns.contains("users.name")) {
          columns.add("users.name");
          if(!tables.contains("users")) {
            tables.add("users");
          }
          if(!tu2.getText().isEmpty()) {
            where.add("users.name='"+ tu2.getText() + "'");
          }
      }

      if(u3.isSelected() && !columns.contains("users.review_count")) {
          columns.add("users.review_count");
          if(!tables.contains("users")) {
            tables.add("users");
          }
          if(!tu3.getText().isEmpty()) {
            where.add("users.review_count='"+ tu3.getText() + "'");
          }
      }

      if(u4.isSelected() && !columns.contains("users.yelping_since")) {
          columns.add("users.yelping_since");
          if(!tables.contains("users")) {
            tables.add("users");
          }
          if(!tu4.getText().isEmpty()) {
            where.add("users.yelping_since='"+ tu4.getText() + "'");
          }
      }

      if(u5.isSelected() && !columns.contains("users.elite")) {
          columns.add("users.elite");
          if(!tables.contains("users")) {
            tables.add("users");
          }
          if(!tu5.getText().isEmpty()) {
            where.add("users.elite='"+ tu5.getText() + "'");
          }
      }

      if(u6.isSelected() && !columns.contains("users.friends")) {
          columns.add("users.friends");
          if(!tables.contains("users")) {
            tables.add("users");
          }
          if(!tu6.getText().isEmpty()) {
            where.add("users.friends='"+ tu6.getText() + "'");
          }
      }

      if(u7.isSelected() && !columns.contains("users.fans")) {
          columns.add("users.fans");
          if(!tables.contains("users")) {
            tables.add("users");
          }
          if(!tu7.getText().isEmpty()) {
            where.add("users.fans='"+ tu7.getText() + "'");
          }
      }

      if(u8.isSelected() && !columns.contains("users.average_stars")) {
          columns.add("users.average_stars");
          if(!tables.contains("users")) {
            tables.add("users");
          }
          if(!tu8.getText().isEmpty()) {
            where.add("users.average_stars='"+ tu8.getText() + "'");
          }
      }

      //Create string
      ArrayList<String> busarr = new ArrayList<String>();
      ArrayList<String> userarr = new ArrayList<String>();
      ArrayList<String> botharr = new ArrayList<String>();
      String temp = "";
      String fullquery = "";
      if(!columns.isEmpty() && !tables.isEmpty()) {
        fullquery += "SELECT ";
        for(int i=0;i<columns.size();i++) {
          if(i == columns.size()-1) { fullquery += columns.get(i) + " ";}
          else { fullquery += columns.get(i) + ", ";}
        }



        fullquery += "FROM ";
        for(int i=0;i<tables.size();i++) {
          if(tables.get(i).equals("business") || tables.get(i).equals("checkin") || tables.get(i).equals("hours") || tables.get(i).equals("location")) {
            busarr.add(tables.get(i));
          }
          if(tables.get(i).equals("users") || tables.get(i).equals("elite_users")) {
            userarr.add(tables.get(i));
          }
          if(tables.get(i).equals("reviews") || tables.get(i).equals("tips")) {
            botharr.add(tables.get(i));
          }
        }
        if(tables.size()==1) { //only one table
          fullquery += tables.get(0) + " ";
        }
        else if(botharr.isEmpty()) { //cross join user and business
          for(int i=0;i<tables.size();i++) {
            if(i == tables.size()-1) { fullquery += tables.get(i) + " ";}
            else {fullquery += tables.get(i) + ", ";}
          }
        }
        else { //multiple tables inner join
          for(int i=0;i<botharr.size();i++)  {
            if(i == 0) {
              fullquery += botharr.get(i) + " ";
              temp = botharr.get(i);
              }
            else {
              fullquery += "INNER JOIN " + botharr.get(i) + " ON " + botharr.get(i) + ".business_id=" + temp + ".business_id and " + botharr.get(i) + ".user_id=" + temp + ".user_id ";
            }
          }

          for(int i=0;i<busarr.size();i++)  {
            fullquery += "INNER JOIN " + busarr.get(i) + " ON " + busarr.get(i) + ".business_id=" + temp + ".business_id ";
          }

          for(int i=0;i<userarr.size();i++)  {
            fullquery += "INNER JOIN " + userarr.get(i) + " ON " + userarr.get(i) + ".user_id=" + temp + ".user_id ";
          }
        }



        if(!where.isEmpty()) {
          fullquery += "where ";
          for(int i=0;i<where.size();i++) {
            if(i == where.size()-1) { fullquery += where.get(i) + " ";}
            else { fullquery += where.get(i) + " and ";}
          }
        }
        if(!tlim.getText().isEmpty()) {
          fullquery += "LIMIT " + tlim.getText();
        }
      }
      query.setText(fullquery);

      //Execute query
      String data = "";
      try {
        Statement stmt = conn.createStatement(); //create a statement object
        ResultSet result = stmt.executeQuery(fullquery); //send statement to DBMS
        int row = 0;
        while (result.next()) {
          if(row == 0) {
              for (int j=0;j<columns.size();j++) {
                if (j == columns.size()-1) { data += columns.get(j) + "\n"; }
                else { data += columns.get(j) + ", ";}
              }
            }
          for(int i=1;i<=columns.size();i++) {
            if (i == columns.size()) { data += result.getString(i) + "\n";}
            else { data += result.getString(i) + ", ";}
          }
          row++;
        }
      }
      catch (Exception e2) {
        e2.printStackTrace();
        JOptionPane.showMessageDialog(null,"Error accessing Database.");
      }


      // If 'Output to file' is selected, output to txt.
      if(out1.isSelected()) {
        try (PrintWriter writer = new PrintWriter(new File("output.txt"))) {
            writer.write(data);
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
      }

      JOptionPane.showMessageDialog(null,data);


    //closing the connection
      try {
        conn.close();
        JOptionPane.showMessageDialog(null,"Connection Closed.");
      } catch(Exception e3) {
        JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
      }
    }
  });

  but2.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
        columns.clear();
        tables.clear();
        where.clear();
    }
  });

  //Question 1
  JButton butq1 = new JButton("Enter Q1 Query");
  JLabel labq1 = new JLabel("Q1. Give 2 restaurants");
  JTextField q1text1 = new JTextField();
  JTextField q1text2 = new JTextField();
  JCheckBox outq1 = new JCheckBox("Output to File");
  labq1.setBounds(30,580,160,20); //x, y, width, height
  q1text1.setBounds(190,580,100,20);
  q1text2.setBounds(300,580,100,20);
  butq1.setBounds(410,580,160,20);
  outq1.setBounds(580,580,130,20);
  f.add(labq1); f.add(butq1); f.add(q1text1); f.add(q1text2); f.add(outq1);
  butq1.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
      Connection conn = null; //Building the connection
      try {
          Class.forName("org.postgresql.Driver");
          conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/section_904_d20",
            my.user, my.pswd);
      } catch (Exception e1) {
          e1.printStackTrace();
          System.err.println(e1.getClass().getName()+": "+e1.getMessage());
          System.exit(0);
      }
      JOptionPane.showMessageDialog(null,"Opened database successfully");


      //create hash table
      //create adjacency list business to users
      //create adjacency list users to businesses
      //BFS from restuarant to restaurant
      //it works

      //or query individually

      //create string WILL BE DIFFERENT
      String fullquery = "";
      fullquery += "SELECT business." + q1text1.getText() + " FROM business";
      System.out.println(fullquery);



      //execute query
      String data = "";
      try {
        Statement stmt = conn.createStatement(); //create a statement object
        ResultSet result = stmt.executeQuery(fullquery); //send statement to DBMS
        int row = 0;
        while (result.next()) {   //WILL BE DIFFERENT

        }
      }
      catch (Exception e2) {
        e2.printStackTrace();
        JOptionPane.showMessageDialog(null,"Error accessing Database.");
      }






      //output file
      if(outq1.isSelected()) {
        try (PrintWriter writer = new PrintWriter(new File("outputq1.txt"))) {
            writer.write(data);
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
      }
      JOptionPane.showMessageDialog(null,data);

      try {  //close connection
        conn.close();
        JOptionPane.showMessageDialog(null,"Connection Closed.");
      } catch(Exception e3) {
        JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
      }
    }
  });

  //Question 2
  String[] q2namelist = { "Jamie", "Nicole", "Danielle", "Butch", "Micah", "Gary", "Jagan", "Yazman", "Shell", "Pearl" };
 String[] q2idlist = { "OwjRMXRC0KyPrIlcjaXeFQ", "nIJD_7ZXHq-FX8byPMOkMQ", "V34qejxNsCbcgD8C0HVk-Q", "ofKDkJKXSKZXu5xJNGiiBQ", "UgMW8bLE0QMJDCkQ1Ax5Mg", "5vD2kmE25YBrbayKhykNxQ", "aq_ZxGHiri48TUXJlpRkCQ", "jOERvhmK6_lo_XGUBPws_w", "Vb2u6EcBCBf1_LJSHpiWMw", "s5j_CRBWDCCMDJ6r7AYqjQ" };
 JButton butq2 = new JButton("Enter Q2 Query");
 JLabel labq2 = new JLabel("Q2. Enter a user ID");
 JComboBox q2drop = new JComboBox(q2namelist);
 JLabel q2lim = new JLabel("Limit");
 JTextField q2textlim = new JTextField();
 JCheckBox outq2 = new JCheckBox("Output to File");
 labq2.setBounds(30,610,160,20); //x, y, width, height
 q2drop.setBounds(190,610,100,20);
 butq2.setBounds(410,610,160,20);
 q2lim.setBounds(600,610,40,20);
 q2textlim.setBounds(640,610,50,20);
 outq2.setBounds(700,610,130,20);
 f.add(labq2); f.add(butq2); f.add(q2drop); f.add(outq2); f.add(q2lim); f.add(q2textlim);
 butq2.addActionListener(new ActionListener(){
   public void actionPerformed(ActionEvent e){
     Connection conn = null; //Building the connection
     try {
         Class.forName("org.postgresql.Driver");
         conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/section_904_d20",
           my.user, my.pswd);
     } catch (Exception e1) {
         e1.printStackTrace();
         System.err.println(e1.getClass().getName()+": "+e1.getMessage());
         System.exit(0);
     }
     JOptionPane.showMessageDialog(null,"Opened database successfully");




     //create string
     String fullquery = "";
     fullquery += "SELECT * FROM q_two where q_two.name='" + q2drop.getSelectedItem() +  "' and q_two.user_id='" + q2idlist[q2drop.getSelectedIndex()] + "'";
     if(!q2textlim.getText().isEmpty()) {
         fullquery += " LIMIT " + q2textlim.getText();
     }
     System.out.println(fullquery);



     //execute query
     String data = "";
     try {
       Statement stmt = conn.createStatement(); //create a statement object
       ResultSet result = stmt.executeQuery(fullquery); //send statement to DBMS
       int row = 0;
       while (result.next()) {
         data += "Stars, Stars, Useful, Funny, Cool, Comment(s) \n";
         for(int i=1;i<=6;i++) {
           if (i == columns.size()) { data += result.getString(i) + "\n";}
           else { data += result.getString(i) + ", ";}
         }
       }
     }
     catch (Exception e2) {
       e2.printStackTrace();
       JOptionPane.showMessageDialog(null,"Error accessing Database.");
     }


     //output file
     if(outq2.isSelected()) {
       try (PrintWriter writer = new PrintWriter(new File("outputq2.txt"))) {
           writer.write(data);
       }
       catch (FileNotFoundException ex) {
           System.out.println(ex.getMessage());
       }
     }
     JOptionPane.showMessageDialog(null,data);

     try {  //close connection
       conn.close();
       JOptionPane.showMessageDialog(null,"Connection Closed.");
     } catch(Exception e3) {
       JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
     }
   }
 });


  //Question 3

  String[] stateAbbr = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
    "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN",
    "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK",
    "OR", "PA", "RI", "SC", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" };
  JButton butq3 = new JButton("Enter Q3 Query");
  JLabel labq3 = new JLabel("Q3. Give a US State");
  JComboBox<String> q3drop= new JComboBox<String>(stateAbbr);
  JCheckBox outq3 = new JCheckBox("Output to File");
  labq3.setBounds(30,640,180,20); //x, y, width, height
  q3drop.setBounds(190,640,50,20);
  butq3.setBounds(410,640,160,20);
  outq3.setBounds(580,640,130,20);
  f.add(labq3); f.add(butq3); f.add(q3drop); f.add(outq3);
  butq3.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){
      Connection conn = null; //Building the connection
      try {
          Class.forName("org.postgresql.Driver");
          conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/section_904_d20", my.user, my.pswd);

      } catch (Exception ex1) {
          ex1.printStackTrace();
          System.err.println(ex1.getClass().getName()+": "+ex1.getMessage());
          System.exit(0);
      }
      JOptionPane.showMessageDialog(null,"Opened database successfully");



      // Create query text
      String query = "";
      String selected = String.valueOf(q3drop.getSelectedItem()).toUpperCase();
      System.out.println(selected);
      query = "SELECT m.business_id, m.latitude, m.longitude, m.stars, m.name FROM business m INNER JOIN (SELECT name, COUNT(name), AVG(stars) FROM business WHERE categories similar to '%Restaurants%' GROUP BY name, stars HAVING COUNT(name)>1 and AVG(stars)>=3.5) n ON m.name=n.name WHERE state=\'"+selected+"\' ORDER BY m.name DESC";
      System.out.println(query);



      // String of ID's that have already been used
      ArrayList<String> idList = new ArrayList<String>();
      // Coordinates of every location
      Map<String, ArrayList<Pair<Double, Double>>> locations = new HashMap<>();
      //execute query
      String data = "";
      try {
        Statement stmt = conn.createStatement(); //create a statement object
        ResultSet result = stmt.executeQuery(query); //send statement to DBMS
        int row = 0;
        while (result.next()) {
            String id = result.getString("business_id");

            // Check if this is a duplicate value
            if (idList.contains(id)) {
                continue;
            }
            idList.add(id);

            // Put latitude and longitude in a tuple
            double lat = result.getDouble("latitude");
            double lon = result.getDouble("longitude");
            Pair<Double, Double> coord = new Pair<>(lon, lat);

            String bname = result.getString("name");
            // Add to a dictionary where values are a list of coordinates
            if (locations.containsKey(bname)) {
                locations.get(bname).add(coord);
            }
            else {
                locations.put(bname, new ArrayList<Pair<Double, Double>>());
                locations.get(bname).add(coord);
            }



          // if(row == 0) {
          //     for (int j=0;j<columns.size();j++) {
          //       if (j == columns.size()-1) { data += columns.get(j) + "\n"; }
          //       else { data += columns.get(j) + ", ";}
          //     }
          //   }
          // for(int i=1;i<=columns.size();i++) {
          //   if (i == columns.size()) { data += result.getString(i) + "\n";}
          //   else { data += result.getString(i) + ", ";}
          // }
          // row++;
        }

        // Holds the areas
        Map<String, Double> spans = new HashMap<>();
        // Traverse map and find area from all coordinates
        for (Map.Entry<String, ArrayList<Pair<Double, Double>>> fr : locations.entrySet()) {
            // calculate area from list of coordeinates
            double area = calcSpan(fr.getValue());
            // Add to map
            spans.put(fr.getKey(), new Double(area));
        }


        // Find the top 5
        // Sorting by value in Ascending order and getting last 5
        ArrayList<Map.Entry<String, Double>> list = new ArrayList<>(spans.entrySet());
        list.sort(Map.Entry.comparingByValue());

        int rank_size = Math.min(5, list.size());
        if (rank_size != 0) {
            for(int i=1; i<=rank_size; i++) {
                System.out.println("#"+i+":  "+list.get(list.size() - i).getKey()+" - "+list.get(list.size() - i).getValue()+" m^2");
            }
        }
        else {
            System.out.println("There are no restaurant franchises in this state satisfying the conditions.");
        }




        // // print out to see if working
        // for (Map.Entry<String, Double> ar : sorted.entrySet()) {
        //     System.out.print(ar.getKey() + ":");
        //     System.out.println(ar.getValue());
        // }



      }
      catch (Exception ex2) {
        ex2.printStackTrace();
        JOptionPane.showMessageDialog(null,"Error accessing Database.");
      }






      //output file
      if(outq3.isSelected()) {
        try (PrintWriter writer = new PrintWriter(new File("outputq3.txt"))) {
            writer.write(data);
        }
        catch (FileNotFoundException ex3) {
            System.out.println(ex3.getMessage());
        }
      }
      JOptionPane.showMessageDialog(null,data);

      try {  //close connection
        conn.close();
        JOptionPane.showMessageDialog(null,"Connection Closed.");
    } catch(Exception ex4) {
        JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
      }
    }
  });

  //Question 4 ----------------------------

  JButton butq4 = new JButton("Enter Q4 Query");
  JLabel labq4 = new JLabel("Q4. Enter a city");
  JTextField q4text1 = new JTextField();
  JCheckBox outq4 = new JCheckBox("Output to File");
  labq4.setBounds(30,670,160,20); //x, y, width, height
  q4text1.setBounds(190,670,100,20);
  butq4.setBounds(410,670,160,20);
  outq4.setBounds(580,670,130,20);
  f.add(labq4); f.add(butq4); f.add(q4text1); f.add(outq4);
  butq4.addActionListener(new ActionListener(){
    public void actionPerformed(ActionEvent e){

      Connection conn = null; //Building the connection
      try {
          Class.forName("org.postgresql.Driver");
          conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/section_904_d20",
            my.user, my.pswd);
      } catch (Exception e1) {
          e1.printStackTrace();
          System.err.println(e1.getClass().getName()+": "+e1.getMessage());
          System.exit(0);
      }
      JOptionPane.showMessageDialog(null,"Opened database successfully");


      //create string WILL BE DIFFERENT
      String fullquery = "";
      String city = q4text1.getText();
      fullquery += "SELECT distinct name FROM tips, business where business.business_id = tips.business_id and business.city = '" + q4text1.getText() + "' and business.categories similar to '%Restaurants%' group by business.name having count (*) = 1";
      System.out.println(fullquery);

      //execute query
      String data = "";
      try {
        Statement stmt = conn.createStatement(); //create a statement object
        ResultSet result = stmt.executeQuery(fullquery); //send statement to DBMS
        ArrayList<String> buis_names = new ArrayList<String>();
        while (result.next()) {   //WILL BE DIFFERENT
          buis_names.add(result.getString("name"));
        }


        int max = 0;
        int cur = 0;
        int max_idx = 0;
        for(int i = 0; i < buis_names.size(); i++){

          String buis_name2 = "";

          for(int j =0; j < buis_names.get(i).length(); j++){
            if(buis_names.get(i).charAt(j) == '\''){
              buis_name2 += '\'';
            }
            buis_name2 += buis_names.get(i).charAt(j);
          }


          String qury = "SELECT count (distinct name) from business, tips where business.business_id = tips.business_id and business.name = '" + buis_name2 + "' and business.city = '" + city + "'";
          Statement stmt2 = conn.createStatement(); //create a statement object
          ResultSet result2 = stmt2.executeQuery(qury); //send statement to DBMS
          while (result2.next()) {   //WILL BE DIFFERENT
            cur = Integer.parseInt(result2.getString("count"));

            if(cur > max){
              max = cur;
              max_idx = i;
            }
          }

        }
        System.out.println("Best restaurant is: " + buis_names.get(max_idx) + " with " + max +" tips");


      }
      catch (Exception e2) {
        e2.printStackTrace();
        JOptionPane.showMessageDialog(null,"Error accessing Database.");
      }


      //output file
      if(outq4.isSelected()) {
        try (PrintWriter writer = new PrintWriter(new File("outputq1.txt"))) {
            writer.write(data);
        }
        catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
      }
      JOptionPane.showMessageDialog(null,data);

      try {  //close connection
        conn.close();
        JOptionPane.showMessageDialog(null,"Connection Closed.");
      } catch(Exception e3) {
        JOptionPane.showMessageDialog(null,"Connection NOT Closed.");
      }
    }
    });






  }
}