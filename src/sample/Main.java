package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    static List<Treap> treaps;
    static VBox pane;


    public static void main(String[] args) {

        pane = new VBox();

        Scanner input = new Scanner(System.in);


        treaps = new ArrayList<>();

        while (true) {

            String data = input.nextLine();
            String[] tmp = data.split(" ");
            int count = Integer.parseInt(tmp[0]);

            if (count == 0)
                break;
            else {

                Treap treap = new Treap();

                for (int i = 1; i <= count; i++) {
                    String tempData = tmp[i].split("/")[0];
                    int tempNum = Integer.parseInt(tmp[i].split("/")[1]);

//                    Treap root = treap.getRoot();
                    treap.add(new Treap(tempNum, tempData));

                }

                treaps.add(treap);
            }
        }


        for (int i = 0; i < treaps.size(); i++)
            System.out.println(treaps.get(i).toString());

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {


        pane.setPadding(new Insets(10, 10, 10, 10));
        draw(treaps.get(0).getRoot(), 500, 30);


        Scene scene = new Scene(pane, 1000, 600, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    String printLevelOrder() {
        Treap root = treaps.get(0).getRoot();
        int h = root.findHeight(root);
        int i;
        StringBuilder levelOrder = new StringBuilder();
//        levelOrder.append("*");
        for (i = 0; i <= h; i++) {
            printGivenLevel(root, i, levelOrder);
            levelOrder.append("_");
        }

        return (levelOrder.toString());
    }

    void printGivenLevel(Treap root, int level, StringBuilder levelOrder) {

        if (root == null) {
            levelOrder.append("-" + " ");
            return;
        }
        if (level == 0)
            levelOrder.append(root.value + "/" + root.priority + " " );
        else if (level > 0) {
            printGivenLevel(root.left, level - 1, levelOrder);
            printGivenLevel(root.right, level - 1, levelOrder);
        }

    }

    void draw(Treap root, int centerX, int centerY) {


        String s = printLevelOrder();

        String[] level_order = s.split("_");

//        System.out.println(s);
//        System.out.println(Arrays.toString(level_order));


        for (int i = 0; i < level_order.length; i++) {
            String[] tmp = level_order[i].split(" ");
//            System.out.println(Arrays.toString(tmp));

            int rowChild = (int) Math.pow(2, i),c=0;
            HBox hBox = new HBox();


            for (int j = 0; j < tmp.length; j++) {
//            while (c<rowChild){

                if (tmp[j].equals("-")) {

                    Ellipse ellipse = new Ellipse(30,15);
                    ellipse.setFill(Color.WHITE);

                    hBox.getChildren().add(ellipse);

                } else {

                    Ellipse ellipse = new Ellipse(centerX, centerY, 30, 15);
                    ellipse.setFill(Color.GREEN);

                    Text text = new Text(tmp[j]);
                    text.setFill(Color.WHITE);

                    StackPane p = new StackPane(ellipse, text);

                    hBox.setAlignment(Pos.CENTER);
                    hBox.getChildren().addAll(p);

                }
            }

            pane.getChildren().add(hBox);
        }

/*
        Stack<Treap> s = new Stack<Treap>();

        int i = 0;

        while(!s.isEmpty() || root!=null)
        {
            if(root!=null)
            {
                centerX -= 80;
                centerY += 20;
//                System.out.print(root.value+":");
                Ellipse ellipse = new Ellipse(centerX, centerY, 30, 15);
                ellipse.setFill(Color.GREEN);

                Text text = new Text(root.node_toString());

                text.setX(ellipse.getCenterX() - 5);
                text.setY(ellipse.getCenterY() + 5);
                text.setFill(Color.WHITE);
                pane.getChildren().addAll(ellipse,text);

                s.push(root);
                root = root.left;
            }
            else
            {
                centerX += 80;
                centerY += 20;
                root = s.pop();
                root = root.right;
            }
        }*/

/*
        if (root == null)
            return;



        System.out.println(root.value + "/" + root.priority + "  " + centerX);

        draw(root.left,centerX,centerY);
        draw(root.right,centerX,centerY);

*/




       /* Random r = new Random();




        int x = r.nextInt(100-40)+40;

        draw(root.left, centerX - x, centerY + 40);


        Ellipse ellipse = new Ellipse(centerX, centerY, 30, 15);
        ellipse.setFill(Color.GREEN);

        Text text = new Text(root.node_toString());

        text.setX(ellipse.getCenterX() - 5);
        text.setY(ellipse.getCenterY() + 5);
        text.setFill(Color.WHITE);



        pane.getChildren().addAll(ellipse,text);


        draw(root.right, centerX + x, centerY + 40);
*/

    }


//    void draw(Treap root) {
//
//        Ellipse ellipse = new Ellipse(500, 40, 30, 15);
//        ellipse.setFill(Color.RED);
//
//        Text text = new Text(root.node_toString());
//        text.setFill(Color.WHITE);
//        text.setX(ellipse.getCenterX() - 5);
//        text.setY(ellipse.getCenterY() + 5);
//
//        pane.getChildren().addAll(ellipse, text);
//
//
//
//
//
//    }


}
