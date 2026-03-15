import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class Main {
    public static void main(String[] args) {
        List<TodoItem> todoList = new ArrayList<>();
        loadFromFile(todoList);
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("\n1:追加, 2:完了にする, 3:表示, 4:削除 5:完了済みタスクを全て削除　9:終了");

            try {
                int choice = scanner.nextInt();

                if (choice == 9){
                    saveToFile(todoList); // 終了する直前に保存を実行
                    break;
                }


                switch (choice) {
                    case 1:
                        System.out.print("タスク名: ");
                        String name = scanner.next();
                        todoList.add(new TodoItem(name));
                        break;

                    case 2:
                        displayList(todoList); // メソッドを呼び出す
                        System.out.print("完了にする番号を入力してください: ");
                        int index = scanner.nextInt();
                        // リストから指定番号のデータを取り出して、中身（isDone）を書き換える
                        if (index >= 0 && index < todoList.size()) {
                            todoList.get(index).isDone = true;
                            System.out.println(todoList.get(index).taskName + "を完了にしました！");
                        }else{
                            System.out.println("エラー：その番号のタスクは存在しません。");
                        }
                        break;

                    case 3:
                        displayList(todoList); // メソッドを呼び出す
                        break;

                    case 4:
                        displayList(todoList);
                        System.out.print("削除する番号を入力してください");
                        int deleteIndex = scanner.nextInt();

                        if (deleteIndex >= 0 && deleteIndex < todoList.size()){
                            TodoItem removed = todoList.remove(deleteIndex);
                            System.out.println(removed.taskName + "を削除しました");
                        }else{
                            System.out.println("エラー:その番号のタスクは存在しません");
                        }
                        break;

                    case 5:
                        todoList.removeIf(item -> item.isDone);
                        System.out.println("完了済みのタスクをすべて削除しました。");
                        break;

                    default:
                        System.out.println("正しい数値を入力してください");
                        continue;

                }
            }catch (Exception e) {
                // エラー（文字入力など）を捕まえて、メッセージを出す
                System.out.println("エラー：数字以外は入力できません！");
                scanner.next(); // 読み飛ばし（これがないと無限ループになるので注意！）
            }
            }

            }
    public static void displayList(List<TodoItem> list) {
        System.out.println("--- 現在のTODOリスト ---");
        for (int i = 0; i < list.size(); i++) {
            TodoItem item = list.get(i);
            String status = item.isDone ? "完了" : "未完了";
            System.out.println(i + ": " + item.taskName + " [" + status + "]");
        }
    }

    public static void saveToFile(List<TodoItem> list) {
        // tryの中にかっこを書くと、使い終わった後に自動でファイルを閉じてくれる
        try (PrintWriter writer = new PrintWriter(new FileWriter("todo.txt"))) {
            for (TodoItem item : list) {
                // 「名前,完了状態」の形式で1行ずつ書き込む
                writer.println(item.taskName + "," + item.isDone);
            }
            System.out.println("ファイルに保存しました！");
        } catch (IOException e) {
            System.out.println("エラー：保存に失敗しました。" + e.getMessage());
        }
    }

    public static void loadFromFile(List<TodoItem> list) {
        try (BufferedReader reader = new BufferedReader(new FileReader("todo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 保存した時の「名前,完了状態」をカンマで分割する
                String[] parts = line.split(",");
                TodoItem item = new TodoItem(parts[0]);
                // 文字列の "true/false" を boolean型に変換してセット
                item.isDone = Boolean.parseBoolean(parts[1]);
                list.add(item);
            }
            System.out.println("以前のデータを読み込みました！");
        } catch (IOException e) {
            // 初回起動時などファイルがない場合は、そのまま次へ進む
            System.out.println("保存ファイルが見つかりません。新規リストを作成します。");
        }
    }



        }
