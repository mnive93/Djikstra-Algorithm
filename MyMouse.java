//<applet code="MyMouse.class" width=600 height=600></applet>
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.applet.*;
class Table//this is the table class that is used to find the shortest path using Dijsktra algo
{
    ArrayList<Integer> r = new ArrayList<Integer>();
ArrayList<Integer> r1=new ArrayList<Integer>();
    int[] known=new int[100];
    int[] dist=new int[100];
    int[] pv=new int[100];
    public Table(int n)
    {
        int i;
        for(i=1;i<=n;i++)
        {
            known[i]=0;
            dist[i]=999;
            pv[i]=0;
        }
    }//used for initializing all the values in constructor
    public  ArrayList pathcalc(int start,int n,int[][] ed,int end)
    {
        int i,j,small=start;
        dist[start]=0;
        pv[start]=start;
               for(;;)//infinite loop will continue till all the nodes are known!!
        {
            known[small]=1;
            displaytable(n);
            for(j=1;j<=n;j++)
            {

                if(known[j]==0 && ed[small][j]!=999)
                    if(dist[small] + ed[small][j] < dist[j])
                    {
                        pv[j]=small;
                        dist[j]=dist[small] + ed[small][j];
                    System.out.print("PVVVV:"+pv[j]);
                    }
            }
            small=smallest(n);
            if(small==-1)
                break;
        }
        for( i=1;i<=n;i++)
        {
            System.out.println("Parree:"+pv[i]);

        }
        j=0;
        r.add(end);
        while(true)//this is used to find the path like 1-2-4-5 and is added to the arraylist
        {

                               j+=1;
            r.add(pv[r.get(j-1)]);
            if(r.get(j)==start)
            {

                break;
            }

        }
        for(i=r.size()-1;i>=0;i--)
        {
            r1.add(r.get(i));
        }

          return r1;
    }
    public  void displaytable(int n)
    {
        int i;
        System.out.println("\nVertex Known distance previous\n");
        for(i=1;i<=n;i++)
        {
            System.out.println("\n\nV"+i+1+" \t "+known[i]+" \t "+dist[i]+"\t"+pv[i]);
        }
    }


    public int smallest(int n)//used to find the smallest node to find the shortest path
    {
        int i,min=999,index=-1;
        for(i=1;i<=n;i++)
        {
            if(known[i]==0 && dist[i]<min)
            {
                min=dist[i];
                index=i;
            }
        }
        System.out.println("SMALLLLLLL:"+index);
        return index;
    }
}


public class MyMouse extends JApplet implements Runnable// main class where the design part is done
{
Point p;
     int counter,flag=0;
    ArrayList<Integer> path = new ArrayList<Integer>();
    JComboBox com = new JComboBox() ;
    JComboBox com2 = new JComboBox() ;
JLabel l= new JLabel("DJIKSTRA ALGORITHM-FIND YOUR SHORTEST PATH FOR FREE :)");
    JTextField text= new JTextField(10);
    JSeparator separator = new JSeparator(JSeparator.VERTICAL);
JButton col = new JButton("CHOOSE COLOR");
//JPanel pa = new JPanel();  			
Color cc;
             Table ta;
    ArrayList <Double>a1 = new ArrayList<Double>();//used to store x co-ordinates of a point
    ArrayList<Double> a2 = new ArrayList<Double>();//used to store y co-ordinates of a point
    ArrayList<Double> a3 = new ArrayList<Double>();
    ArrayList<Double> a4 = new ArrayList<Double>();
    final static float dash1[] = {10.0f};

    Stroke drawingStroke = new BasicStroke(5.0f,
            BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND,
            10.0f, dash1, 0.0f);//to set the stroke value of a line for eg in this I am using Dashed line so using this i convert the line to dashed line
            String[] nodes = new String[100];
    JButton b=new JButton("FIND ROUTE");
    JButton edge=new JButton("DRAW EDGE");
     JPanel pane=new JPanel();
    JPanel pane2 = new JPanel();
    JPanel pane3 = new JPanel();

   public int x,y,x1,y1,x2,y2;
    int[][] matrix = new int[10][10];
   
	public void init()
	{
		
            for(int i=1;i<10;i++)
            {
                for(int j=1;j<10;j++)
                {
                    matrix[i][j]=999;
                }

            }//initially matrix is set to 999
setBounds(0,0,600,400);//the applet is set to size 600 and 400
setLayout(new BorderLayout());
        pane2.setBounds(0, 0, 600, 400);//this panel contains all the button and text box

        pane2.setBackground(Color.GRAY);
        pane2.setOpaque(true);
                        counter=0;
                      pane.setBackground(Color.PINK);
        pane.setBounds(200, 100, 400,200);
        pane.setOpaque(true);
pane3.setBounds(0,0,600,100);//this panel is where we draw the graph
addcomp(b,edge, col,com, com2,text, pane,pane2,separator,pane3,l);//using this function we add the panels and all other components into the Applet
        edge.addActionListener(new ActionListener() {//this is the action listener for draw edge button

            public void actionPerformed(ActionEvent e) {

                String one = (String) com.getSelectedItem();//in the combo box u select one node eg node :1
                String two = (String) com2.getSelectedItem();//u select the other node eg node:2 
                int a = Integer.parseInt(one);
                int b = Integer.parseInt(two);
                double xx1 = a1.get(a - 1);//gets x-co-ordinate of node:1
                double xx2 = a1.get(b - 1);//gets x-co-ordinate of node:2
                double yy1 = a2.get(a - 1);//gets y-co-ordinate of node:2
                double yy2 = a2.get(b - 1);//gets y-co-ordinate of node:2

                Graphics g = pane.getGraphics();//in the drawing panel u get the graphics object
                Graphics2D g2=(Graphics2D)g;
                g2.setColor(Color.BLACK);
                g2.setStroke(drawingStroke);
                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//this is used to draw smooth lines
                String t = text.getText();//u enter the weight in the text box
int x1=(int)xx1;
                int x2=(int)xx2;
                int y1=(int)yy1;
                int y2=(int)yy2;
           g2.draw(new Line2D.Double(xx1, yy1, xx2, yy2)//then line is drawn from node 1 to node 2
                );
     
                g2.drawString(t,(x1+x2)/2,((y1+y2)/2-15));
                matrix[a][b] = Integer.parseInt(t);
                matrix[b][a]=matrix[a][b];//the edge is added to matrix
                System.out.println(matrix[a][b]);
            }
        });
               b.addActionListener(new ActionListener() {//this is used to find the shortest path

                   public void actionPerformed(ActionEvent e) {
//Color c = cc.getColor();                       
System.out.println(counter);
                      ta=new Table(counter);
                       String start=(String)com.getSelectedItem();//set the start point in combobox1
                       String end=(String)com2.getSelectedItem();//set the end point in combobox2
                       int start1=Integer.parseInt(start);
                       int end1=Integer.parseInt(end);
                       path=ta.pathcalc(start1,counter,matrix,end1);//then using path calc calculate the shortest path

                       try {
                           animate(cc);//this is used to create animation
                       } catch (InterruptedException e1) {
                           e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                       }

                   }
               });
col.addActionListener(new ActionListener() {

                   public void actionPerformed(ActionEvent e) {
   			 //cc = new JColorChooser();
			Color c=JColorChooser.showDialog(null,"Choose Color.",Color.RED);
			cc=c;

               }//this is used to choose color
});
        pane.addMouseListener(new MouseAdapter() {//when ever u click in the panel this function is called and an ellipse in drawn
            public void mouseClicked(MouseEvent me) {
                counter++;
                p = me.getPoint();
                x = p.x;
                y = p.y;


                Graphics g = pane.getGraphics();
                Graphics2D g2=(Graphics2D)g;
                g2.setColor(Color.BLACK);
                Ellipse2D.Double ellipse = new Ellipse2D.Double(x,y,30,30);

                g2.fill(ellipse);
                 g2.drawString(Integer.toString(counter), x+30, y-10);
               System.out.println(x + y);
                a1.add(ellipse.getCenterX());
                a2.add(ellipse.getCenterY());
              a3.add(ellipse.getX());
                a4.add(ellipse.getY());
                nodes[counter - 1] = Integer.toString(counter);
              com.addItem(Integer.toString(counter));
                com2.addItem(Integer.toString(counter));



            }

        });

	}

    public void animate(Color c) throws InterruptedException {
        int flag=0;
        double xx3,yy3;
        Ellipse2D.Double ellipse1;
                  for(int i=0;i<path.size()-1;i++)
                  {
                      double xx1=a1.get((path.get(i))-1);//gets the x-co-ordinate of point1

                      double xx2=a1.get((path.get(i+1))-1);//gets the x-co-ordinate of point2
                      double yy1=a2.get((path.get(i))-1);//gets the y-co-ordinate of point1
                      double yy2=a2.get((path.get(i+1))-1);//gets the y-co-ordinate of point2
                      System.out.println(xx2+yy2);
                      Graphics g = pane.getGraphics();
                      Graphics2D g2 = (Graphics2D)g;
                      g2.setRenderingHint(
                              RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//used to draw smooth lines
                      g2.setColor(c);
                      g2.setStroke(drawingStroke);
                      if(flag==0)
                      {
                          xx3 =a3.get((path.get(i))-1);
                          yy3 =a4.get((path.get(i))-1);
                          flag=1;
                          ellipse1= new Ellipse2D.Double(xx3,yy3,30,30);

                          g2.fill(ellipse1);
                          try
                          {
                          Thread.sleep(1000);
                      }
                          catch (InterruptedException e1) {
                              e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                          }
                      }

                      xx3 =a3.get((path.get(i+1))-1);
                      yy3=a4.get((path.get(i+1))-1);

                      //g2.draw(new Line2D.Double(xx1, yy1, xx2, yy2));
int x1=(int)xx1;
int y1=(int)yy1;
int x2=(int)xx2;
int y2=(int)yy2;

line(g2,x1,y1,x2,y2);//using this line function we draw line slowly to create an animation effect
                              ellipse1= new Ellipse2D.Double(xx3,yy3,30,30);
                      g2.fill(ellipse1);

                      try {
                          Thread.sleep(2000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                      }
                  }
    }
    public void run()
    {

    } 
void putPixel(Graphics2D g2, int x, int y)
   {
System.out.println("pixeeee");
 Rectangle2D e= new Rectangle2D.Double(x,y,7,7);
      g2.fill(e); 
   }
public void line(Graphics2D g,int x,int y,int x2, int y2)//this is function is used to find the points that are there between node:1 and node:2 then we draw small small rectangles 
 {
    int w = x2 - x ;
    int h = y2 - y ;//this is called Bresenham's algorithm this algorithm is used to find the list of points in a Line.This is how the algorithm is so don't break your heads understanding this!!

    int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
    if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
    if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
    if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
    int longest = Math.abs(w) ;
    int shortest = Math.abs(h) ;
    if (!(longest>shortest)) {
        longest = Math.abs(h) ;
        shortest = Math.abs(w) ;
        if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
        dx2 = 0 ;            
    }
    int numerator = longest >> 1 ;
try
{
    for (int i=0;i<=longest;i++) {
        putPixel(g,x,y) ;//slowly it will draw rectangles
Thread.sleep(30);
        numerator += shortest ;
        if (!(numerator<longest)) {
            numerator -= longest ;
            x += dx1 ;
            y += dy1 ;
        } else {
            x += dx2 ;
            y += dy2 ;
        }
    }
}
catch(Exception e)
{
  e.printStackTrace(); 
}
}

    public void addcomp(JButton b,JButton edge,JButton col,JComboBox c1,JComboBox c2,JTextField text,JPanel pane,JPanel pane2,JSeparator sep,JPanel p,JLabel l)
    {//this is used to add all the components to the applet
        if(flag==0)
        {
            System.out.println("addingggg");
        pane2.add(b);
pane2.add(sep);
        pane2.add(edge);

pane2.add(sep);
pane2.add(col);

pane2.add(sep);
        pane2.add(c1);
        pane2.add(c2);
            pane2.add(text);
p.add(l);
            add(pane2,BorderLayout.NORTH );
            add(pane,BorderLayout.CENTER );
add(p,BorderLayout.SOUTH);
            flag=1;
        }
    }

    

}
