import java.time.LocalDate;
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
        List<Transaction> expenseList = new ArrayList<>();
        List<Transaction> incomeList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("【家計簿アプリ】「おかえりなさい。今日は何をしましょうか？」");
            System.out.println("\n1:支出を記録する, 2:収入を記録する, 3:現在の貯金額を表示する, 9:終了する");
            int choice = 0;
            try {
                choice = scanner.nextInt();
            }catch (Exception e) {
                System.out.println("【家計簿アプリ】「メニューは数字で入力してください」");
                System.out.println();
                scanner.next();
                continue;
            }


            if (choice == 9) {
                System.out.println("【家計簿アプリ】「またお待ちしております。」");
                break;
            }

            // 支出管理↓↓

            switch (choice) {
                case 1:
                    expenseLoop:
                    while (true) {
                        System.out.println("【家計簿アプリ】「支出を管理する項目を選んでください」");
                        System.out.println("1:入力する, 2:削除する, 3:支出の合計表示する, 9:ホームに戻る");
                        int subChoice = 0;
                        try {
                            subChoice = scanner.nextInt();
                        }catch (Exception e) {
                            System.out.println("【家計簿アプリ】「メニューは数字で入力してください」");
                            System.out.println();
                            scanner.next();
                            continue;
                        }



                        switch (subChoice) {
                            case 1:
                                while (true){
                                    try {
                                        System.out.print("【家計簿アプリ】「日付を入力しましょう 」(今日の場合は t を入力 / 指定する場合は 2024-03-10):");
                                        String dateInput = scanner.next();
                                        LocalDate date;
                                        if (dateInput.equals("t") || dateInput.equals("ｔ")) {
                                            date = LocalDate.now();
                                        } else {
                                            date = LocalDate.parse(dateInput);
                                        }

                                        //空の改行処理
                                        scanner.nextLine();

                                        System.out.print("【家計簿アプリ】「概要を入力しましょう」 (例: 無印化粧水):");
                                        String descriptionInput = scanner.nextLine();
                                        System.out.print("【家計簿アプリ】「金額を入力しましょう」 (例: 800):");
                                        int amountInput = scanner.nextInt();
                                        expenseList.add(new Transaction(date, descriptionInput, amountInput));
                                        System.out.println("【家計簿アプリ】「支出を記録しました！」");
                                        System.out.println();

                                        break ;



                                    }catch (Exception e) {
                                        System.out.println();
                                        System.out.println("【家計簿アプリ】「入力形式が間違っています　例に沿った入力をお願い致します。」");
                                        scanner.nextLine(); // 間違った入力データをクリアしてリセットする
                                        System.out.println();
                                    }
                                }
                                break ;


                            case 2:
                                while (true){
                                    try {
                                        if (expenseList.isEmpty()) {
                                            System.out.println("【家計簿アプリ】「申し訳ございません。現在は削除するデータがありません。」");
                                            System.out.println();
                                            break ;
                                        } else {
                                            // ② 現在のリストを番号付きで表示
                                            System.out.println("　【現在の支出リスト】　");
                                            for (int i = 0; i < expenseList.size(); i++) {
                                                Transaction item = expenseList.get(i);
                                                // (i + 1) で 1番、2番... と表示させる
                                                System.out.println((i + 1) + "番: " + item.date + " " + item.description + " " + item.amount + "円");
                                            }

                                            // ③ 番号を指定して削除
                                            System.out.print("【家計簿アプリ】「削除する番号を入力しましょう」: ");
                                            int deleteTarget = scanner.nextInt();

                                            // リストは0始まりなので、入力された番号から1を引いて削除
                                            expenseList.remove(deleteTarget - 1);
                                            System.out.println("【家計簿アプリ】「削除が完了しました！」");
                                            System.out.println();

                                            break;
                                        }
                                    }catch (Exception e){
                                        System.out.println();
                                        System.out.println("【家計簿アプリ】「入力形式が間違っています　例に沿った入力をお願い致します。」");
                                        scanner.nextLine(); // 間違った入力データをクリアしてリセットする
                                        System.out.println();
                                    }
                                }
                                break ;



                            case 3:
                                int totalExpense = 0;
                                if (expenseList.isEmpty()) {
                                    System.out.println("【家計簿アプリ】「申し訳ございません。現在は合計するデータがありません」");
                                    System.out.println();
                                    break;
                                } else {
                                    for (Transaction item : expenseList) {
                                        totalExpense += item.amount;
                                    }
                                }
                                System.out.println("【家計簿アプリ】「現在の支出の合計は" + totalExpense + "です」");
                                System.out.println();
                                break;

                            case 9:
                                System.out.println("【家計簿アプリ】「ホームに戻ります。」");
                                System.out.println();
                                break expenseLoop;


                            default:
                                System.out.println("【家計簿アプリ】「メニューにある数値を入力してください」");
                                System.out.println();
                                break;


                        }


                    }
                    //expenseLoop後すぐにswichも抜けるためのbreak
                    break;

                //収入管理↓↓

                case 2:
                    incomeLoop:
                    while (true) {
                        System.out.println("【家計簿アプリ】「収入を管理する項目を選んでください」");
                        System.out.println("1:入力, 2:削除, 3:収入の合計表示, 9:ホームに戻る");
                        int subChoice = scanner.nextInt();


                        switch (subChoice) {
                            case 1:
                                System.out.print("【家計簿アプリ】「日付を入力しましょう」 (今日の場合は t を入力 / 指定する場合は 2024-03-10):");
                                String dateInput = scanner.next();
                                LocalDate date;
                                if (dateInput.equals("t")|| dateInput.equals("ｔ")) {
                                    date = LocalDate.now();
                                } else {
                                    date = LocalDate.parse(dateInput);
                                }
                                //空の改行処理
                                scanner.nextLine();

                                System.out.print("【家計簿アプリ】「概要を入力してください」 (例: 給料):");
                                String descriptionInput = scanner.nextLine();
                                System.out.print("【家計簿アプリ】「金額を入力してください」 (例: 200000):");
                                int amountInput = scanner.nextInt();
                                incomeList.add(new Transaction(date, descriptionInput, amountInput));
                                System.out.println("収入を記録しました！");
                                System.out.println();

                                break;

                            case 2:
                                if (incomeList.isEmpty()) {
                                    System.out.println("【家計簿アプリ】「申し訳ございません。現在は削除するデータがありません。」");
                                    System.out.println();
                                    break ;
                                } else {
                                    // ② 現在のリストを番号付きで表示
                                    for (int i = 0; i < incomeList.size(); i++) {
                                        Transaction item = incomeList.get(i);
                                        // (i + 1) で 1番、2番... と表示させる
                                        System.out.println((i + 1) + "番: " + item.date + " " + item.description + " " + item.amount + "円");
                                    }

                                    // ③ 番号を指定して削除
                                    System.out.print("【家計簿アプリ】「削除する番号を入力しましょう」: ");
                                    int deleteTarget = scanner.nextInt();

                                    // リストは0始まりなので、入力された番号から1を引いて削除
                                    incomeList.remove(deleteTarget - 1);
                                    System.out.println("【家計簿アプリ】「削除が完了しました！」");
                                    System.out.println();

                                    break;
                                }


                            case 3:
                                int totalExpense = 0;
                                if (incomeList.isEmpty()) {
                                    System.out.println("【家計簿アプリ】「申し訳ございません。現在は合計するデータがありません」");
                                    System.out.println();
                                    break;
                                } else {
                                    for (Transaction item : incomeList) {
                                        totalExpense += item.amount;
                                    }
                                }
                                System.out.println("【家計簿アプリ】「現在の支出の合計は" + totalExpense + "です」");
                                System.out.println();


                                break;


                            case 9:
                                System.out.println("【家計簿アプリ】「ホームに戻ります。」");
                                System.out.println();
                                break incomeLoop;

                            default:
                                System.out.println("【家計簿アプリ】「メニューにある数値を入力してください」");
                                System.out.println();
                                break;
                        }
                    }
                    //incomeLoop後すぐにswichも抜けるためのbreak
                    break ;
                case 3:
                    int balance = 0;
                    int balance2 = 0;
                    int totalBalance = 0;
                    for (Transaction item : expenseList) {
                        balance += item.amount;
                    }
                    for (Transaction item : incomeList) {
                        balance2 += item.amount;
                    }
                    totalBalance = balance2 - balance;
                    if (totalBalance <= 0) {
                        System.out.println("【家計簿アプリ】「現在の貯金額は" + totalBalance + "円です」");
                        System.out.println("【家計簿アプリ】「現在はあまり貯金ができてないようです、これから頑張っていきましょう！」");
                    } else {
                        System.out.println("【家計簿アプリ】「現在の貯金額は" + totalBalance + "円です」");
                        System.out.println("【家計簿アプリ】「順調に貯金できています！素晴らしいですね！」");
                    }
                    System.out.println();
                    break;

                default:
                    System.out.println("【家計簿アプリ】「メニューにある数値を入力してください」");
                    System.out.println();
                    break;





            }


        }


    }
}





