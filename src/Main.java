//TIP コードを<b>実行</b>するには、<shortcut actionId="Run"/> を押すか
// ガターの <icon src="AllIcons.Actions.Execute"/> アイコンをクリックします。
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
//買い物リスト管理アプリ
//1 登場人物の整理　リスト管理ちゃん　属性　追加したリスト　ふるまい　①リストに項目を追加できる　②リストの項目を削除できる　③リストに特定の項目があるか検索できる
//2 アプリの処理の流れ　①入力　やりたい操作をユーザーに決めてもらう　追加or削除or検索　②ユーザーが終了させるまで追加などを行う　③①の画面にも戻る
//3 どの文法を割り当てるか決める　①　Scannerとswitch文をつかう　②Scannerとif
public class Main {
    public static void main(String[] args) {
        List<String> shoppingList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("何をしますか？数値を入力してください（1:項目を追加　2:項目を削除　3:項目を検索 4:現在のリストの内容を表示 5:終了）");
            int choice = scanner.nextInt();
            if (choice == 5) break;
            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("追加したい項目を入力してください。追加を終えたい場合は終了と入力してください");
                        String add = scanner.next();
                        if (add.equals("終了")) {
                            System.out.println("追加を終了します。");
                            break;
                        } else {
                            shoppingList.add(add);
                        }
                    }
                    break;
                case 2:
                    if (shoppingList.isEmpty()) {
                        System.out.println("リストが空です");
                        break;
                    }
                    while (true) {
                        for (int i = 0; i < shoppingList.size(); i++) {
                            System.out.println((i + 1) + ": " + shoppingList.get(i));
                        }
                        System.out.println("削除するリストの番号を入力してください。削除を終えたい場合は終了と入力してください");
                        String judgement = scanner.next();
                        if (judgement.equals("終了")) break;
                        int deleteIndex = Integer.parseInt(judgement);
                        if (deleteIndex > 0 && deleteIndex <= shoppingList.size()) {
                            shoppingList.remove(deleteIndex - 1);
                        } else {
                            System.out.println("無効な番号です！リストにある番号を入力してください");
                        }

                    }
                    break;
                case 3:
                    if (shoppingList.isEmpty()) {
                        System.out.println("リストが空です");
                        break;
                    }
                        while (true) {
                            System.out.println("検索したい文字を入力してください。検索を終えたい場合は終了と入力してください");
                            String search = scanner.next();
                            if (search.equals("終了")) break;
                            if (shoppingList.contains(search)) {
                                System.out.println("見つかりました！");
                            } else {
                                System.out.println("見つかりませんでした。");
                            }
                        }
                        break;
                        case 4:
                            if (shoppingList.isEmpty()) {
                                System.out.println("リストが空です");
                                break;
                            }
                            for (int i = 0; i < shoppingList.size(); i++) {
                                System.out.println((i + 1) + ": " + shoppingList.get(i));
                                }
                                break;
                            default:
                                System.out.println("正しい数値を入力してください");



                            }

                    }


            }
        }
